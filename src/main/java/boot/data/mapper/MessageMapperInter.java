package boot.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import boot.data.Dto.MessageDto;


@Mapper
public interface MessageMapperInter {
	
	public List<MessageDto> selectAllChatByRoom(int room_num);
	public void insertMessage(MessageDto dto);
	public void deleteMessage(String mess_num);
	public int selectMaxNum();
	//public int getCountOfMessage(Map<String, String> map);
	public void deleteMessageByRoom(int room_num);

	/*public List<MessageDto> selectAllChatMemeber(Map<String, String> map);
	public MessageDto selectRecentMessage(Map<String, String> map);
	public List<MessageDto> selectAllByGroup(Map<String, Object> map);
	public int getRecentGroup(String user_num);
	public int getCountOfMessage(Map<String, String> map);
	public void deleteMessageGroup(int mess_group);*/
}