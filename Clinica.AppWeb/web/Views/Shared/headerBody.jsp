<%-- 
    Document   : headerBody
    Created on : 22 ago. 2022, 08:38:18
    Author     : KATHYA VISCARRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clinica.appweb.utils.*"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<nav>
    <div class="nav-wrapper blue">
        <a href="Home" class="brand-logo">Clinica</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
            <% if (SessionUser.isAuth(request)) {  %>
            <li><a href="Home">Inicio</a></li>
            <li><a href="Contacto">RegistroPaciente</a></li>
            <li><a href="Empresa">Expediente</a></li>
            <li><a href="Usuario">Usuarios</a></li>
            <li><a href="Rol">Roles</a></li>
            <li><a href="Usuario?accion=cambiarpass">Cambiar Password</a></li>
            <li><a href="Usuario?accion=login">Cerrar Sesión</a></li>
            <%}%>
        </ul>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
     <% if (SessionUser.isAuth(request)) {  %>
     <li><a href="Home">Inicio</a></li>
     <li><a href="Contacto">RegistroPaciente</a></li>
     <li><a href="Empresa">Expediente</a></li>
     <li><a href="Usuario">Usuarios</a></li>
     <li><a href="Rol">Roles</a></li>
     <li><a href="Usuario?accion=cambiarpass">Cambiar Password</a></li>
     <li><a href="Usuario?accion=login">Cerrar Sesión</a></li>
     <%}%>
</ul>
