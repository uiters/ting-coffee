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
    public static String delFoodCategory="call USP_Admin_DelFoodCategory( @ID , Result )";
    //account
    
    //bill
    public static String getBill="call USP_Admin_GetBills() ";
    public static String delBill="call USP_Admin_DelBill( @ID )";
    public static String getBillInfo="call USP_Admin_GetBillInfo( @_ID )";
    //dashboard
    
    //acount
    public static String getAccount="call USP_Admin_GetAccount()";
    public static String getAccountType="call USP_Admin_GetAccountType()";
    public static String delAccount="call USP_Admin_DelAccount( @Username )";
    public static String updateAccount="call USP_Admin_UpdateAccount( @username , @name , @sex , @idcard , @address , @number , @birth , @type )";
    public static String addAccount="call USP_Admin_AddAccount( @username , @name , @sex , @idcard , @address , @number , @birth , @type , @pass )";
    public static String resetAccount="call USP_Admin_ResetPass( @username , @pass )";
    
    //login
    public static String checkLogin="call USP_Admin_CheckLogin( @username  )";
    
    
            
}
