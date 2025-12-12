<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestão de Produtos</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Gestão de Produtos</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Menu Principal</a></li>
                <li><a href="estoque.do">Estoque</a></li>
                <li><a href="vendas.do">Vendas</a></li>
            </ul>
        </nav>

        <h2>Lista de Produtos</h2>
        <c:if test="${empty requestScope.produtos}">
            <p>Nenhum produto encontrado.</p>
        </c:if>
        <c:if test="${not empty requestScope.produtos}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Quantidade em Estoque</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${requestScope.produtos}">
                        <tr>
                            <td><c:out value="${produto.id}"/></td>
                            <td><c:out value="${produto.nome}"/></td>
                            <td>R$ <c:out value="${produto.preco}"/></td>
                            <td><c:out value="${produto.quantidadeEstoque}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <h2>Adicionar Novo Produto</h2>
        <html:form action="/produtos.do?action=salvar" method="post">
            <div class="form-group">
                <label for="nome">Nome do Produto:</label>
                <input type="text" id="nome" name="nome" required/>
            </div>
            <div class="form-group">
                <label for="preco">Preço:</label>
                <input type="number" id="preco" name="preco" step="0.01" required/>
            </div>
            <div class="form-group">
                <label for="quantidadeEstoque">Quantidade em Estoque:</label>
                <input type="number" id="quantidadeEstoque" name="quantidadeEstoque" required/>
            </div>
            <html:submit value="Adicionar Produto"/>
        </html:form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/js/app.js"></script>
</body>
</html>
