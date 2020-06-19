package manager.models;

import javafx.beans.property.*;

public class SheetModel {

    public IntegerProperty idSheet = new SimpleIntegerProperty();
    public StringProperty time = new SimpleStringProperty();
    public DoubleProperty length = new SimpleDoubleProperty();
    public DoubleProperty width = new SimpleDoubleProperty();
    public DoubleProperty thickness = new SimpleDoubleProperty();
    public StringProperty type = new SimpleStringProperty();
    public StringProperty location = new SimpleStringProperty();

    public int getIdSheet() {
        return idSheet.get();
    }

    public IntegerProperty idSheetProperty() {
        return idSheet;
    }

    public void setIdSheet(int idSheet) {
        this.idSheet.set(idSheet);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public double getLength() {
        return length.get();
    }

    public DoubleProperty lengthProperty() {
        return length;
    }

    public void setLength(double length) {
        this.length.set(length);
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public double getThickness() {
        return thickness.get();
    }

    public DoubleProperty thicknessProperty() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness.set(thickness);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

}
