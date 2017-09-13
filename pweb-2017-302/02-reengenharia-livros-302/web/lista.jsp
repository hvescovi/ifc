<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Livros</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">

        <script src="jquery-1.10.2.js">
        </script>

        <script type="text/javascript" 
        src="jquery.tablesorter.js"></script> 

        <script>

            $(document).ready(function () {

                $('#keywords').tablesorter();
                
                $(".acaoExclui").click(function () {
                    var clicked = $(this);
                    var id = clicked.attr("id");
                    var partes = id.split("-");
                    var cpf = partes[2];
                    $.get("controlador?op=exclui&cpf=" + cpf, function (data, status) {
                        $('#linha-' + cpf).hide();
                    });
                }); // fim acao exclui
            }); // fim document ready

        </script>


    </head>
    <body>
        <a href="controlador?op=formIncluiPessoa">Incluir uma pessoa</a>
        <hr>
        <table id="keywords" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th><span>Título</span></th>
                    <th><span>Autores</span></th>
                    <th><span>Ano</span></th>
                </tr>
            </thead>
            <tbody>
                <%
                    // obtem a lista de livros do request
                    // essa lista foi inserida no request pelo controlador
                    
                    ArrayList<Livro> livros = (ArrayList<Livro>) request.getAttribute("lista");
                    for (Livro livro : livros) {
                %>

                <tr id="linha-<%=livro.getIdLivro()%>">
                    <td class="lalign"><%=livro.getTitulo()%></td>
                    <td><%=livro.getAutores()%></td>
                    <td>
                        <a href="controlador?op=exclui&cpf=<%=livro.getIdLivro()%>">Exclui</a> 
                        <a href="#" class="acaoExclui" id="link-exclui-<%=livro.getIdLivro()%>">Exclui II jquery</a>
                    </td>
                </tr>

                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
