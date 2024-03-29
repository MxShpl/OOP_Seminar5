package ru.geekbrains.oop.lesson5.presenters;

import ru.geekbrains.oop.lesson5.models.TableService;
import ru.geekbrains.oop.lesson5.views.BookingView;

import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private  View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void updateReservationTableView(int reservationNo){
        view.showReservationTableResult(reservationNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
            updateReservationTableView(-1);
        }

    }

    @Override
    public void changeOnReservationTable(int oldReservation, Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.changeReservationTable(oldReservation, orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
        }

    }


}
