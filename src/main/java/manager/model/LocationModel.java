package manager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocationModel {

    public IntegerProperty idLocation = new SimpleIntegerProperty();
    public StringProperty location = new SimpleStringProperty();

    public int getIdLocation() {
        return idLocation.get();
    }

    public void setIdLocation(int idLocation) {
        this.idLocation.set(idLocation);
    }

    public IntegerProperty idLocationProperty() {
        return idLocation;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }
}
