package egovframework.nicc.hne.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.nicc.hne.global.model.enumeration.GlobalType;
import egovframework.nicc.hne.global.service.ToggleService;


@RestController
@RequestMapping("/toggle")
public class ToggleController {

  @Autowired
  ToggleService toggleService;
  
  @GetMapping("/{type}")
  public Boolean getToggle(@PathVariable GlobalType type) {
  	return toggleService.isToogled(type);
  }
  
  @PutMapping("/{type}")
  public Boolean updateToggle(@PathVariable GlobalType type) {
    return toggleService.toggle(type);
  }
}
