    package sample;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.concurrent.Task;
    import javafx.fxml.FXML;
    import javafx.scene.control.TableView;
    import sample.Model.Artist;
    import sample.Model.DataSource;

    public class Controller {
        @FXML
        private TableView artistTable;

        public void listArtists(){
            Task<ObservableList<Artist>> task = new GetAllArtistsTask();
            artistTable.itemsProperty().bind(task.valueProperty());

            new Thread(task).start();
        }
    }
    class GetAllArtistsTask extends Task{
        @Override
        public ObservableList<Artist> call() {
            return FXCollections.observableArrayList(DataSource.getInstance().queryArtist(DataSource.ORDER_BY_ASC));
        }
    }
