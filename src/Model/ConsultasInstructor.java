/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author HP
 */
public class ConsultasInstructor extends Conexion {
    
 // =========== METODO REGISTRAR
    public boolean registrar (Instructor i){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "insert into instructor (nombreInstructor, telefonoInstructor, nacimientoInstructor, direccionInstructor,correoInstructor) values (?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getTelefono());
            ps.setString(3, i.getFecha_nacimiento());
            ps.setString(4, i.getDireccion());
            ps.setString(5, i.getCorreo());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN REGISTRAR 
    
     // =========== METODO MODIFICAR
    public boolean modificar (Instructor i){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "update instructor set nombreInstructor=?, telefonoInstructor=?, nacimientoInstructor=?, direccionInstructor=?,correoInstructor=? where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getTelefono());
            ps.setString(3, i.getFecha_nacimiento());
            ps.setString(4, i.getDireccion());
            ps.setString(5, i.getCorreo());
            ps.setInt(6, i.getPk());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN MODIFICAR     
    
     // =========== METODO ELIMINAR
    public boolean eliminar (Instructor i){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from instructor where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, i.getPk());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN eliminar 
    
                    // =========== METODO BUSCAR
    public boolean buscar (Instructor i){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from instructor where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, i.getPk());
            rs = ps.executeQuery();
            if(rs.next()){
                i.setPk(Integer.parseInt(rs.getString("id")));
                i.setNombre(rs.getString("nombreInstructor"));
                i.setTelefono(rs.getString("telefonoInstructor"));
                i.setFecha_nacimiento(rs.getString("nacimientoInstructor"));
                i.setDireccion(rs.getString("direccionInstructor"));
                i.setCorreo(rs.getString("correoInstructor"));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN BUSCAR 
    
}
