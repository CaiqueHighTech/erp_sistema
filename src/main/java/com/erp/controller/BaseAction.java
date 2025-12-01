package com.erp.controller;

import org.apache.struts.action.Action;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;

/**
 * Classe base para Actions do Struts que fornece acesso ao Spring Application Context.
 */
public abstract class BaseAction extends Action {

    /**
     * Obtém um bean do Spring Application Context.
     * @param servletContext O contexto do Servlet.
     * @param beanName O nome do bean (geralmente o nome da classe em camelCase).
     * @return O bean solicitado.
     */
    protected Object getBean(ServletContext servletContext, String beanName) {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (context == null) {
            throw new IllegalStateException("Spring WebApplicationContext não encontrado. Verifique a configuração do ContextLoaderListener no web.xml.");
        }
        return context.getBean(beanName);
    }
}
