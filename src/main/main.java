/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Controller.ControladorMonitor;
import Controller.ControladorPrincipal;
import Model.Conexion;

/**
 *
 * @author Estudiantes
 */
public class main {
    
    public static void main(String[] args){
        ControladorPrincipal maincont = new ControladorPrincipal();
        maincont.initPrincipal();
        
        Conexion con = new Conexion();
        con.getConexion();
        
    }
    
}
