/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Models.FoodCategoryModel.FoodCategory;
import Controllers.FoodCategoryController;
import Models.FoodCategoryModel;
import com.placeholder.PlaceHolder;
import java.util.List;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Thang Le
 */
public class FoodCategoryView extends View{
    private DefaultTableModel tablemodel;
    private JTextField idText; //ID text
    private JTextField nameText; //Nametext
    private JTable table; //table FOOD
    private final AddFrameView addFrame;
    private List<String> list=new ArrayList<>(); // dung de luu list gan qua foodview
    private List<String> listCate=new ArrayList<>(); // dung de luu list gan qua foodview
    private final FoodCategoryController controller;
    public FoodCategoryView()
    {
        
        controller = FoodCategoryController.getInstance(this);
        addFrame=new AddFrameView("Add Cagetory", controller);
        
     }
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
        FoodCategory category = (FoodCategory)objects;
        ((DefaultTableModel)table.getModel()).addRow(new Object[]{category.id, category.nameCategory});
    }
    
    @Override
    public void delete(int row){
        ((DefaultTableModel)table.getModel()).removeRow(row);
    }
    
    @Override
    public void update(int row, Object objects){
        FoodCategory category = (FoodCategory)objects;
        ((DefaultTableModel)table.getModel()).setValueAt(category.nameCategory, row, 1);
    }
    
    @Override
    public void loadView(Object objects){
        List<FoodCategory> categories = (List<FoodCategory>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        categories.forEach((foodcategory) -> {
            model.addRow(new Object[] { foodcategory.id,  foodcategory.nameCategory});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------
    public void Load(JPanel main,JPanel info,JPanel footer)
    {
        LoadCategory(main);
        LoadInfoCategory(info);
        LoadFooter(footer);
        
    }
    
    //Load table FoodCategory
    public void LoadCategory(JPanel main)
    {
        main.removeAll();
        
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
         //Table
        String []title=new String[]{"ID","Name"};
        
        DefaultTableModel model= new DefaultTableModel(null,title){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        final TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(model); 
        table=new JTable();      
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.getTableHeader().setReorderingAllowed(false); // khong cho di chuyen thu tu cac column
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setSelectionMode(0);
        table.setRowHeight(80); // chỉnh độ cao của hàng
        table.setRowSorter(sorter1);
       JScrollPane jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        controller.loadFull();
        
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
    
    //Load info FoodCategory
    public void LoadInfoCategory(JPanel info)
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
        JLabel lblSearch=new JLabel("Search");
        lblSearch.setForeground(new Color(0,107,68));
        lblSearch.add(Box.createRigidArea(new Dimension(43, 20)));
        lblSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(lblSearch);
        search.add(Box.createRigidArea(new Dimension(50,0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        
        searchText.getDocument().addDocumentListener(new DocumentListener(){
           @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchText.getText().toLowerCase();
                if(text.equalsIgnoreCase("Name"))
                    text = "";
                Object data =  controller.Filter(text, null);
                if(data != null)
                    loadView(data);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 String text = searchText.getText().toLowerCase();
                if(text.equalsIgnoreCase("Name"))
                    text = "";
                Object data =  controller.Filter(text, null);
                if(data != null)
                    loadView(data);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    //Load footer
    void LoadFooter(JPanel footer)
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
         
         //repaint
         footer.revalidate();
         footer.repaint();
         
         /*Sự kiện các btn Add,Update,Delete,Cancel*/
         btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.FoodCategoryAdd();
                JOptionPane.showMessageDialog(null, "Reload database ");
            }
        });
         btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly update
                int row=table.getSelectedRow();
                if(row >= 0)
                {
                    FoodCategory category = FoodCategoryModel.getInstance().new FoodCategory(Integer.parseInt(idText.getText()), nameText.getText());
                    update(row, category);
                    controller.update(category);
                }
                
            }
        });
         
         btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                if(row >= 0)
                {
                    controller.delete(table.getValueAt(row, 0));
                    delete(row);//delete in view
                }
                
            }
        });
         
        /* btnCancel.addActionListener(new ActionListener() {
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
        table.setValueAt(idText.getText(), row, 0);
        table.setValueAt(nameText.getText(), row, 1);
    }
    
    /*set food category*/

    
    
    /*end set food category*/
    

}
