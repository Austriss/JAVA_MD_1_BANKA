package models;

public class LargeTransaction extends Transaction{
    public Employee employee_to_check;
    public boolean is_accepted;

    public void set_employee_to_check(Employee emp) {
        employee_to_check = emp;
    }
    public void set_is_accepted(boolean accepted) {
        is_accepted = accepted;
    }

    public Employee get_employee_to_check() {
        return employee_to_check;
    }
    public boolean get_is_accepted() {
        return is_accepted;
    }

    public LargeTransaction(Employee emp) {
        set_employee_to_check(emp);
        set_is_accepted(false);
    }
}
