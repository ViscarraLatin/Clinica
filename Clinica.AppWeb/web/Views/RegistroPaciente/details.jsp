<%-- 
    Document   : details
    Created on : 22 ago. 2022, 08:33:21
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<% RegistroPaciente registroPaciente = (RegistroPaciente) request.getAttribute("registropacientes");%>
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
                    
                    
                    
        <div class="input-field col l4 s12">
            <input  id="txtFechaNac" type="text" name="fechanac" value="<%=registroPaciente.getFechaNac()%>">
            <label for="txtFechaNac">FechaNac</label>
        </div> 


        <div class="input-field col l4 s12">
            <input  id="txtLugarNac" type="text" name="lugarnac" value="<%=registroPaciente.getLugarNac()%>">
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
            <input  id="txtCelular" type="text" name="celular" value="<%=registroPaciente.getCelular()%>">
            <label for="txtCelular">Celular</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEmail" type="text" name="email" value="<%=registroPaciente.getEmail()%>">
            <label for="txtEmail">Email</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtEstadoCivil" type="text" name="etadocivil" value="<%=registroPaciente.getEstadoCivil()%>">
            <label for="txtEstadoCivil">Estado Civil</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEdad" type="number" name="edad">
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
