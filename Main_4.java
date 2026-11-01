import java.util.Scanner;
import java.util.ArrayList;

abstract class Singer {
    private String fullName;
    private int yearOfExperience;
    private int numberOfAlbumSale;
    private int numberOFPerformance;

    public Singer(String fullName, int yearOfExperience,int numberOfAlbumSale, int numberOFPerformance) {
        this.fullName = fullName;
        this.yearOfExperience = yearOfExperience;
        this.numberOfAlbumSale = numberOfAlbumSale;
        this.numberOFPerformance = numberOFPerformance;
    }

    public String getFullName() {
        return fullName;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public int getNumberOfAlbumSale() {
        return numberOfAlbumSale;
    }

    public int getNumberOfPerformance() {
        return numberOFPerformance;
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("Name: "+ fullName);
        System.out.println("Year of experience: "+ yearOfExperience);
        System.out.println("Number of album sale: "+ numberOfAlbumSale);
        System.out.println("Number of performance: "+ numberOFPerformance);
        System.out.printf("Salary: %,.0f VND\n", calculateSalary());
    }
}

class Famous extends Singer {
    private int numberOfGameShows;

    public Famous(String fullName, int yearOfExperience,int numberOfAlbumSale, int numberOFPerformance, int numberOfGameShows) {
        super(fullName, yearOfExperience, numberOfAlbumSale, numberOFPerformance);
        this.numberOfGameShows = numberOfGameShows;
    }

    public int getNumberOfGameShows() {
        return numberOfGameShows;
    }

    @Override
    public double calculateSalary() {
        return 5_000_000.0
        + 500_000.0 * getYearOfExperience()
        + 1_200.0 * getNumberOfAlbumSale()
        + 500_000.0 * getNumberOfPerformance()
        + 500_000.0 * getNumberOfGameShows();
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getFullName());
        System.out.println("Year of experience: " + getYearOfExperience());
        System.out.println("Number of album sale: " + getNumberOfAlbumSale());
        System.out.println("Number of performance: " + getNumberOfPerformance());
        System.out.println("Number of game shows: " + numberOfGameShows);
        System.out.printf("Salary: %,.0f VND\n", calculateSalary());
        System.out.println("Number of game shows: "+ numberOfGameShows);
    }
}

class Regular extends Singer {
    public Regular(String fullName, int yearOfExperience,int numberOfAlbumSale, int numberOFPerformance) {
        super(fullName, yearOfExperience, numberOfAlbumSale, numberOFPerformance);
    }

    @Override
    public double calculateSalary() {
        return 3_000_000.0 
        + 500_000.0 * getYearOfExperience()
        + 1_000.0 * getNumberOfAlbumSale()
        + 200_000.0 * getNumberOfPerformance();
    }
}

class Main_4 {
    public static void main(String[] args) {
        ArrayList<Singer> singers = new ArrayList<>();

        singers.add(new Regular("Nguyen Van A", 3, 5000, 10));
        singers.add(new Famous("Tran Thi B", 5, 20000, 15, 4));

        System.out.println("=== DANH SÁCH CA SĨ ===");
        for (Singer s : singers) {
            s.displayInfo();
            System.out.println("-------------------------");
        }
        if(!singers.isEmpty()) {
            Singer highestPaid = singers.get(0);
            for(Singer s : singers) {
                if(s.calculateSalary() > highestPaid.calculateSalary()) {
                    highestPaid = s;
                }
            }
            System.out.println("=== CA SĨ LƯƠNG CAO NHẤT ===");
            highestPaid.displayInfo();
        }
    }
}