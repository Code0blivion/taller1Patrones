/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.Registro;
import View.Consulta;
import View.Modificacion;

/**
 *
 * @author Estudiantes
 */
public class ControladorMonitor {
    
    private Registro interfazRegistro = new Registro();
    private Consulta interfazConsulta = new Consulta();
    private Modificacion interfazModificacion = new Modificacion();
    
    public ControladorMonitor(){
        interfazRegistro.initView();
    }
    
}
