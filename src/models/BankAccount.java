package models;


public class BankAccount {
    private static int id_counter = 0;
    private int id;
    private String iban;
    private Float balance;
    private boolean is_active;

    public void set_iban(String ibann) {
        //todo regex
        iban = ibann;
    }
    public void set_balance(Float bal) {
        balance = bal;
    }
    public void set_active(boolean active) {
        is_active = active;
    }

    public int get_id() {
        return id;
    }
    public String get_iban() {
        return iban;
    }
    public Float get_balance() {
        return balance;
    }
    public boolean is_active() {
        return is_active;
    }

    public BankAccount() {
        id_counter++;
        id=id_counter;
        set_balance((float)0);
        set_active(true);
    }
    public BankAccount(String iban) {
        id_counter++;
        id = id_counter;
        set_iban(iban);
        set_active(true);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", is_active=" + is_active +
                '}';
    }
}
