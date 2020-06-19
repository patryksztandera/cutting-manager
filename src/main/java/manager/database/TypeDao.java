package manager.database;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manager.models.TypeModel;
import manager.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TypeDao {

    private Dao dao = new Dao();

    private ObservableList<TypeModel> typeModelObservableList = FXCollections.observableArrayList();
    private ObjectProperty<TypeModel> typeModelObjectProperty = new SimpleObjectProperty<>(new TypeModel());
    private ObjectProperty<TypeModel> editableObjectProperty = new SimpleObjectProperty<>(new TypeModel());

    public TypeModel typeModel = new TypeModel();

    public void insert() {
        dao.dataManipulation("INSERT INTO type VALUES (NULL,'" +
                this.typeModelObjectProperty.get().typeProperty().getValue() + "','" +
                this.typeModelObjectProperty.get().infoProperty().getValue() + "');");
    }

    public void delete() throws ClassNotFoundException {
        dao.dataManipulation("DELETE FROM type WHERE id_type='" +
                this.editableObjectProperty.get().idTypeProperty().getValue() + "';");
    }

    public void selectAll() {

        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type");

            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {
                TypeModel typeModel = new TypeModel();
                typeModel.setIdType(rs.getInt("id_type"));
                typeModel.setType(rs.getString("type"));
                typeModel.setInfo(rs.getString("information"));
                this.typeModelObservableList.add(typeModel);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectType(int id) {

        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type WHERE id_type='"+id+"';");


            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {

                typeModel.setIdType(rs.getInt("id_type"));
                typeModel.setType(rs.getString("type"));
                typeModel.setInfo(rs.getString("information"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TypeModel> getTypeModelObservableList() {
        return typeModelObservableList;
    }

    public void setTypeModelObservableList(ObservableList<TypeModel> typeModelObservableList) {
        this.typeModelObservableList = typeModelObservableList;
    }

    public TypeModel getTypeModelObjectProperty() {
        return typeModelObjectProperty.get();
    }

    public ObjectProperty<TypeModel> typeModelProperty() {
        return typeModelObjectProperty;
    }

    public void setTypeModelObjectProperty(TypeModel typeModelObjectProperty) {
        this.typeModelObjectProperty.set(typeModelObjectProperty);
    }

    public TypeModel getEditableObjectProperty() {
        return editableObjectProperty.get();
    }

    public ObjectProperty<TypeModel> editableObjectPropertyProperty() {
        return editableObjectProperty;
    }

    public void setEditableObjectProperty(TypeModel editableObjectProperty) {
        this.editableObjectProperty.set(editableObjectProperty);
    }

}

