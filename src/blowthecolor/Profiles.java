/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blowthecolor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author iostrows
 */
public class Profiles extends JPanel implements ActionListener {

    private enum status{
        NORMAL,
        HOVER
    };
        
    private final static int WIDTH  = 200;
    private final static int HEIGHT = 200;
    
    private String      name;
    private int         id;
    private status      status;
    
    private JFrame      newProfile;
    private JButton     newProfileOK;
    private JTextField  newProfileName;
    private JLabel      profileName;
    
    private ImageIcon normalIcon;
    private ImageIcon hoverIcon;
    
    public Profiles(int id){
        this.id = id;
        status  = status.NORMAL;
        
        Dimension size = new Dimension(WIDTH, HEIGHT);
        
        setPreferredSize(size);
        setMinimumSize  (size);
        setMaximumSize  (size);
        setLayout       (null);
        setBounds       (80+(this.id-1)*220, 100, WIDTH, HEIGHT);
        
        loadProfileName (id);
        
        if(this.name=="")
            profileName = new JLabel("NOWY", SwingConstants.CENTER);
        else{
            profileName = new JLabel(this.name, SwingConstants.CENTER);
            DelProfiles del = new DelProfiles(WIDTH, HEIGHT);
        }
        profileName.setBounds    (0, 75, 200, 50);
        profileName.setFont      (new Font("Berlin Sans FB", Font.PLAIN, 20));
        profileName.setForeground(Color.LIGHT_GRAY);
        
        normalIcon = new ImageIcon("images/profiles.png");
        hoverIcon = new ImageIcon("images/profiles_hover.png");
        
        this.add  (profileName);
        setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img;
        if(status == status.NORMAL)
            img = normalIcon.getImage();
        else if(status == status.HOVER)
            img = hoverIcon.getImage();
        else
            img = null;
        g.drawImage(img, 0, 0, null);
    }
    
    private void loadProfileName(int id){
        File file = new File("Profile" + id + ".txt");
        //LOAD
        try {
            if(file.exists()) {
            
                Scanner input = new Scanner(file);
                this.name = input.nextLine();
                //DEBUG
                System.out.println("Znaleziono: " + "Profile" + id + ".txt" + " Imie: " + this.name);
            }
            else
                this.name = "";
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void hoverProfile() {
        status = status.HOVER;
        this.repaint();
    }
    
    public void normalProfile() {
        status = status.NORMAL;
        this.repaint();
    }
    
    public void createProfile() {
        newProfile = new JFrame            ("Nowy profil!");
        newProfile.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newProfile.setLayout               (new FlowLayout());
        newProfile.setAlwaysOnTop          (true);
        newProfile.setUndecorated          (true);
        
        newProfileName = new JTextField("", 12);
        newProfileName.setPreferredSize(new Dimension(200, 30));
        
        newProfileOK = new JButton    ("OK");
        newProfileOK.addActionListener(this);
        newProfile.add                (newProfileName);
        newProfile.add                (newProfileOK);
        
        newProfile.pack       ();
        newProfile.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - newProfile.getWidth())/2,
                               (int)((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - newProfile.getHeight())/1.5));
        newProfile.setVisible  (true);
    }
    public void deleteProfile() {
        
    }
    
    public String getProfileName() {
        return this.name;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if(action == newProfileOK) {
            if (newProfileName.getText().equals(""))
                profileName.setText("NOWY");
            else {
                profileName.setText(newProfileName.getText());
                this.name = newProfileName.getText();
                
                //Create txt file
                File file = new File("Profile" + id + ".txt");
                try {
                    if(file.createNewFile()) {
                            FileWriter stream = new FileWriter(file);
                            stream.write(name);
                            
                            stream.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
            newProfile.dispose();
        }
    }
}
