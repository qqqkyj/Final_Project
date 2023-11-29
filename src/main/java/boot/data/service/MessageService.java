package boot.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.data.Dto.MessageDto;
import boot.data.mapper.MessageMapperInter;

@Service
public class MessageService implements MessageServiceInter {

	@Autowired
	MessageMapperInter mapper;
	
	@Override
	public List<MessageDto> selectAllChatByRoom(int room_num) {
		// TODO Auto-generated method stub
		return mapper.selectAllChatByRoom(room_num);
	}

	@Override
	public void insertMessage(MessageDto dto) {
		// TODO Auto-generated method stub
		mapper.insertMessage(dto);
	}

	@Override
	public void deleteMessage(String mess_num) {
		// TODO Auto-generated method stub
		mapper.deleteMessage(mess_num);
	}

	@Override
	public int selectMaxNum() {
		// TODO Auto-generated method stub
		return mapper.selectMaxNum();
	}

	@Override
	public void deleteMessageByRoom(int room_num) {
		// TODO Auto-generated method stub
		mapper.deleteMessageByRoom(room_num);
	}


	/*@Override
	public int getCountOfMessage(String user_num, String other) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<>();
		
		map.put("user_num", user_num);
		map.put("other", other);
		
		return mapper.getCountOfMessage(map);
	}*/

}