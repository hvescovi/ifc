package controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Livro;

/**
 *
 * @author friend
 */
@WebServlet(urlPatterns = {"/controlador"})
public class controlador extends HttpServlet {

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

            // o que o controlador deve fazer?
            String op = request.getParameter("op");

            if (op.equals("APIInsereLivro")) {

                out.println("ok livro inserido para sua alegria");

            } else if (op.equals("APIBuscaLivroPorTitulo")) {

                // pegar o parâmetro de busca (livro que eu quero encontrar)
                String procurado = request.getParameter("procura");
                
                // pedir ao DAO os livros que contenham esse título buscado
                LivroDAO ldao = new LivroDAO();
                ArrayList<Livro> encontrados = ldao.buscaLivrosPorTitulo(procurado);
                
                // preparar uma resposta da lista de livros em formato texto
                String nomesLivros = "";
                for (Livro livro : encontrados) {
                    if (!nomesLivros.equals("")) {
                        nomesLivros += "|";
                    }
                    nomesLivros += livro.getTitulo();
                }
                
                // enviar a resposta
                out.println(nomesLivros);
            }

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
