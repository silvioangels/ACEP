package br.com.fiap.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet("/ServI18N")
public class ServI18N extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServI18N() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String language = request.getParameter("lingua");
		if(language == null){
			language = "en";
		}
		Locale locale = new Locale(language);
		Config.set(request.getSession(), Config.FMT_LOCALE,locale);
		Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);
		
		request.getSession().setAttribute("lingua", language);
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		response.sendRedirect("pages-signin.jsp?lingua="+locale);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void limparAlertMensagem(HttpSession sessao){
		
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("msgAlert", "");
		
	}

}
