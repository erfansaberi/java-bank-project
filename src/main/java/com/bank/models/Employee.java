package com.bank.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Employee extends Person {
    static String EMPLOYEE_DATAFILE_PATH = "src/main/java/com/bank/data/employees.csv";
    static ArrayList<Employee> allEmployees = new ArrayList<>(); // All created accounts

    private EmployeeStatus status;

    /**
     * Create new employee. Employee is not saved to arraylist.
     * Save method must be called to save employee to arraylist.
     * Id is generated automatically.
     * Status is set to EmployeeStatus.ACTIVE.
     * Join date is set to current date.
     */
    public Employee() {
        super();
        this.joinDate = new Date();
        this.status = EmployeeStatus.ACTIVE;
        this.id = allEmployees.size() + 1;
    }

    /**
     * Save employee to arraylist.
     */
    public void save() {
        allEmployees.add(this);
    }

    /**
     * Check if email is used in arraylist.
     * 
     * @param email Email to check.
     * @return True if email is used, false otherwise.
     */
    public static boolean emailExists(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                if (employee.getStatus() != EmployeeStatus.DELETED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get employee by email.
     * 
     * @param email Email to get employee by.
     * @return Employee with given email, null if not found.
     */
    public static Employee getByEmail(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                if (employee.getStatus() != EmployeeStatus.DELETED) {
                    return employee;
                }
            }
        }
        return null;
    }

    /**
     * Authenticate employee.
     * 
     * @param email       Email entered by user.
     * @param rawPassword Password entered by user.
     * @return true if email and password are correct, false otherwise
     */
    public static boolean authenticate(String email, String rawPassword) {
        if (emailExists(email)) {
            Employee employee = Employee.getByEmail(email);
            if (employee.getPassword().equals(Core.hashPassword(rawPassword))) {
                if (employee.getStatus() == EmployeeStatus.ACTIVE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Read all employees from employees.csv file and save them to arraylist.
     * CSV file format:
     * id, firstName, lastName, email, password, gender, nationalId, birthDate,
     * joinDate, status
     */
    public static void loadData() {
        try (Scanner employeesScanner = new Scanner(new File(EMPLOYEE_DATAFILE_PATH))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (employeesScanner.hasNextLine()) {
                String line = employeesScanner.nextLine();
                String[] employeeData = line.split(", ");
                Employee employee = new Employee();
                employee.setId(Long.parseLong(employeeData[0]));
                employee.setFirstName(employeeData[1]);
                employee.setLastName(employeeData[2]);
                employee.setEmail(employeeData[3]);
                employee.setPhoneNumber(employeeData[4]);
                employee.setPasswordHash(employeeData[5]);
                employee.setGender(Gender.valueOf(employeeData[6]));
                employee.setNationalId(employeeData[7]);
                employee.setBirthDate(dateFormat.parse(employeeData[8]));
                employee.setJoinDate(dateFormat.parse(employeeData[9]));
                employee.setStatus(EmployeeStatus.valueOf(employeeData[10]));
                employee.save();
            }
        } catch (NumberFormatException | FileNotFoundException | ParseException e) {
            System.err.println("[!] Error loading employees from file.");
            e.printStackTrace();
        }
    }

    /**
     * Save all employees to employees.csv file.
     * Write all employees data to employees.csv file in format:
     * id, firstName, lastName, email, phoneNumber, passwordHash, gender,
     * nationalId, birthDate, joinDate, status
     */
    public static void saveData() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            FileWriter dataFile = new FileWriter(EMPLOYEE_DATAFILE_PATH);
            for (Employee employee : allEmployees) {
                dataFile.write(employee.getId() + ", " + employee.getFirstName() + ", " + employee.getLastName() + ", "
                        + employee.getEmail() + ", " + employee.getPhoneNumber() + ", " + employee.getPassword() + ", "
                        + employee.getGender() + ", " + employee.getNationalId() + ", "
                        + dateFormat.format(employee.getBirthDate()) + ", " + dateFormat.format(employee.getJoinDate())
                        + ", " + employee.getStatus() + "\n");
            }
            dataFile.close();
        } catch (Exception e) {
            System.err.println("[!] Error saving employees to file.");
            System.err.println(e);
        }
    }

    // Getters and setters
    public ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public EmployeeStatus getStatus() {
        return this.status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public String toString() {
        return "Employee{" +
                "id=" + this.id +
                ", email='" + this.email + '\'' +
                ", status=" + this.status +
                '}';
    }
}

/**
 * Employee status.
 * ACTIVE: Employee is active.
 * INACTIVE: Employee is inactive.
 * DELETED: Employee is deleted.
 */
enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    DELETED
}