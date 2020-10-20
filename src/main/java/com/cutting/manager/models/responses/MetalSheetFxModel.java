package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.MetalSheetEntity;
import javafx.beans.property.*;

import java.time.format.DateTimeFormatter;

public class MetalSheetFxModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty zonedDateTime = new SimpleStringProperty();
    private final DoubleProperty length = new SimpleDoubleProperty();
    private final DoubleProperty width = new SimpleDoubleProperty();
    private final DoubleProperty thickness = new SimpleDoubleProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty owner = new SimpleStringProperty();

    public MetalSheetFxModel(Double length, Double width, Double thickness, Integer quantity, String type, String location) {
        this.length.setValue(length);
        this.width.setValue(width);
        this.thickness.setValue(thickness);
        this.quantity.setValue(quantity);
        this.type.setValue(type);
        this.location.setValue(location);
    }

    public MetalSheetFxModel() {
    }

    public MetalSheetFxModel(MetalSheetEntity entity) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        this.id.setValue(entity.getId());
        this.zonedDateTime.setValue(entity.getTimestamp().format(formatter));
        this.length.setValue(entity.getLength());
        this.width.setValue(entity.getWidth());
        this.thickness.setValue(entity.getThickness());
        this.quantity.setValue(entity.getQuantity());
        this.type.setValue(entity.getTypeEntity().getType());
        this.location.setValue(entity.getLocationEntity().getLocation());
        this.owner.setValue(entity.getOwner());
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getZonedDateTime() {
        return zonedDateTime.get();
    }

    public StringProperty zonedDateTimeProperty() {
        return zonedDateTime;
    }

    public double getLength() {
        return length.get();
    }

    public DoubleProperty lengthProperty() {
        return length;
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public double getThickness() {
        return thickness.get();
    }

    public DoubleProperty thicknessProperty() {
        return thickness;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }
}
