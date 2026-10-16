package egovframework.nicc.hne.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.model.dto.ComTeamDTO;
import egovframework.nicc.hne.com.service.TeamService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/team")
@Log4j2
public class TeamController {

	@Autowired
	TeamService teamService;
	@Autowired
	VolService volService;

	// TEAM TAB
	@GetMapping(value = "/all", params = "orgCd")
	public List<ComTeamDTO> getTeams(@RequestParam String orgCd) throws Exception {
		log.info("/team/all/{}", orgCd);
		return teamService.getTeams(orgCd);
	}

	@GetMapping("/all")
	public List<ComTeamDTO> getTeams(HttpServletRequest request) throws Exception {
		log.info("/team/all");
		AuthUserDTO authUserDTO = (AuthUserDTO) request.getSession().getAttribute("login");
		String orgCd = authUserDTO.getOrgCd();
		return teamService.getTeams(orgCd);
	}

	@GetMapping("/{teamCd}")
	public ComTeamDTO getTeam(@PathVariable String teamCd) throws Exception {
		log.info("/team/{}", teamCd);
		return teamService.getTeam(teamCd);
	}

	@GetMapping("/count")
	public int countOrg(@RequestParam(defaultValue = "")String orgCd) {
		log.info("/org/count");
		return teamService.countAllTeam(orgCd);
	}

	@PostMapping("/insert")
	public void insertTeam(@RequestBody ComTeamDTO comTeamDTO) throws Exception {
		log.info("/team/insert {}", comTeamDTO);
		teamService.insertTeam(comTeamDTO);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update")
	public void updateTeam(@RequestBody ComTeamDTO comTeamDTO) throws Exception {
		log.info("/team/update :  {}", comTeamDTO);
		teamService.updateTeamInfo(comTeamDTO);
		teamService.updateTeamName(comTeamDTO);
		teamService.updateTeamSort(comTeamDTO);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/all")
	public void updateTeams(@RequestBody List<ComTeamDTO> comTeamDTOs) throws Exception {
		log.info("/team/update/all : {}", comTeamDTOs);
		for (ComTeamDTO comTeamDTO : comTeamDTOs) {
			teamService.updateTeamInfo(comTeamDTO);
			teamService.updateTeamName(comTeamDTO);
			teamService.updateTeamSort(comTeamDTO);
		}
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/visible")
	public void switchTeamVisible(@RequestBody ComTeamDTO comTeamDTO) {
		log.info("/team/visible : {}", comTeamDTO);
		teamService.switchTeamVisible(comTeamDTO);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/update/sync")
	public void switchTeamSync(@RequestBody ComTeamDTO comTeamDTO) {
		log.info("/team/sync : {}", comTeamDTO);
		teamService.switchTeamSync(comTeamDTO);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@DeleteMapping("/delete/{teamCd}")
	public void deleteTeam(@PathVariable String teamCd) {
		log.info("/team/delete/{}", teamCd);
		teamService.deleteTeam(teamCd);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}

	@PutMapping("/sort")
	public void sortTeam(@RequestParam(defaultValue = "") String orgCd) {
		log.info("/team/sort?orgCd={}", orgCd);
		teamService.sortTeam(orgCd);
		volService.updateClientVol(VolService.TEAM);
		volService.updateClientVol(VolService.STAFF);
	}
}
