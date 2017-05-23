package exemplo5.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LivroDAO extends GenericoDAO {

    String nomeArq = "livros.xml";
    
    @Override
    public <T> void salvar(ArrayList<T> lista) 
            throws FileNotFoundException{
        salvarParaArquivo(lista, base+nomeArq);
    }

    @Override
    public <V> ArrayList<V> ler() throws FileNotFoundException {
        return lerDeArquivo(base+nomeArq);
    }
    
}
