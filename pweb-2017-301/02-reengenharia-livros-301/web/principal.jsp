<%-- 
    Document   : principal
    Created on : May 11, 2017, 9:06:30 AM
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

                        $("#botaoInserir").click(
                                function () {

                                    //$("#divFormInserir").text("olah :-)");
                                    $("#divFormInserir").html("<img src=procurando.gif width=80px>");

                                }

                        );


                        // OPERACAO DE BUSCA
                        $("#buscarTitulo").keyup(
                                function () {

                                    // pega o valor digitado
                                    var valor = $(this).val();

                                    // pede ao controlador lista de livros que contém
                                    // o valor digitado no titulo
                                    $.get("controlador?op=APIBuscaLivrosPorTitulo&procurado=" + valor, function (data, status) {

                                        // pegar a resposta
                                        var resposta = data;

                                        // partir a resposta
                                        var partes = resposta.split("|");

                                        resultadoDeBusca = "";

                                        for (i = 0; i < partes.length; i++) {
                                            resultadoDeBusca += partes[i] + "<br>";
                                        }
                                        
                                        // se nao achou livros, mostra John
                                        if (resposta === "") {
                                            $("#divResultados").html("<img src=procurando.gif width=120px>");
                                        } else {
                                            // mostrar a resposta
                                            $("#divResultados").html(resultadoDeBusca);
                                        }


                                    });


                                }

                        );

                    }

            );

        </script>
    </head>
    <body>
        <h1>Biblioteca do IFC</h1>
        Buscar: <input type="text" id="buscarTitulo"> 
        <input type="submit" id="botaoInserir" value="Inserir">

        <div id="divFormInserir">
        </div>

        <div id="divResultados">
        </div>



    </body>
</html>
