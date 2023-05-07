import java.rmi.Naming;
import java.util.ArrayList;
import  javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class ViewAllContacts extends JFrame {

    /**
     * Creates new form ViewAllContacts
     */
    public ViewAllContacts() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new   JPanel();
        jLabel1 = new   JLabel();
        btnViewAll = new   JButton();
        jScrollPane1 = new   JScrollPane();
        tblContacts = new   JTable();

        setDefaultCloseOperation(  WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("All Contacts");
        jLabel1.setBounds(140, 20, 250, 40);

        btnViewAll.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnViewAll.setText("View All");
        btnViewAll.setBounds(200, 80, 120, 40);
        btnViewAll.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        tblContacts.setModel(new   javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Contact Name", "Phone Number"
            }
        ));
        jScrollPane1.setViewportView(tblContacts);
        jScrollPane1.setBounds(20, 140, 480, 220);
        jPanel1.setLayout(null);
        jPanel1.add(jLabel1);
        jPanel1.add(btnViewAll);
        jPanel1.add(jScrollPane1);
        jPanel1.setPreferredSize(new Dimension(550, 400));
        getContentPane().add(jPanel1, BorderLayout.CENTER);
        setSize(800, 600);
        setLocationRelativeTo(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed( ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();
        ArrayList array = new ArrayList();

        try {
            RMIInterface ri = (RMIInterface) Naming.lookup("rmi://localhost:8081/x");
            int total = 0;
            for (int i = 0; i < 2; i++) {
                array = ri.viewAllContacts(i);
                for (int j = 0; j < array.size(); j++) {
                    model.setValueAt(array.get(j), j, i);
                }
                total = array.size();
            }
            if (total == 0) {
                JOptionPane.showMessageDialog(this, "No Contact Found!", "Tele Directory: No Contact Exist.", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnViewAllActionPerformed

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
            for (  UIManager.LookAndFeelInfo info :   UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                      UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewAllContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAllContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAllContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (  UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAllContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAllContacts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private   JButton btnViewAll;
    private   JLabel jLabel1;
    private   JPanel jPanel1;
    private   JScrollPane jScrollPane1;
    private   JTable tblContacts;
    // End of variables declaration//GEN-END:variables
}
