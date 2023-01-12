package guru.springframework.brewery.service;

import com.marco.dtocommoninterface.model.BeerDto;
import com.marco.dtocommoninterface.model.BeerStyleEnum;
import guru.springframework.brewery.web.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDto findBeerById(UUID beerId);
}
