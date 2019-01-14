/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import Models.FoodCategoryModel.FoodCategory;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author thienlan
 */
public class FoodModel {
    
    private static FoodModel _instance = null;
    
    public static FoodModel getInstance() {
        if(_instance == null)
            _instance = new FoodModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private FoodModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
    /**
     *
     * @return List<Food>
     * @throws IOException
     */
    public List<Food> getFoods() throws IOException //get all foods from database
    {
        String rawJson = mySqlConnection.executeQuery(Query.getFoods, null);
        if(rawJson == null)
            return null;
        Food[] foods = json.fromJson(rawJson, Food[].class);//convert json to foods[]
        List<Food> listFoods = new LinkedList<>(Arrays.asList(foods)); //convert food[] to list<food>
        return listFoods;
    }
    
    public List<FoodCategory> getFoodCategory() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getFoodCategory, null);
        if(rawJson==null)
            return null;
        FoodCategory[] foodcategories=json.fromJson(rawJson, FoodCategory[].class); // convert json to foodcategory[]
        List<FoodCategory> listFoodCategories = new LinkedList<>(Arrays.asList(foodcategories));
        return listFoodCategories;
    }
    
    
    public void update(int index,String name,String category,double price,String pathimg) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.updateFood, new Object[] { index , name ,category,price,pathimg });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Updated image successfully!");
    }
    
    public void update(int index,String name,String category,double price) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.updateInfoFood, new Object[] { index , name ,category,price });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Updated successfully!");
    }
    
    
    private int BeforeDelete(int id) throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.beforedelFood, new Object[] { id });
        Result[] ress=json.fromJson(rawJson, Result[].class);
        List<Result> list = new LinkedList<>(Arrays.asList(ress));
        return list.get(0).result;
    }
    public void delete(int id) throws IOException
    {
        //delete 
        int d=BeforeDelete(id);
        if(d<=0)
        {
            String raw=mySqlConnection.executeNoneQuery(Query.delFood, new Object[] { id });
            JOptionPane.showMessageDialog(null, "Deleted successfully!");
        }
        else JOptionPane.showMessageDialog(null, "Can't delete because it exists in bills!");
    }
    
    public void addFood(String name,String category,double price,String path) throws IOException
    {
        String raw= mySqlConnection.executeNoneQuery(Query.addFood, new Object[] { name , category , price, path });
        if (raw.equals("1")==true) JOptionPane.showMessageDialog(null, "Added successfully!");
    }
    
    public int getIDLast() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getIDLastFood, null);
         if(rawJson==null)
            return 0;
         Food[] foods=json.fromJson(rawJson, Food[].class); // convert json to foodcategory[]
            List<Food> listFood = new LinkedList<>(Arrays.asList(foods));
         return listFood.get(0).id;
    }
    
    
    
    //-------------------------------------------------------------------------------------
    public class Food {
        @SerializedName("ID") public int id;
        @SerializedName("IDCategory") public int idCategory;
        @SerializedName("NameCategory") public String nameCategory;
        @SerializedName("Name") public String name;
        @SerializedName("Price") public double price;
        @SerializedName("IDImage") public int idImage;
        @SerializedName("Image") @Expose public String stringImage;
        public byte[] image;
        public Food(int id, int idCategory, String name, double price, int idIamge){
            this.id = id;
            this.idCategory = idCategory;
            this.name = name;
            this.price = price;
            this.idImage = idIamge;
        }
        
        public Food(int id, String name, String category, double price, String pathIamge){
            this.id = id;
            this.nameCategory = category;
            this.name = name;
            this.price = price;
            this.stringImage = pathIamge;
        }
        
        public Food(int id, String name, String category, double price){
            this.id = id;
            this.nameCategory = category;
            this.name = name;
            this.price = price;
        }
        
        public Food(String name, String category, double price){
            this.nameCategory = category;
            this.name = name;
            this.price = price;
        }
        
        public Food(String name, String category, double price, String path){
            this.nameCategory = category;
            this.name = name;
            this.price = price;
            this.stringImage = path;
        }
        
        public byte[] getImage(){
            if(image == null)
                image = Base64.getDecoder().decode(stringImage);
            return image;
        }
        public Food(){}
    }
    
    //class nay dung de luu ket qua select
    public class Result
    {
        @SerializedName("count(*)") public int result;
    }
}
