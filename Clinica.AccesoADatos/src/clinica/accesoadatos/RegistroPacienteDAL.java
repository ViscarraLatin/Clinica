
package clinica.accesoadatos;

import java.util.*; // Utilizar la utileria de java https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
import java.sql.*;
import clinica.entidadesdenegocio.*; // Agregar la referencia al proyecto de entidades de negocio y poder utilizar las entidades de Rol y Usuario

public class RegistroPacienteDAL { // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Rol

    
    static String obtenerCampos() {
        return "r.Id,r.Nombre,r.Apellido,r.Dui,r.Genero,r.FechaNac,r.LugarNac,r.Ocupacion,"
                + "r.Telefono,r.Celular,r.Email,r.EstadoCivil,r.Edad,r.Direccion,r.Peso,r.Estatura";
    }

    
    private static String obtenerSelect(RegistroPaciente pRegistroPaciente) {
        String sql;
        sql = "SELECT ";
        if (pRegistroPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pRegistroPaciente.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM RegistroPaciente r"); // Agregar los campos de la tabla de Rol mas el FROM a la tabla Rol con su alias "r"
        return sql;
    }

    // Metodo para obtener Order by a la consulta SELECT de la tabla Rol y ordene los registros de mayor a menor 
    private static String agregarOrderBy(RegistroPaciente pRegistroPaciente) {
        String sql = " ORDER BY r.Id DESC";
        if (pRegistroPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            
            sql += " LIMIT " + pRegistroPaciente.getTop_aux() + " ";
        }
        return sql;
    }
    // Metodo para poder insertar un nuevo registro en la tabla de Rol
    public static int crear(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO RegistroPaciente (Nombre,Apellido,Dui,Genero,FechaNac,LugarNac,Ocupacion,"
                    + "Telefono,Celular,Email,EstadoCivil,Edad,Direccion,Peso,Estatura) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
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
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }

    // Metodo para poder actualizar un registro en la tabla de Rol
    public static int modificar(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE RegistroPaciente SET Nombre=?, Apellido=?, Dui=?, Genero=?, FechaNac=?, LugarNac=?, Ocupacion=?,"
                    + "Telefono=?, Celular=?, Email=?, EstadoCivil=?, Edad=?, Direccion=?, Peso=?, Estatura=? WHERE Id=?"; 
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
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; 
        }
        return result; 
    }

    // Metodo para poder eliminar un registro en la tabla de Rol
    public static int eliminar(RegistroPaciente pRegistroPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM RegistroPaciente WHERE Id=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(0, pRegistroPaciente.getId());
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
               // ps.setInt(16, pRegistroPaciente.getId());   // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate();  // Ejecutar la consulta DELETE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    }    
    
    // Metodo para llenar las propiedades de la entidad de Rol con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(RegistroPaciente pRegistroPaciente, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
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
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Rol
                RegistroPaciente registroPaciente = new RegistroPaciente(); 
                asignarDatosResultSet(registroPaciente, resultSet, 0); // Llenar las propiedaddes de la Entidad Rol con los datos obtenidos de la fila en el ResultSet
                pRegistroPaciente.add(registroPaciente); // Agregar la entidad Rol al ArrayList de Rol
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
   // Metodo para obtener por Id un registro de la tabla de Rol 
    public static RegistroPaciente obtenerPorId(RegistroPaciente pRegistroPaciente) throws Exception {
        RegistroPaciente registroPaciente = new RegistroPaciente();
        ArrayList<RegistroPaciente> registroPacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pRegistroPaciente); // Obtener la consulta SELECT de la tabla Rol
            sql += " WHERE r.Id=?"; // Concatenar a la consulta SELECT de la tabla Rol el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pRegistroPaciente.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, registroPacientes);
// Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (registroPacientes.size() > 0) { // Verificar si el ArrayList de Rol trae mas de un registro en tal caso solo debe de traer uno
            registroPaciente = registroPacientes.get(0); // Si el ArrayList de Rol trae un registro o mas obtenemos solo el primero 
        }
        return registroPaciente; // Devolver el rol encontrado por Id 
    }

