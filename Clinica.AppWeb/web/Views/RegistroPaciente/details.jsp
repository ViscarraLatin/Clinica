<%-- 
    Document   : details
    Created on : 22 ago. 2022, 08:33:21
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<% RegistroPaciente registroPaciente = (RegistroPaciente) request.getAttribute("registroPaciente");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles del Paciente</title>
    </head>
    <body>
         <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle del Paciente</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=registroPaciente.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>         
                    <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtApellido" type="text" value="<%=registroPaciente.getApellido()%>">
                    <label for="txtApellido">Apellido</label>
                </div>      
                    <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtDui" type="text" value="<%=registroPaciente.getDui()%>">
                    <label for="txtDui">Dui</label>
                </div>      
                    <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtGnero" type="text" value="<%=registroPaciente.getGenero()%>">
                    <label for="txtGenero">Genero</label>
                </div>      
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="RegistroPaciente?accion=edit&id=<%=registroPaciente.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="RegistroPaciente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
                    
                    
                    
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
    </body>
</html>
