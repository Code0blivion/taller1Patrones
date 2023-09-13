/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Estudiantes
 */
public class ControladorPrincipal implements ActionListener{
    
    
    private ControladorMonitor controladormon = new ControladorMonitor(this);
    private ControladorInstructor controladorins = new ControladorInstructor(this);
    private VentanaPrincipal ventana = new VentanaPrincipal();
    
    public ControladorPrincipal(){
        ventana.setActionCommands();
        ventana.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String response = e.getActionCommand();
        
        switch(response){
            case "registermon":
                controladormon.initRegister();
            break;
            
            case "registerins":
                controladorins.initRegister();
            break;
            
            //Codigo de prueba. Modificar luego
            case "consultins":
                controladorins.initModification();
            break;
        }
        ventana.closeView();
    }
    
    public void initPrincipal(){
        ventana.initView();
    }
    
}
