
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Registro Pacientefsdfasdf</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Registro Paciente safdsf</h5>
            <form action="RegistroPaciente" method="post">
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
                        <label for="txtDui">Dui</label>
                    </div>    
                     <div class="input-field col l4 s12">
                        <input  id="txtGenero" type="text" name="genero" required class="validate" maxlength="30">
                        <label for="txtGenero">Genero</label>
                    </div>    
                    <div class="input-field col l4 s12">
                        <input  id="txtFechaNac" type="text" name="FechaNac" required class="validate" maxlength="30">
                        <label for="txtFechaNac"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtLugarNac" type="text" name="LugarNac" required class="validate" maxlength="30">
                        <label for="txtLugarNac"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtOcupacion" type="text" name="ocupacion" required class="validate" maxlength="30">
                        <label for="txtOcupacion"></label>
                    </div>    
         l p           
                       <div class="input-field col l4 s12">
                        <input  id="txtTelefono" type="text" name="Telefono" required class="validate" maxlength="30">
                        <label for="txtTelefono"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtCelular" type="text" name="Celular" required class="validate" maxlength="30">
                        <label for="txtlarCelu"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEmail" type="text" name="Email" required class="validate" maxlength="30">
                        <label for="txtEmail"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEstadoCivil" type="text" name="Estadocivil" required class="validate" maxlength="30">
                        <label for="txt"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEdad" type="text" name="Edad" required class="validate" maxlength="30">
                        <label for="txtEdad"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtDireccion" type="text" name="direccion" required class="validate" maxlength="30">
                        <label for="txtDireccion"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtPeso" type="text" name="Peso" required class="validate" maxlength="30">
                        <label for="txtPeso"></label>
                    </div>    
                       <div class="input-field col l4 s12">
                        <input  id="txtEstatura" type="text" name="Estatura" required class="validate" maxlength="30">
                        <label for="txtEstatura"></label>
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
