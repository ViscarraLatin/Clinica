

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<% RegistroPaciente registroPaciente = (RegistroPaciente) request.getAttribute("registroPaciente");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Paciente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Paciente</h5>          
            <form action="RegistroPaciente" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=registroPaciente.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=registroPaciente.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                    
                           
                         <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtApellido" type="text" name="nombre" value="<%=registroPaciente.getApellido()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Apellido</label>
                    </div>      
                         <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtDui" type="text" name="nombre" value="<%=registroPaciente.getDui()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Dui</label>
                    </div>      
                        
                               <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtDui" type="text" name="nombre" value="<%=registroPaciente.getGenero()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Genero</label>
                    </div>      
                    
                </div>                                        
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Rol" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
