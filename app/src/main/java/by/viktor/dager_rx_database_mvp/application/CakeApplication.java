package by.viktor.dager_rx_database_mvp.application;

import android.app.Application;

import by.viktor.dager_rx_database_mvp.di.components.ApplicationComponent;
import by.viktor.dager_rx_database_mvp.di.components.DaggerApplicationComponent;
import by.viktor.dager_rx_database_mvp.di.module.ApplicationModule;

public class CakeApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "https://gist.githubusercontent.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
