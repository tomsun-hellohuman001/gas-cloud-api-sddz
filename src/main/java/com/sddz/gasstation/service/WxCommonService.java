package com.sddz.gasstation.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sddz.gasstation.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@Slf4j
public class WxCommonService {
    private PoolingHttpClientConnectionManager poolConnManager;

    private int maxTotalPool = 100;
    private int maxConPerRoute = 20;

    private int socketTimeout = 30000;

    private int connectionRequestTimeout = 8000;

    private int connectTimeout = 8000;

    private String access_token = "";

    private Long expireTime = 0L;

    /**
     * jsTicket
     */
    private String jsTicket;
    private String apiTicket;


    /**
     * jsTicket超时时间
     */
    private long jsTicketExpire = 0;
    private long apiTicketExpire = 0;


    @PostConstruct
    private void init() {
        try {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();

            X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // Increase max total connection to 200
            poolConnManager.setMaxTotal(maxTotalPool);
            // Increase default max connection per route to 20
            poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();
            poolConnManager.setDefaultSocketConfig(socketConfig);
        } catch (Exception e) {
            log.error("InterfacePhpUtilManager init Exception" + e.toString());
        }
    }

    public String getAccessToken(String appId, String secret) {

        //long currentTime = System.currentTimeMillis() / 1000;
        //if (expireTime > currentTime) {
        //    return "{\"access_token\":\"" + access_token + "\"}";
        //}
        CloseableHttpClient httpClient = getHttpClient();
        Map<String, String> params = new HashMap();
        params.put("grant_type", "client_credential");
        params.put("appid", appId);
        params.put("secret", secret);
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
        HttpResponse response = doGet(httpClient, accessTokenUrl, params);
        String result = null;
        if (response != null) {
            try {
                result = EntityUtils.toString(response.getEntity());
                log.debug("WechatCommonService > getAccessToken > result" + result);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        JsonElement access = JSONUtils.fromJson(result);
        JsonElement errode = access.getAsJsonObject().getAsJsonPrimitive("errcode");
        if (errode == null) {
            access_token = access.getAsJsonObject().getAsJsonPrimitive("access_token").getAsString();
            expireTime = access.getAsJsonObject().getAsJsonPrimitive("expires_in").getAsLong()
                    + System.currentTimeMillis() / 1000 - 60;
        }
        return result;
    }

    /**
     * 获取临时二维码 ticket,有效时间设为30天
     *
     * @param accessToken
     * @param
     * @return
     */
    public String getTicket(String accessToken, String uuid) {
        Long UUID = Long.valueOf(Long.parseLong(uuid));
        String getticketurl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        String json = "{\"expire_seconds\":" + 2592000
                + " , \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + UUID + "}}}";
        HttpResponse response = doPost(getticketurl, null, json);
        String result;
        if (response != null) {
            try {
                result = EntityUtils.toString(response.getEntity());
                log.debug("result" + result);
            } catch (IOException e) {
                result = "{\"errcode\":\"9999\",\"errmsg\":\"获取ticket失败\"}";
                log.error(e.getMessage(), e);
            }
        } else {
            result = "{\"errcode\":\"9999\",\"errmsg\":\"获取ticket失败\"}";
        }
        return result;
    }

    /**
     * 获取js_ticket值
     *
     * @param accessToken access_token
     * @return
     */
    public String getJsTicket(String accessToken) {
        long currentTime = System.currentTimeMillis();
        if (jsTicketExpire <= currentTime) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
                    + "&type=jsapi";
            HttpResponse response = doGet(url, null);
            try {
                String result = EntityUtils.toString(response.getEntity());
                JsonElement jsonElement = JSONUtils.fromJson(result);
                JsonObject root = jsonElement.getAsJsonObject();
                String errorCode = root.getAsJsonPrimitive("errcode").getAsString();
                if (StringUtils.equals("0", errorCode)) {
                    jsTicket = root.getAsJsonPrimitive("ticket").getAsString();
                    currentTime = System.currentTimeMillis() - 60000;
                    jsTicketExpire = currentTime + root.getAsJsonPrimitive("expires_in").getAsInt() * 1000;
                } else {
                    log.error("获取js-ticket失败:" + result);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        log.debug("js ticket is: {}", jsTicket);
        return jsTicket;

    }

    /**
     * 获取api_tickt值
     *
     * @param accessToken access_token
     * @return
     */
    public String getapiTicket(String accessToken) {
        long currentTime = System.currentTimeMillis();
        if (apiTicketExpire <= currentTime) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
                    + "&type=wx_card";
            HttpResponse response = doGet(url, null);
            try {
                String result = EntityUtils.toString(response.getEntity());
                JsonElement jsonElement = JSONUtils.fromJson(result);
                JsonObject root = jsonElement.getAsJsonObject();
                String errorCode = root.getAsJsonPrimitive("errcode").getAsString();
                if (StringUtils.equals("0", errorCode)) {
                    apiTicket = root.getAsJsonPrimitive("ticket").getAsString();
                    currentTime = System.currentTimeMillis() - 60000;
                    apiTicketExpire = currentTime + root.getAsJsonPrimitive("expires_in").getAsInt() * 1000;
                } else {
                    log.error("获取api-ticket失败:" + result);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return apiTicket;

    }

    /**
     * 执行get请求
     *
     * @return
     */
    public HttpResponse doGet(String url, Map<String, String> params) {
        CloseableHttpClient client = getHttpClient();
        return doGet(client, url, params);
    }

    /**
     * 执行post请求
     *
     * @param url      链接地址
     * @param params   参数
     * @param formData 请求数据
     * @return请求结果
     */
    public HttpResponse doPost(String url, Map<String, String> params, String formData) {
        CloseableHttpClient client = getHttpClient();
        log.debug("signUrl" + url);
        log.debug("formData" + formData);
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(buildEntity(params, formData));
            log.debug("postEntity" + EntityUtils.toString(post.getEntity()));
            return client.execute(post);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            //IOUtils.closeQuietly(client);
        }
        return null;
    }

    private HttpEntity buildEntity(Map<String, String> params, String formData) throws UnsupportedEncodingException {
        HttpEntity entity = null;
        if (StringUtils.isNotEmpty(formData)) {
            entity = new StringEntity(formData, ContentType.APPLICATION_JSON);
        } else {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            builPrams(nvps, params);
            entity = new UrlEncodedFormEntity(nvps);
        }
        return entity;
    }

    private void builPrams(List<NameValuePair> nvps, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
    }


    private HttpResponse doGet(CloseableHttpClient httpClient, String url, Map<String, String> params) {

        if (StringUtils.isEmpty(url)) {
            return null;
        }
        int bufferSize = url.length();
        if (MapUtils.isNotEmpty(params)) {
            bufferSize += params.size() * 10;
        }

        StringBuffer urlBuffer = new StringBuffer(bufferSize);
        urlBuffer.append(url);
        if (urlBuffer.indexOf("?") == -1) {
            if (MapUtils.isNotEmpty(params)) {
                urlBuffer.append('?');
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlBuffer.append(encodeUrl(entry.getKey())).append('=').append(encodeUrl(entry.getValue()))
                            .append('&');
                }
                urlBuffer.deleteCharAt(urlBuffer.length() - 1);
            }
        } else {
            if (urlBuffer.indexOf("?") == urlBuffer.length() - 1) {
                if (MapUtils.isNotEmpty(params)) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        urlBuffer.append(encodeUrl(entry.getKey())).append('=').append(encodeUrl(entry.getValue()))
                                .append('&');
                    }
                    urlBuffer.deleteCharAt(urlBuffer.length() - 1);
                } else {
                    urlBuffer.deleteCharAt(urlBuffer.length() - 1);
                }
            } else {
                if (MapUtils.isNotEmpty(params)) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        urlBuffer.append('&').append(encodeUrl(entry.getKey())).append('=')
                                .append(encodeUrl(entry.getValue()));
                    }
                }
            }
        }
        HttpGet httpGet = new HttpGet(urlBuffer.toString());
        try {
            log.debug(urlBuffer.toString());
            return httpClient.execute(httpGet);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            //IOUtils.closeQuietly(httpClient);
        }
        return null;
    }

    private String encodeUrl(String key) {
        try {
            return URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage(), e);
        }
        return key;
    }

    private CloseableHttpClient getHttpClient() {
        if (poolConnManager == null) {
            init();
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig).build();
        if (poolConnManager != null && poolConnManager.getTotalStats() != null) {
            log.info("now client pool " + poolConnManager.getTotalStats().toString());
        }
        return httpClient;
    }


    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getAccessTokenCache(String appId, String secret) {
        log.debug("getAccessTokenCache:access_token expire_time=" + expireTime);
        long currentTime = System.currentTimeMillis();
        if (expireTime == 0 || currentTime / 1000 > expireTime) {
            getAccessToken(appId, secret);
        }
        log.debug("getAccessTokenCache:access_token=" + access_token);

        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenIdUrl(String appid, String basePath, String state,String sourceType) throws UnsupportedEncodingException {

        StringBuffer urlBf = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
        urlBf.append("?appid=" + appid);

        StringBuffer url = new StringBuffer(basePath + "/wechat/mall/openIdCallBack?sourceType="+sourceType);
        String urlEncode = URLEncoder.encode(url.toString(), "UTF-8");

        urlBf.append("&redirect_uri=").append(urlEncode);
        urlBf.append("&response_type=code");
        urlBf.append("&scope=snsapi_base");
        if (StringUtils.isNotBlank(state)) {
            urlBf.append("&state=").append(state);
        }
        urlBf.append("#wechat_redirect");

        log.debug("wxUserInfo >  getOpenId > URL:" + urlBf.toString());
        log.debug("wxUserInfo >  getOpenId > URL:" + urlEncode);
        return urlBf.toString();
    }

    public Map<String, String> jssdkSign(String AppId,String url, String jsTicket) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("timestamp", generateTimestamp());
        result.put("nonceStr", generateNoncestr());
        doJssdkSign(AppId,url, jsTicket, result);
        return result;
    }
    public String generateTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public String generateNoncestr() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private void doJssdkSign(String AppId,String url, String api_ticket, Map<String, String> result) {
        //注意这里参数名必须全部小写，且必须有序
        String unSign = "jsapi_ticket=" + api_ticket +
                "&noncestr=" + result.get("nonceStr") +
                "&timestamp=" + result.get("timestamp") +
                "&url=" + url;


        String sign = sha1String(unSign);
        result.put("signature", sign);
        result.put("appid", AppId);

        result.put("url", url);
    }

    public String sha1String(String unsign) {
        log.debug("sha1 sign src: {}", unsign);
        byte[] signByte = sha1(unsign);
        String result = null;
        if (signByte != null) {
            result = Hex.encodeHexString(signByte);
            log.debug("sha1 sign result: {}", result);
        }
        return result;
    }
    public byte[] sha1(String unsign) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(unsign.getBytes("UTF-8"));
            return crypt.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
