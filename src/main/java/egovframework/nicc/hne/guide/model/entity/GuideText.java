package egovframework.nicc.hne.guide.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "guide_text")
public class GuideText {
  

  @Id
  @Column(name = "text_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer textId;
  @Column(name = "text_content")
  private String textContent;
  @Column(name = "text_sort")
  private Integer textSort;
  @Column(name = "text_font_size")
  private Integer textFontSize;
  @Column(name = "text_font_style")
  private String textFontStyle;
  @Column(name = "text_font_family")
  private String textFontFamily;
  @Column(name = "text_width")
  private Double textWidth;
  @Column(name = "text_height")
  private Double textHeight;
  @Column(name = "text_top")
  private Double textTop;
  @Column(name = "text_left")
  private Double textLeft;
  @Column(name = "text_br_x")
  private Double textBrX;
  @Column(name = "text_br_y")
  private Double textBrY;
  @Column(name = "text_tl_x")
  private Double textTlX;
  @Column(name = "text_tl_y")
  private Double textTlY;
  @Column(name = "team_cds")
  private String teamCds;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "org_cd")
  private ComOrg comOrg;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="floor_id")
  private GuideFloor guideFloor;
}
