package pl.spring.demo.desktop.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.Model.AuthorModel;
import pl.spring.demo.to.AuthorTo;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAuthorToBookController {
	@FXML
	Button backButton;
	@FXML
	TextField name;

	@FXML
	TextField lastName;

	@FXML
	Button saveButton;

	private final ListProperty<AuthorTo> added = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));
	private final AuthorModel model;

	public AddAuthorToBookController(AuthorModel model) {
		this.model = model;
	}

	@FXML
	private void initialize() {

		saveButton.disableProperty()
				.bind(Bindings.isEmpty(name.textProperty()).and(Bindings.isEmpty(lastName.textProperty())));

		added.bind(model.resultProperty());

	}

	@FXML
	public void backButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void saveButtonAction(ActionEvent event) {
		Stage stage = (Stage) saveButton.getScene().getWindow();
		added.add(new AuthorTo(null, name.getText(), lastName.getText()));
		stage.close();
	}

}
