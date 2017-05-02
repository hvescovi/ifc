<%-- 
    Document   : lista
    Created on : Apr 5, 2017, 6:44:42 PM
    Author     : friend
--%>

<%@page import="dao.PessoaDAO"%>
<%@page import="modelo.Pessoa"%>
<%@page import="modelo.Agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Pessoas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">

        <script src="jquery-1.10.2.js">
        </script>

        <script type="text/javascript" 
        src="jquery.tablesorter.js"></script> 

        <script>

            $(document).ready(
                    function () {

                        $(function () {
                            $('#keywords').tablesorter();
                        });
                    }

            $(".acaoExclui").click(function () {
                var clicked = $(this);
                var id = clicked.attr("id");
                var partes = id.split("-");
                var cpf = partes[2];
                $.get("controlador?op=exclui&cpf=" + cpf, function (data, status) {
                    $('#linha-' + cpf).hide();
                });

            }
            ));

        </script>


    </head>
    <body>
        <a href="controlador?op=formIncluiPessoa">Incluir uma pessoa</a>
        <hr>
        <table id="keywords" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th><span>Nome</span></th>
                    <th><span>Telefone</span></th>
                    <th><span>Operações</span></th>
                </tr>
            </thead>
            <tbody>
                <%
                    HttpSession ses = request.getSession(true);

                    // busca a agenda
                    Agenda ag = (Agenda) ses.getAttribute("agenda");

                    if (ag == null) {
                        ag = new Agenda();
                        PessoaDAO pdao = new PessoaDAO();
                        ag.setAgenda(pdao.carregaListaDePessoas());
                        ses.setAttribute("agenda", ag);
                    }
                    for (Pessoa p : ag.getAgenda()) {
                %>

                <tr id="linha-<%=p.getCpf()%>">
                    <td class="lalign"><%=p.getNome()%></td>
                    <td><%=p.getTelefone()%></td>
                    <td>
                        <a href="controlador?op=exclui&cpf=<%=p.getCpf()%>">Exclui</a> 
                        <a href="#" class="acaoExclui" id="link-exclui-<%=p.getCpf()%>">Exclui II jquery</a>
                    </td>
                </tr>



                <%
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
