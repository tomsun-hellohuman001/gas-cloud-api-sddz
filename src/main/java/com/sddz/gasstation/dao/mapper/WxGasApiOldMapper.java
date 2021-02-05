package com.sddz.gasstation.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public interface WxGasApiOldMapper {
	public List<Map<String,Object>> getGasStation(HashMap<String, Object> paramap);

	public Map<String,Object> getOrderDetail(HashMap<String, Object> paramap);

	public void userLocationLog(HashMap<String, Object> paramap);

	List<Map<String,Object>> getgunsBygas(HashMap<String, Object> paramap);

	public Map integralcon(HashMap<String, Object> paramap);

	public List<Map<String,Object>> getintegral(HashMap<String, Object> paramap);

    public List getUserByPhone(Map para);

	void insertPointHis(Map para);

	void updatePointDelHis(Map para);

    public  List<Map> getinteProductList(HashMap<String, Object> paramap);

	public List<Map> getintecateList(HashMap<String, Object> paramap);

	public Map getinteProductDetail(HashMap<String, Object> paramap);

    public List<Map<String,Object>> getAdminGasStation(HashMap<String, Object> paramap);
    public Map getGoodsName(Long id);
}
