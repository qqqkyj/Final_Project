package boot.data.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import boot.data.Dto.MessageDto;
import boot.data.service.MessageRoomService;
import boot.data.service.MessageService;


//상속받은 TextWebSocketHandler는 handleTextMessage를 실행
@Component
public class SocketHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	
	@Autowired
	MessageService mservice;
	
	@Autowired
	MessageRoomService roomservice;
	
	//웹소켓 연결이 되면 동작하는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결시
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
    }
    
    //웹소켓 연결이 종료되면 동작하는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료시
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
    
  //메시지를 발송하면 동작하는 메소드
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        //메시지 발송시
        String msg = message.getPayload();
        
        JSONObject ob=new JSONObject(msg);
        
        
      //메시지 구분(보낸사람:내용)
        int mynum=Integer.parseInt(ob.getString("mynum")); //보낸사람
        int room_num=ob.getInt("room_num"); //그룹
        int reciever= roomservice.getRoomById(room_num).getReceiver_num();//받는사람num
        String content=ob.getString("msg"); //내용
        
        if(mynum==reciever) {//판매자가 채팅창에 들어올 때, 즉 판매자가 구매자에게 메시지를 보내게 됨
        	reciever=roomservice.getRoomById(room_num).getSender_num();
        }
        
        //메시지 저장
        MessageDto dto=new MessageDto();
		  
        int user_num=mynum;
        dto.setSender_num(user_num);
        
        dto.setReceiver_num(reciever);
        
        dto.setRoom_num(room_num);
        
        dto.setMess_content(content);
		  
        mservice.insertMessage(dto);
        
        
        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
		
        
    }

}