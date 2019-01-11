/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Constants.Password;
import Controllers.Controller;
import Models.AccountModel;
import Models.AccountModel.Account;
import Models.FoodModel;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Thang Le
 */
public class AddFrameView {
    private final String title;
    private JDialog jf; // xài dialog vì để hold main excution (sau khi click add,frame gọi tới sẽ tự reload database)
    private JPanel panel;
    private JTextField nameText;
    private JTextField birthText;
    private JTextField addressText;
    private JTextField phoneText;
    private JTextField priceText;
    private final Controller controller;
    private JComboBox cb;
    private JComboBox cbType;
    //private final String [] list = {"An vat","Mon chinh","Mon trang mieng"} ; //danh sách trong category
    public AddFrameView(String title, Controller c)
    {
       this.title=title;
       controller = c;
    }
    
    /*FOOD*/
    public void FoodAdd(JComboBox a)
    {
        jf=new JDialog(jf, title);
        jf.setModal(true); // hold main excution
        jf.setSize(300, 250);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        
        panel=new JPanel();
        panel.setBackground(Color.green);
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        detail.setPreferredSize(new Dimension(250,300));
        
        
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
        
        /*Category*/
        JPanel Categorygroup=new JPanel();
        Categorygroup.setLayout(new BoxLayout(Categorygroup,BoxLayout.X_AXIS));
        Categorygroup.setBackground(new Color(209, 228, 252));
        Categorygroup.setMaximumSize(new Dimension(300, 30));
        
        
        cb=new JComboBox();
        cb=a;
        cb.setSelectedItem(null);
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel LabelCategory=new JLabel("Category : ");
        LabelCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Categorygroup.add(Box.createRigidArea(new Dimension(5,0)));
        Categorygroup.add(LabelCategory);
        Categorygroup.add(Box.createRigidArea(new Dimension(30,0)));
        Categorygroup.add(cb);
        Categorygroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*End Category*/
          
        /*Price*/
        JPanel Pricegroup=new JPanel();
        Pricegroup.setLayout(new BoxLayout(Pricegroup,BoxLayout.X_AXIS));
        Pricegroup.setBackground(new Color(209, 228, 252));
        Pricegroup.setMaximumSize(new Dimension(300, 30));
        
         priceText=new JTextField();
         priceText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel priceLabel=new JLabel("Price : ");
         priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Pricegroup.add(priceLabel);
        Pricegroup.add(Box.createRigidArea(new Dimension(50,0)));
        Pricegroup.add(priceText);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        priceText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                    (c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) 
                    {
                        e.consume();
                    }
            }
});
        /*End Price*/
        
        
         /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.setForeground(new Color(0,107,68));
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setForeground(new Color(0,107,68));
        
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
        
        /*end Button Add,Cacnel*/
        
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Categorygroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Pricegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup);
        
        
        /*end info detail*/

        panel.add(detail);
        
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText()!=null && priceText.getText()!=null && cb.getSelectedItem()!=null)
                {
                    //Xu ly btn add
                    String namecategory=cb.getSelectedItem().toString();
                    URL default_url = getClass().getResource("../image/food_default.png");
                    File file=new File(default_url.getPath());
                    String encoded="";
                    try
                    {
                        encoded = Base64.encodeBase64String(getByte(file));
                    }
                    catch (IOException ex)
                    {
                        
                    }
                    FoodModel.Food food=FoodModel.getInstance().new Food( nameText.getText() ,namecategory,Double.parseDouble(priceText.getText()),encoded );
                    controller.insert(food);
                    jf.dispose();
                }
                else  JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 //JOptionPane.showMessageDialog(null, "Close frame add food");
                  jf.dispose();
            }
        });
        
        
        jf.add(detail, BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    /*FOOD CATEGORY*/
    public void FoodCategoryAdd()
    {
        jf=new JDialog(jf, title);
        jf.setModal(true);
        jf.setSize(300, 150);
    
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
        
        /*end info detail*/
         /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.setForeground(new Color(0,107,68));
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setForeground(new Color(0,107,68));
        
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
       
        /*end Button Add,Cancel*/
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup);
        
        panel.add(detail);
        
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText().equals("")==false)
                {
                    //Xu ly btn add
                    controller.insert(nameText.getText());
                    //JOptionPane.showMessageDialog(null, "Đã thêm 1 food category thành công!");
                    jf.dispose();
                }
                else  JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 //JOptionPane.showMessageDialog(null, "Close frame add food cagetory");
                  jf.dispose();
            }
        });
        
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(true);
        
    }
    
    /*TABLE*/
    public void TableAdd()
    {
        jf=new JDialog(jf,title);
        jf.setModal(true);
        jf.setSize(300, 150);
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
        
        /*end info detail*/
         /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.setForeground(new Color(0,107,68));
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setForeground(new Color(0,107,68));
        
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
        
        /*end Button Add,Cancel*/
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup);
        
        panel.add(detail);
        
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText().equals("")==false)
                {
                    //Xu ly btn add
                    controller.insert(nameText.getText());
                    //JOptionPane.showMessageDialog(null, "Đã thêm 1 table thành công!");
                    jf.dispose();
                }
                else  JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 //JOptionPane.showMessageDialog(null, "Close frame add food cagetory");
                  jf.dispose();
            }
        });
        
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    /*ACCOUNT*/
    public void StaffAdd(JComboBox a)
    {
        jf=new JDialog(jf,title);
        jf.setModal(true);
        jf.setSize(300, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(new Color(209, 228, 252));
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         JTextField idText=new JTextField();
         idText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idLabel=new JLabel("Username : ");
         idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDgroup.add(idLabel);
        IDgroup.add(Box.createRigidArea(new Dimension(22,0)));
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
        /*ID Card*/
        JPanel IDCardgroup=new JPanel();
        IDCardgroup.setLayout(new BoxLayout(IDCardgroup,BoxLayout.X_AXIS));
        IDCardgroup.setBackground(new Color(209, 228, 252));
        IDCardgroup.setMaximumSize(new Dimension(300, 30));
        
         JTextField idCardText=new JTextField();
         idCardText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idcardLabel=new JLabel("ID Card : ");
         idcardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDCardgroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDCardgroup.add(idcardLabel);
        IDCardgroup.add(Box.createRigidArea(new Dimension(40,0)));
        IDCardgroup.add(idCardText);
        IDCardgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end IDCard*/
        
        /*Birthday*/
        JPanel Birthgroup=new JPanel();
        Birthgroup.setLayout(new BoxLayout(Birthgroup,BoxLayout.X_AXIS));
        Birthgroup.setBackground(new Color(209, 228, 252));
        Birthgroup.setMaximumSize(new Dimension(300, 30));
        
        JDateChooser birthday=new JDateChooser();
        birthday.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthday.setDateFormatString("yyyy--MM-dd");
        
         
         JLabel birthLabel=new JLabel("Birthday : ");
         birthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Birthgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Birthgroup.add(birthLabel);
        Birthgroup.add(Box.createRigidArea(new Dimension(33,0)));
        Birthgroup.add(birthday);
        Birthgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end Birthday*/
        
        /*Sex*/
        JPanel Sexgroup=new JPanel();
        Sexgroup.setLayout(new BoxLayout(Sexgroup,BoxLayout.X_AXIS));
        Sexgroup.setBackground(new Color(209, 228, 252));
        Sexgroup.setMaximumSize(new Dimension(300, 30));
        
        String []list={"Female","Male"};
        cb=new JComboBox(list);
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setSelectedItem(list[0]);
        
        JLabel sexLabel=new JLabel("Sex : ");
        sexLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Sexgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Sexgroup.add(sexLabel);
        Sexgroup.add(Box.createRigidArea(new Dimension(58,0)));
        Sexgroup.add(cb);
        Sexgroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*end Sex*/
        
        /*Address*/
        JPanel Addressgroup=new JPanel();
        Addressgroup.setLayout(new BoxLayout(Addressgroup,BoxLayout.X_AXIS));
        Addressgroup.setBackground(new Color(209, 228, 252));
        Addressgroup.setMaximumSize(new Dimension(300, 30));
        
         addressText=new JTextField();
         addressText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel addressLabel=new JLabel("Address : ");
         addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Addressgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Addressgroup.add(addressLabel);
        Addressgroup.add(Box.createRigidArea(new Dimension(32,0)));
        Addressgroup.add(addressText);
        Addressgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end Address*/
        
        /*Phonenumber*/
        JPanel Phonegroup=new JPanel();
        Phonegroup.setLayout(new BoxLayout(Phonegroup,BoxLayout.X_AXIS));
        Phonegroup.setBackground(new Color(209, 228, 252));
        Phonegroup.setMaximumSize(new Dimension(300, 30));
        
         phoneText=new JTextField();
         phoneText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel phoneLabel=new JLabel("Phone : ");
         phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Phonegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Phonegroup.add(phoneLabel);
        Phonegroup.add(Box.createRigidArea(new Dimension(43,0)));
        Phonegroup.add(phoneText);
        Phonegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        phoneText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                    (c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) 
                    {
                        e.consume();
                    }
            }
});
        /*end Phonenumber*/
        
        /*Account Type*/
        JPanel Typegroup=new JPanel();
        Typegroup.setLayout(new BoxLayout(Typegroup,BoxLayout.X_AXIS));
        Typegroup.setBackground(new Color(209, 228, 252));
        Typegroup.setMaximumSize(new Dimension(300, 30));
        
        cbType=new JComboBox();
        /*for(String obj : list)
        {
            cbType.addItem(obj);    
        }*/
        cbType=a; 
        cbType.setAlignmentX(Component.CENTER_ALIGNMENT);
        cbType.setSelectedItem(null);

        
        
        JLabel typeLabel=new JLabel("Account Type : ");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Typegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Typegroup.add(typeLabel);
        Typegroup.add(Box.createRigidArea(new Dimension(3,0)));
        Typegroup.add(cbType);
        Typegroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*end Type*/
        
        /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.setForeground(new Color(0,107,68));
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setForeground(new Color(0,107,68));
        
        Btngroup.add(Box.createRigidArea(new Dimension(15,0)));
        Btngroup.add(btnAdd);
        Btngroup.add(Box.createRigidArea(new Dimension(135,0)));
        Btngroup.add(btnCancel);
        Btngroup.add(Box.createRigidArea(new Dimension(15,0)));
        /*end Button Add,Cancel*/
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(IDCardgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Birthgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Sexgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Addressgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Phonegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Typegroup);     
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup);
        
        /*end Staff info detail*/
        panel.add(detail);
        
        
        /*Event Btn Add,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText().equals("")==false&birthday.getDate()!=null
                        &&addressText.getText().equals("")==false&&phoneText.getText().equals("")==false
                        &&cb.getSelectedItem()!=null)
                {
                    int index=cb.getSelectedIndex();
                    int type=cbType.getSelectedIndex();
                    String typename=cbType.getSelectedItem().toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate =dateFormat.format(birthday.getDate());
                    String pass=Password.hashPassword(phoneText.getText());
                    //Xu ly btn add
                    Account acc=AccountModel.getInstance().new Account(idText.getText(),nameText.getText(),idCardText.getText(),strDate,
                            index,addressText.getText(),phoneText.getText(),typename,pass);
                    controller.insert(acc);
                    jf.dispose();
                }
               else  JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
            }
             
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 //JOptionPane.showMessageDialog(null, "Close frame add staff");
                  jf.dispose();
            }
        });
        
        jf.add(detail, BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    
    public byte[] getByte(File file) throws IOException
    {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
}
