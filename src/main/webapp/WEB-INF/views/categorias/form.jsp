<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${categoria != null ? 'Editar' : 'Nova'} Categoria - Madeireira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/nav.jsp" />
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">${categoria != null ? 'Editar' : 'Nova'} Categoria</h1>
        </div>
        
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/categorias/${categoria != null ? 'editar' : 'novo'}" method="post">
                <c:if test="${categoria != null}">
                    <input type="hidden" name="id" value="${categoria.id}">
                </c:if>
                
                <div class="form-group">
                    <label for="descricao">Descrição *</label>
                    <input type="text" id="descricao" name="descricao" value="${categoria.descricao}" required>
                </div>
                
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/categorias" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
