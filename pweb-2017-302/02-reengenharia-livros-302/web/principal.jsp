<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca do IFC</title>
        <link rel="stylesheet" href="css/estilo.css">
        <script src="jquery-1.10.2.js"></script>
        <script>

            $(document).ready(function () {

                $("#linkInserir").click(function () {

                    //$("#divFormInserir").text("oi");
                    $("#divFormInserir").hide();

                    $("#divFormInserir").load("formInsereLivro.html", function () {
                        $("#botaoInserir").click(function () {

                            //alert("o clique funcionou");

                            // pegar os valores dos campos
                            var titulo = $("#titulo").val();
                            var autores = $("#autores").val();
                            var ano = $("#ano").val();

                            // pedir para o controlador cadastrar o livro
                            $.get("controlador?op=APIInsereLivro&titulo=" + titulo + "&autores=" + autores + "&ano=" + ano, function (data, status) {

                                //alert("estou tratando a resposta do controlador");

                                // capturar a resposta
                                var resposta = data;

                                // coloca a resposta na tela
                                // mostrar a resposta do controlador na div do formulario
                                $("#divFormInserir").text(resposta);

                            }); // fim da chamada get
                        }); // fim do click do botão inserir
                    }); // fim da abertura do form inserir

                    $("#divFormInserir").fadeIn(2800);

                }); // fim do click link inserir

                $("#buscaTitulo").keyup(function () {

                    // pega o valor digitado
                    var procura = $(this).val();

                    // pede para o controlador trazer a lista de livros
                    // que contem o que foi digitado na busca
                    $.get("controlador?op=APIBuscaLivroPorTitulo&procura=" + procura, function (data, status) {

                        //alert(" recebeu a resposta do controlador");

                        // peeega a resposta
                        var resp = data;
                        //alert(resp);

                        var partes = resp.split("|");

                        var imprime = "";

                        for (i = 0; i < partes.length; i++) {
                            imprime += partes[i] + " <a href=controlador?op=abrirFormEditarLivro&titulo=" + partes[i] + ">editar</a><br>";
                        }

                        // mostra a resposta (livros)
                        $("#resultados").html(imprime);
                    }); // fim chamada get à busca de livros
                    //$("#divFormInserir").text(procura);
                }); // fim do keyup na busca por livros
            }); // fim document ready

        </script>
    </head>
    <body>
        <h1>Biblioteca do IFC</h1>
        <a href="controlador?op=listaLivros">Listagem geral</a><hr>
        Busca por título: <input type="text" id="buscaTitulo">
        <a href="#" id="linkInserir">Inserir livro</a> | <a href="01-linhas.jsp">Gráfico</a>

        <div id="divFormInserir">
        </div>

        <div id="resultados">
            ola
        </div>

        <div id="divAdmin">

            <br><br><br><br><br><br><br><br><br><br><br><br>

            <%
                // conectar-se na sessao
                HttpSession ses = request.getSession(true);

                // pegar a variavel adminLogado da sessao
                Boolean adminLogado = (Boolean) ses.getAttribute("adminLogado");

                // o admin está logado?
                if ((adminLogado != null) && (adminLogado)) {
            %>
            
            <!-- se o admin estiver logado exibe esta parte... -->
            Opções do admin: 
            <a href="">Menu</a>
            <a href="controlador?op=vazaAdmin">Logout</a>

            <%
            } else {
            %>

            <!-- se o admin NÃO estiver logado, exibe esta parte --->
            <form action="controlador">
                <input type="hidden" name="op" value="login">
                Login do admin:
                <input type="text" name="login">
                Senha:
                <input type="password" name="senha">
                <input type="submit" value="Login">
            </form>
            
            <%
                } // esta chaves serve apenas pra fechar o "if" em Java
%>

        </div>
    </body>
</html>
