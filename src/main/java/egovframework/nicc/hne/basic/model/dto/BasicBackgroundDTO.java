package egovframework.nicc.hne.basic.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicBackgroundDTO {

	private Integer backgroundCd;
	private String backgroundName;
	private Integer backgroundSort;
	private Date backgroundNdt;
	private Date backgroundEdt;
	private String backgroundOriginFilename;
	private String backgroundFilename;
}
