package com.tank.config;

import com.tank.constants.Comment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;

/**
 * @author tank198435163.com
 */
@Configuration
@Comment(desc = "数据初始化,@PostConstruct和CommandLineRunner是冲突的,只能二选一")
public class InitConfig {


  @PostConstruct
  public void init() {
    System.out.println("*****************InitConfig*****************");
  }

  @Bean("dateFormatter")
  public DateTimeFormatter initDateFormatter() {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return df;
  }

  @Bean("dateTimeFormatter")
  public DateTimeFormatter initDateTimeFormatter() {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return df;
  }
}
