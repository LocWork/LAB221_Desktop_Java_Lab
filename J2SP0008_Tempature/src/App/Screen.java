/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 *
 * @author hi
 */
public class Screen extends JFrame{
    
    private JTextField inputNumber;
    private JLabel lbC;
    private JLabel lbFa;
    private JButton btn;
    DecimalFormat df = new DecimalFormat("###.##");
        Font san = new Font("Sanserif",Font.BOLD,40);
        Font san2 = new Font("Sanserif",Font.BOLD,20);
           
    public Screen(){
        initcomponent();
        

      
       
    }
    
    public void initcomponent(){
        Container con = getContentPane();
        con.setLayout(new GridLayout(2, 2));
     
        inputNumber = new JTextField();
        inputNumber.setHorizontalAlignment(JTextField.CENTER);
        inputNumber.setFont(san); 
        
        lbC = new JLabel("Celsius");
     
       
        
        btn = new JButton("");
        btn.setSize(120,30);
      
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sf) {
                click(sf);
            }
        });
        lbFa = new JLabel("Fahrenheit");
       
        
        lbC.setFont(san2);
        lbC.setHorizontalAlignment(JLabel.CENTER);
        lbFa.setFont(san2);
        lbFa.setHorizontalAlignment(JLabel.CENTER);        
        lbFa.setForeground(Color.RED);        
        
        con.add(inputNumber);
        con.add(lbC);
        con.add(btn);
        con.add(lbFa);  
    }
    
    public void click(ActionEvent sf){
        String celsius = inputNumber.getText();
        if(validator(celsius)){
            String degree = converter(Double.parseDouble(celsius));
            lbFa.setText(degree+"°"+" Fahrenheit");
        }else{
            lbFa.setText("U/N Fahrenheit");
        }
        
       
    }
    
    public String converter(double x){
        String result = "";
        double cal;
        cal = (x * 9/5)+32;
        result = df.format(cal);
        return result;
    }
    public boolean validator(String x){
        String rex = "\\-?[0-9.]+";
        String rex2 = "\\+?[0-9.]+";
        try {
            if(!x.matches(rex) && !x.matches(rex2))throw new Exception();
            return true;
        } catch (Exception e) {
            return false;
        }
    }    
    
    public static void main(String[] args) {
        Screen sc = new Screen();
        sc.setSize(700, 700);
        sc.setDefaultCloseOperation(EXIT_ON_CLOSE);
        sc.setResizable(true);
        sc.setVisible(true);          
    }
    
}
