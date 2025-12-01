<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${usuario != null ? 'Editar' : 'Novo'} Usuário - Madeireira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/nav.jsp" />
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">${usuario != null ? 'Editar' : 'Novo'} Usuário</h1>
        </div>
        
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/usuarios/${usuario != null ? 'editar' : 'novo'}" method="post">
                <c:if test="${usuario != null}">
                    <input type="hidden" name="id" value="${usuario.id}">
                </c:if>
                
                <div class="form-group">
                    <label for="nome">Nome Completo *</label>
                    <input type="text" id="nome" name="nome" value="${usuario.nome}" required>
                </div>
                
                <div class="form-group">
                    <label for="email">E-mail *</label>
                    <input type="email" id="email" name="email" value="${usuario.email}" required>
                </div>
                
                <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input type="text" id="telefone" name="telefone" value="${usuario.telefone}">
                </div>
                
                <div class="form-group">
                    <label for="endereco">Endereço</label>
                    <input type="text" id="endereco" name="endereco" value="${usuario.endereco}">
                </div>
                
                <div class="form-group">
                    <label for="senha">Senha *</label>
                    <input type="password" id="senha" name="senha" value="${usuario.senha}" required>
                </div>
                
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
