package entity;

import java.util.Scanner;

public class Book {
	
	public static final String KHTN = "Khoa học tự nhiên";
    public static final String VHNT = "Văn học – Nghệ thuật";
    public static final String DTVT = "Điện tử Viễn thông";
    public static final String CNTT = "Công nghệ thông tin";
	
    private static int AUTO_ID = -1;
    
    private int id;
    private String name;
    private String author;
    private String specialization;
    private String publishingYear;
	
    public Book() {
        if (AUTO_ID == -1) {
            AUTO_ID = 10000;
            this.id = AUTO_ID;
            return;
        }
        this.id = ++AUTO_ID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String setSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String setPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(String publishingYear) {
        this.publishingYear = publishingYear;
    }


    @Override
    public String toString() {
        return "entity.Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", specialization='" + specialization + '\'' +
                ", publishingYear='" + publishingYear + '\'' +
                '}';
    }

    public void inputInfo() {
        System.out.print("Nhập tên cho sách: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.print("Nhập tác giả cho sách: ");
        this.author = new Scanner(System.in).nextLine();
        System.out.print("Nhập năm sản xuất cho sách: ");
        this.publishingYear = new Scanner(System.in).nextLine();
        System.out.println("Nhập chuyên ngành sách là 1 trong các lựa chọn dưới đây: ");
        System.out.println("1. Khoa học tự nhiên");
        System.out.println("2. Văn học – Nghệ thuật");
        System.out.println("3. Điện tử Viễn thông");
        System.out.println("4. Công nghệ thông tin");
        System.out.print("Xin mời nhập lựa chọn của bạn: ");
        int choice = -1;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 4) {
                break;
            }
            System.out.print("Lựa chọn loại khách hàng không hợp lệ, vui lòng chọn lại: ");
        } while (choice > 4 || choice < 1);
        switch (choice) {
            case 1:
                this.specialization = Book.KHTN;
                break;
            case 2:
                this.specialization = Book.VHNT;
                break;
            case 3:
                this.specialization = Book.DTVT;
                break;
            case 4:
                this.specialization = Book.CNTT;
                break;
        }
    }
}
