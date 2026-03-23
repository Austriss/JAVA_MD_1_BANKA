package models;

import java.util.ArrayList;
import java.util.Objects;

public class Client extends Person {
    private String email;
    private Address address;
    private ArrayList<BankAccount> accounts;
    private String client_code;

    public void set_email(String mail) {
        email = mail;
    }
    public void set_address(Address addy) {
        address = addy;
    }
    public void add_bank_account(BankAccount acc) {
        for (int i=0; i< accounts.size(); i++) {
            if (acc.get_iban().equals(accounts.get(i).get_iban())) {
                return;
            }
        }
        accounts.add(acc);
    }
//    public void add_bank_account(String iban) {
//        for (int i=0; i< accounts.size(); i++) {
//            if (iban.equals(accounts.get(i).get_iban())) {
//                return;
//            }
//        }
//        accounts.add(new BankAccount());
//    }
    public void set_client_code() {
        client_code = get_surname().charAt(0) + "_" + get_name().charAt(0) + get_personal_code();
    }
    public void remove_bank_account(String iban_to_delete) {
        for (int i=0; i<accounts.size(); i++) {
            if (Objects.equals(accounts.get(i).get_iban(), iban_to_delete)) {
                accounts.remove(i);
                return;
            }
        }
    }
    public String get_mail() {
        return email;
    }
    public Address get_address() {
        return address;
    }
    public ArrayList<BankAccount> get_accounts() {
        return accounts;
    }
    public BankAccount get_account(String iban) {
        BankAccount account = new BankAccount();
        for (BankAccount acc: accounts) {
            if (Objects.equals(acc.get_iban(), iban)) {
                account = acc;
            }
        }
        return account;
    }
    public String get_client_code() {
        return client_code;
    }

    public Client(String mail, Address addy) {
        set_email(mail);
        set_address(addy);
        accounts = new ArrayList<BankAccount>();
    }
}
