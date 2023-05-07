import java.rmi.Naming;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
public class PhoneDirClient extends   JFrame {

    /**
     * Creates new form PhoneDirClient
     */
    public PhoneDirClient() {
        super("Phone Directory");
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        txtName = new JTextField();
        txtPhoneNo = new JTextField();
        btnRegister = new JButton();
        txtSearchPhone = new JTextField();
        jLabel4 = new JLabel();
        btnSearch = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        JButton btnView = new JButton();
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 0, 48));
        jLabel1.setText("Phone Directory");
        jLabel1.setBounds(80, 10, 450, 50);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Contact Name:");
        jLabel2.setBounds(20, 80, 170, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setText("Phone Number:");
        jLabel3.setBounds(20, 120, 180, 25);

        txtName.setBounds(170, 80, 250, 25);
        txtPhoneNo.setBounds(170, 120, 250, 25);

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnRegister.setText("Register");
        btnRegister.setBounds(20, 200, 110, 25);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        btnView.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnView.setText("View All Contacts");
        btnView.setBounds(100,250, 300, 25);
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	btnViewActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel4.setText("Enter Phone to Search:");
        jLabel4.setBounds(20, 160, 250, 25);

        txtSearchPhone.setBounds(240, 160, 170, 25);

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnSearch.setText("Search");
        btnSearch.setBounds(380, 200, 100, 25);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnUpdate.setText("Update");
        btnUpdate.setBounds(140, 200, 110, 25);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnDelete.setText("Delete");
        btnDelete.setBounds(260, 200, 110, 25);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        // Set the layout of the JPanel to null
        jPanel1.setLayout(null);

        // Add the components to the panel
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(txtName);
        jPanel1.add(txtPhoneNo);
        jPanel1.add(btnRegister);
        jPanel1.add(jLabel4);
        jPanel1.add(txtSearchPhone);
        jPanel1.add(btnSearch);
        jPanel1.add(btnUpdate);
        jPanel1.add(btnDelete);
        jPanel1.add(btnView);

        // Set the size of the JPanel
        jPanel1.setPreferredSize(new Dimension(500, 400));

        // Set the layout of the content pane to BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Add the JPanel to the center of the content pane
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        // Pack the frame and make it visible
        pack();
        setVisible(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed( ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        try {
            String contactName = txtName.getText();
            String phoneNo = txtPhoneNo.getText();

            RMIInterface ri = (RMIInterface) Naming.lookup("rmi://localhost:8081/x");
            String res = ri.addContact(contactName, phoneNo);

            if (res.equals("1")) {
                JOptionPane.showMessageDialog(this, "Contact has been Registered!", "Phone Dir: Contact Registered.", JOptionPane.INFORMATION_MESSAGE);
            } else if (res.equals("0")) {
                JOptionPane.showMessageDialog(this, "Contact already Exist!", "Phone Dir: Contact Exist.", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnSearchActionPerformed( ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        ArrayList array = new ArrayList();
        try {
            // String phoneNo = txtSearchPhone.getText();
            String contactName = txtSearchPhone.getText();
            RMIInterface ri = (RMIInterface) Naming.lookup("rmi://localhost:8081/x");
            for (int i = 0; i < 2; i++) {
                array = ri.searchContact(contactName);
            }
            if (!array.isEmpty()) {
                txtName.setText(array.get(1).toString());
                txtPhoneNo.setText(array.get(0).toString());
            } else if (array.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No record found.", "Phone Dire: No Record Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed( ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String contactName = txtName.getText();
            String phoneNo = txtPhoneNo.getText();
            RMIInterface ri = (RMIInterface) Naming.lookup("rmi://localhost:8081/x");
            String result = ri.updateContact(contactName, phoneNo);

            if (result.equals("1")) {
                JOptionPane.showMessageDialog(this, "Contact Updated Successfully!", "Tele Dir: Contact Updated.", JOptionPane.INFORMATION_MESSAGE);

            } else if (result.equals("0")) {
                JOptionPane.showMessageDialog(this, "Contact Doesn't Exist!", "Tele Dir: No Contact.", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed( ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            String contactName = txtName.getText();
            
            RMIInterface ri = (RMIInterface) Naming.lookup("rmi://localhost:8081/x");
            String result = ri.deleteContact(contactName);

            if (result.equals("1")) {
                JOptionPane.showMessageDialog(this, "Contact Deleted Successfully!", "Tele Dir: Contact Deleted.", JOptionPane.INFORMATION_MESSAGE);

            } else if (result.equals("0")) {
                JOptionPane.showMessageDialog(this, "Contact Doesn't Exist!", "Tele Dir: No Contact.", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     * private 
     */
    private void btnViewActionPerformed(ActionEvent evt) {
    	ViewAllContacts view=new ViewAllContacts();
        view.setVisible(true);
        setVisible(false);
        
    }
    public static void main(String args[]) {
        
        new PhoneDirClient().setVisible(true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private   JButton btnDelete;
    private   JButton btnRegister;
    private   JButton btnSearch;
    private   JButton btnUpdate;
    private   JLabel jLabel1;
    private   JLabel jLabel2;
    private   JLabel jLabel3;
    private   JLabel jLabel4;
    private   JPanel jPanel1;
    private   JTextField txtName;
    private   JTextField txtPhoneNo;
    private   JTextField txtSearchPhone;
    // End of variables declaration//GEN-END:variables
}