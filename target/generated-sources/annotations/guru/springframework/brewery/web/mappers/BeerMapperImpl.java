package guru.springframework.brewery.web.mappers;

import com.marco.dtocommoninterface.model.BeerDto;
import com.marco.dtocommoninterface.model.BeerStyleEnum;
import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.domain.BeerInventory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-23T09:18:42+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Private Build)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDto.BeerDtoBuilder beerDto = BeerDto.builder();

        return beerDto.build();
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        UUID id = null;
        Long version = null;
        Timestamp createdDate = null;
        Timestamp lastModifiedDate = null;
        String beerName = null;
        BeerStyleEnum beerStyle = null;
        Long upc = null;
        BigDecimal price = null;

        id = beerDto.getId();
        if ( beerDto.getVersion() != null ) {
            version = beerDto.getVersion().longValue();
        }
        createdDate = dateMapper.asTimestamp( beerDto.getCreatedDate() );
        lastModifiedDate = dateMapper.asTimestamp( beerDto.getLastModifiedDate() );
        beerName = beerDto.getBeerName();
        beerStyle = beerDto.getBeerStyle();
        if ( beerDto.getUpc() != null ) {
            upc = Long.parseLong( beerDto.getUpc() );
        }
        price = beerDto.getPrice();

        Integer minOnHand = null;
        Integer quantityToBrew = null;
        Set<BeerInventory> beerInventory = null;

        Beer beer = new Beer( id, version, createdDate, lastModifiedDate, beerName, beerStyle, upc, minOnHand, quantityToBrew, price, beerInventory );

        return beer;
    }
}
