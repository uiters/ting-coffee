/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

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
    
    private FoodModel() {}
    
    public ArrayList<Food> getFoods() //get all food from database
    {
        return null;
    }
    
    public class Food {

        public int id;
        public int idCategory;
        public String name;
        public double price;
        public int idImage;

        Food(int id, int idCategory, String name, double price, int idImage) {
            this.id = id;
            this.name = name;
            this.name = name;
            this.price = price;
            this.idImage = idImage;
        }
    }
}
