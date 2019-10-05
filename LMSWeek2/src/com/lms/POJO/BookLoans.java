package com.lms.POJO;

import java.time.LocalDateTime;

public class BookLoans {
	Book book = new Book();
	LibraryBranch branch = new LibraryBranch();
	Borrower borrower = new Borrower();
	private LocalDateTime dateOut;
	private LocalDateTime dueDate;
	
	public BookLoans() {
		
	}
	
	public BookLoans(Book book, LibraryBranch branch, Borrower borrower, LocalDateTime dateOut, LocalDateTime dueDate) {
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
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

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public LocalDateTime getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
}
