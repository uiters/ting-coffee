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
public class Adminapp extends JFrame{

    JTabbedPane tabbedPane;
    JFrame jf;
    JPanel header;
    JPanel main;
    JPanel info;
    JPanel footer;
    String []list={"All","An vat","Mon chinh","Mon trang mieng"} ; //danh sách trong category
    public Adminapp()
    {
        jf=new JFrame("Cafe Management || Admin App");
        
       jf.setSize(1000, 700);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // đóng toàn bộ frame liên quan
       jf.setVisible(true);
       jf.setLocationRelativeTo(null);
       
        /* HEADER */
        header = new JPanel();
        
        
        /* MAIN TABLE */
        main = new JPanel();
        main.setBackground(Color.green);
       
        
        /* INFO */
        info = new JPanel();
        info.setBackground(Color.yellow);
        
        /* FOOTER */
        footer = new JPanel();
        footer.setBackground(Color.cyan);
        
        createHeader(header);
        jf.add(header, BorderLayout.PAGE_START);
        jf.add(main, BorderLayout.CENTER);
        jf.add(info, BorderLayout.LINE_END);
        jf.add(footer, BorderLayout.PAGE_END);
    }
    
    private void createHeader(JPanel header)
    {
         header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
         header.setBackground(new Color(228,249,245));
         
          /* BRAND */
        JPanel brandSection = new JPanel();
        brandSection.setBackground(new Color(228,249,245));
        
        JLabel brandImage = new JLabel();
        URL imgURL = getClass().getResource("../image/logo.png");
        brandImage.setIcon(new ImageIcon(imgURL));
        
        JLabel brandText = new JLabel("Starbucks – The Best Coffee and Espresso Drinks");
        brandText.setForeground(new Color(0,107,68));
        brandText.setBackground(new Color(228,249,245));
        brandText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
        brandSection.add(Box.createRigidArea(new Dimension(5, 0)));
        brandSection.add(brandImage);
        brandSection.add(Box.createRigidArea(new Dimension(15, 0)));
        brandSection.add(brandText);
        brandSection.add(Box.createRigidArea(new Dimension(10, 0)));
        /* END BRAND*/
        
        /*OPTIONS*/
        JPanel options = new JPanel();
        options.setBackground(new Color(228,249,245));
        options.setLayout(new BoxLayout(options, BoxLayout.X_AXIS));
        
        /*DASHBOARD*/
        JPanel dashboard = new JPanel();
        dashboard.setLayout(new BoxLayout(dashboard, BoxLayout.Y_AXIS));
        dashboard.setBackground(new Color(228,249,245));
        
        JLabel dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setForeground(new Color(41,55,72));
        dashboardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL dashboardURL = getClass().getResource("../image/dashboard.png");
        JLabel dashboardIcon = new JLabel(new ImageIcon(dashboardURL));
        dashboardIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        dashboard.add(dashboardTitle);
        dashboard.add(Box.createRigidArea(new Dimension(0, 6)));
        dashboard.add(dashboardIcon);
        /*END DASHBOARD OPTIONS*/
        
        /*FOOD*/
        JPanel food = new JPanel();
        food.setLayout(new BoxLayout(food, BoxLayout.Y_AXIS));
        food.setBackground(new Color(228,249,245));
        
        JLabel foodTitle = new JLabel("Food");
        foodTitle.setForeground(new Color(41,55,72));
        foodTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL foodURL = getClass().getResource("../image/food.png");
        JLabel foodIcon = new JLabel(new ImageIcon(foodURL));
        foodIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        food.add(foodTitle);
        food.add(Box.createRigidArea(new Dimension(0, 6)));
        food.add(foodIcon);
        
        food.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 LoadFood(main);
                 LoadInfoFood(info);
                 LoadFooter(footer);
             }
            
});
        /*END FOOD OPTIONS*/
        
         /*FOODCATEGORY*/
        JPanel foodcategory = new JPanel();
        foodcategory.setLayout(new BoxLayout(foodcategory, BoxLayout.Y_AXIS));
        foodcategory.setBackground(new Color(228,249,245));
        
        JLabel categoryTitle = new JLabel("Food Category");
        categoryTitle.setForeground(new Color(41,55,72));
        categoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL categoryURL = getClass().getResource("../image/category.png");
        JLabel categoryIcon = new JLabel(new ImageIcon(categoryURL));
        categoryIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        foodcategory.add(categoryTitle);
        foodcategory.add(Box.createRigidArea(new Dimension(0, 6)));
        foodcategory.add(categoryIcon);
        /*END FOOD OPTIONS*/
        
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(dashboard);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(food);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(foodcategory);
        /*END OPTIONS*/
        header.add(brandSection);
        header.add(options);
    }
    /**
     * @param args the command line arguments
     */
    void LoadFood(JPanel main)
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

    
    void LoadInfoFood(JPanel info)
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
    
    void LoadFooter(JPanel footer)
    {
        footer.removeAll(); // remove all components
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        footer.setPreferredSize(new Dimension(footer.getWidth(),50));
        JPanel btn=new JPanel();
        btn.setLayout(new BoxLayout(btn,BoxLayout.X_AXIS));
        btn.setBackground(Color.cyan);
        
         JButton btnAdd=new JButton("Add");
         JButton btnUpdate=new JButton("Update");
         JButton btnDelete=new JButton("Delete");
         JButton btnCancel=new JButton("Cancel");
         
         
         btn.add(Box.createRigidArea(new Dimension(5,0)));
         btn.add(btnAdd);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         btn.add(btnUpdate);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         btn.add(btnDelete);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         btn.add(btnCancel);
         btn.add(Box.createRigidArea(new Dimension(50,0)));
         
         footer.add(btn);
         footer.revalidate();
         
    }
    void Clear(JPanel a)
    {
        a.removeAll();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Adminapp app=new Adminapp();
    }
    
}
