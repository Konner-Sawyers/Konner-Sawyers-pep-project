package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public Message createMessage(Message message){
        return messageDAO.createMesseage(message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id){
        return messageDAO.deleteMessageById(id);
    }

    public Message updateMessageById(int id, String text){
        return messageDAO.updateMessageById(id);
    }

    public List<Message> getMessageByUserId(int id){
        return messageDAO.getMessageByUserId(id);
    }

}
