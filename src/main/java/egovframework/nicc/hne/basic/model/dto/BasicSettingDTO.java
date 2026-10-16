package egovframework.nicc.hne.basic.model.dto;

import java.util.Date;

import egovframework.nicc.hne.basic.model.entity.BasicSetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicSettingDTO {
	private Integer settingCd;
	private BasicSetting.Type settingType;
	private String settingFilename;
	private String settingOriginFilename;
	private Date settingNdt;
	private Date settingEdt;
}
