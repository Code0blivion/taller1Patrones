/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String sql = "update monitor set nombreMonitor=?, telefonoMonitor=?, nacimientoMonitor=?, direccionMonitor=?,correoMonitor=? where idMonitor=?";
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
    public boolean eliminar (int pk){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from monitor where idMonitor=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } //------------ FIN eliminar 
    
                    // =========== METODO BUSCAR
    public Monitor buscar (int pk){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from monitor where idMonitor=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if(rs.next()){
                Monitor m = new Monitor(Integer.parseInt(rs.getString("idMonitor")),
                rs.getString("nombreMonitor"),rs.getString("telefonoMonitor"),
                rs.getString("nacimientoMonitor"),rs.getString("direccionMonitor"),
                rs.getString("correoMonitor"));

                return m;
            }

        }catch(SQLException e){
            System.err.println(e);
        }
        return null;
    } //------------ FIN BUSCAR 
    
                    // =========== METODO BUSCAR
    public ArrayList<Usuario> buscarTodos (){
        ArrayList<Usuario> monitores = new ArrayList<Usuario>();
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from monitor";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Monitor m = new Monitor(Integer.parseInt(rs.getString("idMonitor")),
                rs.getString("nombreMonitor"),rs.getString("telefonoMonitor"),
                rs.getString("nacimientoMonitor"),rs.getString("direccionMonitor"),
                rs.getString("correoMonitor"));
                monitores.add((Usuario)m);
            }
            
            return monitores;
            
        }catch(SQLException e){
            System.err.println(e);
        }
        return null;
    } //------------ FIN BUSCAR 
}
