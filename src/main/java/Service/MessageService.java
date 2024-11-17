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
        //Returns null if message size is invalid
        if(message.getMessage_text().length() > 255 || message.getMessage_text().trim().length()== 0){return null;}
        return messageDAO.createMesseage(message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id){
        if(messageDAO.getMessageById(id) != null){
            return messageDAO.deleteMessageById(id);
        }
        else{return null;}
    }

    public Message updateMessageById(int id, Message message){
        String text = message.getMessage_text();
        if(text.length() > 255 || text.trim().length() < 1){return null;}
        return messageDAO.updateMessageById(id, text);
    }

    public List<Message> getMessageByUserId(int id){
        return messageDAO.getMessageByUserId(id);
    }

}
