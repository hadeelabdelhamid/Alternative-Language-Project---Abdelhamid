import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CellManager {
    private List<Cell> cells = new ArrayList<>();

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void deleteCell(Cell cell) {
        cells.remove(cell);
    }

    public Set<String> getUniqueValues(Function<Cell, String> extractor) {
        Set<String> uniqueValues = new HashSet<>();
        for (Cell cell : cells) {
            uniqueValues.add(extractor.apply(cell));
        }
        return uniqueValues;
    }

    public double meanBodyWeight() {
        double sum = 0;
        int count = 0;
        for (Cell cell : cells) {
            if (cell.getBodyWeight() != null) {
                sum += cell.getBodyWeight();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    public String modeOEM() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Cell cell : cells) {
            String oem = cell.getOem();
            countMap.put(oem, countMap.getOrDefault(oem, 0) + 1);
        }
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Task 1: Highest average weight of the phone body
    public String highestAverageBodyWeight() {
        Map<String, Double> averageWeightMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (Cell cell : cells) {
            String oem = cell.getOem();
            Float bodyWeight = cell.getBodyWeight();

            if (oem != null && bodyWeight != null) {
                averageWeightMap.put(oem, averageWeightMap.getOrDefault(oem, 0.0) + bodyWeight);
                countMap.put(oem, countMap.getOrDefault(oem, 0) + 1);
            }
        }

        Map.Entry<String, Double> maxEntry = null;
        for (Map.Entry<String, Double> entry : averageWeightMap.entrySet()) {
            if (maxEntry == null || entry.getValue() / countMap.get(entry.getKey()) > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry != null ? maxEntry.getKey() : null;
    }

    // Task 2: Phones announced in one year and released in another
    public List<Cell> phonesAnnouncedReleasedDifferentYears() {
        List<Cell> result = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getLaunchAnnounced() != null && cell.getLaunchStatus() != null &&
                    !cell.getLaunchAnnounced().toString().equals(cell.getLaunchStatus())) {
                result.add(cell);
            }
        }
        return result;
    }

    // Task 3: Phones with only one feature sensor
    public int phonesWithOneFeatureSensor() {
        int count = 0;
        for (Cell cell : cells) {
            if (cell.getFeaturesSensors() != null && cell.getFeaturesSensors().split(",").length == 1) {
                count++;
            }
        }
        return count;
    }

    // Task 4: Year with the most phones launched after 1999
    public int yearWithMostPhonesLaunchedAfter1999() {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        int year = 0;

        for (Cell cell : cells) {
            if (cell.getLaunchAnnounced() != null && cell.getLaunchAnnounced() > 1999) {
                int launchYear = cell.getLaunchAnnounced();
                countMap.put(launchYear, countMap.getOrDefault(launchYear, 0) + 1);
                if (countMap.get(launchYear) > maxCount) {
                    maxCount = countMap.get(launchYear);
                    year = launchYear;
                }
            }
        }

        return year;
    }

    // Method to read cells from CSV file and populate the list
    public void readCellsFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    Cell cell = new Cell(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                            parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(),
                            parts[8].trim(), parts[9].trim(), parts[10].trim(), parts[11].trim()
                    );
                    cells.add(cell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}