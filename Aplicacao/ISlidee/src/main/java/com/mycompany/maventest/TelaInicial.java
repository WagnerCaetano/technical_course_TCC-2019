/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventest;

import classes.Server;
import classes.Utils;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author u18300
 */
public class TelaInicial extends javax.swing.JFrame {
    // CAMINHOS
    String PATH_SLIDE;
    String PATH_PASTA_IMAGENS;
    // CONFIG
    Server Servidor = null;
    Tray IO;
    String IP_ADDRESS;
    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() throws AWTException, IOException {
        initComponents();
        this.setVisible(false);
        IO = new Tray(this);        
        IP_ADDRESS = getIPAddress(true);
        txtConexao.setText(IP_ADDRESS);
        Servidor = new Server();
        Servidor.receberMensagem();
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
        txtConexao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnReload = new javax.swing.JButton();
        btnEscolherSlide = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtSlidePath = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 3, 24)); // NOI18N
        jLabel1.setText("iSlidee - Manipulador de Slide");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Conexão:");

        txtConexao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtConexao.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("<html>  <head>  <p>5º Sincronize por wireless o celular com o computador seguindo as instruções.</p> \t<p>E pronto , pode utilizar o aplicativo.</p> \t</head> </html>");

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reload.png"))); // NOI18N
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        btnEscolherSlide.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEscolherSlide.setText("Escolher Arquivo");
        btnEscolherSlide.setActionCommand("Escolher_arquivo");
        btnEscolherSlide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscolherSlideActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("<html> \t<head> \t<p>Passo a passo para se conectar: </p> \t<p>1º Conecte-se ao Wifi que está conetado seu computador.</p> \t<p>2º Instale o aplicativo no celular.</p> \t<p>3º Selecione sua apresentação de slide:</p>");

        txtSlidePath.setForeground(new java.awt.Color(255, 0, 51));
        txtSlidePath.setText("Arquivo escolhido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEscolherSlide)
                                .addGap(96, 96, 96)
                                .addComponent(txtSlidePath)))
                        .addContainerGap(48, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscolherSlide)
                    .addComponent(txtSlidePath, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.getAccessibleContext().setAccessibleName("Passo a passo para se conectar: \n1º Conecte-se a um Wifi.\n2º Instale o aplicativo no celular.\n3º Sincronize com o computador.\nE pronto , pode começar a utilizar.\n");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        String ip = getIPAddress(true);
        //String msg = txtConexao.getText();
        txtConexao.setText(ip);
        /*try { Thread.sleep(5000); } catch (InterruptedException ex) { Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex); }
        txtConexao.setText(msg);*/
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnEscolherSlideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscolherSlideActionPerformed
        try {
            JFileChooser arquivo = new JFileChooser();
            FileNameExtensionFilter filtroPowerPoint = new FileNameExtensionFilter("Arquivos PowerPoint", "pptx");
            arquivo.addChoosableFileFilter(filtroPowerPoint);
            arquivo.setAcceptAllFileFilterUsed(false);
            txtConexao.setText("ESCOLHENDO SLIDE...");
            if(arquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                txtSlidePath.setText(arquivo.getSelectedFile().getAbsolutePath());
                PATH_SLIDE = arquivo.getSelectedFile().getAbsolutePath();
                txtConexao.setText("SLIDE ESCOLHIDO");
            }
            txtConexao.setText("CRIANDO LUGAR DE IMAGENS...");
            PATH_PASTA_IMAGENS = "C:\\Temp\\SLIDES";
            int qtdImg = Utils.listaSlides(PATH_SLIDE,PATH_PASTA_IMAGENS);
            txtConexao.setText("CRIANDO IMAGENS DOS SLIDES...");
            Servidor.enviarSlides(PATH_SLIDE, qtdImg);
            txtConexao.setText("ENVIANDO IMAGENS AO CELULAR...");
            Thread.sleep(200);
            Servidor.receberImagens();
            txtConexao.setText("ESPERANDO POR IMAGENS...");
        } catch (HeadlessException | IOException | InterruptedException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEscolherSlideActionPerformed

    public String getTemp() throws IOException{
        File temp = File.createTempFile("temp-file-name", ".tmp"); 
            System.out.println("Temp file : " + temp.getAbsolutePath());
		//Get tempropary file path
            String absolutePath = temp.getAbsolutePath();
            return absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
    }
    
    public String getIPAddress(boolean useIPv4) {
            try {
                List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            boolean isIPv4 = sAddr.indexOf(':')<0;

                            if (useIPv4) {
                                if (isIPv4)
                                    return sAddr;
                            } else {
                                if (!isIPv4) {
                                    int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                    return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                }
                            }
                        }
                    }
                }
            } catch (Exception ignored) { } // for now eat exceptions
            return null;
    }

    
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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaInicial().setVisible(true);
                } catch (AWTException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscolherSlide;
    private javax.swing.JButton btnReload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtConexao;
    private javax.swing.JLabel txtSlidePath;
    // End of variables declaration//GEN-END:variables
}