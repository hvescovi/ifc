package testes;

import dao.xml.LivroDAO;
import java.util.ArrayList;
import modelo.Livro;

public class TestaLivroDAO {
    
    public static void main(String[] args) {
        // criar um livroDAO
        LivroDAO ldao = new LivroDAO();
        
        /*
        
        
        // criar uma lista de livros
        ArrayList<Livro> livros = new ArrayList();
        
        // criar livros para colocar na lista
        Livro l1 = new Livro();
        l1.setIdLivro(1);
        l1.setTitulo("Java");
        l1.setAutores("John");
        l1.setAno("1996");
        
        Livro l2 = new Livro(2, "Pyhton", "Jack", "2018");
        Livro l3 = new Livro(3, "Pascal", "Paul", "2015");
        
        // inserir os livros na lista
        livros.add(l1);
        livros.add(l2);
        livros.add(l3);
        
        // testar o metodo de salvar livros
        ldao.salvaLivros(livros);
        */
        
        // testa a leitura dos livros
        ArrayList<Livro> livros2 = ldao.carregaLivros();
        
        // exibe os livros carregados
        for (Livro livro : livros2) {
            System.out.println("Titulo do Livro: "+livro.getTitulo());
        }
        /*
        System.out.println("Testando busca de livros por titulo");
        // exibe livros que tem a palavra "Para"
        livros2 = ldao.buscaLivrosPorTitulo("Py");
        for (Livro livro : livros2) {
            System.out.println("Titulo do Livro: "+livro.getTitulo());
        }

*/
        /*
        
        Livro novo = new Livro();
        novo.setIdLivro(1);
        novo.setTitulo("Java");
        novo.setAutores("John e Jack");
        novo.setAno("2010");
        /*
        System.out.println("Inserindo um livro...");
        ldao.insereLivro(novo);
        System.out.println("Livro inserido com SUCESSO!");
        */
        /*
        novo.setTitulo("Pascal");
        System.out.println("Atualizando o livro...");
        ldao.atualizaLivro(novo);
        System.out.println("Livro atualizado!");
        */
//ldao.atualizaLivro(novo);
        
    }
    
}
