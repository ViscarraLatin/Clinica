<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.Expediente"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Expediente> expedientes = (ArrayList<Expediente>) request.getAttribute("expedientes");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (expedientes == null) {
        expedientes = new ArrayList();
    } else if (expedientes.size() > numReg) {
        double divNumPage = (double) expedientes.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Expedientes</title>

    </head>
    
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
           
            <h5>Buscar Expedientes</h5>
            <form action="Expediente" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                
                <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtMotivoConsulta" type="text" name="motivoConsulta">
                        <label for="txtMotivoConsulta">MotivoConsulta</label>                      
                    </div>
                   <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtSintomas" type="text" name="sintomas">
                        <label for="txtSintomas">Sintomas</label>                      
                    </div>
                   
                      <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtDescripcion" type="text" name="descripcion">
                        <label for="txtDescripcion">Descripcion</label>                      
                    </div>
                          
                  <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtExamenesComp" type="text" name="examenesComp">
                        <label for="txtExamenesComp">ExamenesComp</label>                      
                    </div>
                  </div>
                            <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtDiagnostico" type="text" name="diagnostico">
                        <label for="txtDiagnostico">Diagnostico</label>                      
                    </div>
                                  <div class="row">
                    <div class="input-field col l4 s12">   
                         <input  id="txtTratamiento" type="text" name="tratamiento">
                        <label for="txtTratamiento">Tratamiento</label>                      
                    </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Expediente?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr> 
                                    <th>RegistroPaciente</th> 
                                    <th>MotivoConsulta</th>  
                                    <th>Sintomas</th> 
                                    <th>SignosVitales</th>  
                                    <th>Descripcion</th>  
                                    <th>ExamenesComp</th>   
                                    <th>Diagnostico</th>  
                                    <th>Tratamiento</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Expediente expediente : expedientes) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">  
                                    <td><%=expediente.getRegistroPaciente().getNombre()%></td> 
                                    <td><%=expediente.getMotivoConsulta()%></td>  
                                    <td><%=expediente.getSintomas()%></td>
                                    <td><%=expediente.getSignosVitales()%></td>  
                                    <td><%=expediente.getDescripcion()%></td>
                                    <td><%=expediente.getExamenesComp()%></td> 
                                    <td><%=expediente.getDiagnostico()%></td> 
                                    <td><%=expediente.getTratamiento()%></td> 
                                    <td>
                                        <div style="display:flex">
                                             <a href="Expediente?accion=edit&id=<%=expediente.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                        <a href="Expediente?accion=details&id=<%=expediente.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="Expediente?accion=delete&id=<%=expediente.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>    
                                        </div>                                                                    
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    
</html>