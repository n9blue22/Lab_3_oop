import java.util.Scanner;
import java.util.ArrayList;

abstract class Ticket {
    private String ticketId;
    private String holderGame;
    private int yearOfBirth;
    protected int numberGamePlayed;

    public Ticket() {
    }

    public Ticket(String ticketId, String holderGame, int yearOfBirth, int numberGamePlayed) {
        this.ticketId = ticketId;
        this.holderGame = holderGame;
        this.yearOfBirth = yearOfBirth;
        this.numberGamePlayed = numberGamePlayed;
    }
    public int getgamesPlayed() {
        return numberGamePlayed;
    }
    
    public abstract double calculatePrice();

    public void displayInfo() {
        System.out.println("ID: " + ticketId);
        System.out.println("Name: " + holderGame);
        System.out.println("Year of Birth: " + yearOfBirth);
        System.out.println("Number Game: " + numberGamePlayed);
        System.out.printf("Price: %,.0f VND\n", calculatePrice());
    }

    public int getNumberGamePlayed() {
        return numberGamePlayed;
    }
}

class fullPackage extends Ticket {
    public fullPackage(String ticketID, String holderGame, int yearOfBirth) {
        super(ticketID, holderGame, yearOfBirth, 30);
    }
    public fullPackage() {
        super();
        this.numberGamePlayed = 30;
    }
    @Override
    public double calculatePrice() {
        return 200000;
    }
}

class patrialTicket extends Ticket {
    public patrialTicket() {
        super();
    }

    public patrialTicket(String ticketID, String holderGame,int yearOfBirth,int numberGamePlayed) {
        super(ticketID, holderGame, yearOfBirth, numberGamePlayed);
    }

    @Override
    public double calculatePrice() {
        return 70000 + (20000 * this.numberGamePlayed);
    }
}

class Main_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Ticket> tickets = new ArrayList<>();

        System.out.print("Enter number of tickets: ");
        int n = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < n; i++) {
            System.out.println("\n--- Ticket "+ (i + 1) + " ---");
            System.out.println("Type (1 - Full Package, 2 - Patrial): ");
            int type = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Ticket ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Year of Birth: ");
            int yob = Integer.parseInt(sc.nextLine());

            if(type == 1) {
                tickets.add(new fullPackage(id, name, yob));
            } else {
                System.out.print("Enter Number of Games Played: ");
                int games = Integer.parseInt(sc.nextLine());
                tickets.add(new patrialTicket(id, name, yob, games));
            }
        }
        double totalRevenue = 0;
        int countPatrial = 0;

        System.out.println("\n--- TICKET LIST ---");
        for (Ticket t : tickets) {
            t.displayInfo();
            totalRevenue += t.calculatePrice();
            if(t instanceof patrialTicket) {
                countPatrial++;
            }
        }
        System.out.printf("\nTotal Revenue: %,.0f VND\n", totalRevenue);
        System.out.println("Number of Partial Tickets sold: " + countPatrial);
    }
}