package guru.springframework.brewery.web.mappers;

import com.marco.dtocommoninterface.model.order.BeerOrderDto;
import com.marco.dtocommoninterface.model.order.BeerOrderLineDto;
import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.BeerOrderLine;
import guru.springframework.brewery.domain.Customer;
import guru.springframework.brewery.domain.OrderStatusEnum;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
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
public class BeerOrderMapperImpl implements BeerOrderMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }

        BeerOrderDto.BeerOrderDtoBuilder beerOrderDto = BeerOrderDto.builder();

        return beerOrderDto.build();
    }

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        UUID id = null;
        Long version = null;
        Timestamp createdDate = null;
        Timestamp lastModifiedDate = null;
        String customerRef = null;
        Set<BeerOrderLine> beerOrderLines = null;
        OrderStatusEnum orderStatus = null;
        String orderStatusCallbackUrl = null;

        id = dto.getId();
        if ( dto.getVersion() != null ) {
            version = dto.getVersion().longValue();
        }
        createdDate = dateMapper.asTimestamp( dto.getCreatedDate() );
        lastModifiedDate = dateMapper.asTimestamp( dto.getLastModifiedDate() );
        customerRef = dto.getCustomerRef();
        beerOrderLines = beerOrderLineDtoListToBeerOrderLineSet( dto.getBeerOrderLines() );
        if ( dto.getOrderStatus() != null ) {
            orderStatus = Enum.valueOf( OrderStatusEnum.class, dto.getOrderStatus() );
        }
        orderStatusCallbackUrl = dto.getOrderStatusCallbackUrl();

        Customer customer = null;

        BeerOrder beerOrder = new BeerOrder( id, version, createdDate, lastModifiedDate, customerRef, customer, beerOrderLines, orderStatus, orderStatusCallbackUrl );

        return beerOrder;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDto.BeerOrderLineDtoBuilder beerOrderLineDto = BeerOrderLineDto.builder();

        return beerOrderLineDto.build();
    }

    protected Set<BeerOrderLine> beerOrderLineDtoListToBeerOrderLineSet(List<BeerOrderLineDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<BeerOrderLine> set = new LinkedHashSet<BeerOrderLine>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( BeerOrderLineDto beerOrderLineDto : list ) {
            set.add( dtoToBeerOrder( beerOrderLineDto ) );
        }

        return set;
    }
}
