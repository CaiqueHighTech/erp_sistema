<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ERP Sistema Híbrido</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao ERP Sistema Híbrido</h1>
        <p>Este sistema demonstra a integração de Struts 1.x, Spring e Hibernate.</p>

        <nav>
            <ul>
                <li><a href="produtos.do?action=listar">Gestão de Produtos (Struts + Spring + Hibernate)</a></li>
                <li><a href="estoque.do">Gestão de Estoque (A implementar)</a></li>
                <li><a href="vendas.do">Gestão de Vendas (A implementar)</a></li>
            </ul>
        </nav>

        <p>Acesse a <a href="produtos.do?action=listar">Gestão de Produtos</a> para ver a funcionalidade implementada.</p>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/js/app.js"></script>
</body>
</html>
