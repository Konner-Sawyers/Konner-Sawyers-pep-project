package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    public Message createMesseage(Message message){
        return null;
    } 

    public List<Message> getAllMessages(){
        List<Message> messages = new ArrayList<>();

        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getLong(4)
                );
                messages.add(message);
            }
        }

        catch(SQLException e){System.out.println(e);}

        return messages;
    }

    public Message getMessageById(int id){
        return null;
    }

    public String deleteMessageById(int id){
        return null;
    }

    public Message updateMessageById(int id){
        return null;
    }


    public List<Message> getMessageByUserId(int id){
        List<Message> messages = new ArrayList<>();

        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getLong(4)
                );
                messages.add(message);
            }
        }

        catch(SQLException e){System.out.println(e);}

        return messages;
    }



}
