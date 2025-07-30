/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Seguridad;
import clases.Sesion;
import menulateral.SideMenuItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import paquete.Conexion;
/**
 *
 * @author ayele
 */
public class ActualizarDentista extends javax.swing.JFrame {
    private int idUsuario;
    private String nuevaContraseña = null;
private boolean seCambioContraseña = false;

    Conexion conexion = new Conexion();
    /**
     * Creates new form ActualizarDentista
     * @param idUsuario
     */
    public ActualizarDentista(int idUsuario) {
        initComponents();
        cargarGeneros();
        cargarTurno();
        cargarDatosDoctor(idUsuario);
        this.idUsuario=Sesion.getIdUsuario();
        
         
         
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
        
        menu.setMenuItemAction("Ayuda", e -> {
                        new Ayuda().setVisible(true);
                        this.dispose();
                    });
        menu.setMenuItemAction("Gestionar citas", e -> {
                        new GestionarCitas().setVisible(true);
                        this.dispose();
                    });
    
    }
     private void cargarTurno() {
    Conexion conn = new Conexion(); // Tu clase personalizada de conexión
    ResultSet rs = conn.showColumnsLike("asistente", "turno"); // Usamos el método genérico

    try {
        cmbTurno.removeAllItems();

        if (rs != null && rs.next()) {
            // Obtenemos el valor del tipo ENUM desde la columna 'Type'
            String enumValues = rs.getString("Type");
            // Removemos los paréntesis y dividimos los valores
            enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.lastIndexOf(")"));
            String[] valores = enumValues.split(",");

            for (String valor : valores) {
                valor = valor.replace("'", "").trim(); // Limpiamos comillas simples y espacios
                cmbTurno.addItem(valor); // Agregamos al combo
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "❌ Error al cargar los turnos.");
    }
}

