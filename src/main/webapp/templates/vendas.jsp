<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestão de Vendas</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Gestão de Vendas</h1>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Menu Principal</a></li>
                <li><a href="produtos.do?action=listar">Produtos</a></li>
                <li><a href="estoque.do">Estoque</a></li>
                <li><a href="vendas.do">Vendas</a></li>
            </ul>
        </nav>

        <logic:present name="erro">
            <div class="erro">
                <strong>Erro:</strong> <bean:write name="erro"/>
            </div>
        </logic:present>

        <logic:present name="sucesso">
            <div class="info">
                <strong>Sucesso:</strong> <bean:write name="sucesso"/>
            </div>
        </logic:present>

        <h2>Adicionar Nova Venda</h2>
        <form action="vendas.do" method="post" id="vendaForm">
            <input type="hidden" name="action" value="salvar">
            <div class="form-group">
                <label for="produtoId">Produto:</label>
                <select id="produtoId" name="produtoId" required>
                    <option value="">-- Selecione um produto --</option>
                    <c:if test="${not empty requestScope.produtos}">
                        <c:forEach var="produto" items="${requestScope.produtos}">
                            <c:if test="${produto.quantidadeEstoque > 0}">
                                <option value="${produto.id}">
                                    ${produto.nome} (Disponível: ${produto.quantidadeEstoque})
                                </option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="form-group">
                <label for="valorUnitario">Valor Unitário (R$):</label>
                <input type="number" id="valorUnitario" name="valorUnitario" step="0.01" required/>
            </div>
            <div class="form-group">
                <label for="quantidade">Quantidade Vendida:</label>
                <input type="number" id="quantidade" name="quantidade" required/>
            </div>
            <div class="form-group">
                <label for="valorTotal">Valor Total (R$):</label>
                <input type="number" id="valorTotal" name="valorTotal" step="0.01" readonly/>
            </div>
            <input type="submit" value="Registrar Venda"/>
        </form>

        <h2>Histórico de Vendas</h2>
        <logic:present name="vendas">
            <logic:empty name="vendas">
                <div class="info">
                    <strong>Nenhuma venda encontrada.</strong>
                </div>
            </logic:empty>
            
            <logic:notEmpty name="vendas">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Produto</th>
                            <th>Quantidade Vendida</th>
                            <th>Valor Unitário</th>
                            <th>Valor Total</th>
                            <th>Data da Venda</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate name="vendas" id="venda">
                            <tr>
                                <td><bean:write name="venda" property="id"/></td>
                                <td><bean:write name="venda" property="produto.nome"/></td>
                                <td><bean:write name="venda" property="quantidadeVendida"/></td>
                                <td>
                                    R$ <fmt:formatNumber value="${venda.valorUnitario}" type="currency" currencyCode="BRL" maxFractionDigits="2"/>
                                </td>
                                <td>
                                    R$ <fmt:formatNumber value="${venda.valorTotal}" type="currency" currencyCode="BRL" maxFractionDigits="2"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${venda.dataVenda}" pattern="dd/MM/yyyy HH:mm"/>
                                </td>
                                <td>
                                    <a href="#">Editar</a> | 
                                    <a href="#">Deletar</a>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            </logic:notEmpty>
        </logic:present>

        <logic:notPresent name="vendas">
            <div class="info">
                <strong>Nenhuma venda encontrada.</strong>
            </div>
        </logic:notPresent>

        <br/>
        <a href="index.jsp">Voltar ao Menu Principal</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/js/app.js"></script>
    <script>
        // Atualizar valor total quando mudar quantidade ou valor unitário
        $(document).ready(function() {
            function atualizarValorTotal() {
                var valorUnitario = parseFloat($('#valorUnitario').val()) || 0;
                var quantidade = parseFloat($('#quantidade').val()) || 0;
                var valorTotal = valorUnitario * quantidade;
                $('#valorTotal').val(valorTotal.toFixed(2));
            }
            
            $('#valorUnitario, #quantidade').on('input', atualizarValorTotal);
        });
    </script>
</body>
</html>


