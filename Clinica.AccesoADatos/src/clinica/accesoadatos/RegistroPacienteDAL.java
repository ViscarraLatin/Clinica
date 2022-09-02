
package clinica.accesoadatos;

import java.util.*; 
import java.sql.*;
import clinica.entidadesdenegocio.*; 

public class RegistroPacienteDAL { 

    
    static String obtenerCampos() {
        return "r.Id,r.Nombre,r.Apellido,r.Dui,r.Genero,r.FechaNac,r.LugarNac,r.Ocupacion,"
                + "r.Telefono,r.Celular,r.Email,r.EstadoCivil,r.Edad,r.Direccion,r.Peso,r.Estatura";
    }

    
    private static String obtenerSelect(RegistroPaciente pRegistroPaciente) {
        String sql;
        sql = "SELECT ";
        if (pRegistroPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
       
            sql += "TOP " + pRegistroPaciente.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM RegistroPaciente r"); 
        return sql;
    }

   
    private static String agregarOrderBy(RegistroPaciente pRegistroPaciente) {
        String sql = " ORDER BY r.Id DESC";
        if (pRegistroPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            
            sql += " LIMIT " + pRegistroPaciente.getTop_aux() + " ";
        }
        return sql;
    }
  
    public static int crear(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO RegistroPaciente (Nombre,Apellido,Dui,Genero,FechaNac,LugarNac,Ocupacion,"
                    + "Telefono,Celular,Email,EstadoCivil,Edad,Direccion,Peso,Estatura) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setString(1, pRegistroPaciente.getNombre());
                ps.setString(2, pRegistroPaciente.getApellido());
                ps.setString(3, pRegistroPaciente.getDui());
                ps.setString(4, pRegistroPaciente.getGenero());
                ps.setString(5, pRegistroPaciente.getFechaNac());
                 ps.setString(6, pRegistroPaciente.getLugarNac());
                  ps.setString(7, pRegistroPaciente.getOcupacion());
                    ps.setString(8, pRegistroPaciente.getTelefono());
                      ps.setString(9, pRegistroPaciente.getCelular());
                        ps.setString(10, pRegistroPaciente.getEmail());
                          ps.setString(11, pRegistroPaciente.getEstadoCivil());
                            ps.setInt(12, pRegistroPaciente.getEdad());
                              ps.setString(13, pRegistroPaciente.getDireccion());
                                ps.setInt(14, pRegistroPaciente.getPeso());
                                  ps.setString(15, pRegistroPaciente.getEstatura());
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

  
    public static int modificar(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "UPDATE RegistroPaciente SET Nombre=?, Apellido=?, Dui=?, Genero=?, FechaNac=?, LugarNac=?, Ocupacion=?, Telefono=?, Celular=?, Email=?, EstadoCivil=?, Edad=?, Direccion=?, Peso=?, Estatura=? WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setString(1, pRegistroPaciente.getNombre()); 
                ps.setString(2, pRegistroPaciente.getApellido()); 
                ps.setString(3, pRegistroPaciente.getDui()); 
                ps.setString(4, pRegistroPaciente.getGenero()); 
                ps.setString(5, pRegistroPaciente.getFechaNac()); 
                ps.setString(6, pRegistroPaciente.getLugarNac()); 
                ps.setString(7, pRegistroPaciente.getOcupacion()); 
                ps.setString(8, pRegistroPaciente.getTelefono()); 
                ps.setString(9, pRegistroPaciente.getCelular()); 
                ps.setString(10, pRegistroPaciente.getEmail()); 
                ps.setString(11, pRegistroPaciente.getEstadoCivil());
                ps.setInt(12, pRegistroPaciente.getEdad()); 
                ps.setString(13, pRegistroPaciente.getDireccion()); 
                ps.setInt(14, pRegistroPaciente.getPeso()); 
                ps.setString(15, pRegistroPaciente.getEstatura()); 
                ps.setInt(16, pRegistroPaciente.getId());   
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

    
    public static int eliminar(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM RegistroPaciente WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {  
                ps.setInt(1, pRegistroPaciente.getId());
                  ps.setString(1, pRegistroPaciente.getNombre());
                ps.setString(2, pRegistroPaciente.getApellido()); 
                ps.setString(3, pRegistroPaciente.getDui()); 
                ps.setString(4, pRegistroPaciente.getGenero()); 
                ps.setString(5, pRegistroPaciente.getFechaNac()); 
                ps.setString(6, pRegistroPaciente.getLugarNac()); 
                ps.setString(7, pRegistroPaciente.getOcupacion()); 
                ps.setString(8, pRegistroPaciente.getTelefono()); 
                ps.setString(9, pRegistroPaciente.getCelular()); 
                ps.setString(10, pRegistroPaciente.getEmail()); 
                ps.setString(11, pRegistroPaciente.getEstadoCivil());
               ps.setInt(12, pRegistroPaciente.getEdad()); 
                ps.setString(13, pRegistroPaciente.getDireccion()); 
                ps.setInt(14, pRegistroPaciente.getPeso()); 
                ps.setString(15, pRegistroPaciente.getEstatura()); 
            
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
    
    
    static int asignarDatosResultSet(RegistroPaciente pRegistroPaciente, ResultSet pResultSet, int pIndex) throws Exception {
   
        pIndex++;
        pRegistroPaciente.setId(pResultSet.getInt(pIndex)); 
        
        pIndex++;
        pRegistroPaciente.setNombre(pResultSet.getString(pIndex)); 
        
        
        
        pIndex++;
        pRegistroPaciente.setApellido(pResultSet.getString(pIndex)); 
        
        
        
        pIndex++;
        pRegistroPaciente.setDui(pResultSet.getString(pIndex)); 
        
        
        
       
        pIndex++;
        pRegistroPaciente.setGenero(pResultSet.getString(pIndex)); 
        
        
        
     
        pIndex++;
        pRegistroPaciente.setFechaNac(pResultSet.getString(pIndex)); 
        
        
        pIndex++;
        pRegistroPaciente.setLugarNac(pResultSet.getString(pIndex)); 
        
        pIndex++;
        pRegistroPaciente.setOcupacion(pResultSet.getString(pIndex)); 
        
      
        pIndex++;
        pRegistroPaciente.setTelefono(pResultSet.getString(pIndex)); 
        
        
       
        pIndex++;
        pRegistroPaciente.setCelular(pResultSet.getString(pIndex)); 
        
       
        pIndex++;
        pRegistroPaciente.setEmail(pResultSet.getString(pIndex)); 
        
      
        pIndex++;
        pRegistroPaciente.setEstadoCivil(pResultSet.getString(pIndex)); 
        
        
        pIndex++;
        pRegistroPaciente.setEdad(pResultSet.getInt(pIndex)); 
        
       
        pIndex++;
        pRegistroPaciente.setDireccion(pResultSet.getString(pIndex)); 
        
      
        pIndex++;
        pRegistroPaciente.setPeso(pResultSet.getInt(pIndex)); 
  
        pIndex++;
        pRegistroPaciente.setEstatura(pResultSet.getString(pIndex)); 
        
      
        return pIndex;
    }
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Rol 
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<RegistroPaciente> pRegistroPaciente) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            while (resultSet.next()) { 
                RegistroPaciente registroPaciente = new RegistroPaciente(); 
                asignarDatosResultSet(registroPaciente, resultSet, 0); 
                pRegistroPaciente.add(registroPaciente); 
            }
            resultSet.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
    }
    
   // Metodo para obtener por Id un registro de la tabla de Rol 
    public static RegistroPaciente obtenerPorId(RegistroPaciente pRegistroPaciente) throws Exception {
        RegistroPaciente registroPaciente = new RegistroPaciente();
        ArrayList<RegistroPaciente> registroPacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pRegistroPaciente); 
            sql += " WHERE r.Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pRegistroPaciente.getId()); 
                obtenerDatos(ps, registroPacientes);

                ps.close(); 
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; 
        }
        if (registroPacientes.size() > 0) { 
            registroPaciente = registroPacientes.get(0); 
        }
        return registroPaciente; 
    }


    public static ArrayList<RegistroPaciente> obtenerTodos() throws Exception {
        ArrayList<RegistroPaciente> registroPacientes;
        registroPacientes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new RegistroPaciente());  
            sql += agregarOrderBy(new RegistroPaciente()); 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                obtenerDatos(ps, registroPacientes); 
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        } 
        catch (SQLException ex) {
            throw ex; 
        }
        return registroPacientes; 
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Rol de forma dinamica
    static void querySelect(RegistroPaciente pRegistroPaciente, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); 
        if (pRegistroPaciente.getId() > 0) { 
            pUtilQuery.AgregarWhereAnd(" r.Id=? "); 
            if (statement != null) { 
              
                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getId()); 
            }
        }
     
