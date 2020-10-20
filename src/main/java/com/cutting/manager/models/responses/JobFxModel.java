package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.JobEntity;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JobFxModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty postTime =  new SimpleStringProperty();
    private final StringProperty endTime =  new SimpleStringProperty();
    private final StringProperty fileType = new SimpleStringProperty();
    private final LongProperty metalSheetId = new SimpleLongProperty();
    private byte[] imgByte = new byte[0];

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public JobFxModel(String name, String fileType, Long metalSheetId, byte[] imgByte) {
        this.name.setValue(name);
        this.fileType.setValue(fileType);
        this.metalSheetId.setValue(metalSheetId);
        this.imgByte = imgByte;
    }

    public JobFxModel() {
    }

    public JobFxModel(JobEntity entity) {
        this.id.setValue(entity.getId());
        this.name.setValue(entity.getName());
        this.postTime.setValue(entity.getPostTime().format(formatter));
        this.fileType.setValue(entity.getFileType());
        this.metalSheetId.setValue(entity.getMetalSheetEntity().getId());
        this.imgByte = entity.getImgByte();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPostTime() {
        return postTime.get();
    }

    public StringProperty postTimeProperty() {
        return postTime;
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public String getFileType() {
        return fileType.get();
    }

    public StringProperty fileTypeProperty() {
        return fileType;
    }

    public long getMetalSheetId() {
        return metalSheetId.get();
    }

    public LongProperty metalSheetIdProperty() {
        return metalSheetId;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime.set(endTime.format(formatter));
    }
}
