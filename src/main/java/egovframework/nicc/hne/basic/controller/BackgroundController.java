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

import egovframework.nicc.hne.basic.model.dto.BasicBackgroundDTO;
import egovframework.nicc.hne.basic.service.BackgroundService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/background/*")
@Log4j2
public class BackgroundController {
	final static int BACKGROUND_PAGE_SIZE = 6;
	final static String BACKGROUND_DIR = "backGround";

	@Autowired
	BackgroundService backgroundService;
	@Autowired
	VolService volService;
	@Autowired
	FileService fileService;

	@GetMapping("/list")
	public void goList() {
		log.info("/background/list");
	}

	@GetMapping("/detail")
	public void goDetail(@RequestParam Integer backgroundCd) {
		log.info("/background/detail?backgroundCd={}", backgroundCd);
	}

	@GetMapping("/insert")
	public void goInsert() {
		log.info("/background/insert");
	}

	@GetMapping("/update")
	public void goUpdate() {
		log.info("/background/update");
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public List<BasicBackgroundDTO> getBackgrounds(
			@PageableDefault(page = 0, size = BACKGROUND_PAGE_SIZE, sort = "backgroundSort", direction = Sort.Direction.ASC) Pageable pageable) {
		log.info("/background/all {}", pageable);
		return backgroundService.getBackgrounds(pageable);
	}

	@GetMapping("/{backgroundCd}")
	@ResponseBody
	public BasicBackgroundDTO getBackground(@PathVariable Integer backgroundCd) {
		log.info("/background/{}", backgroundCd);
		return backgroundService.getBackground(backgroundCd);
	}

	@GetMapping("/count")
	@ResponseBody
	public Long countBackground() {
		return backgroundService.getCount();
	}

	@PostMapping("/insert")
	@ResponseBody
	public void doInsert(@RequestBody BasicBackgroundDTO backgroundDTO) {
		log.info("/background/insert : {}", backgroundDTO);
		backgroundService.insertBackground(backgroundDTO);
		volService.updateClientVol(VolService.BACKGROUND);
	}

	@PutMapping("/update")
	@ResponseBody
	public void doUpdate(@RequestBody BasicBackgroundDTO backgroundDTO) throws Exception {
		log.info("/background/update : {}", backgroundDTO);
		backgroundService.updateBackgroundName(backgroundDTO);
		backgroundService.updateBackgroundSort(backgroundDTO);
		backgroundService.updateBackgroundOriginFilename(backgroundDTO);
		String filename = backgroundService.updateBackgroundFilename(backgroundDTO);
		fileService.deleteFile(filename, BACKGROUND_DIR);
		volService.updateClientVol(VolService.BACKGROUND);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void doDelete(@RequestParam Integer backgroundCd) throws Exception {
		log.info("/background/delete?backgroundCd={}", backgroundCd);
		String filename = backgroundService.deleteBackground(backgroundCd);
		fileService.deleteFile(filename, BACKGROUND_DIR);
		volService.updateClientVol(VolService.BACKGROUND);
	}
}
