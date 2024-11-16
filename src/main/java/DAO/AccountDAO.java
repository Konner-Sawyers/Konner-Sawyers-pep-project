package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public Account createAccount(Account account){
        //Checks for valid username and password entry
        if(account.username == null || account.username.trim().isEmpty() == true || account.password.length() < 5){return null;}

        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.username);
            preparedStatement.executeQuery();
            ResultSet users = preparedStatement.getGeneratedKeys();

            //If the results are empty
            if(!users.isBeforeFirst()){
                sql = "INSERT INTO account (username, password) VALUES (?,?);";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement2.setString(1, account.username);
                preparedStatement2.setString(2, account.password);

                preparedStatement2.executeUpdate();
                ResultSet user = preparedStatement2.getGeneratedKeys();
                
                //Read the inserted fields
                if(user.next()){
                    System.out.println("MADE IT HERE AGAIN!!!");
                    return new Account((int)user.getLong(1), account.username, account.password);
                }

            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public Account loginAccount(Account account){
        try{
            Connection conn = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.username);
            ResultSet user = preparedStatement.executeQuery();
            
            if(user.next()){
                if(user.getString("password").equals(account.password)){
                    return new Account((int)user.getLong(1), account.username, account.password);
                }
            }
        }

        catch(SQLException e){
            System.out.println(e);
        }

        return null;
    }



}
