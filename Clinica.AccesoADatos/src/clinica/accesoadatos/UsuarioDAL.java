
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
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario y JOIN a la tabla de Rol
    private static void obtenerDatosIncluirRol(PreparedStatement pPS, ArrayList<Usuario> pUsuarios) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            HashMap<Integer, Rol> rolMap = new HashMap(); 
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                 
                int index = asignarDatosResultSet(usuario, resultSet, 0);
                if (rolMap.containsKey(usuario.getIdRol()) == false) { 
                    Rol rol = new Rol();
                    
                    RolDAL.asignarDatosResultSet(rol, resultSet, index);
                    rolMap.put(rol.getId(), rol); 
                    usuario.setRol(rol); 
                } else {
                    
                    usuario.setRol(rolMap.get(usuario.getIdRol())); 
                }
                pUsuarios.add(usuario); 
            }
            resultSet.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
    }

    // Metodo para obtener por Id un registro de la tabla de Usuario 
    public static Usuario obtenerPorId(Usuario pUsuario) throws Exception {
        Usuario usuario = new Usuario();
        ArrayList<Usuario> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pUsuario); 
            
            sql += " WHERE u.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pUsuario.getId());
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
            usuario = usuarios.get(0); 
        }
        return usuario; 
    }

     // Metodo para obtener todos los registro de la tabla de Usuario
    public static ArrayList<Usuario> obtenerTodos() throws Exception {
        ArrayList<Usuario> usuarios;
        usuarios = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(new Usuario());
            sql += agregarOrderBy(new Usuario()); 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                obtenerDatos(ps, usuarios);
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; }
        return usuarios; 
    }

    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Usuario de forma dinamica
    static void querySelect(Usuario pUsuario, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); 
        if (pUsuario.getId() > 0) { 
            pUtilQuery.AgregarWhereAnd(" u.Id=? "); 
            if (statement != null) {
                 
                statement.setInt(pUtilQuery.getNumWhere(), pUsuario.getId());
            }
        }
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getIdRol() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.IdRol=? "); 
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pUsuario.getIdRol());
            }
        }
        // verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getNombre() != null && pUsuario.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" u.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pUsuario.getNombre() + "%");
            }
        }
        // Verificar si se va incluir el campo Apellido en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getApellido() != null && pUsuario.getApellido().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" u.Apellido LIKE ? "); 
            if (statement != null) {
                
                statement.setString(pUtilQuery.getNumWhere(), "%" + pUsuario.getApellido() + "%");
            }
        }
        // Verificar si se va incluir el campo Login en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getLogin() != null && pUsuario.getLogin().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" u.Login=? ");
            if (statement != null) {
                 
                statement.setString(pUtilQuery.getNumWhere(), pUsuario.getLogin());
            }
        }
        // Verificar si se va incluir el campo Estatus en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getEstatus() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.Estatus=? "); 
            if (statement != null) {
                 
                statement.setInt(pUtilQuery.getNumWhere(), pUsuario.getEstatus());
            }
        }
    }

     // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Usuario 
    public static ArrayList<Usuario> buscar(Usuario pUsuario) throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsuario); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pUsuario, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pUsuario); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pUsuario, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return usuarios; // Devolver el ArrayList de Usuario
    }
    
    // Metodo para verificar si el Usuario puede ser autorizado en el sistema
    // comparando el Login, Password, Estatus en la base de datos
    public static Usuario login(Usuario pUsuario) throws Exception {
        Usuario usuario = new Usuario();
        ArrayList<Usuario> usuarios = new ArrayList();
        String password = encriptarMD5(pUsuario.getPassword()); // Encriptar el password en MD5
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsuario); // Obtener la consulta SELECT de la tabla Usuario
             // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar los campos de Login, Password, Estatus
            sql += " WHERE u.Login=? AND u.Password=? AND u.Estatus=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pUsuario.getLogin()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                ps.setString(2, password); // Agregar el parametro a la consulta donde estan el simbolo ? #2 
                ps.setByte(3, Usuario.EstatusUsuario.ACTIVO); // Agregar el parametro a la consulta donde estan el simbolo ? #3 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (usuarios.size() > 0) { // Verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            usuario = usuarios.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return usuario; // Devolver la instancia de Usuario 
    }

    // Metodo para cambiar el password de un Usuario el cual solo se puede cambiar si envia el password actual correctamente
    public static int cambiarPassword(Usuario pUsuario, String pPasswordAnt) throws Exception {
        int result;
        String sql;
        Usuario usuarioAnt = new Usuario();
        usuarioAnt.setLogin(pUsuario.getLogin());
        usuarioAnt.setPassword(pPasswordAnt);
        Usuario usuarioAut = login(usuarioAnt); // Obtenemos el Usuario autorizado validandolo en el metodo de login
        // Si el usuario que retorno el metodo de login tiene el Id mayor a cero y el Login es igual que el Login del Usuario que viene
        // en el parametro es un Usuario Autorizado
        if (usuarioAut.getId() > 0 && usuarioAut.getLogin().equals(pUsuario.getLogin())) {
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                sql = "UPDATE Usuario SET Password=? WHERE Id=?"; // Crear la consulta Update a la tabla de Usuario para poder modificar el Password
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                    // Agregar el parametro a la consulta donde estan el simbolo ? #1 pero antes encriptar el password para enviarlo encriptado
                    ps.setString(1, encriptarMD5(pUsuario.getPassword())); //
                    ps.setInt(2, pUsuario.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #2 
                    result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                    ps.close(); // Cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
                }
                conn.close(); // Cerrar la conexion a la base de datos
            }
            catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
            }
        } else {
            result = 0;
            // Enviar la excepcion en el caso que el usuario que intenta modificar el password ingresa un password incorrecto
            throw new RuntimeException("El password actual es incorrecto");
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }

    // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Usuario 
    public static ArrayList<Usuario> buscarIncluirRol(Usuario pUsuario) throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pUsuario.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pUsuario.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += RolDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Usuario u";
            sql += " JOIN Rol r on (u.IdRol=r.Id)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pUsuario, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pUsuario); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pUsuario, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirRol(ps, usuarios);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return usuarios; // Devolver el ArrayList de Usuario
    }

}