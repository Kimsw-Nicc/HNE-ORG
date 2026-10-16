package egovframework.nicc.hne.client.model.jsonvo;

import java.text.SimpleDateFormat;

import egovframework.nicc.hne.basic.model.dto.BasicMovieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicMovieVO {

	private Integer vi_no;
	private String vi_name;
	private String vi_video;
	private String vi_ndt;
	private String section_cd;
	private String vi_edt;
	private String vi_thumbnail;

	public BasicMovieVO(BasicMovieDTO movieDTO) {

		vi_no = movieDTO.getMovieCd();
		vi_name = movieDTO.getMovieName();
		vi_video = movieDTO.getMovieFilename();
		if (movieDTO.getOrgCd() == null) {
			section_cd = "none";
		} else {
			section_cd = movieDTO.getOrgCd();
		}
		vi_thumbnail = movieDTO.getMovieThumbnail();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		vi_ndt = format.format(movieDTO.getMovieNdt());
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		vi_edt = format.format(movieDTO.getMovieEdt());
	}
}
