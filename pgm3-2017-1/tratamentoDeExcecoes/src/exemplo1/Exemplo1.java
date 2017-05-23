package exemplo1;

public class Exemplo1 {

    public static void main(String[] args) {

        int[] x = new int[3];
        x[0] = 10;
        x[1] = 12;
        x[2] = 14;
        for (int i = 0; i < 4; i++) {
            System.out.println("i = " + i);

            try {
                int tmp = x[i];
                float r = tmp / i;
                System.out.println(tmp + "/" + i + "=" + r);
            } catch (ArithmeticException ex) {
                System.out.println("erro aritmetico: " + ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("erro de estouro de indice em vetor: " + ex.getMessage());

            } 
            //catch (Exception ex) {
            //    System.out.println("erro: " + ex.getMessage());
            //}
        }
        System.out.println("fim do programa");
    }

}
