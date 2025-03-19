import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) throws IOException {
        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        updateTestsReport(valuesFilePath, testsFilePath, reportFilePath);
    }

    private static Map<Integer, String> loadValues(String valuesFilePath) throws IOException {
        Map<Integer, String> valuesMap = new HashMap<>();
        String valuesJson = readFile(valuesFilePath);

        JSONObject valuesObject = new JSONObject(valuesJson);
        JSONArray valuesArray = valuesObject.getJSONArray("values");

        for (int i = 0; i < valuesArray.length(); i++) {
            JSONObject valueEntry = valuesArray.getJSONObject(i);
            int id = valueEntry.getInt("id");
            String value = valueEntry.getString("value");
            valuesMap.put(id, value);
        }
        return valuesMap;
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static void updateTestsReport(String valuesFilePath, String testsFilePath, String reportFilePath) throws IOException {
        Map<Integer, String> valuesMap = loadValues(valuesFilePath);

        String testsJson = readFile(testsFilePath);
        JSONObject testsObject = new JSONObject(testsJson);
        JSONArray testsArray = testsObject.getJSONArray("tests");

        updateTestValues(testsArray, valuesMap);

        String updatedReportJson = testsObject.toString(4);
        writeFile(reportFilePath, updatedReportJson);
    }

    private static void updateTestValues(JSONArray testsArray, Map<Integer, String> valuesMap) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject testEntry = testsArray.getJSONObject(i);
            int id = testEntry.getInt("id");

            if (valuesMap.containsKey(id)) {
                testEntry.put("value", valuesMap.get(id));
            }

            if (testEntry.has("values")) {
                JSONArray nestedTests = testEntry.getJSONArray("values");
                updateTestValues(nestedTests, valuesMap);
            }
        }
    }
}

/*   
   - Откройте терминал
   - Скомпилируйте программу командой: javac -cp ".;libraries/json-java.jar" src/Task3.java
   - Запустите программу командой: java -cp ".;libraries/json-java.jar;src" Task3 values.json tests.json report.json 
   */
