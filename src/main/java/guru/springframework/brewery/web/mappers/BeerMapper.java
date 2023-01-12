package guru.springframework.brewery.web.mappers;

import com.marco.dtocommoninterface.model.BeerDto;
import guru.springframework.brewery.domain.Beer;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
