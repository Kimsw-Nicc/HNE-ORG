package egovframework.nicc.hne.basic.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MovieStreamingController {

  @GetMapping(value="/resources/upload/movie/{video_name:.+}")
	public String streamingMediaVideo(@PathVariable("video_name")String video_name, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

    log.debug("streaming video~");
		//확장자 확인
		String[] filename_seperate = video_name.split("\\.");
		
		if(filename_seperate.length <= 1) {
			// 확장자 에러
			String resultMsg = "Wrong file name, You need to include expand file name";
			log.error(resultMsg);
			response.getOutputStream().write(resultMsg.getBytes());
			response.getOutputStream().flush();
			return null;
		} else {
		}
		
		//progressbar 에서 특정 위치를 클릭하거나 해서 임의 위치의 내용을 요청할 수 있으므로 
		//파일의 임의의 위치에서 읽어오기 위해 RandomAccessFile 클래스를 사용한다. 
		//해당 파일이 없을 경우 예외 발생
		File file = new File(request.getSession().getServletContext().getRealPath("/resources/upload/movie/"+video_name));
		if( !file.exists() ) { new FileNotFoundException(); }
		
		//log.info("- Stream request start : " + video_name);
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(file, "r");
		} catch (Exception e) {
			randomFile.close();
			return "";
		}
		
		long rangeStart = 0; // 요청범위 시작위치
		long rangeEnd = 0; // 요청범위 끝 위치
		boolean isPart = false; // 부분 요청일 경우 true, 전체 요청의 경우 false
		
		try {
			
			//영상 파일 크기
			long movieSize = randomFile.length();
			//log.info("fileSize : " + movieSize);
			//스트림 요청 범위, request의 헤더에서 range를 읽는다
			String range = request.getHeader("range");
			
			// 브라우저에 따라 range 형식이 다름, 기본형식 "bytes={start}-{end}" 형식
			// range가 null이거나, reqStart가 0이고 end가 없을 경우 전체 요청
			// 요청 범위를 구함
			if(range != null) {
				// 처리 편의를 위해 요청 range에 end 값이 없을 경우 넣어줌
				if(range.endsWith("-")) {
					range = range+(movieSize - 1);
				}
				
				int idxm = range.trim().indexOf("-");
				rangeStart = Long.parseLong(range.substring(6, idxm));
				rangeEnd = Long.parseLong(range.substring(idxm+1));
				if(rangeStart > 0) {
					isPart = true;
				}
			} else {
				// range가 null 인 경우 영상 전체 크기로 초기값 세팅, 0부터 시작하므로 -1
				rangeStart = 0;
				rangeEnd = movieSize - 1;
			}
			
			// 전송 파일 크기
			long partSize = rangeEnd - rangeStart + 1;
			// 전송 시작
			response.reset();
			// 전체 요청일 경우 200, 부분 요청일 경우 206을 반환상태 코드 지정
			response.setStatus(isPart ? 206 : 200);
			// mime type 지정
			response.setContentType("video/mp4");
			
			//전송 내용을 헤드에 넣어줌, 마지막에 파일 전체 크기를 넣음
			response.setHeader("Content-Range", "bytes "+rangeStart+"-"+rangeEnd+"/"+movieSize);
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Length", ""+partSize);
			
			OutputStream out = response.getOutputStream();
			// 영상 파일의 전송시작 위치 지정
			randomFile.seek(rangeStart);
			
			// 파일 전송.. java io는 1회 전송 byte수가 int로 지정됨
			// 영상 파일의 경우 int형으로는 처리 안되는 크기의 파일이 있으므로
			// 8kb로 잘라서 파일의 크기가 크더라도 문제가 되지 않도록 구현
			int bufferSize = 8*1024;
			byte[] buf = new byte[bufferSize];
			
			do {
				int block = partSize > bufferSize ? bufferSize : (int)partSize;
				int len = randomFile.read(buf, 0, block);
				out.write(buf, 0, len);
				partSize -= block;
			}while(partSize > 0);
			
			
		} catch(IOException e) {
		}finally {
			if(randomFile != null) randomFile.close();
		}
		
		return null;
	}
}
