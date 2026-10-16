package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicSettingDTO;
import egovframework.nicc.hne.basic.model.entity.BasicSetting;
import egovframework.nicc.hne.basic.repo.BasicSettingRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class BasicSettingService {

	@Autowired
	BasicSettingRepo settingRepo;

	@Autowired
	ModelConverter modelConverter;

	public class TooManySettings extends Exception {
		private static final long serialVersionUID = 1L;
	}

	public BasicSettingDTO getSetting(BasicSetting.Type type) {
		List<BasicSetting> basicSettings = settingRepo.findAllBySettingType(type);
		// 일단은 1개의 로고, 슬로건만 사용하니, 첫먼째만 반환하도록 하자
		if (basicSettings.size() == 0) {
			return null;
		} else {
			return modelConverter.map(basicSettings.get(0), BasicSettingDTO.class);
		}
	}

	public List<BasicSettingDTO> getSettings() {
		return modelConverter.mapList(settingRepo.findAll(), BasicSettingDTO.class);
	}

	public void insertSetting(BasicSettingDTO basicSettingDTO) throws TooManySettings {
		List<BasicSetting> basicSettings = settingRepo.findAllBySettingType(basicSettingDTO.getSettingType());
		if (basicSettings.size() < 1) {
			BasicSetting basicSetting = modelConverter.map(basicSettingDTO, BasicSetting.class);
			settingRepo.save(basicSetting);
		} else {
			throw new TooManySettings();
		}
	}

	public void updateSettingType(BasicSettingDTO basicSettingDTO) {
		BasicSetting basicSetting = settingRepo.findOne(basicSettingDTO.getSettingCd());
		basicSetting.setSettingType(basicSettingDTO.getSettingType());
		settingRepo.save(basicSetting);
	}

	public String updateSettingFilename(BasicSettingDTO settingDTO) {
		BasicSetting setting = settingRepo.findOne(settingDTO.getSettingCd());
		String filename = setting.getSettingFilename();
		setting.setSettingFilename(settingDTO.getSettingFilename());
		settingRepo.save(setting);
		if (filename.equals(settingDTO.getSettingFilename())) {
			return null;
		}
		return filename;
	}

	public void updateSettingOriginFilename(BasicSettingDTO basicSettingDTO) {
		BasicSetting basicSetting = settingRepo.findOne(basicSettingDTO.getSettingCd());
		basicSetting.setSettingOriginFilename(basicSettingDTO.getSettingOriginFilename());
		basicSetting.setSettingFilename(basicSettingDTO.getSettingFilename());
		settingRepo.save(basicSetting);
	}

	public String deleteSetting(Integer settingCd) {
		BasicSetting setting = settingRepo.findOne(settingCd);
		settingRepo.delete(settingCd);
		return setting.getSettingFilename();
	}
}
