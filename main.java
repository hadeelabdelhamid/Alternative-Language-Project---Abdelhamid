import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Set;

public class main {
    public static void main(String[] args) {
        cellManager classCellsManager = new cellManager();
        String filePath = "cells.csv"; // Adjust the path as needed

        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length >= 12) {
                    classCells classCells = new classCells(
                        values[0].trim(),
                        values[1].trim(),
                        values[2].trim(),
                        values[3].trim(),
                        values[4].trim(), // bodyDimensions
                        values[5].trim(), // bodyWeight
                        values[6].trim(), // bodySim
                        values[7].trim(), // displayType
                        values[8].trim(), // displaySize
                        values[9].trim(), // displayResolution
                        values[10].trim(), // featuresSensors
                        values[11].trim()  // platformOs
                    );
                    classCellsManager.addCell(classCells);
                } else {
                    System.out.println("Null " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return; // Exit the program if the file can't be found
        }

        // Perform analysis using classCellsManager methods and display the results
        Set<String> uniqueLaunchStatuses = classCellsManager.getUniqueValues(classCells::getLaunchStatus);
        System.out.println("\n\nUnique Launch Statuses: " + uniqueLaunchStatuses);
        
        System.out.println("\n");

        double meanWeight = classCellsManager.meanBodyWeight();
        System.out.printf("\nMean Body Weight: %.2f%n", meanWeight);

        String modeOEM = classCellsManager.modeOEM();
        System.out.println("Most common OEM: " + modeOEM);

        String highestAvgWeightOEM = classCellsManager.highestAverageWeightOEM();
        System.out.println("OEM with the highest average weight: " + highestAvgWeightOEM);

        List<String> phonesDifferentYears = classCellsManager.phonesWithDifferentAnnounceReleaseYears();
        System.out.println("Phones announced and released in different years: " + phonesDifferentYears);

        int countSingleSensorPhones = classCellsManager.countPhonesWithSingleFeatureSensor();
        System.out.println("Number of phones with only one feature sensor: " + countSingleSensorPhones);

        int mostLaunchesYear = classCellsManager.yearWithMostLaunchesPost1999();
        System.out.println("Year with the most phone launches after 1999: " + mostLaunchesYear);
    }
    
}
