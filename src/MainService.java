import models.*;
import models.enums.AutomaticPayementScheduleType;
import models.enums.City;
import models.enums.EmployeeStatus;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainService {
    public static ArrayList<Client> clients = new ArrayList<Client>();
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<Card> cards = new ArrayList<Card>();
    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) throws Exception {
        Person person = new Person("kristaps", "porzingis", "12345678901");

        Address add = new Address(City.Pope, "inzinieru iela", 10);
        Employee employee = new Employee(
                EmployeeStatus.Active,
                100,
                "Austris",
                "Zeidmanis",
                "23456789012"
        );
        Client client = new Client("austris@austris.com" , add, "Austris", "Zeidmanis", "12345678984");
        client.set_client_code();

        BankAccount sa = new BankAccount();
        sa.set_iban("LV00HABA123456789");
        sa.set_balance((float)500);
        BankAccount ta = new BankAccount();
        ta.set_iban("LV00HABA987654321");
        ta.set_balance((float)100);

        client.add_bank_account(sa);

        Card card = new Card();
        card.set_account(sa);

        Transaction transaction = new Transaction(
                sa,
                ta,
                50,
                "maksajums"
        );

        AutomaticPayment autoPayment = new AutomaticPayment(
                "ire",
                AutomaticPayementScheduleType.Monthly
        );
        autoPayment.set_source_acc(sa);
        autoPayment.set_target_acc(ta);
        autoPayment.set_amount(75);
        autoPayment.set_description("masksajums");

        LargeTransaction largeTransac = new LargeTransaction(employee);
        largeTransac.set_source_acc(sa);
        largeTransac.set_target_acc(ta);
        largeTransac.set_amount(200);
        largeTransac.set_description("parskaitijums");
        largeTransac.set_is_accepted(largeTransac.can_employee_approve());

        System.out.println(person);
        System.out.println(add);
        System.out.println(employee);
        System.out.println(client);
        System.out.println(sa);
        System.out.println(ta);
        System.out.println(card);
        System.out.println(transaction);
        System.out.println(autoPayment);
        System.out.println(largeTransac);
    }
    public static Employee create_employee(String name, String surname, String personal_code, EmployeeStatus status, float approval_limit) throws Exception {
        for (Employee emp: employees) {
            if (emp.get_personal_code().equals(personal_code)) {
                throw new Exception("Employee with same pc already exists");
            }
        }
        Employee emp = new Employee(status, approval_limit, name, surname, personal_code);
        System.out.println("created employee " + emp);
        employees.add(emp);
        return emp;
    }

    public static Employee find_employee_by_pc(String personal_code) {
        for (Employee emp : employees) {
            if (emp.get_personal_code().equals(personal_code)) {
                return emp;
            }
        }
        return null;
    }
    public static boolean update_employee_approval_limit(String personal_code, float limit) {
        Employee emp = find_employee_by_pc(personal_code);
        if (emp instanceof Employee) {
            emp.set_aproval_limit(limit);
            return true;
        }
        return false;
    }
    public static boolean update_employee_status(String personal_code, EmployeeStatus status) {
        Employee emp = find_employee_by_pc(personal_code);
        if (emp instanceof Employee) {
            emp.set_status(status);
            return true;
        }
        return false;
    }
    public static boolean delete_employee(String personal_code) {
        // return: successful update or not
        for (int i=0; i<employees.size(); i++) {
            if (employees.get(i).get_personal_code().equals(personal_code)) {
                employees.remove(i);
                return true;
            }
        }
        return false;
    }
    public static ArrayList<BankAccount> get_client_accounts(String client_code) {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        for (Client cl : clients) {
            if (cl.get_personal_code().equals(client_code)) {
                accounts = cl.get_accounts();
            }
        }
        return accounts;
    }
    public static ArrayList<Transaction> get_account_transactions(String iban) {
        ArrayList<Transaction> transactions_list = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.get_source_account().get_iban().equals(iban)) {
                transactions_list.add(transaction);
                continue;
            }
            if (transaction.get_target_account().get_iban().equals(iban)) {
                transactions_list.add(transaction);
            }
        }
        return transactions_list;
    }
    public static ArrayList<Client> get_clients_from_city(City city) throws Exception {
        ArrayList<Client> clients_list = new ArrayList<Client>();
        if (!(city instanceof City)) {
            throw new Exception("No such city");
        }
        for (Client client : clients) {
            if (client.get_address().get_city().equals(city)) {
                clients_list.add(client);
            }
        }
        return clients_list;
    }
    public static ArrayList<Transaction> get_client_receiver_transactions_by_client_code(String client_code) throws Exception {
        ArrayList<Transaction> transactions_list = new ArrayList<Transaction>();
        Client client = null;
        for (Client cl : clients) {
            if (cl.get_client_code().equals(client_code)) {
                client = cl;
                break;
            }
        }
        if (client == null) {
            throw new Exception("No client in database");
        }
        for (Transaction tr: transactions) {
            for (BankAccount acc: client.get_accounts()) {
                if (tr.get_target_account().get_iban().equals(acc.get_iban())) {
                    transactions_list.add(tr);
                }
            }
        }
        return transactions_list;
    }
    public static void create_account_by_client_code(String client_code) throws Exception {
        Client client = null;
        for (Client cl : clients) {
            if (cl.get_client_code().equals(client_code)) {
                BankAccount acc = new BankAccount();
                cl.add_bank_account(acc);
                return;
            }
        }
        throw new Exception("No client found");
    }
    public static ArrayList<Card> get_close_to_expired_cards() {
        ArrayList<Card> card_list = new ArrayList<Card>();
        LocalDate date = LocalDate.now().plusDays(7);
        for (Card card : cards) {
            if (card.get_expiry_date().isBefore(date)) {
                card_list.add(card);
            }
        }
        return card_list;
    }
    public static ArrayList<BankAccount> add_accounts_linked_to_cards() {
        ArrayList<BankAccount> accounts_list = new ArrayList<BankAccount>();
        for (Card card: cards) {
            boolean found = false;
            for (BankAccount acc: accounts_list) {
                if (acc.get_iban().equals(card.get_account().get_iban())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                accounts_list.add(card.get_account());
            }
        }
        return accounts_list;
    }
    public static ArrayList<Employee> get_employees_allowed_to_approve_limit(float necessary_amount) {
        ArrayList<Employee> able_employees = new ArrayList<Employee>();
        for (Employee emp : employees) {
            if (emp.get_aproval_limit() >= necessary_amount) {
                able_employees.add(emp);
            }
        }
        return able_employees;
    }
    public static int create_transaction(float amount, String desc, BankAccount source, BankAccount target) {
        Transaction transaction = new Transaction(
                source, target, amount, desc
        );
        transactions.add(transaction);
        return transaction.get_id();
    }
    public static int create_auto_transaction(float amount, String desc, BankAccount source, BankAccount target, String title, AutomaticPayementScheduleType schedule_type, LocalDate date) {
        AutomaticPayment transaction = new AutomaticPayment(
                title, schedule_type
        );
        transaction.set_source_acc(source);
        transaction.set_target_acc(target);
        transaction.set_amount(amount);
        transaction.set_description(desc);

        return transaction.get_id();
    }

    public static int create_large_transaction(float amount, String desc, BankAccount source, BankAccount target, Employee emp) {
        LargeTransaction transaction = new LargeTransaction(emp);
        transaction.set_amount(amount);
        transaction.set_description(desc);
        transaction.set_source_acc(source);
        transaction.set_target_acc(target);
        if (transaction.can_employee_approve()) {
            transaction.set_is_accepted(true);
        }
        return transaction.get_id();
    }
    public static void make_auto_payments() {
        for (Transaction transaction: transactions) {
            if (transaction instanceof AutomaticPayment) {
                if (((AutomaticPayment) transaction).get_next_payment_date().isBefore(LocalDate.now())) {
                    transactions.add(new Transaction(
                            transaction.get_source_account(), transaction.get_target_account(), transaction.get_amount(), transaction.get_description()
                    ));
                    ((AutomaticPayment) transaction).update_next_payment_date();
                }
            }
        }
    }
    public static ArrayList<Employee> sort_employees_by_limit(ArrayList<Employee> employees) {
        ArrayList<Employee> array_copy = (ArrayList<Employee>) employees.clone();
        for (int i = 0; i < array_copy.size() - 1; i++) {
            boolean modified = false;
            for (int n = 0; n < array_copy.size() - 1; n++) {
                if (array_copy.get(n).get_aproval_limit() > array_copy.get(n+1).get_aproval_limit()) {
                    Employee temp = array_copy.get(n);
                    array_copy.set(n, array_copy.get(n+1));
                    array_copy.set(n+1, temp);
                    modified = true;
                }
            }
            if (!modified) {
                return array_copy;
            }
        }
        return array_copy;
    }
}
