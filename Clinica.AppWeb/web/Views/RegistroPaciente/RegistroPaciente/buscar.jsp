<%-- 
    Document   : buscar
    Created on : 2 sep. 2022, 15:59:35
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<RegistroPaciente> registroPacientes = (ArrayList<RegistroPaciente>) request.getAttribute("lista");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (registroPacientes == null) { 
        registroPacientes = new ArrayList();
        
} else if (registroPacientes.size() > numReg) {
        double divNumPage = (double) registroPacientes.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }

        %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <table>
            <thead>
                <tr>
                    <td>nombre</td>
                    <td>apellido</td>
                    <td>dui</td>
                </tr>
            </thead>
            <tbody>
                 <% 
                       if(registroPacientes.size()>0){                                            
                            for (RegistroPaciente registroPaciente : registroPacientes) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                <tr>
                    <td><%=registroPaciente.getNombre()%>  </td>
                    <td><%=registroPaciente.getApellido()%></td>
                    <td><%=registroPaciente.getDui()%></td>
                </tr>
                <%}
                }
                %>
            </tbody>
        </table>


    </body>
</html>
