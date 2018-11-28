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
    
    public void addTable(String name) throws IOException
    {
        mySqlConnection.executeNoneQuery(Query.addTable, new Object[] { name });
    }

    public void delete(int id) {
        //delete 
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
