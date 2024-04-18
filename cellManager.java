import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class cellManager {
    // Use a HashMap with a composite key (OEM + Model)
    private Map<String, classCells> cellMap = new HashMap<>();

    // Adds a cell to the manager with a unique key
    public void addCell(classCells cell) {
        String key = generateKey(cell);
        cellMap.put(key, cell);
    }

    // Deletes a cell from the manager by key
    public void deleteCell(classCells cell) {
        String key = generateKey(cell);
        cellMap.remove(key);
    }

    // Helper method to generate a unique key for each cell
    private String generateKey(classCells cell) {
        return cell.getOem() + " " + cell.getModel();
    }

    // Gets unique values for a specific attribute of cells
    public Set<String> getUniqueValues(Function<classCells, String> extractor) {
        return cellMap.values().stream()
                .map(extractor)
                .collect(Collectors.toSet());
    }

    // Calculates the mean body weight of all cells
    public double meanBodyWeight() {
        return cellMap.values().stream()
                .map(classCells::getBodyWeight)
                .filter(Objects::nonNull)
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0);
    }

    // Finds the most common OEM (mode of OEMs)
    public String modeOEM() {
        return cellMap.values().stream()
                .collect(Collectors.groupingBy(classCells::getOem, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String highestAverageWeightOEM() {
        Map<String, Double> totalWeights = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
    
        for (classCells cell : cellMap.values()) {
            Float weight = cell.getBodyWeight();
            if (weight != null) {
                String oem = cell.getOem();
                totalWeights.put(oem, totalWeights.getOrDefault(oem, 0.0) + weight);
                counts.put(oem, counts.getOrDefault(oem, 0) + 1);
            }
        }
    
        String maxOem = null;
        double maxAverage = 0;
        for (Map.Entry<String, Double> entry : totalWeights.entrySet()) {
            double averageWeight = entry.getValue() / counts.get(entry.getKey());
            if (averageWeight > maxAverage) {
                maxAverage = averageWeight;
                maxOem = entry.getKey();
            }
        }
    
        return maxOem; // This should return the OEM name with the highest average weight
    }

    // Finds phones announced in one year and released in another
    public List<String> phonesWithDifferentAnnounceReleaseYears() {
        return cellMap.values().stream()
                .filter(cell -> {
                    Integer launchYear = cell.getLaunchAnnounced();
                    String launchStatus = cell.getLaunchStatus();
                    return launchYear != null && launchStatus != null
                            && launchStatus.matches(".*\\d{4}.*")
                            && !launchStatus.contains(launchYear.toString());
                })
                .map(cell -> cell.getOem() + " " + cell.getModel())
                .collect(Collectors.toList());
    }

    // Counts phones with only one feature sensor
    public int countPhonesWithSingleFeatureSensor() {
        return (int) cellMap.values().stream()
                .filter(cell -> cell.getFeaturesSensors() != null
                        && cell.getFeaturesSensors().split(",").length == 1)
                .count();
    }

    public int yearWithMostLaunchesPost1999() {
        return cellMap.values().stream()
                .map(classCells::getLaunchAnnounced) // This will extract the launch year
                .filter(Objects::nonNull) // This will filter out null years
                .filter(year -> year > 1999) // This will filter out years that are <= 1999
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Group and count by year
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Find the year with the max count
                .map(Map.Entry::getKey) // Extract the year from the entry
                .orElse(0); // Return 0 if no year is found
    }
}
