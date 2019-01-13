/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Models.TablesModel.Tables;
import Controllers.TablesController;
import Models.TablesModel;
import com.placeholder.PlaceHolder;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;

/**
 *
 * @author Thang Le
 */
public class TableView extends View {
    
    private JTextField idText; //ID text
    private JTextField nameText; //Nametext
    private JTable table; //table Table
    private AddFrameView addFrame;
    private final TablesController controller;
    
    public TableView()
    {
        controller=TablesController.getInstance(this);
        addFrame=new AddFrameView("Add Table", controller);
        
    }
    
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
        Tables category = (Tables)objects;
        ((DefaultTableModel)table.getModel()).addRow(new Object[]{category.id, category.name});
    }
    
    @Override
    public void delete(int row){
        ((DefaultTableModel)table.getModel()).removeRow(row);
    }
    
    @Override
    public void update(int row, Object objects){
        Tables selectedtable = (Tables)objects;
        ((DefaultTableModel)table.getModel()).setValueAt(selectedtable.name, row, 1);
    }
    
    @Override
    public void loadView(Object objects){
        List<Tables> items = (List<Tables>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        items.forEach((item) -> {
            model.addRow(new Object[] { item.id,  item.name});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------
    
    public void Load(JPanel main,JPanel info,JPanel footer)
    {

        LoadTable(main);
        LoadInfoTable(info);
        LoadFooter(footer);
    }
    
    
    public void LoadTable(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
         //Table
        String []title=new String[]{"ID","Name"};
        
        /*Object [][]object=new Object[][]{
            {1,"Ban 1"},
            {2,"Ban 2"},
             {3,"Ban 3"},
                
        };*/
        DefaultTableModel model= new DefaultTableModel(null,title){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        table = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return int.class;
                    case 2:
                        return int.class;
                    default:
                        return String.class;
                }
            }
        };
        
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setSelectionMode(0);
        table.setRowHeight(80); // chỉnh độ cao của hàng
        
        controller.loadFull();
        JScrollPane jsp=new JScrollPane(table);
        
        /*Sự kiện click ở table*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                    int row=table.getSelectedRow();
                    setInfo( row);
                    
            }    
});
         /*End click table event*/
         
         
        /*END*/
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.add(jsp);
        main.add(Box.createRigidArea(new Dimension(5,0)));
        
        //repaint
        main.revalidate();
     
    }
    
    public void LoadInfoTable(JPanel info)
    {
        info.removeAll(); // remove all components
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300,info.getHeight()));
        
        /*search field*/
        JPanel search=new JPanel();
        search.setLayout(new BoxLayout(search,BoxLayout.X_AXIS));
        search.setBackground(new Color(209, 228, 252));
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));
        
        
        JTextField searchText=new JTextField();
        PlaceHolder p1;
        p1=new PlaceHolder (searchText,"Name");
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch=new JButton("Search");
        btnSearch.setForeground(new Color(0,107,68));
        btnSearch.add(Box.createRigidArea(new Dimension(43, 20)));
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(btnSearch);
        search.add(Box.createRigidArea(new Dimension(15,0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchText.getText().toLowerCase();
                if(text.equalsIgnoreCase("Name"))
                    text = "";
                Object data =  controller.Filter(text, null);
                if(data != null)
                    loadView(data);
            }
            
        });
        /*end search field*/
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(new Color(209, 228, 252));
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         idText=new JTextField();
         idText.setEditable(false);
         idText.setBackground(Color.lightGray);
         idText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idLabel=new JLabel("ID : ");
         idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDgroup.add(idLabel);
        IDgroup.add(Box.createRigidArea(new Dimension(70,0)));
        IDgroup.add(idText);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));         
        /*end ID*/
        
        /*Name*/
        JPanel Namegroup=new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(new Color(209, 228, 252));
        Namegroup.setMaximumSize(new Dimension(300, 30));
        
         nameText=new JTextField();
         nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel nameLabel=new JLabel("Name : ");
         nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Namegroup.add(nameLabel);
        Namegroup.add(Box.createRigidArea(new Dimension(48,0)));
        Namegroup.add(nameText);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end Name*/
        
        
        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        
         /*end info detail*/
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(search);
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(detail);
        
        //repaint panel
        info.revalidate();
        info.repaint();
    }
    
    public void LoadFooter(JPanel footer)
    {
        footer.removeAll(); // remove all components
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        footer.setPreferredSize(new Dimension(footer.getWidth(),50));
        JPanel btn=new JPanel();
        btn.setLayout(new BoxLayout(btn,BoxLayout.X_AXIS));
        btn.setBackground(new Color(228,249,245));
        
         JButton btnAdd=new JButton("Add");
        btnAdd.setForeground(new Color(0,107,68));
        btnAdd.add(Box.createRigidArea(new Dimension(50, 20)));
         JButton btnUpdate=new JButton("Update");
        btnUpdate.setForeground(new Color(0,107,68));
        btnUpdate.add(Box.createRigidArea(new Dimension(50, 20)));
         JButton btnDelete=new JButton("Delete");
        btnDelete.setForeground(new Color(0,107,68));
        btnDelete.add(Box.createRigidArea(new Dimension(50, 20)));
         //JButton btnCancel=new JButton("Cancel");
        //btnCancel.setForeground(new Color(0,107,68));
        //btnCancel.add(Box.createRigidArea(new Dimension(50, 20)));
         
         
         btn.add(Box.createRigidArea(new Dimension(5,0)));
         btn.add(btnAdd);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         btn.add(btnUpdate);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         btn.add(btnDelete);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         //btn.add(btnCancel);
         //btn.add(Box.createRigidArea(new Dimension(50,0)));
         
         footer.add(btn);
         //repaint panel
         footer.revalidate();
         footer.repaint();
         
         /*Sự kiện các btn Add,Update,Delete,Cancel*/
         btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.TableAdd();
                //controller.loadFull();
            }
        });
         btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly update
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    Tables item=TablesModel.getInstance().new Tables(Integer.parseInt(idText.getText()),nameText.getText(),-1);
                    update(row, item);
                    controller.update(item);
                }
                
            }
        });
         
         btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    controller.delete(table.getValueAt(row, 0));
                    delete(row);
                }
                
            }
        });
         
         /*btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly
            }
        });*/
         /*END sự kiện btn Add,....*/
    }
    
    /*get value from table and set to textfield*/
    private void setInfo(int row)
    {
            String ID=table.getModel().getValueAt(row, 0).toString();
            String Name=table.getModel().getValueAt(row, 1).toString();   
            idText.setText(ID);
            nameText.setText(Name);
    }
    
    
    /*set value to table*/
    private void setTable(int row)
    {
        table.setValueAt(idText.getText().toString(), row, 0);
        table.setValueAt(nameText.getText().toString(), row, 1);
    }

    public void setlistTables(List<Tables> list)
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        for(Tables obj : list)
        {
            model.addRow(new Object[] { obj.id,obj.name,obj.status});
         
        table.setModel(model);
        }
    }
    
    
}
