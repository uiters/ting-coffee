/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constants;

/**
 *
 * @author thienlan
 */
public class Query {
    //table
    public static String getTable="call USP_Admin_GetTables()";
    public static String addTable="call USP_InsertTable( @Nametable )";
    public static String getIDLastTable="call USP_Admin_GetIDLastTable()";
    public static String delTable="call USP_Admin_DelTables( @ID )";
    public static String updateTable="call USP_Admin_UpdateTable( @ID , @Name )";
    
    
    //food
     public static String getFoods = "call USP_Admin_GetFoods()";
     
     
     
    //food category
    public static String getFoodCategory= "call USP_GetFoodCategories()";
    public static String addFoodCagetory = "call USP_InsertFoodCatetory( @_Name )";
    public static String getIDLastFoodCategory="call USP_Admin_GetIDLastCategory()";
    public static String updateFoodCategory="call USP_Admin_UpdateFoodCategory( @ID , @Name )";
    
    //account
    
    //bill
    public static String getBill="call USP_Admin_GetBills() ";
    public static String delBill="call USP_Admin_DelBill( @ID )";
    //dashboard
   
    
    
            
}
