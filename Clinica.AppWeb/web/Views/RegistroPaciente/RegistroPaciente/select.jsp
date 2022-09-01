<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<%@page import="clinica.accesoadatos.RegistroPacienteDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<RegistroPaciente> registroPacientes = RegistroPacienteDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slRegistroPaciente" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (RegistroPaciente registroPaciente : registroPacientes) {%>
    <option <%=(id == registroPaciente.getId()) ? "selected" : ""%>  value="<%=registroPaciente.getId()%>"><%= registroPaciente.getNombre()%></option>
    <%}%>
</select>
<label for="idRegistroPaciente">RegistroPaciente</label>
