/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
    LoginView loginView;
    ProfileView profileView;
    JLabel dashboardTitle;
    JLabel foodTitle;
    JLabel categoryTitle;
    JLabel tableTitle;
    JLabel staffTitle;
    JLabel billTitle;
    JLabel logoutTitle;
    JLabel profileTitle;
    String username="";
    public AdminappView(String a)
    {
        username=a;
        jf=new JFrame("Cafe Management || Admin App");
        
       jf.setSize(1400, 800);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // đóng toàn bộ frame liên quan
       jf.setLocationRelativeTo(null);
       
       
       
        /* HEADER */
        header = new JPanel();
        
        
        /* MAIN TABLE */
        main = new JPanel();
        main.setBackground(new Color(0,107,68));
       
        
        /* INFO */
        info = new JPanel();
        info.setBackground(new Color(209, 228, 252));
        
        /* FOOTER */
        footer = new JPanel();
        footer.setBackground(new Color(228,249,245));
        foodMain=new FoodView();
        categoryMain=new FoodCategoryView();
        dashboardMain=new DashboardView();
        tableMain=new TableView();
        billMain=new BillView();
        staffMain=new AccountView();
        profileView=new ProfileView(username);
        
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
        
        dashboardTitle = new JLabel("Dashboard");
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
                 /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 setForeColor();
                 dashboardTitle.setForeground(Color.red);
                 dashboardMain.Load(main,info,footer);
             }
            
});
        /*END DASHBOARD OPTIONS*/
        
        /*FOOD*/
        JPanel food = new JPanel();
        food.setLayout(new BoxLayout(food, BoxLayout.Y_AXIS));
        food.setBackground(new Color(228,249,245));
        
        foodTitle = new JLabel("Food");
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
                /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 //foodMain.setList(categoryMain.getList());
                 setForeColor();
                 foodTitle.setForeground(Color.red);
                 foodMain.Load(main,info,footer);
             }
            
});
        /*END FOOD OPTIONS*/
        
         /*FOODCATEGORY*/
        JPanel foodcategory = new JPanel();
        foodcategory.setLayout(new BoxLayout(foodcategory, BoxLayout.Y_AXIS));
        foodcategory.setBackground(new Color(228,249,245));
        
        categoryTitle = new JLabel("Food Category");
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
               /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 setForeColor();
                 categoryTitle.setForeground(Color.red);
                 categoryMain.Load(main,info,footer);
                 //categoryMain.getList();
             }
});
        /*END FOODCATEGORY OPTIONS*/
        
        
        /*TABLE*/
        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(new Color(228,249,245));
        
        tableTitle = new JLabel("Table");
        tableTitle.setForeground(new Color(41,55,72));
        tableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL tableURL = getClass().getResource("../image/table.png");
        JLabel tableIcon = new JLabel(new ImageIcon(tableURL));
        tableIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         table.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 //load
                 setForeColor();
                 tableTitle.setForeground(Color.red);
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
        
        staffTitle = new JLabel("Account");
        staffTitle.setForeground(new Color(41,55,72));
        staffTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL staffURL = getClass().getResource("../image/staff.png");
        JLabel staffIcon = new JLabel(new ImageIcon(staffURL));
        staffIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         staff.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 //load
                 setForeColor();
                 staffTitle.setForeground(Color.red);
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
        
        billTitle = new JLabel("Bill");
        billTitle.setForeground(new Color(41,55,72));
        billTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL billURL = getClass().getResource("../image/bill.png");
        JLabel billIcon = new JLabel(new ImageIcon(billURL));
        billIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
         bill.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                /*JOptionPane.showMessageDialog(null,"Xử lý sự kiện Load Database");*/
                 //load
                 setForeColor();
                 billTitle.setForeground(Color.red);
                 billMain.Load(main, info, footer);
             }
         });
        
        bill.add(billTitle);
        bill.add(Box.createRigidArea(new Dimension(0, 6)));
        bill.add(billIcon);
        /*END BILL OPTIONS*/
        
         /*LOG OUT*/
        JPanel logout = new JPanel();
        logout.setLayout(new BoxLayout(logout, BoxLayout.Y_AXIS));
        logout.setBackground(new Color(228,249,245));
        
        logoutTitle = new JLabel("Exit");
        logoutTitle.setForeground(new Color(41,55,72));
        logoutTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL logoutURL = getClass().getResource("../image/logout.png");
        JLabel logoutIcon = new JLabel(new ImageIcon(logoutURL));
        logoutIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        logout.add(logoutTitle);
        logout.add(Box.createRigidArea(new Dimension(0, 6)));
        logout.add(logoutIcon);
        
        logout.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 setForeColor();
                 logoutTitle.setForeground(Color.red);
                 int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Notification", JOptionPane.YES_NO_OPTION);
                 if (reply == JOptionPane.YES_OPTION) {
                    //xu ly log out
                    System.exit(0);
                }
                else {
                }
                
             }
            
});
        /*END LOG OUT OPTIONS*/
        
        /*PROFILE*/
        JPanel profile = new JPanel();
        profile.setLayout(new BoxLayout(profile, BoxLayout.Y_AXIS));
        profile.setBackground(new Color(228,249,245));
        
        profileTitle = new JLabel("Password");
        profileTitle.setForeground(new Color(41,55,72));
        profileTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL profileURL = getClass().getResource("../image/key.png");
        JLabel profileIcon = new JLabel(new ImageIcon(profileURL));
        profileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        profile.add(profileTitle);
        profile.add(Box.createRigidArea(new Dimension(0, 6)));
        profile.add(profileIcon);
        
        profile.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                 setForeColor();
                 profileTitle.setForeground(Color.red);
                 profileView.Load();
                 Wait(2);
                
             }
            
});
        /*END PROFILE*/
        
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
        options.add(profile);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        options.add(logout);
        options.add(Box.createRigidArea(new Dimension(30, 0)));
        /*END OPTIONS*/
        header.add(brandSection);
        header.add(options);
    }
    /**
     * @param args the command line arguments
     */
   
    public void setLoginView(LoginView a)
    {
        loginView=a;
    }
    
    private void Wait(int x)
    {
            //long timewait=Long.parseLong(timeText.getText()); // convert string to long
            Timer wait=new Timer();
            //flag=true; //set flag to recognize timer is started
            TimerTask task=new TimerTask() {
                long second=0;
                @Override
                public void run() {               
                    //JOptionPane.showMessageDialog(null, "Refresh!");
                    second=second+1;
                    System.out.println(second);
                    if(second==x)
                    {
                        //controller.loadFull();
                        second=0;
                        setForeColor();
                        wait.cancel();
                        wait.purge();
                    }
                }      
            };
           wait.scheduleAtFixedRate(task, 1000, x*1000);
    }
    private void setForeColor()
    {
        Color defColor=new Color(41,55,72);
        dashboardTitle.setForeground(defColor);
        foodTitle.setForeground(defColor);
        categoryTitle.setForeground(defColor);
        tableTitle.setForeground(defColor);
        staffTitle.setForeground(defColor);
        billTitle.setForeground(defColor);
        logoutTitle.setForeground(defColor);
        profileTitle.setForeground(defColor);
    }
}
