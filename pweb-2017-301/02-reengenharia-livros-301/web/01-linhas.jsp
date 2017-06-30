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
                type: 'line'
                },
                        title: {
                        text: 'Monthly Average Temperature'
                        },
                        subtitle: {
                        text: 'Source: WorldClimate.com'
                        },
                <%

                    ArrayList<DadoDoGrafico> categs = new ArrayList();
                    categs.add(new DadoDoGrafico("Janeiro"));
                    categs.get(0).getPontosY().add((float) 10);
                    categs.get(0).getPontosY().add((float) 20);
                    categs.get(0).getPontosY().add((float) 30);

                    categs.add(new DadoDoGrafico("Fevereiro"));
                    categs.get(1).getPontosY().add((float) 10);
                    categs.get(1).getPontosY().add((float) 1);
                    categs.get(1).getPontosY().add((float) 40);

                    categs.add(new DadoDoGrafico("Mar�o"));
                    categs.get(2).getPontosY().add((float) 10);
                    categs.get(2).getPontosY().add((float) 15);
                    categs.get(2).getPontosY().add((float) 30);

                    categs.add(new DadoDoGrafico("Abril"));
                    categs.get(3).getPontosY().add((float) 10);
                    categs.get(3).getPontosY().add((float) 11);
                    categs.get(3).getPontosY().add((float) 22);

                    categs.add(new DadoDoGrafico("Maio"));
                    categs.get(4).getPontosY().add((float) 7);
                    categs.get(4).getPontosY().add((float) 20);
                    categs.get(4).getPontosY().add((float) 9);

                    categs.add(new DadoDoGrafico("Junho"));
                    categs.get(5).getPontosY().add((float) 1);
                    categs.get(5).getPontosY().add((float) 40);
                    categs.get(5).getPontosY().add((float) 3);

                    categs.add(new DadoDoGrafico("Julho"));
                    categs.get(6).getPontosY().add((float) 1);
                    categs.get(6).getPontosY().add((float) 2);
                    categs.get(6).getPontosY().add((float) 38);

                    categs.add(new DadoDoGrafico("Agosto"));
                    categs.get(7).getPontosY().add((float) 50);
                    categs.get(7).getPontosY().add((float) 2);
                    categs.get(7).getPontosY().add((float) 10);

                    String categoriaX = "";
                    String valores1 = "";
                    String valores2 = "";
                    String valores3 = "";

                    for (DadoDoGrafico d : categs) {
                        categoriaX += "'" + d.getValorX() + "',";
                        valores1 += d.getPontosY().get(0) + ",";
                        valores2 += d.getPontosY().get(1) + ",";
                        valores3 += d.getPontosY().get(2) + ",";
                    }

                    //String categoriaX = "'01', '02', '03', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'";

                %>

                xAxis: {
                categories: [<%=categoriaX%>]
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
                                data: [<%=valores1%>]
                        }, {
                        name: 'London',
                                data: [<%=valores2%>]
                        }, {
                        name: 'Brazil',
                                data: [<%=valores3%>]
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
