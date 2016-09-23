/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

/**
 *
 * @author Shashank Duhan
 */
public abstract interface Animatable {
    //This intrface can become very diverse
    //but for the project sake I am keeping it simple
    //and to the point
    public int getX1();
    public int getY1();
    
    public void setX1(int i);
    public void setY1(int i);
    
    public void afterEffects(Object x);//Function to be called after animation
    public void afterEffects();
}
