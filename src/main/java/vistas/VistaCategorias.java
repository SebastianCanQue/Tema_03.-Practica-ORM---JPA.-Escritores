package vistas;

import controladores.*;
import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.*;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class VistaCategorias extends javax.swing.JFrame {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("uniPersistencia");
    ;
    //Controladores de las clases
    private ctrlJpaCategoria ctrlCateg = new ctrlJpaCategoria(emf);
    private ctrlJpaLibro ctrlLibro = new ctrlJpaLibro(emf);
    //Modelos de las tablas
    private DefaultTableModel modelCateg = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel modelLibros = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public VistaCategorias() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("CRUD Categorias - Sebastián Candelas Quero");
        this.toFront();
        inicModelosTablas();
        rellenarTablaCategorias(ctrlCateg.obtenerCategorias());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategorias = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLibros = new javax.swing.JTable();
        jLabelAutor = new javax.swing.JLabel();
        jTextFieldAutor = new javax.swing.JTextField();
        jMenuBarAutores = new javax.swing.JMenuBar();
        jMenuVolver = new javax.swing.JMenu();
        jMenuAlta = new javax.swing.JMenu();
        jMenuBaja = new javax.swing.JMenu();
        jMenuMod = new javax.swing.JMenu();
        jMenuRefrescar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jTableCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategorias);

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
        jTableLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLibrosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableLibros);

        jLabelAutor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAutor.setText("Autor:");

        jMenuVolver.setText("Volver");
        jMenuVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuVolverMouseClicked(evt);
            }
        });
        jMenuBarAutores.add(jMenuVolver);

        jMenuAlta.setText("Alta");
        jMenuAlta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAltaMouseClicked(evt);
            }
        });
        jMenuBarAutores.add(jMenuAlta);

        jMenuBaja.setText("Baja");
        jMenuBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBajaMouseClicked(evt);
            }
        });
        jMenuBarAutores.add(jMenuBaja);

        jMenuMod.setText("Modificar");
        jMenuMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuModMouseClicked(evt);
            }
        });
        jMenuBarAutores.add(jMenuMod);

        jMenuRefrescar.setText("Refrescar");
        jMenuRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuRefrescarMouseClicked(evt);
            }
        });
        jMenuBarAutores.add(jMenuRefrescar);

        setJMenuBar(jMenuBarAutores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabelAutor)
                        .addGap(120, 120, 120)
                        .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAutor)
                    .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuVolverMouseClicked
        //Cerramos la ventana Categorias
        dispose();
        //Creamos la nueva ventana principal y la mostramos
        Principal principal = new Principal();
        principal.setVisible(true);
    }//GEN-LAST:event_jMenuVolverMouseClicked

    private void jTableCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoriasMouseClicked
        if (jTableCategorias.getSelectedRow() != -1) {
            Set<Libro> colecLibros;
            int row = jTableCategorias.getSelectedRow();
            Object idCategoria = modelCateg.getValueAt(row, 0);
            colecLibros = ctrlCateg.obtenerCategXId(idCategoria).getLibrosSet();
            rellenarTablaLibros(colecLibros);
        }
    }//GEN-LAST:event_jTableCategoriasMouseClicked

    private void jTableLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLibrosMouseClicked
        if (jTableLibros.getSelectedRow() != -1) {
            Libro libro;
            int row = jTableLibros.getSelectedRow();
            Object nombreLi = modelLibros.getValueAt(row, 0);
            libro = ctrlLibro.obtenerLibroXTitulo(nombreLi);
            jTextFieldAutor.setText(libro.getAutor().getNomAutor());
        }
    }//GEN-LAST:event_jTableLibrosMouseClicked

    private void jMenuAltaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAltaMouseClicked
        DialogAniadirCategoria aniadirCat = new DialogAniadirCategoria(this, rootPaneCheckingEnabled);
        aniadirCat.setVisible(true);
        rellenarTablaCategorias(ctrlCateg.obtenerCategorias());
    }//GEN-LAST:event_jMenuAltaMouseClicked

    private void jMenuRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuRefrescarMouseClicked
        rellenarTablaCategorias(ctrlCateg.obtenerCategorias());
    }//GEN-LAST:event_jMenuRefrescarMouseClicked

    private void jMenuBajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBajaMouseClicked
        if (jTableCategorias.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria de la tabla para dar de baja");
        } else {
            int selec = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar la categoría seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (selec == JOptionPane.YES_OPTION) {
                Object idCateg = modelCateg.getValueAt(jTableCategorias.getSelectedRow(), 0);
                Categoria cat = ctrlCateg.obtenerCategXId(idCateg);
                ctrlCateg.baja(cat);
                rellenarTablaCategorias(ctrlCateg.obtenerCategorias());
                rellenarTablaLibros(new HashSet<Libro>());
            } else {
                JOptionPane.showMessageDialog(null, "Baja cancelada");
            }
        }
    }//GEN-LAST:event_jMenuBajaMouseClicked

    private void jMenuModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuModMouseClicked
        if(jTableCategorias.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una categoria de la tabla para modificar");
        }else{
            Object idCat = modelCateg.getValueAt(jTableCategorias.getSelectedRow(), 0);
            Categoria catVentana = ctrlCateg.obtenerCategXId(idCat);
            DialogModCategoria modCat = new DialogModCategoria(this, rootPaneCheckingEnabled, catVentana);
            modCat.setVisible(true);
        }
    }//GEN-LAST:event_jMenuModMouseClicked

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
            java.util.logging.Logger.getLogger(VistaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JMenu jMenuAlta;
    private javax.swing.JMenu jMenuBaja;
    private javax.swing.JMenuBar jMenuBarAutores;
    private javax.swing.JMenu jMenuMod;
    private javax.swing.JMenu jMenuRefrescar;
    private javax.swing.JMenu jMenuVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCategorias;
    private javax.swing.JTable jTableLibros;
    private javax.swing.JTextField jTextFieldAutor;
    // End of variables declaration//GEN-END:variables

    private void inicModelosTablas() {
        //Tabla categorias
        modelCateg.addColumn("Id");
        modelCateg.addColumn("Nombre");
        jTableCategorias.setModel(modelCateg);
        //Tabla libros
        modelLibros.addColumn("Titulo");
        modelLibros.addColumn("Fecha Publicación");
        modelLibros.addColumn("Precio");
        jTableLibros.setModel(modelLibros);

    }

    private void rellenarTablaCategorias(List<Categoria> listaCategoria) {
        modelCateg.setRowCount(0);
        for (Categoria c : listaCategoria) {
            Object[] fila = {c.getIdCategoria(), c.getNomCategoria()};
            modelCateg.addRow(fila);
        }
    }

    private void rellenarTablaLibros(Set<Libro> colecLibros) {
        modelLibros.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Libro l : colecLibros) {
            Object[] fila = {l.getTitulo(), sdf.format(l.getFechaPublicacion()), l.getPrecio()};
            modelLibros.addRow(fila);
        }
    }
}
