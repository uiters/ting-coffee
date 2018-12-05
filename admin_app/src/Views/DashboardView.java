/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Thang Le
 */
public class DashboardView {
    private JMonthChooser date;
    private JYearChooser year;
    private JButton btn;
    private JFreeChart lineChart;
    public DashboardView()
    {
        
    }
    
    public void Load(JPanel main,JPanel info,JPanel footer)
    {
        LoadMain(main);
        LoadInfo(info);
        LoadFooter(footer);
    }
    
    public void LoadMain(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        
        lineChart = ChartFactory.createLineChart(
         "Dashboard",
         "Day","Total",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new Dimension( main.getWidth() , main.getHeight() ) );
        
        
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.add(chartPanel);
        
        main.revalidate();
    }
    
    public void LoadInfo(JPanel info)
    {
        info.removeAll();
        info.setPreferredSize( new Dimension( 5,5) );
        info.revalidate();
    }
    
    public void LoadFooter(JPanel footer)
    {
        footer.removeAll(); // remove all components
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        footer.setPreferredSize(new Dimension(footer.getWidth(),50));
        
        
         /*Birthday*/
        JPanel Monthgroup=new JPanel();
        Monthgroup.setLayout(new BoxLayout(Monthgroup,BoxLayout.X_AXIS));
        Monthgroup.setMaximumSize(new Dimension(400, 30));
        Monthgroup.setBackground(Color.cyan);
        
        
        date=new JMonthChooser();
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        year=new JYearChooser();
        year.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn=new JButton("View");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
         JLabel dateLabel=new JLabel("Month : ");
         dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Monthgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Monthgroup.add(dateLabel);
        Monthgroup.add(Box.createRigidArea(new Dimension(33,0)));
        Monthgroup.add(date);
        Monthgroup.add(Box.createRigidArea(new Dimension(10,0))); 
        Monthgroup.add(year);
        Monthgroup.add(Box.createRigidArea(new Dimension(33,0))); 
        Monthgroup.add(btn);
        
        footer.add(Monthgroup);
        
        /*end Birthday*/
        footer.revalidate();
        footer.repaint();
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDataset();
            }
        });
    }
    
    DefaultCategoryDataset createDataset()
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15000 , "VND" , "2018-8" );
        dataset.addValue( 300000 , "VND" , "2018-9" );
        dataset.addValue( 600000 , "VND" ,  "2018-10" );
        dataset.addValue( 1200000 , "VND" , "2018-11" );
        dataset.addValue( 240000 , "VND" , "2018-12" );
        dataset.addValue( 300000 , "VND", "2019-1" );
        return dataset;
    }
}
