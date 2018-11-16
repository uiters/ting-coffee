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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
public class StaffView {
    
    private JTextField idText; //ID text
    private JTextField nameText; //IDtable text
    private JTextField birthText; //Birthday text
    private JTextField addressText; //address text
    private JTextField phoneText; //Phone text
    private JComboBox cb;//Sex combobox
    private JTable table; //table Staff
    private AddFrameView addFrame;
    
    public StaffView()
    {
        addFrame=new AddFrameView("Add Staff");
    }
    
    public void Load(JPanel main,JPanel info,JPanel footer)
    {
        LoadStaff(main);
        LoadStaffInfo(info);
        LoadFooter(footer);
    }
    
    public void LoadStaff(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
         //Table
        String []title=new String[]{"ID","Name","Birthday","Sex","Address","Phone"};
        Object [][]object=new Object[][]{
            {1,"Nguyen Van A","1990","Male","TPHCM","123456"},
            {2,"Ha Thi C","1995","Female","Da Nang","123456789"},
            {3,"Nguyen Hoang C","1988","Male","TPHCM","123456666"}
                
        };
        DefaultTableModel model= new DefaultTableModel(object,title);
        table=new JTable();
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setSelectionMode(0);
        table.setRowHeight(80); // chỉnh độ cao của hàng
        
        JScrollPane jsp=new JScrollPane(table);
        
        /*Sự kiện click ở table*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                    int row=table.getSelectedRow();
                    setInfo( row);
                    
            }    
});
         /*End click table event*/
         
         
        /*END*/
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.add(jsp);
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.revalidate();
    }
    
    
    public void LoadStaffInfo(JPanel info)
    {
        info.removeAll();// remove all components
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300,info.getHeight()));
        
        
        /*search field*/
        JPanel search=new JPanel();
        search.setLayout(new BoxLayout(search,BoxLayout.X_AXIS));
        search.setBackground(Color.yellow);
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));
        
        
        JTextField searchText=new JTextField();
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch=new JButton("Search");
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(btnSearch);
        search.add(Box.createRigidArea(new Dimension(15,0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        
        /*end search field*/
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(Color.yellow);
           
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(Color.yellow);
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         idText=new JTextField();
         idText.setEditable(false);
         idText.setBackground(Color.lightGray);
         idText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idLabel=new JLabel("ID : ");
         idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDgroup.add(idLabel);
        IDgroup.add(Box.createRigidArea(new Dimension(70,0)));
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
        
         birthText=new JTextField();
         birthText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel birthLabel=new JLabel("Birthday : ");
         birthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Birthgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Birthgroup.add(birthLabel);
        Birthgroup.add(Box.createRigidArea(new Dimension(33,0)));
        Birthgroup.add(birthText);
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
        
        /*end Staff info detail*/
        
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(search);
        //info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(detail);
        info.revalidate();
        info.repaint();
        
        
    }
    
    public void LoadFooter(JPanel footer)
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
         footer.repaint();
         
         /*Sự kiện các btn Add,Update,Delete,Cancel*/
         btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.StaffAdd();
              
            }
        });
         btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly update
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    //setTable(row)
                    setTable(row);
                    
                    JOptionPane.showMessageDialog(null, "Đã update thành công! o hang thu "+row);
                }
                
            }
        });
         
         btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    ((DefaultTableModel)table.getModel()).removeRow(row);
                    JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                }
                
            }
        });
         
         btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly
            }
        });
         /*END sự kiện btn Add,....*/
    }
    
    
    /*get value from table and set to textfield*/
    private void setInfo(int row)
    {
            String ID=table.getModel().getValueAt(row, 0).toString();
            String Name=table.getModel().getValueAt(row, 1).toString();
            String Birth=table.getModel().getValueAt(row, 2).toString();
            String Sex=table.getModel().getValueAt(row, 3).toString();
            String Address=table.getModel().getValueAt(row, 4).toString();
            String Phone=table.getModel().getValueAt(row, 5).toString();
            
            idText.setText(ID);
            nameText.setText(Name);
            birthText.setText(Birth);
            addressText.setText(Address);
            phoneText.setText(Phone);
            for(int i=0;i<cb.getItemCount();i++)
            {
                if(cb.getItemAt(i).toString()==Sex)
                {
                    cb.setSelectedIndex(i);
                }
            }
    }
    
    /*set value to table*/
    private void setTable(int row)
    {
        
        Boolean flag=true;
        try {
            Double f=Double.parseDouble(phoneText.getText().toString());
            flag=true; // jtextfield chi chua 0-9
        } catch (NumberFormatException e)
        {
            flag=false;
        }
        if(idText.getText().toString()!=null&&nameText.getText().toString()!=null&&flag==true
                &&cb.getSelectedItem()!=null&&addressText.getText().toString()!=null&&birthText.getText().toString()!=null)
        {
            table.setValueAt(idText.getText().toString(), row, 0);
            table.setValueAt(nameText.getText().toString(), row, 1);
            table.setValueAt(birthText.getText().toString(), row, 2);
            table.setValueAt(cb.getSelectedItem().toString(), row, 3);
            table.setValueAt(addressText.getText().toString(), row, 4);
            table.setValueAt(phoneText.getText().toString(), row, 5);
        }
        
    }
}
