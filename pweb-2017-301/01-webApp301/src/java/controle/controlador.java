/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Agenda;
import modelo.Pessoa;

/**
 *
 * @author friend
 */
@WebServlet(name = "controller", urlPatterns = {"/controlador"})
public class controlador extends HttpServlet {

    private RequestDispatcher rd = null;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //busca a agenda da sessao
            String op = request.getParameter("op");

            if (op.equals("lista")) {
                
                // connecta a sessao
                HttpSession ses = request.getSession(true);
                
                // busca a agenda
                Agenda ag = (Agenda) ses.getAttribute("agenda");
                
                if (ag== null) {
                    ag = new Agenda();
                }
                
                rd = request.getRequestDispatcher("lista.jsp");
                rd.forward(request, response);
                
            } else if (op.equals("formIncluiPessoa")) {
                rd = request.getRequestDispatcher("formIncluiPessoa.jsp");
                rd.forward(request, response);
                
            } else if (op.equals("incluiPessoa")) {
                // connecta a sessao
                HttpSession ses = request.getSession(true);
                // busca a agenda
                Agenda ag = (Agenda) ses.getAttribute("agenda");
                
                if (ag== null) {
                    ag = new Agenda();
                }
                
                // busca os campos da pessoa
                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String telefone = request.getParameter("telefone");
                
                // cria uma pessoa
                Pessoa p = new Pessoa (nome, endereco, telefone);
                
                // adiciona na agenda
                ag.adicionaPessoa(p);
                
                // atualiza a agenda da sessao
                ses.setAttribute("agenda", ag);
                
                // cria a mensagem de sucesso
                ses.setAttribute("mensagem", 
                        "A pessoa foi incluída no cadastro.");
                
                // encaminha pra tela de sucesso
                rd = request.getRequestDispatcher("sucesso.jsp");
                rd.forward(request, response);
            } else {
                out.println("Controlador do sistema de pessoas, versão 1.0");
            }
        }
        catch (Exception ex) {
            System.out.println("Erro: "+ex.getMessage());
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
