/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Instructor;
import View.ConsultaIndividual;
import View.GestionUsuario;
import View.Modificacion;
import View.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Model.ConsultasInstructor;

/**
 *
 * @author Estudiantes
 */
public class ControladorInstructor implements ActionListener {

    private Registro interfazRegistro = new Registro();
    private GestionUsuario interfazGestion = new GestionUsuario();
    private Modificacion interfazModificacion = new Modificacion();
    private ConsultaIndividual interfazConsulta = new ConsultaIndividual();
    private ControladorPrincipal principal;
    private ConsultasInstructor miconsulta = new ConsultasInstructor();
    private int pk;

    public ControladorInstructor(ControladorPrincipal principal) {
        this.principal = principal;
    }

    public void initRegister() {
        interfazRegistro.initView();
        interfazRegistro.setActionsCommands();
        interfazRegistro.addListeners(this);
    }

    public void initModification() {
        interfazModificacion.initView();
        interfazModificacion.setActionsCommands();
        interfazModificacion.addListeners(this);
    }

    public void initConsulta() {
        interfazConsulta.initView();
        interfazConsulta.setActionsCommands();
        interfazConsulta.addListeners(this);
    }

    public void initGestion() {
        //Aqui se cargan los instructores desde la base de datos
        interfazGestion.showRegisters(miconsulta.buscarTodos(), this);
        interfazGestion.setActionsCommands();
        interfazGestion.addListeners(this);
        interfazGestion.initView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String response = e.getActionCommand();

        switch (response) {
            case "returnreg":
                interfazRegistro.closeView();
                principal.initPrincipal();
                break;

            case "register":
                String nombre = interfazRegistro.getNameField().getText();
                String direccion = interfazRegistro.getAddressField().getText();
                String phone = interfazRegistro.getPhoneField().getText();
                String email = interfazRegistro.getEmailField().getText();
                DateFormat dateformat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
                String date = dateformat.format(interfazRegistro.getDateField().getDate());

                Instructor instructor = new Instructor(0, nombre, phone, date, direccion, email);

                System.out.println("Nombre: " + instructor.getNombre() + "Fecha: " + instructor.getFecha_nacimiento());
                //Falta validar...
                miconsulta.registrar(instructor);

                interfazRegistro.clearView();
                // Mandar alerta de registro exitoso o fallido. 
                //Si es exitoso se sale de la interfaz, si es fallido solo limpia los campos

                break;

            case "returnmod":
                interfazModificacion.closeView();
                break;

            case "returnges":
                principal.initPrincipal();
                interfazGestion.closeView();
                break;

            case "returncons":
                interfazConsulta.closeView();
                break;

            case "update":
                //Actualizar el registro en la base de datos
                Instructor nuevo = new Instructor(pk, interfazModificacion.getNameField().getText(),
                        interfazModificacion.getPhoneField().getText(),
                        DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format(interfazRegistro.getDateField().getDate()),
                        interfazModificacion.getAddressField().getText(), interfazModificacion.getEmailField().getText());

                miconsulta.modificar(nuevo);
                interfazModificacion.closeView();
                break;

            default:

                /*Para consultar individualmete, modificar o eliminar instructores se necesita la clave primaria 
                la cual depende del registro*/
                //Inicialmente se carga el registro a modificar/consultar/eliminar de la base de datos como un objeto...
                //Para cargar el registro se usa la clave primaria sobre la tabla de instructores
                if (response.startsWith("modify")) {
                    pk = Integer.parseInt(response.replace("modify", ""));
                    //Aqui se carga el registro de la base de datos...
                    Instructor i = miconsulta.buscar(pk);

                    //
                    interfazModificacion.getNameField().setText(i.getNombre());
                    interfazModificacion.getAddressField().setText(i.getDireccion());
                    interfazModificacion.getEmailField().setText(i.getCorreo());
                    interfazModificacion.getPhoneField().setText(i.getTelefono());

                    //Revisar el parse de fechas. No está convirtiendo correctamente el mes
                    try {
                        interfazModificacion.getDateField().setDate(new SimpleDateFormat("dd/mm/yyyy").parse(i.getFecha_nacimiento()));
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorInstructor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    initModification();

                } else if (response.startsWith("delete")) {
                    pk = Integer.parseInt(response.replace("delete", ""));
                    int confirm = JOptionPane.showConfirmDialog(interfazGestion, "Seguro que quiere eliminar este instructor?", "", JOptionPane.YES_NO_OPTION);
                    if (confirm == 0) {
                        //Se elimina el registro de la base de datos usando pk...
                        miconsulta.eliminar(pk);
                        interfazGestion.showRegisters(miconsulta.buscarTodos(), this);
                    }
                } else if (response.startsWith("consult")) {
                    pk = Integer.parseInt(response.replace("consult", ""));
                    //Cargar el registro de la base de datos con la pk
                    Instructor test = miconsulta.buscar(pk);
                    //
                    interfazConsulta.getNameLabel().setText(test.getNombre());
                    interfazConsulta.getAddressLabel().setText(test.getDireccion());
                    interfazConsulta.getPhoneLabel().setText(test.getTelefono());
                    interfazConsulta.getMailLabel().setText(test.getCorreo());
                    interfazConsulta.getDateLabel().setText(test.getFecha_nacimiento());
                    initConsulta();
                }

        }
    }

}
