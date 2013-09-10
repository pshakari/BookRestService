package no.shakari.rest.bookrestservice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import no.shakari.rest.bookrestservice.BookRepository;
import no.shakari.rest.bookrestservice.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRespositoryImpl implements BookRepository {

	@Autowired
	private DataSource dataSource;

	private Connection conn = null;

	@Override
	public List<Book> getBooks() {
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select * from BOOK");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("ID");
				String name = rs.getString("NAME");

				bookList.add(new Book(id, name));
			}

			rs.close();
			conn.close();

			return bookList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Book getBook(long id) {

		//Book book = new Book();

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("select * from BOOK WHERE ID = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long bookId = rs.getInt("ID");
				String bookName = rs.getString("NAME");

				return new Book(bookId, bookName);
			}

			rs.close();
			conn.close();

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Book updateBook(Book book) {
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("update BOOK set name=? where id=? ");

			ps.setString(1, book.getName());
			ps.setLong(2, book.getId());

			ps.executeUpdate();

			conn.close();

			return book;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public Book addBook(Book book) {
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("insert into BOOK(id,name) values(?,?) ");
			ps.setLong(1, book.getId());
			ps.setString(2, book.getName());

			ps.executeUpdate();

			conn.close();

			return book;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public void removeBook(long id) {
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("delete from BOOK where id=? ");
			ps.setLong(1, id);

			ps.executeUpdate();			
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
