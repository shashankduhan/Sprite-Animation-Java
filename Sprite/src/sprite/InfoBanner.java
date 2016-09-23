/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import java.awt.image.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.Timer;

/**
 *
 * @author Shahshak Duhan
 * 
 * This class is used for making a property container of our animating
 * tour object
 */
public class InfoBanner implements Animatable{

    private int x1;
    private int y1;
    public static int phase = 1;
    private boolean showAgain = false;
    private Sprite mainClass;
    private Translate translator;
    Timer delay;
    
    BufferedImage image;
    
    public InfoBanner(int x1, int y1, Sprite mainClass){
        this.x1 = x1;
        this.y1 = y1;
        this.mainClass = mainClass;
    }
    
    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public int getY1() {
       return y1; 
    }


    @Override
    public void setX1(int i) {
        x1 = i;
    }


    @Override
    public void setY1(int i) {
        y1 = i;
    }

    @Override
    public void afterEffects(Object x) {
        x = (String) x;
        System.out.println(x);
    }

    @Override
    public void afterEffects() {
        //There are different phases of tour.
        if(phase == 1){//Change image of our infoGraphic object
            try{
                image = ImageIO.read(new File("resource/space_button_instruction.png"));
            }catch(IOException e){
              System.out.println(e);
            }
            showAgain = true;
            phase++;
        }else if(phase == 2){
            phase++;
        }else if(phase == 3){
            try{
                image = ImageIO.read(new File("resource/enjoy.png"));
            }catch(IOException e){
              System.out.println(e);
            }
            showAgain = false;//Make it true to get a Enjoy! Banner   | Got little bug
            phase++;
        
        }else if(phase == 4){
            delay = new Timer(1200, new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            slide("^", 300);
                            delay.stop();
                        }
                        
                    });
            delay.start();
            phase++;
        }
        
        if(showAgain){
            showAgain = false;
            mainClass.changeInfoGraphic(image);
            slide("v", 0);
        }
    }
    
    //A simple slider function which uses Translate Object
    public void slide(String dir, int stopAt){
        translator = new Translate(this, dir, 0, stopAt, mainClass.whiteBoard);
        translator.start();
    }

    
    
    
}
