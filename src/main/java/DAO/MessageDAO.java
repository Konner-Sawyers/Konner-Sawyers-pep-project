package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    public Message createMesseage(Message message){
        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while(rs.next()){
                Message reutrn_message = new Message(
                    rs.getInt(1),
                    message.getPosted_by(),
                    message.getMessage_text(),
                    message.getTime_posted_epoch()
                );
                return reutrn_message;
            }  
        }

        catch(SQLException e){System.out.println(e);}

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
        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM message WHERE message_id = ?;";
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
                return message;
            }
        }

        catch(SQLException e){System.out.println(e);}
        return null;
    }

    public Message deleteMessageById(int id){
        try{
        Message message = getMessageById(id);

            Connection conn = ConnectionUtil.getConnection();
            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            return message;
        }

        catch(SQLException e){System.out.println(e);}
        return null;
    }

    public Message updateMessageById(int id, String text){
        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

            return getMessageById(id);
        }

        catch(SQLException e){System.out.println(e);}

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
