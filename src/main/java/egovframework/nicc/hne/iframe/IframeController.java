package egovframework.nicc.hne.iframe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/iframe")
@Log4j2
public class IframeController {

	@GetMapping("/**")
	public String iframe(HttpServletRequest request, Model model) {
		String fullPath = request.getRequestURI().substring("/iframe".length());

    // 경로가 없으면 기본 페이지를 설정
    if (fullPath.isEmpty()) {
        fullPath = "/default"; // 기본 페이지 (원하는 값으로 변경 가능)
    }
		log.info(fullPath);
		model.addAttribute("iframeSrc", fullPath);
		return "iframePage";
	}
}
