import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Task4 {

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        System.out.println(calculateMinimalMoves(filePath));
    }

    public static int calculateMinimalMoves(String filePath) throws IOException {
        List<Integer> values = readValuesFromFile(filePath);
        Collections.sort(values);

        if (values.size() % 2 == 1) {
            int centralValue = values.get(values.size() / 2);
            return computeMoves(values, centralValue);
        } else {
            int centralValue1 = values.get(values.size() / 2);
            int centralValue2 = values.get(values.size() / 2 - 1);
            return Math.min(computeMoves(values, centralValue1), computeMoves(values, centralValue2));
        }
    }

    public static List<Integer> readValuesFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Integer> values = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            for (String part : parts) {
                values.add(Integer.parseInt(part));
            }
        }
        return values;
    }

    public static int computeMoves(List<Integer> values, int targetValue) {
        int totalMoves = 0;
        for (int value : values) {
            totalMoves += Math.abs(targetValue - value);
        }
        return totalMoves;
    }
}

/*
   - Для запуска программы убедитесь, что вы находитесь в папке /src
   - Откройте терминал
   - Скомпилируйте программу командой: javac Task4.java
   - Запустите программу командой: java Task4 nums.txt
   */