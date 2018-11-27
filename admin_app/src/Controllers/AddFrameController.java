/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AddFrameModel;
import Models.TablesModel;
import Views.AddFrameView;
import Views.TableView;
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
public class AddFrameController {
    private static AddFrameController _instance=null;
    
    
    public static AddFrameController getInstance(AddFrameView view)
    {
        if(_instance == null)
            _instance = new AddFrameController(view);
        return _instance;
    }
    
    private final AddFrameView view;
    private final AddFrameModel model;
    private List<TablesModel.Tables> tables=null; // save food categories
    
    private String result=null;
    
    private AddFrameController(AddFrameView view)
    {
        this.view=view;
        this.model=AddFrameModel.getInstance();
    }
    
    public String AddTable(String id)
    {
        CompletableFuture<String>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                if(result== null)
                    result = model.addTable(id);
                JOptionPane.showMessageDialog(null, result);
                return result;
            } catch (IOException ex) {
                Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        return null;
        
    }
}
