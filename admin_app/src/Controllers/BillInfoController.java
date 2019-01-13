/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillInfoModel;
import Models.BillInfoModel.BillInfo;
import Views.BillInfoView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Le
 */
public class BillInfoController extends Controller {
    private static BillInfoController _instance = null;
    
    public static BillInfoController getInstance(BillInfoView view) {
        _instance = new BillInfoController(view);
        return _instance;
    }
    
    private final BillInfoView view;
    private final BillInfoModel model;
    private List<BillInfo> bills = null;//save
    
    private BillInfoController(BillInfoView view) {
        this.view = view;
        this.model = BillInfoModel.getInstance();
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
        if(keyWord.isEmpty() || keyWord.trim().isEmpty())
            return bills;
        else
        {
            return null;
        }
    }
    
    public void LoadInfo(Object object)
    {
        int index=(int)object;
        CompletableFuture<List<BillInfo>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    bills = model.getBillInfo(index);
                return bills;
            } catch (IOException ex) {
                Logger.getLogger(BillInfoController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listBills -> view.loadView(listBills));
    }
}
