package manager.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeModel {

    public IntegerProperty idType = new SimpleIntegerProperty();
    public StringProperty type = new SimpleStringProperty();
    public StringProperty info = new SimpleStringProperty();


    public int getIdType() {
        return idType.get();
    }

    public IntegerProperty idTypeProperty() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType.set(idType);
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

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    @Override
    public String toString() {
        return type.getValue() ;
    }
}
