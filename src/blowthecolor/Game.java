/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blowthecolor;

/**
 *
 * @author dell
 */
class Game {
    
    public enum gameState{
        HOLD,
        LVL1
    };
    public static gameState state;

    public Game() {
        //Start od Menu
        state = gameState.HOLD; 
    }
    public void tick(){
        if(state == gameState.HOLD){
            
        }
    }
}
