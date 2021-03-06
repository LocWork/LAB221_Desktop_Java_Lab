package gui;


import dao.StockDAO;
import dto.StockDTO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class StocksGUI extends javax.swing.JFrame {
    DefaultTableModel stockModel;
    /**
     * Creates new form StockDTO
     */
    public StocksGUI() {
        initComponents();
        stockModel = (DefaultTableModel)tblContents.getModel();
        loadStock();
    }

    private void loadStock() {
        try {
            stockModel.setRowCount(0);
            StockDAO dao = new StockDAO();
            List<StockDTO> list = dao.getStockList();
            for (StockDTO dto : list) {
                stockModel.addRow(dto.toVector());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblContents = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblContents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "StockID", "StockName", "Address", "DateAvailable", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblContents);

        btnInsert.setText("Insert to DB");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(btnInsert)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnInsert)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        StockDAO dao = new StockDAO();
        List<StockDTO> list = new ArrayList<>();
        for (int i = 0; i < tblContents.getRowCount(); i++) {
            int id = (int) tblContents.getValueAt(i, 0);
            String name = (String) tblContents.getValueAt(i, 1);
            String address = (String) tblContents.getValueAt(i, 2);
            Date dateAvailable = null;
            try {
                dateAvailable = (Date) tblContents.getValueAt(i, 3);
            } catch (ClassCastException e) {
                    //xu li chinh sua date field
                String strDate = (String)tblContents.getValueAt(i, 3);
                StringTokenizer stk = new StringTokenizer(strDate, "-");
                int year=0, month=0, day=0;
                try {
                    year = Integer.parseInt(stk.nextToken());
                    month = Integer.parseInt(stk.nextToken());
                    day = Integer.parseInt(stk.nextToken());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Wrong date format");
                }
                dateAvailable = new Date(year - 1900, month - 1, day);
            }
            String note = (String) tblContents.getValueAt(i, 4);
            StockDTO stock = new StockDTO(id, name, address, dateAvailable, note);
            list.add(stock);
        }
        dao.updateStocks(list);
    }//GEN-LAST:event_btnInsertActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StocksGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StocksGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StocksGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StocksGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StocksGUI().setVisible(true);
            }
        });
    }
//        StockDAO dao = new StockDAO();
//        List<StockDTO> list = new ArrayList<>();
//        for (int i = 0; i < tblContents.getRowCount(); i++) {
//            int id = (int) tblContents.getValueAt(i, 0);
//            String name = (String) tblContents.getValueAt(i, 1);
//            String address = (String) tblContents.getValueAt(i, 2);
//            Date dateAvailable = null;
//            try {
//                dateAvailable = (Date) tblContents.getValueAt(i, 3);
//            } catch (ClassCastException e) {
//                    //xu li chinh sua date field
//                String strDate = (String)tblContents.getValueAt(i, 3);
//                StringTokenizer stk = new StringTokenizer(strDate + "-");
//                int year=0, month=0, day=0;
//                try {
//                    year = Integer.parseInt(stk.nextToken());
//                    month = Integer.parseInt(stk.nextToken());
//                    day = Integer.parseInt(stk.nextToken());
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(this, "Wrong date format");
//                }
//                dateAvailable = new Date(year - 1900, month - 1, day);
//            }
//            String note = (String) tblContents.getValueAt(i, 4);
//            StockDTO stock = new StockDTO(id, name, address, dateAvailable, note);
//            list.add(stock);
//        }
//        dao.updateStocks(list);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContents;
    // End of variables declaration//GEN-END:variables
}
