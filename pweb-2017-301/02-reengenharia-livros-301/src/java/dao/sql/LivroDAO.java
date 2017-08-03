package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Livro;

public class LivroDAO {

    public Connection retornaConexaoDeBanco() {

        Connection conn = null;

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "sql10.freemysqlhosting.net";
            String mydatabase = "sql10188319";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String userName = "sql10188319";
            String password = "dJJi7hYlIc";

            conn = DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("erro de classe nao encontrado: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("erro de conexao: " + ex.getMessage());
        }

        return conn;
    }

    public void insereLivro(Livro novo) {

        // conecta no banco de dados
        Connection conn = retornaConexaoDeBanco();

        // prepara o comando SQL
        String sql = "insert into Livro (titulo, autores, ano) values ('"
                + novo.getTitulo() + "','"
                + novo.getAutores() + "','"
                + novo.getAno() + "'); ";

        try {

            // executa o SQL
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.execute();

            // fecha a conexao com o BD
            cmd.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("erro de conexao: " + ex.getMessage());
        }

    }

    public ArrayList<Livro> buscaLivrosPorTitulo(String procurado) {

        // cria a conexao com o BD
        Connection conn = retornaConexaoDeBanco();

        // cria uma lista de livros de retorno, vazia
        ArrayList<Livro> retorno = new ArrayList();

        if (!procurado.equals("")) {

            // cria o SQL de busca
            String sql = "select * from Livro where (titulo like '%"
                    + procurado + "%')";

            try {

                // executa o SQL
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                // percorre os resultados encontrados
                while (rs.next()) {

                    // adiciona cada livro na lista de retorno
                    Livro x = new Livro();
                    x.setIdLivro(rs.getInt("idLivro"));
                    x.setTitulo(rs.getString("titulo"));
                    x.setAutores(rs.getString("autores"));
                    x.setAno(rs.getString("ano"));

                    retorno.add(x);
                }

                // fecha as conexoes com o BD
                st.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("erro de SQL: " + ex.getMessage());
            }
        }
        return retorno;
    }

    public Livro buscaLivroPorTitulo(String procurado) {

        // cria a conexao com o BD
        Connection conn = retornaConexaoDeBanco();

        // cria um livro de retorno, vazio
        Livro retorno = new Livro();

        if (!procurado.equals("")) {

            // cria o SQL de busca
            String sql = "select * from Livro where (titulo = '"
                    + procurado + "')";

            try {

                // executa o SQL
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                // percorre os resultados encontrados (deve ser apenas 1 livro)
                while (rs.next()) {

                    // configura o livro que vai ser retornado
                    retorno.setIdLivro(rs.getInt("idLivro"));
                    retorno.setTitulo(rs.getString("titulo"));
                    retorno.setAutores(rs.getString("autores"));
                    retorno.setAno(rs.getString("ano"));
                }

                // fecha as conexoes com o BD
                st.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("erro de SQL: " + ex.getMessage());
            }
        }
        return retorno;
    }

    public void atualizaLivro(Livro novo) {

        // conecta no banco de dados
        Connection conn = retornaConexaoDeBanco();

        // prepara o comando SQL
        String sql = "update Livro set titulo='"
                + novo.getTitulo() + "', autores='"
                + novo.getAutores() + "', ano='"
                + novo.getAno() + "' where idLivro="
                + novo.getIdLivro();

        try {

            // executa o SQL
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.execute();

            // fecha a conexao com o BD
            cmd.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("erro de conexao: " + ex.getMessage());
        }

    }

}
