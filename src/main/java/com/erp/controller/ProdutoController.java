package com.erp.controller;

import com.erp.model.Estoque;
import com.erp.model.Produto;
import com.erp.service.EstoqueService;
import com.erp.service.ProdutoService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller (Action) do Struts para gerenciar operações de Produto.
 */
public class ProdutoController extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Obtém os serviços do Spring Context
        ProdutoService produtoService = (ProdutoService) getBean(getServlet().getServletContext(), "produtoService");
        EstoqueService estoqueService = (EstoqueService) getBean(getServlet().getServletContext(), "estoqueService");

        String action = request.getParameter("action");

        if ("listar".equals(action) || action == null) {
            List<Produto> produtos = produtoService.listarTodos();
            request.setAttribute("produtos", produtos);
            return mapping.findForward("listarProdutos");
        } else if ("salvar".equals(action)) {
            // Obtém os dados do formulário
            String nome = request.getParameter("nome");
            String precoStr = request.getParameter("preco");
            String quantidadeStr = request.getParameter("quantidadeEstoque");
            
            try {
                Double preco = Double.parseDouble(precoStr);
                Integer quantidade = Integer.parseInt(quantidadeStr);
                
                // Cria e salva o novo produto
                Produto novoProduto = new Produto();
                novoProduto.setNome(nome);
                novoProduto.setPreco(preco);
                novoProduto.setQuantidadeEstoque(quantidade);
                produtoService.salvar(novoProduto);

                // Cria um registro de estoque para o novo produto
                Estoque estoque = new Estoque();
                estoque.setProduto(novoProduto);
                estoque.setQuantidade(quantidade);
                estoqueService.salvar(estoque);

                // Após salvar, redireciona para a listagem
                return mapping.findForward("listarProdutosRedirect");
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Erro ao converter valores numéricos. Verifique os dados inseridos.");
                List<Produto> produtos = produtoService.listarTodos();
                request.setAttribute("produtos", produtos);
                return mapping.findForward("listarProdutos");
            }
        }

        return mapping.findForward("erro");
    }
}
