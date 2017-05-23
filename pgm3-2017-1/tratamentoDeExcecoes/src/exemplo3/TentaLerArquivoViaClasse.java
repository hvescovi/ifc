package exemplo3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TentaLerArquivoViaClasse {

    public void mostraConteudoDeArquivo(String nomeArq) throws FileNotFoundException {

        System.out.println("iniciando");

        Scanner arq;

        arq = new Scanner(new File(nomeArq));
        while (arq.hasNextLine()) {
            String linha = arq.nextLine();
            System.out.println(linha);
        }

        System.out.println("processamento finalizado");

    }

    public static void main(String[] args) {

        TentaLerArquivoViaClasse tenta = new TentaLerArquivoViaClasse();
        String nomeArquivo = "/home/friend/valores2.txt";
        try { 
            tenta.mostraConteudoDeArquivo(nomeArquivo);
        } catch (FileNotFoundException ex) {
            System.out.println("erro, arquivo inexistente: "+nomeArquivo);
        }

    }

}
