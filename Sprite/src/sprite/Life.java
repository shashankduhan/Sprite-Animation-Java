/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import javax.swing.Timer;

/**
 *
 * @author Shashank Duhan on Abhi's iMAC
 */
public class Life {
    
    /***
     * Our sprite animation timer.
     */
    private Timer cycle;
    
    public Life(Timer t){
        cycle = t;
    }
    
    //Our simple begin and end methods
    public void begin(){
        if(cycle != null){
            cycle.start();
        }
    }
    public void end(){
        if(cycle != null){
            cycle.stop();
        }
    }
    //Simple method to set Delay of timer
    public void setmoment(int i){
        cycle.setDelay(i);
    }
}
