package com.lms.POJO;

import java.util.Date;

public class BookLoans {
	Book book = new Book();
	LibraryBranch branch = new LibraryBranch();
	Borrower borrower = new Borrower();
	private Date dateOut;
	private Date dueDate;
	
	public BookLoans(Book book, LibraryBranch branch, Borrower borrower, Date dateOut, Date dueDate) {
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

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
