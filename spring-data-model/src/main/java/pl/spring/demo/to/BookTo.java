package pl.spring.demo.to;

import java.util.Set;

public class BookTo {
    private Long id;
    private String title;
    private Set<AuthorTo> authors;

	public BookTo() {
    }


    public BookTo(Long id, String title, Set<AuthorTo> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Set<AuthorTo> getAuthors() {
		return authors;
	}


	public void setAuthors(Set<AuthorTo> authors) {
		this.authors = authors;
	}

	public String getAuthorsString(){
		String returned="";
		for(AuthorTo a:authors){
			returned=returned+a.getFirstName()+" "+a.getLastName()+", ";
		}
return returned.substring(0,returned.length()-2);
	}

}