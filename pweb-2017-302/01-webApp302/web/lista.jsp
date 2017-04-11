<%-- 
    Document   : lista
    Created on : Apr 5, 2017, 6:44:42 PM
    Author     : friend
--%>

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

            );

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
                </tr>
            </thead>
            <tbody>
                <%
                    HttpSession ses = request.getSession(true);

                    // busca a agenda
                    Agenda ag = (Agenda) ses.getAttribute("agenda");

                    if (ag == null) {
                        ag = new Agenda();
                    }
                    for (Pessoa p : ag.getAgenda()) {
                %>

                <tr>
                    <td class="lalign"><%=p.getNome()%></td>
                    <td><%=p.getTelefone()%></td>
                </tr>



                <%
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
