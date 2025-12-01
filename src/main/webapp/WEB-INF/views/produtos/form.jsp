<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${produto != null ? 'Editar' : 'Novo'} Produto - Madeireira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/nav.jsp" />
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">${produto != null ? 'Editar' : 'Novo'} Produto</h1>
        </div>
        
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/produtos/${produto != null ? 'editar' : 'novo'}" method="post">
                <c:if test="${produto != null}">
                    <input type="hidden" name="id" value="${produto.id}">
                </c:if>
                
                <div class="form-group">
                    <label for="nome">Nome do Produto *</label>
                    <input type="text" id="nome" name="nome" value="${produto.nome}" required>
                </div>
                
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <textarea id="descricao" name="descricao">${produto.descricao}</textarea>
                </div>
                
                <div class="form-group">
                    <label for="fkCategoria">Categoria *</label>
                    <select id="fkCategoria" name="fkCategoria" required>
                        <option value="">Selecione uma categoria</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.id}" ${produto.fkCategoria == categoria.id ? 'selected' : ''}>
                                ${categoria.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="fkStatus">Status *</label>
                    <select id="fkStatus" name="fkStatus" required>
                        <option value="">Selecione um status</option>
                        <c:forEach var="st" items="${status}">
                            <option value="${st.id}" ${produto.fkStatus == st.id ? 'selected' : ''}>
                                ${st.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="quantidade">Quantidade em Estoque *</label>
                    <input type="number" id="quantidade" name="quantidade" step="0.01" value="${produto.quantidade}" required>
                </div>
                
                <div class="form-group">
                    <label for="unidadeMedida">Unidade de Medida *</label>
                    <input type="text" id="unidadeMedida" name="unidadeMedida" value="${produto.unidadeMedida != null ? produto.unidadeMedida : 'un'}" required>
                </div>
                
                <div class="form-group">
                    <label for="preco">Preço (R$) *</label>
                    <input type="number" id="preco" name="preco" step="0.01" value="${produto.preco}" required>
                </div>
                
                <div class="form-group">
                    <label for="imagemUrl">URL da Imagem</label>
                    <input type="url" id="imagemUrl" name="imagemUrl" value="${produto.imagemUrl}" placeholder="https://exemplo.com/imagem.jpg">
                </div>
                
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/produtos" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
