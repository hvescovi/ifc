<%-- 
    Document   : sucesso
    Created on : Apr 5, 2017, 6:50:00 PM
    Author     : friend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Problema na execução da operação</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(true);
            String msg = (String) ses.getAttribute("mensagem");
        %>
        
        <h1>Erro</h1>
        <b><%=msg%></b>
        
        <br>
        <a href="controlador?op=lista">Continuar</a>
        
    </body>
</html>
