/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.accesoadatos;

import java.util.*;
import java.sql.*;
import clinica.entidadesdenegocio.*; // Agregar la referencia al proyecto de entidades de negocio y poder utilizar las entidades de Rol y Usuario
import java.time.LocalDate; // Agregar referencia al paquete para manejar Fechas

public class ExpedienteDAL { // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Usuario

 
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Usuario
    static String obtenerCampos() {
        return "p.Id, p.IdRegistroPaciente, p.MotivoConsulta, p.Sintomas, p.SignosVitales, p.Descripcion, p.ExamenesComp, p.Diagnostico, p.Tratamiento";
    }
    // Metodo para obtener el SELECT a la tabla Usuario y el top en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Expediente pExpediente) {
        String sql;
        sql = "SELECT ";
        if (pExpediente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
             // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y getTop_aux es mayor a cero
            sql += "TOP " + pExpediente.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Expediente u");
        return sql;
    }   
    // Metodo para obtener Order by a la consulta SELECT de la tabla Usuario y ordene los registros de mayor a menor 
    private static String agregarOrderBy(Expediente pExpediente) {
        String sql = " ORDER BY p.Id DESC";
        if (pExpediente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Usuario en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pExpediente.getTop_aux() + " ";
        }
        return sql;
    }
   
    
    // Metodo para poder insertar un nuevo registro en la tabla de Usuario
    public static int crear(Expediente pExpediente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                 //Definir la consulta INSERT a la tabla de Usuario utilizando el simbolo "?" para enviar parametros
                sql = "INSERT INTO Expediente(IdRegistroPaciente,MotivoConsulta,Sintomas,SignosVitales,Descripcion,ExamenesComp,Diagnostico,Tratamiento,FechaRegistro) VALUES(?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pExpediente.getIdRegistroPaciente()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                    ps.setString(2, pExpediente.getMotivoConsulta()); // Agregar el parametro a la consulta donde estan el simbolo "?" #2 
                    ps.setString(3, pExpediente.getSintomas()); // agregar el parametro a la consulta donde estan el simbolo "?" #3
                    ps.setString(4, pExpediente.getSignosVitales()); 
                    ps.setString(5, pExpediente.getDescripcion()); 
                    ps.setString(6, pExpediente.getExamenesComp()); 
                    ps.setString(7, pExpediente.getDiagnostico()); 
                    ps.setString(8, pExpediente.getTratamiento()); 
                    ps.setDate(9, java.sql.Date.valueOf(LocalDate.now())); // agregar el parametro a la consulta donde estan el simbolo "?" #7 
                    result = ps.executeUpdate(); // ejecutar la consulta INSERT en la base de datos
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
                }
                conn.close();
            } // Handle any errors that may have occurred.
            catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al obtener la conexion en el caso que suceda
            }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }

     // Metodo para poder actualizar un registro en la tabla de Usuario
    public static int modificar(Expediente pExpediente) throws Exception {
        int result;
        String sql;
        
      
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                //Definir la consulta UPDATE a la tabla de Usuario utilizando el simbolo ? para enviar parametros
                sql = "UPDATE Expediente SET IdRegistroPaciente=?, MotivoConsulta=?, Sintomas=?, SignosVitales=?, Descripcion=?, ExamenesComp=?, Diagnostico=?, Tratamiento=?, WHERE Id=?";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pExpediente.getIdRegistroPaciente()); // agregar el parametro a la consulta donde estan el simbolo ? #1  
                    ps.setString(2, pExpediente.getMotivoConsulta()); // agregar el parametro a la consulta donde estan el simbolo ? #2  
                    ps.setString(3, pExpediente.getSintomas()); // agregar el parametro a la consulta donde estan el simbolo ? #3   
                    ps.setString(4, pExpediente.getSignosVitales());
                    ps.setString(5, pExpediente.getDescripcion());
                    ps.setString(6, pExpediente.getExamenesComp());
                    ps.setString(7, pExpediente.getDiagnostico());
                    ps.setString(8, pExpediente.getTratamiento());
                    ps.setInt(9, pExpediente.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #6  
                    result = ps.executeUpdate(); // ejecutar la consulta UPDATE en la base de datos
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
                }
                conn.close(); // cerrar la conexion a la base de datos
            } 
            catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
            }
        return 0;
    }
    

    // Metodo para poder eliminar un registro en la tabla de Usuario
    public static int eliminar(Expediente pExpediente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Expediente WHERE Id=?"; //definir la consulta DELETE a la tabla de Usuario utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {  // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pExpediente.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate(); // ejecutar la consulta DELETE en la base de datos
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex;  // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    }

    // Metodo para llenar las propiedades de la entidad de Usuario con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Expediente pExpediente, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pExpediente.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pExpediente.setIdRegistroPaciente(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pExpediente.setMotivoConsulta(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pExpediente.setSintomas(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pExpediente.setSignosVitales(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pExpediente.setDescripcion(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pExpediente.setExamenesComp(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pExpediente.setDiagnostico(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pExpediente.setTratamiento(pResultSet.getString(pIndex)); // index 6
        return pIndex;
    
        
    }

    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Expediente> pExpedientes) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Expediente expediente = new Expediente();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(expediente, resultSet, 0);
                pExpedientes.add(expediente); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
 // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario y JOIN a la tabla de Rol
    private static void obtenerDatosIncluirRegistroPaciente(PreparedStatement pPS, ArrayList<Expediente> pExpedientes) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, RegistroPaciente> registroPacienteMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                Expediente expediente = new Expediente();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(expediente, resultSet, 0);
                if (registroPacienteMap.containsKey(expediente.getIdRegistroPaciente()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    RegistroPaciente registroPaciente = new RegistroPaciente();
                    // en el caso que el Rol no este en el HashMap se asignara
                    RegistroPacienteDAL.asignarDatosResultSet(registroPaciente, resultSet, index);
                    registroPacienteMap.put(registroPaciente.getId(), registroPaciente); // agregar el Rol al  HashMap
                    expediente.setRegistroPaciente(registroPaciente); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    expediente.setRegistroPaciente(registroPacienteMap.get(expediente.getIdRegistroPaciente())); 
                }
                pExpedientes.add(expediente); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

    // Metodo para obtener por Id un registro de la tabla de Usuario 
    public static Expediente obtenerPorId(Expediente pExpediente) throws Exception {
        Expediente expediente = new Expediente();
        ArrayList<Expediente> expedientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pExpediente); // obtener la consulta SELECT de la tabla Usuario
             // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE p.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pExpediente.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, expedientes); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (expedientes.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            expediente = expedientes.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return expediente; // devolver el Usuario encontrado por Id 
    }

     // Metodo para obtener todos los registro de la tabla de Usuario
    public static ArrayList<Expediente> obtenerTodos() throws Exception {
        ArrayList<Expediente> expedientes;
        expedientes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Expediente()); // obtener la consulta SELECT de la tabla Usuario
            sql += agregarOrderBy(new Expediente()); // concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, expedientes); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return expedientes; // devolver el ArrayList de Usuario
    }

    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Usuario de forma dinamica
    static void querySelect(Expediente pExpediente, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pExpediente.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" p.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pExpediente.getId());
            }
        }
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getIdRegistroPaciente() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.IdRegistroPaciente=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pExpediente.getIdRegistroPaciente());
            }
        }
        // verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getMotivoConsulta() != null && pExpediente.getMotivoConsulta().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.MotivoConsulta LIKE ? "); // agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%" + pExpediente.getMotivoConsulta() + "%");
            }
        }
        // Verificar si se va incluir el campo Apellido en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getSintomas() != null && pExpediente.getSintomas().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Sintomas LIKE ? "); // agregar el campo Apellido al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Apellido a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%" + pExpediente.getSintomas() + "%");
            }
        }
        // Verificar si se va incluir el campo Login en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getSignosVitales() != null && pExpediente.getSignosVitales().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.SignosVitales=? "); // agregar el campo Login al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Login a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), pExpediente.getSignosVitales());
            }
        }
         // Verificar si se va incluir el campo Login en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getDescripcion() != null && pExpediente.getDescripcion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Descripcion=? "); // agregar el campo Login al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Login a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), pExpediente.getDescripcion());
            }
        }
         // Verificar si se va incluir el campo Login en el filtro de la consulta SELECT de la tabla de Usuario
        if (pExpediente.getExamenesComp() != null && pExpediente.getExamenesComp().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.ExamenesComp=?" ); // agregar el campo Login al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Login a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), pExpediente.getExamenesComp());
            }
        }
        if (pExpediente.getDiagnostico() != null && pExpediente.getDiagnostico().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Diagnostico=? "); // agregar el campo Login al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Login a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), pExpediente.getDiagnostico());
            }
        }
        if (pExpediente.getTratamiento() != null && pExpediente.getTratamiento().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Tratamiento=? "); // agregar el campo Login al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Login a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), pExpediente.getTratamiento());
            }
        }
        
    }

     // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Usuario 
    public static ArrayList<Expediente> buscar(Expediente pExpediente) throws Exception {
        ArrayList<Expediente> expedientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pExpediente); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pExpediente, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pExpediente); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pExpediente, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, expedientes); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return expedientes; // Devolver el ArrayList de Usuario
    }
    


    // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Usuario 
    public static ArrayList<Expediente> buscarIncluirRegistroPaciente(Expediente pExpediente) throws Exception {
        ArrayList<Expediente> expedientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pExpediente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pExpediente.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += RegistroPacienteDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Expediente p";
            sql += " JOIN RegistroPaciente r on (p.IdRegistroPaciente=r.Id)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pExpediente, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pExpediente); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pExpediente, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirRegistroPaciente(ps, expedientes);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return expedientes; // Devolver el ArrayList de Usuario
    }
}

