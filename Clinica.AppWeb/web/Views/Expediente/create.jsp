<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.Expediente"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Expediente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Expediente</h5>
            <form action="Expediente" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtMotivoConsulta" type="text" name="motivoConsulta" required class="validate" maxlength="30">
                        <label for="txtMotivoConsulta">MotivoConsulta</label>
                    </div>   
                    <div class="input-field col l4 s12">
                        <input  id="txtSintomas" type="text" name="sintomas" required class="validate" maxlength="30">
                        <label for="txtSintomas">Síntomas</label>
                    </div>             
                     <div class="input-field col l4 s12">
                        <input  id="txtSignosVitales" type="text" name="signosVitales" required class="validate" maxlength="25">
                        <label for="txtSignosVitales">SignosVitales</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="descripcion" required class="validate" maxlength="25">
                        <label for="txtDescripcion">Descripción</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtExamenesComp" type="text" name="examenesComp" required class="validate" maxlength="25">
                        <label for="txtDescripcion">Exámenes Complementarios</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtDiagnostico" type="text" name="diagnostico" required class="validate" maxlength="30">
                        <label for="txtDiagnostico">Diagnóstico</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtTratamiento" type="text" name="tratamiento" required class="validate" maxlength="25">
                        <label for="txtTratamiento">Tratamiento</label>
                    </div> 
                 
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/RegistroPaciente/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slRegistroPaciente_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Expediente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slRegistroPaciente = document.getElementById("slRegistroPaciente");
                var slRegistroPaciente_error = document.getElementById("slRegistroPaciente_error");
                if (slRegistroPaciente.value == 0) {
                    slRegistroPaciente_error.innerHTML = "El Paciente es obligatorio";
                    result = false;
                } else {
                    slRegistroPaciente_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>

