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
    <link rel="stylesheet" type="text/css" href="../static/css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .erro { color: red; background-color: #ffcccc; padding: 10px; margin: 10px 0; }
        .info { color: blue; background-color: #ccccff; padding: 10px; margin: 10px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Gestão de Vendas</h1>
        
        <logic:present name="erro">
            <div class="erro">
                <strong>Erro:</strong> <bean:write name="erro"/>
            </div>
        </logic:present>

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
                                    R$ <fmt:formatNumber value="${venda.valorTotal}" type="currency" currencyCode="BRL" maxFractionDigits="2"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${venda.dataVenda}" pattern="dd/MM/yyyy"/>
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
        <a href="../index.jsp">Voltar ao Menu Principal</a>
    </div>
</body>
</html>

