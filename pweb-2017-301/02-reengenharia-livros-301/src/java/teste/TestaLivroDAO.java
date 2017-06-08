package teste;

import dao.xml.LivroDAO;
import java.util.ArrayList;
import modelo.Livro;

public class TestaLivroDAO {
    
    public static void main(String[] args) {

        // cria alguns livros
        Livro l1 = new Livro();
        l1.setIdLivro(1);
        l1.setTitulo("Java");
        l1.setAutores("John");
        l1.setAno("2017");
        
        Livro l2 = new Livro(2, "Python", "Jack", "2018");
        
        // cria uma lista de livros
        ArrayList<Livro> livros = new ArrayList();
        
        // insere os livros na lista de livros
        livros.add(l1);
        livros.add(l2);
        
        // cria um DAO
        LivroDAO ldao = new LivroDAO();
                
        // salva a lista de livros
        ldao.salvaLivros(livros);
        
        // lê a lista de livros
        livros = null;
        livros = ldao.carregaLivros();
        
        // mostra os livros da lista
        for (Livro livro: livros) {
            System.out.println("Titulo: "+livro.getTitulo());
        }
        
        System.out.println("TESTE de inserção do livro");
        
        // inserir um livro
        ldao.insereLivro(new Livro(3, "Pascal", "Nicolas", "2016"));
        
        // busca novamente os livros do disco
        livros = ldao.carregaLivros();
        
        // mostra os livros da lista
        for (Livro livro: livros) {
            System.out.println("Titulo: "+livro.getTitulo());
        }
        
    }
}
