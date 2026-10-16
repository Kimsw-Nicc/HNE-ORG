package egovframework.nicc.hne.client.model.dto;

import java.util.Date;

import egovframework.nicc.hne.client.model.enumeration.PatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPatchDTO {

	private Integer patchCd;
	private PatchType patchType;
	private String patchName;
	private Date patchEdt;
	private String patchAndroidVersion;
	private String patchInfo;

}