        if (pRegistroPaciente.getNombre() != null && pRegistroPaciente.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? "); 
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getNombre() + "%"); 
            }
        }
         if (pRegistroPaciente.getApellido() != null && pRegistroPaciente.getApellido().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Apellido LIKE ? ");
            if (statement != null) {
               
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getApellido() + "%"); 
            }
        }
        
          if (pRegistroPaciente.getDui() != null && pRegistroPaciente.getDui().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Dui LIKE ? "); 
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getDui() + "%"); 
            }
        }
           if (pRegistroPaciente.getGenero() != null && pRegistroPaciente.getGenero().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Genero LIKE ? ");
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getGenero() + "%"); 
            }
        }
            if (pRegistroPaciente.getFechaNac() != null && pRegistroPaciente.getFechaNac().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.FechaNac LIKE ? ");  
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getFechaNac() + "%"); 
            }
        }
             if (pRegistroPaciente.getLugarNac() != null && pRegistroPaciente.getLugarNac().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.LugarNac LIKE ? "); 
            if (statement != null) {
            
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getLugarNac() + "%"); 
            }
        }
            
               if (pRegistroPaciente.getOcupacion() != null && pRegistroPaciente.getOcupacion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Ocupacion LIKE ? ");
            if (statement != null) {
          
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getOcupacion() + "%"); 
            }
        }
                if (pRegistroPaciente.getTelefono() != null && pRegistroPaciente.getTelefono().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Telefono LIKE ? ");
            if (statement != null) {
                             statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getTelefono() + "%"); 
            }
        }
                if (pRegistroPaciente.getCelular() != null && pRegistroPaciente.getCelular().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Celular LIKE ? "); 
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getCelular() + "%"); 
            }
        }
                
                if (pRegistroPaciente.getEmail() != null && pRegistroPaciente.getEmail().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Email LIKE ? "); 
            if (statement != null) {
         
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEmail() + "%"); 
            }
        }
                if (pRegistroPaciente.getEstadoCivil() != null && pRegistroPaciente.getEstadoCivil().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.EstadoCivil LIKE ? "); 
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEstadoCivil() + "%"); 
            }
        } 
          
                if (pRegistroPaciente.getEdad()> 0) { 
            pUtilQuery.AgregarWhereAnd(" r.Edad LIKE ? "); 
            if (statement != null) { 
         
                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getEdad()); 
            }
        }       
        
                if (pRegistroPaciente.getDireccion() != null && pRegistroPaciente.getDireccion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Direccion LIKE ? "); 
            if (statement != null) {

                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getDireccion() + "%"); 
            }
                }
        
         
            pUtilQuery.AgregarWhereAnd(" r.Peso LIKE ? "); 
            if (statement != null) {

                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getPeso()); 
            
        }
                
                 if (pRegistroPaciente.getEstatura() != null && pRegistroPaciente.getEstatura().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Estatura LIKE ? ");
            if (statement != null) {
        
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEstatura() + "%"); 
            }
        }
             
    }
               
    public static ArrayList<RegistroPaciente> buscar(RegistroPaciente pRegistroPaciente) throws Exception {
        ArrayList<RegistroPaciente> registroPacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pRegistroPaciente); 
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pRegistroPaciente, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pRegistroPaciente); 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pRegistroPaciente, utilQuery);  
                obtenerDatos(ps, registroPacientes); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; 
        }
        return registroPacientes; 
    }
    
    
}
