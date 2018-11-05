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
    Food foodMain;
    
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
        
        foodMain=new Food();
        
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
                 foodMain.Load(main,info);
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

    
    public static void main(String[] args) {
        // TODO code application logic here
        Adminapp app=new Adminapp();
    }
    
}
