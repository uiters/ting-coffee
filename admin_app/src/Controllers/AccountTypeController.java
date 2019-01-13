/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Thang Le
 */
public class AccountTypeController extends Controller {
    private static AccountTypeController _instance = null;
    
    public static AccountTypeController getInstance() {
        _instance = new AccountTypeController();
        return _instance;
    }
    private AccountTypeController() {
       
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
    @Override
    public Object Filter(String keyWord, Object opt)
    {
        return null;
    }
    
}
