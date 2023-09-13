/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Instructor;
import View.Consulta;
import View.Modificacion;
import View.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Estudiantes
 */
public class ControladorInstructor implements ActionListener{
    
    private Registro interfazRegistro = new Registro();
    private Consulta interfazConsulta = new Consulta();
    private Modificacion interfazModificacion = new Modificacion();
    private ControladorPrincipal principal;
    
    public ControladorInstructor(ControladorPrincipal principal){
        this.principal = principal;
    }
    
    public void initRegister(){
        interfazRegistro.initView();
        interfazRegistro.setActionsCommands();
        interfazRegistro.addListeners(this);
    }
    
    public void initModification(){
        interfazModificacion.initView();
        interfazModificacion.setActionsCommands();
        interfazModificacion.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String response = e.getActionCommand();
        
        switch(response){
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
                
                Instructor instructor = new Instructor(nombre, phone, date, direccion, email);
                
                System.out.println("Nombre: " + instructor.getNombre() + "Fecha: "+instructor.getFecha_nacimiento() );
                //Falta validar y guardar en base de datos...
                
                interfazRegistro.clearView();
                // Mandar alerta de registro exitoso o fallido. 
                //Si es exitoso se sale de la interfaz, si es fallido solo limpia los campos
                
                break;
                
            case "returnmod":
                interfazModificacion.closeView();
                principal.initPrincipal();
             break;
             
            case "update":
                //Inicialmente se carga el registro de la base de datos como un objeto...
                Instructor test = new Instructor("Luis", "3215785454", "03/11/2000", "Cra 34 # 98-89", "luis@gmail.com");
                interfazModificacion.getNameField().setText(test.getNombre());
                interfazModificacion.getAddressField().setText(test.getDireccion());
                interfazModificacion.getEmailField().setText(test.getCorreo());
                interfazModificacion.getPhoneField().setText(test.getTelefono());
            {
                //Revisar el parse de fechas. No está convirtiendo correctamente el mes
                try {
                    interfazModificacion.getDateField().setDate(new SimpleDateFormat("dd/mm/yyyy").parse(test.getFecha_nacimiento()));
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorInstructor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

                
        }
    }
    
}
