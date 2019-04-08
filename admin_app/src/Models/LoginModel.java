/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import Models.AccountModel.Account;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class LoginModel {
    private static LoginModel _instance=null;
    
    public static LoginModel getInstance()
    {
        if(_instance == null)
            _instance = new LoginModel();
        return _instance;
    }
    
    private final Gson json;// convert json
    private final MySqlConnection mySqlConnection;
    
    private LoginModel()
    {
        json=new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
    public List<Account> CheckLogin(String username) throws IOException
    {
        String rawJson= mySqlConnection.executeQuery(Query.checkLogin, new Object[] { username });
        if(rawJson.equals("[]")==true)
        {
            JOptionPane.showMessageDialog(null, "Account information is not correct!");
            return null;
        }
            
        Account[] item=json.fromJson(rawJson, Account[].class); // convert json to foodcategory[]
        List<Account> list = new LinkedList<>(Arrays.asList(item));
        return list;
    }
    
}
