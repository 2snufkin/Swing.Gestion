
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        passwordLbl = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        userLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        userLbl.setText("User");

        jLabel2.setText("Password");

        userFld.setText(" ");

        passwordFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout passwordLblLayout = new javax.swing.GroupLayout(passwordLbl);
        passwordLbl.setLayout(passwordLblLayout);
        passwordLblLayout.setHorizontalGroup(
                passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, passwordLblLayout.createSequentialGroup()
                                .addGroup(passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, passwordLblLayout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addGroup(passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(userLbl))
                                                .addGap(41, 41, 41)
                                                .addGroup(passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(userFld, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                                        .addComponent(passwordFld)))
                                        .addGroup(passwordLblLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(loginBtn)))
                                .addGap(66, 66, 66))
        );
        passwordLblLayout.setVerticalGroup(
                passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(passwordLblLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userLbl)
                                        .addComponent(userFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(passwordLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addComponent(loginBtn)
                                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void passwordFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField passwordFld;
    private javax.swing.JPanel passwordLbl;
    private javax.swing.JTextField userFld;
    private javax.swing.JLabel userLbl;
    // End of variables declaration
}
