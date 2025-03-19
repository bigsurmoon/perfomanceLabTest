import java.io.*;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {
        Circle circle = readCircleFromFile(args[0]);
        List<Point> points = readPointsFromFile(args[1]);

        for (Point point : points) {
            System.out.println(point.getPosition(circle));
        }
    }

    public static Circle readCircleFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        String[] circleData = reader.readLine().split(" ");
        double x = Double.parseDouble(circleData[0]);
        double y = Double.parseDouble(circleData[1]);
        double radius = Double.parseDouble(reader.readLine());
        reader.close();
        return new Circle(x, y, radius);
    }

    public static List<Point> readPointsFromFile(String filename) throws IOException {
        List<Point> points = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] pointData = line.split(" ");
            double x = Double.parseDouble(pointData[0]);
            double y = Double.parseDouble(pointData[1]);
            points.add(new Point(x, y));
        }
        reader.close();
        return points;
    }

    public static class Point {
        private final double x;
        private final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int getPosition(Circle circle) {
            double distance = Math.sqrt(Math.pow(this.x - circle.getX(), 2) + Math.pow(this.y - circle.getY(), 2));

            if (distance == circle.getRadius()) {
                return 0;
            } else if (distance < circle.getRadius()) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    public static class Circle {
        private final double x;
        private final double y;
        private final double radius;

        public Circle(double x, double y, double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getRadius() {
            return radius;
        }
    }
}

/*
   - Для запуска программы убедитесь, что вы находитесь в папке /src
   - Откройте терминал
   - Скомпилируйте программу командой: javac Task2.java
   - Запустите программу командой: java Task2 circle.txt points.txt
   */