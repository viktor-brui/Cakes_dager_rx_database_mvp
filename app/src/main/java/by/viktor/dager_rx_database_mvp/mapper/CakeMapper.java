package by.viktor.dager_rx_database_mvp.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.viktor.dager_rx_database_mvp.mvp.model.Cake;
import by.viktor.dager_rx_database_mvp.mvp.model.CakesResponse;
import by.viktor.dager_rx_database_mvp.mvp.model.CakesResponseCakes;
import by.viktor.dager_rx_database_mvp.mvp.model.Storage;

public class CakeMapper {

    @Inject
    public CakeMapper() {

    }

    public List<Cake> mapCakes(Storage storage, CakesResponse response){
        List<Cake> cakeList = new ArrayList<>();

        if (response != null) {
            CakesResponseCakes[] responseCakes = response.getCakes();
            if (responseCakes != null) {
                for (CakesResponseCakes cake : responseCakes) {
                    Cake myCake = new Cake();
                    myCake.setId(cake.getId());
                    myCake.setTitle(cake.getTitle());
                    myCake.setPreviewDescription(cake.getPreviewDescription());
                    myCake.setDetailDescription(cake.getDetailDescription());
                    myCake.setImageUrl(cake.getImage());
                    storage.addCake(myCake);
                    cakeList.add(myCake);
                }
            }
        }
        return cakeList;
    }
}
