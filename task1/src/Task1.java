import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива: ");
        int n = scanner.nextInt();
        System.out.println("Введите длину обхода: ");
        int m = scanner.nextInt();

        int[] circArr = new int[n];
        for (int i = 0; i < n; i++) {
            circArr[i] = i + 1;
        }

        int index = 0;
        int pathIndex = 0;

        int[] path = new int[n];

        do {
            path[pathIndex++] = circArr[index];
            index = (index + m - 1) % n;
        } while (index != 0);

        System.out.print("Полученный путь: ");
        for (int i = 0; i < pathIndex; i++) {
            System.out.print(path[i]);
        }
        scanner.close();
    }
}