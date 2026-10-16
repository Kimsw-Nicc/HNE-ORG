package egovframework.nicc.hne.client.model.jsonvo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientPatchsVO {
  
  private Integer patch_type;
  private String patch_type_name;
  private Integer patch_link_type;
  private String patch_link_package;
  private List<ClientPatchVO> patchFile;

  
}
