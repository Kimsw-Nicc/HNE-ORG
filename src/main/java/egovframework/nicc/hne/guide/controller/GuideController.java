package egovframework.nicc.hne.guide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.file.FileDTO;
import egovframework.nicc.hne.file.FileService;
import egovframework.nicc.hne.guide.model.dto.GuideBuildingDTO;
import egovframework.nicc.hne.guide.model.dto.GuideFloorDTO;
import egovframework.nicc.hne.guide.model.dto.GuideTextDTO;
import egovframework.nicc.hne.guide.service.BuildingService;
import egovframework.nicc.hne.guide.service.FloorService;
import egovframework.nicc.hne.guide.service.TextService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guide")
@Log4j2
public class GuideController {

	@Autowired
	BuildingService buildingService;
	@Autowired
	VolService volService;
	@Autowired
	OrgService orgService;
	@Autowired
	FloorService floorService;
	@Autowired
	TextService textService;

	@Autowired
	FileService fileService;

	@GetMapping("/main")
	public void goTextbox() {
		log.info("/guide/main");
	}

	// BUILDING

	@GetMapping("/building/list")
	public void gobuildingList() {
		log.info("/guide/building/list");
	}

	@GetMapping("/building/all")
	@ResponseBody
	public List<GuideBuildingDTO> getBuildings() {
		log.info("/guide/building/all");
		return buildingService.getBuildings();
	}

	@PostMapping("/building/insert")
	@ResponseBody
	public String doBuildingInsert(@RequestBody GuideBuildingDTO guideBuildingDTO) {
		log.info("/guide/building/insert : {}", guideBuildingDTO);
		buildingService.insertBuilding(guideBuildingDTO);
		volService.updateClientVol(VolService.BUILDING);

		return "SUCCESS";
	}

	@PutMapping("/building/updates")
	@ResponseBody
	public void doBuildingUpdate(@RequestBody List<GuideBuildingDTO> guideBuildingDTOs) {
		log.info("/guide/building/updates : {}", guideBuildingDTOs);
		buildingService.updateBuildings(guideBuildingDTOs);
		volService.updateClientVol(VolService.BUILDING);
	}

	@PutMapping("/building/sort")
	@ResponseBody
	public void sortBuilding() {
		buildingService.sortBuildingSort();
	}

	@DeleteMapping("/building/delete")
	@ResponseBody
	public void doBuildingDelete(@RequestParam Integer buildingId) {
		log.info("/guide/building/delete/{}", buildingId);
		buildingService.deleteBuilding(buildingId);
		volService.updateClientVol(VolService.BUILDING);
	}

	// FLOOR

	@GetMapping("/floor/insert")
	public void goFloorInsert() {
		log.info("/floor/insert");
	}

	@GetMapping(value = "/floor/all", params = "buildingId")
	@ResponseBody
	public List<GuideFloorDTO> getFloorsByBuildingId(@RequestParam(defaultValue = "0") Integer buildingId) {
		log.info("/guide/floor/all?buildingId={}", buildingId);
		return floorService.getFloors(buildingId);
	}

	@GetMapping(value = "/floor/{floorId}")
	@ResponseBody
	public GuideFloorDTO getFloor(@PathVariable Integer floorId) {
		log.info("/guide/floor/{}", floorId);
		return floorService.getFloor(floorId);
	}

	@PostMapping("/floor/insert")
	@ResponseBody
	public void doFloorInsert(@RequestBody GuideFloorDTO guideFloorDTO) {
		log.info("/floor/insert : {}", guideFloorDTO);
		floorService.insertFloor(guideFloorDTO);
		volService.updateClientVol(VolService.BUILDING);
	}

	@PutMapping("/floor/update")
	@ResponseBody
	public String doFloorUpdate(@RequestBody GuideFloorDTO guideFloorDTO) {
		log.info("/floor/update : {}", guideFloorDTO);
		floorService.updateFloor(guideFloorDTO);
		volService.updateClientVol(VolService.BUILDING);

		return "redirect:/guide/main";
	}

	@DeleteMapping("/floor/delete/{floorId}")
	@ResponseBody
	public String doFloorDelete(@PathVariable Integer floorId) {
		log.info("/floor/delete/{}", floorId);
		textService.deleteTextByFloor(floorId);
		floorService.deleteFloor(floorId);
		volService.updateClientVol(VolService.BUILDING);

		return "redirect:/guide/main";
	}

	// TEXT
	@GetMapping("/text/all/{floorId}")
	@ResponseBody
	public List<GuideTextDTO> getAllText(@PathVariable Integer floorId) {
		log.info("/text/all/{}", floorId);

		return textService.getTexts(floorId);
	}

	@GetMapping("/text/nextsort/{floorId}")
	@ResponseBody
	public Integer getNextSort(@PathVariable Integer floorId) {
		log.info("/text/nextsort/{}", floorId);

		return textService.getNextSort(floorId);
	}

	@PostMapping("/text/insert")
	@ResponseBody
	public void doTextInsert(@RequestBody List<GuideTextDTO> guidTextDTOs) {
		log.info("/text/insert ; {}", guidTextDTOs);

		textService.insertTexts(guidTextDTOs);
		textService.sortTexts(guidTextDTOs.get(0).getFloorId());
		volService.updateClientVol(VolService.BUILDING);
	}

	@DeleteMapping("/text/delete/{textId}")
	@ResponseBody
	public void doTextDelete(@PathVariable Integer textId) {
		log.info("/text/delete/{}", textId);

		textService.deleteText(textId);
		volService.updateClientVol(VolService.BUILDING);
	}

	@DeleteMapping("/text/delete/all/{floorId}")
	@ResponseBody
	public void doTexstDelete(@PathVariable Integer floorId) {
		log.info("/text/delete/all/{}", floorId);

		textService.deleteTextByFloor(floorId);
		volService.updateClientVol(VolService.BUILDING);
	}

	@PostMapping("/building/uploadImage")
	@ResponseBody
	public GuideBuildingDTO uploadImage(
	        @RequestParam Integer buildingId,
	        @RequestParam MultipartFile file) throws Exception{
	  String uploadDir = "building";
	  
	    // 저장 경로는 프로젝트 업로드 정책에 맞춰 조정
//	    File dest = new File(uploadDir + "/building/" + savedFilename);
//	    file.transferTo(dest);
	  
	  FileDTO fileDTO = fileService.saveFile(file, uploadDir);
	  
	  String savedFilename = fileDTO.getFilename();
	  String originFilename = fileDTO.getOriginalFilename();
	    

	    buildingService.updateBuildingImage(buildingId, savedFilename, originFilename);

	    GuideBuildingDTO dto = new GuideBuildingDTO();
	    dto.setBuildingId(buildingId);
	    dto.setBuildingFilename(savedFilename);
	    dto.setBuildingOriginFilename(originFilename);
	    return dto;
	}
}
