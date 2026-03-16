package models;

import models.enums.EmployeeStatus;

public class Employee extends Person {
    private EmployeeStatus status;
    public Float aproval_limit;

    public EmployeeStatus get_status() {
        return status;
    }
    public Float get_aproval_limit() {
        return aproval_limit;
    }
    public void set_status(EmployeeStatus status) {
        if (status instanceof EmployeeStatus) {
            this.status = status;
        }
    }
    public void set_aproval_limit(Float limit) {
        aproval_limit = limit;
    }
    public Employee(EmployeeStatus status, float limit) {
        set_status(status);
        set_aproval_limit(limit);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "status=" + status +
                ", aproval_limit=" + aproval_limit +
                '}';
    }
}
