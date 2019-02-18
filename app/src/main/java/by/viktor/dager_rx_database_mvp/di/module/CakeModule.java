package by.viktor.dager_rx_database_mvp.di.module;

import by.viktor.dager_rx_database_mvp.api.CakeApiService;
import by.viktor.dager_rx_database_mvp.di.scope.PerActivity;
import by.viktor.dager_rx_database_mvp.mvp.view.MainView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CakeModule {

    private MainView mView;

    public CakeModule(MainView view){
        mView = view;
    }

    @PerActivity
    @Provides
    CakeApiService provideApiService(Retrofit retrofit){
        return retrofit.create(CakeApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }
}
