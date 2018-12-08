/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.FoodCategoryModel;
import Models.FoodCategoryModel.FoodCategory;
import Models.FoodModel;
import Models.FoodModel.Food;
import Views.FoodView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    private List<Food> foods = null;//save food
    private List<FoodCategory> foodcategorie=null; //save food category
    
    private FoodController(FoodView view) {
        this.view = view;
        this.model = FoodModel.getInstance();
    }
    
    
    public void setList()
    {
         CompletableFuture<List<FoodCategoryModel.FoodCategory>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                foodcategorie = model.getFoodCategory();
                return foodcategorie;
            } catch (IOException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listFoodCategories -> view.setList(listFoodCategories));
    }
    
    public void getFoods() // get food from models
    {
        
    }
    @Override
    public void insert(Object object){
        
    }
    @Override
    public void delete(Object object){
      int index=(int)object;
      _deleteFoods(index);
        CompletableFuture.runAsync(() -> { //runAsync no return value
             try
            {
                 //delete
                model.delete(index);
                   
            }catch (IOException ex) {
                Logger.getLogger(FoodCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    //update image food
    @Override
    public void update(Object object){
        Food food = (Food)object;
        _updateFood(food);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.update(food.id,food.name,food.nameCategory,food.price,food.stringImage);
            }catch (IOException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
    }
    
    //update info food
    public void updateInfo(Object object){
        Food food = (Food)object;
        _updateFood(food);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.update(food.id,food.name,food.nameCategory,food.price);
            }catch (IOException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
    }
    
    @Override
    public void loadFull()
    {
        CompletableFuture<List<Food>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    foods = model.getFoods();
                return foods;
            } catch (IOException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listFoods -> view.loadView(listFoods));
    }
    
   private void _deleteFoods(int index)
   {
       //foodcategories.remove(foodCategory); //??
        for(Food category : foods){
            if(category.id == index){
                foods.remove(category);
                break;
            }
        }
   }
   
   private void _updateFood(Food object)
   {
        for(Food category : foods){
            if (category.id == object.id){
                category.name = object.name;
                category.idCategory= object.idCategory;
                category.nameCategory = object.nameCategory;
                category.price = object.price;
                category.stringImage = object.stringImage;
                category.idImage = object.idImage;
                category.image = object.image;
                break;
            }
        }
       
   }
    
}
