package egovframework.nicc.hne.util;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.nicc.hne.sync.service.SyncService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Initializer {
  
  @Autowired
  SyncService syncService;
  
  @PostConstruct
  public void init(){
    try {
      log.info("init start");
      syncService.changeCron();
    } catch (Exception e) {
      log.info("init fail");
    }
  }
}
