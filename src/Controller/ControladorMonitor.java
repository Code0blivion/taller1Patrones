/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Instructor;
import Model.Monitor;
import View.Registro;
import View.GestionUsuario;
import View.Modificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Locale;

/**
 *
 * @author Estudiantes
 */
public class ControladorMonitor implements ActionListener{
    
    private Registro interfazRegistro = new Registro();
    private GestionUsuario interfazConsulta = new GestionUsuario();
    private Modificacion interfazModificacion = new Modificacion();
    private ControladorPrincipal principal;
    
    public ControladorMonitor(ControladorPrincipal principal){
        this.principal = principal;
    }
    
    public void initRegister(){
        interfazRegistro.initView();
        interfazRegistro.setActionsCommands();
        interfazRegistro.addListeners(this);
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
                
                Monitor monitor = new Monitor(1,nombre, phone, date, direccion, email);
                
                System.out.println("Nombre: " + monitor.getNombre() + "Fecha: "+monitor.getFecha_nacimiento() );
                //Falta validar y guardar en base de datos...
                
                interfazRegistro.clearView();
                // Mandar alerta de registro exitoso o fallido. 
                //Si es exitoso se sale de la interfaz, si es fallido solo limpia los campos
                
                break;
                
        }
    }
    
    
    
}
