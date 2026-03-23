package models;

import java.time.LocalDate;

public class Card {
    private int card_number;
    private LocalDate expiry_date;
    private int cvv;
    private boolean is_active;
    private BankAccount account;

    public int get_card_number() {
        return card_number;
    }
    public LocalDate get_expiry_date() {
        return expiry_date;
    }
    public int get_cvv() {
        return cvv;
    }
    public boolean is_active() {
        return is_active;
    }
    public BankAccount get_account() {
        return account;
    }
    public void set_card_number(int number) {
        if (String.valueOf(number).length() == 16) {
            card_number = number;
        }
    }
    public void set_expiry(LocalDate date) {
        expiry_date = date;
    }
    public void set_cvv(int cvv_number) {
        if (String.valueOf(cvv_number).length() == 3) {
            cvv = cvv_number;
        }
    }
    public void set_active(boolean active) {
        is_active = active;
    }
    public void set_account(BankAccount acc) {
        account = acc;
    }

    public Card() {
        set_expiry(LocalDate.now().plusMonths(6));
        set_active(true);

    }
    public Card(int number, LocalDate expiry, int cvv, BankAccount acc) {
        set_card_number(number);
        set_expiry(expiry);
        set_cvv(cvv);
        set_account(acc);
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_number=" + card_number +
                ", expiry_date=" + expiry_date +
                ", cvv=" + cvv +
                ", is_active=" + is_active +
                ", account=" + account +
                '}';
    }
}
