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
    public void update_next_payment_date() {
        if (get_schedule_type() == AutomaticPayementScheduleType.Daily) {
            set_next_payment_date(LocalDate.now().plusDays(1));
        } else if (get_schedule_type() == AutomaticPayementScheduleType.Weekly) {
            set_next_payment_date(LocalDate.now().plusDays(7));
        } else if (get_schedule_type() == AutomaticPayementScheduleType.Monthly) {
            set_next_payment_date(LocalDate.now().plusMonths(1));
        }
    }
    public AutomaticPayment(String title, AutomaticPayementScheduleType schedule_type) {
        set_id();
        set_title(title);
        set_schedule_type(schedule_type);
        if (schedule_type == AutomaticPayementScheduleType.Daily) {
            set_next_payment_date(LocalDate.now().plusDays(1));
        } else if (schedule_type == AutomaticPayementScheduleType.Weekly) {
            set_next_payment_date(LocalDate.now().plusDays(7));
        } else if (schedule_type == AutomaticPayementScheduleType.Monthly) {
            set_next_payment_date(LocalDate.now().plusMonths(1));
        }
        set_date(LocalDate.now());
        set_is_active(true);
    }

    @Override
    public String toString() {
        return "AutomaticPayment{" +
                "title='" + title + '\'' +
                ", schedule_type=" + schedule_type +
                ", next_payment_date=" + next_payment_date +
                ", is_active=" + is_active +
                '}';
    }
}
