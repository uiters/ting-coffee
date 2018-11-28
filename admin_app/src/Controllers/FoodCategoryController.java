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
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.experimental.categories.Categories;



/**
 *
 * @author Thang Le
 */
public class FoodCategoryController extends Controller {
    
    private static  FoodCategoryController _instance = null;
    
    
    public static FoodCategoryController getInstance(FoodCategoryView view)
    {
        if(_instance == null)
            _instance = new FoodCategoryController(view);
        return _instance;
    }
    
    private final FoodCategoryView view;
    private final FoodCategoryModel model;
    
    private List<FoodCategory> foodcategories = null; // save food categories
    
    private FoodCategoryController(FoodCategoryView view)
    {
        this.view = view;
        this.model = FoodCategoryModel.getInstance();
    }
    
    @Override
    public void insert(Object object){
        String name = (String) object;
        
        CompletableFuture<FoodCategory>  future;
        
        future = CompletableFuture.supplyAsync(() -> {
            int id = 12;// id get from model;
            FoodCategory category = model.new FoodCategory(id, name);
            _addCategories(category);
            return category;
            // call get database o day
 
        });
        future.thenAccept(category -> view.insert(category));
    }
    @Override
    public void delete(Object object){
        int id = (int)object;
        _deleteCategories(id);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            //model.delete(id);
        });
    }
    @Override
    public void update(Object object){
        
        FoodCategory foodCategory = (FoodCategory)object;
        
        _updateCategories(foodCategory); 
        CompletableFuture.runAsync(() -> { //runAsync no return value
            model.delete(foodCategory.id);
        });
    }
    
    @Override
    public void loadFull()
    {
        CompletableFuture<List<FoodCategory>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(foodcategories == null){
                    foodcategories = model.getFoodCategory();
                }
                return foodcategories;
            } catch (IOException ex) {
                Logger.getLogger(FoodCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listFoodCategories -> view.loadView(listFoodCategories));
    }
    
    private void _updateCategories(FoodCategory foodCategory){
        for(FoodCategory category : foodcategories){
            if (category.id == foodCategory.id){
                category.nameCategory = foodCategory.nameCategory;
                break;
            }
        }
    }
    
    private void _deleteCategories(int id){
        //foodcategories.remove(foodCategory); //??
        for(FoodCategory category : foodcategories){
            if(category.id == id){
                foodcategories.remove(category);
                break;
            }
        }
    }
    private void _addCategories(FoodCategory category){
        foodcategories.add(category);
    }
            
}
