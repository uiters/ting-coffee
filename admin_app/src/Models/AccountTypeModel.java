/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Thang Le
 */
public class AccountTypeModel {
    private static AccountTypeModel _instance = null;
    
    public static AccountTypeModel getInstance() {
        if(_instance == null)
            _instance = new AccountTypeModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private AccountTypeModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    public class AccountType
    {
        @SerializedName("ID") 
        public int id;
        @SerializedName("Name") 
        public String name;
        
        public AccountType(){}
        public AccountType(int id,String name)
        {
            this.id=id;
            this.name=name;
        }
    }
}
