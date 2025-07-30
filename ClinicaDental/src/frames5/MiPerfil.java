/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Sesion;
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import menulateral.SideMenuItem;

/**
 *
 * @author ayele
 */
public class MiPerfil extends javax.swing.JFrame {

    /**
     * Creates new form MiPerfil
     */
    public MiPerfil() {
        initComponents();
        cargarNombrePaciente();
        ImageIcon icono=new ImageIcon(getClass().getResource("/imagenes/mi-perfil.png"));
        Image imagenEscalada=icono.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenEscalada);
        lblFoto.setIcon(iconoFinal);
        
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagenes/ver-usuario.png"));
        Image imagen = iconoOriginal.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoVer = new ImageIcon(imagen);
        btnVerInformacion.setIcon(iconoVer);
        btnVerInformacion.setText("Ver mi información");
        btnVerInformacion.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnVerInformacion.setVerticalTextPosition(SwingConstants.CENTER); 
        
        ImageIcon iconoA = new ImageIcon(getClass().getResource("/imagenes/editar-perfil.png"));
        Image imagenAct = iconoA.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoEditar = new ImageIcon(imagenAct);
        btnEditarInformacion.setIcon(iconoEditar);
        btnEditarInformacion.setText("Editar mi información");
        btnEditarInformacion.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnEditarInformacion.setVerticalTextPosition(SwingConstants.CENTER); 
        
        ImageIcon iconoB = new ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"));
        Image imagenVer = iconoB.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoSalir = new ImageIcon(imagenVer);
        btnSalir.setIcon(iconoSalir);
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnSalir.setVerticalTextPosition(SwingConstants.CENTER); 
        
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
         
         if (Sesion.getRol() == 2) { // Paciente
    btnExpediente.setVisible(true);
    btnExpediente.setEnabled(true);
} else {
    btnExpediente.setVisible(false);
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
        lblBienvenida = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnVerInformacion = new javax.swing.JButton();
        btnEditarInformacion = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        menu = new menulateral.SideMenuComponent();
        btnExpediente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 247, 229));

        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnVerInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerInformacionActionPerformed(evt);
            }
        });

        btnEditarInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarInformacionActionPerformed(evt);
            }
        });

        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        menu.setBackgroundColor(new java.awt.Color(212, 231, 238));
        menu.setHoverColor(new java.awt.Color(132, 157, 162));
        menu.setModel(new menulateral.SideMenuModel() {{ addItem(new menulateral.SideMenuItem("Inicio", "/icons/home.png", "Ir a Inicio")); addItem(new menulateral.SideMenuItem("Mi perfil", "C:\\Users\\ayele\\Downloads\\usuario (1).png", "Ir a mi perfil ")); addItem(new menulateral.SideMenuItem("Tratamientos", "C:\\Users\\ayele\\Downloads\\procedimiento.png", "Ir a ver los tratamientos ")); addItem(new menulateral.SideMenuItem("Servicios", "C:\\Users\\ayele\\Downloads\\dental (1).png", "Ir a ver los servicios")); addItem(new menulateral.SideMenuItem("Citas", "C:\\Users\\ayele\\Downloads\\cita (2).png", "Ir a citas")); addItem(new menulateral.SideMenuItem("Pagos", "C:\\Users\\ayele\\Downloads\\pago.png", "Ir a pagos")); addItem(new menulateral.SideMenuItem("Configuración", "/icons/config.png", "Ir a configuración")); menulateral.SideMenuItem item7494 = getItem(6); menulateral.SideMenuItem item4726 = new menulateral.SideMenuItem("Ver Perfiles de Usuario", "C:\\Users\\ayele\\Downloads\\usuarios.png"); item7494.addChild(item4726); addItem(new menulateral.SideMenuItem("Gestionar citas", "C:\\Users\\ayele\\Downloads\\chequeo-dental.png")); addItem(new menulateral.SideMenuItem("Ayuda", "/icons/ayuda.png", "Ayuda")); addItem(new menulateral.SideMenuItem("Cerrar Sesión", "C:\\Users\\ayele\\Downloads\\cerrar-sesion.png", "Ir al login")); }});

        btnExpediente.setText("Ver mi expediente");
        btnExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpedienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVerInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnVerInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditarInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerInformacionActionPerformed
        // TODO add your handling code here:
        int rol = Sesion.getRol(); // Obtener el rol del usuario actual
        int idUsuario = Sesion.getIdUsuario(); // Obtener ID del usuario
    switch (rol) {
        case 4:
            new VerDentista(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 2:
            new VerPaciente(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 3:
            new VerAsistente(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 1:
            new VerDatosAdministrador(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 5:
            new VerRecepcionista(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        default:
            JOptionPane.showMessageDialog(this, "Rol no reconocido o inválido.");
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
    }

    this.dispose(); // Cierra la ventana actual si es necesario
    }//GEN-LAST:event_btnVerInformacionActionPerformed

    private void btnEditarInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarInformacionActionPerformed
        // TODO add your handling code here:
        int rol = Sesion.getRol(); // Obtener el rol del usuario actual
        int idUsuario = Sesion.getIdUsuario(); // Obtener ID del usuario
    switch (rol) {
        case 4:
            new ActualizarDentista(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 2:
            new ActualizarPaciente(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        case 3:
            new ActualizarAsistente(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
            case 1:
            {
                try {
                    new ActualizarAdministrador(idUsuario).setVisible(true);
                    this.dispose(); // Cierra la ventana actual si es necesario
                } catch (SQLException ex) {
                    Logger.getLogger(MiPerfil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

        case 5:
            new ActualizarRecepcionista(idUsuario).setVisible(true);
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
        default:
            JOptionPane.showMessageDialog(this, "Rol no reconocido o inválido.");
            this.dispose(); // Cierra la ventana actual si es necesario
            break;
            
    }

    
    }//GEN-LAST:event_btnEditarInformacionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        new LoginConFondo().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpedienteActionPerformed
        try {
            // TODO add your handling code here:
            new Expediente().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MiPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnExpedienteActionPerformed

    private void cargarNombrePaciente(){
        String nombre = Sesion.getNombreUsuario();
        String correo = Sesion.getCorreo();

    if (nombre != null && !nombre.isEmpty()||correo!=null&&!correo.isEmpty()) {
        lblBienvenida.setText("¡Bienvenido, " + nombre + "!");
        lblCorreo.setText(correo);
    } else {
        lblBienvenida.setText("Bienvenido, usuario");
        lblCorreo.setText(correo);
    }
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
            java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiPerfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarInformacion;
    private javax.swing.JButton btnExpediente;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerInformacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFoto;
    private menulateral.SideMenuComponent menu;
    // End of variables declaration//GEN-END:variables
}
