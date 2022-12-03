package client.view.overview;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class ListViewBtnCustom {

    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button();

        HBoxCell(String labelText, String buttonText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText(buttonText);

            this.getChildren().addAll(label, button);
        }
    }

    public Parent createList(int size, String title) {
        BorderPane layout = new BorderPane();

        List<HBoxCell> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new HBoxCell("Job ID " + i, "See more"));
        }

        ListView<HBoxCell> listView = new ListView<HBoxCell>();
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);

        layout.setTop(new Label(title));
        layout.setCenter(listView);

        return layout;
    }
}
