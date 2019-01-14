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
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class DashboardModel {
    private static DashboardModel _instance = null;
    
    public static DashboardModel getInstance() {
        if(_instance == null)
            _instance = new DashboardModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private DashboardModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
     public List<Report> getReportWeek() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getReportWeek, null);
        if(rawJson==null)
            return null;
        Report[] reports=json.fromJson(rawJson, Report[].class); // convert json to foodcategory[]
        List<Report> listReport = new LinkedList<>(Arrays.asList(reports));
        return listReport;
        
    }
     public List<Report> getReportToday() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getReportToday, null);
        if(rawJson==null)
            return null;
        Report[] reports=json.fromJson(rawJson, Report[].class); // convert json to foodcategory[]
        List<Report> listReport = new LinkedList<>(Arrays.asList(reports));
        return listReport;
        
    }
     public List<Report> getReportMonth() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getReportMonth, null);
        if(rawJson==null)
            return null;
        Report[] reports=json.fromJson(rawJson, Report[].class); // convert json to foodcategory[]
        List<Report> listReport = new LinkedList<>(Arrays.asList(reports));
        return listReport;
        
    }
     public List<Report> getReportYear() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getReportYear, null);
        if(rawJson==null)
            return null;
        Report[] reports=json.fromJson(rawJson, Report[].class); // convert json to foodcategory[]
        List<Report> listReport = new LinkedList<>(Arrays.asList(reports));
        return listReport;
        
    }
     
     public void delete(int id) throws IOException
     {
     }
    
    public class Report
    {
        @SerializedName("ID") 
        public int id;
        @SerializedName("_Date") 
        public String _date;
        @SerializedName("TotalPrice") 
        public double price;

        
        
        public Report(int id,String day,Double price)
        {
            this.id = id;
            this._date=day;
            this.price=price;

        }
        
        public Report(){}
    }
}
