/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Constants.Password;
import Controllers.AccountController;
import Models.AccountModel;
import Models.AccountModel.Account;
import Models.AccountTypeModel.AccountType;
import Models.FoodCategoryModel.FoodCategory;
import Models.FoodModel.Food;
import com.placeholder.PlaceHolder;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Thang Le
 */
public class AccountView extends View {
    
    private JTextField idText; //ID text
    private JTextField nameText; //IDtable text
    private JDateChooser birthday;
    private JTextField birthText; //Birthday text
    private JTextField addressText; //address text
    private JTextField phoneText; //Phone text
    private JTextField idCardText; //IDCard text
    private JComboBox cb;//Sex combobox
    private JComboBox cbType=new JComboBox(); //account type combobox
    private JTable table; //table Staff
    private AddFrameView addFrame;
    private List<String> list=null; //list save account type
    private AccountController controller;
    
    public AccountView ()
    {
        
        controller=AccountController.getInstance(this);
        addFrame=new AddFrameView("Add Staff", controller);

        
        
    }
    
    //overide
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
        Account category = (Account)objects;
        String temp="";
        if(category.sex==1) temp="Male";
        else temp="Female";
        ((DefaultTableModel)table.getModel()).addRow(new Object[]{category.username, category.name,category.idcard,
                                                                                       category.birth,temp,category.address,category.number,category.typename});
    }
    
    @Override
    public void delete(int row){
        ((DefaultTableModel)table.getModel()).removeRow(row);
    }
    
    @Override
    public void update(int row, Object objects){
        Account item = (Account)objects;
            cb.setSelectedIndex(item.sex);
            String temp=cb.getSelectedItem().toString();
             ((DefaultTableModel)table.getModel()).setValueAt(item.name, row, 1);
             ((DefaultTableModel)table.getModel()).setValueAt(item.idcard, row, 2);
             ((DefaultTableModel)table.getModel()).setValueAt(item.birth, row, 3);
             ((DefaultTableModel)table.getModel()).setValueAt(temp, row, 4);
             ((DefaultTableModel)table.getModel()).setValueAt(item.address, row, 5);
             ((DefaultTableModel)table.getModel()).setValueAt(item.number, row, 6);
             ((DefaultTableModel)table.getModel()).setValueAt(item.typename, row, 7);

    }
    
    @Override
    public void loadView(Object objects){
         List<Account> categories = (List<Account>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        categories.forEach((item) -> {
            String temp=null;
            if(item.sex==1) temp="Male";
            else temp="Female";
            model.addRow(new Object[] { item.username,  item.name, item.idcard, item.birth, temp,item.address,item.number,item.typename});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------
    
    //set combobox account type
    public void setList(Object objects)
    {
        cbType.removeAllItems();
        list=new ArrayList<String>();
        List<AccountType> categories = (List<AccountType>)(Object)objects;
        for(AccountType item :categories)
        {
            String temp=item.name;
            
            list.add(temp);
        }
        
        int count=list.size();    
        String []obj=new String[count];
        
        for(int i=0;i<obj.length;i++)
        {
            obj[i]=list.get(i);
            //add combo item
            //end
            cbType.addItem(obj[i]);
        }
        cbType.setSelectedItem(null);
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
        String []title=new String[]{"ID","Name","IDCard","Birthday","Sex","Address","Phone","Staff Type"};
        /*Object [][]object=new Object[][]{
            {"abc","Nguyen Van A","123","10-08-1990","Male","TPHCM","123456","Nhan vien"},
            {"xyz","Ha Thi C","123","15-02-1995","Female","Da Nang","123456789","Nhan vien"},
            {"def","Nguyen Hoang C","123","05-03-1988","Male","TPHCM","123456666","Quan ly"}
                
        */
        DefaultTableModel model= new DefaultTableModel(null,title){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        table = new JTable();
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
    }
    
    
    public void LoadStaffInfo(JPanel info)
    {
        info.removeAll();// remove all components
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300,info.getHeight()));
        
        //get account type and set combobox
        controller.setListAccType();
        
        /*search field*/
        JPanel search=new JPanel();
        search.setLayout(new BoxLayout(search,BoxLayout.X_AXIS));
        search.setBackground(new Color(209, 228, 252));
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));
        
        
        JTextField searchText=new JTextField();
        PlaceHolder p1;
        p1=new PlaceHolder (searchText,"Tìm với ID,Name,Phone");
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch=new JButton("Search");
        btnSearch.setForeground(new Color(0,107,68));
        btnSearch.add(Box.createRigidArea(new Dimension(43, 20)));
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
                if(!text.equals("")){
                for(int i=0;i<table.getRowCount();i++)
                    
                {
                    if(text.equalsIgnoreCase(table.getValueAt(i, 0).toString())==true)
                    {
                        table.setRowSelectionInterval(i, i);
                         setInfo(i);
                        break;
                    }
                   
                    if(String.valueOf(table.getValueAt(i, 1)).toLowerCase().contains(text.toLowerCase()))
                    {
                            table.setRowSelectionInterval(i, i);
                            setInfo(i);
                            break;
                    }
                    if(String.valueOf(table.getValueAt(i, 6)).toLowerCase().contains(text.toLowerCase()))
                    {
                            table.setRowSelectionInterval(i, i);
                            setInfo(i);
                            break;
                    }
                    
                }
                }
            }
            
        });
        /*end search field*/
        
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
           
        /*ID*/
        JPanel IDgroup=new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup,BoxLayout.X_AXIS));
        IDgroup.setBackground(new Color(209, 228, 252));
        IDgroup.setMaximumSize(new Dimension(300, 30));
        
         idText=new JTextField();
         idText.setEditable(false);
         idText.setBackground(Color.lightGray);
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
        
         idCardText=new JTextField();
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
        
        
        birthday=new JDateChooser();
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
        Sexgroup.setBackground(new Color(209, 228, 252));
        Sexgroup.setMaximumSize(new Dimension(300, 30));
        
        String []list2=new String[2];
        list2[0]="Female";
        list2[1]="Male";
        cb=new JComboBox(list2);
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setSelectedItem(null);
        
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
        
        //numeric 
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
        btnAdd.setForeground(new Color(0,107,68));
        btnAdd.add(Box.createRigidArea(new Dimension(50, 20)));
         JButton btnUpdate=new JButton("Update");
        btnUpdate.setForeground(new Color(0,107,68));
        btnUpdate.add(Box.createRigidArea(new Dimension(50, 20)));
         JButton btnDelete=new JButton("Delete");
        btnDelete.setForeground(new Color(0,107,68));
        btnDelete.add(Box.createRigidArea(new Dimension(50, 20)));
         JButton btnCancel=new JButton("Reset Password");
        btnCancel.setForeground(new Color(0,107,68));
        btnCancel.add(Box.createRigidArea(new Dimension(100, 20)));
         
         
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
                addFrame.StaffAdd(cbType);
                JOptionPane.showMessageDialog(null, "Reload database ");
            }
        });
         btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly update
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    //if(idText.getText()!=null&&nameText.getText()!=null&&idCardText.getText()!=null&&addressText.getText()!=null)
                    {
                        int index=cb.getSelectedIndex();
                        int type=cbType.getSelectedIndex();
                        String typename=cbType.getSelectedItem().toString();
                        //convert date to string
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate =dateFormat.format(birthday.getDate());
                        //Account acc = AccountModel.getInstance().new Account(idText.getText(),nameText.getText(),idCardText.getText(),strDate,
                            //index,addressText.getText(),phoneText.getText(),type);
                        //acc 2 dung constructor khac de update trong database
                        Account acc2 = AccountModel.getInstance().new Account(idText.getText(),nameText.getText(),idCardText.getText(),strDate,
                            index,addressText.getText(),phoneText.getText(),typename);
                        update(row, acc2);
                        controller.update(acc2);
                    }
                    
                }
                
            }
        });
         
         btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                if(row>=0)
                {
                    controller.delete(table.getValueAt(row, 0));
                    delete(row);//delete in view
                }
                
            }
        });
         
         btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly reset password
                 int row=table.getSelectedRow();
                if(row>=0)
                {
                    String user=table.getValueAt(row, 0).toString();
                    String pass=Password.hashPassword(Constants.Constant.defaultpass);
                    controller.ResetPass(user,pass);
                }
            }
        });
         /*END sự kiện btn Add,....*/
    }
    
    
    /*get value from table and set to textfield*/
    private void setInfo(int row)
    {
            String ID=table.getModel().getValueAt(row, 0).toString();
            String Name=table.getModel().getValueAt(row, 1).toString();
            String IDCard=table.getModel().getValueAt(row, 2).toString();
            String Sex=table.getModel().getValueAt(row, 4).toString();
            String Address=table.getModel().getValueAt(row, 5).toString();
            String Phone=table.getModel().getValueAt(row, 6).toString();
            String Type=table.getModel().getValueAt(row, 7).toString();
            idText.setText(ID);
            nameText.setText(Name);
            idCardText.setText(IDCard);
            addressText.setText(Address);
            phoneText.setText(Phone);
            for(int i=0;i<cb.getItemCount();i++)
            {
                if(cb.getItemAt(i).toString()==Sex)
                {
                    cb.setSelectedIndex(i);
                }
            }
            
            for(int i=0;i<cbType.getItemCount();i++)
            {
                if(cbType.getItemAt(i).toString().equals(Type)==true)
                {
                    cbType.setSelectedIndex(i);

                }
            }
            
            
            //set date for jdatechooser from a value get from table
            try {
                String Birth=table.getModel().getValueAt(row, 3).toString();
                Date date=new SimpleDateFormat("yy-MM-dd").parse(Birth);
                birthday.setDate(date);
            }catch (ParseException ex)
            {
                
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
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date=birthday.getDate();
        String birth=sdf.format(date);
        if(idText.getText().toString()!=null&&nameText.getText().toString()!=null&&flag==true
                &&cb.getSelectedItem()!=null&&addressText.getText().toString()!=null&&birth!=null)
        {
            table.setValueAt(idText.getText().toString(), row, 0);
            table.setValueAt(nameText.getText().toString(), row, 1);
            table.setValueAt(birth, row, 2);
            table.setValueAt(cb.getSelectedItem().toString(), row, 3);
            table.setValueAt(addressText.getText().toString(), row, 4);
            table.setValueAt(phoneText.getText().toString(), row, 5);
        }
        
    }
}
