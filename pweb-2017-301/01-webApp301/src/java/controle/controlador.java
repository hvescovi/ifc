/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PessoaDAO;
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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //busca a agenda da sessao
            String op = request.getParameter("op");

            if (op.equals("lista")) {

                // conecta a sessao
                HttpSession ses = request.getSession(true);

                // busca a agenda
                Agenda ag = (Agenda) ses.getAttribute("agenda");

                // se nao houver agenda, cria uma
                if (ag == null) {
                    ag = new Agenda();
                    ses.setAttribute("agenda", ag);
                }

                // encaminha a resposta para a pagina de listagem
                rd = request.getRequestDispatcher("lista.jsp");
                rd.forward(request, response);

            } else if (op.equals("formIncluiPessoa")) {

                // encaminha a resposta para o formulario de inclusao de pessoa
                rd = request.getRequestDispatcher("formIncluiPessoa.jsp");
                rd.forward(request, response);

            } else if (op.equals("incluiPessoa")) {

                // connecta a sessao
                HttpSession ses = request.getSession(true);

                // busca a agenda
                Agenda ag = (Agenda) ses.getAttribute("agenda");

                // se nao houver agenda, cria uma
                if (ag == null) {
                    ag = new Agenda();
                }

                // busca os campos da pessoa
                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String telefone = request.getParameter("telefone");
                String cpf = request.getParameter("cpf");

                // algum campo foi deixado em branco?
                if ((nome.equals("")) || (endereco.equals(""))
                        || (telefone.equals(""))) {

                    // cria a mensagem de erro
                    ses.setAttribute("mensagem",
                            "Algum campo não foi preenchido :-(");

                    // encaminha pra tela de erro
                    rd = request.getRequestDispatcher("erro.jsp");
                    rd.forward(request, response);
                } else {

                    // cria uma pessoa
                    Pessoa p = new Pessoa(nome, endereco, telefone);
                    p.setCpf(cpf);

                    // adiciona a pessoa no arquivo XML
                    PessoaDAO pd = new PessoaDAO();
                    boolean foi = pd.inserePessoa(p);

                    if (!foi) {
                        // cria a mensagem de erro
                        ses.setAttribute("mensagem",
                                "ERRO: não foi possível gravar os dados no arquivo XML");

                        // encaminha pra tela de erro
                        rd = request.getRequestDispatcher("erro.jsp");
                        rd.forward(request, response);
                    } else {
                        // adiciona na agenda
                        ag.adicionaPessoa(p);

                        // atualiza a agenda na sessao
                        ses.setAttribute("agenda", ag);

                        // cria a mensagem de sucesso
                        ses.setAttribute("mensagem",
                                "A pessoa foi incluída no cadastro.");

                        // encaminha pra tela de sucesso
                        rd = request.getRequestDispatcher("sucesso.jsp");
                        rd.forward(request, response);
                    }
                }
            } else if (op.equals("exclui")) {

                // connecta a sessao
                HttpSession ses = request.getSession(true);

                // busca a agenda
                Agenda ag = (Agenda) ses.getAttribute("agenda");

                // quem vai ser apagado?
                String cpfApagar = request.getParameter("cpf");

                // remove a pessoa da agenda
                ag.removePessoaPeloCPF(cpfApagar);

                // atualiza a agenda na camada de persistência
                PessoaDAO pdao = new PessoaDAO();
                pdao.salvaListaDePessoas(ag.getAgenda());

                // atualiza a agenda na sessao
                ses.setAttribute("agenda", ag);

                // cria a mensagem de sucesso
                ses.setAttribute("mensagem",
                        "A pessoa foi EXCLUÍDA do cadastro.");

                // encaminha pra tela de sucesso
                rd = request.getRequestDispatcher("sucesso.jsp");
                rd.forward(request, response);

            } else {
                // se op eh desconhecido, mostra informacao sobre o controlador
                out.println("Controlador do sistema de pessoas, versão 1.0");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
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
