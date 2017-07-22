package org.jufe.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rao-mengnan on 2017/7/16.
 */
@SpringBootApplication(scanBasePackages = {"org.jufe.erp"})
@EnableAutoConfiguration
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
