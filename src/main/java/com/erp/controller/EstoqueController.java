package com.erp.controller;

import com.erp.model.Estoque;
import com.erp.service.EstoqueService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EstoqueController extends BaseAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obter o ApplicationContext do Spring
            WebApplicationContext applicationContext = WebApplicationContextUtils
                    .getWebApplicationContext(getServlet().getServletContext());
            
            EstoqueService estoqueService = (EstoqueService) applicationContext.getBean("estoqueService");
            
            // Listar todos os estoques
            List<Estoque> estoques = estoqueService.listarTodos();
            request.setAttribute("estoques", estoques);
            
            return mapping.findForward("listarEstoque");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao listar estoques: " + e.getMessage());
            return mapping.findForward("erro");
        }
    }
}
