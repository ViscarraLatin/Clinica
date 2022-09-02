<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.entidadesdenegocio.RegistroPaciente"%>
<%@page import="clinica.accesoadatos.RegistroPacienteDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<RegistroPaciente> registroPacientes = RegistroPacienteDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slRegistroPaciente" name="idRegistroPaciente">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (RegistroPaciente registroPaciente : registroPacientes) {%>
    <option <%=(id == registroPaciente.getId()) ? "selected" : ""%>  value="<%=registroPaciente.getId()%>"><%= registroPaciente.getNombre()%><%=registroPaciente.getDui()%><%=registroPaciente.getGenero()%><%=registroPaciente.getFechaNac()%><%=registroPaciente.getLugarNac()%>
    <%=registroPaciente.getOcupacion()%><%=registroPaciente.getTelefono()%><%=registroPaciente.getCelular()%><%=registroPaciente.getEmail()%>
    <%=registroPaciente.getEstadoCivil()%><%=registroPaciente.getEdad()%><%=registroPaciente.getDireccion()%>
    <%=registroPaciente.getPeso()%><%=registroPaciente.getEstatura()%></option>
    <%}%>
</select>
<label for="idRegistroPaciente">RegistroPaciente</label>