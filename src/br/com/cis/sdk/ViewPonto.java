/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cis.sdk;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import dao.DigitalDAO;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Digital;

/**
 *
 * @author MRMSistemas
 */
public class ViewPonto extends javax.swing.JFrame {

    /**
     * Creates new form ViewPonto
     */
    public ViewPonto() {
        initComponents();

        CisBiox biox = new CisBiox();

        if (biox.iniciar() != 1) {
            lbStatus.setText("ERRO");
        }
        lbStatus.setText("Iniciado");
        new Thread(LerDigital1).start();

    }

    private Runnable LerDigital1 = new Runnable() {
        public void run() {
            try {
                // Instanciar a DLL
                CisBiox biox = new CisBiox();

                // Capturar a digital no leitor   
                Pointer pDigital;
                IntByReference iRet = new IntByReference();
                pDigital = biox.lerDigital_RetornoPonteiro(iRet);
                int iRetorno2 = iRet.getValue();
                if (iRetorno2 != 1) {
                    biox.finalizar();

                    return;
                }
                byte[] buffer = pDigital.getByteArray(0, 669);
                System.out.println(buffer);
                DigitalDAO dao = new DigitalDAO();
                Digital d = new Digital();

                // Criar a pasta e apagar a digital anterior
                new File("C:/CIS_SDK/DIGITAIS").mkdir();
                File file = new File("C:/CIS_SDK/DIGITAIS/Template1.tpl");
                file.delete();

                // Gravar a digital em um arquivo
                File arquivo = new File("C:/CIS_SDK/DIGITAIS/Template1.tpl");

                try (FileOutputStream arq = new FileOutputStream(arquivo)) {
                    arq.write(buffer);
                    arq.flush();
                    arq.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro !!! ");
                }

                // Finalizar o SDK        
                int iRetorno = biox.finalizar();
                if (iRetorno != 1) {
                    JOptionPane.showMessageDialog(null, "Erro: " + Integer.toString(iRetorno));
                    return;
                }
                d.setDigital1(buffer);
                dao.create(d);
                JOptionPane.showMessageDialog(null, "Template gerado!");
                lbStatus.setText("Finalizado");
            } catch (HeadlessException | SQLException e) {
                System.out.println(e);
            }
        }
    ;

    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbStatus.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(lbStatus)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(lbStatus)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ViewPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPonto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
