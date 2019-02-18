package by.viktor.dager_rx_database_mvp.di.components;

import by.viktor.dager_rx_database_mvp.di.module.CakeModule;
import by.viktor.dager_rx_database_mvp.di.scope.PerActivity;
import by.viktor.dager_rx_database_mvp.modules.home.MainActivity;
import dagger.Component;

@PerActivity
@Component(modules = CakeModule.class, dependencies = ApplicationComponent.class)
public interface CakeComponent {

    void inject(MainActivity activity);

}
