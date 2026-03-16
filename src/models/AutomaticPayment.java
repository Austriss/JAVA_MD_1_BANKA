package models;

import models.enums.AutomaticPayementScheduleType;

import java.time.LocalDate;

public class AutomaticPayment extends Transaction {
    private String title;
    private AutomaticPayementScheduleType schedule_type;
    private LocalDate next_payment_date;
    private boolean is_active;

    public String get_title() {
        return title;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public AutomaticPayementScheduleType get_schedule_type() {
        return schedule_type;
    }

    public void set_schedule_type(AutomaticPayementScheduleType schedule_type) {
        this.schedule_type = schedule_type;
    }

    public LocalDate get_next_payment_date() {
        return next_payment_date;
    }

    public void set_next_payment_date(LocalDate next_payment_date) {
        this.next_payment_date = next_payment_date;
    }

    public boolean is_active() {
        return is_active;
    }

    public void set_is_active(boolean is_active) {
        this.is_active = is_active;
    }

    public AutomaticPayment(String title, AutomaticPayementScheduleType schedule_type, LocalDate date) {
        set_title(title);
        set_schedule_type(schedule_type);
        set_date(date);
        set_is_active(true);
    }
}
