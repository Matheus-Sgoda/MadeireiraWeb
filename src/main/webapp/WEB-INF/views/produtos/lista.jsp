<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos - Madeireira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/nav.jsp" />
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">Inventário de Produtos</h1>
            <a href="${pageContext.request.contextPath}/produtos/novo" class="btn btn-primary">
                + Adicionar Produto
            </a>
        </div>
        
        <div class="table-container">
            <c:choose>
                <c:when test="${empty listaProdutos}">
                    <div class="empty-state">
                        <h3>Nenhum produto cadastrado</h3>
                        <p>Clique em "Adicionar Produto" para começar</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>Imagem</th>
                                <th>Nome do Produto</th>
                                <th>Tipo</th>
                                <th>Estoque</th>
                                <th>Preço</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody id="produtosTable">
                            <c:forEach var="produto" items="${listaProdutos}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty produto.imagemUrl}">
                                                <img src="${produto.imagemUrl}" alt="${produto.nome}" class="product-image">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="product-image" style="background-color: #e5e7eb;"></div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${produto.nome}</td>
                                    <td>${produto.nomeCategoria}</td>
                                    <td>${produto.quantidade}</td>
                                    <td>${produto.precoFormatado}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${pageContext.request.contextPath}/produtos/editar?id=${produto.id}" class="icon-btn" title="Editar">
                                                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                                    <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                                                    <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                                                </svg>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/produtos/excluir?id=${produto.id}" class="icon-btn delete" title="Excluir" onclick="return confirm('Tem certeza que deseja excluir o produto ${produto.nome}?')">
                                                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                                    <polyline points="3 6 5 6 21 6"></polyline>
                                                    <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                                                </svg>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    

</body>
</html>
