package com.lms.POJO;

public class BookCopies {
	Book book = new Book();
	LibraryBranch branch = new LibraryBranch();
	private int noOfCopies;
	
	public BookCopies() {
		
	}
	
	public BookCopies(Book book, LibraryBranch branch, int noOfCopies) {
		this.book = book;
		this.branch = branch;
		this.noOfCopies = noOfCopies;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
