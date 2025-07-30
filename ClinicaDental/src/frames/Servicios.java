/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Sesion;
import java.awt.HeadlessException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import paquete.Conexion;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import menulateral.SideMenuItem;

/**
 *
 * @author busta
 */
public class Servicios extends javax.swing.JFrame {
    private int rolUsuario; 
    private TableRowSorter<DefaultTableModel> sorter;
     Conexion conexion = new Conexion();

    /**
     * Creates new form Servicios
     */
   public Servicios() {
       
     this.rolUsuario = rolUsuario;
     initComponents();
    
     cargarServiciosEnTabla(tablaServicios);
     
     
      for (SideMenuItem item : menu.getModel().getItems()) {
        if ("Configuración".equals(item.getText())) { // Buscamos el padre
            System.out.println("Clase del item: " + item.getClass().getName());
System.out.println("Clase del child: " + item.getChildren().get(0).getClass().getName());

            for (SideMenuItem child : item.getChildren()) {
                if ("Ver Perfiles de Usuario".equals(child.getText())) {

                    // Agregamos el actionListener directamente al child
                    menu.setMenuItemAction("Ver Perfiles de Usuario", e -> {
                        int rol = Sesion.getRol(); // obtener el rol actual de sesión

                        if (rol == 1) { // Suponiendo que el ID de administrador es 4
                            new Perfil().setVisible(true);
                            this.dispose();
                        } else {
                            child.setEnabled(false); // Deshabilita
                            child.setShown(false);   // Oculta
                        }
                    });

                    // Puedes deshabilitarlo directamente aquí si el rol no es 4
                    if (Sesion.getRol() != 1) {
                        child.setEnabled(false);
                        child.setShown(false);
                    }

                    break;
                }
            }
            break; // ya encontramos "Perfil", salir del primer for
        }
        
    }
    
    
    
    menu.setMenuItemAction("Mi perfil", e -> {
                        new MiPerfil().setVisible(true);
                        this.dispose();
                    });
    
     menu.setMenuItemAction("Inicio", e -> {
                        new Inicio().setVisible(true);
                        this.dispose();
                    });
     
      menu.setMenuItemAction("Citas", e -> {
                        new Citas().setVisible(true);
                        this.dispose();
                    });
      
       menu.setMenuItemAction("Cerrar Sesión", e -> {
                        new LoginConFondo().setVisible(true);
                        this.dispose();
                    });
         
        menu.setMenuItemAction("Pagos", e -> {
                        new Pagos().setVisible(true);
                        this.dispose();
                    });
        
        menu.setMenuItemAction("Tratamientos", e -> {
                        new Procedimientos().setVisible(true);
                        this.dispose();
                    });
        
        menu.setMenuItemAction("Servicios", e -> {
                        new Servicios().setVisible(true);
                        this.dispose();
                    });
        menu.setMenuItemAction("Gestionar citas", e -> {
                        new GestionarCitas().setVisible(true);
                        this.dispose();
                    });
}

    public void cargarServiciosEnTabla(JTable tablaServicios) {
    DefaultTableModel modelo = (DefaultTableModel) tablaServicios.getModel();
    modelo.setRowCount(0); // Limpiar tabla

    try {
        // Consulta a la base de datos (asegúrate de que devuelva también el estado)
        ResultSet rsServicios = conexion.consultarTodo("servicio");

        while (rsServicios != null && rsServicios.next()) {
            int idServicio = rsServicios.getInt("id_servicio");
            String nombre = rsServicios.getString("nombre");
            String estado = rsServicios.getString("activo");

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{idServicio, nombre, estado});
        }

        if (rsServicios != null) rsServicios.close();

    } catch (SQLException e) {
        System.err.println("Error al cargar servicios: " + e.getMessage());
    }

