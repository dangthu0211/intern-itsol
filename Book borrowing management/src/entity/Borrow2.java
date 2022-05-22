package entity;

public class Borrow2 {

	/*
	 * phiếu mượn: 1 bạn đọc có thể mượn nhiều sách với số lượng
	 */
	
	private Reader reader;
	private Borrowing[] borrows;
	
	public Reader getReader() {
		return reader;
	}
	
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	public Borrowing[] getBorrowing() {
		return borrows;
	}
	
	public void setBorrowing(Borrowing[] borrows) {
		this.borrows = borrows;
	}
}
