import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

abstract class Product {
    protected String Id;
    protected String type;
    protected double volume;
    protected double price;

    public Product(String Id, String type, double volume, double price) {
        this.Id = Id;
        this.type = type;
        this.volume = volume;
        this.price = price;
    }

    public Product(String type, double price) {
        this("", type, 0.0, price);
    }

    public String getId() {
        return Id;
    }

    public String setId() {
        return Id;
    }

    public String getType() {
        return type;
    }

    public String setType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public double setVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public double setPrice() {
        return price;
    }

    public void input(Scanner scanner) {
        System.out.print("  - Nhập mã sản phẩm: ");
        this.Id = scanner.nextLine();
        System.out.print("  - Nhập thể tích (ml): ");
        this.volume = Double.parseDouble(scanner.nextLine());
    }

    public double calculatePrice() {
        return this.volume * this.price;
    }

    public void output() {
        System.out.printf("Mã: %-6s | Loại: %-28s | Dung tích: %5.1f ml | Thành tiền: %,.0f VND%n", Id, type, volume, calculatePrice());
    }
    
    public String toFileString() {
        return String.format("Mã: %s | Loại: %s | Dung tích: %.1f ml | Đơn giá: %,.0f VND/ml | Thành tiền: %,.0f VND", Id, type, volume, price, calculatePrice());
    }
}

class RosePerfume extends Product {
    public RosePerfume() {
        super("Rose perfume", 20000);
    }
}

class ChamomilePerfume extends Product {
    public ChamomilePerfume() {
        super("Chamomile Perfume", 15000);
    }
}

class DryHairShampoo extends Product {
    public DryHairShampoo() {
        super("Dry Hair Shampoo ", 20000);
    }
}

class OilyHairShampooStd1 extends Product {
    public OilyHairShampooStd1() {
        super("Oily Hair Shampoo - Standard 1", 30000);
    }
}

class OilyHairShampooStd2 extends Product {
    public OilyHairShampooStd2() {
        super("Oily Hair Shampoo - Standard 2 ", 40000);
    }
}

class DrySkinShowerGel extends Product {
    public DrySkinShowerGel() {
        super("Dry Skin Shower Gel ", 20000);
    }
}

class OilySkinShowerGel extends Product {
    public OilySkinShowerGel() {
        super("Oily Skin Shower Gel ", 20000);
    }
}

abstract class Package {
    protected String packageName;
    protected List<Product> products = new ArrayList<>();

