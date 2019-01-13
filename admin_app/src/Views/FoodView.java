/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.FoodController;
import Models.FoodCategoryModel.FoodCategory;
import Models.FoodModel;
import Models.FoodModel.Food;
import com.placeholder.PlaceHolder;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Thang Le
 */
public class FoodView extends View{

    private List<String> list = null; //danh sách trong category
    private JTextField idText; //ID text
    private JTextField nameText; //Nametext
    private JTextField priceText;
    private JTable table; //table FOOD
    private JComboBox cb2=new JComboBox();
    private AddFrameView addFrame;
    private final FoodController controller;
    
    String pathImg="";
    byte[] data=null;

    FoodView() {
        controller = FoodController.getInstance(this);
        addFrame = new AddFrameView("Add Food",controller);
        
        
    }
    
    public void setList(Object objects)
    {
        list=new ArrayList<String>();
        cb2.removeAllItems();
        List<FoodCategory> categories = (List<FoodCategory>)(Object)objects;
        for(FoodCategory item :categories)
        {
            String temp=item.nameCategory;
            list.add(temp);
        }
        
        int count=list.size();
        //JOptionPane.showMessageDialog(null, count);
        String []obj=new String[count];
        for(int i=0;i<obj.length;i++)
        {
            obj[i]=list.get(i);
            //add combo item
            //end
            cb2.addItem(obj[i]);
        }
        cb2.setSelectedItem(null);
        
    }
    
    
    //---------------------------------------------------------------------------------------------------------
    private void updateInfo(int row,Object objects)
    {
        Food category = (Food)objects;
        ((DefaultTableModel)table.getModel()).setValueAt(category.name, row, 1);
        ((DefaultTableModel)table.getModel()).setValueAt(category.nameCategory, row, 2);
        //((DefaultTableModel)table.getModel()).setValueAt(this.getImage(category.getImage()), row, 3);
        ((DefaultTableModel)table.getModel()).setValueAt(category.price, row, 4);
    }
    @Override
    public void insert(Object objects){
        Food category = (Food)objects;
        ((DefaultTableModel)table.getModel()).addRow(new Object[]{category.id, category.name,category.nameCategory,
                                                                                       this.getImage(category.getImage()),category.price });
    }
    
    @Override
    public void delete(int row){
        ((DefaultTableModel)table.getModel()).removeRow(row);
    }
    
    @Override
    public void update(int row, Object objects){
        Food category = (Food)objects;
        ((DefaultTableModel)table.getModel()).setValueAt(category.name, row, 1);
        ((DefaultTableModel)table.getModel()).setValueAt(category.nameCategory, row, 2);
        ((DefaultTableModel)table.getModel()).setValueAt(this.getImage(category.getImage()), row, 3);
        ((DefaultTableModel)table.getModel()).setValueAt(category.price, row, 4);
    }
    
