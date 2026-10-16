package egovframework.nicc.hne.basic.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import egovframework.nicc.hne.com.model.entity.ComOrg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "basic_gallery")
@EntityListeners(AuditingEntityListener.class)
public class BasicGallery {

	@Id
	@Column(name = "gallery_cd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer galleryCd;
	@Column(name = "gallery_name")
	private String galleryName;
	@Column(name = "gallery_filename")
	private String galleryFilename;
	@Column(name = "gallery_origin_filename")
	private String galleryOriginFilename;
	@Column(name = "gallery_visible")
	private Boolean galleryVisible;
	@Column(name = "gallery_sort")
	private Integer gallerySort;
	@Column(name = "gallery_ndt")
	@CreatedDate
	private Date galleryNdt;
	@Column(name = "gallery_edt")
	@LastModifiedDate
	private Date galleryEdt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_cd")
	private ComOrg comOrg;
}
