package dao.xml;

import java.util.ArrayList;
import modelo.Livro;

public class Inicializa {
    
    public static void main(String[] args) {
        ArrayList<Livro> retorno = new ArrayList();
        LivroDAO ldao = new LivroDAO();
        ldao.salvaLivros(retorno);
        System.out.println("Arquivo de dados XML inicializado.");
    }
    
}
