import java.util.Scanner;

import entity.*;

public class MainRun {

	private static Reader[] READERS = new Reader[100];
	private static Book[] BOOKS = new Book[100];
	private static Borrow2[] BORROWS = new Borrow2[100];

	public static void main(String[] args) {
		showMenu();
	}
	
    private static void showMenu() {
        while (true) {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    inputNewReader();
                    break;
                case 2:
                    showAllReader();
                    break;
                case 3:
                    inputNewBook();
                    break;
                case 4:
                    showAllBook();
                    break;
                case 5:
                    createBorrow();
                    break;
                case 6:
                	showAllBorrow();
                    break;
                case 7:
                	showSearchBorrow();
                    break;
                case 8:
                	showSortBorrow();
                    break;
                case 9:
                    System.out.println("Xin cảm ơn đã sử dụng phần mềm của chúng tôi!");
                    return;
            }
        }
    }


	private static int functionChoice() {
		System.out.println("\n\n===== PHẦN MỀM QUẢN LÝ MƯỢN SÁCH THƯ VIỆN =====\n\n");
		System.out.println("1. Nhập danh sách bạn đọc mới.");
		System.out.println("2. In ra danh sách bạn đọc trong hệ thống.");
		System.out.println("3. Nhập danh sách sách mới.");
		System.out.println("4. In ra danh sách sách trong hệ thống.");
		System.out.println("5. Lập phiếu mượn sách cho các bạn đọc.");
		System.out.println("6. In ra danh sách phiếu mượn");
		System.out.println("7. Tìm kiếm danh sách phiếu mượn theo tên bạn đọc.");
		System.out.println("8. Sắp xếp danh sách phiếu mượn theo tên bạn đọc và số lượng sách mượn giảm");
		System.out.println("9. Thoát chương trình");
		System.out.println("--------------------------------------");
		System.out.print("Xin mời nhập lựa chọn của bạn: ");
		int functionChoice = -1;
		do {
			functionChoice = new Scanner(System.in).nextInt();
			if (functionChoice >= 1 && functionChoice <= 9) {
				break;
			}
			System.out.print("Chức năng được chọn không hợp lệ, vui lòng chọn lại: ");
		} while (functionChoice > 9 || functionChoice < 1);
		return functionChoice;
	}

	/*
	 * Nhập hiện Bạn đọc
	 */

	private static void inputNewReader() {
		System.out.print("Nhập số lượng khách hàng mới muốn thêm: ");
		int newReadersNumber = new Scanner(System.in).nextInt();
		for (int i = 0; i < newReadersNumber; i++) {
			System.out.println("Nhập thông tin cho khách hàng thứ " + (i + 1));
			Reader reader = new Reader();
			reader.inputInfo();
			saveReader(reader);
		}
	}

	private static void saveReader(Reader reader) {
		for (int j = 0; j < READERS.length; j++) {
			if (READERS[j] == null) {
				READERS[j] = reader;
				break;
			}
		}
	}

	private static void showAllReader() {
		for (int i = 0; i < READERS.length; i++) {
			if (READERS[i] == null) {
				continue;
			}
			System.out.println(READERS[i]);
		}
	}

	/*
	 * Nhập hiện Sách
	 */

	private static void inputNewBook() {
		System.out.print("Nhập số lượng sách mới muốn thêm: ");
		int newBooksNumber = new Scanner(System.in).nextInt();
		for (int i = 0; i < newBooksNumber; i++) {
			System.out.println("Nhập thông tin cho sách thứ " + (i + 1));
			Book book = new Book();
			book.inputInfo();
			saveBook(book);
		}
	}

	private static void saveBook(Book book) {
		for (int j = 0; j < BOOKS.length; j++) {
			if (BOOKS[j] == null) {
				BOOKS[j] = book;
				break;
			}
		}
	}

	private static void showAllBook() {
		for (int i = 0; i < BOOKS.length; i++) {
			if (BOOKS[i] == null) {
				continue;
			}
			System.out.println(BOOKS[i]);
		}
	}

	/*
	 * Nhập QL Mượn sách
	 */

	// Nhập danh sách bạn đọc với phiếu mượn và chi tiết mượn
	private static void createBorrow() {
		// check dữ liệu đã có hay chưa trong bảng reader và book
		if (!checkData()) {
			System.out.println("Chưa có dữ liệu về bạn đọc hoặc sách để thao tác, vui lòng nhập bạn đọc và sách trước đã!");
			return;
		}
		System.out.print("Nhập số lượng bạn đọc muốn mượn sách: ");
		int readerNumber = new Scanner(System.in).nextInt();
		for (int i = 0; i < readerNumber; i++) {
			// tạo phiếu mượn
			Borrow2 borrow = new Borrow2();
			// điền reader cho phiếu mượn
			Reader reader = pushReader(i);
			borrow.setReader(reader);
			// điền chi tiết mượn cho bạn đọc này
			Borrowing[] borrowDetails = createBorrowingDetail(reader);
			borrow.setBorrowing(borrowDetails);
			// lưu phiếu và danh sách phiếu của thư viện
			saveBorrow(borrow);
			System.out.println("-------------");
		}
	}

	private static boolean checkData() {
		boolean isReaderData = false;
		for (int i = 0; i < READERS.length; i++) {
			if (READERS[i] != null) {
				isReaderData = true;
				break;
			}
		}
		boolean isBookData = false;
		for (int i = 0; i < BOOKS.length; i++) {
			if (BOOKS[i] != null) {
				isBookData = true;
				break;
			}
		}
		return isReaderData && isBookData;
	}

	// Nhập chi tiết mượn của một bạn đọc
	private static Borrowing[] createBorrowingDetail(Reader reader) {

		// số lượng đầu sách < 5
		int bookQuantity;
		do {
			System.out.print("Nhập số lượng sách mà bạn đọc này muốn mượn: ");
			bookQuantity = new Scanner(System.in).nextInt();
			if (bookQuantity < 5 && bookQuantity > 0) {
				break;
			}
			System.out.print("Số lượng đầu sách phải là số dương và không quá 5, vui lòng nhập lại: ");
		} while (true);

		Borrowing[] borrowDetails = new Borrowing[bookQuantity];;
		

		// chi tiết mượn cho từng đầu sách: 1 book - số lượng < 3
		for (int j = 0; j < bookQuantity; j++) {
			Borrowing borrowing = new Borrowing();

			// xác định book theo id và lưu vào chi tiết
			Book book = pushBook(j,borrowDetails); // nhập cho đến khi tồn tại nên luôn khác null nè
			borrowing.setBook(book);

			// xác định số lượng và lưu số lượng
			System.out.print("Nhập số lượng muốn mượn cho đầu sách này: ");
			int quantity;
			do {
				quantity = new Scanner(System.in).nextInt();
				if (quantity > 0 && quantity < 3) {
					break;
				}
				System.out.print("Số lượng muốn mua phải là số dương và không quá 3, vui lòng nhập lại: ");
			} while (true);
			borrowing.setQuantity(quantity);

			// lưu chi tiết vào phiếu mượn của bạn đọc vào danh sách phiếu mượn
			for (int i = 0; i < borrowDetails.length; i++) {
				if (borrowDetails[i] == null) {
					borrowDetails[i] = borrowing;
					break;
				}
			}
		}

		return borrowDetails;
	}

	// Nhập từng bạn đọc vào phiếu mượn theo mã bạn đọc
	private static Reader pushReader(int index) {
		System.out.print("Xin mời nhập mã bạn đọc thứ " + (index + 1) + " muốn mượn sách: ");
		int idReader;
		Reader reader;
		do {
			idReader = new Scanner(System.in).nextInt();

			// xác định reader theo id
			reader = searchReaderById(idReader);
			if (reader != null) {
				break;
			}
			System.out.print("Không tồn tại người dùng có ID là " + idReader + ", vui lòng nhập lại: ");
		} while (true); // nhập cho đến khi có reader tồn tại
		return reader; // trả về reader có dữ liệu
	}

	private static Reader searchReaderById(int idReader) {
		for (int i = 0; i < READERS.length; i++) {
			Reader reader = READERS[i];
			if (reader != null && reader.getId() == idReader) {
				return reader;
			}
		}
		return null;
	}

	// Xác định từng sách đã có trong danh sách Sách, đã có trong chi tiết mượn của bạn đọc đó hay chưa
	private static Book pushBook(int index, Borrowing[] borrowDetails) {
		System.out.print("Xin mời nhập mã sách thứ " + (index + 1) + " mà bạn đọc muốn mượn: ");
		int idBook;
		Book book;
		do {
			idBook = new Scanner(System.in).nextInt();
			book = searchBookById(idBook);
			if (book != null) { //kiểm tra trong DS Sách
				
				//kiểm tra trong chi tiết mượn của bạn đọc
				boolean check = false;

				for(int i=0; i<borrowDetails.length; i++) {
					if(borrowDetails[i] != null) {
						if(book == borrowDetails[i].getBook()) {
							check = false; break;
						}
					} else check = true;
				}
				if(check) break;
				else System.out.print("Bạn đọc nãy đã mượn sách " + idBook + ", vui lòng nhập lại: ");
			}
			else System.out.print("Không tồn tại sách có ID là " + idBook + ", vui lòng nhập lại: ");
						
		} while (true); // nhập cho đến khi có book tồn tại
		return book; // trả về book có dữ liệu
	}

	private static Book searchBookById(int idBook) {
		for (int i = 0; i < BOOKS.length; i++) {
			Book book = BOOKS[i];
			if (book != null && book.getId() == idBook) {
				return book;
			}
		}
		return null;
	}

	// lưu phiếu mượn vào danh sách phiếu mượn
	private static void saveBorrow(Borrow2 borrow) {
		for (int i = 0; i < BORROWS.length; i++) {
			if (BORROWS[i] == null) {
				BORROWS[i] = borrow;
				return;
			}
		}
	}

	private static void showAllBorrow() {
		for (int i = 0; i < BORROWS.length; i++) {
			Borrow2 borrow = BORROWS[i];
			if (borrow != null) {
				System.out.println("Bạn đọc " + borrow.getReader().getName() + " đã mượn các sách sau: ");
				for (int j = 0; j < borrow.getBorrowing().length; j++) {
					System.out.println(borrow.getBorrowing()[j]);
				}
				System.out.println("-------------------------------");
			}
		}

	}

	/*
	 * Lập bảng
	 */
	
	/*
	 * Tìm kiếm và hiển thị danh sách mượn sách theo tên bạn đọc.
	 */
	private static void showSearchBorrow() {
		if(!checkDataBorrows(BORROWS)) {
			System.out.println("Chưa có dữ liệu về danh sách mượn để thao tác, vui lòng nhập danh sách mượn trước đã!");
			return;
		}
		
		//tìm kiếm
		System.out.print("Xin mời nhập tên bạn đọc cần tìm: ");
		String nameReader = new Scanner(System.in).nextLine();

		Borrow2[] borrows = searchBorrowsByReaderName(nameReader);	
		
		if(borrows == null) {
			System.out.println("Bạn đọc " + nameReader + " chưa mượn sách nào");
			return;
		}
		
		//hiện danh sách tìm kiếm
		for (int i = 0; i < borrows.length; i++) {
			Borrow2 borrow = borrows[i];
			if (borrow != null) {
				System.out.println("Bạn đọc " + borrow.getReader().getName() + " đã mượn các sách sau: ");
				for (int j = 0; j < borrow.getBorrowing().length; j++) {
					System.out.println(borrow.getBorrowing()[j]);
				}
				System.out.println("-------------------------------");
			}
		}
	}
	
	private static boolean checkDataBorrows(Borrow2[] borrows) {
		boolean isBorrowsData = false;
		for (int i = 0; i < borrows.length; i++) {
			if (borrows[i] != null) {
				isBorrowsData = true;
				break;
			}
		}
		return isBorrowsData;
	}
	
	private static Borrow2[] searchBorrowsByReaderName(String nameReader) {
		Borrow2[] borrows = new Borrow2[20];
		int j = 0;
		for (int i = 0; i < BORROWS.length; i++) {
			Borrow2 borrow = BORROWS[i];
			if (borrow!=null && borrow.getReader().getName().equals(nameReader)) {
				borrows[j] = BORROWS[i];
				j++;
			}
		}
		if(j==0) return null;
		return borrows;
	}
	
	/*
	 * Sắp xếp danh sách mượn
	 */	
	
	private static void showSortBorrow() {
		
		sortBorrow(BORROWS);
		showAllBorrow();
		
		/*
		 * System.out.println("---Sắp xếp danh sách theo---");
		 * System.out.println("1. Theo tên bạn đọc");
		 * System.out.println("2. Theo số lượng cuốn sách được mượn giảm dần");
		 * System.out.println("--------------------------------------");
		 * System.out.print("Xin mời nhập lựa chọn của bạn: "); int functionChoice = -1;
		 * do { functionChoice = new Scanner(System.in).nextInt(); if (functionChoice >=
		 * 1 && functionChoice <= 2) { break; }
		 * System.out.print("Chức năng được chọn không hợp lệ, vui lòng chọn lại: "); }
		 * while (functionChoice > 2 || functionChoice < 1);
		 * 
		 * switch (functionChoice) { case 1: sortBorrow(BORROWS, "theo ten"); break;
		 * case 2: sortBorrow(BORROWS); break; }
		 */
	}
	
	private static void sortBorrow(Borrow2[] borrows) {
		if(!checkDataBorrows(borrows)) {
			System.out.println("Chưa có dữ liệu về danh sách mượn để thao tác, vui lòng nhập danh sách mượn trước đã!");
			return;
		}
		for(int i=0; i<borrows.length; i++) {
			for(int j=i+1; j<borrows.length; j++) {
				if(borrows[i] != null && borrows[j] != null && borrows[i].getReader().getName().compareTo(borrows[j].getReader().getName()) > 0 ) {
        			Borrow2 tmp = borrows[i]; 
        			borrows[i] = borrows[j]; 
        			borrows[j] = tmp;
        		}
			}
		}
		for(int i=0; i<borrows.length; i++) {
			if(borrows[i] != null) {
				sortBorrow(borrows[i].getBorrowing());
			}
		}
	}
	
	private static void sortBorrow(Borrowing[] borrow) {
		
		//có bạn đọc là có sách mượn do phía  trên set luôn > 0
		
		for(int i=0; i<borrow.length; i++) {
			for(int j=i+1; j<borrow.length; j++) {
				if(borrow[i] != null && borrow[j] != null && borrow[i].getQuantity() < borrow[j].getQuantity() ) {
        			Borrowing tmp = borrow[i]; 
        			borrow[i] = borrow[j]; 
        			borrow[j] = tmp;
        		}
			}
		}
	}
	
	
	
}