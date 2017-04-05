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
    </head>
    <body>
        <a href="controlador?op=formIncluiPessoa">Incluir uma pessoa</a>
        <hr>
        <%
            HttpSession ses = request.getSession(true);

            // busca a agenda
            Agenda ag = (Agenda) ses.getAttribute("agenda");

            if (ag == null) {
                ag = new Agenda();
            }
            for (Pessoa p : ag.getAgenda()) {
        %>

        <%=p.getNome()%> <br>

        <%
            }
        %>

    </body>
</html>
