mainimport java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CellManager cellManager = new CellManager();
        String filePath = "cells.csv"; // Assuming the CSV is in the current directory

        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(","); // Assuming there are no commas in the values themselves
                // Check if there are at least 12 columns
                if (values.length >= 12) {
                    // Create the Cell object using the values
                    Cell cell = new Cell(
                        values[0].trim(), // oem
                        values[1].trim(), // model
                        values[2].trim(), // launchAnnounced
                        values[3].trim(), // launchStatus
                        values[4].trim(), // bodyDimensions
                        values[5].trim(), // bodyWeight
                        values[6].trim(), // bodySim
                        values[7].trim(), // displayType
                        values[8].trim(), // displaySize
                        values[9].trim(), // displayResolution
                        values[10].trim(), // featuresSensors
                        values[11].trim()  // platformOs
                    );
                    cellManager.addCell(cell);
                } else {
                    System.out.println("Skipped line due to missing information: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        // Now that cells are loaded, perform the analysis
        System.out.println("OEM with the highest average weight: " + cellManager.highestAverageWeightOEM());

        List<String> differentYearPhones = cellManager.phonesWithDifferentAnnounceReleaseYears();
        System.out.println("Phones announced and released in different years: " + differentYearPhones);

        int singleFeatureSensorsCount = cellManager.countPhonesWithSingleFeatureSensor();
        System.out.println("Number of phones with only one feature sensor: " + singleFeatureSensorsCount);

        int yearWithMostLaunches = cellManager.yearWithMostLaunchesPost1999();
        System.out.println("Year with the most phone launches after 1999: " + yearWithMostLaunches);
    }
}