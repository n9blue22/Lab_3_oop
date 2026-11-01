import java.util.ArrayList;
import java.util.Scanner;

abstract class Student {
    private String student_id;
    private String full_name;
    private String address;
    private int total_credits;
    private double gpa;

    public Student(String student_id, String full_name, String address, int total_credits, double gpa) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.address = address;
        this.total_credits = total_credits;
        this.gpa = gpa;
    }

    public String getStudentid() {
        return student_id;
    }
    public String getFullName() {
        return full_name;
    }
    public double getGpa() {
        return gpa;
    }

    public abstract boolean isEligibleForGraduation();

    public void displayinfo() {
        System.out.println("ID: "+ student_id);
        System.out.println("Name: "+ full_name);
        System.out.println("Address: "+ address);
        System.out.println("Credits: "+ total_credits);
        System.out.println("GPA: "+ gpa);
    }

    public int getTotalCredits() {
        return total_credits;
    }
}

class CollegeStudent extends Student {
    private double graduation_exam_score;

    public CollegeStudent(String student_id, String full_name, String address, int total_credits, double gpa, double exam_score) {
        super(student_id, full_name, address, total_credits, gpa);
        this.graduation_exam_score = exam_score;
    }

    @Override
    public boolean isEligibleForGraduation() {
        return getTotalCredits() >= 120
            && getGpa() >= 5.0
            && graduation_exam_score >= 5.0;
    }

    @Override
    public void displayinfo() {
        super.displayinfo();
        System.out.println("Exam score: "+ graduation_exam_score);
    }
}

class UniversityStudent extends Student {
    private String thesis_title;
    private double thesis_score;

    public UniversityStudent(String student_id, String full_name, String address, int total_credits, double gpa, String thesis_title, double thesis_score) {
        super(student_id, full_name, address, total_credits, gpa);
        this.thesis_title = thesis_title;
        this.thesis_score = thesis_score;
    }

    @Override
    public boolean isEligibleForGraduation() {
        return getTotalCredits() >= 170
            && getGpa() >= 5.0
            && thesis_score >= 5.0;
    }

    @Override
    public void displayinfo() {
        super.displayinfo();
        System.out.println("Thesis: "+ thesis_title);
        System.out.println("Thesis Score: "+ thesis_score);
    }
}

class Main_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        System.out.print("Enter a number of student: ");
        int n = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));
            System.out.print("Type (1-College, 2-University): ");
            int type = Integer.parseInt(sc.nextLine());

        if(type != 1 && type != 2) {
            System.out.println("Invalid student type. ");
            i--;
            continue;
        }

        System.out.print("Student ID: ");
        String student_id = sc.nextLine();

        System.out.print("Full Name: ");
        String full_name = sc.nextLine();

        System.out.print("Addres: ");
        String address = sc.nextLine();

        System.out.print("Total Credits: ");
        int total_credits = Integer.parseInt(sc.nextLine());

        System.out.print("GPA: ");
        double gpa = Double.parseDouble(sc.nextLine());

        if (total_credits < 0 || gpa < 0 || gpa > 10) {
            System.out.println("Invalid credits or GPA.");
            i--;
            continue;
        }
        if(type == 1) {
            System.out.print("Gradutation exam score: ");
            double exam = Double.parseDouble(sc.nextLine());

            if(exam < 0 || exam > 10) {
                System.out.println("Invalid exam score");
                i--;
                continue;
            }
            students.add(new CollegeStudent(
                student_id,full_name, address, total_credits, gpa, exam));
        } else {
                System.out.print("Thesis title: ");
                String title = sc.nextLine();

                System.out.print("Thesis score: ");
                double score = Double.parseDouble(sc.nextLine());

                if (score < 0 || score > 10) {
                    System.out.println("Invalid thesis score.");
                    i--;
                    continue;
                }
                students.add(new UniversityStudent(
                            student_id, full_name, address, total_credits, gpa, title, score));
        }
    }
    int count = 0;
    for(Student s : students) {
        if(s.isEligibleForGraduation()) {
            count++;
        }
    }
    System.out.println("\nEligible students: "+ count);

    UniversityStudent best = null;
    for(Student s : students) {
        if(s instanceof UniversityStudent) {
            UniversityStudent u = (UniversityStudent) s;

            if(best == null || u.getGpa() > best.getGpa()) {
                best = u;
            }
        }
    }
    if(best != null) {
        System.out.println("\nUniversity student with highest GPA:");
        best.displayinfo();
    } else {
        System.out.println("No university studetn found.");
    }

    sc.close();
    }
}