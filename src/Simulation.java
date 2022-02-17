package src;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Simulation {

    public static ArrayList<Package> simulate(int amountOfPackages) throws IOException {
        ArrayList<Package> generatedPackages = new ArrayList<Package>();
        for (int i = 0; i < amountOfPackages; i++) {
            generatedPackages.add(generatePackage(generateAddress(), generateAddress()));
        }
        return generatedPackages;
    }

    private static Address generateAddress() throws IOException {   
        int zipCodeIndex = (int) (Math.random() * 42741);
        String streetName = getRandomLine("streetNames.txt");
        String strNum = Integer.toString((int) (Math.random() * 998) + 1);
        long zip = getZipOfIndex(zipCodeIndex, loadZipArray());
        String[] locArray = getCityAndState((int) zip);
        return new Address(strNum, streetName, locArray[0], locArray[1], Long.toString(zip));
    }

    private static Package generatePackage(Address a1, Address a2) {
        int[] possibleWeights = {1, 20, 70, 150, 150};
        int[] possibleLengths = {15, 18, 60, 108, 108};
        int[] possibleWidths = {12, 14, 30, 65, 83};
        int[] possibleHeights = {3, 8, 65, 65, 83};
        int randIndex = (int) (Math.random() * 5);
        return new Package(a1, a2, possibleWeights[randIndex], possibleLengths[randIndex], possibleWidths[randIndex], possibleHeights[randIndex]);
    }

    private static String getRandomLine(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/" + fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        Random r = new Random();
        int selectedLine = r.nextInt(lines);
        return Files.readAllLines(Paths.get("data/" + fileName)).get(selectedLine);
    }

    private static String[] getCityAndState(int zipCode) throws IOException{
        String[] returnArray = new String[2];
        JSONArray jsonObject = loadZipArray();
        int lowBound = 0;
        int highBound = 42740;
        int index = (highBound + lowBound) / 2;
        long currentZip = getZipOfIndex(index, jsonObject);
        while (currentZip != zipCode) {
            if (currentZip == zipCode);
            else if (currentZip < zipCode) lowBound = index;
            else highBound = index;
            index = (highBound + lowBound) / 2;
            currentZip = getZipOfIndex(index, jsonObject);
        }
        JSONObject loc = (JSONObject) jsonObject.get(index);
        String city = (String) loc.get("city");
        String state = (String) loc.get("state");
        returnArray[0] = city;
        returnArray[1] = state;
        return returnArray;
    }

    private static long getZipOfIndex(int index, JSONArray jsonArray) {
            JSONObject zipObj = (JSONObject) jsonArray.get(index);
            return (long) zipObj.get("zip_code");
    }

    private static JSONArray loadZipArray() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data/USCities.json"));
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}
