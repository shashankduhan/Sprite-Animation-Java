/*
 * Enviornment Background Credits: Matt Sanz
 *                                - https://www.behance.net/MattSanz

 * Massey University | Albany
 * Semester Two | 2016
 * 159.235
 * Shashank Duhan
 */
package sprite;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 *
 * @author Shashank Duhan
 */
public class Sprite implements Animator, KeyListener{
    
    //Our variable declaration
    public static BufferedImage sprite;
    public static BufferedImage background;
    public static BufferedImage infoGraphic;
    public static InfoBanner tourGuide;
    public static int action_number = 1;
    public static int animation_number = 1;
    public static String bifuDirection = "left";//Bifu is our main character.
    public static int bifuPosition = 10; //Initial position of Bifu on x axis.
    public static boolean walking = false;
    public Frame frame;
    public Panel whiteBoard;
    protected Animation Anim;
    private boolean valid = false;
    protected Life life;
    private int moment = 1;
    private boolean firstFrame = true;
    private int tourStep = 1;
    
    
    
    public Sprite(){
        frame = new Frame("Bifu");
        frame.container.setLayout(new BorderLayout());
        frame.setResizable(false);
        whiteBoard = new Panel(new Rectangle(0,0,1200,497));
        frame.container.add(whiteBoard, BorderLayout.PAGE_START);


        try{
          sprite = ImageIO.read(new File("resource/skeleton-sprite.png"));
          background = ImageIO.read(new File("resource/enviornmentOne.jpg"));
          infoGraphic = ImageIO.read(new File("resource/bifu_intro.png"));
        }catch(IOException e){
          System.out.println(e);
        }
        
        tourGuide = new InfoBanner(0, 0,this);
        
        frame.addKeyListener(this);
        frame.rebuild();
    }

    

    
    public enum Animation{
        BIRTH (10), 
        IDLE (6), 
        WALK_LEFT (8), 
        WALK_RIGHT (8), 
        DEATH (8), 
        HIT (8);
        
        //We are using actionCount to store number of sprite in an action
        public int actionCount;

        Animation(int actionCount){
            this.actionCount = actionCount;
        }
    }
    

    @Override
    public void animateSprite() {
        //Let's redraw our image
        whiteBoard.repaint();
        //And change properties for our next frame
        if(!firstFrame){
          //START NOT first frame
         //If this is not first frame of an action
            if(action_number < Anim.actionCount){
                 action_number++;
            }else{
                //if an action completes its one cycle
                if(Anim == Animation.DEATH){
                     life.end();
                     life = null;
                }else if(walking){
                    action_number = 1;
                    firstFrame = true;
                    animation_number = 3;
                }
                else{
                    //put character on IDLE animation loop 
                    //if no input is given
                     Anim = Animation.IDLE;
                     action_number = 1;
                     animation_number = 2;
                     firstFrame = true;
                }

            }
            
         //END NOT first frame
        }else{firstFrame = false;}
        
    }

   
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Translate t;//used for tour guide
        //Our methods for different key inputs
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                //Begin our character's new life
                if(life == null){
                    Anim = Animation.BIRTH;
                    animation_number = 1;
                    life = new Life(new Timer((60*moment), new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            animateSprite();
                            
                        }
                        
                    }));
                    life.begin();
                    //For initializing tour
                    //called only once
                    if(tourStep == 1){
                        t = new Translate(tourGuide, "^", 0, 300, whiteBoard);
                        t.start();
                        tourStep++;
                    }
                    
                    valid = true;//This makes this cmd a valid action rebooter
                                 //means now sprite will start from beginning
                }
                 
                
                break;
            case KeyEvent.VK_DOWN:
                if(life != null && Anim != Animation.DEATH){
                    Anim = Animation.DEATH;
                    animation_number = 4;
                    valid = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                Anim = Animation.WALK_LEFT;
                animation_number = 3;
                valid = false;
                walking = true;
                bifuDirection = "left";
                break;
            case KeyEvent.VK_RIGHT:
                Anim = Animation.WALK_RIGHT;
                animation_number = 3;
                valid = false;
                walking = true;
                bifuDirection = "right";
                break;
            case KeyEvent.VK_SPACE:
                if(Anim != Animation.HIT){
                    Anim = Animation.HIT;
                    animation_number = 5;
                    if(tourStep == 2){
                        t = new Translate(tourGuide, "^", 0, 300, whiteBoard);
                        t.start();
                        tourStep++;
                    }
                    
                    valid = true;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                life.end();
                life = null;
                break;
        }
        
        if(valid){//Action rebooter if this is a valid input
            action_number = 1;
            valid = false;
            firstFrame = true;
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Used for continuos walking action
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                walking = false;
                break;
            case KeyEvent.VK_RIGHT:
                walking = false;
                break;
        }
    }
    
    
    public void changeInfoGraphic(BufferedImage img){//related to tour guide
        this.infoGraphic = img;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("--------------------------------------");
        System.out.println("159.235 Assignment 2, Semseter 2 2016");
        System.out.println("Submitted by: Shashank Duhan, 16120197");
        System.out.println("--------------------------------------");
        new Sprite();
    }
}
