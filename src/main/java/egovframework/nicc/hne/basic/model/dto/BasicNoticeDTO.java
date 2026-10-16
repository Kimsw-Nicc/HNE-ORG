package egovframework.nicc.hne.basic.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicNoticeDTO {

	private Integer noticeCd;
	private Integer noticeSort;
	private String noticeName;
	private String noticeContent;
	private String noticeFilename;
	private String noticeOriginFilename;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date noticeStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date noticeEndDate;
	private Date noticeNdt;
	private Date noticeEdt;

	private String orgCd;
	private String orgName;
	private String orgFullname;
	private String orgFullcode;
}
