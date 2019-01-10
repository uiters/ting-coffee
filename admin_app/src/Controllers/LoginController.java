/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AccountModel.Account;
import Models.AccountTypeModel;
import Models.AccountTypeModel.AccountType;
import Models.LoginModel;
import Views.LoginView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class LoginController extends Controller{
     private static  LoginController _instance = null;
    
    
    public static LoginController getInstance(LoginView view)
    {
        if(_instance == null)
            _instance = new LoginController(view);
        return _instance;
    }
    
    private final LoginView view;
    private final LoginModel model;
    
    private List<Account> res=null;
    
    private LoginController(LoginView view)
    {
        this.view = view;
        this.model = LoginModel.getInstance();
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
    
    
    public void CheckLogin(Object object)
    {     
       String item=(String)object;
        CompletableFuture<List<Account>>  future;    
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                res = model.CheckLogin(item);
                return res;
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        //future.thenAccept(listFoodCategories -> view.loadView(listFoodCategories));
        future.thenAccept(acc -> view.loadView(acc));
    }
    
}
