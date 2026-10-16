package egovframework.nicc.hne.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.global.model.entity.GlobalToggle;
import egovframework.nicc.hne.global.model.enumeration.GlobalType;
import egovframework.nicc.hne.global.repo.GlobalToggleRepo;
import egovframework.nicc.hne.global.service.ToggleService;

@Service
public class ToggleService {
	@Autowired
	GlobalToggleRepo globalToggleRepo;

	public Boolean toggle(GlobalType type) {
		GlobalToggle globalToggle = globalToggleRepo.findOne(type);
		globalToggle.setToggleUse(!globalToggle.getToggleUse());
		globalToggleRepo.save(globalToggle);
		return globalToggle.getToggleUse();
	}

	public Boolean isToogled(GlobalType type) {
		return globalToggleRepo.findOne(type).getToggleUse();
	}
}
