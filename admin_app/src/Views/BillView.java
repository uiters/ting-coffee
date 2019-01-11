/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Controllers.BillController;
import Models.BillModel.Bill;
import Models.FoodCategoryModel;
import com.placeholder.PlaceHolder;
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
public class BillView extends View {
    private JTextField idText; //ID text
    private JTextField idtableText; //IDtable text
    private JTextField dateinText; //DateCheckIn text
    private JTextField dateoutText; //DateCheckout text
    private JTextField discountText; //Discount text
    private JTextField totalText; //Total text
    private JTable table; //table Bill
    private BillController controller;
    private BillInfoView infoview;
    public BillView()
    {
        controller=BillController.getInstance(this);
        infoview=new BillInfoView();
    }
    
    public void Load(JPanel main,JPanel info,JPanel footer)
    {
        LoadBill(main);
        LoadBillInfo(info);
        LoadFooter(footer);
    }
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
    }
    
    @Override
    public void delete(int row){
        ((DefaultTableModel)table.getModel()).removeRow(row);
    }
    
    @Override
    public void update(int row, Object objects){
        
    }
    
    @Override
    public void loadView(Object objects){
        java.util.List<Bill> bills = (java.util.List<Bill>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        bills.forEach((item) -> {
            String status="";
            if(item.status==1) status="Paid";
            else status="Unpaid";
            model.addRow(new Object[] { item.id,  item.table,item.checkin,item.checkout,item.discount,item.price,status,item.username});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------
    
    public void LoadBill(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
         //Table
        String []title=new String[]{"ID","Table","CheckIn","Checkout","Discount","Total Price","Status","Username"};
        DefaultTableModel model= new DefaultTableModel(null,title){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        table=new JTable();
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.getTableHeader().setReorderingAllowed(false); // khong cho di chuyen thu tu cac column
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setSelectionMode(0);
        table.setRowHeight(80); // chỉnh độ cao của hàng
        
        controller.loadFull();
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
        main.repaint();
    }
    
    public void LoadBillInfo(JPanel info)
    {
        info.removeAll(); // remove all components
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300,info.getHeight()));
        
        /*search field*/
        JPanel search=new JPanel();
        search.setLayout(new BoxLayout(search,BoxLayout.X_AXIS));
        search.setBackground(new Color(209, 228, 252));
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));
        
        
        JTextField searchText=new JTextField();
        PlaceHolder p1;
        p1=new PlaceHolder (searchText,"ID");
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch=new JButton("Search");
        btnSearch.setForeground(new Color(0,107,68));
        btnSearch.add(Box.createRigidArea(new Dimension(55, 20)));
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        search.add(Box.createRigidArea(new Dimension(5,0)));
        search.add(btnSearch);
        search.add(Box.createRigidArea(new Dimension(15,0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5,0)));
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             String text=searchText.getText();
                for(int i=0;i<table.getRowCount();i++)
                {
                    if(text.equalsIgnoreCase(table.getValueAt(i, 0).toString())==true)
                    {
                        table.setRowSelectionInterval(i, i);
                        setInfo(i);
                        break;
                    }
                }
            }
        });
        /*end search field*/
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        
        /*ID Bill*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(new Color(209, 228, 252));
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         idText=new JTextField();
         idText.setEditable(false);
         idText.setBackground(Color.lightGray);
         idText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idLabel=new JLabel("ID : ");
         idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDgroup.add(idLabel);
        IDgroup.add(Box.createRigidArea(new Dimension(82,0)));
        IDgroup.add(idText);
        IDgroup.add(Box.createRigidArea(new Dimension(5,0)));         
        /*end ID*/
        
        /*ID table*/
        JPanel IDtablegroup=new JPanel();
        IDtablegroup.setLayout(new BoxLayout(IDtablegroup,BoxLayout.X_AXIS));
        IDtablegroup.setBackground(new Color(209, 228, 252));
        IDtablegroup.setMaximumSize(new Dimension(300, 30));
        
         idtableText=new JTextField();
         idtableText.setEditable(false);
         idtableText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel idtableLabel=new JLabel("Table : ");
         idtableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDtablegroup.add(Box.createRigidArea(new Dimension(5,0)));
        IDtablegroup.add(idtableLabel);
        IDtablegroup.add(Box.createRigidArea(new Dimension(48,0)));
        IDtablegroup.add(idtableText);
        IDtablegroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*end ID table*/
        
        
        /*Date Check in*/
        JPanel Dateingroup=new JPanel();
        Dateingroup.setLayout(new BoxLayout(Dateingroup,BoxLayout.X_AXIS));
        Dateingroup.setBackground(new Color(209, 228, 252));
        Dateingroup.setMaximumSize(new Dimension(300, 30));
        
         dateinText=new JTextField();
         dateinText.setEditable(false);
         dateinText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel dateinLabel=new JLabel("Date Check In : ");
         dateinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dateingroup.add(Box.createRigidArea(new Dimension(5,0)));
        Dateingroup.add(dateinLabel);
        Dateingroup.add(Box.createRigidArea(new Dimension(15,0)));
        Dateingroup.add(dateinText);
        Dateingroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END Date Check in*/
        
        
        
        /*Date Check out*/
        JPanel Dateoutgroup=new JPanel();
        
        Dateoutgroup.setLayout(new BoxLayout(Dateoutgroup,BoxLayout.X_AXIS));
        Dateoutgroup.setBackground(new Color(209, 228, 252));
        Dateoutgroup.setMaximumSize(new Dimension(300, 30));
        
         dateoutText=new JTextField();
         dateoutText.setEditable(false);
         dateoutText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel dateoutLabel=new JLabel("Date Check Out : ");
         dateoutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dateoutgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Dateoutgroup.add(dateoutLabel);
        Dateoutgroup.add(Box.createRigidArea(new Dimension(6,0)));
        Dateoutgroup.add(dateoutText);
        Dateoutgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END Date Check out*/
        
        
        /*Discount*/
        JPanel Discountgroup=new JPanel();
        Discountgroup.setLayout(new BoxLayout(Discountgroup,BoxLayout.X_AXIS));
        Discountgroup.setBackground(new Color(209, 228, 252));
        Discountgroup.setMaximumSize(new Dimension(300, 30));
        
         discountText=new JTextField();
         discountText.setEditable(false);
         discountText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel discountLabel=new JLabel("Discount : ");
         discountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Discountgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Discountgroup.add(discountLabel);
        Discountgroup.add(Box.createRigidArea(new Dimension(44,0)));
        Discountgroup.add(discountText);
        Discountgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END Discount*/
        
        
        /*Total Price*/
        JPanel Totalgroup=new JPanel();
        Totalgroup.setLayout(new BoxLayout(Totalgroup,BoxLayout.X_AXIS));
        Totalgroup.setBackground(new Color(209, 228, 252));
        Totalgroup.setMaximumSize(new Dimension(300, 30));
        
         totalText=new JTextField();
         totalText.setEditable(false);
         totalText.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel totalLabel=new JLabel("Total Price : ");
         totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Totalgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Totalgroup.add(totalLabel);
        Totalgroup.add(Box.createRigidArea(new Dimension(32,0)));
        Totalgroup.add(totalText);
        Totalgroup.add(Box.createRigidArea(new Dimension(5,0))); 
        /*END Total Price*/
        
        
        /*Button Delete*/
        JPanel Btngroup=new JPanel();
        Btngroup.setLayout(new BoxLayout(Btngroup,BoxLayout.X_AXIS));
        Btngroup.setBackground(new Color(209, 228, 252));
        Btngroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnDel=new JButton("Delete");
        btnDel.setForeground(new Color(0,107,68));
        btnDel.add(Box.createRigidArea(new Dimension(50, 20)));
        btnDel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Btngroup.add(Box.createRigidArea(new Dimension(5,0)));
        Btngroup.add(btnDel);
        
        /*Event click button Del*/
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int row=table.getSelectedRow();
                if(row>=0)
                {
                    controller.delete(table.getValueAt(row, 0));
                    delete(row);
                    JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                }
            }
        });
        /*End Event click button Del*/
        /*End Button Delete*/
        
        /*Button Delete*/
        JPanel BtnDetailgroup=new JPanel();
        BtnDetailgroup.setLayout(new BoxLayout(BtnDetailgroup,BoxLayout.X_AXIS));
        BtnDetailgroup.setBackground(new Color(209, 228, 252));
        BtnDetailgroup.setMaximumSize(new Dimension(300, 30));
        
        JButton btnDetail=new JButton("Detail");
        btnDetail.setForeground(new Color(0,107,68));
        btnDetail.add(Box.createRigidArea(new Dimension(50, 20)));
        btnDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        BtnDetailgroup.add(Box.createRigidArea(new Dimension(5,0)));
        BtnDetailgroup.add(btnDetail);
        
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int row=table.getSelectedRow();
                if(row>=0)
                {
                    infoview.Load(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
                }
            }
        });
        /*Btn Detail*/
        
        
        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(IDtablegroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Dateingroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Dateoutgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Discountgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Totalgroup);
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(Btngroup); 
        detail.add(Box.createRigidArea(new Dimension(0,20)));
        detail.add(BtnDetailgroup); 
         /*end info detail*/
         
         
         
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(search);
        info.add(Box.createRigidArea(new Dimension(0,20)));
        info.add(detail);
        
        
        //repaint panel
        info.revalidate();
        info.repaint();
    }
    
    public void LoadFooter(JPanel footer)
    {
        footer.removeAll();
        footer.setPreferredSize(null);
        footer.revalidate();
        footer.repaint();
    }
    
    
     /*get value from table and set to textfield*/
    private void setInfo(int row)
    {
            String ID=table.getModel().getValueAt(row, 0).toString();
            String Name=table.getModel().getValueAt(row, 1).toString();  
            String datein=table.getModel().getValueAt(row, 2).toString();  
            String dateout=table.getModel().getValueAt(row, 3).toString();  
            String discount=table.getModel().getValueAt(row, 4).toString();  
            String total=table.getModel().getValueAt(row, 5).toString();  
            idText.setText(ID);
            idtableText.setText(Name);
            dateinText.setText(datein);
            dateoutText.setText(dateout);
            discountText.setText(discount);
            totalText.setText(total);
    }
    
}
