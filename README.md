#  Clínica Dental "Estrella"  

## 👥 Equipo 2  

- 👩‍💻 Bustamante Ríos Flor Estephany – Diseñadora y Programadora  
- 👩‍💻 Ittay Ayelén Olmos Reyes – Diseñadora y Programadora

---
- ## 📑 Tabla de Contenido  

1. [🦷 ¿Qué hace el sistema?](#-qué-hace-el-sistema)  
2. [💻 Tipo de sistema](#-tipo-de-sistema)  
3. [🔗 Componentes externos integrados](#-componentes-externos-integrados)  
4. [🚀 Funcionalidades Clave](#-funcionalidades-clave)  
5. [📦 Dependencias y Configuración](#-dependencias-y-configuración)  
   - [Librerías externas utilizadas](#librerías-externas-utilizadas)  
   - [Requisitos mínimos](#requisitos-mínimos)  
6. [⚙ Pasos para instalación y ejecución](#-pasos-para-instalación-y-ejecución)  



---

## 🦷 ¿Qué hace el sistema?  
El sistema permite agendar y administrar citas para un consultorio dental, ofreciendo:  
- CRUD de usuarios y procedimientos.  
- Gestión de pacientes y citas.  
- Envío de correos electrónicos con datos de acceso en PDF.  

---

## 💻 Tipo de sistema  
📌 Desktop App desarrollada en Java utilizando Swing.  

---

## 🔗 Componentes externos integrados  

- 🎨 Componente visual:  
  Menú desplegable desarrollado por el [Equipo 3]([https://github.com/equipo3/menu-desplegable](https://github.com/fergmlx/componente-menu-lateral)).  

- 📧 Librería externa:  
  Librería de correo electrónico del [Equipo 2](https://github.com/olmomomo/Libreria_correoElectronico).  

---

## 🚀 Funcionalidades Clave  

1. 🔐 Integración de CAPTCHA  
   - Se valida el acceso al sistema al iniciar sesión para evitar accesos automatizados.
     ![Captcha](https://i.ibb.co/qMfg4qyx/captcha.png)

2. 👤 CRUD de Usuarios  
   - Crear, editar, eliminar y visualizar los usuarios del sistema.  
![Usuario](https://i.ibb.co/Y4kwbcK9/Registro.jpg)

3. 📝 CRUD de Procedimientos  
   - Gestión de los diferentes procedimientos realizados en la clínica.  
![Procedimientos](https://i.ibb.co/shFJGg2/procedimientos.png)

4. 📅 Agenda de Citas  
   - Registro de pacientes y programación de citas.
     ![Citas](https://i.ibb.co/ksp9cmZ2/citas-CRUD.jpg)

5. 📨 Envío de correo electrónico con PDF adjunto  
   - Al registrarse un usuario, el sistema envía un correo electrónico con un PDF adjunto que contiene:  
     - Nombre  
     - Rol  
     - Correo electrónico  
     - Contraseña del sistema
    ![CorreoRegistro](https://i.ibb.co/zWVZZgqS/pdf-Correo.png)
   - El PDF se genera con iTextPDF 3.13.4 y 3.13.3.  
   - Se hicieron adecuaciones para integrarlo con el envío de correos.  

6. ⚡ Otras funcionalidades únicas  
   - Conexión a base de datos MySQL usando una librería propia de conexión.  
   - Navegación interna mediante menús desplegables.  

  

---

## 📦 Dependencias y Configuración  

### Librerías externas utilizadas  
- iTextPDF 3.13.4 y 3.13.3 – Generación de documentos PDF.  
- JavaMail y JavaMail API – Envío de correos electrónicos.  
- Activation 1.1.1 – Soporte para envío de archivos adjuntos.  
- MySQL Connector 9.2.0 – Conexión con base de datos MySQL.   
- Componente visual de menú desplegable ([Equipo 3]((https://github.com/fergmlx/componente-menu-lateral)).  

### Requisitos mínimos  
- ☕ Java 22 o superior  
- 🗄 MySQL 8.0 o superior  
- IDE compatible: NetBeans  

---

## ⚙ Pasos para instalación y ejecución  

1. Clonar el repositorio:  
   ```bash
   git clone https://github.com/equipo2/clinica-dental-estrella.git
   Abrir el proyecto en NetBeans o Eclipse.

2. Agregar las librerías externas (.jar) ubicadas en la carpeta libs/.

3. Configurar la base de datos:

Importar el script clinica_dental.sql en MySQL.

Actualizar los parámetros de conexión (usuario, contraseña, host) en la clase de conexión MySQL.

Ejecutar el archivo Main.java para iniciar el sistema.
