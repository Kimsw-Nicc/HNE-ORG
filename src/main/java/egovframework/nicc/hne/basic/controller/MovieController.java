package egovframework.nicc.hne.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.basic.model.dto.BasicMovieDTO;
import egovframework.nicc.hne.basic.service.MovieService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
public class MovieController {
	final static int MOVIE_PAGE_SIZE = 6;
	final static String MOVIE_DIR = "movie";
	final static String THUMBNAIL_DIR = "thumbnail";

	@Autowired
	MovieService movieService;
	@Autowired
	OrgService orgService;
	@Autowired
	VolService volService;
	@Autowired
	FileService fileService;

	@GetMapping("/list")
	public void goList() {
		log.info("/movie/list");
	}

	@GetMapping("/detail")
	public void goReadPage(@RequestParam Integer movieCd) {
		log.info("/movie/detail?movieCd={}", movieCd);
	}

	@GetMapping("/insert")
	public void goInsert() {
		log.info("/movie/insert");
	}

	@GetMapping("/update")
	public void goUpdate() {
		log.info("/movie/update");
	}

	@GetMapping("/all")
	@ResponseBody
	public List<BasicMovieDTO> getMovies(HttpServletRequest request,
			@PageableDefault(page = 0, size = MOVIE_PAGE_SIZE) Pageable pageable, //
			@RequestParam(defaultValue = "") String orgCd) {
		log.info("/movie/all {}", pageable);
		String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
		if (sessionOrgCd != null)
			orgCd = sessionOrgCd;
		List<BasicMovieDTO> result = null;
		if (orgCd.equals("none")) {
			result = movieService.getMovies(pageable);
		} else {
			result = movieService.getMovies(pageable, orgCd);
		}
		return result;
	}

	@GetMapping("/{movieCd}")
	@ResponseBody
	public BasicMovieDTO getMovie(@PathVariable Integer movieCd) {
		log.info("/movie/{}", movieCd);
		return movieService.getMovie(movieCd);
	}

	@GetMapping("/count")
	@ResponseBody
	public Long countMovie(HttpServletRequest request, @RequestParam(defaultValue = "") String orgCd) {
		String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
		if (sessionOrgCd != null)
			orgCd = sessionOrgCd;
		return movieService.getCount(orgCd);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void delete(@RequestParam Integer movieCd) throws Exception {
		log.info("/movie/delete?movieCd={}", movieCd);
		String[] filenames = movieService.deleteMovie(movieCd);
		fileService.deleteFile(filenames[0], MOVIE_DIR);
		fileService.deleteFile(filenames[1], THUMBNAIL_DIR);
		volService.updateClientVol(VolService.VIDEO);
	}

	@PostMapping("/insert")
	@ResponseBody
	public void doInsert(@RequestBody BasicMovieDTO movieDTO) {
		log.info("/movie/insert : {}", movieDTO);
		movieService.insertMovie(movieDTO);
		volService.updateClientVol(VolService.VIDEO);
	}

	@PutMapping("/update")
	@ResponseBody
	public void doUpdate(@RequestBody BasicMovieDTO movieDTO) throws Exception {
		log.info("doModifyPage : {}", movieDTO);
		movieService.updateMovieName(movieDTO);
		movieService.updateMovieSort(movieDTO);
		movieService.updateMovieOrg(movieDTO);
		movieService.updateMovieOriginFilename(movieDTO);
		String filename = movieService.updateMovieFilename(movieDTO);
		fileService.deleteFile(filename, MOVIE_DIR);
		filename = movieService.updateMovieThumbnail(movieDTO);
		fileService.deleteFile(filename, THUMBNAIL_DIR);
		volService.updateClientVol(VolService.VIDEO);
	}

}
