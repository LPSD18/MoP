package tp1.pack1Revisoes;

public class P03WorkWithStrings {

    /**
     * Main, método de arranque da execução
     */
    public static void main(String[] args) {
        test_compareStrings("Bom", "Dia"); // Result = -1
        test_compareStrings("Boa", "Bom"); // Result = -3
        test_compareStrings("Bom", "Bo"); // Result = -3
        test_compareStrings(null, null); // result = 0
        test_compareStrings(null, ""); // result = -1
        test_compareStrings("", null); // result = 1
        test_compareStrings("a", ""); // result = 1
        test_compareStrings("", "a"); // result = -1
        test_compareStrings("a", "a"); // result = 0
        test_compareStrings("b", "a"); // result = 1
        test_compareStrings("a", "b"); // result = -1
        test_compareStrings("aa", "a"); // result = 2
        test_compareStrings("a", "aa"); // result = -2
        test_compareStrings("aa", "aa"); // result = 0
        test_compareStrings("ab", "aa"); // result = 2
        test_compareStrings("ab", "ab"); // result = 0
        test_compareStrings("abc", "abc"); // result = 0
        test_compareStrings("abc", "abd"); // result = -3

    }

    /**
     * Este método recebe duas Strings s1 e s2 e procede à sua comparação,
     * devolvendo um valor positivo se s1 for maior que s2, negativo se ao
     * contrário e 0 se iguais. A comparação deve ser feita primeiro em termos
     * lexicográficos caracter a caracter começando pelos caracteres de menor
     * peso ou em segundo lugar em termos de número de caracteres. Se diferentes
     * deve devolver o índice +1/-1 do caractere que faz a diferença. Ex.
     * s1="Bom", s2="Dia", deve devolver -1; s1="Boa", s2="Bom", deve devolver
     * -3; s1="Bom", s2="Bo", deve devolver 3. Uma String a null é considerada
     * menor que uma string não null.
     *
     * @param s1 string a comparar
     * @param s2 string a comparar
     * @return o resultado da comparação
     */
    private static int compareStrings(String s1, String s2) {
        // Check if any is null
        if (s1 == null && s2 == null)
            return 0;
        // If s2 is a empty string then we return -1, else we return s2 length
        if (s1 == null)
            if (s2.length() == 0)
                return -1;
            else
                return -s2.length();
        // If s1 is a empty string then we return -1, else we return s1 length
        if (s2 == null)
            if (s1.length() == 0)
                return 1;
            else
                return s1.length();

        // Compare Letter by Letter
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {

            if ((int) s1.charAt(i) < (int) s2.charAt(i)) {
                return -(i + 1);
            }
            if ((int) s1.charAt(i) > (int) s2.charAt(i)) {
                return i + 1;
            }
        }

        // Test the length of the strings in second after the for loop
        if (s1.length() < s2.length()) {
            return -s2.length();
        }
        if (s1.length() > s2.length()) {
            return s1.length();
        }

        // If none of the conditions above is true,
        // Then it implies both strings are equal
        return 0;

    }

    /**
     * Auxiliary method that call compareStrings with two strings
     */
    private static void test_compareStrings(String s1, String s2) {
        try {
            System.out.print("compareStrings (" + s1 + ", " + s2 + ") = ");
            int res = compareStrings(s1, s2);
            System.out.println(res);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}