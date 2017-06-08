package dao.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import modelo.Livro;

public class LivroDAO {

    String nomeArq = System.getProperty("user.home")
            + System.getProperty("file.separator") + "livros.xml";

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
        return retorno;
    }

    public void salvaLivros(ArrayList<Livro> livros) {
        try {
            FileOutputStream fos = new FileOutputStream(nomeArq);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmlenc = new XMLEncoder(bos);
            xmlenc.writeObject(livros);
            xmlenc.close();
        } catch (Exception ex) {
            System.out.println("ERRO ao gravar livros: " + ex.getMessage());
        }
    }

    public ArrayList<Livro> buscaLivrosPorTitulo(String procurado) {
        ArrayList<Livro> retorno = new ArrayList();
        if (!procurado.equals("")) {
            ArrayList<Livro> livros = carregaLivros();
            for (Livro livro : livros) {
                if (livro.getTitulo().contains(procurado)) {
                    retorno.add(livro);
                }
            }
        }
        return retorno;
    }
    
    public Livro buscaLivroPorTitulo(String procurado) {
           ArrayList<Livro> livros = carregaLivros();
            for (Livro livro : livros) {
                if (livro.getTitulo().equals(procurado)) {
                    return livro;
                }
            }
        
        return null;
    }
    
    public void atualizaLivro(Livro novo) {
           ArrayList<Livro> livros = carregaLivros();
           Livro velho = null;
            for (Livro livro : livros) {
                if (livro.getIdLivro() == novo.getIdLivro()) {
                    velho = livro;
                    break;
                }
            }
            // achou o livro que vai alterar?
            if (velho != null) {
                // apago o velho
                livros.remove(velho);
                // incluo o novo
                livros.add(novo);
            }
            //atualizar os livros na mídia
            salvaLivros(livros);
    }

}
