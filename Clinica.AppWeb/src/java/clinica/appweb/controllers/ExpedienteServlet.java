 package clinica.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList; // Importar la clase ArrayList
import clinica.accesoadatos.RegistroPacienteDAL; // Importar la clase RolDAL de la capa de acceso a datos
import clinica.accesoadatos.ExpedienteDAL; // Importar la clase UsuarioDAL de la capa de acceso a datos
import clinica.appweb.utils.*; // Importar las clases SessionUser, Utilidad del paquete de utils
import clinica.entidadesdenegocio.RegistroPaciente; // Importar la clase Rol de la capa de entidades de negocio
import clinica.entidadesdenegocio.Expediente; // Importar la clase Usuario de la capa de entidades de negocio


@WebServlet(name = "ExpedienteServlet", urlPatterns = {"/Expediente"})
public class ExpedienteServlet extends HttpServlet {

   
    private Expediente obtenerExpediente(HttpServletRequest request) {
        // Obtener el parÃ¡metro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Expediente expediente = new Expediente();
        // Obtener el parÃ¡metro nombre del request  y asignar ese valor a la propiedad Nombre de Usuario.
        expediente.setMotivoConsulta(Utilidad.getParameter(request, "motivoConsulta", ""));
        // Obtener el parÃ¡metro apellido del request  y asignar ese valor a la propiedad Apellido de Usuario.
        expediente.setSintomas(Utilidad.getParameter(request, "sintomas", ""));
        // Obtener el parÃ¡metro login del request  y asignar ese valor a la propiedad Login de Usuario.
        expediente.setSignosVitales(Utilidad.getParameter(request, "signosVitales", ""));
        expediente.setDescripcion(Utilidad.getParameter(request, "descripcion", ""));
        expediente.setExamenesComp(Utilidad.getParameter(request, "examenesComp", ""));
        expediente.setDiagnostico(Utilidad.getParameter(request, "diagnostico", ""));
        expediente.setTratamiento(Utilidad.getParameter(request, "tratamiento", ""));
        // Obtener el parÃ¡metro idRol del request  y asignar ese valor a la propiedad IdRol de Usuario.
        expediente.setIdRegistroPaciente(Integer.parseInt(Utilidad.getParameter(request, "idRegistroPaciente", "0")));
        // Obtener el parÃ¡metro estatus del request  y asignar ese valor a la propiedad Estatus de Usuario.
        
        if(accion.equals("create")== false)
            {
                //Obtener el parametro id del request y asignar ese valor a la propiedad Id de 
                //Rol
                expediente.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
                
            }
        
        if (accion.equals("index")) {
            // Obtener el parÃ¡metro top_aux del request  y asignar ese valor a la propiedad Top_aux de Usuario.
            expediente.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            expediente.setTop_aux(expediente.getTop_aux() == 0 ? Integer.MAX_VALUE : expediente.getTop_aux());
        }
        return expediente;
    }
    
    
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = new Expediente(); // Crear una instancia  de la entidad de Usuario.
            expediente.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Usuario.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Expediente> expedientes = ExpedienteDAL.buscarIncluirRegistroPaciente(expediente);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("expedientes", expedientes);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", expediente.getTop_aux());
            request.getRequestDispatcher("Views/Expediente/index.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Expediente> expedientes = ExpedienteDAL.buscarIncluirRegistroPaciente(expediente);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("expedientes", expedientes);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", expediente.getTop_aux());
            request.getRequestDispatcher("Views/Expediente/index.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Usuario
        request.getRequestDispatcher("Views/Expediente/create.jsp").forward(request, response);
    }

