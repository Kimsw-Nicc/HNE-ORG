package egovframework.nicc.hne.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.nicc.hne.client.model.dto.ClientAclDTO;
import egovframework.nicc.hne.client.service.AclService;
import egovframework.nicc.hne.global.model.enumeration.GlobalType;
import egovframework.nicc.hne.global.service.ToggleService;

public class IpCheckInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  AclService aclService;
  @Autowired
  ToggleService toggleService;

  private final String SERVER_IP = "105.15.2.68";
  // private final String SERVER_IP = "192.168.0.153";
  private final String BLOCKING_PAGE = "/acl/blocking";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Boolean isIpChecked = toggleService.isToogled(GlobalType.ipCheck);
    if (!isIpChecked) { // 사용중이지 않으면 정상 흐름
      return true;
    }
    if(request.getRequestURI().equals(BLOCKING_PAGE)){
      return true;
    }
    if (request.getSession().getAttribute("login") == null) { // 로그인중이 아니라면
      List<ClientAclDTO> clientAcls = aclService.getAcls();

      IpConverter ipConverter = new IpConverter.Builder().address(request.getRemoteAddr()).build();
      String address = ipConverter.getIPv4Address();
      if (!isValueExists(clientAcls, address) && !address.equals(SERVER_IP)) {// 아이피가 목록에 없거나, 서버아이피가 아니면!
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.sendRedirect(BLOCKING_PAGE);
        out.flush();
        return false;
      }
    }
    return true;
  }

  public boolean isValueExists(List<ClientAclDTO> clientAclDTOs, String ip) {
    for (ClientAclDTO clientDto : clientAclDTOs) {
      if (clientDto.getAclIp().equals(ip)) {
        return true;
      }
    }
    return false;
  }

}