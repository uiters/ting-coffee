/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Constants.Password;
import Controllers.LoginController;
import Models.AccountModel;
import Models.AccountModel.Account;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Views.AdminappView;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author Thang Le
 */
public class LoginView extends View {
    private AdminappView app;
    private JFrame jf;
    private JTextField nameText;
    private JPasswordField passText;
    private boolean check=false;
    private LoginController controller;
    
    LoginView()
    {
        jf=new JFrame("Login | Admin App");
        jf.setSize(550, 450);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // đóng frame 
        
        jf.setResizable(false);
        
        controller=LoginController.getInstance(this);

        jf.setLocationRelativeTo(null);
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        
        
        /*LOGO*/
        JPanel brandSection = new JPanel();
        brandSection.setBackground(new Color(255,255,208));
        
        JLabel brandImage = new JLabel();
        URL imgURL = getClass().getResource("../image/logo1.png");
        brandImage.setIcon(new ImageIcon(imgURL));
        
        JLabel brandText = new JLabel("Starbucks – "
                + "The Best Coffee and Espresso Drinks");
    
        brandText.setForeground(new Color(100,40,251));
        brandText.setBackground(new Color(228,249,245));
      
        brandText.setFont(new Font("SansSerif", Font.PLAIN, 20));
  
        
        brandSection.add(Box.createRigidArea(new Dimension(5, 0)));
        brandSection.add(brandImage);
        brandSection.add(Box.createRigidArea(new Dimension(15, 0)));
        brandSection.add(brandText);
        brandSection.add(Box.createRigidArea(new Dimension(10, 0)));

        /*END LOGO */
        
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(228,249,255));
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        /*USERNAME*/
        JPanel Namegroup=new JPanel();
          Namegroup.setBorder(
            BorderFactory.createEmptyBorder());
        Namegroup.setLayout(new BorderLayout());
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(new Color(228,249,245));
        Namegroup.setMaximumSize(new Dimension(340, 55));
         nameText=new JTextField();
         nameText.setBackground(new Color(228,249,245));
         nameText.setBorder(
            BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, (new Color(0,107,68))
                    , (new Color(0,107,68))), "Username"));
   
        Namegroup.add(Box.createRigidArea(new Dimension(30,0)));
        Namegroup.add(nameText);
        Namegroup.add(Box.createRigidArea(new Dimension(0,0))); 
        /*END USERNAME*/
        
        
        /*PASSWORD*/
         JPanel Passgroup=new JPanel();
        Passgroup.setBorder(
        BorderFactory.createEmptyBorder());
        Passgroup.setLayout(new BorderLayout());
        Passgroup.setLayout(new BoxLayout(Passgroup,BoxLayout.X_AXIS));
        Passgroup.setBackground(new Color(228,249,245));
        Passgroup.setMaximumSize(new Dimension(340, 55));
        
         passText=new JPasswordField();
         passText.add(Box.createRigidArea(new Dimension(400, 20)));
         passText.setBackground(new Color(228,249,245));
         passText.setAlignmentX(Component.CENTER_ALIGNMENT);
         passText.setBorder(
            BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, (new Color(0,107,68))
                    , (new Color(0,107,68))), "Password"));
       
        Passgroup.add(Box.createRigidArea(new Dimension(30,0)));
        Passgroup.add(passText);
        Passgroup.add(Box.createRigidArea(new Dimension(0,0))); 
        /*END PASSWORD*/
        
        /*end info detail*/
         /*Button Add,Cancel*/
        JPanel Btngroup2=new JPanel();
        Btngroup2.setLayout(new BoxLayout(Btngroup2,BoxLayout.X_AXIS));
        Btngroup2.setBackground(new Color(228,249,245));
        JButton btnAdd=new JButton("Login");
        btnAdd.setForeground(new Color(255,255,255));
    
        btnAdd.add(Box.createRigidArea(new Dimension(250, 20)));
        btnAdd.setBackground(new Color(255,82,82));
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel Btngroup1=new JPanel();
        Btngroup1.setLayout(new BoxLayout(Btngroup1,BoxLayout.X_AXIS));
        Btngroup1.setBackground(new Color(228,249,245));
        
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setForeground(new Color(255,255,255));
        btnCancel.add(Box.createRigidArea(new Dimension(250, 20)));
        btnCancel.setBackground(new Color(255,82,82));
     
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup2.add(Box.createRigidArea(new Dimension(30,10)));
        Btngroup2.add(btnAdd);
        
        Btngroup1.add(Box.createRigidArea(new Dimension(30,20)));
        Btngroup1.add(btnCancel);
       
        /*end Button Add,Cancel*/
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Passgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup2);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup1);
     
        panel.add(brandSection);
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(detail);
        
        
        
        
        nameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                  if(nameText.getText().toString().equals("")==false && passText.getText().equals("")==false)
                {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                         Account input=AccountModel.getInstance().new Account(nameText.getText(),passText.getText());
                        //JOptionPane.showMessageDialog(null, "Info "+input.username+ " "+input.pass);
                        controller.CheckLogin(input.username);
                    }                  
                }
            }
});
        
        passText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                  if(nameText.getText().equals("")==false && passText.getText().equals("")==false)
                {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                        Account input=AccountModel.getInstance().new Account(nameText.getText(),passText.getText());
                        //JOptionPane.showMessageDialog(null, "Info "+input.username+ " "+input.pass);
                        controller.CheckLogin(input.username);
                    }                  
                }
                   
            }
});
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xử lý sự kiện check login
                if(nameText.getText().equals("")==false&&passText.getText().equals("")==false)
                {
                    //Xu ly btn login         
                    Account input=AccountModel.getInstance().new Account(nameText.getText(),passText.getText());
                    controller.CheckLogin(input.username);
                    //LoadApp();
                }

            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                  jf.dispose();
            }
        });
        
        jf.getContentPane().add(brandSection,BorderLayout.PAGE_START);
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    
    private void LoadApp(String a)
    {
        JOptionPane.showMessageDialog(null, "Login successfully!");
        app=new AdminappView(a);
        //app.setLoginView(this);
        app = null;
        nameText.setText("");
        passText.setText("");
        //jf.setVisible(false);
        jf.dispose();
        
        
    }
    
    public void Visible(boolean a)
    {
        jf.setVisible(a);
    }
    
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
    }
    
    @Override
    public void delete(int row){
        
    }
    
    @Override
    public void update(int row, Object objects){
        
    }
    
    @Override
    public void loadView(Object objects){
        List<Account> result=(List<Account>)(Object)objects;
        if(result.get(0).username.equals(nameText.getText())== true && Password.checkPassword(passText.getText(), result.get(0).pass)==true)
        {
            if(result.get(0).type==1) //account type la admin
                LoadApp(result.get(0).username);
            else JOptionPane.showMessageDialog(null, "Your account must be admin to login this app!");
        }        
        else JOptionPane.showMessageDialog(null, "Username or password is not correct!");
    }
   
}
