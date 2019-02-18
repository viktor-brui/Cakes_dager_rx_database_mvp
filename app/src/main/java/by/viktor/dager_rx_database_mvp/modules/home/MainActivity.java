package by.viktor.dager_rx_database_mvp.modules.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindAnim;
import butterknife.BindView;
import by.viktor.dager_rx_database_mvp.R;
import by.viktor.dager_rx_database_mvp.base.BaseActivity;
import by.viktor.dager_rx_database_mvp.di.components.DaggerCakeComponent;
import by.viktor.dager_rx_database_mvp.di.module.CakeModule;
import by.viktor.dager_rx_database_mvp.modules.home.adapter.CakeAdapter;
import by.viktor.dager_rx_database_mvp.mvp.model.Cake;
import by.viktor.dager_rx_database_mvp.mvp.presenter.CakePresenter;
import by.viktor.dager_rx_database_mvp.mvp.view.MainView;
import by.viktor.dager_rx_database_mvp.utilites.NetworkUtils;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.cake_list)protected RecyclerView mCakeList;
    @Inject protected CakePresenter mPresenter;
    private CakeAdapter mCakeAdapter;


    @Override
    public void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();

        if (NetworkUtils.isNetworkAvailable(this)){
            mPresenter.getCakes();
        }else {
            mPresenter.getCakesFromDatabase();
        }

        mPresenter.getCakes();
    }

//mCakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    private void initializeList() {
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCakeAdapter = new CakeAdapter(getLayoutInflater());
        mCakeList.setAdapter(mCakeAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerCakeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .cakeModule(new CakeModule(this))
        .build()
        .inject(this);
    }

    @Override
    public void onCakeLoaded(List<Cake> cakes) {
        mCakeAdapter.addCakes(cakes);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }
}
