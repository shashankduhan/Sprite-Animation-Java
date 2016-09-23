/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Shashank Duhan
 * Our simple Translator Object
 */
public class Translate {
    
    private Animatable parcel;
    private String direction;// Use ^ or UP and v or DOWN
    private int stopAtX;
    private int stopAtY;
    private Panel panel;
    private Timer timer;
    private int speed = 5;
    private int stepSize = 5;
    
    //It takes an object that implements Animatable interface
    public Translate(Animatable obj, String dir, int stpAtX, int stpAtY, Panel panl){
        this.parcel = obj;
        this.direction = dir;
        this.stopAtX = stpAtX;
        this.stopAtY = stpAtY;
        this.panel = panl;
        
        timer = new Timer(this.speed, new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            animate();
                            
                        }
                        
                    });
        
    }
    
    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    //Our Animate method
    //not complete for a Translator
    //Only specified for our project needs
    public void animate(){
        
        if(direction.equals("^") || direction.equals("UP")){
            //Animate Upwards                    
            if((stopAtY - Math.abs(parcel.getY1())) > 0){//It means parcel still away from destination
                parcel.setY1(parcel.getY1() - stepSize);
                panel.repaint();
            }else{
                
                stop();
                parcel.afterEffects();
            }
            
                                
        }else if(direction.equals("v") || direction.equals("DOWN")){
            //Animates Downwards    
            if((stopAtY + parcel.getY1()) < 0){
                parcel.setY1(parcel.getY1() + stepSize);
                panel.repaint();
            }else{
                stop();
                parcel.afterEffects();
            }
            
        }else{
            this.stop();
        }
    }
    
}
