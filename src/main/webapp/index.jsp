<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ERP</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao ERP</h1>
        <p>Este sistema ajuda na gestão das empresas.</p>

        <nav>
            <ul>
                <li><a href="produtos.do?action=listar">Gestão de Produtos</a></li>
                <li><a href="estoque.do">Gestão de Estoque</a></li>
                <li><a href="vendas.do">Gestão de Vendas</a></li>
            </ul>
        </nav>

        <p>Acesse a <a href="produtos.do?action=listar">Gestão de Produtos</a> para ver a funcionalidade implementada.</p>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/js/app.js"></script>
</body>
</html>