    // Metodo para obtener todos los registro de la tabla de Rol
    public static ArrayList<RegistroPaciente> obtenerTodos() throws Exception {
        ArrayList<RegistroPaciente> registroPacientes;
        registroPacientes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new RegistroPaciente());  // Obtener la consulta SELECT de la tabla Rol
            sql += agregarOrderBy(new RegistroPaciente());  // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, registroPacientes); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return registroPacientes; // Devolver el ArrayList de Rol
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Rol de forma dinamica
    static void querySelect(RegistroPaciente pRegistroPaciente, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pRegistroPaciente.getId() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Rol
            pUtilQuery.AgregarWhereAnd(" r.Id=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getId()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Rol
        if (pRegistroPaciente.getNombre() != null && pRegistroPaciente.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getNombre() + "%"); 
            }
        }
         if (pRegistroPaciente.getApellido() != null && pRegistroPaciente.getApellido().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Apellido LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getApellido() + "%"); 
            }
        }
        
          if (pRegistroPaciente.getDui() != null && pRegistroPaciente.getDui().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Dui LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getDui() + "%"); 
            }
        }
           if (pRegistroPaciente.getGenero() != null && pRegistroPaciente.getGenero().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Genero LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getGenero() + "%"); 
            }
        }
            if (pRegistroPaciente.getFechaNac() != null && pRegistroPaciente.getFechaNac().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.FechaNac LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getFechaNac() + "%"); 
            }
        }
             if (pRegistroPaciente.getLugarNac() != null && pRegistroPaciente.getLugarNac().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.LugarNac LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getLugarNac() + "%"); 
            }
        }
            
               if (pRegistroPaciente.getOcupacion() != null && pRegistroPaciente.getOcupacion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Ocupacion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getOcupacion() + "%"); 
            }
        }
                if (pRegistroPaciente.getTelefono() != null && pRegistroPaciente.getTelefono().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Telefono LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getTelefono() + "%"); 
            }
        }
                if (pRegistroPaciente.getCelular() != null && pRegistroPaciente.getCelular().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Celular LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getCelular() + "%"); 
            }
        }
                
                if (pRegistroPaciente.getEmail() != null && pRegistroPaciente.getEmail().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Email LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEmail() + "%"); 
            }
        }
                if (pRegistroPaciente.getEstadoCivil() != null && pRegistroPaciente.getEstadoCivil().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.EstadoCivil LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEstadoCivil() + "%"); 
            }
        } 
          
                if (pRegistroPaciente.getEdad()> 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Rol
            pUtilQuery.AgregarWhereAnd(" r.Edad LIKE ? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getEdad()); 
            }
        }       
        
                if (pRegistroPaciente.getDireccion() != null && pRegistroPaciente.getDireccion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Direccion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getDireccion() + "%"); 
            }
                }
        
            //   if (pRegistroPaciente.getPeso() > 0) {
            pUtilQuery.AgregarWhereAnd(" r.Peso LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pRegistroPaciente.getPeso()); 
            
        }
                
                 if (pRegistroPaciente.getEstatura() != null && pRegistroPaciente.getEstatura().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Estatura LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRegistroPaciente.getEstatura() + "%"); 
            }
        }
             
    }
               
    public static ArrayList<RegistroPaciente> buscar(RegistroPaciente pRegistroPaciente) throws Exception {
        ArrayList<RegistroPaciente> registroPacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pRegistroPaciente); // Obtener la consulta SELECT de la tabla Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pRegistroPaciente, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Rol 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pRegistroPaciente); // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pRegistroPaciente, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Rol
                obtenerDatos(ps, registroPacientes); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return registroPacientes; // Devolver el ArrayList de Rol
    }
    
    
}
