<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<% RegistroPaciente registroPaciente = (RegistroPaciente) request.getAttribute("registropaciente");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Paciente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar paciente</h5>
            <form action="Rol" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=registroPaciente.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=registroPaciente.getNombre()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>           
                        
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
                        
                         <div class="input-field col l4 s12">
            <input  id="txtFechaNac" type="text" name="fechaNac" value="<%=registroPaciente.getFechaNac()%>" required class="validate" maxlength="30">
            <label for="txtFechaNac">FechaNac</label>
        </div> 


        <div class="input-field col l4 s12">
            <input  id="txtLugarNac" type="text" name="lugarNac" value="<%=registroPaciente.getLugarNac()%>" required class="validate" maxlength="30" >
            <label for="txtLugarNac">LugarNac</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtOcupacion" type="text" name="ocupacion" value="<%=registroPaciente.getOcupacion()%>" required class="validate" maxlength="30">
            <label for="txtOcupacion">Ocupacion</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtTelefono" type="text" name="telefono" value="<%=registroPaciente.getTelefono()%>" required class="validate" maxlength="30">
            <label for="txtTelefono">Telefono</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtCelular" type="text" name="celular"value="<%=registroPaciente.getCelular()%>" required class="validate" maxlength="30">
            <label for="txtCelular">Celular</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEmail" type="text" name="email" value="<%=registroPaciente.getEmail()%>" required class="validate" maxlength="30">
            <label for="txtEmail">Email</label>
        </div> 

        <div class="input-field col l4 s12">
            <input  id="txtEstadoCivil" type="text" name="etadocivil"value="<%=registroPaciente.getEstadoCivil()%>" required class="validate" maxlength="30">
            <label for="txtEstadoCivil">Estado Civil</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEdad" type="number" name="edad" value="<%=registroPaciente.getEdad()%>" required class="validate" maxlength="30">
            <label for="txtEdad">Edad</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtDireccion" type="text" name="direccion" value="<%=registroPaciente.getDireccion()%>" required class="validate" maxlength="30">
            <label for="txtDireccion">Direccion</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtPeso" type="number" name="peso" value="<%=registroPaciente.getPeso()%>" required class="validate" maxlength="30">
            <label for="txtPeso">Peso</label>
        </div> 
        <div class="input-field col l4 s12">
            <input  id="txtEstatura" type="text" name="estatura" value="<%=registroPaciente.getEstatura()%>" required class="validate" maxlength="30">
            <label for="txtEstatura">Estatura</label>
        </div> 

                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="RegistroPaciente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
