//package egovframework.nicc.hne.websocket;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SendToUser;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import egovframework.nicc.hne.client.model.dto.ClientEquipmentDTO;
//import egovframework.nicc.hne.client.model.entity.ClientPatch;
//import egovframework.nicc.hne.client.model.jsonvo.ClientEquipmentVO;
//import egovframework.nicc.hne.client.service.EquipmentService;
//import egovframework.nicc.hne.client.service.PatchService;
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@Log4j2
//public class StompController {
//
//  @Autowired
////  EquipmentService equipmentService;
//  @Autowired
//  PatchService patchService;
//
//  private static final Map<String, Session> sessionMap = new HashMap<>();
//
//  @OnOpen
//  public void handleOpen(Session session) {
//    log.info("connected {}", session.getId());
//  }
//
//  @OnMessage
//  public void sendMessage(String message, Session session) throws IOException {
//    String userId = message;
//    sessionMap.put(userId, session);
//    log.info("message {} ", message);
//  }
//
//  private SimpMessagingTemplate template;
//
//  @Autowired
//  public StompController(SimpMessagingTemplate template) {
//    this.template = template;
//  }
//
//  public void disConnect(String session_id) throws Exception {
//    log.info("disconnect : {}", session_id);
//    equipmentService.updateEquipmentStatusBySessionId(session_id);
//    template.convertAndSend("/queue/disConnect/" + session_id, session_id);
//  }
//
//  // 여기부턴 웹소켓
//
//  @GetMapping("/restart")
//  @ResponseBody
//  public void restart(RequestCode code, ClientEquipmentDTO clientEquipmentDTO, String page_session_id) {
//    log.info("/restart : {}, {}, {}", code, clientEquipmentDTO, page_session_id);
//    code.setData(new ClientEquipmentVO(clientEquipmentDTO));
//    code.setRequest_code("FUN-01-001");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    code.setPage_session_id(page_session_id);
//    // query/restart 구독자들한테 메시지 날림. code 데이터 날림
//    template.convertAndSend("/query/reStart/" + code.getData().getMac_address(), code);
//  }
//
//  @MessageMapping("/restart/result/{mac_address}")
//  public void restartResult(RequestCode code) {
//    code.setRequest_code("FUR-01-001");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    template.convertAndSend("/queue/reStart/result/server/" + code.getPage_session_id(), code);
//  }
//
//  @MessageMapping("/deviceUpdate")
//  public void update(RequestCode code, ClientEquipmentDTO clientEquipmentDTO, String page_session_id) {
//    code.setRequest_code("FUN-02-001");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    template.convertAndSend("/queue/update/MACAddress/" + code.getData().getMac_address(), code);
//  }
//
//  @MessageMapping("/update/MACAddress/result/{mac_address}")
//  public void updateResult(RequestCode code) {
//    code.setRequest_code("FUN-02-002");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    template.convertAndSend("/queue/update/MACAddress/result/server/" + code.getPage_session_id(), code);
//  }
//
//  @GetMapping("/sectionUpdate")
//  public void sectionUpdate(RequestCode code, ClientEquipmentDTO clientEquipmentDTO, String page_sesseion_id) {
//    code.setRequest_code("FUN-02-002");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    code.setPage_session_id(page_sesseion_id);
//    code.setData(new ClientEquipmentVO(clientEquipmentDTO));
//    template.convertAndSend("/queue/update/sectionFullcode/" + code.getData().getSection_fullcode(), code);
//  }
//
//  @MessageMapping("/update/sectionFullcode/result/{section_fullcode}")
//  @RequestMapping("/resultSectionUpdate")
//  public String messageSectionUpdate(RequestCode code) {
//    code.setRequest_code("FUR-02-002");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    template.convertAndSend("/queue/update/sectionFullcode/result/server/" + code.getPage_session_id(), code);
//    return code.getStatus_code();
//  }
//
//  @MessageMapping("/socketReturn/{mac_address}")
//  @SendToUser("/queue/socketReturn/{mac_address}")
//  public RequestCode socketReturn(RequestCode code) throws Exception {
//    return code;
//  }
//
//  @PostMapping("/devicePatch")
//  @ResponseBody
//  public void deviceUpdate(RequestCode code, ClientEquipmentDTO clientEquipmentDTO, String page_session_id)
//      throws Exception {
//    clientEquipmentDTO.setEquipmentVersion(patchService.getLatestVersion(ClientPatch.Type.client));
//    code.setData(new ClientEquipmentVO(clientEquipmentDTO));
//    code.setRequest_code("FUN-03-001");
//    code.setStatus_code("SUCCESS");
//    code.setVersion("1.0");
//    template.convertAndSend("/queue/patch/" + code.getData().getSection_fullcode(), code);
//  }
//
//  @GetMapping("/patchWhether/{mac_address}/{patchVersion}/{patchWhether}")
//  public void patchWhether(@PathVariable("mac_address") String mac_address,
//      @PathVariable("patchWhether") String patchWhether,
//      // 아무것도 안 함
//      @PathVariable("patchVersion") String patchVersion) throws Exception {
//    if (patchVersion.equals("N")) {
//      return;
//    } else {
//    }
//  }
//
//  @MessageMapping("/macInsert/{mac_address}")
//  @SendToUser("/queue/macInsert/{mac_address}")
//  public RequestCode macInsert(RequestCode code, @Header("simpSessionId") String session) {
//    // RequestCode는 어디서 받아오는걸까?
//    // macInsert 시 자동으로 본인 정보를 긁어 보내는걸까?
//    log.info("MacInsert : {}", code);
//    code.setVersion("1.0");
//    code.getData().setSession_id(session);
//    code.getData().setEquipment_manager_status(1);
//    ClientEquipmentDTO clientEquipmentDTO;
//    try {
//      clientEquipmentDTO = equipmentService.getEquipmentByMac(code.getData().getMac_address());
//    } catch (Exception e) {
//      clientEquipmentDTO = null;
//    }
//    if (clientEquipmentDTO == null) { // 세션값 x시
//      try {
//        equipmentService.insertEquipment(code.getData().toDTO());
//        System.out.println("저장!");
//        code.setRequest_code("DAR-01-001");
//        code.setStatus_code("SUCCESS");
//      } catch (Exception e) {
//        System.out.println("실패!");
//        e.printStackTrace();
//        code.setRequest_code("DAR-01-001");
//        code.setStatus_code("FAIL");
//      }
//    } else { // 세션값 존재시
//      try {
//        System.out.println("저장!");
//        equipmentService.updateEquipmentIp(clientEquipmentDTO);
//        equipmentService.updateEquipmentVersion(clientEquipmentDTO);
//        code.setRequest_code("DAR-02-001");
//        code.setStatus_code("SUCCESS");
//      } catch (Exception e) {
//        System.out.println("실패!");
//        e.printStackTrace();
//        code.setRequest_code("DAR-02-001");
//        code.setStatus_code("FAIL");
//      }
//    }
//
//    return code;
//  }
//
//  @MessageMapping("/patch/{mac_address}")
//  @SendToUser("/queue/patch/{mac_address}")
//  // public RequestCode patch(RequestCode code, @Header("simpSessionId") String
//  // sessionId) {
//  public RequestCode patch(@Header("simpSessionId") String sessionId) {
//    log.info("Patch ");
//    RequestCode requestCode = null;
//    return requestCode;
//  }
//
//  @MessageMapping("/checkDevice")
//	@SendToUser("/topic/checkDevice")
//	public String checkDevice(String json) throws Exception {
////		Gson gson = new Gson();
//		template.convertAndSend("/topic/checkDevice", json);
////		System.out.println(json);
//		return json;
//	}
//	
//	// 단말기에서는 아이피값 담아서 서버에 단일 전송
//	@MessageMapping("/checkDeviceResponse/{ip}")
//	@SendToUser("/queue/checkDeviceResponse/{ip}")
//	public String checkDeviceResponse(String json, @DestinationVariable("ip") String ip) throws Exception {
////		System.out.println(json);
//		return json;
//	}
//
//}
