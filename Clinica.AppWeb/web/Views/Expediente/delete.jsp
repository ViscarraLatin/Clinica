<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.Expediente"%>
<% Expediente expediente = (Expediente) request.getAttribute("expediente");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Expediente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Expediente</h5>
            <form action="Expediente" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=expediente.getId()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input id="txtRegistroPaciente" type="text" value="<%=expediente.getRegistroPacientes().getNombre()%>" disabled>
                        <label for="txtRegistropaciente">Paciente</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtMotivoConsulta" type="text" value="<%=expediente.getMotivoConsulta()%>" disabled>
                        <label for="txtMotivoConsulta">Motivo de Consulta</label>
                    </div>                         
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Expediente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>