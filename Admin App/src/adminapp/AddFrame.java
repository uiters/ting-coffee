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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 *
 * @author Thang Le
 */
public class AddFrame {
    private String title;
    private JFrame jf;
    private JPanel panel;
    private JTextField nameText;
    private JTextField priceText;
    private JComboBox cb;
    private String []list={"An vat","Mon chinh","Mon trang mieng"} ; //danh sách trong category
    public AddFrame(String title)
    {
       this.title=title;
        
    }
    
    public void FoodAdd()
    {
        jf=new JFrame(title);
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        jf.add(panel, BorderLayout.CENTER);
        jf.setVisible(true);
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
                if(nameText.getText().toString()!=null&&priceText.getText().toString()!=null&&cb.getSelectedItem()!=null)
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
    }
<<<<<<< HEAD
=======
    
    
    public void FoodCategoryAdd()
    {
        jf=new JFrame(title);
        jf.setSize(300, 200);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setBackground(Color.green);
        jf.add(panel, BorderLayout.CENTER);
        jf.setVisible(true);
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
                if(nameText.getText().toString().equals("")==false)
                {
                    //Xu ly btn add
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
        
    }
>>>>>>> origin/admin_app
}
