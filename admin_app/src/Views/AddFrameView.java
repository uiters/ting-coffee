/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.Controller;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final String [] list = {"An vat","Mon chinh","Mon trang mieng"} ; //danh sách trong category
    public AddFrameView(String title, Controller c)
    {
       this.title=title;
       controller = c;
    }
    
    public void FoodAdd()
    {
        jf=new JDialog(jf, title);
        jf.setModal(true); // hold main excution
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        
        panel=new JPanel();
        panel.setBackground(Color.green);
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(Color.yellow);
        detail.setPreferredSize(new Dimension(250,300));
        
        
        JPanel Namegroup=new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(Color.yellow);
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
        Categorygroup.setBackground(Color.yellow);
        Categorygroup.setMaximumSize(new Dimension(300, 30));
        
        
        cb=new JComboBox(list);
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
                choose.setCurrentDirectory(null);
                FileNameExtensionFilter filter=new FileNameExtensionFilter("*.Images","jpg","gif","png");
                choose.setFileFilter(filter);
                int returnVal=choose.showOpenDialog(null);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    // xu ly chon anh
                    
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
        
         priceText=new JTextField();
         priceText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel priceLabel=new JLabel("Price : ");
         priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        Pricegroup.add(priceLabel);
        Pricegroup.add(Box.createRigidArea(new Dimension(50,0)));
        Pricegroup.add(priceText);
        Pricegroup.add(Box.createRigidArea(new Dimension(5,0)));
        /*End Price*/
        
        
         /*Button Add,Cancel*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(Color.yellow);
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnAdd=new JButton("Add");
        btnChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
        /*end Button Add,Cacnel*/
        
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Categorygroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Pricegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Choosegroup);
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
                    JOptionPane.showMessageDialog(null, "Đã thêm 1 food thành công!");
                    jf.dispose();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 JOptionPane.showMessageDialog(null, "Close frame add food");
                  jf.dispose();
            }
        });
        
        
        jf.add(detail, BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    
    public void FoodCategoryAdd()
    {
        jf=new JDialog(jf, title);
        jf.setModal(true);
        jf.setSize(300, 200);
    
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        //jf.add(panel, BorderLayout.CENTER);
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(Color.yellow);
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        
        JPanel Namegroup=new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(Color.yellow);
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
        Btngroup.setBackground(Color.yellow);
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
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
                    JOptionPane.showMessageDialog(null, "Đã thêm 1 food category thành công!");
                    jf.dispose();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 JOptionPane.showMessageDialog(null, "Close frame add food cagetory");
                  jf.dispose();
            }
        });
        
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(true);
        
    }
    
    
    public void TableAdd()
    {
        jf=new JDialog(jf,title);
        jf.setModal(true);
        jf.setSize(300, 200);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        //jf.add(panel, BorderLayout.CENTER);
        
         /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(Color.yellow);
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        
        JPanel Namegroup=new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup,BoxLayout.X_AXIS));
        Namegroup.setBackground(Color.yellow);
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
        Btngroup.setBackground(Color.yellow);
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup.add(Box.createRigidArea(new Dimension(25,0)));
        Btngroup.add(btnCancel);
        Btngroup.add(Box.createRigidArea(new Dimension(115,0)));
        Btngroup.add(btnAdd);
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
                    JOptionPane.showMessageDialog(null, "Đã thêm 1 table thành công!");
                    jf.dispose();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 JOptionPane.showMessageDialog(null, "Close frame add food cagetory");
                  jf.dispose();
            }
        });
        
        jf.getContentPane().add(detail,BorderLayout.CENTER);
        jf.setVisible(true);
    }
    
    public void StaffAdd()
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
        detail.setBackground(Color.yellow);
        detail.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(Color.yellow);
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
        Namegroup.setBackground(Color.yellow);
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
        
        /*Birthday*/
        JPanel Birthgroup=new JPanel();
        Birthgroup.setLayout(new BoxLayout(Birthgroup,BoxLayout.X_AXIS));
        Birthgroup.setBackground(Color.yellow);
        Birthgroup.setMaximumSize(new Dimension(300, 30));
        
        JDateChooser birthday=new JDateChooser();
        birthday.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthday.setDateFormatString("yyyy--MM-dd");
        
         birthText=new JTextField();
         birthText.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        Sexgroup.setBackground(Color.yellow);
        Sexgroup.setMaximumSize(new Dimension(300, 30));
        
        String []list={"Male","Female"};
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
        Addressgroup.setBackground(Color.yellow);
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
        Phonegroup.setBackground(Color.yellow);
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
        /*end Phonenumber*/
        
        /*Account Type*/
        JPanel Typegroup=new JPanel();
        Typegroup.setLayout(new BoxLayout(Typegroup,BoxLayout.X_AXIS));
        Typegroup.setBackground(Color.yellow);
        Typegroup.setMaximumSize(new Dimension(300, 30));
        
        JComboBox cbType=new JComboBox();
        cbType.addItem("Name");            
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
        Btngroup.setBackground(Color.yellow);
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup.add(Box.createRigidArea(new Dimension(15,0)));
        Btngroup.add(btnCancel);
        Btngroup.add(Box.createRigidArea(new Dimension(135,0)));
        Btngroup.add(btnAdd);
        Btngroup.add(Box.createRigidArea(new Dimension(15,0)));
        /*end Button Add,Cancel*/
        
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Namegroup);
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
                if(nameText.getText().equals("")==false&birthText.getText().equals("")==false
                        &&addressText.getText().equals("")==false&&phoneText.getText().equals("")==false
                        &&cb.getSelectedItem()!=null);
                {
                    //Xu ly btn add
                    JOptionPane.showMessageDialog(null, "Đã thêm 1 staff thành công!");
                    jf.dispose();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //Xu ly btn cancel
                 JOptionPane.showMessageDialog(null, "Close frame add staff");
                  jf.dispose();
            }
        });
        
        jf.add(detail, BorderLayout.CENTER);
        jf.setVisible(true);
    }
}
