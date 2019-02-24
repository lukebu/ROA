package com.lukebu.workmt;

import com.lukebu.workmt.toDoItemData.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItem> toDoItem;

    @FXML
    private ListView toDoItemListView;
    @FXML
    private TextArea toDoItemTextAreaDetails;

    public void initialize() {
        ToDoItem toDoItemOne = new ToDoItem("Zadanie 1", "Opis długi zadania 1"
                ,LocalDate.of(2019,Month.MARCH, 22));
        ToDoItem toDoItemTwo = new ToDoItem("Zadanie 2", "Opis długi zadania 2"
                ,LocalDate.of(2019,Month.MARCH, 22));
        ToDoItem toDoItemThree = new ToDoItem("Zadanie 3", "Opis długi zadania 3"
                ,LocalDate.of(2019,Month.MARCH, 22));
        ToDoItem toDoItemFour = new ToDoItem("Zadanie 4", "Opis długi zadania 4"
                ,LocalDate.of(2019,Month.MARCH, 22));
        ToDoItem toDoItemFive = new ToDoItem("Zadanie 5", "Opis długi zadania 5"
                ,LocalDate.of(2019,Month.MARCH, 22));

        toDoItem = new ArrayList<ToDoItem>();
        toDoItem.add(toDoItemOne);
        toDoItem.add(toDoItemTwo);
        toDoItem.add(toDoItemThree);
        toDoItem.add(toDoItemFour);
        toDoItem.add(toDoItemFive);

        toDoItemListView.getItems().setAll(toDoItem);
        toDoItemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void handleClickListView() {
        ToDoItem toDoItem = (ToDoItem)toDoItemListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder(toDoItem.getLongDescription());
        sb.append(toDoItem.getLongDescription());
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(toDoItem.getDueDate().toString());
        toDoItemTextAreaDetails.setText(sb.toString());
    }



}
