package no.shakari.rest.bookrestservice.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import no.shakari.rest.bookrestservice.BookRepository;
import no.shakari.rest.bookrestservice.BookService;
import no.shakari.rest.bookrestservice.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@Path("/books")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@GET
	public List<Book> getBooks() {
		return bookRepository.getBooks();
	}

	@GET
	@Path("/{id}")
	public Book getBook(@PathParam("id") long id) {
		return bookRepository.getBook(id);
	}

	@PUT
	@Path("/update")
	public Book updateBook(@PathVariable("book") Book book) {
		if (existsBook(book)) {
			return bookRepository.updateBook(book);
		}
		return null;
	}

	@POST
	@Path("/add")
	public Book addBook(@PathVariable("book") Book book) {
		if (!existsBook(book)) {
			return bookRepository.addBook(book);
		}

		return null;
	}

	@DELETE
	@Path("/remove/{id}")
	public void removeBook(@PathParam("id") long id) {
		if(existsBook(new Book(id,""))){
			bookRepository.removeBook(id);
		}
	}

	private boolean existsBook(Book book) {
		return book != null && getBook(book.getId()) != null;
	}

}
