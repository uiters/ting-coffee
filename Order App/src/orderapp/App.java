package orderapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;



/**
 *
 * @author ndc07
 */
public class App {
    
    private JFrame mainFrame;
    
    public App() {
        initComponents();
    }
    
    public static void main(String[] args) {
        new App();
    }
    
    private void initComponents() {
        
        mainFrame = new JFrame("Cafe Management || Order App");
        mainFrame.setSize(1070, 700);
        
        /* HEADER */
        JPanel header = new JPanel();
        createHeader(header);
        
        /* MENU */
        JPanel menuSection = new JPanel();
        createMenu(menuSection);
        
        /* BILL */
        JPanel billSection = new JPanel();
        createBill(billSection);
        
        /* FOOTER */
        JPanel footer = new JPanel();
        createFooter(footer);  
        
        
        mainFrame.getContentPane().add(header, BorderLayout.PAGE_START);
        mainFrame.getContentPane().add(menuSection, BorderLayout.CENTER);
        mainFrame.getContentPane().add(billSection, BorderLayout.LINE_END);
        mainFrame.getContentPane().add(footer, BorderLayout.PAGE_END);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        
    }
    
    private void createHeader(JPanel header) {
        
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBackground(new Color(228,249,245));
        
        /* BRAND */
        JPanel brandSection = new JPanel();
        brandSection.setBackground(new Color(228,249,245));
        
        JLabel brandImage = new JLabel();
        URL imgURL = getClass().getResource("../images/logo.png");
        brandImage.setIcon(new ImageIcon(imgURL));
        brandImage.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                brandImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                brandImage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel brandText = new JLabel("Starbucks – The Best Coffee and Espresso Drinks");
        brandText.setForeground(new Color(0,107,68));
        brandText.setBackground(new Color(228,249,245));
        brandText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
        JSeparator brandLine = new JSeparator();
        brandLine.setBackground(new Color(0,107,68));
        
        JPanel brandTitle = new JPanel();
        brandTitle.setBackground(new Color(228,249,245));
        brandTitle.setLayout(new BoxLayout(brandTitle, BoxLayout.Y_AXIS));
        brandTitle.add(brandText);
        brandTitle.add(brandLine);
        brandTitle.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                brandTitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                brandTitle.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        brandSection.add(Box.createRigidArea(new Dimension(10, 0)));
        brandSection.add(brandImage);
        brandSection.add(Box.createRigidArea(new Dimension(15, 0)));
        brandSection.add(brandTitle);
        brandSection.add(Box.createRigidArea(new Dimension(10, 0)));
        /* END BRAND */
        
        /*CONTROLS*/
        JPanel controlSection = new JPanel();
        controlSection.setBackground(new Color(228,249,245));
        controlSection.setLayout(new BoxLayout(controlSection, BoxLayout.X_AXIS));
        
        /* HOME CONTROL */
        JPanel homeControl = new JPanel();
        homeControl.setLayout(new BoxLayout(homeControl, BoxLayout.Y_AXIS));
        homeControl.setBackground(new Color(228,249,245));
        homeControl.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeControl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                homeControl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel homeTitle = new JLabel("Home");
        homeTitle.setForeground(new Color(41,55,72));
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        homeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL homeURL = getClass().getResource("../images/home.png");
        JLabel homeIcon = new JLabel(new ImageIcon(homeURL));
        homeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        homeControl.add(homeTitle);
        homeControl.add(Box.createRigidArea(new Dimension(0, 6)));
        homeControl.add(homeIcon);
        /* END HOME CONTROL */
        
        /* HISTORY CONTROL */
        JPanel historyControl = new JPanel();
        historyControl.setLayout(new BoxLayout(historyControl, BoxLayout.Y_AXIS));
        historyControl.setBackground(new Color(228,249,245));
        historyControl.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                historyControl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                historyControl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel historyTitle = new JLabel("History");
        historyTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        historyTitle.setForeground(new Color(41,55,72));
        historyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL historyURL = getClass().getResource("../images/history.png");
        JLabel historyIcon = new JLabel(new ImageIcon(historyURL));
        historyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        historyControl.add(historyTitle);
        historyControl.add(Box.createRigidArea(new Dimension(0, 6)));
        historyControl.add(historyIcon);
        /* END HISTORY CONTROL */
        
        /* HELP CONTROL */
        JPanel helpControl = new JPanel();
        helpControl.setLayout(new BoxLayout(helpControl, BoxLayout.Y_AXIS));
        helpControl.setBackground(new Color(228,249,245));
        helpControl.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                helpControl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                helpControl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel helpTitle = new JLabel("Help");
        helpTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        helpTitle.setForeground(new Color(41,55,72));
        helpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL helpURL = getClass().getResource("../images/help.png");
        JLabel helpIcon = new JLabel(new ImageIcon(helpURL));
        helpIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        helpControl.add(helpTitle);
        helpControl.add(Box.createRigidArea(new Dimension(0, 6)));
        helpControl.add(helpIcon);
        /* END HELP CONTROL */
        
        /* ABOUT CONTROL */
        JPanel aboutControl = new JPanel();
        aboutControl.setLayout(new BoxLayout(aboutControl, BoxLayout.Y_AXIS));
        aboutControl.setBackground(new Color(228,249,245));
        aboutControl.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aboutControl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                aboutControl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel aboutTitle = new JLabel("About");
        aboutTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        aboutTitle.setForeground(new Color(41,55,72));
        aboutTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        URL aboutURL = getClass().getResource("../images/about.png");
        JLabel aboutIcon = new JLabel(new ImageIcon(aboutURL));
        aboutIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        aboutControl.add(aboutTitle);
        aboutControl.add(Box.createRigidArea(new Dimension(0, 6)));
        aboutControl.add(aboutIcon);
        /* END ABOUT CONTROL */
        
        controlSection.add(Box.createRigidArea(new Dimension(30, 0)));
        controlSection.add(homeControl);
        controlSection.add(Box.createRigidArea(new Dimension(30, 0)));
        controlSection.add(historyControl);
        controlSection.add(Box.createRigidArea(new Dimension(30, 0)));
        controlSection.add(helpControl);
        controlSection.add(Box.createRigidArea(new Dimension(30, 0)));
        controlSection.add(aboutControl);
        controlSection.add(Box.createRigidArea(new Dimension(30, 0)));
        
        /* END CONTROLS */
        
        header.add(brandSection);
        header.add(controlSection);
        
    }
    
