import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String socialSecurityNumber;
    private int yearOfEmployment;

    public Employee(String firstName, String lastName, String address, String email, String socialSecurityNumber, int yearOfEmployment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
        this.yearOfEmployment = yearOfEmployment;
    }

    public int calculateSalary() {
        int currentYear = java.time.Year.now().getValue();
        int yearsWorked = currentYear - yearOfEmployment;
        return 3000 + yearsWorked * 1000;
    }
}

class Developer extends Employee {
    private List<Technology> technologies = new ArrayList<>();

    public Developer(String firstName, String lastName, String address, String email, String socialSecurityNumber, int yearOfEmployment) {
        super(firstName, lastName, address, email, socialSecurityNumber, yearOfEmployment);
    }

    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }

    @Override
    public int calculateSalary() {
        int baseSalary = super.calculateSalary();
        for (Technology tech : technologies) {
            baseSalary += tech.getBonus();
        }
        return baseSalary;
    }
}

class Tester extends Employee {
    private List<String> testTypes = new ArrayList<>();

    public Tester(String firstName, String lastName, String address, String email, String socialSecurityNumber, int yearOfEmployment) {
        super(firstName, lastName, address, email, socialSecurityNumber, yearOfEmployment);
    }

    public void addTestType(String testType) {
        testTypes.add(testType);
    }

    @Override
    public int calculateSalary() {
        int baseSalary = super.calculateSalary();
        baseSalary += testTypes.size() * 300;
        return baseSalary;
    }
}

class Manager extends Employee {
    private List<Goal> goals = new ArrayList<>();

    public Manager(String firstName, String lastName, String address, String email, String socialSecurityNumber, int yearOfEmployment) {
        super(firstName, lastName, address, email, socialSecurityNumber, yearOfEmployment);
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    @Override
    public int calculateSalary() {
        int baseSalary = super.calculateSalary();
        int currentYear = java.time.LocalDate.now().getYear();
        int currentMonth = java.time.LocalDate.now().getMonthValue(); // Corrected line for month
        for (Goal goal : goals) {
            if (goal.getYear() == currentYear && goal.getMonth() == currentMonth) {
                baseSalary += goal.getBonus();
            }
        }
        return baseSalary;
    }
}


class Technology {
    private String name;
    private int bonus;

    public Technology(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}

class Goal {
    private int year;
    private int month;
    private int day;
    private String name;
    private int bonus;

    public Goal(int year, int month, int day, String name, int bonus) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.name = name;
        this.bonus = bonus;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getBonus() {
        return bonus;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Developer dev = new Developer("John", "Doe", "New York", "john@doe.com", "123456789", 2020);
        Technology java = new Technology("Java", 800);
        dev.addTechnology(java);
        employees.add(dev);
        Tester tester = new Tester("Jane", "Smith", "Los Angeles", "jane@smith.com", "987654321", 2021);
        tester.addTestType("UI/UX");
        employees.add(tester);
        Manager manager = new Manager("Bob", "Johnson", "Chicago", "bob@johnson.com", "1122334455", 2019);
        Goal fbLogin = new Goal(2024, 4, 15, "Implementing FB login", 1000);
        manager.addGoal(fbLogin);
        employees.add(manager);
        int totalAmount = 0;
        for (Employee employee : employees) {
            totalAmount += employee.calculateSalary();
        }
        System.out.println("Total amount to be paid this month: " + totalAmount + " PLN");
    }
}