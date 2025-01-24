package vistas;

import controladores.*;
import controladores.exceptions.NonexistentEntityException;
import jakarta.persistence.*;
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
public class DialogModAutor extends javax.swing.JDialog {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("uniPersistencia");
    //Controladores
    private ctrlJpaAutor ctrlAutor = new ctrlJpaAutor(emf);
    private ctrlJpaLibro ctrlLibro = new ctrlJpaLibro(emf);
    //Modelos
    private DefaultTableModel modelLibros = new DefaultTableModel();
    private DefaultTableModel modelLibrosSelec = new DefaultTableModel();
    private Autor autor = null;

    public DialogModAutor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Añadir un Autor");
        rellenarComponentes();
        initModelosTablas();
        initTablas(ctrlLibro.obtenerAllLibros());
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
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelNom = new javax.swing.JLabel();
        jButtonModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLibros = new javax.swing.JTable();
        jButtonEliminar = new javax.swing.JButton();
        jButtonSelec = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLibrosSelec = new javax.swing.JTable();
        jLabelLibros = new javax.swing.JLabel();
        jLabelLibrosAut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelTitle.setText("Modificar Autor");

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabelNom.setText("Nombre");

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jTableLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableLibros);

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

        jTableLibrosSelec.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableLibrosSelec);

        jLabelLibros.setText("Libros");

        jLabelLibrosAut.setText("Libros del Autor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabelNom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonModificar)
                .addGap(205, 205, 205))
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabelLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLibrosAut)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSelec, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabelTitle)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelTitle)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jButtonSelec)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonModificar)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNom)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLibros)
                            .addComponent(jLabelLibrosAut))
                        .addContainerGap(64, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        if (jTextFieldNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Introduzca un nombre");
        } else {
            Set<Libro> listaLibros = new HashSet<Libro>();
            Libro libro;
            for (int i = 0; i < modelLibrosSelec.getRowCount(); i++) {
                libro = ctrlLibro.obtenerLibroXId(modelLibrosSelec.getValueAt(i, 0));
                listaLibros.add(libro);
            }
            Autor autorMod = new Autor(jTextFieldNombre.getText(), listaLibros);
            try {
                ctrlAutor.editar(autorMod);
                this.dispose();
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(DialogModAutor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(DialogModAutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (jTableLibrosSelec.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un libro de la tabla Libros del Autor");
        } else {
            int row = jTableLibrosSelec.getSelectedRow();
            Object[] fila = {modelLibrosSelec.getValueAt(row, 0), modelLibrosSelec.getValueAt(row, 1)};
            modelLibrosSelec.removeRow(row);
            modelLibros.addRow(fila);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecActionPerformed
        if (jTableLibros.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un libro de la tabla Libros");
        } else {
            int row = jTableLibros.getSelectedRow();
            Object[] fila = {modelLibros.getValueAt(row, 0), modelLibros.getValueAt(row, 1)};
            modelLibros.removeRow(row);
            modelLibrosSelec.addRow(fila);
        }
    }//GEN-LAST:event_jButtonSelecActionPerformed

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
            java.util.logging.Logger.getLogger(DialogModAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogModAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogModAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogModAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogModAutor dialog = new DialogModAutor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabelLibros;
    private javax.swing.JLabel jLabelLibrosAut;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableLibros;
    private javax.swing.JTable jTableLibrosSelec;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    private void initTablas(List<Libro> listaLibros) {
        for (Libro l : autor.getLibrosSet()) {
            listaLibros.remove(l);
            Object[] fila = {l.getIdLibros(), l.getTitulo()};
            modelLibrosSelec.addRow(fila);
        }
        for (Libro l : listaLibros) {
            Object[] fila = {l.getIdLibros(), l.getTitulo()};
            modelLibros.addRow(fila);
        }
    }

    private void initModelosTablas() {
        //Tabla Libros
        modelLibros.addColumn("Id");
        modelLibros.addColumn("Nombre");
        jTableLibros.setModel(modelLibros);
        //Tabla LibrosSelec
        modelLibrosSelec.addColumn("Id");
        modelLibrosSelec.addColumn("Nombre");
        jTableLibrosSelec.setModel(modelLibrosSelec);
    }

    public void setAutor(Autor aut){
        autor = aut;
    }

    private void rellenarComponentes() {
        jTextFieldNombre.setText(autor.getNomAutor());
    }
}
