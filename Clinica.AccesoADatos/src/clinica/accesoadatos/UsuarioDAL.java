
package clinica.accesoadatos;
import clinica.entidadesdenegocio.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class UsuarioDAL {
      public static String encriptarMD5(String txt) throws Exception {
        try {
            StringBuffer sb;
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw ex;
        }
    }
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Usuario
    static String obtenerCampos() {
        return "u.Id, u.IdRol, u.Nombre, u.Apellido, u.Login,u.password, u.Estatus, u.FechaRegistro";
    }
    // Metodo para obtener el SELECT a la tabla Usuario y el top en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Usuario pUsuario) {
        String sql;
        sql = "SELECT ";
        if (pUsuario.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            
            sql += "TOP " + pUsuario.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Usuario u");
        return sql;
    }   
    // Metodo para obtener Order by a la consulta SELECT de la tabla Usuario y ordene los registros de mayor a menor 
    private static String agregarOrderBy(Usuario pUsuario) {
        String sql = " ORDER BY u.Id DESC";
        if (pUsuario.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pUsuario.getTop_aux() + " ";
        }
        return sql;
    }
    

// Metodo para verificar si el login de usuario que se desea agregar o modificar 
    private static boolean existeLogin(Usuario pUsuario) throws Exception {
        boolean existe = false;
        ArrayList<Usuario> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pUsuario);  
           
            sql += " WHERE u.Id<>? AND u.Login=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pUsuario.getId());  
                ps.setString(2, pUsuario.getLogin()); 
                obtenerDatos(ps, usuarios);
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; 
        }
        if (usuarios.size() > 0) { 
            Usuario usuario;
            usuario = usuarios.get(0); 
            if (usuario.getId() > 0 && usuario.getLogin().equals(pUsuario.getLogin())) {
               
                existe = true;
            }
        }
        return existe; 
    }
    
    // Metodo para poder insertar un nuevo registro en la tabla de Usuario
    public static int crear(Usuario pUsuario) throws Exception {
        int result;
        String sql;
        boolean existe = existeLogin(pUsuario); 
        if (existe == false) {
            try (Connection conn = ComunDB.obtenerConexion();) { 
               
                sql = "INSERT INTO Usuario(IdRol,Nombre,Apellido,Login,Password,Estatus,FechaRegistro) VALUES(?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                    ps.setInt(1, pUsuario.getIdRol()); 
                    ps.setString(2, pUsuario.getNombre()); 
                    ps.setString(3, pUsuario.getApellido()); 
                    ps.setString(4, pUsuario.getLogin()); 
                    ps.setString(5, encriptarMD5(pUsuario.getPassword()));  
                    ps.setByte(6, pUsuario.getEstatus()); 
                    ps.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
                    result = ps.executeUpdate(); 
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; 
                }
                conn.close();
            } 
            catch (SQLException ex) {
                throw ex; 
            }
        } else {
            result = 0;
            throw new RuntimeException("Login ya existe"); 
        }
        return result; 
    }

     // Metodo para poder actualizar un registro en la tabla de Usuario
    public static int modificar(Usuario pUsuario) throws Exception {
        int result;
        String sql;
        boolean existe = existeLogin(pUsuario); 
        if (existe == false) {
            try (Connection conn = ComunDB.obtenerConexion();) { 
                
                sql = "UPDATE Usuario SET IdRol=?, Nombre=?, Apellido=?, Login=?, Estatus=? WHERE Id=?";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pUsuario.getIdRol());
                    ps.setString(2, pUsuario.getNombre()); 
                    ps.setString(3, pUsuario.getApellido());  
                    ps.setString(4, pUsuario.getLogin()); 
                    ps.setByte(5, pUsuario.getEstatus());  
                    ps.setInt(6, pUsuario.getId());
                    result = ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    throw ex;
                }
                conn.close();
            } 
            catch (SQLException ex) {
                throw ex;  
            }
        } else {
            result = 0;
            throw new RuntimeException("Login ya existe"); 
        }
        return result; 
    }

    // Metodo para poder eliminar un registro en la tabla de Usuario
    public static int eliminar(Usuario pUsuario) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM Usuario WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {  
                ps.setInt(1, pUsuario.getId()); 
                result = ps.executeUpdate(); 
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex;  
        }
        return result; }

    // Metodo para llenar las propiedades de la entidad de Usuario con los datos que viene en el ResultSet,
   
    static int asignarDatosResultSet(Usuario pUsuario, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pUsuario.setId(pResultSet.getInt(pIndex)); 
        pIndex++;
        pUsuario.setIdRol(pResultSet.getInt(pIndex)); 
        pIndex++;
        pUsuario.setNombre(pResultSet.getString(pIndex)); 
        pIndex++;
        pUsuario.setApellido(pResultSet.getString(pIndex)); 
        pIndex++;
        pUsuario.setLogin(pResultSet.getString(pIndex)); 
        pIndex++;
        pUsuario.setEstatus(pResultSet.getByte(pIndex)); 
        pIndex++;
        pUsuario.setFechaRegistro(pResultSet.getDate(pIndex).toLocalDate()); 
        return pIndex;
    }

    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Usuario> pUsuarios) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            while (resultSet.next()) { 
                Usuario usuario = new Usuario();
                
                asignarDatosResultSet(usuario, resultSet, 0);
                pUsuarios.add(usuario); 
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}