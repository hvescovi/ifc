package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Livro;

public class LivroDAO {

    public Connection retornaConexaoComBanco() {

        Connection conn = null;
        try {
            // fazer a conexao com o BD

            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "sql10.freemysqlhosting.net";
            String mydatabase = "sql10188319";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String userName = "sql10188319";
            String password = "dJJi7hYlIc";

            conn = DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("erro de classe nao encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("erro ao conectar: " + ex.getMessage());
        }
        return conn;
    }

    public void atualizaLivro(Livro novo) {

        // preparar o comando SQL de alteração de dados 
        String sql = "update Livro set titulo='"
                + novo.getTitulo() + "', autores='"
                + novo.getAutores() + "', ano='"
                + novo.getAno() + "' where idLivro="
                + novo.getIdLivro();

        System.out.println(sql);
        executaComando(sql);
    }

    public void executaComando(String sql) {

        try {
            // fazer a conexao com o BD
            Connection conn = retornaConexaoComBanco();

            // executa o comando SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            // fecha as conexões do BD     
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("erro ao conectar: " + ex.getMessage());
        }
    }

    public void insereLivro(Livro novo) {

        // preparar o comando SQL de inserção de dados 
        String sql = "insert into Livro (titulo, autores, ano) values ('"
                + novo.getTitulo() + "','"
                + novo.getAutores() + "','"
                + novo.getAno() + "')";

        executaComando(sql);
    }

    public ArrayList<Livro> buscaLivrosPorTitulo(String procurado) {
        // cria uma lista de retorno dos livros encontrados
        ArrayList<Livro> retorno = new ArrayList();

        if (!procurado.equals("")) {

            // conecta com o banco de dados
            Connection conn = retornaConexaoComBanco();

            // prepara o SQL de busca
            String sql = "select * from Livro where titulo like '%"
                    + procurado + "%'";

            try {
            
            // executa o SQL de busca
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            // percorre os resultados encontrados
            while (rs.next()) {
            
                // adiciona o livro encontrado na lista de retorno
                Livro encontrado = new Livro();
                encontrado.setIdLivro(rs.getInt("idLivro"));
                encontrado.setTitulo(rs.getString("titulo"));
                encontrado.setAutores(rs.getString("autores"));
                encontrado.setAno(rs.getString("ano"));
                
                retorno.add(encontrado);
            }
            
            } catch (SQLException ex) {
                System.out.println("erro de sql: "+ ex.getMessage());
            }
        }
        return retorno;
    }
    
    public Livro buscaLivroPorTitulo(String procurado) {
        // cria um livro para ser retornado
        Livro retorno = new Livro();

        if (!procurado.equals("")) {

            // conecta com o banco de dados
            Connection conn = retornaConexaoComBanco();

            // prepara o SQL de busca
            String sql = "select * from Livro where titulo = '"
                    + procurado + "'";

            try {
            
            // executa o SQL de busca
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            // percorre os resultados encontrados
            // Nicolas: poderia trocar while por if,
            // jah que espera-se que apenas 1 livro seja encontrado
            while (rs.next()) {
            
                // configura o livro a ser retornado com as informações obtidas
                retorno.setIdLivro(rs.getInt("idLivro"));
                retorno.setTitulo(rs.getString("titulo"));
                retorno.setAutores(rs.getString("autores"));
                retorno.setAno(rs.getString("ano"));
            }
            
            } catch (SQLException ex) {
                System.out.println("erro de sql: "+ ex.getMessage());
            }
        }
        return retorno;
    }

}
