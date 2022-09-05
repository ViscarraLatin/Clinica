<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<RegistroPaciente> registroPacientes = (ArrayList<RegistroPaciente>) request.getAttribute("registropaciente");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (registroPacientes == null) {
        registroPacientes = new ArrayList();

    } else if (registroPacientes.size() > numReg) {
        double divNumPage = (double) registroPacientes.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
 String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>

<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title>Buscar Paciente</title> 
</head>

<jsp:include page="/Views/Shared/headerBody.jsp" />  

<h5>Buscar Paciente as</h5>
<form action="RegistroPaciente" method="post">
    
        <div class="row">
            <div class="col l12 s12">
                <input name="buscar" type="search" placeholder="Buscar aqui">
                <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                <a href="RegistroPaciente?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                <a href="RegistroPaciente?accion=index" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Refrescar</a>                          
            </div>
        </div>
</form> 
<div class="row">
    <div class="col l12 s12">
        <div style="overflow: auto">
            <table class="paginationjs">
                <thead>
                    <tr>                                     
                        <th>Nombre</th>  
                        <th>Apellido</th> 
                        <th>Dui</th>  
                        <th>Genero</th>  
                        <th>FechaNac</th>   
                        <th>LugarNac</th>   
                        <th>Ocupacion</th>
                        <th>Telefono</th>
                        <th>Celular</th>
                        <th>Email</th>
                        <th>EstadoCivil</th> 
                        <th>Edad</th>
                        <th>Direccion</th> 
                        <th>Peso</th> 
                        <th>Estatura</th>                                    
                        <th>Acciones</th>
                    </tr>
                </thead>   
                <tbody>                           
                    <% for (RegistroPaciente registroPaciente : registroPacientes) {
                            int tempNumPage = numPage;
                            if (numPage > 1) {
                                countReg++;
                                double divTempNumPage = (double) countReg / (double) numReg;
                                tempNumPage = (int) Math.ceil(divTempNumPage);
                            }
                    %>
                    <tr data-page="<%= tempNumPage%>">                                    
                        <td><%=registroPaciente.getNombre()%></td>  
                        <td><%=registroPaciente.getApellido()%></td>  
                        <td><%=registroPaciente.getDui()%></td>  
                        <td><%=registroPaciente.getGenero()%></td>  
                        <td><%=registroPaciente.getFechaNac()%></td>  
                        <td><%=registroPaciente.getLugarNac()%></td>  
                        <td><%=registroPaciente.getOcupacion()%></td>  
                        <td><%=registroPaciente.getTelefono()%></td>  
                        <td><%=registroPaciente.getCelular()%></td>  
                        <td><%=registroPaciente.getEmail()%></td>  
                        <td><%=registroPaciente.getEstadoCivil()%></td>  
                        <td><%=registroPaciente.getEdad()%></td>  
                        <td><%=registroPaciente.getDireccion()%></td>  
                        <td><%=registroPaciente.getPeso()%></td>  
                        <td><%=registroPaciente.getEstatura()%></td>
                        <td>
                            <div style="display:flex">
                                <a href="RegistroPaciente?accion=edit&id=<%=registroPaciente.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                    <i class="material-icons">edit</i>
                                </a>
                                <a href="RegistroPaciente?accion=details&id=<%=registroPaciente.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                    <i class="material-icons">description</i>
                                </a>
                                <a href="#" title="Eliminar" class="waves-effect waves-light btn red" onclick="eliminar('<%=registroPaciente.getId()%>')">
                                    <i class="material-icons">delete</i>
                                </a>    
                            </div>                                                                    
                        </td>                                   
                    </tr>
                    <%}%>                                                       
                </tbody>
            </table>
        </div>                  
    </div>
</div>             
<div class="row">
    <div class="col l12 s12">
        <jsp:include page="/Views/Shared/paginacion.jsp">
            <jsp:param name="numPage" value="<%= numPage%>" />                        
        </jsp:include>
    </div>
</div>
</main>


<script>
    function eliminar(id){
        Swal.fire({
            title: 'Confirmacion',
            text: "Esta seguro de eliminar el registro?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Aceptar', 
            cancelButtonText: 'Cancelar'
          }).then((result) => {
            if (result.isConfirmed) {
                console.log("funciona");
                window.location.href="RegistroPaciente?accion=delete&id="+id;
            }
          });
    }
        
</script>


<jsp:include page="/Views/Shared/footerBody.jsp" />   
</body>

