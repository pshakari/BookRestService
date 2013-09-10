package no.shakari.rest.bookrestservice;

import java.util.List;

import no.shakari.rest.bookrestservice.domain.Book;

public interface BookService {
	List<Book> getBooks();
	Book getBook(long id);
	Book updateBook(Book book);
	Book addBook(Book book);
	void removeBook(long id);
}
