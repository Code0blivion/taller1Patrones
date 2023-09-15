/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


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
        String sql = "update instructor set nombreInstructor=?, telefonoInstructor=?, nacimientoInstructor=?, direccionInstructor=?,correoInstructor=? where idInstructor=?";
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
    public boolean eliminar (int pk){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from instructor where idInstructor=?";
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
    public Instructor buscar (int pk){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from instructor where idInstructor=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if(rs.next()){
                Instructor i = new Instructor(Integer.parseInt(rs.getString("idInstructor")),
                rs.getString("nombreInstructor"),rs.getString("telefonoInstructor"),
                rs.getString("nacimientoInstructor"),rs.getString("direccionInstructor"),
                rs.getString("correoInstructor"));
                return i;
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return null;
    } 
    
    // =========== METODO BUSCAR
    public ArrayList<Usuario> buscarTodos (){
        ArrayList<Usuario> instructores = new ArrayList<Usuario>();
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from instructor";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Instructor i = new Instructor(Integer.parseInt(rs.getString("idInstructor")),
                rs.getString("nombreInstructor"),rs.getString("telefonoInstructor"),
                rs.getString("nacimientoInstructor"),rs.getString("direccionInstructor"),
                rs.getString("correoInstructor"));
                instructores.add((Usuario)i);
            }
            return instructores;
            
        }catch(SQLException e){
            System.err.println(e);
        }
        return null;
    } 
//------------ FIN BUSCAR 
    
}
