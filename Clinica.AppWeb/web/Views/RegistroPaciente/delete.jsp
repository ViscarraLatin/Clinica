<%-- 
    Document   : delete
    Created on : 22 ago. 2022, 08:33:13
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<% RegistroPaciente registroPaciente = (RegistroPaciente) request.getAttribute("registropaciente");%>
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
                    <input disabled  id="txtNombre" type="text" name="nombre" value="<%=registroPaciente.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                    
                           
                         <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtApellido" type="text" name="apellido" value="<%=registroPaciente.getApellido()%>">
                        <label for="txtNombre">Apellido</label>
                    </div>      
                         <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtDui" type="text" name="dui" value="<%=registroPaciente.getDui()%>">
                        <label for="txtDui">Dui</label>
                    </div>      
                        
                               <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtGenero" type="text" name="genero" value="<%=registroPaciente.getGenero()%>">
                        <label for="txtGenero">Genero</label>
                    </div>      
                    
                     <div class="input-field col l4 s12">
            <input  id="txtFechaNac" type="text" name="fechaNac" value="<%=registroPaciente.getFechaNac()%>">
            <label for="txtFechaNac">FechaNac</label>
        </div> 


        <div class="input-field col l4 s12">
            <input  id="txtLugarNac" type="text" name="lugarNac" value="<%=registroPaciente.getLugarNac()%>">
            <label for="txtLugarNac">LugarNac</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtOcupacion" type="text" name="ocupacion" value="<%=registroPaciente.getOcupacion()%>">
            <label for="txtOcupacion">Ocupacion</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtTelefono" type="text" name="telefono" value="<%=registroPaciente.getTelefono()%>">
            <label for="txtTelefono">Telefono</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtCelular" type="text" name="celular"value="<%=registroPaciente.getCelular()%>">
            <label for="txtCelular">Celular</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEmail" type="text" name="email" value="<%=registroPaciente.getEmail()%>">
            <label for="txtEmail">Email</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtEstadoCivil" type="text" name="etadocivil"value="<%=registroPaciente.getEstadoCivil()%>">
            <label for="txtEstadoCivil">Estado Civil</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEdad" type="number" name="edad" value="<%=registroPaciente.getEdad()%>">
            <label for="txtEdad">Edad</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtDireccion" type="text" name="direccion" value="<%=registroPaciente.getDireccion()%>">
            <label for="txtDireccion">Direccion</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtPeso" type="number" name="peso" value="<%=registroPaciente.getPeso()%>">
            <label for="txtPeso">Peso</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEstatura" type="text" name="estatura" value="<%=registroPaciente.getEstatura()%>">
            <label for="txtEstatura">Estatura</label>
        </div> 

                        
                </div>                                        
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="RegistroPaciente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
