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
public class BasicGalleryDTO {

	private Integer galleryCd;
	private String galleryName;
	private String galleryCaption;
	private String galleryFilename;
	private String galleryOriginFilename;
	private Boolean galleryVisible;
	private Integer gallerySort;
	private Date galleryNdt;
	private Date galleryEdt;

	private String orgCd;
	private String orgName;
	private String orgFullname;
	private String orgFullcode;
}
