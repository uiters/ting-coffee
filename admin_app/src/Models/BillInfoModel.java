/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thang Le
 */
public class BillInfoModel {
    private static BillInfoModel _instance = null;
    
    public static BillInfoModel getInstance() {
        if(_instance == null)
            _instance = new BillInfoModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private BillInfoModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
    
     public List<BillInfo> getBillInfo(int id) throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getBillInfo, new Object[] { id });
        if(rawJson==null)
            return null;
        BillInfo[] bills=json.fromJson(rawJson, BillInfo[].class); // convert json to foodcategory[]
        List<BillInfo> listBill = new LinkedList<>(Arrays.asList(bills));
        return listBill;
        
    }
    
    public class BillInfo
    {
        @SerializedName("IDBill") 
        public int id;
        @SerializedName("IDFood") 
        public int idfood;
        @SerializedName("FoodName") 
        public String namefood;  
        @SerializedName("Quantity") 
        public int quantity;
        
        public BillInfo(){}
        public BillInfo(int id,int idfood,String food,int quantity)
        {
            this.id=id;
            this.idfood=idfood;
            this.namefood=food;
            this.quantity=quantity;
        }
    }
}
