package pl.spring.demo.desktop.dataProvider.Impl;

import java.util.List;
import java.util.stream.Collectors;
import pl.spring.demo.desktop.App;
import pl.spring.demo.desktop.dataProvider.DataProvider;
import pl.spring.demo.desktop.dataProvider.Clients.AuthorClient;
import pl.spring.demo.desktop.dataProvider.Clients.BookClient;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class DataProviderImpl implements DataProvider {

	  BookClient clientBook = (BookClient) App.getContext().getBean("bookClient");
	  AuthorClient clientAuthor = (AuthorClient) App.getContext().getBean("authorClient");

	@Override
	public  List<BookTo> findAllBooks() {
			return clientBook.list();
	}
	@Override
	public List<AuthorTo> findAllAuthors() {
		return clientAuthor.list();
	}
	@Override
	public AuthorTo saveAuthor(AuthorTo a) {
		return clientAuthor.saveAuthor(a);
	}
	@Override
	public List<AuthorTo> findAuthors(String name, String last) {

		List <AuthorTo> result = clientAuthor.list().stream().filter(p -> //
		((name == null || name.isEmpty()) || (name != null && !name.isEmpty() && p.getFirstName().contains(name))) //
				&& //
				((last == null || last.isEmpty()) || (last != null && !last.isEmpty() && p.getLastName().contains(last))) //
		).collect(Collectors.toList());
		return result;
	}
	@Override
	public List<BookTo> findBooks(String title, String name, String lastName) {
		List <BookTo> result = clientBook.list().stream().filter(p -> //
		((title == null || title.isEmpty()) || (title != null && !title.isEmpty() && p.getTitle().contains(title))) //
				&& //
				((name == null || name.isEmpty()) || (name != null && !name.isEmpty() && p.getAuthors().stream().anyMatch(author->author.getFirstName().contains(name))))
						&& //
						((lastName == null || lastName.isEmpty()) || (lastName != null && !lastName.isEmpty() && p.getAuthors().stream().anyMatch(author->author.getLastName().contains(lastName))))//
		).collect(Collectors.toList());
		return result;
	}
	public BookTo saveBook(BookTo book){
		return clientBook.saveBook(book);
	}
}
