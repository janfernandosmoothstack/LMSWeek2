package com.lms.POJO;

public class Book {
	private String bookId;
	private String title;
	Author author = new Author();
	Publisher publisher = new Publisher();
	
	public Book() {
		
	}
	
	public Book(String bookId, String title, Author author, Publisher publisher) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
