/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blowthecolor;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author dell
 */
public class BlowTheColor extends Canvas implements Runnable{
    public static JFrame       frame;
    public static JPanel       loadwindow;
    public static BlowTheColor gamewindow;
    
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    
    private int  fps;
    private long fpsStart;
    
    private static Dimension size;
    
    private boolean running;
    private Thread  thread;
    
    private Screen screen;
    private Game   game;
    
    public BlowTheColor() throws IOException{
        size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        screen = new Screen(WIDTH, HEIGHT);
        game = new Game();
    }
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
        fpsStart = System.nanoTime();
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            //Logger.getLogger(MindGames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        while(running){
            //MAIN LOOP
            tick();//Game logic
            
            try {
                render();//Render
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            fps++;
            if(System.nanoTime()-fpsStart>=1000000000){
                System.out.println(fps + " FPS");
                fpsStart=System.nanoTime();
                fps=0;
            }
        }
    }
    
    private void tick() {
        game.tick();
    }
    
    private void render() throws IOException {
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        screen.render(game, g);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) throws IOException{
        loadwindow = new LoadPanel();
        gamewindow = new BlowTheColor();
        
        frame = new JFrame            ("Blow Rhe Color!");
        frame.add                     (loadwindow);
        frame.pack                    ();
        frame.setResizable            (false);
        frame.setLocationRelativeTo   (null);
        frame.setSize                 (size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible              (true);
    }
}
    

