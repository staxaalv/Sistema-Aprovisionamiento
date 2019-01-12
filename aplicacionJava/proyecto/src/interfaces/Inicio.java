/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.ConectorDB;
import clases.Consulta;
import clases.PE;
import clases.SSHConnector;
import clases.User;
import com.jcraft.jsch.JSchException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class Inicio extends javax.swing.JFrame {
    public static ArrayList<PE>pes=new ArrayList<PE>();
    ConectorDB conDB;
    Consulta consulta;
    User user;
    PE pe;
    SSHConnector sshConnector;
    Administracion adminForm;
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        InicializaComboBoxPE();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ROUTER");

        jLabel2.setText("USUARIO");

        jLabel3.setText("CONTRASEÑA");

        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2)
                    .addComponent(jComboBox1, 0, 112, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (!jComboBox1.getSelectedItem().toString().equals("Seleccione")) {
                if (!(jTextField1.getText().equals("")) && !(jTextField2.getText().equals(""))) {
                    switch (validaruser(jTextField1.getText(), jTextField2.getText())) {
                        case 1:
                            sshConnector = new SSHConnector();
                            try {
                                System.out.println(pe.toString());
                                sshConnector.connect(user, pe, 22);
                                sshConnector.executeCommand2("show run\n       ");
                                //String result = sshConnector.executeCommand("show run\n       ");
                                adminForm=new Administracion();
                                adminForm.setPE(pe);
                                adminForm.setUser(user);
                                adminForm.setConectorDB(conDB);
                                adminForm.setSSHConector(sshConnector);
                                adminForm.setjLabelRouter(pe.getNombre());
                                adminForm.setVisible(true);
                                this.setVisible(false);
                                sshConnector.disconnect();
                                //if(sshConnector.getSesion().isConnected()){sshConnector.disconnect();}
                                
                            } catch (JSchException E) {
                                JOptionPane.showMessageDialog(null, "problemas de conexion SSH, intentelo mas tarde");
                            }
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta inténtelo de nuevo");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "La contraseña es Incorrecta");
                            break;

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Falta información, inténtelo de nuevo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falta información, inténtelo de nuevo");
            }

        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        for(PE p:pes){
            if (jComboBox1.getSelectedItem().toString().equals(p.getNombre())){
                this.pe=new PE(p.getNombre(), p.getCiudad(), p.getDireccionIP());
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public void InicializaComboBoxPE() {
        try {
            conDB = new ConectorDB();
            Connection reg = conDB.getConnection();
            consulta = new Consulta();
            ResultSet rs = consulta.getResultSetTabla(reg,"providerEdge");
            if (rs != null) {
                while (rs.next()) {
                    pes.add(new PE(rs.getString("nombrePE"),rs.getString("ciudad"), rs.getString("direccionIP")));
                    jComboBox1.addItem(rs.getString("nombrePE"));
                }
            } else {
                System.out.println("ERROR");
            }
            rs.close();
            reg.close();
            conDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int validaruser(String user, String pass) {
        int op = -1;
        try {
            conDB = new ConectorDB();
            Connection reg = conDB.getConnection();
            consulta = new Consulta();
            ResultSet rs = consulta.getResultSetTabla(reg,"user");
            if (rs != null) {
                while (rs.next()) {

                    if (user.equals(rs.getString("usuario"))) {
                        if (pass.equals(rs.getString("contrasena"))) {
                            this.user = new User(user, pass, Integer.parseInt(rs.getString("privilegio")));
                            rs.close();
                            reg.close();
                            conDB.desconectar();
                            return 1;
                        }
                        op = 0;
                    }

                }
            } else {
                System.out.println("ERROR");
            }
            rs.close();
            reg.close();
            conDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return op;
    }
}