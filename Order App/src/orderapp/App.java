/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ndc07
 */
public class App {
    
    private JFrame mainFrame;
    
    public App() {
        initComponents();
    }
    
    public static void main(String[] args) {
        new App();
    }
    
    private void initComponents() {
        
        mainFrame = new JFrame("Cafe Management || Order App");
        mainFrame.setSize(1000, 700);
        
        /* HEADER */
        JPanel header = new JPanel();
        createHeader(header);
        
        /* MENU */
        JPanel menuSection = new JPanel();
        menuSection.setBackground(Color.green);
        
        /* BILL */
        JPanel billSection = new JPanel();
        billSection.setBackground(Color.yellow);
        
        
        /* FOOTER */
        JPanel footer = new JPanel();
        footer.setBackground(Color.cyan);
        
        mainFrame.getContentPane().add(header, BorderLayout.PAGE_START);
        mainFrame.getContentPane().add(menuSection, BorderLayout.CENTER);
        mainFrame.getContentPane().add(billSection, BorderLayout.LINE_END);
        mainFrame.getContentPane().add(footer, BorderLayout.PAGE_END);
        
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        
    }
    
    private void createHeader(JPanel header) {
        
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        
        /* BRAND */
        JPanel brandSection = new JPanel();
        brandSection.setBackground(Color.white);
        
        JLabel brandImage = new JLabel();
        URL imgURL = getClass().getResource("../images/logo.png");
        brandImage.setIcon(new ImageIcon(imgURL));
        
        brandSection.add(brandImage);
        /* END BRAND */
        
        /*CONTROLS*/
        JPanel controlSection = new JPanel();
        controlSection.setBackground(Color.orange);
        
        controlSection.setLayout(new BoxLayout(controlSection, BoxLayout.X_AXIS));
        
        /* HOME CONTROL */
        JPanel homeControl = new JPanel();
        homeControl.setLayout(new BoxLayout(homeControl, BoxLayout.Y_AXIS));
        JLabel homeTitle = new JLabel("Home");
        URL homeURL = getClass().getResource("../images/home.png");
        JLabel homeIcon = new JLabel(new ImageIcon(homeURL));
        homeControl.add(homeTitle);
        homeControl.add(homeIcon);
        /* END HOME CONTROL */
        
        /* HISTORY CONTROL */
        JPanel historyControl = new JPanel();
        historyControl.setLayout(new BoxLayout(historyControl, BoxLayout.Y_AXIS));
        JLabel historyTitle = new JLabel("History");
        URL historyURL = getClass().getResource("../images/history.png");
        JLabel historyIcon = new JLabel(new ImageIcon(historyURL));
        historyControl.add(historyTitle);
        historyControl.add(historyIcon);
        /* END HISTORY CONTROL */
        
        /* HELP CONTROL */
        JPanel helpControl = new JPanel();
        helpControl.setLayout(new BoxLayout(helpControl, BoxLayout.Y_AXIS));
        JLabel helpTitle = new JLabel("Admin");
        URL helpURL = getClass().getResource("../images/help.png");
        JLabel helpIcon = new JLabel(new ImageIcon(helpURL));
        helpControl.add(helpTitle);
        helpControl.add(helpIcon);
        /* END HELP CONTROL */
        
        /* ABOUT CONTROL */
        JPanel aboutControl = new JPanel();
        aboutControl.setLayout(new BoxLayout(aboutControl, BoxLayout.Y_AXIS));
        JLabel aboutTitle = new JLabel("Admin");
        URL aboutURL = getClass().getResource("../images/about.png");
        JLabel aboutIcon = new JLabel(new ImageIcon(aboutURL));
        aboutControl.add(aboutTitle);
        aboutControl.add(aboutIcon);
        /* END ABOUT CONTROL */
        
        controlSection.add(homeControl);
        controlSection.add(historyControl);
        controlSection.add(helpControl);
        controlSection.add(aboutControl);
        /* END CONTROLS */
        
        header.add(brandSection);
        header.add(controlSection);
        
    }
}
