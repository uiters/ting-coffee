/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.TableView;
import Models.TablesModel;
import Models.TablesModel.Tables;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Thang Le
 */
public class TablesController extends Controller {
    private static TablesController _instance=null;
    
    
    public static TablesController getInstance(TableView view)
    {
        if(_instance == null)
            _instance = new TablesController(view);
        return _instance;
    }
    
    private final TableView view;
    private final TablesModel model;
    private List<Tables> tables=null; // save food categories
    
    private String result=null;
    
    private TablesController(TableView view)
    {
        this.view=view;
        this.model=TablesModel.getInstance();
    }
    
    @Override
    public void insert(Object object){
        String name = (String) object;
        
        CompletableFuture<Tables>  future;
        
        future = CompletableFuture.supplyAsync(() -> {
            try
                {
                    //int id = model.getIDLast();// id get from model;
                    model.addTable(name); // insert to database
                    int id=model.getIDLast();
                    Tables item = model.new Tables(id, name,-1);
                    _addTables(item); // insert to table in list local
                    return item;
                }catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
           
            // call get database o day
            
        });
        future.thenAccept(item -> view.insert(item)); // insert to view
    }
    
    
    @Override
    public void delete(Object object){
        int id = (int)object;
        _deleteTables(id);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                    model.delete(id);
            }catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    @Override
    public void update(Object object){
        
        Tables item = (Tables)object;
        
        _updateTables(item); 
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                   // update
                model.update(item.id,item.name);
            }catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void loadFull()
    {
        CompletableFuture<List<Tables>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    tables = model.getTables();
                return tables;
            } catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listTables -> view.loadView(listTables));
    }
    
    @Override
    public Object Filter(String keyWord, Object opt)
    {
        if(tables == null) return null;
        if(keyWord.isEmpty() || keyWord.trim().isEmpty())
        {
            return tables;
        }
        else
        {
            return tables.stream().filter(item -> 
                    item.name.toLowerCase().contains(keyWord)).collect(Collectors.toList());
        }
    }
    
    //add trong list local
    private void _addTables(Tables item)
    {
        tables.add(item);
    }
    
    //xoa trong list local
    private void _deleteTables(int index)
    {
        for (Tables item : tables)
        {
            if (item.id==index)
            {
                tables.remove(item);
                break;
            }
                
        }
    }
    
    
    //cap nhat trong list local
    private void _updateTables(Tables index)
    {
        for (Tables item : tables)
        {
            if (item.id==index.id)
            {
                item.name=index.name;
                break;
            }             
        }
    }
}

