<%-- 
    Document   : formIncluiPessoa
    Created on : Apr 5, 2017, 6:47:58 PM
    Author     : friend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Pessoas: incluir</title>
    </head>
    <body>
        <form action="controlador" method="post">
            <input type="hidden" name="op" value="incluiPessoa">
            CPF: <input type="text" name="cpf"> <br>
            Nome: <input type="text" name="nome"> <br>
            Endereço: <textarea name="endereco"></textarea> <br>
            Telefone: <input type="text" name="telefone"> <br>
            <input type="submit" value="Cadastrar">            
        </form>
    </body>
</html>
