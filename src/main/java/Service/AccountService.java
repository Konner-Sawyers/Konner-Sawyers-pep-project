package Service;

import DAO.AccountDAO;
import Model.Account;


public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account createAccount(Account account){
        Account returnAccount = accountDAO.createAccount(account);
        return returnAccount;
    }

    public Account login(Account account){
        Account returnAccount = accountDAO.loginAccount(account);
        return returnAccount;
    }

}
