/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
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
public class PasswordModel {
    private static PasswordModel _instance=null;
    
    public static PasswordModel getInstance()
    {
        if(_instance==null)
            _instance=new PasswordModel();
        return _instance;
    }
    
    private final Gson json;
    private final MySqlConnection mySqlConnection;
    
    private PasswordModel()
    {
        json=new Gson();
        mySqlConnection=MySqlConnection.getInstance();
        
    }
    
    
     public void update(String name,String pass) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.updatePassword, new Object[] { name , pass });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Changed password successfully!");
    }
    
    public List<AccountModel.Account> CheckPassword(String username) throws IOException
    {
        String rawJson= mySqlConnection.executeQuery(Query.checkPassword, new Object[] { username });
        if(rawJson.equals("[]")==true)
        {
            JOptionPane.showMessageDialog(null, "Account information is not correct!");
            return null;
        }
            
        AccountModel.Account[] item=json.fromJson(rawJson, AccountModel.Account[].class); // convert json to foodcategory[]
        List<AccountModel.Account> list = new LinkedList<>(Arrays.asList(item));
        return list;
    }
}
