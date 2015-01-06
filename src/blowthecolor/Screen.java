/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blowthecolor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen{
    public final int width;
    public final int height;
    
    private BufferedImage menu_background;
    
    
    public Screen(int width, int height) throws IOException{
        this.width  = width;
        this.height = height;
        
        //załadowanie obrazów
        menu_background = ImageIO.read(new File("images/black.png"));
    }
    public void render(Game game, Graphics g) throws IOException{
        //wyswietlanie
        if(Game.state == Game.gameState.HOLD){
            g.drawImage(menu_background, 0, 0, null);
            
            
            
        }
    }
}