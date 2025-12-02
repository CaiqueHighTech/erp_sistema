<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestão de Estoque</title>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Gestão de Estoque</h1>
        
        <logic:present name="erro">
            <div class="erro">
                <strong>Erro:</strong> <bean:write name="erro"/>
            </div>
        </logic:present>

        <logic:present name="estoques">
            <table border="1" cellpadding="5" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Produto</th>
                        <th>Quantidade</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="estoques" id="estoque">
                        <tr>
                            <td><bean:write name="estoque" property="id"/></td>
                            <td><bean:write name="estoque" property="produto.nome"/></td>
                            <td><bean:write name="estoque" property="quantidade"/></td>
                            <td>
                                <a href="#">Editar</a> | 
                                <a href="#">Deletar</a>
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>

        <logic:notPresent name="estoques">
            <p>Nenhum estoque encontrado.</p>
        </logic:notPresent>

        <br/>
        <a href="../index.jsp">Voltar ao Menu Principal</a>
    </div>
</body>
</html>