    public Package(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculatePackagePrice() {
        double total = 0;
        for (Product p : products) {
            total += p.calculatePrice();
        }
        return total;
    }
    public abstract void inputPackage(Scanner scanner); 

    public void outputPackage() {
        System.out.println("    * Gói đã chọn: " + packageName);
        for (Product p : products) {
            System.out.print("  -");
            p.output();
        }
        System.out.printf("     => Tổng tiền gói: %,.0f VND%n", calculatePackagePrice());
    }
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     Gói:    ").append(packageName).append("\n");
        for (Product p : products) {
            sb.append("    + ").append(p.toFileString()).append("\n");
        }
        sb.append(String.format("    => Tổng tiền gói: %,.0f VND\n", calculatePackagePrice()));
        return sb.toString();
    }
    protected Product chooseShampoo(Scanner scanner) {
        System.out.println("    1. Dry Hair (20k/ml) | 2. Oily Standard 1 (30k/ml) | 3. Oily Standard 2 (40k/ml)");
        System.out.print("    Chọn loại dầu gội: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Product p;
        switch (choice) {
            case 2: p = new OilyHairShampooStd1(); break;
            case 3: p = new OilyHairShampooStd2(); break;
            default: p = new DryHairShampoo(); break;
        }
        p.input(scanner);
        return p;
    }
    protected Product chooseShowerGel(Scanner scanner) {
        System.out.println("    1. Dry Skin Shower Gel (20k/ml) | 2. Oily Skin Shower Gel (40k/ml)");
        System.out.print("    Chọn loại sữa tắm: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Product p = (choice == 2) ? new OilySkinShowerGel() : new DrySkinShowerGel();
        p.input(scanner);
        return p;
    }
    protected Product choosePerfume(Scanner scanner) {
        System.out.println("    1. Rose Perfume (20k/ml) | 2. Chamomile Perfume (15k/ml)");
        System.out.print("    Chọn loại nước hoa: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Product p = (choice == 2) ? new ChamomilePerfume() : new RosePerfume();
        p.input(scanner);
        return p;
    }
}    

class RomancePackage extends Package {
    public RomancePackage() {
        super("Romance Package");
    }

    @Override
    public void inputPackage(Scanner scanner) {
        System.out.println("    [Bắt buộc] Nhập Rose Perfume:");
        Product rose = new RosePerfume();
        rose.input(scanner);
        products.add(rose);

        System.out.print("  [Tùy chọn] Bạn có muốn thêm Dầu gội (Shampoo)? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            products.add(chooseShampoo(scanner));
        }

        System.out.print("  [Tùy chọn] Bạn có muốn thêm Sữa tắm (Shower Gel)? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            products.add(chooseShowerGel(scanner));
        }
    }
}
class FreshAirPackage extends Package {
    public FreshAirPackage() {
        super("Fresh-Air Package");
    }
    @Override
    public void inputPackage(Scanner scanner) {
        System.out.println("  [Bắt buộc] Chọn loại Oily Hair Shampoo:");
        System.out.println("    1. Standard 1 (30k/ml) | 2. Standard 2 (40k/ml)");
        System.out.print("    Chọn (1/2): ");
        int choice = Integer.parseInt(scanner.nextLine());
        Product shampoo = (choice == 2) ? new OilyHairShampooStd2() : new OilyHairShampooStd1();
        shampoo.input(scanner);
        products.add(shampoo);

        System.out.print("  [Tùy chọn] Bạn có muốn thêm Nước hoa (Perfume)? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            products.add(choosePerfume(scanner));
        }

        System.out.print("  [Tùy chọn] Bạn có muốn thêm Sữa tắm (Shower Gel)? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            products.add(chooseShowerGel(scanner));
        }
    }
}

class Customer {
    private String customerID;
    private String fullName;
    private String address;
    private String phoneNumber;

    public void input(Scanner scanner) {
        System.out.print("Nhập mã khách hàng: ");
        this.customerID = scanner.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        this.fullName = scanner.nextLine();
        System.out.print("Nhập địa chỉ khách hàng: ");
        this.address = scanner.nextLine();
        System.out.print("Nhập số điện thoại khách hàng: ");
        this.phoneNumber = scanner.nextLine();
    }

    public void output() {
        System.out.println("Khách hàng: " + fullName + "  (Mã: " + customerID + ") | ĐT: " + phoneNumber + " | ĐC: " + address);
    }
    public String toFileString() {
        return "Khách hàng: " + fullName + " (Mã: " + customerID + ") | ĐT: " + phoneNumber + " | ĐC: " + address;
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private String invoiceDate;
    private Package pkg;

    public void input(Scanner scanner) {
        System.out.print("Nhập mã đơn hàng: ");
        this.orderId = scanner.nextLine();
        System.out.print("Nhập ngày lập hóa đơn (dd/mm/yyyy): ");
        this.invoiceDate = scanner.nextLine();

        System.out.println("--- Thông tin khách hàng ---");
        this.customer = new Customer();
        this.customer.input(scanner);

        System.out.println("--- Chọn loại gói sản phẩm ---");
        System.out.println("1. Romantic Package");
        System.out.println("2. Fresh-Air Package");
        System.out.print("Lựa chọn (1/2): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            this.pkg = new RomancePackage();
        } else {
            this.pkg = new FreshAirPackage();
        }
        this.pkg.inputPackage(scanner);
    }

    public double getOrderPrice() {
        return pkg.calculatePackagePrice();
    }

    public void display() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Mã đơn: " + orderId + " | Ngày lập: " + invoiceDate);
        customer.output();
        pkg.outputPackage();
        System.out.printf("TỔNG TIỀN ĐƠN HÀNG: %,.0f VND%n", getOrderPrice());
        System.out.println("--------------------------------------------------");
    }

    public String toFileString() {
        return "--------------------------------------------------\n" +
               "Mã đơn: " + orderId + " | Ngày lập: " + invoiceDate + "\n" +
               customer.toFileString() + "\n" +
               pkg.toFileString() +
               String.format("TỔNG TIỀN ĐƠN HÀNG: %,.0f VND\n", getOrderPrice()) +
               "--------------------------------------------------\n";
    }
}

public class Main_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Order> orders = new ArrayList<>();

        System.out.print("Nhập số lượng đơn hàng (N): ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\n=== NHẬP ĐƠN HÀNG THỨ " + (i + 1) + " ===");
            Order order = new Order();
            order.input(scanner);
            orders.add(order);
        }

        System.out.println("\n\n==================================================");
        System.out.println("        DANH SÁCH TẤT CẢ CÁC ĐƠN HÀNG");
        System.out.println("==================================================");
        for (Order order : orders) {
            order.display();
        }

        saveOrdersToFile(orders, "order_list.txt");
    }
    private static void saveOrdersToFile(List<Order> orders, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("================ DANH SÁCH ĐƠN HÀNG ================");
            for (Order order : orders) {
                writer.print(order.toFileString());
            }
            System.out.println("\n-> Đã lưu danh sách " + orders.size() + " đơn hàng thành công vào file '" + filename + "'!");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
