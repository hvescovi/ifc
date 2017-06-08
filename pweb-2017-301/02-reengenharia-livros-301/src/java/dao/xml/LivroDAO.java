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

    //data access object
    String nomeArq = System.getProperty("user.home")
            + System.getProperty("file.separator") + "livros.xml";

    public ArrayList<Livro> carregaLivros() {
        // cria uma lista vazia
        ArrayList<Livro> livros = new ArrayList();

        try {
            FileInputStream fis = new FileInputStream(nomeArq);
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmldec = new XMLDecoder(bis);
            livros = (ArrayList<Livro>) xmldec.readObject();
        } catch (Exception ex) {
            System.out.println("ERRO ao ler XML: " + ex.getMessage());
        }
        return livros;
    }

    public void salvaLivros(ArrayList<Livro> livros) {
        try {
            FileOutputStream fos = new FileOutputStream(nomeArq);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmlenc = new XMLEncoder(bos);
            xmlenc.writeObject(livros);
            xmlenc.close();
        } catch (Exception ex) {
            System.out.println("ERRO ao salvar livros: " + ex.getMessage());
        }
    }

    public void insereLivro(Livro novo) {

        // carrega todos os livros
        ArrayList<Livro> livros = carregaLivros();

        // descobre o ultimo ID usado
        Integer maior = 0;
        for (Livro umLivro : livros) {
            if (umLivro.getIdLivro() > maior) {
                maior = umLivro.getIdLivro();
            }
        }

        // configura o novo livro com um novo ID
        novo.setIdLivro(maior + 1);

        // adiciona o novo livro na estrutura
        livros.add(novo);

        // salva todos os livros (atualiza)
        salvaLivros(livros);
    }

    public ArrayList<Livro> buscaLivrosPorTitulo(String procurado) {

        // carregar os livros
        ArrayList<Livro> livros = carregaLivros();

        // cria uma lista de livros de retorno, vazia
        ArrayList<Livro> retorno = new ArrayList();

        if (!procurado.equals("")) {

            // percorrer os livros
            for (Livro livro : livros) {

                // se o livro contiver no titulo o que esta sendo procurado
                if (livro.getTitulo().contains(procurado)) {

                    // adiciona esse livro na lista de retorno
                    retorno.add(livro);
                }
            }
        }

        // retorna a lista de retorno
        return retorno;
    }

    public Livro buscaLivroPorTitulo(String procurado) {

        // carregar os livros
        ArrayList<Livro> livros = carregaLivros();

        // percorrer os livros
        for (Livro livro : livros) {

            // se achou o livro procurado
            if (livro.getTitulo().equals(procurado)) {

                return livro;
            }
        }

        // se nao achou o livro
        return null;
    }

    public void atualizaLivro(Livro novo) {

        // carregar os livros
        ArrayList<Livro> livros = carregaLivros();

        Livro velho = null;

        // percorrer os livros
        for (Livro livro : livros) {

            // se achou o livro procurado
            if (livro.getIdLivro() == novo.getIdLivro()) {

                velho = livro;
                break;
            }
        }

        // achou pra trocar?
        if (velho != null) {
            // tira o velho
            livros.remove(velho);
            // bota o novo
            livros.add(novo);
        }
        
        // atualiza a lista de livros
        salvaLivros(livros);
    }

}
