package com.lukebu.workmt.controller;

import com.lukebu.workmt.datamodel.ToDoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItem> toDoItem;

    @FXML
    private ListView<ToDoItem> toDoItemListView;
    @FXML
    private TextArea toDoItemTextAreaDetails;
    @FXML
    private Label dueDateLabel;

    public void initialize() {
        ToDoItem toDoItemOne = new ToDoItem("Zadanie 1 ", "Opis długi zadania 1 "
                , LocalDateTime.of(2019,Month.MARCH, 6, 12,0));
        ToDoItem toDoItemTwo = new ToDoItem("Zadanie 2 ", "Opis długi zadania 2 "
                ,LocalDateTime.of(2019,Month.MARCH, 9, 12,0));
        ToDoItem toDoItemThree = new ToDoItem("Zadanie 3 ", "Opis długi zadania 3 "
                ,LocalDateTime.of(2019,Month.MARCH, 12, 12,0));
        ToDoItem toDoItemFour = new ToDoItem("Zadanie 4 ", "Opis długi zadania 4 "
                ,LocalDateTime.of(2019,Month.MARCH, 14, 12,0));
        ToDoItem toDoItemFive = new ToDoItem("Zadanie 5 ", "Opis długi zadania 5 "
                ,LocalDateTime.of(2019,Month.MARCH, 17, 12,0));

        toDoItem = new ArrayList<ToDoItem>();
        toDoItem.add(toDoItemOne);
        toDoItem.add(toDoItemTwo);
        toDoItem.add(toDoItemThree);
        toDoItem.add(toDoItemFour);
        toDoItem.add(toDoItemFive);



        toDoItemListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
                if (newValue != null) {
                    ToDoItem item = toDoItemListView.getSelectionModel().getSelectedItem();
                    toDoItemTextAreaDetails.setText(item.getTaskDescription());
                    DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
                    dueDateLabel.setText(df.format(item.getDueDate()));
            }
        };
        });
        toDoItemListView.getItems().setAll(toDoItem);
        toDoItemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoItemListView.getSelectionModel().selectFirst();
    }

    public void handleClickListView() {
        ToDoItem toDoItem = toDoItemListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder();
        sb.append(toDoItem.getTaskDescription());
        //sb.append(toDoItem.getTaskName());
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");

        toDoItemTextAreaDetails.setText(sb.toString());

    }
}
