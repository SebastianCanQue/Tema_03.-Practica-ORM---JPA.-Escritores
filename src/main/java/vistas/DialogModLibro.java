package vistas;

import controladores.*;
import controladores.exceptions.NonexistentEntityException;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.*;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class DialogModLibro extends javax.swing.JDialog {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("uniPersistencia");
    //Controladores
    private ctrlJpaAutor ctrlAutor = new ctrlJpaAutor(emf);
    private ctrlJpaLibro ctrlLibro = new ctrlJpaLibro(emf);
    private ctrlJpaCategoria ctrlCateg = new ctrlJpaCategoria(emf);
    //Modelos
    private DefaultTableModel modelCateg = new DefaultTableModel();
    private DefaultTableModel modelCategLibro = new DefaultTableModel();
    private Libro libro;

    public DialogModLibro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Modificar un Libro");
        initModelosTablas();
        initTablas(ctrlCateg.obtenerCategorias());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jLabelNom = new javax.swing.JLabel();
        jButtonModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCateg = new javax.swing.JTable();
        jButtonEliminar = new javax.swing.JButton();
        jButtonSelec = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCategLibro = new javax.swing.JTable();
        jLabelCateg = new javax.swing.JLabel();
        jLabelCategLibro = new javax.swing.JLabel();
        jLabelAutor = new javax.swing.JLabel();
        jLabelPrecio = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jComboBoxAutores = new javax.swing.JComboBox<>();
        jTextFieldPrecio = new javax.swing.JTextField();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelTitle.setText("Modificar Libro");

        jTextFieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloActionPerformed(evt);
            }
        });

        jLabelNom.setText("Titulo");

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jTableCateg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCateg);

        jButtonEliminar.setText("<");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonSelec.setText(">");
        jButtonSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecActionPerformed(evt);
            }
        });

        jTableCategLibro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableCategLibro);

        jLabelCateg.setText("Categorias");

        jLabelCategLibro.setText("Categorias del Libro");

        jLabelAutor.setText("Autor");

        jLabelPrecio.setText("Precio");

        jLabelFecha.setText("Fecha");

        jComboBoxAutores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioActionPerformed(evt);
            }
        });
        jTextFieldPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxAutores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPrecio)
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle)
                    .addComponent(jButtonModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSelec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabelCateg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCategLibro)
                        .addGap(48, 48, 48)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelTitle)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNom)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAutor)
                    .addComponent(jComboBoxAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecio)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFecha)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButtonSelec)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCateg)
                            .addComponent(jLabelCategLibro))))
                .addGap(37, 37, 37)
                .addComponent(jButtonModificar)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        if (jTextFieldTitulo.getText().equals("") || jTextFieldPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Compruebe que todos los campos esten rellenos");
        } else {
            if (jDateChooserFecha.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha");
            } else {
                Set<Categoria> listaCateg = new HashSet<Categoria>();
                Categoria categ;
                for (int i = 0; i < modelCategLibro.getRowCount(); i++) {
                    categ = ctrlCateg.obtenerCategXId(modelCategLibro.getValueAt(i, 0));
                    listaCateg.add(categ);
                }
                BigDecimal bD = new BigDecimal(jTextFieldPrecio.getText());
                Autor autor = ctrlAutor.obtenerAutorXNombre(jComboBoxAutores.getSelectedItem());
                Libro libroMod = new Libro(jTextFieldTitulo.getText(), jDateChooserFecha.getDate(), bD, listaCateg, autor);
                try {
                    ctrlLibro.editar(libroMod);
                    this.dispose();
                } catch (NonexistentEntityException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(DialogModLibro.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(DialogModLibro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (jTableCategLibro.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Categoria de la tabla Categorias del Libro");
        } else {
            int row = jTableCategLibro.getSelectedRow();
            Object[] fila = {modelCategLibro.getValueAt(row, 0), modelCategLibro.getValueAt(row, 1)};
            modelCategLibro.removeRow(row);
            modelCateg.addRow(fila);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecActionPerformed
        if (jTableCateg.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Categoria de la tabla Categorias");
        } else {
            int row = jTableCateg.getSelectedRow();
            Object[] fila = {modelCateg.getValueAt(row, 0), modelCateg.getValueAt(row, 1)};
            modelCateg.removeRow(row);
            modelCategLibro.addRow(fila);
        }
    }//GEN-LAST:event_jButtonSelecActionPerformed

    private void jTextFieldPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioActionPerformed

    private void jTextFieldPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioKeyTyped
        char c = evt.getKeyChar();
        String texto = jTextFieldPrecio.getText();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        } else if (c == '.' && texto.contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPrecioKeyTyped

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
            java.util.logging.Logger.getLogger(DialogModLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogModLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogModLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogModLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogModLibro dialog = new DialogModLibro(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonSelec;
    private javax.swing.JComboBox<String> jComboBoxAutores;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelCateg;
    private javax.swing.JLabel jLabelCategLibro;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCateg;
    private javax.swing.JTable jTableCategLibro;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables

    private void initTablas(List<Categoria> listaCategorias) {
        for (Categoria c : libro.getCategoriasSet()) {
            listaCategorias.remove(c);
            Object[] fila = {c.getIdCategoria(), c.getNomCategoria()};
            modelCategLibro.addRow(fila);
        }
        for (Categoria c : listaCategorias) {
            Object[] fila = {c.getIdCategoria(), c.getNomCategoria()};
            modelCateg.addRow(fila);
        }
    }

    private void initModelosTablas() {
        //Tabla Categ
        modelCateg.addColumn("Id");
        modelCateg.addColumn("Nombre");
        jTableCateg.setModel(modelCateg);
        //Tabla CategSelec
        modelCategLibro.addColumn("Id");
        modelCategLibro.addColumn("Nombre");
        jTableCategLibro.setModel(modelCategLibro);
    }

}
