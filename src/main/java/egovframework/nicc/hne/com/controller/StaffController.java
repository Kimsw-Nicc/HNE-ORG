package egovframework.nicc.hne.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.model.dto.ComStaffDTO;
import egovframework.nicc.hne.com.service.StaffService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/staff")
@Log4j2
public class StaffController {
	final static int STAFF_PAGE_SIZE = 10;
	final static String STAFF_DIR = "staff";

	@Autowired
	StaffService staffService;
	@Autowired
	VolService volService;
	@Autowired
	FileService fileService;

	// STAFF TAB
	@GetMapping(value = "/all", params = { "orgCd", "teamCd", "keyword" })
	public List<ComStaffDTO> getStaffs(
			@PageableDefault(page = 0, size = STAFF_PAGE_SIZE, sort = "none") Pageable pageable,
			@RequestParam(defaultValue = "") String orgCd, @RequestParam(defaultValue = "") String teamCd,
			@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "staffName") String keywordType,
			@RequestParam(defaultValue = "") String status) throws Exception {
		log.info("/staff/all");
		return staffService.getStaffs(pageable, orgCd, teamCd, keyword, keywordType, status);
	}

	@GetMapping("/count")
	public int countStaff(@RequestParam(defaultValue = "") String orgCd, @RequestParam(defaultValue = "") String teamCd,
			@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "staffName") String keywordType,
			@RequestParam(defaultValue = "") String status) {
		log.info("/staff/count?orgCd={}&teamCd={}&keyword={}&keywordType={}&status={}",orgCd, teamCd, keyword, keywordType, status);
		return staffService.countStaff(orgCd, teamCd, keyword, keywordType, status);
	}

	@GetMapping("/edited")
	public List<ComStaffDTO> getStaffs() {
		log.info("/staff/edited");
		return staffService.getEditedStaffs();
	}

	@GetMapping("/{staffCd}")
	public ComStaffDTO getSTaff(@PathVariable Integer staffCd) {
		log.info("/staff/{}", staffCd);
		return staffService.getStaff(staffCd);
	}

	@GetMapping(value = "/all", params = "teamCd")
	public List<ComStaffDTO> getStaffsByTeamCd(String teamCd) {
		log.info("/staff/all?teamCd={}", teamCd);
		return staffService.getStaffsByTeamCd(teamCd);
	}

	@GetMapping(value = "/all", params = "staffName")
	public List<ComStaffDTO> getStaffsByStaffName(String staffName) {
		log.info("/staff/all?staffName={}", staffName);
		return staffService.getStaffsByStaffName(staffName);
	}

	@PostMapping("/insert")
	public void insertStaff(@RequestBody ComStaffDTO comStaffDTO) {
		log.info("/staff/insert : {}", comStaffDTO);
		staffService.insertStaff(comStaffDTO);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update")
	public void updateStaff(@RequestBody ComStaffDTO comStaffDTO) throws Exception{
		log.info("/staff/update: {}", comStaffDTO);
		staffService.updateStaffOrg(comStaffDTO);
		staffService.updateStaffTeam(comStaffDTO);
		staffService.updateStaffFilename(comStaffDTO);
		staffService.updateStaffName(comStaffDTO);
		staffService.updateStaffPosition(comStaffDTO);
		staffService.updateStaffTelno(comStaffDTO);
		staffService.updateStaffFaxno(comStaffDTO);
		staffService.updateStaffEmail(comStaffDTO);
		staffService.updateStaffSort(comStaffDTO);
		staffService.updateStaffDuty(comStaffDTO);
		staffService.updateStaffStatus(comStaffDTO);
		staffService.updateStaffPresent(comStaffDTO);
//    staffService.updateStaffVisible(comStaffDTO);
//    staffService.updateStaffSync(comStaffDTO);
		staffService.updateStaffOriginFilename(comStaffDTO);
		staffService.updateStaffEdt(comStaffDTO);
		String filename = staffService.updateStaffFilename(comStaffDTO);
		fileService.deleteFile(filename, STAFF_DIR);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/visible")
	public void switchStaffVisible(@RequestBody ComStaffDTO comStaffDTO) {
		log.info("/staff/visible : {}", comStaffDTO);
		staffService.updateStaffVisible(comStaffDTO);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/sync")
	public void switchStaffSync(@RequestBody ComStaffDTO comStaffDTO) {
		log.info("/staff/sync : {}", comStaffDTO);
		staffService.updateStaffSync(comStaffDTO);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/all/sort")
	public void updateSort(@RequestBody List<ComStaffDTO> comStaffDTOs) {
		log.info("/staff/update/sort");
		for (ComStaffDTO comStaffDTO : comStaffDTOs) {
			staffService.updateStaffSort(comStaffDTO);
		}
		volService.updateClientVol(VolService.STAFF);
	}

	@DeleteMapping("/delete/{staffCd}")
	public void deleteStaffReal(@PathVariable Integer staffCd) throws Exception {
		log.info("/staff/delete/{}", staffCd);
		staffService.deleteStaff(staffCd);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/sort")
	public void sortStaff(@RequestParam(defaultValue = "") String orgCd) {
		log.info("/staff/sort?orgCd={}", orgCd);
		staffService.sortStaff(orgCd);
	}
	
	@PutMapping("/testInvalid")
	public void testInvalid() {
	  log.info("TEST INVALIDDDDD");
	  staffService.testInvalid();
	}
}
