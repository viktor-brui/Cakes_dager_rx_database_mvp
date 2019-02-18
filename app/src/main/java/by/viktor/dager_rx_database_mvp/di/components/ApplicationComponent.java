package by.viktor.dager_rx_database_mvp.di.components;

import android.content.Context;

import javax.inject.Singleton;

import by.viktor.dager_rx_database_mvp.di.module.ApplicationModule;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();

}
