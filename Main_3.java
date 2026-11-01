import java.util.Scanner;
import java.util.ArrayList;

abstract class Person {
    private String fullName;
    private int age;

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public void displayInfo() {
        System.out.println("Full Name: "+ fullName);
        System.out.println("Age: "+ age);
    }
}

class Guest extends Person {
    private String citizenID;

    public Guest(String fullName, int age, String citizenID) {
        super(fullName, age);
        this.citizenID = citizenID;
    }

    public String getCitizenID() {
        return citizenID;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Citizen ID: "+ citizenID);
    }
}

class Staff extends Person {
    private String licenseID;
    private String employeeID;

    public Staff(String fullName, int age, String licenseID, String employeeID) {
        super(fullName, age);
        this.licenseID = licenseID;
        this.employeeID = employeeID;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("License ID: "+ licenseID);
        System.out.println("Employee ID: "+ employeeID);
    }
}

class Room {
    private String roomType;
    private int rentalDay;
    private long pricePerDay;
    private ArrayList<Guest> guests;

    public Room(String roomType, int rentalDay) {
        this.roomType = roomType;
        this.rentalDay = rentalDay;
        this.guests = new ArrayList<>();
        this.pricePerDay = setPriceBasedOnType(this.roomType);
    }
    
    public String getRoomType() {
        return roomType;
    }

    public int getRentalDay() {
        return rentalDay;
    }

    private long setPriceBasedOnType(String type) {
        switch (type.trim().toUpperCase()) {
            case "VIP1": return 900000;
            case "VIP2": return 700000;
            case "VIP3": return 500000;
            default: return 0;
        }
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
        System.out.println("--> Đã thêm khách: "+ guest.getFullName());
    }

    public void removeGuest(String citizenID) {
        boolean isRemoved = false;
        for(int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getCitizenID().equals(citizenID)) {
                System.out.println("--> Đã xóa khách: "+ guests.get(i).getFullName());
                guests.remove(i);
                isRemoved = true;
                break;
            }
        }
        if(!isRemoved) {
            System.out.println("--> Không tìm thấy khách với CCCD: "+ citizenID);
        }
    }

    public long calculateTotalCost() {
        return pricePerDay * rentalDay;
    }

    public void displayInfo() {
        System.out.println("\n=== THÔNG TIN PHÒNG ===");
        System.out.println("Room Type: "+ roomType);
        System.out.println("Rental Day: "+ rentalDay);
        System.out.println("Price Per Day: "+ pricePerDay + " VND");
        System.out.println("Total Cost: " + calculateTotalCost() + " VND");

        System.out.println("--- Danh Sách Khách ---");
        if(guests.isEmpty()) {
            System.out.println("Phòng Đang Trống.");
        } else {
            for(Guest g : guests) {
                g.displayInfo();
                System.out.println("-----------------------");
            }
        }
    }
}

class Main_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập Loại Phòng (VIP1, VIP2, VIP3): ");
        String roomType = sc.nextLine();

        System.out.print("Nhập số ngày thuê: ");
        int rentalDay = sc.nextInt();
        sc.nextLine();

        Room room = new Room(roomType, rentalDay);

        Guest g1 = new Guest("Nguyen Van A", 30, "001202001111");
        Guest g2 = new Guest("Tran Thi B", 25, "001202002222");

        System.out.println("\n[THỰC HIỆN THÊM KHÁCH]");
        room.addGuest(g1);
        room.addGuest(g2);

        room.displayInfo();

        System.out.println("\n[THỰC HIỆN XÓA KHÁCH]");
        room.removeGuest("001202001111");

        room.displayInfo();

        sc.close();
    }
}