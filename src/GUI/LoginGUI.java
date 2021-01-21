/*

            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Invalid Username / Password","Login Error",2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Login_Form.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    }
// open the register form when the user click on this jlabel with the mouse
    private void jLabel_Create_AccountMouseClicked(java.awt.event.MouseEvent evt) {
        Register_Form rf = new Register_Form();
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();

 */

package GUI;
import dAO.UserDAO;
import connection.Datasource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI extends javax.swing.JFrame {

    public LoginGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
    //Creating the Objects
        passwordLbl = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        userLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JPasswordField();
        //my query



        //Set it to really close
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    //Button
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        //Labels
        userLbl.setText("User");
        jLabel2.setText("Password");

        //Input
        userFld.setText(" ");
        passwordFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFldActionPerformed(evt);
            }
        });

        //Beauty
        this.setLocationRelativeTo(null); //Center the window
        // create a yellow border for the jpanel_title
        // 0 border in the top
         // set the border to the jPanel_title



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
        int role = 0;
        System.out.println(query);
        conn = Datasource.getInstance();
        String username = userFld.getText().trim();
        System.out.println(username);
        String password = String.valueOf(passwordFld.getPassword()).trim();
        System.out.println(password);

        if (username.isEmpty()) JOptionPane.showMessageDialog(this, "Enter Your Username", "Empty Username", 2);
        else if (password.isBlank())
            JOptionPane.showMessageDialog(this, "Enter Your Password", "Empty Username", 2);
        else {
            try {
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    role = rs.getInt(4);
                    System.out.println(role);

                    if (role == 1) {
                        AdminActionsGUI adminW = new AdminActionsGUI();
                        adminW.setVisible(true);
                        adminW.pack();
                        adminW.setLocationRelativeTo(null);
                        adminW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();//close the current window
                    } else {
                        UserActionsGUI userW = new UserActionsGUI();
                        userW.setVisible(true);
                        userW.pack();
                        userW.setLocationRelativeTo(null);
                        userW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();//close the current window

                    }
                } else
                    JOptionPane.showMessageDialog(this, "User dosent exist");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "oh oh ");
                System.out.println(role);

            }
        }

    }//Button Actiob

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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel userLbl;
    private javax.swing.JLabel icon;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JTextField userFld;
    private javax.swing.JPanel passwordLbl;
    // SELECT * FROM  users   WHERE user = 'Avi' AND password = 'beaugoss';
    public static final String query = "SELECT * FROM " + UserDAO.TABLE_USERS+  " WHERE " +UserDAO.COLUMN_USERS_NAME +" = ? AND "+
    UserDAO.COLUMN_USERS_PASSWORD + " = ?";
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;



    // End of variables declaration
}
