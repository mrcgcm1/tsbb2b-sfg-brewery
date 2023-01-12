package guru.springframework.brewery.web.mappers;

import com.marco.dtocommoninterface.model.order.BeerOrderDto;
import com.marco.dtocommoninterface.model.order.BeerOrderLineDto;
import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.BeerOrderLine;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerOrderMapper {

    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);


    default BeerOrderLine dtoToBeerOrder(BeerOrderLineDto dto) {
        return BeerOrderLine.builder()
                .orderQuantity(dto.getOrderQuantity())
                .beer(Beer.builder().id(dto.getBeerId()).build())
                .build();
    }
}
