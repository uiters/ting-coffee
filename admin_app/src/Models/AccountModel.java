/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import Models.AccountTypeModel.AccountType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class AccountModel {
    private static AccountModel _instance = null;
    
    public static AccountModel getInstance() {
        if(_instance == null)
            _instance = new AccountModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private AccountModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
    //get all acount
    public List<Account> getAccounts() throws IOException //get all foods from database
    {
        String rawJson = mySqlConnection.executeQuery(Query.getAccount, null);
        if(rawJson == null)
            return null;
        Account[] accounts = json.fromJson(rawJson, Account[].class);//convert json to foods[]
        List<Account> listAccs = new LinkedList<>(Arrays.asList(accounts)); //convert food[] to list<food>
        return listAccs;
    }
    
    //get all account type
    public List<AccountType> getAccountTypes() throws IOException
    {
         String rawJson = mySqlConnection.executeQuery(Query.getAccountType, null);
        if(rawJson == null)
            return null;
        AccountType[] types = json.fromJson(rawJson, AccountType[].class);//
        List<AccountType> listTypes = new LinkedList<>(Arrays.asList(types)); //
        //JOptionPane.showMessageDialog(null, listTypes.size());
        return listTypes;
    }
    
    //delete
    public void delete(String username) throws IOException
    {
        //delete 
        String raw=mySqlConnection.executeNoneQuery(Query.delAccount, new Object[] { username });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Đã xóa thành công");
    }
    
    //update
    public void update(String username,String name,int sex,String idcard,String address,String number,String birth,String type) throws IOException
    {
        String raw=mySqlConnection.executeNoneQuery(Query.updateAccount, new Object[] { username,name,sex,idcard,address,number,birth,type });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Đã cập nhật thành công");
    }
    

    //-------------------
    
    
    
    public class Account
    {
        @SerializedName("Username") 
        public String username;
        @SerializedName("DisplayName") 
        public String name;
        @SerializedName("Sex") 
        public int sex;
        @SerializedName("IDCard") 
        public String idcard;
        @SerializedName("Address") 
        public String address;
        @SerializedName("PhoneNumber") 
        public String number;
        @SerializedName("BirthDay") 
        public String birth;
        @SerializedName("IDAccountType") 
        public int type;
        @SerializedName("AccountType") 
        public String typename;
        
        
        
        public Account(String user, String name,String id,String birth,int sex,String address,String number,int acctype)
        {
            this.username=user;
            this.name=name;
            this.idcard=id;
            this.birth=birth;
            this.sex=sex;
            this.address=address;
            this.number=number;
            this.type=acctype;
        }
        
        public Account(String user, String name,String id,String birth,int sex,String address,String number,String acctype)
        {
            this.username=user;
            this.name=name;
            this.idcard=id;
            this.birth=birth;
            this.sex=sex;
            this.address=address;
            this.number=number;
            this.typename=acctype;
        }
        
        public Account(){}
    }
    
    
}
