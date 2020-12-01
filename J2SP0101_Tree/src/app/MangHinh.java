/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import sun.util.locale.StringTokenIterator;

/**
 *
 * @author hi
 */
public class MangHinh extends JFrame{
    
    JButton btnDelete;
    JTree tree;
    JTextField txtAdd;
    String fileName = "nodeData2.txt";    
    DefaultMutableTreeNode root;  
    DefaultMutableTreeNode currentFatherNode;
    DefaultMutableTreeNode currentSonNode;
    boolean isFater = false;
    boolean isSon = false;
    boolean isRoot = false;
    public MangHinh(){
        initconponent();
    }
    
    public void initconponent(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout(0,5));
        
        
        
        Font theFont = new Font("SansSerif", Font.PLAIN, 30);
        txtAdd = new JTextField();
        txtAdd.setPreferredSize(new Dimension(WIDTH, 60));
        txtAdd.setFont(theFont);

        
        
        root = new DefaultMutableTreeNode("RootNode");
        loadData();
        tree = new JTree(root);
      
        
        btnDelete = new JButton("Select node and click here to remove it!");
        btnDelete.setFont(theFont);
        btnDelete.setPreferredSize(new Dimension(WIDTH, 60));
        
        
        
        
        ActionListener action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               addNewNode();
//               saveFile();
            }
        };
        txtAdd.addActionListener(action);

        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                treeClick();
            }
        });
                
        
        ActionListener delAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               removeNode();
            }
        };
        btnDelete.addActionListener(delAction);
        
        con.add(txtAdd, BorderLayout.NORTH);
        con.add(tree, BorderLayout.CENTER);
        con.add(btnDelete, BorderLayout.SOUTH);
    }
    
    public void testPrint(){
        JOptionPane.showMessageDialog(this, currentFatherNode+" "+currentSonNode);
    }
    
    public void loadData(){
        String line = "";
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            while((line = br.readLine()) != null){
                boolean isFather = line.charAt(line.length() - 1) == ':';
                StringTokenIterator token = new StringTokenIterator(line, ":"); 
                if(isFather){
                                       
                    currentFatherNode = new DefaultMutableTreeNode(token.first());
                    root.add(currentFatherNode);
                }else{
                    currentSonNode = new DefaultMutableTreeNode(line);
                    currentFatherNode.add(currentSonNode);
                }
            }
            
        } catch (IOException e) {
        }
    }
    
//    public void saveFile(){
//        if(root.getChildCount() == 0)return;
//        String S = "";
//      try {
//            FileWriter f = new FileWriter(fileName);
//            PrintWriter pf = new PrintWriter(f);
//            Enumeration depts = root.children();
//            while(depts.hasMoreElements()){
//                DefaultMutableTreeNode fatherNode = (DefaultMutableTreeNode) depts.nextElement();
//          
//                S = fatherNode.toString()+":";
//                pf.println(S);
//                Enumeration depts2 = fatherNode.children();
//                while(depts2.hasMoreElements()){
//                    DefaultMutableTreeNode sonNode = (DefaultMutableTreeNode) depts2.nextElement();
//                    Enumeration deptsMore = sonNode.children();
////                    if(deptsMore.hasMoreElements()){
////                        moreSave(S, fatherNode, pf);
////                    }
//                    S = sonNode.toString();
//                    pf.println(S);
//                }
//            }
//            pf.close();f.close();
//            JOptionPane.showMessageDialog(this, "Data are saved to the file "+ fileName);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//        }               
//    }
    
//    public void moreSave(String S, DefaultMutableTreeNode fatherNode,PrintWriter pf){
//        try {      
//                Enumeration depts3 = fatherNode.children();
//                while(depts3.hasMoreElements()){
//                    DefaultMutableTreeNode sonNode = (DefaultMutableTreeNode) depts3.nextElement();
//                    Enumeration deptsMore = sonNode.children();
//                        if(deptsMore.hasMoreElements()){
//                            moreSave(S, fatherNode, pf);
//                        }                        
//                    S = sonNode.toString();
//                    pf.println(S);
//                }            
//        } catch (Exception e) {
//        }
//                    
//    }
    
    public void treeClick(){
        tree.setEditable(false);        
        TreePath path = tree.getSelectionPath();

        if(path == null){
            return;
        }
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
        int numberOfChild = selectedNode.getChildCount();
        if(root != null){
        if(selectedNode == root){
            currentFatherNode = currentSonNode= root;
            
            isRoot = true;
            isFater = false;
            isSon = false;
        }else{
            if(numberOfChild > 0){
                currentFatherNode = selectedNode;
                currentSonNode = null;
                isFater = true;
                isSon = false;
                isRoot = false;
            }else{
                currentFatherNode = (DefaultMutableTreeNode) selectedNode.getParent();
                currentSonNode = selectedNode;
                isSon = true;
                isFater = false;
                isRoot = false;
            }
        }             
        }
        
    }
    
    public void addNewNode(){
        String x = txtAdd.getText();
        if(tree.getSelectionPath() == null && root != null){
            JOptionPane.showMessageDialog(this, "You have not choosen any node");
            return;
        }         
        if(root == null){
            root = new DefaultMutableTreeNode(x);
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.setRoot(root);
        }else{
            if(validateString(x) == false){
                return;
            }
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(x);
        
            if(isRoot){
                root.add(newNode);
            }else if(isFater){
                currentFatherNode.add(newNode);
                
            }else if(isSon){
                currentSonNode.add(newNode);           
            }
        } 
       

        txtAdd.setText("");
        this.tree.updateUI();
    }
   
    public void removeNode(){
        if(tree.getSelectionPath() != null){
        TreePath path = tree.getSelectionPath();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        int numberOfChild = selectedNode.getChildCount();
        if(numberOfChild == 0){
            if(selectedNode != root){
                if(isSon){
                    if(JOptionPane.showConfirmDialog(this,"Are you sure","Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        currentFatherNode.remove(this.currentSonNode);
                    }                    
                }else{
                    root.remove(this.currentSonNode);
                }
            }else{
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                root = (DefaultMutableTreeNode) model.getRoot();
                root.removeAllChildren();
                root = null;
                model.reload();
                model.setRoot(null);        
            }
        }else{
            JOptionPane.showMessageDialog(this, "There is still item in here");
        }
        

        tree.updateUI();
        }else{
            JOptionPane.showMessageDialog(this, "Please choose a node");
        }
    }
    
    public boolean validateString(String x){
        if(tree.getSelectionPath() != null){
           TreePath path = tree.getSelectionPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            for(int i = 0; i< selectedNode.getChildCount();i++){
                if(selectedNode.getChildAt(i).toString().equals(x)){
                    JOptionPane.showMessageDialog(this, "Don't add the same item");
                    return false;
                }
            }            
        }
        
        

        if(x.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please write something");
            return false;
        }
        if(x.trim().length() == 0){
            JOptionPane.showMessageDialog(this, "Don't add blank");
            return false;
        }
        if(x.charAt(x.length() -1) == ':'){
            JOptionPane.showMessageDialog(this, "Don't put : at the end");
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        MangHinh mh = new MangHinh();
        mh.setVisible(true);
        mh.setSize(900, 700);
        mh.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
}
