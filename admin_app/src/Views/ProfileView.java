/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Constants.Password;
import Controllers.PasswordController;
import Models.AccountModel;
import Models.AccountModel.Account;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Thang Le
 */
public class ProfileView extends View {
    private JDialog jf; // xài dialog vì để hold main excution (sau khi click add,frame gọi tới sẽ tự reload database)
    private JPanel panel;
    private JTextField user;
    private JPasswordField curText;
    private JPasswordField newText;
    JButton change;
    String username="";
    PasswordController controller;
    public ProfileView()
    {
        InitComponent();
        controller=PasswordController.getInstance(this);
    }
    
    public ProfileView(String a)
    {
        username=a;
        InitComponent();
        controller=PasswordController.getInstance(this);
    }
    
    public void Load()
    {
        
        jf.setVisible(true);
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

    }
    //----------------------------------------------------------------------------------------------------------
    
    private void InitComponent()
    {
        jf=new JDialog(jf,"Profile");
        jf.setModal(true);
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        //jf.add(panel, BorderLayout.CENTER);
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        /*PROFILE*/
       JPanel Profilegroup=new JPanel();
        Profilegroup.setLayout(new BoxLayout(Profilegroup,BoxLayout.X_AXIS));
        Profilegroup.setBackground(new Color(209, 228, 252));
        Profilegroup.setMaximumSize(new Dimension(350, 30));
        
        
         user=new JTextField();
         user.setEditable(false);
         user.setText(username);
         user.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel userLabel=new JLabel("Username : ");
         userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Profilegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Profilegroup.add(userLabel);
        Profilegroup.add(Box.createRigidArea(new Dimension(73,0)));
        Profilegroup.add(user);
        Profilegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END PROFILE*/
       
        
        /*CURRENT PASS*/
        JPanel Namegroup=new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(new Color(209, 228, 252));
        Namegroup.setMaximumSize(new Dimension(350, 30));
        
        
         curText=new JPasswordField();
         curText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel nameLabel=new JLabel("Current password : ");
         nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Namegroup.add(nameLabel);
        Namegroup.add(Box.createRigidArea(new Dimension(30,0)));
        Namegroup.add(curText);
        Namegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        
        /*END CURRENT PASS*/
        
        /*NEW PASS*/
        JPanel Newgroup=new JPanel();
        Newgroup.setLayout(new BoxLayout(Newgroup,BoxLayout.X_AXIS));
        Newgroup.setBackground(new Color(209, 228, 252));
        Newgroup.setMaximumSize(new Dimension(350, 30));
        
         newText=new JPasswordField();
         newText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel newLabel=new JLabel("New password : ");
         newLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Newgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Newgroup.add(newLabel);
        Newgroup.add(Box.createRigidArea(new Dimension(48,0)));
        Newgroup.add(newText);
        Newgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END NEW PASS*/
        /*end info detail*/
         /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        
        JButton btnAdd=new JButton("Change password");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setForeground(new Color(0,107,68));
        
        Btngroup.add(Box.createRigidArea(new Dimension(85,0)));
        Btngroup.add(btnAdd);

        
        /*end Button Add,Cancel*/
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Profilegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Newgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup);
        
        panel.add(detail);
        
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getText().toString().equals("")==false&&curText.getText().toString().equals("")==false&&
                        newText.getText().toString().equals("")==false)
                {
                    
                    controller.UpdatePassword(user.getText().toString());

                }
            }
        });
        
     
        
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(false);
    }
    
    public void checkPassword(Object objects)
    {
         List<AccountModel.Account> result=(List<AccountModel.Account>)(Object)objects;
        if(result.get(0).username.equals(user.getText())==true&&Password.checkPassword(curText.getText(), result.get(0).pass)==true)
        {
            //JOptionPane.showMessageDialog(null, newText.getText());
            String pass=Password.hashPassword(newText.getText());
            Account item= AccountModel.getInstance().new Account(user.getText(), pass);
            controller.update(item);
        }        
        else JOptionPane.showMessageDialog(null, "Username or password is not correct!");
    }
    
 
}
