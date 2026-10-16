//package egovframework.nicc.hne.websocket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import lombok.extern.log4j.Log4j2;
//
////@ServerEndpoint("/websocket")
////@ServerEndpoint("/websocket/{mac}")
//@Log4j2
//public class StompControllerM {
//	private static final List<Session> session = new ArrayList<Session>();
//	private static final String HEARTBEAT_MESSAGE = "PING";
//	private static final Long HEARTBEAT_INTERVAL = 5000L;
//
//	private Timer timer = new Timer();
//
//	@OnOpen
//  public void open(Session newUser, @PathParam(value = "mac") String mac){
//      log.info("connected : {}", newUser);
//      session.add(newUser);
//      for(int i = 0; i< session.size(); i++){
//          try{
//              session.get(i).getBasicRemote().sendText(newUser.getId() + "님이 입장하였습니다");
//          }catch(IOException e){
//              e.printStackTrace();
//          }
//      }
//      timer.scheduleAtFixedRate(new TimerTask() {
//        @Override
//        public void run() {
//            try {
//            	newUser.getBasicRemote().sendText(HEARTBEAT_MESSAGE);
//            } catch (IOException e) {
//            	log.error("session deleted: " + newUser.getId());
//            	timer.cancel();
//            } catch (IllegalStateException e) {
//            	log.error("session deleted: " + newUser.getId());
//            	timer.cancel();
//            }
//        }
//    }, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL);
//	}
//
//	@OnMessage
//	public void getMsg(Session recieveSession, String msg, @PathParam(value = "mac") String mac) {
//		for (int i = 0; i < session.size(); i++) {
//			if (!recieveSession.getId().equals(session.get(i).getId())) {
//				try {
//					session.get(i).getBasicRemote().sendText(mac + " : " + msg);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//				try {
//					session.get(i).getBasicRemote().sendText(mac + " : " + msg);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	@OnClose
//	public void onClose(Session recieveSession, @PathParam(value = "mac") String mac) {
//		session.remove(recieveSession);
//		log.info("{} 번 {} 가 나감", recieveSession.getId(), mac);
//	}
//
//}
