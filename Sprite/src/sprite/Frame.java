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
 */
//Our Simple Frame Class
public class Frame extends JFrame{
    Container container;

    public Frame(){
      super("Title");
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      container = getContentPane();
      pack();
      setVisible(true);
    }
    public Frame(String title){
      super(title);
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      container = getContentPane();
      pack();
      setVisible(true);
    }

    public void rebuild(){
      this.pack();
      this.setVisible(true);
    }
}
