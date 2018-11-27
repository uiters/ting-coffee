/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.FoodCategoryModel;
import Models.FoodCategoryModel.FoodCategory;
import Views.FoodCategoryView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Thang Le
 */
public class FoodCategoryController {
    private static  FoodCategoryController _instance=null;
    
    
    public static FoodCategoryController getInstance(FoodCategoryView view)
    {
        if(_instance == null)
            _instance = new FoodCategoryController(view);
        return _instance;
    }
    
    private final FoodCategoryView view;
    private final FoodCategoryModel model;
    private List<FoodCategory> foodcategories=null; // save food categories
    
    private FoodCategoryController(FoodCategoryView view)
    {
        this.view=view;
        this.model=FoodCategoryModel.getInstance();
    }
    
    public void getFoodCategory()
    {
        CompletableFuture<List<FoodCategory>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(foodcategories == null)
                    foodcategories = model.getFoodCategory();
                return foodcategories;
            } catch (IOException ex) {
                Logger.getLogger(FoodCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listFoodCategories -> view.SetFoodCategory(listFoodCategories));
        future.thenAccept(list -> view.SetListCategory(list));
    }
    
    public void getList()
    {
        CompletableFuture<List<FoodCategory>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(foodcategories == null)
                    foodcategories = model.getFoodCategory();
                return foodcategories;
            } catch (IOException ex) {
                Logger.getLogger(FoodCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(list -> view.SetListCategory(list));
    }
        
}
