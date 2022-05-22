package entity;

import java.util.Scanner;

public class Reader extends Human{
	
	public static final String SV = "Sinh viên";
    public static final String HV = "Học viên cao học";
    public static final String GV = "Giáo viên";
	
    private static int AUTO_ID = -1;
    
    private int id;
    private String readerCategory;
	
    public Reader() {
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

    

    public String getCustomerCategory() {
        return readerCategory;
    }

    public void setCustomerCategory(String readerCategory) {
        this.readerCategory = readerCategory;
    }

    @Override
    public String toString() {
        return "entity.Reader{" +
                "id=" + id +
                super.toString() +
                ", readerCategory='" + readerCategory + '\'' +
                '}';
    }

    public void inputInfo() {
        super.inputInfo();
        System.out.println("Nhập loại bạn đọc là 1 trong các lựa chọn dưới đây: ");
        System.out.println("1. Sinh viên");
        System.out.println("2. Học viên cao học");
        System.out.println("3. Giáo viên");
        System.out.print("Xin mời nhập lựa chọn của bạn: ");
        int choice = -1;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 3) {
                break;
            }
            System.out.print("Lựa chọn loại khách hàng không hợp lệ, vui lòng chọn lại: ");
        } while (choice > 3 || choice < 1);
        switch (choice) {
            case 1:
                this.readerCategory = Reader.SV;
                break;
            case 2:
                this.readerCategory = Reader.HV;
                break;
            case 3:
                this.readerCategory = Reader.GV;
                break;
        }
    }
}
