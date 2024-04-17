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

    // Getters and Setters
    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem != null && !oem.equals("-") ? oem : null;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model != null && !model.equals("-") ? model : null;
    }

    public Integer getLaunchAnnounced() {
        return launchAnnounced;
    }

    public void setLaunchAnnounced(String launchAnnounced) {
        if (launchAnnounced != null && launchAnnounced.matches(".*\\d{4}.*")) {
            this.launchAnnounced = Integer.parseInt(launchAnnounced.replaceAll("\\D+", ""));
        } else {
            this.launchAnnounced = null;
        }
    }

    public String getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(String launchStatus) {
        this.launchStatus = (launchStatus != null && (launchStatus.matches(".*\\d{4}.*") || launchStatus.equals("Discontinued") || launchStatus.equals("Cancelled"))) ? launchStatus : null;
    }

    public String getBodyDimensions() {
        return bodyDimensions;
    }

    public void setBodyDimensions(String bodyDimensions) {
        this.bodyDimensions = bodyDimensions != null && !bodyDimensions.equals("-") ? bodyDimensions : null;
    }

    public Float getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(String bodyWeight) {
        if (bodyWeight != null && bodyWeight.matches("\\d+\\s*g.*")) {
            this.bodyWeight = Float.parseFloat(bodyWeight.substring(0, bodyWeight.indexOf("g")).trim());
        } else {
            this.bodyWeight = null;
        }
    }

    public String getBodySim() {
        return bodySim;
    }

    public void setBodySim(String bodySim) {
        this.bodySim = (bodySim != null && !bodySim.equals("No") && !bodySim.equals("Yes") && !bodySim.equals("-")) ? bodySim : null;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType != null && !displayType.equals("-") ? displayType : null;
    }

    public Float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        if (displaySize != null && displaySize.matches("\\d+(\\.\\d+)?\\s*inches.*")) {
            this.displaySize = Float.parseFloat(displaySize.split(" ")[0]);
        } else {
            this.displaySize = null;
        }
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution != null && !displayResolution.equals("-") ? displayResolution : null;
    }

    public String getFeaturesSensors() {
        return featuresSensors;
    }

    public void setFeaturesSensors(String featuresSensors) {
        this.featuresSensors = featuresSensors != null && !featuresSensors.matches("\\d+") ? featuresSensors : null;
    }

    public String getPlatformOs() {
        return platformOs;
    }

    public void setPlatformOs(String platformOs) {
        if (platformOs != null && !platformOs.equals("-")) {
            this.platformOs = platformOs.contains(",") ? platformOs.substring(0, platformOs.indexOf(",")) : platformOs;
        } else {
            this.platformOs = null;
        }
    }
}
