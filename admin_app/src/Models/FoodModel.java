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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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
        List<Food> listFoods = Arrays.asList(foods); //convert food[] to list<food>
        return listFoods;
    }
    //-------------------------------------------------------------------------------------
    public class Food {
        @SerializedName("ID") public int id;
        @SerializedName("IDCategory") public int idCategory;
        @SerializedName("NameCategory") public String nameCategory;
        @SerializedName("Name") public String name;
        @SerializedName("Price") public double price;
        @SerializedName("IDImage") public int idImage;
        @SerializedName("Image") @Expose private String stringImage;
        public byte[] image;
        public Food(int id, int idCategory, String name, double price, int idIamge){
            this.id = id;
            this.idCategory = idCategory;
            this.name = name;
            this.price = price;
            this.idImage = idIamge;
        }
        public byte[] getImage(){
            if(image == null)
                image = Base64.getDecoder().decode(stringImage);
            return image;
        }
        public Food(){}
    }
}
