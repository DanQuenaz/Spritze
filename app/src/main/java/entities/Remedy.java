package entities;

/**
 * Created by danqu on 12/11/2017.
 */

public class Remedy {
    private String name;
    private String description;
    private String manufacturer;

    public Remedy(){}

    public Remedy(String name, String description, String manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toString(){
        return this.name;
    }
}
