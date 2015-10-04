package pl.spring.demo.desktop.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.spring.demo.desktop.App;

import pl.spring.demo.desktop.Model.AuthorModel;
import pl.spring.demo.desktop.Model.BookModel;
import pl.spring.demo.desktop.dataProvider.DataProvider;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class AddBookController {
	@FXML
	Button backButton;

	@FXML
	Button saveButton;

	@FXML
	TextField titleField;

	@FXML
	Button addAuthor;

	@FXML
	TableView<AuthorTo> autors;

	@FXML
	TableColumn<AuthorTo, String> name;

	@FXML
	TableColumn<AuthorTo, String> last;

	private final AuthorModel model = new AuthorModel();
	private final BookModel modelBook = new BookModel();
	private final DataProvider dataProvider = DataProvider.INSTANCE;

	@FXML
	private void initialize() {

		saveButton.disableProperty().bind(Bindings.isEmpty(name.textProperty()));

		autors.itemsProperty().bind(model.resultProperty());
		initializeResultTable();
	}

	private void initializeResultTable() {

		name.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
		last.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));

		autors.setPlaceholder(new Label("Musisz dodac jakiegos autora"));

	}

	@FXML
	public void backButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void saveButtonAction(ActionEvent event) {
		BookTo bookToSave = new BookTo();
		bookToSave.setTitle(titleField.getText());
		addAuthorsToBook(bookToSave);
		System.out.println("Dodaje ksiazke");
		Stage stage = (Stage) saveButton.getScene().getWindow();
		Task<BookTo> backgroundTask = new Task<BookTo>() {

			@Override
			protected BookTo call() throws Exception {
				return dataProvider.saveBook(bookToSave);
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
				modelBook.resultProperty().add(backgroundTask.getValue());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sukces");
				alert.setHeaderText("Pomyslnie dodano ksiazke do bazy");
				alert.setContentText("Dadana ksiazka to: " + backgroundTask.getValue().getTitle() + " Autorstwa:"
						+ bookToSave.getAuthorsString());

				alert.showAndWait();

			}

		});

		new Thread(backgroundTask).start();
		stage.close();
	}

	@FXML
	public void addAuthor(ActionEvent event) throws IOException {

		Stage newStage = new Stage();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/" + "addAuthorToBook" + ".fxml"));
		loader.setController(new AddAuthorToBookController(model));
		Scene scene = new Scene(loader.load());
		newStage.setScene(scene);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.setTitle("Dodaj autora do ksiazki");
		newStage.showAndWait();
	}

	public void addAuthorsToBook(BookTo book) {
		Set<AuthorTo> auth = new HashSet<AuthorTo>(model.resultProperty());
		book.setAuthors(auth);
	}

}
