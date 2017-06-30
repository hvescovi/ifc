<%@page import="modelo.DadoDoGrafico"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>

    <head>
        <title>Teste de gráfico</title>
        <meta charset="utf-8">
    </head>

    <body>

        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>

        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto">

            <script>

                Highcharts.chart('container', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Monthly Average Temperature'
                    },
                    subtitle: {
                        text: 'Source: WorldClimate.com'
                    },
                    xAxis: {
                <%
                    //String categoriasX = "'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'";
                    ArrayList<DadoDoGrafico> colunas = new ArrayList();
                    colunas.add(new DadoDoGrafico("Janeiro/2017", 10, 10));
                    colunas.get(0).getPontos().add((float) 15);
                    colunas.add(new DadoDoGrafico("Fevereiro/2017", 20, 14));
                    colunas.get(1).getPontos().add((float) 12);
                    colunas.add(new DadoDoGrafico("Mar�o/2017", 30, 33));
                    colunas.get(2).getPontos().add((float) 10);
                    colunas.add(new DadoDoGrafico("Abril/2017", 10, 24));
                    colunas.get(3).getPontos().add((float) 5);
                    colunas.add(new DadoDoGrafico("Maio/2017", 5, 11));
                    colunas.get(4).getPontos().add((float) 8);
                    colunas.add(new DadoDoGrafico("Junho/2017", 3, 4));
                    colunas.get(5).getPontos().add((float) 3);
                    colunas.add(new DadoDoGrafico("Julho/2017", new float[]{35, 20, 88}));
                    colunas.add(new DadoDoGrafico("Agosto/2017", new float[]{25, 34, 38}));
                    colunas.add(new DadoDoGrafico("Setembro/2017", new float[]{15, 10, 18}));
                    colunas.add(new DadoDoGrafico("Outubro/2017", new float[]{5, 22, 28}));
                    colunas.add(new DadoDoGrafico("Novembro/2017", new float[]{45, 20, 58}));

                    String categX = "";
                    String eixoY1 = "";
                    String eixoY2 = "";
                    String eixoY3 = "";

                    for (DadoDoGrafico d : colunas) {
                        categX += "'" + d.getTituloX() + "',";
                        eixoY1 += String.valueOf(d.getPontos().get(0)) + ",";
                        eixoY2 += String.valueOf(d.getPontos().get(1)) + ",";
                        eixoY3 += String.valueOf(d.getPontos().get(2)) + ",";
                    }
                    //'01','02','03',

                    //String categoriasX = "'01', '02', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'";
                %>
                        categories: [<%=categX%>]
                    },
                    yAxis: {
                        title: {
                            text: 'Temperature (°C)'
                        }
                    },
                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true
                            },
                            enableMouseTracking: true
                        }

                    },
                    series: [{
                            name: 'Tokyo',
                            data: [<%=eixoY1%>]
                        }, {
                            name: 'London',
                            data: [<%=eixoY2%>]
                        }, {
                            name: 'Brazil',
                            data: [<%=eixoY3%>]
                        }]
                });

            </script>

        </div>

        <pre>
https://www.highcharts.com/
http://jsfiddle.net/gh/get/library/pure/highcharts/highcharts/tree/master/samples/highcharts/demo/line-labels/
        </pre>

    </body>

</html>
