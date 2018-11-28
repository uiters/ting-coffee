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
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class TablesController {
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
    
    public void getTables()
    {
        CompletableFuture<List<Tables>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(tables == null)
                    tables = model.getTables();
                return tables;
            } catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(list -> view.setlistTables(list));
    }
    
    
}
