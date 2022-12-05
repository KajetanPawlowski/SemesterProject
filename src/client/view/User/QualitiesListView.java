package client.view.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;


public  class QualitiesListView {
    private static QualitiesListView instance = null;
    private ArrayList<QualityCell> qualityCells = new ArrayList<>();

    private QualitiesListView(){
        instance = this;
    }

    public static QualitiesListView getInstance(){
        if(instance == null){
            return new QualitiesListView();
        }
        return instance;
    }

    public ArrayList<QualityCell> getQualityCellsArrayList(){
        return qualityCells;
    }

    public BorderPane createCheckedList(String title, ArrayList<String> qualities) {
        BorderPane layout = new BorderPane();

        List<QualityCell> list = new ArrayList<>();
        for (int i = 0; i < qualities.size(); i++) {
            QualityCell nextCell = new QualityCell(qualities.get(i));
            getQualityCellsArrayList().add(nextCell);
            list.add(nextCell);
        }

        ListView<QualityCell> listView = new ListView<QualityCell>();
        ObservableList<QualityCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);

       layout.setTop(new Label(title));
       layout.setCenter(listView);

       return layout;
    }
    public ArrayList<String> getPickedQualities(){
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < qualityCells.size(); i++){
            if(qualityCells.get(i).isChecked()){
                result.add(qualityCells.get(i).getLabel());
            }
        }
        return result;
    }
    public void setEditable(boolean isEditable){
        for(int i = 0; i < qualityCells.size(); i++){
            qualityCells.get(i).setEditable(isEditable);
        }

    }

    public class QualityCell extends HBox {

        Label label = new Label();
        CheckBox checkBox = new CheckBox();


        QualityCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            checkBox.setDisable(true);

            getQualityCellsArrayList().add(this);
            this.getChildren().addAll(label, checkBox);
        }
        public boolean isChecked(){
            return checkBox.isSelected();
        }
        public void setEditable(boolean isEditable){
            checkBox.setDisable(isEditable);
        }
        public String getLabel(){
            return label.getText();
        }
    }

}
