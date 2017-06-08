<%-- 
    Document   : formEditarLivro
    Created on : Jun 8, 2017, 8:32:27 AM
    Author     : friend
--%>

<%@page import="modelo.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edição de Livro</title>
    </head>
    <body>
        <h1>Edição de livro</h1>
        <form action="controlador">

            <%
                // 3.1 obtém da sessão o livro a ser editado 
                HttpSession ses = request.getSession(true);
                Livro livro = (Livro) ses.getAttribute("livro");

                // 3.2 mostra os campos do livro, preenchendo-os com os valores do livro atual
%>

            Título: <input type="text" name="titulo" value="<%=livro.getTitulo()%>"> <br>
            Autores: <input type="text" name="autores" value="<%=livro.getAutores()%>"> <br>
            Ano: <input type="text" name="ano" value="<%=livro.getAno()%>"> <br>
            <input type="submit" value="Salvar">

            <%
// 3.3 o botão "Salvar" chamará o controlador, informando a operação "alteraDadosLivro" e 
// o código do livro a ter os dados alterados (esse segundo campo é um campo escondido)
%>     
            <input type="hidden" name="op" value="alteraDadosLivro">
            <input type="hidden" name="idlivro" value="<%=livro.getIdLivro()%>">

        </form>
    </body>
</html>
