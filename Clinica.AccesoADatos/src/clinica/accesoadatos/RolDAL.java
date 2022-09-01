
package clinica.accesoadatos;
import clinica.entidadesdenegocio.*;
import java.sql.*;
import java.util.*;

public class RolDAL {
     
    static String obtenerCampos()
    {
    return "r.Id,r.Nombre";
    }
    private static String obtenerSelect(Rol pRol){
        String sql;
        sql="SELECT ";
        if(pRol.getTop_aux() > 0 && ComunDB.TIPODB ==ComunDB.TipoDB.SQLSERVER){
          sql += "TOP" + pRol.getTop_aux() + "";
       }
        sql += (obtenerCampos() + " FROM Rol r ");
        return sql;
    }
    
    private static String agregarOrderBy(Rol pRol){
        String sql = " ORDER BY r.Id DESC ";
        if(pRol.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL
                ){
            sql += " LIMIT " + pRol.getTop_aux() + "";
        }
        return sql;
    }
    
    public static int crear(Rol pRol) throws Exception
    { int result;
     String sql;
     try(Connection conn = ComunDB .obtenerConexion();){
        sql = "INSERT INTO Rol(Nombre)VALUES(?)";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);){
            ps.setString(1, pRol.getNombre());
            result = ps.executeUpdate();
            ps.close();
        }
 catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
        return result;
    }

    // Metodo para poder actualizar un registro en la tabla de Rol
    public static int modificar(Rol pRol) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "UPDATE Rol SET Nombre=? WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setString(1, pRol.getNombre()); 
                ps.setInt(2, pRol.getId()); 
                result = ps.executeUpdate();
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex; 
        }
        return result;
    }

    // Metodo para poder eliminar un registro en la tabla de Rol
    public static int eliminar(Rol pRol) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM Rol WHERE Id=?";  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pRol.getId());
                result = ps.executeUpdate();  
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; 
        }
        return result; 
    }    
    
    // Metodo para llenar las propiedades de la entidad de Rol con los datos que viene en el ResultSet,
   
    static int asignarDatosResultSet(Rol pRol, ResultSet pResultSet, int pIndex) throws Exception {
       
        pIndex++;
        pRol.setId(pResultSet.getInt(pIndex)); 
        pIndex++;
        pRol.setNombre(pResultSet.getString(pIndex));
        return pIndex;
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Rol 
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Rol> pRoles) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) { 
                Rol rol = new Rol(); 
                asignarDatosResultSet(rol, resultSet, 0); 
                pRoles.add(rol); 
            }
            resultSet.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
    }
    
   // Metodo para obtener por Id un registro de la tabla de Rol 
    public static Rol obtenerPorId(Rol pRol) throws Exception {
        Rol rol = new Rol();
        ArrayList<Rol> roles = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pRol); 
            sql += " WHERE r.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pRol.getId()); 
                obtenerDatos(ps, roles); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; 
        }
        if (roles.size() > 0) { 
            rol = roles.get(0); 
        }
        return rol; }

    // Metodo para obtener todos los registro de la tabla de Rol
    public static ArrayList<Rol> obtenerTodos() throws Exception {
        ArrayList<Rol> roles;
        roles = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Rol());  
            sql += agregarOrderBy(new Rol());  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                obtenerDatos(ps, roles); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        } 
        catch (SQLException ex) {
            throw ex; 
        }
        return roles; // Devolver el ArrayList de Rol
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Rol de forma dinamica
    static void querySelect(Rol pRol, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); 
        if (pRol.getId() > 0) { 
            pUtilQuery.AgregarWhereAnd(" r.Id=? "); 
            if (statement != null) { 
                
                statement.setInt(pUtilQuery.getNumWhere(), pRol.getId()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Rol
        if (pRol.getNombre() != null && pRol.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? ");
            if (statement != null) {
               
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRol.getNombre() + "%"); 
            }
            // if (pRol.getNombre() != null && pRol.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? ");
            if (statement != null) {
               
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRol.getNombre() + "%"); 
            } 
        }
    }

     // Metodo para obtener todos los registro de la tabla de Rol que cumplan con los filtros agregados 
     
    public static ArrayList<Rol> buscar(Rol pRol) throws Exception {
        ArrayList<Rol> roles = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pRol); 
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pRol, utilQuery);  
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pRol); 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pRol, utilQuery);  
                obtenerDatos(ps, roles); 
                ps.close();
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close(); }
        catch (SQLException ex) {
            throw ex; 
        }
        return roles; 
    }
}  
