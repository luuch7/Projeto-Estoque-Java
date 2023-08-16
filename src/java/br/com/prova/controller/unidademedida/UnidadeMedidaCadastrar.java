/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prova.controller.unidademedida;

import br.com.prova.dao.GenericDAO;
import br.com.prova.dao.UnidadeMedidaDAO;
import br.com.prova.model.UnidadeMedida;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luuch
 */
@WebServlet(name = "UnidadeMedidaCadastrar", urlPatterns = {"/UnidadeMedidaCadastrar"})
public class UnidadeMedidaCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        int idUnidadeMedida = Integer.parseInt(request.getParameter("idunidademedida"));
        String descricao = request.getParameter("descricao");
        String sigla = request.getParameter("sigla");
        String mensagem = null;
        
        UnidadeMedida oUnidadeMedida = new UnidadeMedida();
        oUnidadeMedida.setIdUnidadeMedida(idUnidadeMedida);
        oUnidadeMedida.setDescricao(descricao);
        oUnidadeMedida.setSigla(sigla);
        
        try {
            GenericDAO dao = new UnidadeMedidaDAO();
            if(dao.cadastrar(oUnidadeMedida)){
                mensagem = "UnidadeMedida cadastrada com sucesso! ";
            }else{
                mensagem = "Problemas ao cadastrar UnidadeMedida";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("UnidadeMedidaListar");
        } catch (Exception e) {
            System.out.println("Problemas no servelet ao cadastrar UnidadeMedida! Erro: "+ e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
