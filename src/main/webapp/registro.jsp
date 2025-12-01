<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro - Madeireira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="auth-container">
        <div class="auth-box">
            <h1 class="auth-subtitle">Cadastro</h1>
            
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("erro") %>
                </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/registro" method="post">
                <div class="form-group">
                    <label for="nome">Nome completo</label>
                    <input type="text" id="nome" name="nome" required>
                </div>
                
                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="senha">Senha</label>
                    <input type="password" id="senha" name="senha" required>
                </div>
                
                <div class="form-group">
                    <label for="confirmacaoSenha">Confirmação de senha</label>
                    <input type="password" id="confirmacaoSenha" name="confirmacaoSenha" required>
                </div>
                
                <button type="submit" class="btn btn-primary">Finalizar cadastro</button>
            </form>
            
            <div class="auth-link">
                Já tem uma conta? <a href="${pageContext.request.contextPath}/login">Faça login</a>
            </div>
        </div>
    </div>
</body>
</html>
