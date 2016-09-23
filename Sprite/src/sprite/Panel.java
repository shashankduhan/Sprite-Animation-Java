/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Shashank Duhan
 * 
 * Our Simple Panel Class using JPanel
 */
public class Panel extends JPanel{
    public Panel(){
    super(null);
    setPreferredSize(new Dimension(500, 500));
  }
  public Panel(int x, int y){
    super(null);
    setPreferredSize(new Dimension(x, y));
    setOpaque(false);
  }
  public Panel(Rectangle bounds){
    super(null);
    setOpaque(false);
    setBounds(bounds);
    setPreferredSize(new Dimension(bounds.width, bounds.height));
  }


  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    g2.setBackground(Color.white);
    g2.clearRect(0,0, getWidth(), getHeight());
    
    
    
    if(Sprite.sprite != null){
      
       g2.drawImage(Sprite.background, 0,0, null);
       //Our tourGuide (infoGraphic)
       int idx1 = Sprite.tourGuide.getX1();
       int idy1 = Sprite.tourGuide.getY1();
       g2.drawImage(Sprite.infoGraphic, idx1, idy1, null);
       
       // Our Character Rendering
       //img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer
      int x = Sprite.action_number; 
      int y = Sprite.animation_number;
      
      int x1, x2;//For sprite cropping coordinates.
      //------------------------
      //** Flipping our Character Left/Right
      //------------------------
      if(Sprite.bifuDirection.equals("left")){
          x1 = (x-1)*256;
          x2 = x*256;
      }else{
          x1 = x*256;
          x2 = (x-1)*256;
      }
      //--------------------------
      //** Change the Position of character if in walking state.
      //--------------------------
      int a = Sprite.bifuPosition;
      int footSize = 6;
      if(Sprite.walking){
          if(Sprite.bifuDirection.equals("left")){
              a = a-footSize;
              if(a < -200){ a = 1145;}
          }else{//Moving towards right
              a = a+footSize;
              if(a > 1150){a = -210;}
          }
      }
      Sprite.bifuPosition = a;
      int px1 = a;
      int px2 = a+256;
      
      
      
      g2.drawImage(Sprite.sprite, 
              px1, 500-256, px2, 500,
              x1, (y-1)*256, x2, 256*(y),
              null);
    }
  }
  


}
