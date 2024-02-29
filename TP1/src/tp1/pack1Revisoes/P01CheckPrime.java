package tp1.pack1Revisoes;

import java.util.Scanner;

public class P01CheckPrime {

    /**
     * Main, método de arranque da execução
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduza um numero inteiro");
        int num = s.nextInt();
        boolean flag = isPrime(num);
        while (!flag) {
            System.out.println("O numero que introduziu não é primo, introduza um novo número");
            num = s.nextInt();
            flag = isPrime(num);
        }
        s.close();
    }

    /**
     * Método que recebe numero inteiro escolhido pelo utilizador e analisa
     * o numero através de um ciclo while até metade do seu valor para verificar
     * se existe algum numero que o divide, para além do 1 e de si proprio.
     * 
     * @param number
     * @return boolean
     */
    public static boolean isPrime(int number) {
        int i = 2;
        boolean flag = false;
        while (i <= number / 2) {
            if (number % i == 0) {
                flag = true;
                break;
            }
            i++;
        }
        if (!flag) {
            System.out.println("O número " + number + " é um número primo");
            return true;
        } else {
            System.out.println(("O número " + number + " não é um número primo"));
            return false;
        }
    }
}