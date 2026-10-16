package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicAlertDTO;
import egovframework.nicc.hne.basic.model.entity.BasicAlert;
import egovframework.nicc.hne.basic.repo.BasicAlertRepo;
import egovframework.nicc.hne.basic.service.AlertService;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class AlertService {

	@Autowired
	BasicAlertRepo alertRepo;
	@Autowired
	ComOrgRepo comOrgRepo;

	@Autowired
	ModelConverter modelConverter;

	public List<BasicAlertDTO> getAlerts(String keyword, Pageable pageable) {
		Page<BasicAlert> alertPages = alertRepo.findByAlertContentContaining(keyword, pageable);
		return modelConverter.mapList(alertPages.getContent(), BasicAlertDTO.class);
	}

	public List<BasicAlertDTO> getAlerts() {
		return modelConverter.mapList(alertRepo.findAll(), BasicAlertDTO.class);
	}

	public BasicAlertDTO getAlert(Integer alertCd) {
		return modelConverter.map(alertRepo.findOne(alertCd), BasicAlertDTO.class);
	}

	public void updateAlertCategory(BasicAlertDTO alertDTO) {
		BasicAlert alert = alertRepo.findOne(alertDTO.getAlertCd());
		alert.setAlertCategory(alertDTO.getAlertCategory());
		alertRepo.save(alert);
	}

	public void updateAlertType(BasicAlertDTO alertDTO) {
		BasicAlert alert = alertRepo.findOne(alertDTO.getAlertCd());
		alert.setAlertType(alertDTO.getAlertType());
		alertRepo.save(alert);
	}

	public void updateAlertDate(BasicAlertDTO alertDTO) {
		BasicAlert alert = alertRepo.findOne(alertDTO.getAlertCd());
		alert.setAlertStartDate(alertDTO.getAlertStartDate());
		alert.setAlertEndDate(alertDTO.getAlertEndDate());
		alert.setAlertStartTime(alertDTO.getAlertStartTime());
		alert.setAlertEndTime(alertDTO.getAlertEndTime());
		alertRepo.save(alert);
	}

	public void updateAlertContent(BasicAlertDTO alertDTO) {
		BasicAlert alert = alertRepo.findOne(alertDTO.getAlertCd());
		alert.setAlertContent(alertDTO.getAlertContent());
		alertRepo.save(alert);
	}

	public void updateAlertVisible(BasicAlertDTO alertDTO) {
		BasicAlert alert = alertRepo.findOne(alertDTO.getAlertCd());
		alert.setAlertVisible(alertDTO.getAlertVisible());
		alertRepo.save(alert);
	}

	public void insertAlert(BasicAlertDTO alertDTO) {
		BasicAlert alert = modelConverter.map(alertDTO, BasicAlert.class);
		alertRepo.save(alert);
	}

	public void deleteAlert(Integer alertCd) {
		alertRepo.delete(alertCd);
	}

	public int getCount(String keyword) {
		return alertRepo.countAll(keyword);
	}

}
