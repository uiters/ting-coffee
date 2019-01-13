/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AccountModel;
import Models.AccountModel.Account;
import Models.PasswordModel;
import Views.ProfileView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Le
 */
public class PasswordController extends Controller {
    private static PasswordController _instance=null;
    
    
    public static PasswordController getInstance(ProfileView view)
    {
        _instance = new PasswordController(view);
        return _instance;
    }
    
    private final ProfileView view;
    private final PasswordModel model;
    private List<AccountModel.Account> res=null;
    
    private PasswordController(ProfileView view)
    {
        this.view=view;
        this.model=PasswordModel.getInstance();
    }
    
    @Override
    public void insert(Object object){

    }
    
    @Override
    public Object Filter(String keyWord, Object opt)
    {
        return null;
    }
    
    @Override
    public void delete(Object object){

    }
    @Override
    public void update(Object object){
        Account item = (Account)object;
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.update(item.username,item.pass);
            }catch (IOException ex) {
                Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void loadFull()
    {

    }
    
    public void UpdatePassword(Object object)
    {     
       String item=(String)object;
        CompletableFuture<List<AccountModel.Account>>  future;    
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                res = model.CheckPassword(item);
                return res;
            } catch (IOException ex) {
                Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        //future.thenAccept(listFoodCategories -> view.loadView(listFoodCategories));
        future.thenAccept(acc -> view.checkPassword(acc));
    }
}
