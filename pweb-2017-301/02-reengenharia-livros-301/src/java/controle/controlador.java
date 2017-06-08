package controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.xml.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

            // obtém a qual a operação a ser executada
            String op = request.getParameter("op");

            if (op.equals("APIBuscaLivrosPorTitulo")) {

                // pegar o que estah sendo procurado
                String procurado = request.getParameter("procurado");

                // pedir ao DAO os livros que contem o 
                // que estah sendo procurado no titulo
                LivroDAO ldao = new LivroDAO();
                ArrayList<Livro> resultados = ldao.buscaLivrosPorTitulo(procurado);

                // converter a lista de livros em uma string
                String nomesLivros = "";
                for (Livro livro : resultados) {
                    if (!nomesLivros.equals("")) {
                        nomesLivros += "|";
                    }
                    nomesLivros += livro.getTitulo();
                }

                // retornar a string 
                out.print(nomesLivros);

            } else if (op.equals("loginAdmin")) {

                // pegar os dados
                String login = request.getParameter("login");
                String senha = request.getParameter("senha");

                // validar os dados: se o login estah certo...
                if (login.equals("admin") && senha.equals("123")) {

                    // botar a variavel administradorLogado na sessao,
                    // com o valor "verdadeiro"
                    HttpSession ses = request.getSession(true);
                    ses.setAttribute("administradorLogado", true);

                    // mostra que o administrador estah logado! show!
                    out.println("Admin, bem vindo! <a href=principal.jsp>Continuar</a>");

                    // se o login estah incorreto...
                } else {

                    // mostra que o login estah incorreto!
                    out.println("Login incorreto!!!!!! <a href=\"principal.jsp\">Continuar</a>");
                }
            } else if (op.equals("logout")) {

                // tira a variavel administradorLogado da sessao
                HttpSession ses = request.getSession(true);
                ses.removeAttribute("administradorLogado");

                // volta pra tela principal
                RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
                rd.forward(request, response);

            } else if (op.equals("abrirFormEditarLivro")) {

                //2.05 buscar o id do livro a ser editado
                String procurar = request.getParameter("titulo");

                // 2.1 solicita ao LivroDAO o Livro a ser editado
                LivroDAO ldao = new LivroDAO();
                Livro paraEditar = ldao.buscaLivroPorTitulo(procurar);

                // 2.2 armazena o livro a ser editado na sessão
                HttpSession ses = request.getSession(true);
                ses.setAttribute("livro", paraEditar);

                // 2.3 repassa o fluxo de execução para a página "formEditarLivro.jsp"
                RequestDispatcher rd = request.getRequestDispatcher("formEditarLivro.jsp");
                rd.forward(request, response);

            } else if (op.equals("alteraDadosLivro")) {

                //4.1 obtém os novos dados do livro (que foram alterados, ou não)
                String idlivro = request.getParameter("idlivro");
                String titulo = request.getParameter("titulo");
                String autores = request.getParameter("autores");
                String ano = request.getParameter("ano");

                //4.2 cria um livro com os dados novos
                Livro novo = new Livro(Integer.parseInt(idlivro), titulo, autores, ano);

                //4.3 solicita ao DAO que altere os dados do livro 
                //(será usado o id do livro, pelo DAO, para localizar o livro)
                LivroDAO ldao = new LivroDAO();
                ldao.atualizaLivro(novo);

                //4.4 mostra uma mensagem de que o livro foi alterado, e um link para continuar
                out.println("livro atualizado. <a href=principal.jsp>continuar</a>");
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
