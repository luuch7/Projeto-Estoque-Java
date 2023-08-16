/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prova.controller.funcionario;

import br.com.prova.dao.FuncionarioDAO;
import br.com.prova.dao.GenericDAO;
import br.com.prova.model.Funcionario;
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
@WebServlet(name = "FuncionarioCadastrar", urlPatterns = {"/FuncionarioCadastrar"})
public class FuncionarioCadastrar extends HttpServlet {

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
        
        int idFuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
        String nomeFuncionario = request.getParameter("nomeFuncionario"); //se der erro pode ser pela letra maiuscula
        String mensagem = null;
        
        Funcionario oFuncionario = new Funcionario();
        oFuncionario.setIdFuncionario(idFuncionario);
        oFuncionario.setNomeFuncionario(nomeFuncionario);
        
        try {
            GenericDAO dao = new FuncionarioDAO();
            if(dao.cadastrar(oFuncionario)){
                mensagem = "Funcionario cadastrado com sucesso! ";
            }else{
                mensagem = "Problemas ao cadastrar Funcionario. Verifique os dados e tente novamente! ";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("FuncionarioListar");
        } catch (Exception e) {
            System.out.println("Problemas no servelet ao cadastrar Funcionario! Erro: "+ e.getMessage());
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
