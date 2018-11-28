/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class TablesModel {
    private static TablesModel _instance=null;
    
    public static TablesModel getInstance()
    {
        if(_instance==null)
            _instance=new TablesModel();
        return _instance;
    }
    
    private final Gson json;
    private final MySqlConnection mySqlConnection;
    
    private TablesModel()
    {
        json=new Gson();
        mySqlConnection=MySqlConnection.getInstance();
        
    }
    
    /**
     *
     * @return List<Tables>
     * @throws IOException
     */
    public List<Tables> getTables() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getTable, null);
        
        if(rawJson==null)
            return null;
        Tables[] tables=json.fromJson(rawJson, Tables[].class);
        List<Tables> listTable=Arrays.asList(tables);
        //JOptionPane.showMessageDialog(null, tables[0].name.toString());
        return listTable;
    }
    
   
    
    //-----------
    public class Tables
    {
        @SerializedName("ID") public int id;
        @SerializedName("Name") public String name;
        @SerializedName("Status") public int status;

        
        public Tables(int id,String name,int status)
        {
            this.id=id;
            this.name=name;
            this.status=status;
        }
        
        public Tables(){}
    }
}
