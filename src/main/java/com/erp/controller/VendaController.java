package com.erp.controller;

import com.erp.model.Venda;
import com.erp.service.VendaService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class VendaController extends BaseAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obter o ApplicationContext do Spring
            WebApplicationContext applicationContext = WebApplicationContextUtils
                    .getWebApplicationContext(getServlet().getServletContext());
            
            VendaService vendaService = (VendaService) applicationContext.getBean("vendaService");
            
            // Listar todas as vendas
            List<Venda> vendas = vendaService.listarTodos();
            request.setAttribute("vendas", vendas);
            
            return mapping.findForward("listarVendas");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao listar vendas: " + e.getMessage());
            return mapping.findForward("erro");
        }
    }
}
