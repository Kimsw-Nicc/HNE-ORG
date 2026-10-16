package egovframework.nicc.hne.client.model.jsonvo;

import egovframework.nicc.hne.client.model.dto.ClientPatchDTO;
import egovframework.nicc.hne.client.model.enumeration.PatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientPatchVO {
  private Integer patch_number;
	private String patch_name;
	private String patch_android_version;
	private Integer patch_type;
	private Long patch_edited_date;
	private String patch_info;

  public ClientPatchVO(ClientPatchDTO clientPatchDTO){
    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    patch_number = clientPatchDTO.getPatchCd();
    patch_name = clientPatchDTO.getPatchName();
    patch_android_version = clientPatchDTO.getPatchAndroidVersion();
    if(PatchType.client==clientPatchDTO.getPatchType()) patch_type = 1;
    else if(PatchType.luncher==clientPatchDTO.getPatchType()) patch_type = 2;
    else if(PatchType.manager==clientPatchDTO.getPatchType()) patch_type = 3;
    else if(PatchType.conf==clientPatchDTO.getPatchType()) patch_type = 4;
//    else if(PatchType.food==clientPatchDTO.getPatchType()) patch_type = 5;
    patch_edited_date = clientPatchDTO.getPatchEdt().getTime();
    patch_info = clientPatchDTO.getPatchInfo();
  }
}
