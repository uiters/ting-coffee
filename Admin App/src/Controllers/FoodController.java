/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.FoodModel;
import Models.FoodModel.Food;
import java.util.ArrayList;

/**
 *
 * @author thienlan
 */
public class FoodController {
    private static FoodController _instance = null;
    
    public static FoodController getInstance() {
        if(_instance == null)
            _instance = new FoodController();
        return _instance;
    }
    
    private FoodController() {}
    
    public ArrayList<Food> getFoods() // get food from models
    {
        return FoodModel.getInstance().getFoods();
    }
}
