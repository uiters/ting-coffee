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
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    public Adminapp()
    {
        jf=new JFrame("Cafe Management || Admin App");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // đóng toàn bộ frame liên quan
        jf.setSize(1280, 800);
        
        
        
        
        //Design tab Dashboard
        tabbedPane=new JTabbedPane(); // mặc định tabbedPane sẽ luôn ở top
        JPanel panel1=new JPanel(null);
        tabbedPane.addTab("Dashboard", null, panel1, "Dashboard");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        
       
        tabbedPane.addTab("Food",  CreatePanelFood());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
       
        
        JPanel panel3=new JPanel(null);
        panel3.add(new Label("test"));
        tabbedPane.addTab("FoodCagetory", null, panel3, "FoodCagetory");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        
        
        jf.getContentPane().add(tabbedPane);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null); // set mặc định mở form ở giữa màn hình
        jf.setVisible(true);
    }
    
    
    //Food
    public JPanel CreatePanelFood()
    {
         JPanel panel2=new JPanel(null);
        
        //text search
        JTextField text=new JTextField();
        text.setSize(240, 30);
        text.setLocation(120, 20);
        text.setFont(new java.awt.Font(text.getFont().toString(), Font.PLAIN, 18));
        
        //button Search
        JButton btn=new JButton("Search");
        btn.setSize(100,30);
        btn.setLocation(380, 20);
        btn.setFont(new java.awt.Font(btn.getFont().toString(), Font.PLAIN, 18));
        
        //Label
        JLabel label=new JLabel("Select Cagetory");
        label.setSize(150,30);
        label.setLocation(800,20);
        label.setFont(new java.awt.Font(label.getFont().toString(), Font.PLAIN, 18));
         
        // List Cagetory
        String []list={"All","A","B","C"};
        JComboBox cb=new JComboBox(list);
        cb.setMaximumRowCount(5); // số hàng tối đa được hiển thị
        cb.setSize(150,30);
        cb.setLocation(960,20);
        cb.setFont(new java.awt.Font(cb.getFont().toString(), Font.PLAIN, 18));
        
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
        table.setModel(model);
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setRowHeight(60); // chỉnh độ cao của hàng
        
        JScrollPane jsp=new JScrollPane(table);
        jsp.setSize(1200,350);
        jsp.setLocation(40, 70);
        
        //Label id
        JLabel id=new JLabel("ID");
        id.setSize(150,30);
        id.setLocation(50, 430);
        id.setFont(new java.awt.Font(id.getFont().toString(), Font.PLAIN, 18));
        
        //Text ID
         JTextField textID=new JTextField();
        textID.setSize(240, 30);
        textID.setLocation(50, 470);
        textID.setEditable(false);
        textID.setBackground(Color.lightGray);
        textID.setFont(new java.awt.Font(textID.getFont().toString(), Font.PLAIN, 18));
        
        //Label name
        JLabel name=new JLabel("Name");
        name.setSize(150,30);
        name.setLocation(50, 510);
        name.setFont(new java.awt.Font(name.getFont().toString(), Font.PLAIN, 18));
        
        //Text Name
         JTextField textName=new JTextField();
        textName.setSize(240, 30);
        textName.setLocation(50, 550);
        textName.setFont(new java.awt.Font(textName.getFont().toString(), Font.PLAIN, 18));
        
         //Label cagetory
        JLabel cagetory=new JLabel("Category");
        cagetory.setSize(150,30);
        cagetory.setLocation(50, 590);
        cagetory.setFont(new java.awt.Font(name.getFont().toString(), Font.PLAIN, 18));
        
       // List Cagetory
        JComboBox cb2=new JComboBox(list);
        cb2.setMaximumRowCount(5); // số hàng tối đa được hiển thị
        cb2.setSize(150,30);
        cb2.setLocation(50,640);
        cb2.setFont(new java.awt.Font(cb2.getFont().toString(), Font.PLAIN, 18));
        
        //Label image
        JLabel image=new JLabel("Image");
        image.setSize(150,30);
        image.setLocation(400, 430);
        image.setFont(new java.awt.Font(image.getFont().toString(), Font.PLAIN, 18));
        
        //Choose Button
        JButton btnChoose=new JButton("Choose File");
        btnChoose.setSize(150,30);
        btnChoose.setLocation(400, 470);
        btnChoose.setFont(new java.awt.Font(btn.getFont().toString(), Font.PLAIN, 18));
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
        
        
          //Label price
        JLabel price=new JLabel("Price");
        price.setSize(150,30);
        price.setLocation(400, 510);
        price.setFont(new java.awt.Font(price.getFont().toString(), Font.PLAIN, 18));
        
        //Text Name
        JTextField textPrice=new JTextField();
        textPrice.setSize(240, 30);
        textPrice.setLocation(400, 550);
        textPrice.setFont(new java.awt.Font(textPrice.getFont().toString(), Font.PLAIN, 18));
        
        
        JButton btnAdd=new JButton("Add");
        btnAdd.setSize(150,30);
        btnAdd.setLocation(800, 470);
        btnAdd.setFont(new java.awt.Font(btnAdd.getFont().toString(), Font.PLAIN, 18));
        
        JButton btnUpdate=new JButton("Update");
        btnUpdate.setSize(150,30);
        btnUpdate.setLocation(800, 550);
        btnUpdate.setFont(new java.awt.Font(btnUpdate.getFont().toString(), Font.PLAIN, 18));
        
        JButton btnDelete=new JButton("Delete");
        btnDelete.setSize(150,30);
        btnDelete.setLocation(1000, 470);
        btnDelete.setFont(new java.awt.Font(btnDelete.getFont().toString(), Font.PLAIN, 18));
        
        JButton btnCancel=new JButton("Cancel");
        btnCancel.setSize(150,30);
        btnCancel.setLocation(1000, 550);
        btnCancel.setFont(new java.awt.Font(btnCancel.getFont().toString(), Font.PLAIN, 18));
        
        
        panel2.add(text);
        panel2.add(btn);
        panel2.add(label);
        panel2.add(cb);
        panel2.add(jsp);
        panel2.add(id);
        panel2.add(textID);
        panel2.add(name);
        panel2.add(textName);
        panel2.add(cagetory);
        panel2.add(cb2);
        panel2.add(image);
        panel2.add(btnChoose); 
        panel2.add(price);
        panel2.add(textPrice);
        panel2.add(btnAdd);
        panel2.add(btnUpdate);
        panel2.add(btnDelete);
        panel2.add(btnCancel);
        return panel2;
    }
    /**
     * @param args the command line arguments
     */
    

    
    public static void main(String[] args) {
        // TODO code application logic here
        Adminapp app=new Adminapp();
    }
    
}
