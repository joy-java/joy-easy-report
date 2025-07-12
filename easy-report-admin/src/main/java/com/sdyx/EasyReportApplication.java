package com.sdyx;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 启动程序
 *
 * @author EasyReport
 */
@SpringBootApplication(scanBasePackages = "com.sdyx",exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class EasyReportApplication  implements ApplicationRunner {

    @Resource
    private ApplicationContext context;
   // @Resource
  //  private RequestMappingHandlerMapping requestMappingHandlerMapping;
    public static void main(String[] args) {
        log.info("EasyReport开始启动");
        SpringApplication.run(EasyReportApplication.class, args);
        log.info("EasyReport启动成功");
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("当前运行模式: {}",
                context instanceof ServletWebServerApplicationContext ? "嵌入式容器" : "外部容器");
        if (context instanceof ServletWebServerApplicationContext) {
            WebServer webServer = ((ServletWebServerApplicationContext) context).getWebServer();
            if (webServer != null) {
                log.info("嵌入式Tomcat端口: {}", webServer.getPort());
            } else {
                log.info("当前运行在外部Tomcat，端口由Tomcat配置决定");
            }
        }
        log.info("===========================================================================");
        log.info("@2025-"+ DateUtil.year(new Date()) +" Application EasyReport started successfully.");
        log.info("===========================================================================");
        log.info("========== EasyReport Running Now ==========");
       /* requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> {
            log.info("Mapped URL: {} -> {}", key, value);
        });*/
    }
}
