package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicMovieDTO;
import egovframework.nicc.hne.basic.model.entity.BasicMovie;
import egovframework.nicc.hne.basic.repo.BasicMovieRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class MovieService {

	@Autowired
	BasicMovieRepo movieRepo;
	@Autowired
	ComOrgRepo orgRepo;

	@Autowired
	ModelConverter modelConverter;

	// 페이징 전부 가져오기
	public List<BasicMovieDTO> getMovies(Pageable pageable) {
		List<BasicMovie> movies = movieRepo.findAll(pageable).getContent();
		return modelConverter.mapList(movies, BasicMovieDTO.class);
	}

	// 페이징 부서코드로 가져오기
	public List<BasicMovieDTO> getMovies(Pageable pageable, String orgCd) {
		List<BasicMovie> movies = movieRepo.findByOrgCd(pageable, orgCd);
		return modelConverter.mapList(movies, BasicMovieDTO.class);
	}

	// 일반 전부 가져오기
	public List<BasicMovieDTO> getMoviesAll() {
		return modelConverter.mapList(movieRepo.findAll(), BasicMovieDTO.class);
	}

	// 일반 부서코드로 가져오기
	public List<BasicMovieDTO> getMoviesAll(String orgCd) {
		return modelConverter.mapList(movieRepo.findByOrgCd(orgCd), BasicMovieDTO.class);
	}

	// 하나 가져오기
	public BasicMovieDTO getMovie(Integer movieId) {
		return modelConverter.map(movieRepo.findOne(movieId), BasicMovieDTO.class);
	}

	// 개수 가져오기
	public Long getCount(String orgCd) {
		if(orgCd.equals("none")) return movieRepo.count(); 
		else return movieRepo.count(orgCd);
	}

	// INSERT
	public void insertMovie(BasicMovieDTO movieDTO) {
		BasicMovie movie = modelConverter.map(movieDTO, BasicMovie.class);
		movie.setComOrg(orgRepo.findOne(movieDTO.getOrgCd()));
		movieRepo.save(movie);
	}

	// UPDATE
	public void updateMovieOrg(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		movie.setComOrg(orgRepo.findOne(movieDTO.getOrgCd()));
		movieRepo.save(movie);
	}

	public String updateMovieFilename(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		String filename = movie.getMovieFilename();
		movie.setMovieFilename(movieDTO.getMovieFilename());
		movieRepo.save(movie);
		if (filename.equals(movieDTO.getMovieFilename())) {
			return null;
		}
		return filename;
	}

	public void updateMovieOriginFilename(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		movie.setMovieOriginFilename(movieDTO.getMovieOriginFilename());
		movieRepo.save(movie);
	}

	public String updateMovieThumbnail(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		String filename = movie.getMovieThumbnail();
		movie.setMovieThumbnail(movieDTO.getMovieThumbnail());
		movieRepo.save(movie);
		if (filename.equals(movieDTO.getMovieThumbnail())) {
			return null;
		}
		return filename;
	}

	public void updateMovieName(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		movie.setMovieName(movieDTO.getMovieName());
		movieRepo.save(movie);
	}

	public void updateMovieSort(BasicMovieDTO movieDTO) {
		BasicMovie movie = movieRepo.findOne(movieDTO.getMovieCd());
		movie.setMovieSort(movieDTO.getMovieSort());
		movieRepo.save(movie);
	}

	// DELETE
	public String[] deleteMovie(Integer movieCd) {
		String[] filenames = new String[2];
		movieRepo.delete(movieCd);

		return filenames;
	}

}
