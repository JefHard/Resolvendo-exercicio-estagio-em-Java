import java.util.Scanner;

public class StringInvertida {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma string: ");
        String original = scanner.nextLine();

        char[] caracteres = original.toCharArray();
        int inicio = 0;
        int fim = caracteres.length - 1;

        while (inicio < fim) {
            char temp = caracteres[inicio];
            caracteres[inicio] = caracteres[fim];
            caracteres[fim] = temp;
            inicio++;
            fim--;
        }

        String invertida = new String(caracteres);
        System.out.println("String invertida: " + invertida);

        scanner.close();
    }
}