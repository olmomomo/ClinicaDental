/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Procedimiento;
import clases.Sesion;
import clases.Tratamiento;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JRadioButton;
import menulateral.SideMenuItem;
import paquete.Conexion;

/**
 *
 * @author ayele
 */
public class Citas extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    private int idUsuario;
    private List<Procedimiento> listaProcedimientos = new ArrayList<>();
    private final List<Tratamiento> listaTratamientos = new ArrayList<>();
    /**
     * Creates new form Citas
     */
    public Citas() {
        initComponents();
        cargarDentistas();
        cargarProcedimientos();
        cargarTratamientos();
        cargarServicios();
        ImageIcon icono=new ImageIcon(getClass().getResource("/imagenes/buscar.png"));
        Image imagenEscalada=icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenEscalada);
        btnBuscar.setIcon(iconoFinal);
        
         for (SideMenuItem item : menu.getModel().getItems()) {
        if ("Configuración".equals(item.getText())) { // Buscamos el padre
            System.out.println("Clase del item: " + item.getClass().getName());
System.out.println("Clase del child: " + item.getChildren().get(0).getClass().getName());

            for (SideMenuItem child : item.getChildren()) {
                if ("Ver Perfiles de Usuarios".equals(child.getText())) {

                    // Agregamos el actionListener directamente al child
                    menu.setMenuItemAction("Ver Perfiles de Usuarios", e -> {
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
        
        menu.setMenuItemAction("Procedimientos", e -> {
                        new Pagos().setVisible(true);
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

    private void cargarDentistas() {
    try {
        cmbNomEspe.removeAllItems();

        // 1. Traer todos los usuarios con rol = 4 (dentistas)
        ResultSet usuarios = conexion.buscarPorCampos("usuario", new String[]{"id_rol"}, new Object[]{4});
        while (usuarios.next()) {
            int idUsuario = usuarios.getInt("id_usuario");
            String nombre = usuarios.getString("nombre");

            // 2. Buscar en dentista por ese id_usuario
            ResultSet dentista = conexion.buscarPorCampos("dentista", new String[]{"id_usuario"}, new Object[]{idUsuario});
            if (dentista.next()) {
                String especialidad = dentista.getString("especialidad");
                cmbNomEspe.addItem(idUsuario + "|" + nombre + " - " + especialidad);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar dentistas: " + e.getMessage());
    }
}

    private void cargarTratamientos() {
    try {
        listaTratamientos.clear();
        ResultSet rs = conexion.consultarTodo("tratamiento");
        while (rs.next()) {
            int idServicio = rs.getInt("id_servicio");
            int idProcedimiento = rs.getInt("id_procedimiento");
            listaTratamientos.add(new Tratamiento(idServicio, idProcedimiento));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar tratamientos: " + e.getMessage());
    }
}

    private void cargarServicios() {
    try {
        cmbServicio.removeAllItems();
        ResultSet rs = conexion.consultarTodo("servicio");
        while (rs.next()) {
            int id = rs.getInt("id_servicio");
            String nombre = rs.getString("nombre");
            cmbServicio.addItem(id + "|" + nombre);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar servicios: " + e.getMessage());
    }
}
    
    private int obtenerIdDeCombo(String item) {
    return Integer.parseInt(item.split("\\|")[0]);
}

    private int obtenerIdDentistaDesdeIdUsuario(int idUsuario) {
    try {
        ResultSet rs = conexion.buscarPorCampos("dentista", new String[]{"id_usuario"}, new Object[]{idUsuario});
        if (rs != null && rs.next()) {
            return rs.getInt("id_dentista");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}

    private void cargarProcedimientos() {
    try {
        listaProcedimientos.clear();
        ResultSet rs = conexion.consultarTodo("procedimiento");
        while (rs.next()) {
            int id = rs.getInt("id_procedimiento");
            String nombre = rs.getString("nombre");
            listaProcedimientos.add(new Procedimiento(id, nombre));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar procedimientos: " + e.getMessage());
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

        grupoHoras = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        menu = new menulateral.SideMenuComponent();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnVerHorarios = new javax.swing.JButton();
        rb14 = new javax.swing.JRadioButton();
        rb13 = new javax.swing.JRadioButton();
        rb15 = new javax.swing.JRadioButton();
        rb17 = new javax.swing.JRadioButton();
        rb9 = new javax.swing.JRadioButton();
        rb10 = new javax.swing.JRadioButton();
        rb11 = new javax.swing.JRadioButton();
        rb12 = new javax.swing.JRadioButton();
        rb16 = new javax.swing.JRadioButton();
        rb18 = new javax.swing.JRadioButton();
        btnNueva = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cmbNomEspe = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbProcedimiento = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cmbServicio = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 247, 229));

        menu.setBackgroundColor(new java.awt.Color(212, 231, 238));
        menu.setHoverColor(new java.awt.Color(132, 157, 162));
        menu.setModel(new menulateral.SideMenuModel() {{ addItem(new menulateral.SideMenuItem("Inicio", "/icons/home.png", "Ir a Inicio")); addItem(new menulateral.SideMenuItem("Mi perfil", "C:\\Users\\ayele\\Downloads\\usuario (1).png", "Ir a mi perfil ")); addItem(new menulateral.SideMenuItem("Tratamientos", "C:\\Users\\ayele\\Downloads\\procedimiento.png", "Ir a ver los tratamientos ")); addItem(new menulateral.SideMenuItem("Servicios", "C:\\Users\\ayele\\Downloads\\dental (1).png", "Ir a ver los servicios")); addItem(new menulateral.SideMenuItem("Citas", "C:\\Users\\ayele\\Downloads\\cita (2).png", "Ir a citas")); addItem(new menulateral.SideMenuItem("Pagos", "C:\\Users\\ayele\\Downloads\\pago.png", "Ir a pagos")); addItem(new menulateral.SideMenuItem("Configuración", "/icons/config.png", "Ir a configuración")); menulateral.SideMenuItem item3226 = getItem(6); menulateral.SideMenuItem item9918 = new menulateral.SideMenuItem("Ver Perfiles de Usuario", "C:\\Users\\ayele\\Downloads\\usuarios.png"); item3226.addChild(item9918); addItem(new menulateral.SideMenuItem("Gestionar citas", "C:\\Users\\ayele\\Downloads\\chequeo-dental.png")); addItem(new menulateral.SideMenuItem("Ayuda", "/icons/ayuda.png", "Ayuda")); addItem(new menulateral.SideMenuItem("Cerrar Sesión", "C:\\Users\\ayele\\Downloads\\cerrar-sesion.png", "Ir al login")); }});

        jPanel2.setBackground(new java.awt.Color(132, 157, 162));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre ");

        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dirección");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Teléfono");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(txtNombrePaciente))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombrePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(132, 157, 162));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la cita", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de la cita ");

        btnVerHorarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerHorarios.setText("Ver horarios disponibles");

        grupoHoras.add(rb14);
        rb14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb14.setForeground(new java.awt.Color(255, 255, 255));
        rb14.setText("14:00 - 15:00");
        rb14.setActionCommand("9:00-10:00");
        rb14.setBorderPainted(true);

        grupoHoras.add(rb13);
        rb13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb13.setForeground(new java.awt.Color(255, 255, 255));
        rb13.setText("13:00 - 14:00");
        rb13.setBorderPainted(true);
        rb13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb13ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb15);
        rb15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb15.setForeground(new java.awt.Color(255, 255, 255));
        rb15.setText("15:00 - 16:00");
        rb15.setBorderPainted(true);
        rb15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb15ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb17);
        rb17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb17.setForeground(new java.awt.Color(255, 255, 255));
        rb17.setText("17:00 - 18:00");
        rb17.setBorderPainted(true);
        rb17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb17ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb9);
        rb9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb9.setForeground(new java.awt.Color(255, 255, 255));
        rb9.setText("09:00 - 10:00");
        rb9.setActionCommand("9:00-10:00");
        rb9.setBorderPainted(true);

        grupoHoras.add(rb10);
        rb10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb10.setForeground(new java.awt.Color(255, 255, 255));
        rb10.setText("10:00-11:00");
        rb10.setBorderPainted(true);
        rb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb10ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb11);
        rb11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb11.setForeground(new java.awt.Color(255, 255, 255));
        rb11.setText("11:00-12:00");
        rb11.setBorderPainted(true);
        rb11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb11ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb12);
        rb12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb12.setForeground(new java.awt.Color(255, 255, 255));
        rb12.setText("12:00-13:00");
        rb12.setBorderPainted(true);
        rb12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb12ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb16);
        rb16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb16.setForeground(new java.awt.Color(255, 255, 255));
        rb16.setText("16:00 - 17:00");
        rb16.setBorderPainted(true);
        rb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb16ActionPerformed(evt);
            }
        });

        grupoHoras.add(rb18);
        rb18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rb18.setForeground(new java.awt.Color(255, 255, 255));
        rb18.setText("18:00 - 19:00");
        rb18.setBorderPainted(true);
        rb18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb18ActionPerformed(evt);
            }
        });

        btnNueva.setText("Nueva cita");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Matutino");

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("Verpertino");

        btnCancelar.setText("Cancelar cambios");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rb9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(rb14, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rb10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(rb13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rb12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(rb11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rb16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rb17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rb18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rb15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(92, 92, 92)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rb9)
                                    .addComponent(rb14)))
                            .addComponent(jLabel7)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rb15)
                                        .addGap(18, 18, 18)
                                        .addComponent(rb16))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rb10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb13)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(76, 103, 112));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reservar cita");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(132, 157, 162));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del doctor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Servicio");

        cmbNomEspe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Doctor y especialidad");

        cmbProcedimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProcedimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProcedimientoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tratamiento/Procedimiento");

        cmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNomEspe, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 42, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(85, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(cmbNomEspe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(589, 589, 589)))
                .addGap(2579, 2579, 2579)
                .addComponent(jSeparator1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb13ActionPerformed

    private void rb15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb15ActionPerformed

    private void rb17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb17ActionPerformed

    private void rb10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb10ActionPerformed

    private void rb11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb11ActionPerformed

    private void rb12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb12ActionPerformed

    private void rb16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb16ActionPerformed

    private void rb18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb18ActionPerformed

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        // TODO add your handling code here:
        // Habilitar el botón de ver horarios
    btnVerHorarios.setEnabled(true);

    // Habilitar el calendario para elegir fecha
    calendario.setEnabled(true);


    // Si usas botones de horario (JRadioButtons), puedes deseleccionarlos:
    grupoHoras.clearSelection();
    Enumeration<AbstractButton> botones = grupoHoras.getElements();
while (botones.hasMoreElements()) {
    AbstractButton boton = botones.nextElement();
    boton.setEnabled(true);  // <-- esto es lo que te faltaba
}

    }//GEN-LAST:event_btnNuevaActionPerformed

 private void buscarPaciente(String nombreCompletoBuscado) {
    try {
        // Paso 1: Obtener todos los usuarios con rol de paciente (id_rol = 2)
        ResultSet rsUsuarios = conexion.buscarPorCampos("usuario", new String[]{"id_rol"}, new Object[]{2});
        boolean encontrado = false;

        while (rsUsuarios.next()) {
            int idUsuario = rsUsuarios.getInt("id_usuario");
            String nombre = rsUsuarios.getString("nombre");
            String apellidoP = rsUsuarios.getString("apellidoP");
            String apellidoM = rsUsuarios.getString("apellidoM");

            String nombreCompletoBD = nombre + " " + apellidoP + " " + apellidoM;

            // Comparar ignorando mayúsculas y espacios extras
            if (nombreCompletoBD.trim().equalsIgnoreCase(nombreCompletoBuscado.trim())) {
                // Ya tenemos el idUsuario, ahora buscamos al paciente
                ResultSet rsPaciente = conexion.buscarPorCampos("paciente", new String[]{"id_usuario"}, new Object[]{idUsuario});
                if (rsPaciente != null && rsPaciente.next()) {
                    txtDireccion.setText(rsPaciente.getString("direccion"));
                    txtTelefono.setText(rsPaciente.getString("telefono"));
                    txtDireccion.setEditable(false);
                    txtTelefono.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró información en la tabla paciente.");
                    txtDireccion.setText("");
                    txtTelefono.setText("");
                }

                encontrado = true;
                break; // ya no buscamos más
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            txtDireccion.setText("");
            txtTelefono.setText("");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al buscar paciente: " + e.getMessage());
    }
}


    private int obtenerIdPacienteDesdeNombreCompleto(String nombreCompleto) {
    try {
        // Buscar en usuario por nombre completo concatenado
        ResultSet rsUsuarios = conexion.buscarPorCampos("usuario", new String[]{"nombre", "apellidoP", "apellidoM"}, new Object[]{"valorNombre", "valorApellidoP", "valorApellidoM"});
        // Pero como no puedes buscar con concatenación directamente, deberás buscar por cada campo por separado o buscar todos y filtrar en Java.

        // Alternativa: Traer todos los usuarios con rol paciente y comparar en Java:
        ResultSet rs = conexion.buscarPorCampos("usuario", new String[]{"id_rol"}, new Object[]{2}); // Rol paciente = 2
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellidoP = rs.getString("apellidoP");
            String apellidoM = rs.getString("apellidoM");
            String nombreCompletoBD = nombre + " " + apellidoP + " " + apellidoM;
            if (nombreCompletoBD.equalsIgnoreCase(nombreCompleto.trim())) {
                int idUsuario = rs.getInt("id_usuario");
                // Buscar id_paciente con id_usuario
                ResultSet rsPaciente = conexion.buscarPorCampos("paciente", new String[]{"id_usuario"}, new Object[]{idUsuario});
                if (rsPaciente != null && rsPaciente.next()) {
                    return rsPaciente.getInt("id_paciente");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}


    private String getHoraSeleccionada() {
    Enumeration<AbstractButton> botones = grupoHoras.getElements();
    while (botones.hasMoreElements()) {
        AbstractButton boton = botones.nextElement();
        if (boton.isSelected()) {
            String texto = boton.getText(); // Ej: "09:00 - 10:00"
            // Extraemos la hora de inicio (antes del espacio)
            String horaInicio = texto.split("-")[0].trim(); // "09:00"
            return horaInicio;
        }
    }
    return null;
}




public int obtenerUltimoIdCita() {
    int id = -1;
    try {
        ResultSet rs = conexion.ejecutarConsulta("SELECT MAX(id_cita) AS ultimo FROM cita");
        if (rs != null && rs.next()) {
            id = rs.getInt("ultimo");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return id;
}


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here
    try {
        int idPaciente = obtenerIdPacienteDesdeNombreCompleto(txtNombrePaciente.getText().trim());
if (idPaciente == -1) {
    JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
    return;
}
        int idUsuarioDentista = obtenerIdDeCombo((String) cmbNomEspe.getSelectedItem());
        int idDentista = obtenerIdDentistaDesdeIdUsuario(idUsuarioDentista);
        if (idDentista == -1) {
            JOptionPane.showMessageDialog(this, "Dentista no encontrado.");
            return;
        }
        int idProcedimiento = obtenerIdDeCombo((String) cmbProcedimiento.getSelectedItem());

        // Validar fecha
        java.util.Date fechaDate = calendario.getDate();
        if (fechaDate == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una fecha.");
            return;
        }
        java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());

        // Obtener y validar hora seleccionada
        String horaSeleccionada = getHoraSeleccionada();
        if (horaSeleccionada == null || horaSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una hora para la cita.");
            return;
        }
        java.sql.Time horaSQL = java.sql.Time.valueOf(horaSeleccionada + ":00");

        // Insertar en la tabla cita
        boolean okCita = conexion.insertar("cita",
                "id_paciente, id_dentista, fecha, hora, estado",
                "?, ?, ?, ?, ?",
                idPaciente, idDentista, fechaSQL, horaSQL, "Agendada");

        if (!okCita) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita.");
            return;
        }

        // Obtener el último id_cita insertado
        int idCita = obtenerUltimoIdCita();
        if (idCita == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el ID de la cita.");
            return;
        }

        // Insertar en detalle_cita
        String observaciones = ""; // Puedes usar un JTextArea si lo necesitas
        boolean okDetalle = conexion.insertar("detalle_cita",
                "id_cita, id_procedimiento, observaciones",
                "?, ?, ?",
                idCita, idProcedimiento, observaciones);

        if (okDetalle) {
            JOptionPane.showMessageDialog(this, "Cita y detalle guardados correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el detalle de la cita.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void limpiarCampos() {
    txtNombrePaciente.setText("");
    txtDireccion.setText("");
    txtTelefono.setText("");
    txtFecha.setText("");
    txtDireccion.setEditable(true);
    txtTelefono.setEditable(true);

    // Desseleccionar botones de horario
    grupoHoras.clearSelection();
    
}

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas cancelar el proceso?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
        limpiarCampos();
        // Rehabilitar calendario y grupo de horarios
        calendario.setEnabled(true);
        Enumeration<AbstractButton> botones = grupoHoras.getElements();
        while (botones.hasMoreElements()) {
            botones.nextElement().setEnabled(true);
        }
        // Deshabilitar botón nueva cita porque cancelamos el proceso
        btnNueva.setEnabled(false);
    }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
       String nombre = txtNombrePaciente.getText().trim();
    if (!nombre.isEmpty()) {
        buscarPaciente(nombre);
        
        // Después de buscar y cargar paciente, deshabilitar calendario y horarios, habilitar btnNueva
        calendario.setEnabled(false);
        // Deshabilitar todos los JRadioButtons del grupoHoras
        Enumeration<AbstractButton> botones = grupoHoras.getElements();
        while (botones.hasMoreElements()) {
            botones.nextElement().setEnabled(false);
        }
        btnNueva.setEnabled(true);
    } else {
        JOptionPane.showMessageDialog(this, "Ingresa un nombre para buscar.");
    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbProcedimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProcedimientoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmbProcedimientoActionPerformed

    private void cmbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbServicioActionPerformed
        // TODO add your handling code here:
      filtrarProcedimientosPorServicio();
    }//GEN-LAST:event_cmbServicioActionPerformed

    private void filtrarProcedimientosPorServicio() {
    cmbProcedimiento.removeAllItems();

    String itemSeleccionado = (String) cmbServicio.getSelectedItem();
    if (itemSeleccionado == null || !itemSeleccionado.contains("|")) return;

    int idServicioSeleccionado = Integer.parseInt(itemSeleccionado.split("\\|")[0]);

    // Obtener IDs de los procedimientos relacionados con ese servicio
    Set<Integer> idsProcedimientosRelacionados = new HashSet<>();
    for (Tratamiento t : listaTratamientos) {
        if (t.getIdServicio() == idServicioSeleccionado) {
            idsProcedimientosRelacionados.add(t.getIdProcedimiento());
        }
    }

    // Agregar solo los procedimientos relacionados
    for (Procedimiento p : listaProcedimientos) {
        if (idsProcedimientosRelacionados.contains(p.getId())) {
            cmbProcedimiento.addItem(p.getId() + "|" + p.getNombre());
        }
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
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Citas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnVerHorarios;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JComboBox<String> cmbNomEspe;
    private javax.swing.JComboBox<String> cmbProcedimiento;
    private javax.swing.JComboBox<String> cmbServicio;
    private javax.swing.ButtonGroup grupoHoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private menulateral.SideMenuComponent menu;
    private javax.swing.JRadioButton rb10;
    private javax.swing.JRadioButton rb11;
    private javax.swing.JRadioButton rb12;
    private javax.swing.JRadioButton rb13;
    private javax.swing.JRadioButton rb14;
    private javax.swing.JRadioButton rb15;
    private javax.swing.JRadioButton rb16;
    private javax.swing.JRadioButton rb17;
    private javax.swing.JRadioButton rb18;
    private javax.swing.JRadioButton rb9;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
