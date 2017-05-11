package dao;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import modelo.Livro;

public class LivroDAO {

    String nomeArq = System.getProperty("user.home")+
            System.getProperty("file.separator")+"livros.xml";
    
    public ArrayList<Livro> carregaLivros() {
        ArrayList<Livro> retorno = new ArrayList();
        try {

            FileInputStream fis = new FileInputStream(nomeArq);
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmldec = new XMLDecoder(bis);
            retorno = (ArrayList<Livro>) xmldec.readObject();
            
        } catch (Exception ex) {
            System.out.println("ERRO ao ler livros: " + ex.getMessage());
        }
    }

}
