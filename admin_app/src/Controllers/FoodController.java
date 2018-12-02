/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.FoodModel;
import Models.FoodModel.Food;
import Views.FoodView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thienlan
 */
public class FoodController extends Controller{
    private static FoodController _instance = null;
    
    public static FoodController getInstance(FoodView view) {
        if(_instance == null)
            _instance = new FoodController(view);
        return _instance;
    }
    
    private final FoodView view;
    private final FoodModel model;
    private List<Food> foods = null;//save
    
    private FoodController(FoodView view) {
        this.view = view;
        this.model = FoodModel.getInstance();
    }
    
    public void getFoods() // get food from models
    {
        CompletableFuture<List<Food>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(foods == null)
                    foods = model.getFoods();
                return foods;
            } catch (IOException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listFoods -> view.setFoods(listFoods));
    }
    @Override
    public void insert(Object object){
        
    }
    @Override
    public void delete(Object object){
      
    }
    @Override
    public void update(Object object){
        
       
    }
    
    @Override
    public void loadFull()
    {
        
    }
    
   
    
}
