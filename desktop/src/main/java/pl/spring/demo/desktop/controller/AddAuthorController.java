package pl.spring.demo.desktop.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.dataProvider.DataProvider;
import pl.spring.demo.to.AuthorTo;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAuthorController {
	@FXML
	Button backButton;
	@FXML
	TextField name;

	@FXML
	TextField lastName;

	@FXML
	Button saveButton;

	@FXML
	public void backButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void saveButtonAction(ActionEvent event) {

		AuthorTo author = new AuthorTo(null, name.getText(), lastName.getText());
		Stage stage = (Stage) saveButton.getScene().getWindow();
		Task<AuthorTo> backgroundTask = new Task<AuthorTo>() {

			@Override
			protected AuthorTo call() throws Exception {
				return DataProvider.INSTANCE.saveAuthor(author);
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
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Dodano autora");
				alert.setHeaderText("Pomyslnie dodano autora do bazy");
				alert.setContentText("Dadany autor to: " + backgroundTask.getValue().getFirstName() + " "
						+ backgroundTask.getValue().getLastName());

				alert.showAndWait();

			}

		});

		new Thread(backgroundTask).start();
		stage.close();
	}

}
