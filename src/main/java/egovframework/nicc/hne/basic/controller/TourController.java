package egovframework.nicc.hne.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import egovframework.nicc.hne.basic.model.dto.BasicTourDTO;
import egovframework.nicc.hne.basic.service.TourService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/tour/*")
@Log4j2
public class TourController {
	final static int TOUR_PAGE_SIZE = 6;
	final static String TOUR_DIR = "tour";

	@Autowired
	TourService tourService;
	@Autowired
	VolService volService;
	@Autowired
	FileService fileService;

	@GetMapping("/list")
	public void goList() {
		log.info("/tour/list");
	}

	@GetMapping("/detail")
	public void goDetail(@RequestParam Integer tourCd) {
		log.info("/tour/detail?tourCd={}", tourCd);
	}

	@GetMapping("/insert")
	public void goInsert() {
	}

	@GetMapping("/update")
	public void goUpdate() {
		log.info("/tour/update");
	}

	@GetMapping("/all")
	@ResponseBody
	public List<BasicTourDTO> getTours(
			@PageableDefault(page = 0, size = TOUR_PAGE_SIZE, sort = "tourEdt", direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("/tour/all : {}", pageable);
		return tourService.getTours(pageable);
	}

	@GetMapping("/{tourCd}")
	@ResponseBody
	public BasicTourDTO getTour(@PathVariable Integer tourCd) {
		log.info("/tour/{}", tourCd);
		return tourService.getTour(tourCd);
	}

	@GetMapping("/count")
	@ResponseBody
	public Long countTour() {
		return tourService.countTour();
	}

	@PostMapping("/insert")
	@ResponseBody
	public void doInsert(@RequestBody BasicTourDTO basicTourDTO) {
		log.info("/tour/insert : {}", basicTourDTO);
		tourService.insertTour(basicTourDTO);
		volService.updateClientVol(VolService.TOUR);
	}

	@PutMapping("/update")
	@ResponseBody
	public void doUpdate(@RequestBody BasicTourDTO basicTourDTO) throws Exception {
		log.info("/tour/update : {}", basicTourDTO);
		tourService.updateTourName(basicTourDTO);
		tourService.updateTourOrg(basicTourDTO);
		tourService.updateTourOriginFilename(basicTourDTO);
		String filename = tourService.updateTourFilename(basicTourDTO);
		fileService.deleteFile(filename, TOUR_DIR);
		volService.updateClientVol(VolService.TOUR);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void doDelete(@RequestParam Integer tourCd) throws Exception{
		log.info("/tour/delete/{}", tourCd);
		String filename = tourService.deleteTour(tourCd);
		fileService.deleteFile(filename, TOUR_DIR);
		volService.updateClientVol(VolService.TOUR);
	}

}
