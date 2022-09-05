<%-- 
    Document   : create
    Created on : 22 ago. 2022, 08:33:03
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Registro Paciente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Registro Pacientesss</h5>
            <form action="RegistroPaciente" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>    
                    
                 <div class="input-field col l4 s12">
                        <input  id="txtApellido" type="text" name="apellido" required class="validate" maxlength="30">
                        <label for="txtApellido">Apellido</label>
                    </div>    
                     <div class="input-field col l4 s12">
                        <input  id="txtDui" type="text" name="dui" required class="validate" maxlength="30">
                        <label for="txtDui">DUI</label>
                    </div>    
                     <div class="input-field col l4 s12">
                        <input  id="txtGenero" type="text" name="genero" required class="validate" maxlength="30">
                        <label for="txtGenero">Genero</label>
                    </div>    
                    <div class="input-field col l4 s12">
                        <input  id="txtFechaNac" type="text" name="fechanac" required class="validate" maxlength="30">
                        <label for="txtFechaNac">FechaNac</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtLugarNac" type="text" name="lugarnac" required class="validate" maxlength="30">
                        <label for="txtLugarNac">LugarNac</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtOcupacion" type="text" name="ocupacion" required class="validate" maxlength="30">
                        <label for="txtOcupacion">Ocupacion</label>
                    </div>    
                    
                       <div class="input-field col l4 s12">
                        <input  id="txtTelefono" type="text" name="telefono" required class="validate" maxlength="30">
                        <label for="txtTelefono">Telefono</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtCelular" type="text" name="celular" required class="validate" maxlength="30">
                        <label for="txtCelular">celular</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEmail" type="text" name="email" required class="validate" maxlength="30">
                        <label for="txtEmail">Email</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEstadoCivil" type="text" name="estadoCivil" required class="validate" maxlength="30">
                        <label for="txtEstadoCivil">Estado Civil</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEdad" type="number" name="edad" required class="validate" maxlength="30">
                        <label for="txtEdad">Edad</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtDireccion" type="text" name="direccion" required class="validate" maxlength="30">
                        <label for="txtDireccion">Direccion</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtPeso" type="number" name="peso" required class="validate" maxlength="30">
                        <label for="intPeso">Peso</label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEstatura" type="text" name="estatura" required class="validate" maxlength="30">
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
