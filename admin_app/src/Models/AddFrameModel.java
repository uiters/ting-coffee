/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Models.TablesModel.Tables;
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
public class AddFrameModel {
     private static AddFrameModel _instance=null;
    
    public static AddFrameModel getInstance()
    {
        if(_instance==null)
            _instance=new AddFrameModel();
        return _instance;
    }
    
    private final Gson json;
    private final MySqlConnection mySqlConnection;
    
    private AddFrameModel()
    {
        json=new Gson();
        mySqlConnection=MySqlConnection.getInstance();
        
    }
    
    public String addTable(String id) throws IOException
    {
        String result=mySqlConnection.executeNoneQuery(Query.addTable, new Object[]{id});
        return result;
    }
}
