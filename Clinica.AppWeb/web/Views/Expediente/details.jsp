<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.Expediente"%>
<% Expediente expediente = (Expediente) request.getAttribute("expediente");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Expediente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Expediente</h5>
             <div class="row">
                     <div class="input-field col l4 s12">
                        <input id="txtRegistroPaciente" type="text" value="<%=expediente.getRegistroPaciente().getNombre() %>" disabled>
                        <label for="txtRegistroPaciente">Paciente</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="MotivoConsulta" type="text" value="<%=expediente.getMotivoConsulta()%>" disabled>
                        <label for="txtMotivoConsulta">Motivo de Consulta</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtSintomas" type="text" value="<%=expediente.getSintomas()%>" disabled>
                        <label for="txtSintomas">Síntomas</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtSignosVitales" type="text" value="<%=expediente.getSignosVitales()%>" disabled>
                        <label for="txtSignosVitales">SignosVitales</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" value="<%=expediente.getDescripcion()%>" disabled>
                        <label for="txtDescripcion">Descripción</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtExamenesComp" type="text" value="<%=expediente.getExamenesComp()%>" disabled>
                        <label for="txtExamenesComp">Exámenes Complementarios</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtDiagnostico" type="text" value="<%=expediente.getDiagnostico()%>" disabled>
                        <label for="txtDiagnostico">Diagnóstico</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtTratamiento" type="text" value="<%=expediente.getTratamiento()%>" disabled>
                        <label for="txtTratamiento">Tratamiento</label>
                    </div>                                          
                </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="Expediente?accion=edit&id=<%=expediente.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Expediente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>
