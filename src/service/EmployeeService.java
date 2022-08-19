package service;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import model.Employee;

import java.util.*;

public class EmployeeService {


    private static final int LIMIT = 10;

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String name,
                        String surname,
                        int department,
                        double salary) {
        Employee employee = new Employee(name, surname, department,salary);


        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private String getKey(String name, String surname) {
        return name + " " + surname;
    }
}
