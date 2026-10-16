package egovframework.nicc.hne.client.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import egovframework.nicc.hne.client.model.enumeration.PatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_patch")
@EntityListeners(AuditingEntityListener.class)
public class ClientPatch {
	
	@Id
	@Column(name = "patch_cd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patchCd;
	@Column(name = "patch_type")
	@Enumerated(EnumType.STRING)
	private PatchType patchType;
	@Column(name = "patch_name")
	private String patchName;
	@Column(name = "patch_edt")
	@LastModifiedDate
	private Date patchEdt;
	@Column(name = "patch_android_version")
	private String patchAndroidVersion;
	@Column(name = "patch_info")
	private String patchInfo;

}
