package egovframework.nicc.hne.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.basic.model.dto.BasicSettingDTO;
import egovframework.nicc.hne.basic.model.entity.BasicSetting;
import egovframework.nicc.hne.basic.service.BasicSettingService;
import egovframework.nicc.hne.basic.service.BasicSettingService.TooManySettings;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/basicsetting")
public class BasicSettingController {
	final static String BASICSETTING_DIR = "basicSetting";

	@Autowired
	BasicSettingService settingService;
	@Autowired
	VolService volService;
	@Autowired
	FileService fileService;

	@GetMapping("/manage")
	public void goList() {
		log.info("/basicsetting/manage");
	}

	// REST

	@GetMapping("")
	@ResponseBody
	public BasicSettingDTO read(@RequestParam BasicSetting.Type settingType) {
		log.info("/basisetting/{}", settingType);
		return settingService.getSetting(settingType);
	}

	@PostMapping("/insert")
	@ResponseBody
	public void insert(@RequestBody BasicSettingDTO settingDTO) throws Exception {
		log.info("/basisetting/insert {}", settingDTO);
		try {
			settingService.insertSetting(settingDTO);
			volService.updateClientVol(VolService.BASIC);
		} catch (TooManySettings e) {
			fileService.deleteFile(settingDTO.getSettingFilename(), BASICSETTING_DIR);
			throw new Exception();
		}
	}

	@PutMapping("/update")
	@ResponseBody
	public void update(@RequestBody BasicSettingDTO basicImageDTO) throws Exception {
		log.info("/basisetting/update {}", basicImageDTO);
		settingService.updateSettingOriginFilename(basicImageDTO);
		String filename = settingService.updateSettingFilename(basicImageDTO);
		System.out.println("디버그!!"+filename);
		fileService.deleteFile(filename, BASICSETTING_DIR);
		volService.updateClientVol(VolService.BASIC);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void delete(@RequestParam Integer settingCd) throws Exception {
		log.info("/basisetting/delete?settingCd={}", settingCd);
		String filename = settingService.deleteSetting(settingCd);
		fileService.deleteFile(filename, BASICSETTING_DIR);
		volService.updateClientVol(VolService.BASIC);
	}
}
