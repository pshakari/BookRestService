package no.shakari.rest.bookrestservice.domain;

import javax.xml.bind.annotation.XmlRootElement;


//@XmlRootElement

public class Book {

	private long id;

	private String name;

	public Book() {
	}

	public Book(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public String toString() {
//		return "Book [id=" + id + ", name=" + name + "]";
//	}
}
