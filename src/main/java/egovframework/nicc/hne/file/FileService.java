package egovframework.nicc.hne.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	FileDTO saveFile(MultipartFile file, String dirPath) throws Exception;
	
	void deleteFile(String filename, String dirPath) throws Exception;

	String saveThumbnail(String videoFileName, String dirPath) throws Exception;
	
	FileDTO saveApkFile(MultipartFile file, String dirPath, String patchType) throws Exception;
}
