/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thang Le
 */
public class Food {
    private String []list={"All","An vat","Mon chinh","Mon trang mieng"} ; //danh sách trong category
    Food()
    {
        
    }
    
    public void Load(JPanel main,JPanel info)
    {
        LoadFood(main);
        LoadInfoFood(info);
    }
    public void LoadFood(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
         //Table
        String []title=new String[]{"ID","Name","Cagetory","Image","Price"};
        Object [][]object=new Object[][]{
            {1,"Banh trang tron","An vat","",10000},
             {2,"Banh trang tron","An vat","",10000},
                {3,"Banh trang tron","An vat","",10000},
                {4,"Banh trang tron","An vat","",10000},
                {5,"Banh trang tron","An vat","",10000},
                {6,"Banh trang tron","An vat","",10000},
                
        };
        DefaultTableModel model= new DefaultTableModel(object,title);
        JTable table=new JTable();
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setRowHeight(80); // chỉnh độ cao của hàng
        
        JScrollPane jsp=new JScrollPane(table);
        /*END*/
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.add(jsp);
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.revalidate();
    }
    
    public void LoadInfoFood(JPanel info)
    {
        info.removeAll(); // remove all components
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300,info.getHeight()));
        
        /*search field*/
        JPanel search=new JPanel();
        search.setLayout(new BoxLayout(search,BoxLayout.X_AXIS));
        search.setBackground(Color.yellow);
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));
        
        
        JTextField searchText=new JTextField();
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch=new JButton("Search");
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(btnSearch);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        
        /*end search field*/
        
        /*category*/
        JPanel category=new JPanel();
        category.setLayout(new BoxLayout(category,BoxLayout.X_AXIS));
        category.setBackground(Color.yellow);
        category.setMaximumSize(new Dimension(300, 30));
        
        JLabel categoryLabel=new JLabel("Select Category : ");
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JComboBox cb=new JComboBox(list);
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setMaximumRowCount(5);
        
        category.add(Box.createRigidArea(new Dimension(5,0)));
        category.add(categoryLabel);
        category.add(Box.createRigidArea(new Dimension(5,0)));
        category.add(cb);
        category.add(Box.createRigidArea(new Dimension(5,0)));
        /*end category*/
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(Color.yellow);
        
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(Color.yellow);
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         JTextField idText=new JTextField();
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
        Namegroup.setBackground(Color.yellow);
        Namegroup.setMaximumSize(new Dimension(300, 30));
        
         JTextField nameText=new JTextField();
         nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel nameLabel=new JLabel("Name : ");
         nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Namegroup.add(nameLabel);
        Namegroup.add(Box.createRigidArea(new Dimension(48,0)));
        Namegroup.add(nameText);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end Name*/
        
        /*Category*/
        JPanel Categorygroup=new JPanel();
        Categorygroup.setLayout(new BoxLayout(Categorygroup,BoxLayout.X_AXIS));
        Categorygroup.setBackground(Color.yellow);
        Categorygroup.setMaximumSize(new Dimension(300, 30));
        
        
        JComboBox cb2=new JComboBox(list);
        cb2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel LabelCategory=new JLabel("Category : ");
        LabelCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Categorygroup.add(Box.createRigidArea(new Dimension(5,0)));
        Categorygroup.add(LabelCategory);
        Categorygroup.add(Box.createRigidArea(new Dimension(30,0)));
        Categorygroup.add(cb2);
        Categorygroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*End Category*/
        
        /*Choose image*/
        JPanel Choosegroup=new JPanel();
        Choosegroup.setLayout(new BoxLayout(Choosegroup,BoxLayout.X_AXIS));
        Choosegroup.setBackground(Color.yellow);
        Choosegroup.setMaximumSize(new Dimension(300, 30));
        
         JLabel imgLabel=new JLabel("Image : ");
         imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         
        //Button choose image
        JButton btnChoose=new JButton("Choose File");
        btnChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
        JFileChooser choose=new JFileChooser();
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal=choose.showOpenDialog(jf);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    // xu ly
                }
            }
        });
        
        Choosegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Choosegroup.add(imgLabel);
        Choosegroup.add(Box.createRigidArea(new Dimension(45,0)));
        Choosegroup.add(btnChoose);
        Choosegroup.add(Box.createRigidArea(new Dimension(5,0)));
        
        /*End Choose image*/
        
        /*Price*/
        JPanel Pricegroup=new JPanel();
        Pricegroup.setLayout(new BoxLayout(Pricegroup,BoxLayout.X_AXIS));
        Pricegroup.setBackground(Color.yellow);
        Pricegroup.setMaximumSize(new Dimension(300, 30));
        
         JTextField priceText=new JTextField();
         priceText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel priceLabel=new JLabel("Price : ");
         priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Pricegroup.add(priceLabel);
        Pricegroup.add(Box.createRigidArea(new Dimension(50,0)));
        Pricegroup.add(priceText);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*End Price*/
        
        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Categorygroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Pricegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Choosegroup);
        
        /*end info detail*/
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(search);
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(category);
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(detail);
        info.revalidate();
        
    }
}