    @Override
    public void loadView(Object objects){
         List<Food> categories = (List<Food>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        categories.forEach((food) -> {
            model.addRow(new Object[] { food.id,  food.name, food.nameCategory, this.getImage(food.getImage()), food.price});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------

    public void Load(JPanel main, JPanel info, JPanel footer) {
        LoadFood(main);
        LoadInfoFood(info);
        LoadFooter(footer);
        
        
    }

    public void LoadFood(JPanel main) {
        main.removeAll();
        
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        /*LOAD TABLE*/
        //Table
        String[] title = new String[]{"ID", "Name", "Category", "Image", "Price"};
        DefaultTableModel model = new DefaultTableModel(null, title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return ImageIcon.class;
                    case 4:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
        };
        table.getTableHeader().setFont(new java.awt.Font(table.getFont().toString(), Font.BOLD, 22));
        table.getTableHeader().setReorderingAllowed(false); // khong cho di chuyen thu tu cac column
        table.setFont(new java.awt.Font(table.getFont().toString(), Font.PLAIN, 18));
        table.setModel(model);
        table.setSelectionMode(0);
        table.setRowHeight(80); // chỉnh độ cao của hàng

        JScrollPane jsp = new JScrollPane(table);

        controller.loadFull();//call get foods
        
        
        /*Sự kiện click ở table*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                setInfo(row);

            }
        });
        /*End click table event*/

 /*END*/
        main.add(Box.createRigidArea(new Dimension(5, 0)));
        main.add(jsp);
        main.add(Box.createRigidArea(new Dimension(5, 0)));
        main.revalidate();
    }

    public void LoadInfoFood(JPanel info) {
        info.removeAll(); // remove all components
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setPreferredSize(new Dimension(300, info.getHeight()));
        
        controller.setList();
        
        /*search field*/
        JPanel search = new JPanel();
        search.setLayout(new BoxLayout(search, BoxLayout.X_AXIS));
        search.setBackground(new Color(209, 228, 252));
        //search.setPreferredSize(new Dimension(info.getWidth(),20));
        search.setMaximumSize(new Dimension(300, 30));
        //search.setMaximumSize(new Dimension(info.getWidth(),20));

        JTextField searchText = new JTextField();
        PlaceHolder p1;
        p1=new PlaceHolder (searchText,"Name,Category");
        searchText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(new Color(0,107,68));
        btnSearch.add(Box.createRigidArea(new Dimension(43, 20)));
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);

        search.add(Box.createRigidArea(new Dimension(5, 0)));
        search.add(btnSearch);
        search.add(Box.createRigidArea(new Dimension(15, 0)));
        search.add(searchText);
        search.add(Box.createRigidArea(new Dimension(5, 0)));
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchText.getText().toLowerCase();
                if(text.equalsIgnoreCase("Name,Category"))
                    text = "";
                Object data =  controller.Filter(text, null);
                if(data != null)
                    loadView(data);
            }
            
        });
        /*end search field*/
 

 /*info detail*/
        JPanel detail = new JPanel();
        detail.setLayout(new BoxLayout(detail, BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));

        /*ID*/
        JPanel IDgroup = new JPanel();
        IDgroup.setLayout(new BoxLayout(IDgroup, BoxLayout.X_AXIS));
        IDgroup.setBackground(new Color(209, 228, 252));
        IDgroup.setMaximumSize(new Dimension(300, 30));

        idText = new JTextField();
        idText.setEditable(false);
        idText.setBackground(Color.lightGray);
        idText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel idLabel = new JLabel("ID : ");
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDgroup.add(Box.createRigidArea(new Dimension(5, 0)));
        IDgroup.add(idLabel);
        IDgroup.add(Box.createRigidArea(new Dimension(70, 0)));
        IDgroup.add(idText);
        IDgroup.add(Box.createRigidArea(new Dimension(5, 0)));
        /*end ID*/

 /*Name*/
        JPanel Namegroup = new JPanel();
        Namegroup.setLayout(new BoxLayout(Namegroup, BoxLayout.X_AXIS));
        Namegroup.setBackground(new Color(209, 228, 252));
        Namegroup.setMaximumSize(new Dimension(300, 30));

        nameText = new JTextField();
        nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Namegroup.add(Box.createRigidArea(new Dimension(5, 0)));
        Namegroup.add(nameLabel);
        Namegroup.add(Box.createRigidArea(new Dimension(48, 0)));
        Namegroup.add(nameText);
        Namegroup.add(Box.createRigidArea(new Dimension(5, 0)));
        /*end Name*/

 /*Category*/
        JPanel Categorygroup = new JPanel();
        Categorygroup.setLayout(new BoxLayout(Categorygroup, BoxLayout.X_AXIS));
        Categorygroup.setBackground(new Color(209, 228, 252));
        Categorygroup.setMaximumSize(new Dimension(300, 30));
        
        cb2.setSelectedItem(null);
        cb2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel LabelCategory = new JLabel("Category : ");
        LabelCategory.setAlignmentX(Component.CENTER_ALIGNMENT);

        Categorygroup.add(Box.createRigidArea(new Dimension(5, 0)));
        Categorygroup.add(LabelCategory);
        Categorygroup.add(Box.createRigidArea(new Dimension(30, 0)));
        Categorygroup.add(cb2);
        Categorygroup.add(Box.createRigidArea(new Dimension(5, 0)));
        /*End Category*/

 /*Choose image*/
        JPanel Choosegroup = new JPanel();
        Choosegroup.setLayout(new BoxLayout(Choosegroup, BoxLayout.X_AXIS));
        Choosegroup.setBackground(new Color(209, 228, 252));
        Choosegroup.setMaximumSize(new Dimension(300, 30));

        JLabel imgLabel = new JLabel("Image : ");
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Button choose image
        JButton btnChoose = new JButton("Choose File");
        btnChoose.setForeground(new Color(0,107,68));
        btnChoose.add(Box.createRigidArea(new Dimension(80, 20)));
        btnChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
        JFileChooser choose = new JFileChooser();
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    choose.setCurrentDirectory(new File(System.getProperty("user.home")));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                    choose.setFileFilter(filter);
                    int returnVal = choose.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        // xu ly chon anh
                        File selectedFile = choose.getSelectedFile();
                        String path = selectedFile.getAbsolutePath();
                        pathImg = path;
                        try
                        {
                            data=getByte(selectedFile);
                        } catch (IOException  ex)
                         {
                                    
                        }
                    }
                }
            }
        });

        Choosegroup.add(Box.createRigidArea(new Dimension(5, 0)));
        Choosegroup.add(imgLabel);
        Choosegroup.add(Box.createRigidArea(new Dimension(45, 0)));
        Choosegroup.add(btnChoose);
        Choosegroup.add(Box.createRigidArea(new Dimension(5, 0)));

        /*End Choose image*/
 /*Price*/
        JPanel Pricegroup = new JPanel();
        Pricegroup.setLayout(new BoxLayout(Pricegroup, BoxLayout.X_AXIS));
        Pricegroup.setBackground(new Color(209, 228, 252));
        Pricegroup.setMaximumSize(new Dimension(300, 30));

        priceText = new JTextField();
        priceText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel priceLabel = new JLabel("Price : ");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Pricegroup.add(Box.createRigidArea(new Dimension(5, 0)));
        Pricegroup.add(priceLabel);
        Pricegroup.add(Box.createRigidArea(new Dimension(50, 0)));
        Pricegroup.add(priceText);
        Pricegroup.add(Box.createRigidArea(new Dimension(5, 0)));
        //numeric
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

        detail.add(IDgroup);
        detail.add(Box.createRigidArea(new Dimension(0, 20)));
        detail.add(Namegroup);
        detail.add(Box.createRigidArea(new Dimension(0, 20)));
        detail.add(Categorygroup);
        detail.add(Box.createRigidArea(new Dimension(0, 20)));
        detail.add(Pricegroup);
        detail.add(Box.createRigidArea(new Dimension(0, 20)));
        detail.add(Choosegroup);

        /*end info detail*/
        info.add(Box.createRigidArea(new Dimension(0, 20)));
        info.add(search);
        info.add(Box.createRigidArea(new Dimension(0, 20)));
        info.add(detail);
        info.revalidate();
        info.repaint();

    }

    void LoadFooter(JPanel footer) {
        footer.removeAll(); // remove all components
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
        footer.setPreferredSize(new Dimension(footer.getWidth(), 50));
        JPanel btn = new JPanel();
        btn.setLayout(new BoxLayout(btn, BoxLayout.X_AXIS));
        btn.setBackground(new Color(228,249,245));

        JButton btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(0,107,68));
        btnAdd.add(Box.createRigidArea(new Dimension(50, 20)));
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setForeground(new Color(0,107,68));
        btnUpdate.add(Box.createRigidArea(new Dimension(50, 20)));
        JButton btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(0,107,68));
        btnDelete.add(Box.createRigidArea(new Dimension(50, 20)));
        //JButton btnCancel = new JButton("Cancel");
        //btnCancel.setForeground(new Color(0,107,68));
        //btnCancel.add(Box.createRigidArea(new Dimension(50, 20)));

        btn.add(Box.createRigidArea(new Dimension(5, 0)));
        btn.add(btnAdd);
        btn.add(Box.createRigidArea(new Dimension(50, 0)));
        btn.add(btnUpdate);
        btn.add(Box.createRigidArea(new Dimension(50, 0)));
        btn.add(btnDelete);
        btn.add(Box.createRigidArea(new Dimension(50, 0)));
        //btn.add(btnCancel);
        //btn.add(Box.createRigidArea(new Dimension(50, 0)));

        footer.add(btn);
        footer.revalidate();

        /*Sự kiện các btn Add,Update,Delete,Cancel*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.FoodAdd(cb2);
                //JOptionPane.showMessageDialog(null, "Reload database ");
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly update
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String name=nameText.getText();
                    String namecategory=cb2.getSelectedItem().toString();
                    double price=Double.parseDouble(priceText.getText());
                    //setTable(row);
                    if(pathImg.equals("")==false) // kiem tra co cap nhat avt hay 
                    {
                        
                       // encode with padding
                        String encoded = Base64.encodeBase64String(data);

                        Food food=FoodModel.getInstance().new Food( Integer.parseInt(idText.getText()), name ,namecategory,price,encoded );
                    
                        update(row, food);
                        controller.update(food);
                    }
                    else
                    {
                        Food food=FoodModel.getInstance().new Food( Integer.parseInt(idText.getText()), name ,namecategory,price );
                        updateInfo(row,food);
                        controller.updateInfo(food);
                    }
                    
                }

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    controller.delete(table.getValueAt(row, 0));
                    delete(row);//delete in view
                }

            }
        });

        /*btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Xu ly
            }
        });*/
        /*END sự kiện btn Add,....*/

    }

    /*get value from table and set to textfield*/
    private void setInfo(int row) {
        String ID = table.getModel().getValueAt(row, 0).toString();
        String Name = table.getModel().getValueAt(row, 1).toString();
        String Category = table.getModel().getValueAt(row, 2).toString();
        String Price = table.getModel().getValueAt(row, 4).toString();
        idText.setText(ID);
        nameText.setText(Name);
        priceText.setText(Price);
        for (int i = 0; i < cb2.getItemCount(); i++) {
            if (cb2.getItemAt(i).toString().equals(Category)) {
                cb2.setSelectedIndex(i);
            }
        }
    }

    /*set value to table*/
    private void setTable(int row) {

        Boolean flag = true;
        try {
            Double f = Double.parseDouble(priceText.getText().toString());
            flag = true; // jtextfield chi chua 0-9
        } catch (NumberFormatException e) {
            flag = false;
        }
        if (idText.getText().toString() != null && nameText.getText().toString() != null && flag == true) {
            table.setValueAt(idText.getText().toString(), row, 0);
            table.setValueAt(nameText.getText().toString(), row, 1);
            table.setValueAt(cb2.getSelectedItem().toString(), row, 2);
            Double f = Double.parseDouble(priceText.getText().toString());
            table.setValueAt(f, row, 4);
        }

    }

    /*get Image and Resize*/
    private ImageIcon ResizeImage(String Imagepath) {
        ImageIcon myimage = new ImageIcon(Imagepath);
        Image img = myimage.getImage();
        //resize
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private ImageIcon getImage(byte[] data) {
        return new ImageIcon(new ImageIcon(data).getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
    }

    public void setFoods(List<Food> foods) {
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        for(Food food : foods)
          model.addRow(new Object[] { food.id,  food.name, food.nameCategory, this.getImage(food.getImage()), food.price});
        table.setModel(model);
    }
    
    public byte[] extractBytes (String ImageName) throws IOException 
    {
            // open image
            File imgPath = new File(ImageName);
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // get DataBufferBytes from Raster
            WritableRaster raster = bufferedImage .getRaster();
            DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

            return ( data.getData() );
    }
    
    public byte[] getByte(File file) throws IOException
    {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
    
    
    
    
}
