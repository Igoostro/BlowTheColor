/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blowthecolor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author iostrows
 */
public final class ProfilesPanel extends JPanel implements MouseListener, MouseMotionListener {
    public int activeSlot;
    
    private final static int WIDTH  = 800;
    private final static int HEIGHT = 400;
    
    public Profiles[] profiles = new Profiles[3]; // po bugu zmienic na private
    
    //Profiles panel appeareance
    public ProfilesPanel() {
        Dimension panel_size = new Dimension(WIDTH, HEIGHT);
        
        setPreferredSize(panel_size);
        setMinimumSize  (panel_size);
        setMaximumSize  (panel_size);
        setLayout       (null);
        
        setBounds       (LoadPanel.WINDOW_WIDTH/2 - (WIDTH/2), LoadPanel.WINDOW_HEIGHT/2 - (HEIGHT/2), WIDTH, HEIGHT);
        loadProfiles    (0);
        loadProfiles    (1);
        loadProfiles    (2);
        setVisible      (true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon("images/profilespanel.png").getImage();
        g.drawImage(img, 0, 0, null);
    }
    
    //Load profile names
    void loadProfiles(int id) {
        this.add(profiles[id] = new Profiles(id+1));
        profiles[id].addMouseListener(this);
        profiles[id].addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object action = e.getSource();
        if (action == profiles[0]) {
            if(profiles[0].getProfileName() == "")
                profiles[0].createProfile(); //IMPLEMENT
            else{
                BlowTheColor.frame.remove    (BlowTheColor.loadwindow);
                BlowTheColor.frame.add       (BlowTheColor.gamewindow);
                BlowTheColor.gamewindow.start();
                BlowTheColor.frame.revalidate();
                
                activeSlot = 0;
            }
        }
        else if (action == profiles[1]) {
            if(profiles[1].getProfileName() == "")
                profiles[1].createProfile();
            else{
                BlowTheColor.frame.remove    (BlowTheColor.loadwindow);
                BlowTheColor.frame.add       (BlowTheColor.gamewindow);
                BlowTheColor.gamewindow.start();
                BlowTheColor.frame.revalidate();
                
                activeSlot = 1;
            }
        }
        else if (action == profiles[2]) {
            if(profiles[2].getProfileName() == "")
                profiles[2].createProfile();
            else{
                BlowTheColor.frame.remove    (BlowTheColor.loadwindow);
                BlowTheColor.frame.add       (BlowTheColor.gamewindow);
                BlowTheColor.gamewindow.start();
                BlowTheColor.frame.revalidate();
                
                activeSlot = 2;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object action = e.getSource();
        if (action == profiles[0])
            profiles[0].hoverProfile();
        else if (action == profiles[1])
            profiles[1].hoverProfile();
        else if (action == profiles[2])
            profiles[2].hoverProfile();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object action = e.getSource();
        if (action == profiles[0])
            profiles[0].normalProfile();
        else if (action == profiles[1])
            profiles[1].normalProfile();
        else if (action == profiles[2])
            profiles[2].normalProfile();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
