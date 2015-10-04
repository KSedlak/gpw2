package pl.spring.demo.desktop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import pl.spring.demo.desktop.Model.AuthorModel;
import pl.spring.demo.desktop.dataProvider.DataProvider;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;
import pl.spring.demo.to.AuthorTo;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AuthorSearchPageController {
	@FXML
	Button backButton;
	@FXML
	Button searchButton;

	@FXML
	TableView<AuthorTo> tableAuthor;

	@FXML
	TableColumn<AuthorTo, String> nameColumn;

	@FXML
	TableColumn<AuthorTo, String> lastNameColumn;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	private final AuthorModel model = new AuthorModel();
	@FXML
	TextField name;
	@FXML
	TextField lastName;

	@FXML
	private void initialize() {

		tableAuthor.itemsProperty().bind(model.resultProperty());
		searchButton.disableProperty()
				.bind(Bindings.isEmpty(name.textProperty()).and(Bindings.isEmpty(lastName.textProperty())));

		initializeResultTable();
	}

	private void initializeResultTable() {

		nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
		lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));

		tableAuthor.setPlaceholder(new Label("Brak wynikow wyszukiwania"));

	}

	@FXML
	public void backButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("authorsMenu"));
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {

		Task<Collection<AuthorTo>> backgroundTask = new Task<Collection<AuthorTo>>() {

			@Override
			protected Collection<AuthorTo> call() throws Exception {
				return dataProvider.findAuthors(name.getText(), lastName.getText());
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
				model.setResult(new ArrayList<AuthorTo>(backgroundTask.getValue()));

			}

		});

		new Thread(backgroundTask).start();

	}

}
