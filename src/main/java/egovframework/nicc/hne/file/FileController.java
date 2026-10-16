package egovframework.nicc.hne.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/file")
@Log4j2
public class FileController {
	
	@Autowired
	FileService fileService;

	@PostMapping("/save")
	public FileDTO saveFile(@RequestParam MultipartFile file, @RequestParam String dirPath) throws Exception{
		log.info("/file/save : {}",dirPath);
		FileDTO fileDTO = fileService.saveFile(file, dirPath);
		if(fileDTO.getFilename().endsWith(".mp4")) {
			try {
				fileDTO.setThumbnail(saveThumbnail(fileDTO.getFilename(), dirPath));
			}  catch (Exception e) {
				log.info("Error with Encoding!");
			}
		}
		return fileDTO;
	}
	
	@PostMapping("/saveApk")
	public FileDTO saveApk(@RequestParam MultipartFile file, @RequestParam String dirPath, @RequestParam String patchType) throws Exception{
		log.info("/file/saveApk");
		FileDTO fileDTO = fileService.saveApkFile(file, dirPath, patchType);
		return fileDTO;
	}
	
	@PostMapping("/saveThumbnail")
	public String saveThumbnail(@RequestParam String videoFilename, @RequestParam(defaultValue = "movie") String dirPath) throws Exception{
		log.info("/file/saveThumbnail : {}/{}", dirPath, videoFilename);
		return fileService.saveThumbnail(videoFilename, dirPath);
	}
	
	@DeleteMapping("/delete")
	public void deleteFile(@RequestParam String filename, @RequestParam  String dirPath) throws Exception{
		log.info("/file/delete : {}/{}",dirPath,filename);
		fileService.deleteFile(filename, dirPath);
	}
}
