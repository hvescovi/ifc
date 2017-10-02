package controle;

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

            try {

                // o que o controlador deve fazer?
                String op = request.getParameter("op");

                LivroDAO ldao = new LivroDAO();

                if (op.equals("APIInsereLivro")) {

                    out.println("ok livro inserido para sua alegria");

                } else if (op.equals("APIBuscaLivroPorTitulo")) {

                    // pegar o parâmetro de busca (livro que eu quero encontrar)
                    String procurado = request.getParameter("procura");

                    // pedir ao DAO os livros que contenham esse título buscado
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
                } else if (op.equals("login")) {

                    // pegar os valores dos campos
                    String login = request.getParameter("login");
                    String senha = request.getParameter("senha");

                    // verificar se login e senha estao corretos;
                    // se estiverem corretos...
                    if (login.equals("admin") && (senha.equals("123"))) {

                        // coloca a variavel adminLogado=true na sessao
                        HttpSession ses = request.getSession(true);
                        ses.setAttribute("adminLogado", true);

                        // boas vindas ao admin! e link pra continuar
                        out.print("Vem admin!!! Parabéns!!! "
                                + "<a href=principal.jsp>Continuar</a>");

                    } else {
                        // se nao estiver correto o login...

                        // responder ao usuario que o login estah incorreto!!!
                        out.print("Você ERRROU o login ou a senha!!! Bah!!! "
                                + "Tente outra vez. <a href=principal.jsp>Continuar</a>");
                    }
                } else if (op.equals("vazaAdmin")) {

                    // coloca na sessao que adminLogado=false
                    HttpSession ses = request.getSession(true);
                    ses.setAttribute("adminLogado", false);

                    // encaminha a requisição de volta pra pagina principal
                    RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
                    rd.forward(request, response);

                } else if (op.equals("abrirFormEditarLivro")) {

                    //2.05 buscar o id (TITULO!!!! mentira, não passei ID) do livro a ser editado
                    String procurado = request.getParameter("titulo");

                    //2.1 solicita ao LivroDAO o Livro a ser editado
                    Livro livro = ldao.buscaLivroPorTitulo(procurado);

                    //2.2 armazena o livro a ser editado na sessão
                    HttpSession ses = request.getSession(true);
                    ses.setAttribute("mickey", livro);

                    //2.3 repassa o fluxo de execução para a página "formEditarLivro.jsp"
                    RequestDispatcher rd = request.getRequestDispatcher("formEditarLivro.jsp");
                    rd.forward(request, response);

                } else if (op.equals("alteraDadosLivro")) {

                    //4.1 obtém os novos dados do livro (que foram alterados, ou não)
                    String titulo = request.getParameter("titulo");
                    String autores = request.getParameter("autores");
                    String ano = request.getParameter("ano");
                    String idlivro = request.getParameter("idlivro");

                    //4.2 cria um livro com os dados novos
                    Livro livro = new Livro(Integer.parseInt(idlivro), titulo, autores, ano);

                    //4.3 solicita ao DAO que altere os dados do livro (será usado o id do livro, pelo DAO, para localizar o livro)
                    ldao.atualizaLivro(livro);

                    //4.4 mostra uma mensagem de que o livro foi alterado, e um link para continuar
                    out.print("livro alterado. <a href=principal.jsp>continuar</a>");
                } else if (op.equals("listaLivros")) {

                    ArrayList<Livro> livros2 = ldao.carregaLivros();
                    request.setAttribute("lista", livros2);
                    RequestDispatcher rd = request.getRequestDispatcher("lista.jsp");
                    rd.forward(request, response);
                } else if (op.equals("APIexclui")) {
                    int idLivro = Integer.valueOf(request.getParameter("idLivro"));
                    ldao.removeLivro(new Livro(idLivro, "", "", ""));
                    out.println("Livro " + idLivro + " removido.");
                } else {
                    out.println("Controlador: ação não reconhecida: " + op);
                }
            } catch (Exception ex) {
                out.println("Erro no controlador: " + ex.getMessage());
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
