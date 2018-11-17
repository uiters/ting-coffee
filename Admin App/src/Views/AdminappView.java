/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

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
public class AdminappView extends JFrame{

    JTabbedPane tabbedPane;
    JFrame jf;
    JPanel header;
    JPanel main;
    JPanel info;
    JPanel footer;
    FoodView foodMain;
    FoodCategoryView categoryMain;
    DashboardView dashboardMain;
    TableView tableMain;
    BillView billMain;
    AccountView staffMain;
    public AdminappView()
    {
        jf=new JFrame("Cafe Management || Admin App");
        
       jf.setSize(1280, 720);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // đóng toàn bộ frame liên quan
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
        
        foodMain=new FoodView();
        categoryMain=new FoodCategoryView();
        dashboardMain=new DashboardView();
        tableMain=new TableView();
        billMain=new BillView();
        staffMain=new AccountView();
        
        createHeader(header);
        jf.add(header, BorderLayout.PAGE_START);
        jf.add(main, BorderLayout.CENTER);
        jf.add(info, BorderLayout.LINE_END);
        jf.add(footer, BorderLayout.PAGE_END);
        jf.setVisible(true);

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
        
        dashboard.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 dashboardMain.Load(main,info);
             }
            
});
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
                 foodMain.Load(main,info,footer);
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
        
        foodcategory.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 categoryMain.Load(main,info,footer);
             }
});
        /*END FOODCATEGORY OPTIONS*/
        
        
        /*TABLE*/
        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(new Color(228,249,245));
        
        JLabel tableTitle = new JLabel("Table");
        tableTitle.setForeground(new Color(41,55,72));
        tableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL tableURL = getClass().getResource("../image/table.png");
        JLabel tableIcon = new JLabel(new ImageIcon(tableURL));
        tableIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         table.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 //load
                 tableMain.Load(main, info, footer);
             }
         });
        
        table.add(tableTitle);
        table.add(Box.createRigidArea(new Dimension(0, 6)));
        table.add(tableIcon);
                       
        /*END TABLE OPTIONS*/
        
        /*STAFF*/
        JPanel staff= new JPanel();
        staff.setLayout(new BoxLayout(staff, BoxLayout.Y_AXIS));
        staff.setBackground(new Color(228,249,245));
        
        JLabel staffTitle = new JLabel("Account");
        staffTitle.setForeground(new Color(41,55,72));
        staffTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL staffURL = getClass().getResource("../image/staff.png");
        JLabel staffIcon = new JLabel(new ImageIcon(staffURL));
        staffIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         staff.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 //load

                 staffMain.Load(main, info, footer);
             }
         });
        
        staff.add(staffTitle);
        staff.add(Box.createRigidArea(new Dimension(0, 6)));
        staff.add(staffIcon);
        
        /*END STAFF OPTIONS*/
        
        
        /*BILL*/
        JPanel bill= new JPanel();
        bill.setLayout(new BoxLayout(bill, BoxLayout.Y_AXIS));
        bill.setBackground(new Color(228,249,245));
        
        JLabel billTitle = new JLabel("Bill");
        billTitle.setForeground(new Color(41,55,72));
        billTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL billURL = getClass().getResource("../image/bill.png");
        JLabel billIcon = new JLabel(new ImageIcon(billURL));
        billIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         bill.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");
                 //load
                 billMain.Load(main, info, footer);
             }
         });
        
        bill.add(billTitle);
        bill.add(Box.createRigidArea(new Dimension(0, 6)));
        bill.add(billIcon);
        /*END BILL OPTIONS*/
        
        
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(bill);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(dashboard);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(food);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(foodcategory);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(table);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(staff);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        /*END OPTIONS*/
        header.add(brandSection);
        header.add(options);
    }
    /**
     * @param args the command line arguments
     */

    
    

    
    
    
}
