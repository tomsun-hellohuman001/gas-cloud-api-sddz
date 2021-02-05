package com.sddz.gasstation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan({"com.sddz.gasstation.dao.mapper"})
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableScheduling
@EnableCaching
@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = BaseBatchRepositoryImpl.class)
public class GasstationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasstationApplication.class, args);
    }

}
