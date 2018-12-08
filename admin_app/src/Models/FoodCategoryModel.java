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
public class FoodCategoryModel {
    private static FoodCategoryModel _instance=null;
    
    public static FoodCategoryModel getInstance()
    {
        if(_instance == null)
            _instance = new FoodCategoryModel();
        return _instance;
    }
    
    private final Gson json;// convert json
    private final MySqlConnection mySqlConnection;
    
    private FoodCategoryModel()
    {
        json=new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
    /*get list FoodCategory*/
    /**
     *
     * @return List<>
     * @throws IOException
     */
    public List<FoodCategory> getFoodCategory() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getFoodCategory, null);
        if(rawJson==null)
            return null;
        FoodCategory[] foodcategories=json.fromJson(rawJson, FoodCategory[].class); // convert json to foodcategory[]
        List<FoodCategory> listFoodCategories = new LinkedList<>(Arrays.asList(foodcategories));
        return listFoodCategories;
        
    }
    
    public void addFoodCagetory(String name) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.addFoodCagetory, new Object[] { name });
    }
    
    public void update(int index,String name) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.updateFoodCategory, new Object[] { index , name });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Đã cập nhật thành công");
    }
    
    private int beforedelete(int index) throws IOException
    {
         String rawJson=mySqlConnection.executeQuery(Query.beforedelFoodCategory, new Object[] { index });
        FoodModel.Result[] ress=json.fromJson(rawJson, FoodModel.Result[].class);
        List<FoodModel.Result> list = new LinkedList<>(Arrays.asList(ress));
        return list.get(0).result;
    }

    public void delete(int id) throws IOException 
    {
        //delete 
        int d=beforedelete(id);
        if(d<=0)
        {
            String raw=mySqlConnection.executeNoneQuery(Query.delFoodCategory, new Object[] { id });
            JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
        }
        else JOptionPane.showMessageDialog(null, "Không thể xóa do đã tồn tại trong bill");
        
    }
    
    //get last id from model to insert to table
    public int getIDLast() throws IOException
    {
         String rawJson=mySqlConnection.executeQuery(Query.getIDLastFoodCategory, null);
         if(rawJson==null)
            return 0;
         FoodCategory[] foodcategories=json.fromJson(rawJson, FoodCategory[].class); // convert json to foodcategory[]
            List<FoodCategory> listFoodCategories = new LinkedList<>(Arrays.asList(foodcategories));
         return listFoodCategories.get(0).id;
    }
    
    /*End*/
    //----------------------------------
    public class FoodCategory
    {
        @SerializedName("ID") 
        public int id;
        @SerializedName("Name") 
        public String nameCategory;
        
        
        public FoodCategory(int id, String name)
        {
            this.id = id;
            this.nameCategory = name;
        }
        
        public FoodCategory(){}
    }
}
