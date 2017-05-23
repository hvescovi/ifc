package exemplo5.dao;

import modelo.Livro;
import exemplo5.Pessoa;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestaDAO {

    public static void main(String[] args) {

        LivroDAO ldao = new LivroDAO();
        PessoaDAO pdao = new PessoaDAO();
        try {
            ArrayList<Livro> livros = ldao.ler();
            for (Livro livro : livros) {
                System.out.println("livro com nome de pessoa: " + livro.getTitulo());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("arquivo nao encontrado: " + ex.getMessage());
        } catch (ClassCastException ex) {
            System.out.println("erro, nao foi possivel converter pessoa em livro");
        }

        System.out.println("fim do programa: pessoas lidas no arraylist de livros. Sera?");

    }

}
