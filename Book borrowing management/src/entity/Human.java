package entity;

import java.util.Scanner;

public class Human {

	private String name;
    private String address;
    private String phoneNumber;
    
    public Human() {}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String toString() {
        return 
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'';
    }
    
    public void inputInfo() {
        System.out.print("Nhập tên cho bạn đọc: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.print("Nhập địa chỉ cho bạn đọc: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.print("Nhập SĐT cho bạn đọc: ");
        this.phoneNumber = new Scanner(System.in).nextLine();
    }
}
