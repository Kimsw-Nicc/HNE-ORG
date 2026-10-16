package egovframework.nicc.hne.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.client.model.dto.ClientPatchDTO;
import egovframework.nicc.hne.client.model.enumeration.PatchType;
import egovframework.nicc.hne.client.service.PatchService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping(value = "/patch")
@Log4j2
public class PatchController {
	final static String PATCH_DIR = "patch";

	@Autowired
	PatchService patchService;
  @Autowired
  VolService volService;
	@Autowired
	FileService fileService;

	@GetMapping("/list")
	public void goList(ClientPatchDTO clientPatchDTO, Model model, HttpServletRequest request) {

	}

	@ResponseBody
	@GetMapping("/type")
	public List<ClientPatchDTO> getPatchs(PatchType type) {
		log.info("/type : {}",type);
		return patchService.getPatchs(type);
	}
	

	
	@ResponseBody
	@PostMapping("/insert")
	public void insertPatch(@RequestBody ClientPatchDTO clientPatchDTO) {
		log.info("/insert {}", clientPatchDTO);
		patchService.insertPatch(clientPatchDTO);
    volService.updateClientVol(VolService.PATCH);
	}
	
	@ResponseBody
	@DeleteMapping("/delete")
	public void deletePatch(@RequestParam Integer patchId,@RequestParam String patchName) throws Exception{
		log.info("/delete {} {}",patchId, patchName);
		String filename = patchService.deletePatch(patchId);
		fileService.deleteFile(filename, PATCH_DIR);
    volService.updateClientVol(VolService.PATCH);
	}
}
