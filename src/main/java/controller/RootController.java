package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.BoardItem;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 控制类，绑定按钮，（调用其他类的方法）进行响应
 */
public class RootController implements Initializable {
    BoardItem curItem;//当前选中的元素


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //新建一个菜单

    }

    @FXML
    protected void action(Event event) {
    }
}

