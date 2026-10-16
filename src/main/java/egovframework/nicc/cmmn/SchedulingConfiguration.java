package egovframework.nicc.cmmn;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulingConfiguration {

  @Bean
  public ThreadPoolTaskScheduler scheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(8);
    taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    return taskScheduler;
  }

}