    /**
     * En este mÃ©todo se ejecutara cuando se envie una peticion post al servlet
     * Usuario , y el parÃ¡metro accion sea igual create.
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion post enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request
            // Enviar los datos de Usuario a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = ExpedienteDAL.crear(expediente);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo expedinete", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }

    /**
     * En este mÃ©todo obtiene por Id un Usuario desde la capa de acceso a datos
     * el Id se captura del request que se envio al servlet de Usuario
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion get o post enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request.
            Expediente expediente_result = ExpedienteDAL.obtenerPorId(expediente); // Obtener desde la capa de acceso a datos el usuario por Id.
            if (expediente_result.getId() > 0) { // Si el Id es mayor a cero.
                RegistroPaciente registroPaciente = new RegistroPaciente();
                registroPaciente.setId(expediente_result.getIdRegistroPaciente());
                // Obtener desde la capa de acceso a datos el rol por Id y asignarlo al usuario.
                expediente_result.setRegistroPaciente(RegistroPacienteDAL.obtenerPorId(registroPaciente));
                // Enviar el atributo usuario con el valor de los datos del usuario de nuestra base de datos a un jsp
                request.setAttribute("expediente", expediente_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Usuario
                Utilidad.enviarError("El Id:" + expediente_result.getId() + " no existe en la tabla de Usuario", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void requestObtenerPorIdPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request.
            ArrayList<Expediente> expediente_resultlist = ExpedienteDAL.buscarIncluirRegistroPaciente(expediente); // Obtener desde la capa de acceso a datos el usuario por Id.
            Expediente expediente_result = new Expediente();
             if (expediente_resultlist.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
                    expediente_result = expediente_resultlist.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
                }
            
            if (expediente_result.getId() > 0) { // Si el Id es mayor a cero.
                RegistroPaciente registroPaciente = new RegistroPaciente();
                registroPaciente.setId(expediente_result.getIdRegistroPaciente());
                // Obtener desde la capa de acceso a datos el rol por Id y asignarlo al usuario.
                expediente_result.setRegistroPaciente(RegistroPacienteDAL.obtenerPorId(registroPaciente));
                // Enviar el atributo usuario con el valor de los datos del usuario de nuestra base de datos a un jsp
                request.setAttribute("expediente", expediente_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Usuario
                Utilidad.enviarError("El Id:" + expediente_result.getId() + " no existe en la tabla de Usuario", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este mÃ©todo se ejecutara cuando se envie una peticion get al servlet
     * Usuario , y el parÃ¡metro accion sea igual edit.
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion get enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el usuario al jsp de edit que se obtiene por Id
        requestObtenerPorId(request, response);
        // Direccionar al jsp edit de Usuario
        request.getRequestDispatcher("Views/Expediente/edit.jsp").forward(request, response);
    }

    /**
     * En este mÃ©todo se ejecutara cuando se envie una peticion post al servlet
     * Usuario , y el parÃ¡metro accion sea igual edit.
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion post enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request.
            // Enviar los datos de Usuario a la capa de accesoa a datos para modificar el registro.
            int result = ExpedienteDAL.modificar(expediente);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron modificado correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este mÃ©todo se ejecutara cuando se envie una peticion get al servlet
     * Usuario , y el parÃ¡metro accion sea igual details.
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion get enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el usuario al jsp de details que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp details de Usuario.
        request.getRequestDispatcher("Views/Expediente/details.jsp").forward(request, response);
    }

    /**
     * En este mÃ©todo se ejecutara cuando se envie una peticion get al servlet
     * Usuario , y el parÃ¡metro accion sea igual delete.
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion get enviada al servlet Usuario
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el usuario al jsp de delete que se obtiene por Id.
        requestObtenerPorIdPaciente(request, response);
        
        // Direccionar al jsp delete de Usuario.
        request.getRequestDispatcher("Views/Expediente/delete.jsp").forward(request, response);
    }

    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Expediente expediente = obtenerExpediente(request); // Llenar la instancia de Usuario con los parÃ¡metros enviados en el request.
            // Enviar los datos de Usuario a la capa de accesoa a datos para que elimine el registro.
            int result = ExpedienteDAL.eliminar(expediente);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);  // Ir al mÃ©todo doGetRequestIndex para que nos direccione al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception.
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
 
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Este mÃ©todo es un override al mÃ©todo de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Usuario
     *
     * @param request en este parÃ¡metro vamos a recibir el request de la
     * peticion get enviada al servlet Usuario
     * @param response en este parÃ¡metro vamos a recibir el response de la
     * peticion get enviada al servlet Usuario que utilizaremos para enviar el
     * jsp al navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parÃ¡metro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        SessionUser.authorize(request, response, () -> {
                // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parÃ¡metro de accion.
                switch (accion) {
                    case "index":
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response); // Ir al mÃ©todo doGetRequestIndex.
                        break;
                    case "create":
                        // Enviar el atributo accion al jsp de create.
                        request.setAttribute("accion", accion);
                        doGetRequestCreate(request, response); // Ir al mÃ©todo doGetRequestCreate.
                        break;
                    case "edit":
                        // Enviar el atributo accion al jsp de edit.
                        request.setAttribute("accion", accion);
                        doGetRequestEdit(request, response); // Ir al mÃ©todo doGetRequestEdit.
                        break;
                    case "delete":
                        // Enviar el atributo accion al jsp de delete.
                        request.setAttribute("accion", accion);
                        doGetRequestDelete(request, response); // Ir al mÃ©todo doGetRequestDelete.
                        break;
                    case "details":
                        // Enviar el atributo accion al jsp de details.
                        request.setAttribute("accion", accion);
                        doGetRequestDetails(request, response); // Ir al mÃ©todo doGetRequestDetails.
                        break;
                    
                    default:
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response); // Ir al mÃ©todo doGetRequestIndex.
                }
            });
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parÃ¡metro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
      SessionUser.authorize(request, response, () -> {
                // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parÃ¡metro de accion.
                switch (accion) {
                    case "index":
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doPostRequestIndex(request, response);  // Ir al metodo doPostRequestIndex.
                        break;
                    case "create":
                        // Enviar el atributo accion al jsp de create.
                        request.setAttribute("accion", accion);
                        doPostRequestCreate(request, response);  // Ir al metodo doPostRequestCreate.
                        break;
                    case "edit":
                        // Enviar el atributo accion al jsp de edit.
                        request.setAttribute("accion", accion);
                        doPostRequestEdit(request, response);  // Ir al metodo doPostRequestEdit.
                        break;
                    case "delete":
                        // Enviar el atributo accion al jsp de delete.
                        request.setAttribute("accion", accion);
                        doPostRequestDelete(request, response);  // Ir al metodo doPostRequestDelete.
                        break;
                   
                    default:
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response);  // Ir al metodo doGetRequestIndex.
                }
            });
    }
    // </editor-fold>
}
       

