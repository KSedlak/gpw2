package pl.spring.demo.desktop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.spring.demo.desktop.Model.BookModel;
import pl.spring.demo.desktop.dataProvider.DataProvider;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;
import pl.spring.demo.to.BookTo;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

public class BookSearchPageController {
	@FXML
	Button backButton;
	@FXML
	Button searchButton;
	@FXML
	TableView<BookTo> tableBook;
	@FXML
	TextField titleField;
	@FXML
	TextField nameField;
	@FXML
	TextField lastNameField;
	@FXML
	TableColumn<BookTo, String> titleColumn;
	@FXML
	TableColumn<BookTo, String> nameColumn;
	@FXML
	TableColumn<BookTo, String> lastNameColumn;
	@FXML
	Button addAuthor;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	private final BookModel model = new BookModel();

	@FXML
	private void initialize() {

		tableBook.itemsProperty().bind(model.resultProperty());
		searchButton.disableProperty().bind(Bindings.isEmpty(titleField.textProperty())
				.and(Bindings.isEmpty(nameField.textProperty())).and(Bindings.isEmpty(lastNameField.textProperty())));

		initializeResultTable();
	}

	private void initializeResultTable() {

		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));

		nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
				cellData.getValue().getAuthors().iterator().next().getFirstName()));
		lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
				cellData.getValue().getAuthors().iterator().next().getLastName()));
		tableBook.setPlaceholder(new Label("Brak wynikow"));

	}

	@FXML
	public void backButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("booksMenu"));
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {

		Task<List<BookTo>> backgroundTask = new Task<List<BookTo>>() {

			@Override
			protected List<BookTo> call() throws Exception {
				return dataProvider.findBooks(titleField.getText(), nameField.getText(), lastNameField.getText());
			}
		};

		backgroundTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if (newValue == State.SUCCEEDED) {
					succes();
				}
			}

			public void succes() {

				model.setResult(new ArrayList<BookTo>(backgroundTask.getValue()));
			}
		});

		new Thread(backgroundTask).start();

	}

	@FXML
	public void addAuthor(ActionEvent event) throws IOException {
		Stage newStage = new Stage();
		newStage.setScene(SceneMaker.getSceneFromFXML("addBook"));

		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.setTitle("Dodaj ksiazke");
		newStage.showAndWait();
	}
}
