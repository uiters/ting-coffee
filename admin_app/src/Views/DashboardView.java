/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    
    public DashboardView()
    {
        
    }
    
    public void Load(JPanel main,JPanel info)
    {
        LoadMain(main);
        LoadInfo(info);
    }
    
    public void LoadMain(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        
        JFreeChart lineChart = ChartFactory.createLineChart(
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
