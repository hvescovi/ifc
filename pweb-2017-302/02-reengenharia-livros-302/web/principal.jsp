<%-- 
    Document   : principal
    Created on : May 11, 2017, 10:01:20 AM
    Author     : friend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca do IFC</title>
        <script src="jquery-1.10.2.js"></script>
        <script>

            $(document).ready(
                    function () {

                        $("#linkInserir").click(
                                function () {

                                    //$("#divFormInserir").text("oi");
                                    $("#divFormInserir").hide();
                                    $("#divFormInserir").load("formInsereLivro.html", function () {

                                        $("#botaoInserir").click(
                                                function () {

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


                                                    });



                                                }

                                        );

                                    });

                                    $("#divFormInserir").fadeIn(2800);

                                }

                        );



                        $("#buscaTitulo").keyup(
                                function () {

                                    // pega o valor digitado
                                    var procura = $(this).val();

                                    // pede para o controlador trazer a lista de livros
                                    // que contem o que foi digitado na busca
                                    $.get("controlador?op=APIBuscaLivroPorTitulo&procura=" + procura, function (data, status) {

                                        //alert(" recebeu a resposta do controlador");
                                        
                                        // peeega a resposta
                                        var resp = data;
                                        //alert(resp);

                                        // mostra a resposta (livros)
                                        $("#resultados").text(resp);
                                    });
                                    //$("#divFormInserir").text(procura);
                                }

                        );


                    }

            );


        </script>
    </head>
    <body>
        <h1>Biblioteca do IFC</h1>
        Busca por titulo: <input type="text" id="buscaTitulo">
        <a href="#" id="linkInserir">Inserir livro</a>

        <div id="divFormInserir">
        </div>

        <div id="resultados">
            ola
        </div>

    </body>
</html>
