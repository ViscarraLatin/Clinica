
package clinica.appweb.controllers;

import clinica.accesoadatos.RegistroPacienteDAL;
import clinica.appweb.utils.*;
import clinica.entidadesdenegocio.RegistroPaciente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RegistroPacienteServlet", urlPatterns = {"/RegistroPaciente"})
public class RegistroPacienteServlet extends HttpServlet {

    
     private RegistroPaciente obtenerRegistroPaciente(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        RegistroPaciente registroPaciente = new RegistroPaciente();
        if (accion.equals("create") == false) { registroPaciente.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));}

         registroPaciente.setNombre(Utilidad.getParameter(request, "nombre", ""));
         registroPaciente.setApellido(Utilidad.getParameter(request, "apellido", ""));
         registroPaciente.setDui(Utilidad.getParameter(request, "dui", ""));
         registroPaciente.setGenero(Utilidad.getParameter(request, "genero", ""));
         registroPaciente.setFechaNac(Utilidad.getParameter(request, "fechanac", ""));
         registroPaciente.setLugarNac(Utilidad.getParameter(request, "lugarnac", ""));
         registroPaciente.setOcupacion(Utilidad.getParameter(request, "ocupacion", ""));
         registroPaciente.setTelefono(Utilidad.getParameter(request, "telefono", ""));
         registroPaciente.setCelular(Utilidad.getParameter(request, "celular", ""));
         registroPaciente.setEmail(Utilidad.getParameter(request, "email", ""));
         registroPaciente.setEstadoCivil(Utilidad.getParameter(request, "estadoCivil", ""));
         registroPaciente.setEdad(Integer.parseInt(Utilidad.getParameter(request, "edad", "")));
         registroPaciente.setDireccion(Utilidad.getParameter(request, "direccion", ""));
         registroPaciente.setPeso(Integer.parseInt(Utilidad.getParameter(request, "peso", "")));
         registroPaciente.setEstatura(Utilidad.getParameter(request, "estatura", ""));
        
        
        if (accion.equals("index\n" +
"        return registroPaciente;\n" +
"    }\n" +
"\n" +
"     \n" +
"     ")) {
            registroPaciente.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            registroPaciente.setTop_aux(registroPaciente.getTop_aux() == 0 ? Integer.MAX_VALUE : registroPaciente.getTop_aux());
        }
     
            return registroPaciente;
     }
     
      private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RegistroPaciente registroPaciente = new RegistroPaciente();
            System.out.println("index  -->");
            //ArrayList<RegistroPaciente> registroPacientes = RegistroPacienteDAL.buscar(registroPaciente);
            ArrayList<RegistroPaciente> registroPacientes = RegistroPacienteDAL.obtenerTodos();
            request.setAttribute("registropaciente", registroPacientes);
           //request.setAttribute("top_aux", registroPaciente.getTop_aux());     
            request.getRequestDispatcher("Views/RegistroPaciente/index.jsp").forward(request, response);
        } catch (Exception ex) { Utilidad.enviarError(ex.getMessage(), request, response);}
    }
      
      //en buscar entra en esta funcion
       private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String b=request.getParameter("buscar");
            System.out.println("buscar trae: "+b);   
            
            ArrayList<RegistroPaciente> registroPacientes=RegistroPacienteDAL.filtro(b);
            //System.out.println(lista.get(0).getNombre());

            request.setAttribute("registropaciente", registroPacientes);
           //request.setAttribute("top_aux", registroPaciente.getTop_aux());     
            request.getRequestDispatcher("Views/RegistroPaciente/index.jsp").forward(request, response);
            
            
            
            
            //request.setAttribute("lista",lista);
            //request.getRequestDispatcher("Views/RegistroPaciente/RegistroPaciente/buscar.jsp").forward(request, response);
            
            
            
            
            /*
            request.getRequestDispatcher("Views/RegistroPaciente/RegistroPaciente/buscar.jsp").forward(request, response);
            System.out.println("enra aquci!  fdsfsdfsfsfsgf");
            RegistroPaciente registroPaciente = obtenerRegistroPaciente(request);
            ArrayList<RegistroPaciente> registropacientes = RegistroPacienteDAL.buscar(registroPaciente);
            request.setAttribute("registropacientes", registropacientes);
               request.setAttribute("top_aux", registroPaciente.getTop_aux());       
            request.getRequestDispatcher("Views/RegistroPaciente/index.jsp").forward(request, response);*/
        } catch (Exception ex) { 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
       private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/RegistroPaciente/create.jsp").forward(request, response);
    }
        private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RegistroPaciente  registroPaciente = obtenerRegistroPaciente(request);
            int result = RegistroPacienteDAL.crear(registroPaciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                System.out.println("enra aquci!  assd");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
         
        
    //Edit al presionar dentro de la lista..
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("id del paciente: "+request.getParameter("id"));
        try {
            //RegistroPaciente registroPaciente = obtenerRegistroPaciente(request);
            RegistroPaciente registroPaciente = RegistroPacienteDAL.obtenerPorId2(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("registropaciente", registroPaciente);
           //request.setAttribute("top_aux", registroPaciente.getTop_aux());     
            //request.getRequestDispatcher("Views/RegistroPaciente/edit.jsp").forward(request, response);
            /*if (registroPaciente_result.getId() > 0) {
                request.setAttribute("registroPaciente", registroPaciente_result);
            } else {
                Utilidad.enviarError("El Id:" + registroPaciente.getId() + " no existe en la tabla de RegistroPaciente", request, response);
            }*/
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/RegistroPaciente/edit.jsp").forward(request, response);
    }
    
    
    //aqui debe entrar en Edit
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("edit");
            RegistroPaciente registroPaciente = obtenerRegistroPaciente(request);
            int result = RegistroPacienteDAL.modificar(registroPaciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("detalle");
         request.setAttribute("exists", true);
        requestObtenerPorId(request, response);        
        request.getRequestDispatcher("Views/RegistroPaciente/edit.jsp").forward(request, response);
            
       
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("entra en delete get");
        
         try {
            //RegistroPaciente registroPaciente = obtenerRegistroPaciente(request);
            int id=Integer.parseInt(request.getParameter("id"));
            int result = RegistroPacienteDAL.eliminar(id);
            if (result != 0) {
                System.out.println("!=0");
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
                
            } else {
                System.out.println("0");
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }        
    }
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*try {
            RegistroPaciente registroPaciente = obtenerRegistroPaciente(request);
            int result = RegistroPacienteDAL.eliminar(registroPaciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }*/
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Métodos para procesar las peticiones Get y Post">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            System.out.println("entra en get");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            System.out.println("entra en post");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
    //</editor-fold>
}
        