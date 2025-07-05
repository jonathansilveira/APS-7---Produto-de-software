package _00_Telas;

import _01_Classes.ConectaBanco;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class _01_Login extends javax.swing.JFrame {
    
// Instâncias
    ConectaBanco conecta = new ConectaBanco(); // Instância da classe de conexão ao banco de dados

// Variáveis globais
    String usuario;
    private int mouseX, mouseY;

    public _01_Login(java.awt.Frame parent, boolean modal) {
        initComponents(); // Inicialização
        setIcon(); // Classe setIcon, abre a tela com icon especificado
        Move(); // Classe Move, para movimentação da janela
    }


// Lança uma exceção UnsupportedOperationException com a mensagem "Not supported yet." indicando que a implementação está pendente.
    public _01_Login() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

// Carrega a imagem do ícone a partir do recurso no caminho especificado
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/_02_Imagens/Icon.png")));
    }

// Classe que permite a movimentação da janela arrastando com o mouse
    public void Move() {
        // Adicionando o MouseListener e MouseMotionListener ao Panel_Main
        Panel_Main.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Armazena as coordenadas X e Y do ponto onde o mouse foi pressionado na tela
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        Panel_Main.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calcula a nova posição da janela enquanto o mouse está sendo arrastado
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                // Define a nova posição da janela com base na movimentação do mouse
                setLocation(x, y);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Main = new javax.swing.JPanel();
        Panel_Left = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        lbl_senha = new javax.swing.JLabel();
        txt_senha = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        lbl_senha1 = new javax.swing.JLabel();
        btn_minimiza = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ONG Jovens Ambientalistas");
        setMinimumSize(new java.awt.Dimension(500, 500));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Main.setBackground(new java.awt.Color(153, 153, 153));
        Panel_Main.setForeground(new java.awt.Color(153, 153, 153));
        Panel_Main.setMinimumSize(new java.awt.Dimension(800, 500));
        Panel_Main.setPreferredSize(new java.awt.Dimension(500, 500));
        Panel_Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Left.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Left.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Left.setPreferredSize(new java.awt.Dimension(400, 500));
        Panel_Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Logo_Home_2.png"))); // NOI18N
        Panel_Left.add(lbl_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 250, 190));

        txt_usuario.setBackground(new java.awt.Color(255, 255, 255));
        txt_usuario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_usuario.setForeground(new java.awt.Color(6, 119, 67));
        txt_usuario.setToolTipText("");
        txt_usuario.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(6, 119, 67), null));
        txt_usuario.setOpaque(false);
        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });
        Panel_Left.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 280, 40));
        txt_usuario.getAccessibleContext().setAccessibleName("");

        lbl_senha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_senha.setForeground(new java.awt.Color(6, 119, 67));
        lbl_senha.setText("Usuário");
        Panel_Left.add(lbl_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        txt_senha.setBackground(new java.awt.Color(255, 255, 255));
        txt_senha.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_senha.setForeground(new java.awt.Color(6, 119, 67));
        txt_senha.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(6, 119, 67), null));
        txt_senha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_senha.setOpaque(false);
        txt_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_senhaActionPerformed(evt);
            }
        });
        Panel_Left.add(txt_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 280, 40));
        txt_senha.getAccessibleContext().setAccessibleName("");

        btn_login.setBackground(new java.awt.Color(6, 119, 67));
        btn_login.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setText("Login");
        btn_login.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_login.setOpaque(false);
        btn_login.setRequestFocusEnabled(false);
        btn_login.setSelected(true);
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        Panel_Left.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 140, 45));

        lbl_senha1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_senha1.setForeground(new java.awt.Color(6, 119, 67));
        lbl_senha1.setText("Senha");
        Panel_Left.add(lbl_senha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        btn_minimiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_2.png"))); // NOI18N
        btn_minimiza.setBorderPainted(false);
        btn_minimiza.setContentAreaFilled(false);
        btn_minimiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizaActionPerformed(evt);
            }
        });
        Panel_Left.add(btn_minimiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_2.png"))); // NOI18N
        btn_sair.setBorderPainted(false);
        btn_sair.setContentAreaFilled(false);
        btn_sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair.setPreferredSize(new java.awt.Dimension(23, 23));
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });
        Panel_Left.add(btn_sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 56, -1));

        jPanel3.setBackground(new java.awt.Color(6, 119, 67));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_Left.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 60));

        jPanel5.setBackground(new java.awt.Color(6, 119, 67));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_Left.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 500, 60));

        Panel_Main.add(Panel_Left, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 700));
        Panel_Left.getAccessibleContext().setAccessibleName("");

        getContentPane().add(Panel_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 700));

        setSize(new java.awt.Dimension(500, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        usuario = txt_usuario.getText();
        new _02_Menu(usuario).setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_senhaActionPerformed

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    private void btn_minimizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizaActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimizaActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sairActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(_01_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(_01_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(_01_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(_01_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            _01_Login dialog = new _01_Login(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Left;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_minimiza;
    private javax.swing.JButton btn_sair;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_senha;
    private javax.swing.JLabel lbl_senha1;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
