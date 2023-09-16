import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Car {
    private String model;
    private int year;
    private boolean available;
    private Date returnDate;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
        this.available = true;
        this.returnDate = null;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

class Customer {
    private String name;
    private String aadharNumber;
    private String licenseNumber;
    private Car rentedCar;
    private Date returnDate;

    public Customer(String name, String aadharNumber, String licenseNumber) {
        this.name = name;
        this.aadharNumber = aadharNumber;
        this.licenseNumber = licenseNumber;
        this.rentedCar = null;
        this.returnDate = null;
    }

    public String getName() {
        return name;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void rentCar(Car car, Date returnDate) {
        if (car.isAvailable()) {
            car.setAvailable(false);
            car.setReturnDate(returnDate);
            rentedCar = car;
            this.returnDate = returnDate;
            System.out.println(name + " rented a " + car.getModel());
            System.out.println("Return Date: " + new SimpleDateFormat("dd/MM/yyyy").format(returnDate));
        } else {
            System.out.println("Sorry, " + car.getModel() + " is not available.");
        }
    }

    public void returnCar() {
        if (rentedCar != null) {
            rentedCar.setAvailable(true);
            rentedCar.setReturnDate(null);
            rentedCar = null;
            returnDate = null;
            System.out.println(name + " returned a car.");
        } else {
            System.out.println(name + " has no car to return.");
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Car[] cars = {
                new Car("Alto", 2022),
                new Car("Benz", 2021),
                new Car("MG", 2020),
                new Car("Toyota", 2022),
                new Car("Honda", 2021),
                new Car("Ford", 2020)
                // Add more cars here...
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Available cars:");
        for (int i = 0; i < cars.length; i++) {
            System.out.println((i + 1) + ". " + cars[i].getModel());
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Aadhar number: ");
        String aadharNumber = scanner.nextLine();

        System.out.print("Enter License number: ");
        String licenseNumber = scanner.nextLine();

        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("Enter the number of the car you want to rent: ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= cars.length) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please choose a number between 1 and " + cars.length + ".");
            }
        }

        scanner.nextLine(); // Consume newline character
        System.out.print("Enter return date (dd/MM/yyyy): ");
        String returnDateString = scanner.nextLine();

        boolean validDate = true;
        Date returnDate = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            returnDate = dateFormat.parse(returnDateString);
        } catch (Exception e) {
            validDate = false;
        }

        if (validDate) {
            Customer customer = new Customer(name, aadharNumber, licenseNumber);
            customer.rentCar(cars[choice - 1], returnDate);
        } else {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        }
    }
}

