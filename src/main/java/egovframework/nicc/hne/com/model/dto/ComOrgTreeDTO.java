package egovframework.nicc.hne.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComOrgTreeDTO {
	// jstree를 위해 가공되는 데이터
	
	private String id;
	private String parent;
	private String text;
	private String icon;
  private Integer sort;
}
