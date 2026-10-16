package egovframework.nicc.hne.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	ServletContext servletContext;

	@Override
	public FileDTO saveFile(MultipartFile file, String dirPath) throws Exception {
		String path = servletContext.getRealPath("/") + makePath(dirPath);
		String filename = makeFileName(file);
		String originalFilename = file.getOriginalFilename();
		File targetFile = new File(path + filename);
		if (targetFile.exists()) {
			FileUtils.deleteQuietly(targetFile); // 중복 파일 제거 혹은 덮어쓰기
		}
		try (InputStream inputStream = file.getInputStream()) {
			FileUtils.copyInputStreamToFile(inputStream, targetFile);
		}
		return FileDTO.builder().filename(filename).originalFilename(originalFilename).build();
	}

	public String makeFileName(MultipartFile multipartFile) throws Exception {
		StringBuilder sb = new StringBuilder();
		int postNum = (int) (Math.random() * 65536);
		String postName = Integer.toHexString(postNum);
		return sb.append(System.currentTimeMillis()).append("_").append(postName).append(getExt(multipartFile)).toString();
	}

	public String getExt(MultipartFile file) throws Exception {
		String filename = file.getOriginalFilename();
		return filename.substring(filename.lastIndexOf("."));
	}

	public String makePath(String dirPath) {
		String[] dirPaths = { "resources", "upload", dirPath };
		StringBuilder sb = new StringBuilder();
		sb.append(File.separator);
		for (String string : dirPaths) {
			sb.append(string).append(File.separator);
		}
		return sb.toString();
	}

	@Override
	public String saveThumbnail(String videoFileName, String dirPath) throws Exception {
		// /{dirpath}/{videoFileName} 에 있는 파일의 {THUMBNAIL_SEC} 의 장면을 캡쳐하여 /thumbnail
		// 디렉토리에 저장한다
		final double THUMBNAIL_SEC = 2; // 영상에서 얻고자 하는 시간대 설정
		String path = servletContext.getRealPath("/") + makePath(dirPath);
		File targetFile = new File(path + videoFileName);

		FrameGrab grab = null;
		SeekableByteChannel channel = null;
		try {
			channel = NIOUtils.readableChannel(targetFile);
			grab = FrameGrab.createFrameGrab(channel);
		} catch (Exception e) {
			if (channel != null) {
				channel.close();
			}
			return "";
		}

//    FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(targetFile));

		grab.seekToSecondPrecise(THUMBNAIL_SEC);
		Picture picture = grab.getNativeFrame();
		BufferedImage bfImg = AWTUtil.toBufferedImage(picture);
		// 해당 THUMBNAIL_DIR_PATH 파라미터를 메소드의 파라미터로 받아 여러 경로에 썸네일 저장 가능
		path = servletContext.getRealPath("/") + makePath("thumbnail");
		// 이름의 확장자를 변경하기위한 코드
		int lastDotIndex = videoFileName.lastIndexOf('.');
		String thumbnailExt = "jpg";
		String thumbnailFileName = "imsi.jpg";
		if (lastDotIndex != -1) {
			thumbnailFileName = videoFileName.substring(0, lastDotIndex + 1) + thumbnailExt;
		} else {
			thumbnailFileName = videoFileName + "." + thumbnailExt;
		}

		ImageIO.write(bfImg, "jpg", new File(path + thumbnailFileName));
		return thumbnailFileName;
	}

	@Override
	public void deleteFile(String filename, String dirPath) throws Exception {
		String path = servletContext.getRealPath("/") + makePath(dirPath);
		File targetFile = new File(path + filename);
		FileUtils.deleteQuietly(targetFile);
	}

	@Override
	public FileDTO saveApkFile(MultipartFile file, String dirPath, String patchType) throws Exception {
		String path = servletContext.getRealPath("/") + makePath(dirPath);
		String originalFilename = file.getOriginalFilename(); // 예: c_1.3.0.apk
		String baseName = patchType;
		String versionWithExtension = originalFilename.split("_")[1]; // 예: 1.3.0.apk
		String version = versionWithExtension.substring(0, versionWithExtension.lastIndexOf('.')); // 예: 1.3.0
		String extension = versionWithExtension.substring(versionWithExtension.lastIndexOf('.')); // 예: .apk

		String newFilename = originalFilename;
		int counter = 1;

		while (new File(path + newFilename).exists()) {
			newFilename = baseName + "_" + version + "." + counter + extension; // 예: c_1.3.0.1.apk
			counter++;
		}

		File targetFile = new File(path + newFilename);
		if (targetFile.exists()) {
			FileUtils.deleteQuietly(targetFile); // 중복 파일 제거 혹은 덮어쓰기
		}
		try (InputStream inputStream = file.getInputStream()) {
			FileUtils.copyInputStreamToFile(inputStream, targetFile);
		}
		return FileDTO.builder().filename(newFilename).build();
	}
}
