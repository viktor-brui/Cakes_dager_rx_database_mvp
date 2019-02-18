package by.viktor.dager_rx_database_mvp.mvp.view;

import java.util.List;

import by.viktor.dager_rx_database_mvp.mvp.model.Cake;

public interface MainView extends BaseView {
    void onCakeLoaded(List<Cake> cakes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