    // Asignar sorter para ordenar y filtrar
    sorter = new TableRowSorter<>(modelo);
    tablaServicios.setRowSorter(sorter);
}

     private Integer getServicioSeleccionado() {
        int fila = tablaServicios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio primero.");
            return null;
        }
        // Suponiendo que la primera columna es id_usuario
        return (Integer) tablaServicios.getValueAt(fila, 0);
    }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSuspender = new javax.swing.JButton();
        menu = new menulateral.SideMenuComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 247, 229));

        jPanel2.setBackground(new java.awt.Color(132, 157, 162));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 255, 153));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Servicios");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 185, 39));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tablaServicios.setBackground(new java.awt.Color(212, 231, 238));
        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaServicios);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSuspender.setText("Suspender");
        btnSuspender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuspenderActionPerformed(evt);
            }
        });

        menu.setBackgroundColor(new java.awt.Color(212, 231, 238));
        menu.setHoverColor(new java.awt.Color(132, 157, 162));
        menu.setModel(new menulateral.SideMenuModel() {{ addItem(new menulateral.SideMenuItem("Inicio", "/icons/home.png", "Ir a Inicio")); addItem(new menulateral.SideMenuItem("Mi perfil", "C:\\Users\\ayele\\Downloads\\usuario (1).png", "Ir a mi perfil ")); addItem(new menulateral.SideMenuItem("Tratamientos", "C:\\Users\\ayele\\Downloads\\procedimiento.png", "Ir a ver los tratamientos ")); addItem(new menulateral.SideMenuItem("Servicios", "C:\\Users\\ayele\\Downloads\\dental (1).png", "Ir a ver los servicios")); addItem(new menulateral.SideMenuItem("Citas", "C:\\Users\\ayele\\Downloads\\cita (2).png", "Ir a citas")); addItem(new menulateral.SideMenuItem("Pagos", "C:\\Users\\ayele\\Downloads\\pago.png", "Ir a pagos")); addItem(new menulateral.SideMenuItem("Configuración", "/icons/config.png", "Ir a configuración")); menulateral.SideMenuItem item283 = getItem(6); menulateral.SideMenuItem item8786 = new menulateral.SideMenuItem("Ver Perfiles de Usuario", "C:\\Users\\ayele\\Downloads\\usuarios.png"); item283.addChild(item8786); addItem(new menulateral.SideMenuItem("Gestionar citas", "C:\\Users\\ayele\\Downloads\\chequeo-dental.png")); addItem(new menulateral.SideMenuItem("Ayuda", "/icons/ayuda.png", "Ayuda")); addItem(new menulateral.SideMenuItem("Cerrar Sesión", "C:\\Users\\ayele\\Downloads\\cerrar-sesion.png", "Ir al login")); }});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnAgregar)
                        .addGap(97, 97, 97)
                        .addComponent(btnEliminar)
                        .addGap(87, 87, 87)
                        .addComponent(btnSuspender)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar)
                            .addComponent(btnSuspender))
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
          String texto = txtBuscar.getText().trim();

        if (texto.length() == 0) {
            sorter.setRowFilter(null); // Mostrar todo
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto)); // Filtrado con ignore case
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
                                          
    try {
        // Validamos el rol del usuario
        String[] campos = {"id_usuario"};
        Object[] valores = {Sesion.getIdUsuario()};
        ResultSet rs = conexion.buscarPorCampos("usuario", campos, valores);

        if (rs != null && rs.next()) {
            int idRol = rs.getInt("id_rol");

            if (idRol == 1) {
                // Abrimos el formulario como JFrame
                AgregarServicio form = new AgregarServicio();
                form.setLocationRelativeTo(this); // Centrar
                form.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, 
                    "Solo el Administrador puede agregar servicios.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error en la operación.");
    }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
        // Validar el rol del usuario en sesión
        String[] campos = {"id_usuario"};
        Object[] valores = {Sesion.getIdUsuario()};
        ResultSet rs = conexion.buscarPorCampos("usuario", campos, valores);

        if (rs != null && rs.next()) {
            int idRol = rs.getInt("id_rol");

            if (idRol == 1) {
                // Solo el Administrador puede eliminar
                Integer idServicio = getServicioSeleccionado();
                if (idServicio == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione un servicio para eliminar.");
                    return;
                }

                String nombreServicio = (String) tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 1);

                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de eliminar el servicio '" + nombreServicio + "'? Esta acción es irreversible.",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean exito = conexion.eliminar("servicio", "id_servicio = ?", idServicio);

                    if (exito) {
                        JOptionPane.showMessageDialog(this, "Servicio eliminado correctamente.");
                        cargarServiciosEnTabla(tablaServicios);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el servicio.");
                    }
                }

            } else {
                // Si no es Administrador, no puede eliminar
                JOptionPane.showMessageDialog(this,
                        "No tienes permisos para eliminar servicios. Solo el Administrador puede hacerlo.");
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al intentar eliminar el servicio.");
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSuspenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuspenderActionPerformed
        // TODO add your handling code here:                                                   
    // Validar que se haya seleccionado un servicio
    int fila = tablaServicios.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un servicio primero.");
        return;
    }

    // Validar el rol (solo admin puede suspender)
    try {
        String[] campos = {"id_usuario"};
        Object[] valores = {Sesion.getIdUsuario()};
        ResultSet rs = conexion.buscarPorCampos("usuario", campos, valores);

        if (rs != null && rs.next()) {
            int idRol = rs.getInt("id_rol");

            if (idRol != 1) {
                JOptionPane.showMessageDialog(this,
                        "No tienes permisos para suspender servicios. Solo el Administrador puede hacerlo.");
                return;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al verificar permisos.");
        return;
    }

    // Datos del servicio
    int idServicio = (int) tablaServicios.getValueAt(fila, 0); // Columna 0 = id
    String estadoActual = tablaServicios.getValueAt(fila, 2).toString(); // Columna 2 = estado

    // Determinar nuevo estado
    String nuevoEstado = estadoActual.equalsIgnoreCase("ACTIVO") ? "INACTIVO" : "ACTIVO";
    String mensajeConfirmacion = estadoActual.equalsIgnoreCase("ACTIVO")
            ? "¿Deseas suspender el servicio?"
            : "El servicio está inactivo. ¿Deseas activarlo de nuevo?";

    // Confirmar acción
    int respuesta = JOptionPane.showConfirmDialog(this, mensajeConfirmacion, "Confirmar", JOptionPane.YES_NO_OPTION);

    if (respuesta == JOptionPane.YES_OPTION) {
        // Actualizar estado en BD
        boolean actualizado = conexion.actualizar(
                "servicio",
                "estado = ?",
                "id_servicio = ?",
                nuevoEstado,
                idServicio
        );

        if (actualizado) {
            String mensajeExito = nuevoEstado.equals("INACTIVO")
                    ? "Servicio suspendido correctamente."
                    : "Servicio activado correctamente.";
            JOptionPane.showMessageDialog(this, mensajeExito);

            // Refrescar tabla
            cargarServiciosEnTabla(tablaServicios);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado del servicio.");
        }
    }       
    }//GEN-LAST:event_btnSuspenderActionPerformed

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
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSuspender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private menulateral.SideMenuComponent menu;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
