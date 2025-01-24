package vistas;

import javax.swing.*;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Practica 01 - Tema 03 ORM - JPA - Sebastián Candelas Quero");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenuAutores = new javax.swing.JMenu();
        jMenuCateg = new javax.swing.JMenu();
        jMenuLibros = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuAutores.setText("Autores");
        jMenuAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAutoresMouseClicked(evt);
            }
        });
        jMenuAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAutoresActionPerformed(evt);
            }
        });
        jMenuBarPrincipal.add(jMenuAutores);

        jMenuCateg.setText("Categorías");
        jMenuCateg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCategMouseClicked(evt);
            }
        });
        jMenuBarPrincipal.add(jMenuCateg);

        jMenuLibros.setText("Libros");
        jMenuLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLibrosMouseClicked(evt);
            }
        });
        jMenuBarPrincipal.add(jMenuLibros);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAutoresActionPerformed

    }//GEN-LAST:event_jMenuAutoresActionPerformed

    private void jMenuAutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAutoresMouseClicked
        javax.swing.JDialog cargandoDialog = new javax.swing.JDialog(this, "Cargando...", true);
        cargandoDialog.setSize(200, 100);
        cargandoDialog.setLocationRelativeTo(this);
        cargandoDialog.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);

        javax.swing.JLabel label = new javax.swing.JLabel("Cargando...", javax.swing.SwingConstants.CENTER);
        cargandoDialog.add(label);
        SwingUtilities.invokeLater(() -> cargandoDialog.setVisible(true));
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            VistaAutores frame = new VistaAutores();
            frame.setVisible(true);
            frame.toFront();

            cargandoDialog.dispose();
        });
    }//GEN-LAST:event_jMenuAutoresMouseClicked

    private void jMenuLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLibrosMouseClicked
        javax.swing.JDialog cargandoDialog = new javax.swing.JDialog(this, "Cargando...", true);
        cargandoDialog.setSize(200, 100);
        cargandoDialog.setLocationRelativeTo(this);
        cargandoDialog.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);

        javax.swing.JLabel label = new javax.swing.JLabel("Cargando...", javax.swing.SwingConstants.CENTER);
        cargandoDialog.add(label);
        SwingUtilities.invokeLater(() -> cargandoDialog.setVisible(true));
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            VistaLibros frame = new VistaLibros();
            frame.setVisible(true);
            frame.toFront();

            cargandoDialog.dispose();
        });
    }//GEN-LAST:event_jMenuLibrosMouseClicked

    private void jMenuCategMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCategMouseClicked
        javax.swing.JDialog cargandoDialog = new javax.swing.JDialog(this, "Cargando...", true);
        cargandoDialog.setSize(200, 100);
        cargandoDialog.setLocationRelativeTo(this);
        cargandoDialog.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);

        javax.swing.JLabel label = new javax.swing.JLabel("Cargando...", javax.swing.SwingConstants.CENTER);
        cargandoDialog.add(label);
        SwingUtilities.invokeLater(() -> cargandoDialog.setVisible(true));
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            VistaCategorias frame = new VistaCategorias();
            frame.setVisible(true);
            frame.toFront();

            cargandoDialog.dispose();
        });
    }//GEN-LAST:event_jMenuCategMouseClicked

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAutores;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuCateg;
    private javax.swing.JMenu jMenuLibros;
    // End of variables declaration//GEN-END:variables
}
