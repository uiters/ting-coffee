/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.BillInfoController;
import Models.BillInfoModel.BillInfo;
import Models.FoodCategoryModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thang Le
 */
public class BillInfoView extends View {
    private JDialog jf; // xài dialog vì để hold main excution (sau khi click add,frame gọi tới sẽ tự reload database)
    private JPanel panel;
    private JTable table;
    private String title;
    private BillInfoController controller;
    public BillInfoView()
    {
        title="Bill Info Detail";
        controller=BillInfoController.getInstance(this);
        
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
         List<BillInfo> categories = (List<BillInfo>)(Object)objects;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        categories.forEach((item) -> {
            model.addRow(new Object[] { item.namefood,item.quantity});
        });
        
        table.setModel(model);
    }
    //----------------------------------------------------------------------------------------------------------
    
    
    public void Load(int id)
    {
        jf=new JDialog(jf, "Bill Info Detail");
        jf.setModal(true); // hold main excution
        jf.setSize(500, 400);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // đóng frame hiện hành
        jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        
        panel=new JPanel();
        panel.setBackground(Color.green);
        /*info detail*/
        JPanel detail=new JPanel();
        detail.setLayout(new BoxLayout(detail,BoxLayout.Y_AXIS));
        detail.setBackground(new Color(209, 228, 252));
        detail.setPreferredSize(new Dimension(500,400));
         //Table
        String []title=new String[]{"Food","Quantity"};
        
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
          
        
        controller.LoadInfo(id);
        JScrollPane jsp=new JScrollPane(table);
        
        //controller.loadFull();
        
        /*Sự kiện click ở table*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                    int row=table.getSelectedRow();
                    //setInfo( row);
                    
            }    
});
         /*End click table event*/
        detail.add(jsp);
        jf.add(detail, BorderLayout.CENTER);
        jf.setVisible(true);
    }
}
