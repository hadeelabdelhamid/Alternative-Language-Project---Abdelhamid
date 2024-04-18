import java.util.Objects;

public class classCells {
    private String oem;
    private String model;
    private Integer launchAnnounced;
    private String launchStatus;
    private String bodyDimensions;
    private Float bodyWeight;
    private String bodySim;
    private String displayType;
    private Float displaySize;
    private String displayResolution;
    private String featuresSensors;
    private String platformOs;
  
    // Constructor
    public classCells(String oem, String model, String launchAnnounced, String launchStatus,
                String bodyDimensions, String bodyWeight, String bodySim, String displayType,
                String displaySize, String displayResolution, String featuresSensors, String platformOs) {
        setOem(oem);
        setModel(model);
        setLaunchAnnounced(launchAnnounced);
        setLaunchStatus(launchStatus);
        setBodyDimensions(bodyDimensions);
        setBodyWeight(bodyWeight);
        setBodySim(bodySim);
        setDisplayType(displayType);
        setDisplaySize(displaySize);
        setDisplayResolution(displayResolution);
        setFeaturesSensors(featuresSensors);
        setPlatformOs(platformOs);
    }

    public void setBodyWeight(String bodyWeight) {
        if (isValid(bodyWeight)) {
            String weight = bodyWeight.split(" ")[0]; // Assumes format "190 g" and takes "190"
            try {
                this.bodyWeight = Float.parseFloat(weight); // Try to parse the weight
            } catch (NumberFormatException e) {
                this.bodyWeight = null; // If parsing fails, set to null
            }
        } else {
            this.bodyWeight = null; // If input is invalid, set to null
        }
    }
  
    // Utility method to check for null or "-" values
    private boolean isValid(String value) {
        return value != null && !value.trim().equals("") && !value.equals("-");
    }
  
    // Setters with data cleaning
    public void setOem(String oem) {
        this.oem = isValid(oem) ? oem : null;
    }
  
    public void setModel(String model) {
        this.model = isValid(model) ? model : null;
    }
  
    public void setLaunchAnnounced(String launchAnnounced) {
        if (isValid(launchAnnounced)) {
            String numericPart = launchAnnounced.replaceAll("[^\\d]", ""); // Remove non-numeric characters
            try {
                this.launchAnnounced = Integer.parseInt(numericPart);
            } catch (NumberFormatException e) {
                this.launchAnnounced = null; // Handle the exception and set to null
            }
        } else {
            this.launchAnnounced = null; // Set to null if input is not valid
        }
    }

    public void setLaunchStatus(String launchStatus) {
        this.launchStatus = isValid(launchStatus) ? launchStatus : null;
    }
  
    public void setBodyDimensions(String bodyDimensions) {
        this.bodyDimensions = isValid(bodyDimensions) ? bodyDimensions : null;
    }

    public void setBodySim(String bodySim) {
        this.bodySim = isValid(bodySim) ? bodySim : null;
    }
  
    public void setDisplayType(String displayType) {
        this.displayType = isValid(displayType) ? displayType : null;
    }
  
    public void setDisplaySize(String displaySize) {
        if (isValid(displaySize)) {
            try {
                this.displaySize = Float.parseFloat(displaySize.split(" ")[0]);
            } catch (NumberFormatException e) {
                this.displaySize = null;
            }
        } else {
            this.displaySize = null;
        }
    }
  
    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = isValid(displayResolution) ? displayResolution : null;
    }
  
    public void setFeaturesSensors(String featuresSensors) {
        this.featuresSensors = isValid(featuresSensors) ? featuresSensors : null;
    }
  
    public void setPlatformOs(String platformOs) {
        this.platformOs = isValid(platformOs) ? platformOs : null;
    }
  
    // Getters
    public String getOem() {
        return oem;
    }
  
    public String getModel() {
        return model;
    }
  
    public Integer getLaunchAnnounced() {
        return launchAnnounced;
    }
  
    public String getLaunchStatus() {
        return launchStatus;
    }
  
    public String getBodyDimensions() {
        return bodyDimensions;
    }
  
    public Float getBodyWeight() {
        return bodyWeight;
    }
    public String getBodySim() {
        return bodySim;
    }
  
    public String getDisplayType() {
        return displayType;
    }
  
    public Float getDisplaySize() {
        return displaySize;
    }
  
    public String getDisplayResolution() {
        return displayResolution;
    }
  
    public String getFeaturesSensors() {
        return featuresSensors;
    }
  
    public String getPlatformOs() {
        return platformOs;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof classCells)) return false;
        classCells cell = (classCells) o;
        return Objects.equals(oem, cell.oem) &&
               Objects.equals(model, cell.model);
        // Include additional fields in equality check if they are part of uniqueness
    }

    @Override
    public int hashCode() {
        return Objects.hash(oem, model);
        // Include additional fields in hash computation if they are part of uniqueness
    }

    // toString
    @Override
    public String toString() {
        // Add all fields to the toString method for completeness
        return "classCells{" +
               "oem='" + oem + '\'' +
               ", model='" + model + '\'' +
               ", launchAnnounced=" + launchAnnounced +
               ", launchStatus='" + launchStatus + '\'' +
               ", bodyDimensions='" + bodyDimensions + '\'' +
               ", bodyWeight=" + bodyWeight +
               ", bodySim='" + bodySim + '\'' +
               ", displayType='" + displayType + '\'' +
               ", displaySize=" + displaySize +
               ", displayResolution='" + displayResolution + '\'' +
               ", featuresSensors='" + featuresSensors + '\'' +
               ", platformOs='" + platformOs + '\'' +
               '}';
    }
}
