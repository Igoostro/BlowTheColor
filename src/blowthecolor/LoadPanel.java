/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blowthecolor;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author iostrows
 */
public class LoadPanel extends JPanel {
    
    public final static int WINDOW_WIDTH  = 1024;
    public final static int WINDOW_HEIGHT = 768;
    
    private static ProfilesPanel loader;

    
    //Frame appearance
    public LoadPanel() {
        Dimension window_size = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout       (null);
        setPreferredSize(window_size);
        setMinimumSize  (window_size);
        setMaximumSize  (window_size);
        
        //DEBUG
        System.out.println("Frame: " + getWidth() + " " + getHeight());
        
        
        loader = new ProfilesPanel();
        this.add(loader);
        this.repaint();
        
        setVisible(true);
    }
    
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image img = new ImageIcon("images/menu_background.JPG").getImage();
            g.drawImage(img, 0, 0, null);
        }
}