    private JPanel createFoodItem() {
        /* FOOD */
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        JPanel food = new JPanel();
        food.setLayout(new BoxLayout(food, BoxLayout.Y_AXIS));
        
        /* NAME */
        //////////////////////////////////////////////////////
        JLabel name = new JLabel("Food 1");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font("SansSerif", Font.BOLD, 16));
        name.setForeground(new Color(0,107,68));
        //////////////////////////////////////////////////////
        /* END NAME */
        
        /* PRICE */
        //////////////////////////////////////////////////////
        JLabel price = new JLabel("$100");
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        price.setFont(new Font("SansSerif", Font.PLAIN, 12));
        price.setForeground(new Color(0,107,68));
        //////////////////////////////////////////////////////
        /* END PRICE */
        
        /* CENTER */
        //////////////////////////////////////////////////////
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
        
        /* IMAGE */
        URL imgURL = getClass().getResource("../images/menu10.png");
        JLabel img = new JLabel(new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));        
        /* END IMAGE */
        
        /* CONTROLS */
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        
        
        URL upURL = getClass().getResource("../images/up.png");
        JLabel btnUp = new JLabel(new ImageIcon(upURL));
        btnUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUp.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                btnUp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        URL downURL = getClass().getResource("../images/down.png");
        JLabel btnDown = new JLabel(new ImageIcon(downURL));
        btnDown.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDown.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07/cafe-management").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnDown.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                btnDown.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        JLabel quantity = new JLabel("2");
        quantity.setFont(new Font("SansSerif", Font.PLAIN, 12));
        quantity.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantity.setForeground(new Color(0,107,68));
        
        controls.add(btnUp);
        controls.add(quantity);
        controls.add(btnDown);
        /* END CONTROLS */
        
        center.add(Box.createRigidArea(new Dimension(10, 0)));
        center.add(img);
        center.add(Box.createRigidArea(new Dimension(12, 0)));
        center.add(controls);
        center.add(Box.createRigidArea(new Dimension(10, 0)));
        //////////////////////////////////////////////////////
        /* END CENTER */
        
        
        food.add(Box.createRigidArea(new Dimension(0, 10)));        
        food.add(price);
        food.add(Box.createRigidArea(new Dimension(0, 10)));        
        food.add(center);
        food.add(Box.createRigidArea(new Dimension(0, 10)));        
        food.add(name);
        food.add(Box.createRigidArea(new Dimension(0, 10)));        
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        /* END FOOD */
        
        return food;
    }
    
    private void createMenu(JPanel menu) {
        
        menu.setBackground(new Color(112,99,129));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        
        /* CONTROLS */
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        controls.setBackground(new Color(112,99,129));
        
        /* Search Control */
        JPanel searchControl = new JPanel();
        searchControl.setLayout(new BoxLayout(searchControl, BoxLayout.X_AXIS));
        
        TextField txbSearch = new TextField();
        txbSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txbSearch.setForeground(new Color(41,55,72));
        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSearch.setForeground(new Color(41,55,72));
        
        searchControl.add(txbSearch);
        searchControl.add(btnSearch);
        /* Filter Control */
        JPanel filterControl = new JPanel();
        filterControl.setLayout(new BoxLayout(filterControl, BoxLayout.X_AXIS));
        
        String cate[] = {"All", "Cate 1", "Cate 2", "Cate 3"};
        JComboBox cbCategories = new JComboBox(cate);
        cbCategories.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbCategories.setForeground(new Color(41,55,72));
        
        filterControl.add(cbCategories);
        /* Add controls */
        controls.add(Box.createRigidArea(new Dimension(5, 0)));
        controls.add(searchControl);
        controls.add(Box.createRigidArea(new Dimension(30, 0)));
        controls.add(filterControl);
        /* END CONTROLS */
        
        /* LIST FOODS */
        JPanel listFoods = new JPanel();
        listFoods.setBackground(new Color(112,99,129));
        listFoods.setLayout(new WrapLayout(WrapLayout.LEFT));
        for (int i = 0; i < 20; i++) {
            listFoods.add(createFoodItem());
        }
        /* END LIST FOODS */
        
        /* JScrollPane */
        JScrollPane scrollPane = new JScrollPane(listFoods);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBorder(null);
        
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(controls);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        menu.add(scrollPane);
        
    }
        
    private void createBill(JPanel bill) {
        
        bill.setBackground(new Color(112,99,129));
        bill.setLayout(new BoxLayout(bill, BoxLayout.Y_AXIS));
        
        /* Show Bill */
        JPanel showBill = new JPanel();
        
        
        URL imgURL = getClass().getResource("../images/bill.png");
        JLabel img = new JLabel(new ImageIcon(imgURL));
        
        showBill.add(img);
        /* End Bill */
        
        /* Controls */
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        
            /* Info */
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        
        JPanel subTotal = new JPanel();
        subTotal.setLayout(new BoxLayout(subTotal, BoxLayout.X_AXIS));
        JLabel lblSub = new JLabel("Sub Total:");
        lblSub.setPreferredSize(new Dimension(80, lblSub.getMinimumSize().height));
        lblSub.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblSub.setForeground(new Color(41,55,72));
        JTextField txbSub = new JTextField();
        txbSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txbSub.setForeground(new Color(41,55,72));
        txbSub.setMaximumSize(new Dimension(Integer.MAX_VALUE, (new JButton("xx")).getMinimumSize().height));
        subTotal.add(lblSub);
        subTotal.add(txbSub);
        
        JPanel total = new JPanel();
        total.setLayout(new BoxLayout(total, BoxLayout.X_AXIS));
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setPreferredSize(new Dimension(80, txbSub.getMinimumSize().height));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTotal.setForeground(new Color(41,55,72));
        JTextField txbTotal = new JTextField();
        txbTotal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txbTotal.setForeground(new Color(41,55,72));
        txbTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, (new JButton("xx")).getMinimumSize().height));
        total.add(lblTotal);
        total.add(txbTotal);
        
        JPanel discount = new JPanel();
        discount.setLayout(new BoxLayout(discount, BoxLayout.X_AXIS));
        JLabel lblDiscount =new JLabel("Discount:");
        lblDiscount.setPreferredSize(new Dimension(80, lblSub.getMinimumSize().height));
        lblDiscount.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblDiscount.setForeground(new Color(41,55,72));
        JTextField txbDiscount = new JTextField();
        txbDiscount.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txbDiscount.setForeground(new Color(41,55,72));
        txbDiscount.setMaximumSize(new Dimension(Integer.MAX_VALUE, (new JButton("xx")).getMinimumSize().height));
        discount.add(lblDiscount);
        discount.add(txbDiscount);
        
        info.add(subTotal);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(discount);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(total);
            /* End Info */
        
            /* Group Button */
        JPanel groupButton = new JPanel();
        groupButton.setLayout(new BoxLayout(groupButton, BoxLayout.Y_AXIS));
        
        JButton btnPay = new JButton("Pay");
        btnPay.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPay.setForeground(new Color(41,55,72));
        btnPay.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnPay.getMinimumSize().height));
        
        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnReset.setForeground(new Color(41,55,72));
        btnReset.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnPay.getMinimumSize().height));
        
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnExit.setForeground(new Color(41,55,72));
        btnExit.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnPay.getMinimumSize().height));
        
        groupButton.add(btnPay);
        groupButton.add(Box.createRigidArea(new Dimension(0, 10)));
        groupButton.add(btnReset);
        groupButton.add(Box.createRigidArea(new Dimension(0, 10)));
        groupButton.add(btnExit);
            /* End Group Button */
        
        controls.add(Box.createRigidArea(new Dimension(10, 0)));
        controls.add(info);
        controls.add(Box.createRigidArea(new Dimension(20, 0)));
        controls.add(groupButton);
        controls.add(Box.createRigidArea(new Dimension(10, 0)));
        /* End Controls */
        
        bill.add(showBill);
        bill.add(controls);
        
    }
    
    private void createFooter(JPanel footer) {
        footer.setBackground(new Color(228,249,245));
        
        JLabel copyright = new JLabel("© 2018 Copyright:");
        copyright.setFont(new Font("SansSerif", Font.PLAIN, 12));
        copyright.setForeground(new Color(41,55,72));
        JLabel author = new JLabel("ndc07");
        author.setFont(new Font("SansSerif", Font.PLAIN, 12));
        author.setForeground(new Color(0,107,68));
        author.addMouseListener(new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ndc07").toURI());
                } catch (IOException ex) {} catch (URISyntaxException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                author.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); 
                author.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }); 
        
        footer.add(copyright);
        footer.add(author);
    }
}
