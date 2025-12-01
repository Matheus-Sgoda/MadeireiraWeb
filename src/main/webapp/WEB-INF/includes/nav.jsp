<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.madeireira.model.Usuario" %>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
%>
<nav class="navbar">
    <a href="index.jsp" class="navbar-brand">Madeireira</a>
    
    <ul class="navbar-menu">
        <li><a href="${pageContext.request.contextPath}/produtos">Produtos</a></li>
        <li><a href="${pageContext.request.contextPath}/categorias">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/status">Status</a></li>
        <li><a href="${pageContext.request.contextPath}/usuarios">Usuários</a></li>
    </ul>
</nav>
