package egovframework.nicc.hne.client.model.dto;

import egovframework.nicc.hne.client.model.entity.ClientAcl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAclDTO {

  private Integer aclId;
  private String aclIp;
  private String aclName;
  private ClientAcl.Type aclType;
}