    private void cargarDatosDoctor(int idUsuario) {
    try {
        
        // Primero obtenemos los datos generales del usuario
        ResultSet rsUsuario = conexion.buscarPorId("usuario", "id_usuario", idUsuario);
        if (rsUsuario != null && rsUsuario.next()) {
            txtNombre.setText(rsUsuario.getString("nombre"));
            txtApeP.setText(rsUsuario.getString("apellidoP"));
            txtApeM.setText(rsUsuario.getString("apellidoM"));
            txtCorreo.setText(rsUsuario.getString("correo"));
            String genero = rsUsuario.getString("genero");
            cmbGenero.setSelectedItem(genero);  // Asegura seleccionar correctamente
           
       
        }

        if (rsUsuario != null) rsUsuario.close();

        // Ahora obtenemos los datos específicos del doctor
        ResultSet rsDoctor = conexion.buscarPorId("dentista", "id_usuario", idUsuario);
        if (rsDoctor != null && rsDoctor.next()) {
            txtEspecialidad.setText(rsDoctor.getString("especialidad"));
            txtCedulaProfesional.setText(rsDoctor.getString("cedula_profesional"));
            String turno=rsDoctor.getString("turno");
            cmbTurno.setSelectedItem(turno);
        }

        if (rsDoctor != null) rsDoctor.close();


    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar datos del dentista: " + e.getMessage());
    }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApeP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApeM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCedulaProfesional = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnActuContraseña = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbTurno = new javax.swing.JComboBox<>();
        menu = new menulateral.SideMenuComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 247, 229));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actualizar Datos del Dentista");

        jLabel1.setText("Nombre(s)");

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel7.setText("Correo");

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel8.setText("Cédula Profesional");

        label.setText("Especialidad");

        jLabel11.setText("Género");

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnActualizar.setBackground(new java.awt.Color(216, 173, 130));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnActuContraseña.setBackground(new java.awt.Color(216, 173, 130));
        btnActuContraseña.setText("Cambiar Contraseña");
        btnActuContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuContraseñaActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(216, 173, 130));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel9.setText("Cédula Profesional");

        cmbTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        menu.setBackgroundColor(new java.awt.Color(212, 231, 238));
        menu.setHoverColor(new java.awt.Color(132, 157, 162));
        menu.setModel(new menulateral.SideMenuModel() {{ addItem(new menulateral.SideMenuItem("Inicio", "/icons/home.png", "Ir a Inicio")); addItem(new menulateral.SideMenuItem("Mi perfil", "C:\\Users\\ayele\\Downloads\\usuario (1).png", "Ir a mi perfil ")); addItem(new menulateral.SideMenuItem("Tratamientos", "C:\\Users\\ayele\\Downloads\\procedimiento.png", "Ir a ver los tratamientos ")); addItem(new menulateral.SideMenuItem("Servicios", "C:\\Users\\ayele\\Downloads\\dental (1).png", "Ir a ver los servicios")); addItem(new menulateral.SideMenuItem("Citas", "C:\\Users\\ayele\\Downloads\\cita (2).png", "Ir a citas")); addItem(new menulateral.SideMenuItem("Pagos", "C:\\Users\\ayele\\Downloads\\pago.png", "Ir a pagos")); addItem(new menulateral.SideMenuItem("Configuración", "/icons/config.png", "Ir a configuración")); menulateral.SideMenuItem item8161 = getItem(6); menulateral.SideMenuItem item440 = new menulateral.SideMenuItem("Ver Perfiles de Usuario", "C:\\Users\\ayele\\Downloads\\usuarios.png"); item8161.addChild(item440); addItem(new menulateral.SideMenuItem("Gestionar Citas", "C:\\Users\\ayele\\Downloads\\chequeo-dental.png")); addItem(new menulateral.SideMenuItem("Ayuda", "/icons/ayuda.png", "Ayuda")); addItem(new menulateral.SideMenuItem("Cerrar Sesión", "C:\\Users\\ayele\\Downloads\\cerrar-sesion.png", "Ir al login")); }});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApeP, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApeM))
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(73, 73, 73)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCedulaProfesional)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEspecialidad)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbTurno, 0, 264, Short.MAX_VALUE))
                                    .addGap(10, 10, 10))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(173, 173, 173)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnActuContraseña)
                                    .addGap(90, 90, 90)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(75, 75, 75)
                                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cmbTurno))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActuContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarGeneros() {
    Conexion conn = new Conexion(); // Tu clase personalizada de conexión
    ResultSet rs = conn.showColumnsLike("usuario", "genero"); // Usamos el método genérico

    try {
        cmbGenero.removeAllItems();

        if (rs != null && rs.next()) {
            // Obtenemos el valor del tipo ENUM desde la columna 'Type'
            String enumValues = rs.getString("Type");
            // Removemos los paréntesis y dividimos los valores
            enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.lastIndexOf(")"));
            String[] valores = enumValues.split(",");

            for (String valor : valores) {
                valor = valor.replace("'", "").trim(); // Limpiamos comillas simples y espacios
                cmbGenero.addItem(valor); // Agregamos al combo
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "❌ Error al cargar los géneros.");
    }
}
    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (txtNombre.getText().trim().isEmpty() ||
        txtApeP.getText().trim().isEmpty() ||
        txtApeM.getText().trim().isEmpty() ||
        txtCorreo.getText().trim().isEmpty() ||
        txtCedulaProfesional.getText().trim().isEmpty() ||
        txtEspecialidad.getText().trim().isEmpty()){

        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos, incluyendo la contraseña actual.");
        return;
    }

    try {
        int idUsu = Sesion.getIdUsuario();


        // Mapear datos del usuario
        Map<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("nombre", txtNombre.getText().trim());
        datosUsuario.put("apellidoP", txtApeP.getText().trim());
        datosUsuario.put("apellidoM", txtApeM.getText().trim());
        datosUsuario.put("correo", txtCorreo.getText().trim());
        datosUsuario.put("genero", cmbGenero.getSelectedItem().toString());

        // Si la nueva contraseña fue establecida en otra interfaz, agregarla
        if (Sesion.nuevaContraseña != null && !Sesion.nuevaContraseña.isBlank()) {
            datosUsuario.put("contraseña", Sesion.nuevaContraseña);
            Sesion.nuevaContraseña = null; // Limpiar después de usarla
        }

        // Actualizar en la base de datos
        conexion.actualizarPorCampo("usuario", datosUsuario, "id_usuario", idUsu);
        // Mapear y actualizar doctor
        Map<String, Object> datosDentista = new HashMap<>();
        datosDentista.put("cedula_profesional", txtCedulaProfesional.getText().trim());
        datosDentista.put("especialidad", txtEspecialidad.getText().trim());
        datosDentista.put("turno", cmbTurno.getSelectedItem().toString());

        conexion.actualizarPorCampo("doctor", datosDentista, "id_doctor", idUsu);

        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");

        // Resetear variables de cambio de contraseña
        seCambioContraseña = false;
        nuevaContraseña = null;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + e.getMessage());
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnActuContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuContraseñaActionPerformed
        // TODO add your handling code here:
        new ActualizarContraseña(this, true).setVisible(true);
    }//GEN-LAST:event_btnActuContraseñaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        new MiPerfil().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActuContraseña;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    private menulateral.SideMenuComponent menu;
    private javax.swing.JTextField txtApeM;
    private javax.swing.JTextField txtApeP;
    private javax.swing.JTextField txtCedulaProfesional;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
