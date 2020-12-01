/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author hi
 */
public class RGBScreen2 extends JFrame{
    JSlider sliderRed = new JSlider();
    JSlider sliderGreen = new JSlider();
    JSlider sliderBlue = new JSlider();
    JLabel txtRed;
    JLabel txtGreen;
    JLabel txtBlue;
    JPanel pannel1;
    JPanel pannel2;
    JPanel pannel3;
    int R = 0;
    int G = 0;
    int B = 0;
    public RGBScreen2(){
        initconponents();
    }
    
    public void initconponents(){
        Container con = getContentPane();
        con.setLayout(new GridLayout(1, 3));
        
        pannel1 = new JPanel(new GridLayout(3, 1));
        sliderRed.setValue(0);
        sliderGreen.setValue(0);
        sliderBlue.setValue(0);
        sliderRed.setMaximum(255);
        sliderGreen.setMaximum(255);
        sliderBlue.setMaximum(255);
        pannel1.add(sliderRed);
        pannel1.add(sliderGreen);
        pannel1.add(sliderBlue);
        
        pannel2 = new JPanel(new GridLayout(3, 1));
        txtRed = new JLabel("R: ");
        txtGreen = new JLabel("G: ");
        txtBlue = new JLabel("B: ");
        Font x = new Font("Tahoma", Font.PLAIN, 24);
        txtRed.setFont(x);
        txtGreen.setFont(x);
        txtBlue.setFont(x);
        txtRed.setForeground(Color.RED);
        txtGreen.setForeground(Color.GREEN);
        txtBlue.setForeground(Color.BLUE);
        txtRed.setHorizontalAlignment(JLabel.CENTER);
        txtGreen.setHorizontalAlignment(JLabel.CENTER);
        txtBlue.setHorizontalAlignment(JLabel.CENTER);  
        
        ChangeListener moveSlideRed = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                mixColorRed();
            }
        };
        sliderRed.addChangeListener(moveSlideRed);
        
        ChangeListener moveSlideGreen = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                mixColorGreen();
            }
        };
        sliderGreen.addChangeListener(moveSlideGreen);
        
        ChangeListener moveSlideBlue = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                mixColorBlue();
            }
        };
        sliderBlue.addChangeListener(moveSlideBlue);        
        
        pannel2.add(txtRed);
        pannel2.add(txtGreen);
        pannel2.add(txtBlue);
        pannel3 = new JPanel();
        
       
        
        
        con.add(pannel1);
        con.add(pannel2);
        con.add(pannel3);
    }
    public void mixColorRed(){
        R = sliderRed.getValue();
        txtRed.setText("R: "+Integer.toString(R));
        changeColer(R, G, B);        
    }
    public void mixColorGreen(){
        G = sliderGreen.getValue();
        txtGreen.setText("G: "+Integer.toString(G));
        changeColer(R, G, B);       
    }
    public void mixColorBlue(){
        B = sliderBlue.getValue();
        txtBlue.setText("B: "+Integer.toString(B));
        changeColer(R, G, B);         
    }    
    
    public void changeColer(int R, int G,int B){
        Color c = new Color(R, G, B);
        pannel3.setBackground(c);
    }    
    public static void main(String[] args) {
        RGBScreen2 rgb = new RGBScreen2();
        rgb.setSize(800, 270);
        rgb.setVisible(true);
        rgb.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
