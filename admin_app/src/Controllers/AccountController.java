/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AccountModel;
import Models.AccountModel.Account;
import Models.AccountTypeModel.AccountType;
import Views.AccountView;
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
public class AccountController extends Controller {
    private static AccountController _instance = null;
    
    public static AccountController getInstance(AccountView view) {
        if(_instance == null)
            _instance = new AccountController(view);
        return _instance;
    }
    
    private final AccountView view;
    private final AccountModel model;
    private List<Account> accounts = null;//save
    private List<AccountType> types=null;
    //private List<FoodCategory> foodcategorie=null;
    
    private AccountController(AccountView view) {
        this.view = view;
        this.model = AccountModel.getInstance();
    }
    
    @Override
    public void insert(Object object){
        Account acc = (Account) object;
        CompletableFuture<Account>  future;
        
        future = CompletableFuture.supplyAsync(() -> {
            try
                { 
                    model.addAccount(acc.username,acc.name,acc.sex,acc.idcard,acc.address,acc.number,acc.birth,acc.typename,acc.pass); // insert to database
                    _addAccount(acc); // insert to table in list local
                    return acc;
                }catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
         
    });
        future.thenAccept(newacc -> view.insert(newacc)); // insert to view
     }
    @Override
    public void delete(Object object){
      String id = (String)object;
        _deleteAccount(id);
        CompletableFuture.runAsync(() -> { //runAsync no return value
             try
            {
                 //delete
                model.delete(id);
            }catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    @Override
    public void update(Object object){
        Account acc = (Account)object;
        _updateAccount(acc);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.update(acc.username,acc.name,acc.sex,acc.idcard,acc.address,acc.number,acc.birth,acc.typename);
            }catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
    }
    
    @Override
    public void loadFull()
    {
        CompletableFuture<List<Account>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    accounts = model.getAccounts();
                return accounts;
            } catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listAccs -> view.loadView(listAccs));
    }
    
    public void setListAccType()
    {
        CompletableFuture<List<AccountType>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    types = model.getAccountTypes();
                return types;
            } catch (IOException ex) 
            {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listAccTypes -> view.setList(listAccTypes));
    }
    
    
    public void ResetPass(String username,String pass)
    {
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.resetPassword(username,pass);
            }catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void _deleteAccount(String index)
    {
        //foodcategories.remove(foodCategory); //??
        for(Account category :accounts){
            if(category.username == index){
                accounts.remove(category);
                break;
            }
        }
    }
    
    private void _updateAccount(Account object)
    {
        for(Account category : accounts){
            if (category.username == object.username){
                category.name = object.name;
                category.sex = object.sex;
                category.address= object.address;
                category.birth = object.birth;
                category.type = object.type;
                category.typename = object.typename;
                category.idcard = object.idcard;
                category.number = object.number;
                break;
            }
        }
    }
    
    private void _addAccount(Account object)
    {
        accounts.add(object);
    }
}
