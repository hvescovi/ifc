package exemplo4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataSetReader {

    double[] data;

    public double[] readFile(String nomeArq) 
            throws FileNotFoundException, BadDataException {
        Scanner arq;
        arq = new Scanner(new File(nomeArq));
        readData(arq);
        return data;
    }

    public void readData(Scanner in) throws BadDataException {
        
        if (!in.hasNextInt()) {
            throw new BadDataException("erro, nao encontrei o numero de valores a serem lidos");
        }
        int numberOfValues = in.nextInt();
        data = new double[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            readValue(in, i);
        }
        if (in.hasNextDouble()) {
            throw new BadDataException("erro: dados excedentes no arquivo");
        }
    }

    public void readValue(Scanner in, int i) throws BadDataException {
        if (!in.hasNextDouble()) {
            throw new BadDataException("erro: valor esperado e nao encontrado, fim de arquivo inesperado");
        }
        data[i] = in.nextDouble();
    }

}
