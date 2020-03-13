package com.ssp.share;

import com.ssp.share.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShareApplication {

    static {
        PortUtil.checkPort(6379,"Redis 服务端",true);
    }

    public static void main(String[] args) {
        SpringApplication.run(ShareApplication.class, args);
    }

}
