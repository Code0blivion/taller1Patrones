/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ConsultasMonitor extends Conexion {
    
 // =========== METODO REGISTRAR
    public boolean registrar (Monitor m){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "insert into monitor (nombreMonitor, telefonoMonitor, nacimientoMonitor, direccionMonitor,correoMonitor) values (?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTelefono());
            ps.setString(3, m.getFecha_nacimiento());
            ps.setString(4, m.getDireccion());
            ps.setString(5, m.getCorreo());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN REGISTRAR 
    
     // =========== METODO MODIFICAR
    public boolean modificar (Monitor m){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "update monitor set nombreMonitor=?, telefonoMonitor=?, nacimientoMonitor=?, direccionMonitor=?,correoMonitor=? where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTelefono());
            ps.setString(3, m.getFecha_nacimiento());
            ps.setString(4, m.getDireccion());
            ps.setString(5, m.getCorreo());
            ps.setInt(6, m.getPk());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN MODIFICAR     
    
     // =========== METODO ELIMINAR
    public boolean eliminar (Monitor m){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from monitor where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, m.getPk());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN eliminar 
    
                    // =========== METODO BUSCAR
    public boolean buscar (Monitor m){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from monitor where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, m.getPk());
            rs = ps.executeQuery();
            if(rs.next()){
                m.setPk(Integer.parseInt(rs.getString("id")));
                m.setNombre(rs.getString("nombreInstructor"));
                m.setTelefono(rs.getString("telefonoInstructor"));
                m.setFecha_nacimiento(rs.getString("nacimientoInstructor"));
                m.setDireccion(rs.getString("direccionInstructor"));
                m.setCorreo(rs.getString("correoInstructor"));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN BUSCAR 
}
