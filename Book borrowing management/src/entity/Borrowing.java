package entity;

public class Borrowing {

	/*
	 * chi tiết mượn sách: mỗi 1 sách - số lượng
	 */
	
	private Book book;
	private int quantity;
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	
	@Override
	public String toString() {
        return "BorrowingDetail{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
