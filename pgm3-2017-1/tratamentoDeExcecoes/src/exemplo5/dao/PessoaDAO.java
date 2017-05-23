package exemplo5.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PessoaDAO extends GenericoDAO {

    String nomeArq = "pessoas.xml";
    
    @Override
    public <K> void salvar(ArrayList<K> lista) throws FileNotFoundException {
        salvarParaArquivo(lista, base+nomeArq);
    }

    @Override
    public <V> ArrayList<V> ler() throws FileNotFoundException {
        return lerDeArquivo(base+nomeArq);
    }
    
}
