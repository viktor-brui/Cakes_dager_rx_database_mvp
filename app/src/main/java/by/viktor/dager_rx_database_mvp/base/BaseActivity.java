package by.viktor.dager_rx_database_mvp.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import by.viktor.dager_rx_database_mvp.application.CakeApplication;
import by.viktor.dager_rx_database_mvp.di.components.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //to be used child activities
        resolveDaggerDependency();

    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this).unbind();
        super.onDestroy();
    }

    protected void resolveDaggerDependency(){
    }

    protected void showDialog(String message){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((CakeApplication) getApplication()).getApplicationComponent();
    }

    protected abstract int getContentView();
}
