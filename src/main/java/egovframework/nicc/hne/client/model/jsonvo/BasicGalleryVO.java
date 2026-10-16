package egovframework.nicc.hne.client.model.jsonvo;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.nicc.hne.basic.model.dto.BasicGalleryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicGalleryVO {
  

  private Integer img_no;
	private String section_cd;
	private String img_title;
	private String img_caption;
	private String img_filenm;
	private String img_wdt;
	private String img_edt;

  public BasicGalleryVO(BasicGalleryDTO galleryDTO){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    img_no = galleryDTO.getGalleryCd();
    section_cd = galleryDTO.getOrgCd();
    img_title = galleryDTO.getGalleryName();
    img_caption = galleryDTO.getGalleryCaption();
    img_filenm = galleryDTO.getGalleryFilename();
    img_wdt = format.format(galleryDTO.getGalleryNdt());
    img_edt = format.format(galleryDTO.getGalleryEdt());
  }
}
