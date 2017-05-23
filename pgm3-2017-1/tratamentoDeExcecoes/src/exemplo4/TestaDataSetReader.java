package exemplo4;

import java.io.FileNotFoundException;

public class TestaDataSetReader {

    public static void main(String[] args) {

        DataSetReader dsr = new DataSetReader();
        String nomeArquivo = "/home/friend/valores.txt";
        try {
            double[] dados = dsr.readFile(nomeArquivo);
            double sum = 0;
            try {
                for (double d : dados) {
                    sum += d;
                }
            } catch (NullPointerException ex) {
                System.out.println("erro: dados nulos");
            }
            System.out.println("soma: " + sum);
        } catch (FileNotFoundException ex) {
            System.out.println("arquivo nao encontrado: " + nomeArquivo);
        } catch (BadDataException ex) {
            System.out.println("erro nos dados: "+ex.getMessage());
        }
        System.out.println("fim do programa");
    }

}
