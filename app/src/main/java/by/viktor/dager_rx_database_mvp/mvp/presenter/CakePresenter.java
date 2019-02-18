package by.viktor.dager_rx_database_mvp.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import by.viktor.dager_rx_database_mvp.api.CakeApiService;
import by.viktor.dager_rx_database_mvp.base.BasePresenter;
import by.viktor.dager_rx_database_mvp.mapper.CakeMapper;
import by.viktor.dager_rx_database_mvp.mvp.model.Cake;
import by.viktor.dager_rx_database_mvp.mvp.model.CakesResponse;
import by.viktor.dager_rx_database_mvp.mvp.model.Storage;
import by.viktor.dager_rx_database_mvp.mvp.view.MainView;
import rx.Observable;
import rx.Observer;

public class CakePresenter extends BasePresenter<MainView> implements Observer<CakesResponse> {

    @Inject protected CakeApiService mApiService;
    @Inject protected CakeMapper mCakeMapper;
    @Inject protected Storage mStorage;

    @Inject
    public CakePresenter(){

    }

    public void getCakes() {
        getView().onShowDialog("Loading Cakes...");
        Observable<CakesResponse> cakesResponseObservable = mApiService.getCakes();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Cakes loading complete!");

    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading cakes " + e.getMessage());

    }

    @Override
    public void onNext(CakesResponse cakesResponse) {
        List<Cake> cakes = mCakeMapper.mapCakes(mStorage, cakesResponse);
        getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }

    public void getCakesFromDatabase() {
        List<Cake> cakes = mStorage.getSavedCakes();
        getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }
}
