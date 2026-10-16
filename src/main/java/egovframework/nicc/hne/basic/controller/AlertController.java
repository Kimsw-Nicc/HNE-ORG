package egovframework.nicc.hne.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.basic.model.dto.BasicAlertDTO;
import egovframework.nicc.hne.basic.service.AlertService;
import egovframework.nicc.hne.client.service.VolService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/alert/*")
@Log4j2
public class AlertController {
	final static int ALERT_PAGE_SIZE = 6;

	@Autowired
	AlertService alertService;
	@Autowired
	VolService volService;

	@GetMapping("/list")
	public void goList() {
		log.info("/alert/list ");
	}

	@GetMapping("/insert")
	public void goInsert() {
		log.info("goInsert");
	}

	@GetMapping("/update")
	public void goModifyPage() {
		log.info("/list/update");
	}
	
	// REST

	@GetMapping("/all")
	@ResponseBody
	public List<BasicAlertDTO> getAlerts(
			@PageableDefault(page = 0, size = ALERT_PAGE_SIZE, sort = "alertNdt", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "") String keyword, HttpServletRequest request) {
		log.info("/alert/all {}, {}", pageable, keyword);
		return alertService.getAlerts(keyword, pageable);
	}

	@GetMapping("/{alertId}")
	@ResponseBody
	public BasicAlertDTO getAlert(@PathVariable Integer alertId) {
		log.info("/alert/{}", alertId);
		return alertService.getAlert(alertId);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getMethodName(@RequestParam(defaultValue = "") String keyword) {
		return alertService.getCount(keyword);
	}

	@PutMapping("/update")
	@ResponseBody
	public void doModifyPage(@RequestBody BasicAlertDTO alertDTO) {
		log.info("/alert/update : {}", alertDTO);
		alertService.updateAlertCategory(alertDTO);
		alertService.updateAlertType(alertDTO);
		alertService.updateAlertDate(alertDTO);
		alertService.updateAlertContent(alertDTO);
		alertService.updateAlertVisible(alertDTO);
		volService.updateClientVol(VolService.ALERT);
	}

	@PostMapping("/insert")
	@ResponseBody
	public void doInsert(@RequestBody BasicAlertDTO alertDTO) {
		log.info("/alert/insert : {}", alertDTO);
		alertService.insertAlert(alertDTO);
		volService.updateClientVol(VolService.ALERT);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void remove(@RequestParam Integer alertCd) {
		log.info("/alert/delete/{}", alertCd);
		alertService.deleteAlert(alertCd);
		volService.updateClientVol(VolService.ALERT);
	}

}
