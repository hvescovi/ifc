package exemplo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TentaLerArquivo {

    public static void main(String[] args) {

        System.out.println("iniciando");

        Scanner arq;
        try {
            arq = new Scanner(new File("/home/friend/valores2.txt"));
            while (arq.hasNextLine()) {
                String linha = arq.nextLine();
                System.out.println(linha);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("arquivo nao encontrado: " + ex.getMessage());
        }
        System.out.println("processamento finalizado");
    }
}
