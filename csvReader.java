import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class csvReader {
    public static void main(String[] args) {
        cellManager cellManager = new cellManager(); // Declare and instantiate the cellManager
        String filePath = "cells.csv";
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length >= 12) { // Ensure there are enough columns
                    classCells cell = new classCells(
                            values[0].trim(), // oem
                            values[1].trim(), // model
                            values[2].trim(), // launch_announced
                            values[3].trim(), // launch_status
                            values[4].trim(), // body_dimensions
                            values[5].trim(), // body_weight
                            values[6].trim(), // body_sim
                            values[7].trim(), // display_type
                            values[8].trim(), // display_size
                            values[9].trim(), // display_resolution
                            values[10].trim(), // features_sensors
                            values[11].trim() // platform_os
                    );
                    cellManager.addCell(cell);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
