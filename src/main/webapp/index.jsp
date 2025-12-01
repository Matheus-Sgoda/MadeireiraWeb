<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Madeireira - Soluções em Madeira</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/nav.jsp" />
    <header class="landing-hero">
        <div class="container hero-content">
            <h1>Madeireira, o parceiro certo para cada projeto em madeira</h1>
            <p class="hero-text">
                Atendemos construtoras, marceneiros e designers com madeira de origem controlada,
                beneficiada com precisão e pronta para chegar ao seu canteiro ou estúdio na data combinada.
            </p>
            <div class="hero-cta">
                <a href="<%= request.getContextPath() %>/produtos" class="btn btn-primary">Conhecer portfólio</a>
            </div>
        </div>
    </header>
    <main>
        <section class="section">
            <div class="container">
                <div class="section-header">
                    <p class="eyebrow">Nossos diferenciais</p>
                    <h2>Soluções completas em madeira, da floresta ao acabamento</h2>
                    <p>Operamos com fornecedores certificados, logística própria e um time que entende o ritmo do seu cronograma.</p>
                </div>
                <div class="highlight-grid">
                    <article class="highlight-card">
                        <h3>Fornecimento sustentável</h3>
                        <p>Matérias-primas de manejo responsável, documentação completa e rastreabilidade garantida.</p>
                    </article>
                    <article class="highlight-card">
                        <h3>Beneficiamento preciso</h3>
                        <p>Cortes milimétricos, secagem controlada e pacotes prontos para montagem sem desperdícios.</p>
                    </article>
                    <article class="highlight-card">
                        <h3>Suporte técnico</h3>
                        <p>Equipe dedicada para especificações, laudos e adequações às normas de qualquer tipo de obra.</p>
                    </article>
                    <article class="highlight-card">
                        <h3>Logística inteligente</h3>
                        <p>Rotas otimizadas, rastreio em tempo real e entregas planejadas conforme cada etapa do projeto.</p>
                    </article>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
