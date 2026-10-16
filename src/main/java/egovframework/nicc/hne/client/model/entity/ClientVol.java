package egovframework.nicc.hne.client.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_vol")
@EntityListeners(AuditingEntityListener.class)
public class ClientVol {

	@Id
	@Column(name = "vol_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer volId;
	@Column(name = "vol_board")
	private Date volBoard;
	@Column(name = "vol_bureau")
	private Date volBureau;
	@Column(name = "vol_image")
	private Date volImage;
	@Column(name = "vol_section")
	private Date volSection;
	@Column(name = "vol_staff")
	private Date volStaff;
	@Column(name = "vol_team")
	private Date volTeam;
	@Column(name = "vol_building")
	private Date volBuilding;
	@Column(name = "vol_about")
	private Date volAbout;
	@Column(name = "vol_alert")
	private Date volAlert;
	@Column(name = "vol_tour")
	private Date volTour;
	@Column(name = "vol_food")
	private Date volFood;
	@Column(name = "vol_video")
	private Date volVideo;
	@Column(name = "vol_bgImg")
	private Date volBgImg;
	@Column(name = "vol_conf")
	private Date volConf;
	@Column(name = "vol_patch")
	private Date volPatch;
	@Column(name = "vol_calendar")
	private Date volCalendar;
	@Column(name = "vol_basic")
	private Date volBasic;
	@Column(name = "vol_layout")
    private Date volLayout;
}
