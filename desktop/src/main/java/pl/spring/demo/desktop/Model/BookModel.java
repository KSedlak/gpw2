package pl.spring.demo.desktop.Model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleListProperty;

public class BookModel {

    private final StringProperty title;
    private final SetProperty<AuthorTo> authors;
    private final ListProperty<BookTo> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));


    public BookModel() {
    	   this(null);
    }
    public BookModel(String title) {
        this.title= new SimpleStringProperty(title);
        authors=null;

    }


	public StringProperty getTitle() {
		return title;
	}


	public SetProperty<AuthorTo> getAuthors() {
		return authors;
	}
	public ListProperty<BookTo> resultProperty() {
		return result;
	}

	public void setResult(ArrayList<BookTo> value) {
		result.setAll(value);

	}
}