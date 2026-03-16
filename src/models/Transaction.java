package models;

import java.lang.classfile.constantpool.LoadableConstantEntry;
import java.time.LocalDate;

public class Transaction {
    private static int id_counter = 1;
    private int id;
    private LocalDate datetime;
    private float amount;
    private String description;
    private BankAccount source_account;
    private BankAccount target_account;

    public void set_date(LocalDate date) {
        datetime = date;
    }
    public void set_amount(float amm) {
        if (amm > 0) {
            amount = amm;
        }
    }
    public void set_description(String desc) {
        description = desc;
    }
    public void set_source_acc(BankAccount acc) {
        if (!acc.get_iban().isEmpty()) {
            source_account = acc;
        }
    }
    public void set_target_acc(BankAccount acc) {
        if (!acc.get_iban().isEmpty()) {
            target_account = acc;
        }
    }
    public void set_id() {
        id=id_counter;
        id_counter++;
    }

    public Transaction() {
        set_id();
        set_date(LocalDate.now());
    }
    public Transaction(BankAccount source_acc, BankAccount target_acc, float amm, String desc) {
        set_id();
        set_date(LocalDate.now());
        set_source_acc(source_acc);
        set_target_acc(target_acc);
        set_amount(amm);
        set_description(desc);
    }
    public int get_id() {
        return id;
    }
    public LocalDate get_date() {
        return datetime;
    }
    public float get_amount() {
        return amount;
    }
    public String get_description() {
        return description;
    }
    public BankAccount get_source_account() {
        return source_account;
    }
    public BankAccount get_target_account() {
        return target_account;
    }

    public boolean check_sufficient_balance() {
        return source_account.get_balance() >= get_amount();
    }

}
