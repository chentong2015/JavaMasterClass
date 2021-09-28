package JavaUserInterfaces.javafx;

public class BaseJavafx {

    // Data Structure 数据结构
    // SortedList 支持排序的List
    // SortedList<T> list = new SortedList<T>(ObservableList<T> source, new Comparator...) 定义Item判断条件
    // FilteredList 筛选的List
    // FilteredList<T> list = new FilteredList<T>(ObservableList<T> source, Predicate<?> predicate) 定义筛选过滤条件

    // 只能在JavaFX Application Thread中操作UI元素SS
    // fx:controller="main.Controller"
    // <Button text="click" onAction="#pressButton" GridPane.rowIndex="0" GridPane.columnIndex="0" />
    // <ListView fx:id="listView" GridPane.rowIndex="1" GridPane.columnIndex="0" />
    // <ProgressBar fx:id="progressBar" GridPane.rowIndex="2" />
    // <Label fx:id="progressLabel" GridPane.rowIndex="3" />

    // import javafx.scene.control.ListView;
    // @FXML
    // private ListView listView;
    // @FXML
    // private ProgressBar progressBar;
    // @FXML
    // public void pressButton() { }

    // String[] name = {}
    // FXCollections.observableArrayList()
    // new task() {
    //    updateMessage("label progress");
    //    updateProgress(2, 6);
    // }

    // 在后台线程结束后，加载数据到UI
    // 1. Platform.runLater(new Runnable() { listView.setItems(); })
    // 2. Data binding 数据绑定: the value property changes the items property will update
    //    listView.itemsProperty().bind(task.valueProperty()); task是后台执行的任务, task实现了Runnable接口 ===> 替换成services
    // 3. listView.progressProperty().bind(task.progressProperty());

    // JavaFX Data Binding
    // SimpleIntegerProperty, SimpleStringProperty
    // Task<Observable<T>> task = new Task<Observable<T>>() {
    // @Override
    // protected Observable<T> call() throws Exception {
    //   return FXCollections.observableArrayList(...);
    // }
}
