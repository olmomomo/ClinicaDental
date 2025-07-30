/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Cita;
import clases.Dentista;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.Sesion;
import java.awt.Image;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import menulateral.SideMenuItem;
import paquete.Conexion;
import javax.swing.JTable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author ayele
 */
public class GestionarCitas extends javax.swing.JFrame {

    Conexion conexion=new Conexion();
    private List<Dentista> dentistas;  // Declaración de la lista
    private List<Cita> citas;


    /**
     * Creates new form GestionarCitas
     */
    public GestionarCitas() {
        initComponents();
        this.citas = new ArrayList<>();
    this.dentistas = new ArrayList<>();
        menu.setMenuItemAction("Inicio", e -> {
                        new Inicio().setVisible(true);
                        this.dispose();
                    });
         
         
    
for (SideMenuItem item : menu.getModel().getItems()) {
        if ("Configuración".equals(item.getText())) { // Buscamos el padre
            System.out.println("Clase del item: " + item.getClass().getName());
System.out.println("Clase del child: " + item.getChildren().get(0).getClass().getName());

            for (SideMenuItem child : item.getChildren()) {
                if ("Ver Perfil de Usuario".equals(child.getText())) {

                    // Agregamos el actionListener directamente al child
                    menu.setMenuItemAction("Ver Perfil de Usuario", e -> {
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

     menu.setMenuItemAction("Mi perfil ", e -> {
                       
                            new MiPerfil().setVisible(true);
                            this.dispose();
                        
                    });
                 
                 menu.setMenuItemAction("Citas", e -> {
                        new Inicio().setVisible(true);
                        this.dispose();
                    });
                 
                 menu.setMenuItemAction("Pagos", e -> {
                        new Pagos().setVisible(true);
                        this.dispose();
                    });
                 
                 menu.setMenuItemAction("Cerrar Sesión", e -> {
                        new LoginConFondo().setVisible(true);
                        this.dispose();
                    });
                 
                 menu.setMenuItemAction("Ayuda", e -> {
                        new Ayuda().setVisible(true);
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
                 
                 ImageIcon iconoA = new ImageIcon(getClass().getResource("/imagenes/cruz.png"));
        Image imagenAct = iconoA.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoEditar = new ImageIcon(imagenAct);
        btnCancelarCita.setIcon(iconoEditar);
        btnCancelarCita.setText("Cancelar Cita");
        btnCancelarCita.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnCancelarCita.setVerticalTextPosition(SwingConstants.CENTER); 
        
        ImageIcon iconoB = new ImageIcon(getClass().getResource("/imagenes/cita-medica.png"));
        Image imagenVer = iconoB.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoVer = new ImageIcon(imagenVer);
        btnCambiarCita.setIcon(iconoVer);
        btnCambiarCita.setText("Cambiar Cita");
        btnCambiarCita.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnCambiarCita.setVerticalTextPosition(SwingConstants.CENTER); 
        
        ImageIcon iconoC = new ImageIcon(getClass().getResource("/imagenes/eliminar cita.png"));
        Image imagenEli = iconoC.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoEliminar = new ImageIcon(imagenEli);
        btnEliminarCita.setIcon(iconoEliminar);
            btnEliminarCita.setText("Eliminar Cita");
        btnEliminarCita.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del ícono
        btnEliminarCita.setVerticalTextPosition(SwingConstants.CENTER); 
        
        
        // Llenar las tres tablas
cargarCitasPorEstado("Agendada", tablaCitasVigentes);
cargarCitasPorEstado("Cancelada", tablaCitasCanceladas);
cargarCitasPorEstado("Realizada", tablaCitasVencidas);

tablaCitasVigentes.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int fila = tablaCitasVigentes.getSelectedRow();
        if (fila != -1) {
            int idCita = (int) tablaCitasVigentes.getValueAt(fila, 0);
            cargarDetallesCita(idCita);
            habilitarBotones(true);  // habilita cambiar y cancelar
        }
    }
});

tablaCitasCanceladas.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int fila = tablaCitasCanceladas.getSelectedRow();
        if (fila != -1) {
            int idCita = (int) tablaCitasCanceladas.getValueAt(fila, 0);
            cargarDetallesCitaCancelada(idCita);
            habilitarBotones(false);  // deshabilita cambiar y cancelar
            habilitarBotonesEliminar(true);
        }
    }
});

tablaCitasVencidas.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int fila = tablaCitasVencidas.getSelectedRow();
        if (fila != -1) {
            int idCita = (int) tablaCitasVencidas.getValueAt(fila, 0);
            cargarDetallesCitaVencida(idCita);
            habilitarBotones(true);  // habilita cambiar, deshabilita cancelar si quieres
        }
    }
});


    }
    
    private void cargarDetallesCitaCancelada(int idCita) {
    try {
        // Buscar cita
        ResultSet rsCita = conexion.buscarPorId("cita", "id_cita", idCita);
        if (rsCita != null && rsCita.next()) {
            int idPaciente = rsCita.getInt("id_paciente");
            java.sql.Date fecha = rsCita.getDate("fecha");
            java.sql.Time hora = rsCita.getTime("hora");

            // Buscar datos del paciente desde la tabla paciente
            ResultSet rsPaciente = conexion.buscarPorId("paciente", "id_paciente", idPaciente);
            if (rsPaciente != null && rsPaciente.next()) {
                int idUsuario = rsPaciente.getInt("id_usuario");
                String direccion = rsPaciente.getString("direccion");
                String telefono = rsPaciente.getString("telefono");

                // Buscar nombre del usuario desde la tabla usuario
                ResultSet rsUsuario = conexion.buscarPorId("usuario", "id_usuario", idUsuario);
                String nombreCompleto = "Desconocido";
                if (rsUsuario != null && rsUsuario.next()) {
                    String nombre = rsUsuario.getString("nombre");
                    String apellidoP = rsUsuario.getString("apellidoP");
                    String apellidoM = rsUsuario.getString("apellidoM");

                    // Concatenar nombre completo
                    nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;
                }

                // Llenar campos
                txtIdCita1.setText(String.valueOf(idCita));
                txtIdPaciente1.setText(String.valueOf(idPaciente));
                txtNombrePaciente1.setText(nombreCompleto);
                txtDireccion1.setText(direccion);
                txtTelefono1.setText(telefono);
                txtFecha1.setText(fecha.toString());
                txtHora1.setText(hora.toString());
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar detalles de la cita: " + e.getMessage());
    }
}
    
    private void cargarDetallesCitaVencida(int idCita) {
    try {
        // Buscar cita
        ResultSet rsCita = conexion.buscarPorId("cita", "id_cita", idCita);
        if (rsCita != null && rsCita.next()) {
            int idPaciente = rsCita.getInt("id_paciente");
            java.sql.Date fecha = rsCita.getDate("fecha");
            java.sql.Time hora = rsCita.getTime("hora");

            // Buscar datos del paciente desde la tabla paciente
            ResultSet rsPaciente = conexion.buscarPorId("paciente", "id_paciente", idPaciente);
            if (rsPaciente != null && rsPaciente.next()) {
                int idUsuario = rsPaciente.getInt("id_usuario");
                String direccion = rsPaciente.getString("direccion");
                String telefono = rsPaciente.getString("telefono");

                // Buscar nombre del usuario desde la tabla usuario
                ResultSet rsUsuario = conexion.buscarPorId("usuario", "id_usuario", idUsuario);
                String nombreCompleto = "Desconocido";
                if (rsUsuario != null && rsUsuario.next()) {
                    String nombre = rsUsuario.getString("nombre");
                    String apellidoP = rsUsuario.getString("apellidoP");
                    String apellidoM = rsUsuario.getString("apellidoM");

                    // Concatenar nombre completo
                    nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;
                }

                // Llenar campos
                txtIdCita2.setText(String.valueOf(idCita));
                txtIdPaciente2.setText(String.valueOf(idPaciente));
                txtNombrePaciente2.setText(nombreCompleto);
                txtDireccion2.setText(direccion);
                txtTelefono2.setText(telefono);
                txtFecha2.setText(fecha.toString());
                txtHora2.setText(hora.toString());
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar detalles de la cita: " + e.getMessage());
    }
}

   private void cargarCitasPorEstado(String estado, JTable tabla) {
    try {
        // 1. Obtener citas desde la base de datos
        ResultSet rsCitas = conexion.buscarPorCampos("cita", new String[]{"estado"}, new Object[]{estado});
        
        // 2. Crear modelo de tabla con columnas necesarias
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID Cita", "Paciente", "Dentista", "Fecha", "Hora"}, 
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        // 3. Procesar cada cita
        while (rsCitas != null && rsCitas.next()) {
            int idCita = rsCitas.getInt("id_cita");
            int idPaciente = rsCitas.getInt("id_paciente");
            int idDentista = rsCitas.getInt("id_dentista");
            Date fecha = rsCitas.getDate("fecha");
            Time hora = rsCitas.getTime("hora");
            
            // Formatear hora para mostrar solo HH:mm
            String horaFormateada = new SimpleDateFormat("HH:mm").format(hora);
            
            // 4. Obtener nombre del paciente
            String nombrePaciente = obtenerNombreCompleto("paciente", idPaciente);
            
            // 5. Obtener nombre del dentista
            String nombreDentista = obtenerNombreCompleto("dentista", idDentista);
            
            // 6. Agregar fila al modelo
            model.addRow(new Object[]{
                idCita,
                nombrePaciente,
                nombreDentista,
                fecha,
                horaFormateada
            });
        }

        // 7. Asignar modelo a la tabla
        tabla.setModel(model);
        
        // 8. Ajustar ancho de columnas
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200); // Paciente
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200); // Dentista
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100); // Fecha
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);  // Hora
        }
        
        // 9. Cerrar ResultSet
        if (rsCitas != null) {
            rsCitas.close();
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, 
            "Error al cargar citas " + estado.toLowerCase() + ": " + e.getMessage(),
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

// Método auxiliar para obtener nombre completo
private String obtenerNombreCompleto(String tabla, int id) throws SQLException {
    String nombreCompleto = "No encontrado";
    
    // Determinar la tabla de referencia y el campo ID
    String campoId = tabla.equals("paciente") ? "id_paciente" : "id_dentista";
    String tablaUsuario = tabla.equals("paciente") ? "paciente" : "dentista";
    
    // 1. Obtener id_usuario desde la tabla correspondiente
    ResultSet rs = conexion.buscarPorId(tablaUsuario, campoId, id);
    if (rs != null && rs.next()) {
        int idUsuario = rs.getInt("id_usuario");
        rs.close();
        
        // 2. Obtener datos del usuario
        ResultSet rsUsuario = conexion.buscarPorId("usuario", "id_usuario", idUsuario);
        if (rsUsuario != null && rsUsuario.next()) {
            String nombre = rsUsuario.getString("nombre");
            String apellidoP = rsUsuario.getString("apellidoP");
            String apellidoM = rsUsuario.getString("apellidoM");
            
            nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;
            rsUsuario.close();
        }
    }
    
    return nombreCompleto;
}



private void cargarDetallesCita(int idCita) {
    try {
        // Buscar cita
        ResultSet rsCita = conexion.buscarPorId("cita", "id_cita", idCita);
        if (rsCita != null && rsCita.next()) {
            int idPaciente = rsCita.getInt("id_paciente");
            java.sql.Date fecha = rsCita.getDate("fecha");
            java.sql.Time hora = rsCita.getTime("hora");

            // Buscar datos del paciente desde la tabla paciente
            ResultSet rsPaciente = conexion.buscarPorId("paciente", "id_paciente", idPaciente);
            if (rsPaciente != null && rsPaciente.next()) {
                int idUsuario = rsPaciente.getInt("id_usuario");
                String direccion = rsPaciente.getString("direccion");
                String telefono = rsPaciente.getString("telefono");

                // Buscar nombre del usuario desde la tabla usuario
                ResultSet rsUsuario = conexion.buscarPorId("usuario", "id_usuario", idUsuario);
                String nombreCompleto = "Desconocido";
                if (rsUsuario != null && rsUsuario.next()) {
                    String nombre = rsUsuario.getString("nombre");
                    String apellidoP = rsUsuario.getString("apellidoP");
                    String apellidoM = rsUsuario.getString("apellidoM");

                    // Concatenar nombre completo
                    nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;
                }

                // Llenar campos
                txtIdCita.setText(String.valueOf(idCita));
                txtIdPaciente.setText(String.valueOf(idPaciente));
                txtNombrePaciente.setText(nombreCompleto);
                txtDireccion.setText(direccion);
                txtTelefono.setText(telefono);
                txtFecha.setText(fecha.toString());
                txtHora.setText(hora.toString());
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar detalles de la cita: " + e.getMessage());
    }
}



    private void habilitarBotones(boolean habilitar) {
    btnCancelarCita.setEnabled(habilitar);
    btnCambiarCita.setEnabled(habilitar);
    
}

    private void habilitarBotonesEliminar(boolean habilitar) {
    btnEliminarCita.setEnabled(habilitar);
    // usualmente el botón cambiar y cancelar no están habilitados aquí
    
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
        menu = new menulateral.SideMenuComponent();
        panelVerCitas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitasVigentes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdCita = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        txtIdPaciente = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        panelCitasVencidas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaCitasVencidas = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtIdCita2 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDireccion2 = new javax.swing.JTextArea();
        txtIdPaciente2 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtFecha2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombrePaciente2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtHora2 = new javax.swing.JTextField();
        panelCitasCanceladas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCitasCanceladas = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdCita1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDireccion1 = new javax.swing.JTextArea();
        txtIdPaciente1 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        txtFecha1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombrePaciente1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtHora1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelarCita = new javax.swing.JButton();
        btnCambiarCita = new javax.swing.JButton();
        btnEliminarCita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 247, 229));

        menu.setBackgroundColor(new java.awt.Color(212, 231, 238));
        menu.setHoverColor(new java.awt.Color(132, 157, 162));
        menu.setModel(new menulateral.SideMenuModel() {{ addItem(new menulateral.SideMenuItem("Inicio", "/icons/home.png", "Ir a Inicio")); addItem(new menulateral.SideMenuItem("Mi perfil", "C:\\Users\\ayele\\Downloads\\usuario (1).png", "Ir a mi perfil ")); addItem(new menulateral.SideMenuItem("Tratamientos", "C:\\Users\\ayele\\Downloads\\procedimiento.png", "Ir a ver los tratamientos ")); addItem(new menulateral.SideMenuItem("Servicios", "C:\\Users\\ayele\\Downloads\\dental (1).png", "Ir a ver los servicios")); addItem(new menulateral.SideMenuItem("Citas", "C:\\Users\\ayele\\Downloads\\cita (2).png", "Ir a citas")); addItem(new menulateral.SideMenuItem("Pagos", "C:\\Users\\ayele\\Downloads\\pago.png", "Ir a pagos")); addItem(new menulateral.SideMenuItem("Configuración", "/icons/config.png", "Ir a configuración")); menulateral.SideMenuItem item3632 = getItem(6); menulateral.SideMenuItem item4388 = new menulateral.SideMenuItem("Ver Perfiles de Usuario", "C:\\Users\\ayele\\Downloads\\usuarios.png"); item3632.addChild(item4388); addItem(new menulateral.SideMenuItem("Gestionar Citas", "C:\\Users\\ayele\\Downloads\\chequeo-dental.png")); addItem(new menulateral.SideMenuItem("Ayuda", "/icons/ayuda.png", "Ayuda")); addItem(new menulateral.SideMenuItem("Cerrar Sesión", "C:\\Users\\ayele\\Downloads\\cerrar-sesion.png", "Ir al login")); }});

        jPanel2.setBackground(new java.awt.Color(132, 157, 162));

        tablaCitasVigentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCitasVigentes);

        jPanel3.setBackground(new java.awt.Color(132, 157, 162));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la cita y del Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N° de Cita:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cod. Paciente:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dirección:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Teléfono:");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane2.setViewportView(txtDireccion);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hora");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPaciente))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdCita, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(346, 346, 346))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelVerCitas.addTab("Citas Vigentes", jPanel2);

        panelCitasVencidas.setBackground(new java.awt.Color(132, 157, 162));

        tablaCitasVencidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Paciente", "Fecha cita", "Hora cita"
            }
        ));
        jScrollPane5.setViewportView(tablaCitasVencidas);

        jPanel6.setBackground(new java.awt.Color(132, 157, 162));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la cita y del Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("N° de Cita:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Cod. Paciente:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Dirección:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Teléfono:");

        txtDireccion2.setColumns(20);
        txtDireccion2.setRows(5);
        jScrollPane6.setViewportView(txtDireccion2);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Fecha:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nombre:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Hora");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPaciente2))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdCita2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombrePaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(346, 346, 346))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6)))))
                .addGap(16, 16, 16))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtIdCita2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(txtHora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(txtNombrePaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCitasVencidasLayout = new javax.swing.GroupLayout(panelCitasVencidas);
        panelCitasVencidas.setLayout(panelCitasVencidasLayout);
        panelCitasVencidasLayout.setHorizontalGroup(
            panelCitasVencidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCitasVencidasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane5)
                .addGap(22, 22, 22))
            .addGroup(panelCitasVencidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(panelCitasVencidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCitasVencidasLayout.setVerticalGroup(
            panelCitasVencidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCitasVencidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelVerCitas.addTab("Citas Vencidas", panelCitasVencidas);

        panelCitasCanceladas.setBackground(new java.awt.Color(132, 157, 162));

        tablaCitasCanceladas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Paciente", "Fecha cita", "Hora cita"
            }
        ));
        jScrollPane3.setViewportView(tablaCitasCanceladas);

        jPanel5.setBackground(new java.awt.Color(132, 157, 162));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la cita y del Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("N° de Cita:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cod. Paciente:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Dirección:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Teléfono:");

        txtDireccion1.setColumns(20);
        txtDireccion1.setRows(5);
        jScrollPane4.setViewportView(txtDireccion1);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fecha:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hora");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPaciente1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdCita1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombrePaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(346, 346, 346))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCita1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(txtHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txtNombrePaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCitasCanceladasLayout = new javax.swing.GroupLayout(panelCitasCanceladas);
        panelCitasCanceladas.setLayout(panelCitasCanceladasLayout);
        panelCitasCanceladasLayout.setHorizontalGroup(
            panelCitasCanceladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCitasCanceladasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCitasCanceladasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(panelCitasCanceladasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCitasCanceladasLayout.setVerticalGroup(
            panelCitasCanceladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCitasCanceladasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelVerCitas.addTab("Citas canceladas", panelCitasCanceladas);

        jPanel4.setBackground(new java.awt.Color(132, 157, 162));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gestión de citas ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(14, 14, 14))
        );

        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        btnCambiarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCitaActionPerformed(evt);
            }
        });

        btnEliminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelVerCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCambiarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(panelVerCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCancelarCita, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnCambiarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCitaActionPerformed
        // TODO add your handling code here:
        try {
        int idCita = Integer.parseInt(txtIdCita.getText());
        boolean actualizado = conexion.actualizarPorCampo(
            "cita",
            Map.of("estado", "Cancelada"),
            "id_cita",
            idCita
        );
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Cita cancelada correctamente.");
            recargarTablas();
            limpiarCamposDetalle();
        } else {
            JOptionPane.showMessageDialog(this, "Error al cancelar la cita.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCancelarCitaActionPerformed

    private void eliminarCitaSiCancelada(int idCita) {
    try {
        // 1. Buscar cita por id
        ResultSet rs = conexion.buscarPorId("cita", "id_cita", idCita);
        if (rs == null || !rs.next()) {
            JOptionPane.showMessageDialog(this, "No se encontró la cita.");
            if (rs != null) rs.close();
            return;
        }

        String estado = rs.getString("estado");
        rs.close();

        // 2. Verificar que la cita esté cancelada
        if (!"cancelada".equalsIgnoreCase(estado)) {
            JOptionPane.showMessageDialog(this, "Solo se pueden eliminar citas canceladas.");
            return;
        }

        // 3. Eliminar detalles en detalle_cita
        boolean detallesEliminados = conexion.eliminar("detalle_cita", "id_cita = ?", idCita);
        if (!detallesEliminados) {
            JOptionPane.showMessageDialog(this, "No se pudieron eliminar los detalles de la cita.");
            return;
        }

        // 4. Eliminar la cita
        boolean citaEliminada = conexion.eliminar("cita", "id_cita = ?", idCita);
        if (!citaEliminada) {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la cita.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");

        // Aquí recarga tus datos o tablas
        recargarListasDesdeBD();
        recargarTablas();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar la cita: " + ex.getMessage());
    }
}

    private void btnCambiarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCitaActionPerformed
        // TODO add your handling code here:
        try {
        int filaSeleccionada = tablaCitasVigentes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita de la tabla.");
            return;
        }

        // 1. Obtener ID directamente de la base de datos para asegurar consistencia
        Object valorId = tablaCitasVigentes.getValueAt(filaSeleccionada, 0);
        int idCita;
        try {
            idCita = Integer.parseInt(valorId.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de la cita no es válido.");
            return;
        }

        // 2. Buscar la cita directamente en la base de datos
        ResultSet rsCita = conexion.buscarPorId("cita", "id_cita", idCita);
        if (rsCita == null || !rsCita.next()) {
            JOptionPane.showMessageDialog(this, "No se encontró la cita en la base de datos.");
            if (rsCita != null) rsCita.close();
            return;
        }

        // 3. Crear objeto Cita con datos frescos de la BD
        Cita citaActual = new Cita();
        citaActual.setIdCita(rsCita.getInt("id_cita"));
        citaActual.setIdPaciente(rsCita.getInt("id_paciente"));
        citaActual.setIdDentista(rsCita.getInt("id_dentista"));
        citaActual.setFecha(rsCita.getDate("fecha"));
        citaActual.setHora(rsCita.getTime("hora").toString());
        citaActual.setEstado(rsCita.getString("estado"));
        rsCita.close();

        // 4. Buscar dentista en la lista (asegurando que esté cargada)
        if (dentistas == null || dentistas.isEmpty()) {
            recargarListasDesdeBD();
        }

        Dentista dentistaActual = dentistas.stream()
                .filter(d -> d.getId() == citaActual.getIdDentista())
                .findFirst()
                .orElse(null);

        if (dentistaActual == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el dentista asignado.");
            return;
        }

        // 5. Mostrar diálogo de cambio
        CambiarCita dialog = new CambiarCita(
            this, 
            true, 
            citaActual, 
            dentistaActual,
            dentistas, 
            citas
        );
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.isGuardado()) {
            recargarListasDesdeBD();
            recargarTablas();
            limpiarCamposDetalle();
            JOptionPane.showMessageDialog(this, "Cita modificada correctamente.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCambiarCitaActionPerformed

    
    
    private void recargarListasDesdeBD() {
    citas.clear();
    dentistas.clear();

    try {
        // Cargar citas
        ResultSet rsCitas = conexion.consultarTodo("cita");
        while (rsCitas != null && rsCitas.next()) {
            Cita c = new Cita();
            c.setIdCita(rsCitas.getInt("id_cita"));
            // Resto de propiedades...
            citas.add(c);
        }
        if (rsCitas != null) rsCitas.close();

        // Cargar dentistas
        String sqlDentistas = "SELECT d.*, u.nombre, u.apellidoP, u.apellidoM FROM dentista d JOIN usuario u ON d.id_usuario = u.id_usuario";
        ResultSet rsDentistas = conexion.ejecutarConsulta(sqlDentistas);
        while (rsDentistas != null && rsDentistas.next()) {
            Dentista d = new Dentista();
            d.setId(rsDentistas.getInt("id_dentista"));
            // Resto de propiedades...
            dentistas.add(d);
        }
        if (rsDentistas != null) rsDentistas.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar datos: " + ex.getMessage());
    }
}


    private void btnEliminarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCitaActionPerformed
        // TODO add your handling code here:
        try {
        int filaSeleccionada = tablaCitasCanceladas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita cancelada de la tabla.");
            return;
        }

        // Suponiendo que el ID de la cita está en la primera columna (índice 0)
        int idCita = (int) tablaCitasCanceladas.getValueAt(filaSeleccionada, 0);

        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar esta cita cancelada?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            boolean eliminado = conexion.eliminar("cita", "id_cita = ?", idCita);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
                recargarTablas();
                limpiarCamposDetalle();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la cita. Puede que existan registros relacionados.");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnEliminarCitaActionPerformed

    private void limpiarCamposDetalle() {
    txtIdCita.setText("");
    txtIdPaciente.setText("");
    txtNombrePaciente.setText("");
    txtDireccion.setText("");
    txtTelefono.setText("");
    txtFecha.setText("");
    txtHora.setText("");
}

    private void recargarTablas() {
    cargarCitasPorEstado("Agendada", tablaCitasVigentes);
    cargarCitasPorEstado("Cancelada", tablaCitasCanceladas);
    cargarCitasPorEstado("Vencida", tablaCitasVencidas);
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
            java.util.logging.Logger.getLogger(GestionarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarCitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarCita;
    private javax.swing.JButton btnCancelarCita;
    private javax.swing.JButton btnEliminarCita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private menulateral.SideMenuComponent menu;
    private javax.swing.JPanel panelCitasCanceladas;
    private javax.swing.JPanel panelCitasVencidas;
    private javax.swing.JTabbedPane panelVerCitas;
    private javax.swing.JTable tablaCitasCanceladas;
    private javax.swing.JTable tablaCitasVencidas;
    private javax.swing.JTable tablaCitasVigentes;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextArea txtDireccion1;
    private javax.swing.JTextArea txtDireccion2;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFecha1;
    private javax.swing.JTextField txtFecha2;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtHora1;
    private javax.swing.JTextField txtHora2;
    private javax.swing.JTextField txtIdCita;
    private javax.swing.JTextField txtIdCita1;
    private javax.swing.JTextField txtIdCita2;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtIdPaciente1;
    private javax.swing.JTextField txtIdPaciente2;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtNombrePaciente1;
    private javax.swing.JTextField txtNombrePaciente2;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
