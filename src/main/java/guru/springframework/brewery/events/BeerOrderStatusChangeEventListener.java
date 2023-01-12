package guru.springframework.brewery.events;

import com.marco.dtocommoninterface.model.order.OrderStatusUpdate;
import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.web.mappers.DateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class BeerOrderStatusChangeEventListener {

    private final RestTemplate restTemplate;

    private final DateMapper dateMapper = new DateMapper();

    public BeerOrderStatusChangeEventListener(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Async
    @EventListener
    public void listen(BeerOrderStatusChangeEvent event){
        System.out.println("I got an order status change event");
        System.out.println(event);

        BeerOrder beerOrder = (BeerOrder) event.getSource();

        Long version = beerOrder.getVersion() != null ? beerOrder.getVersion() : null;
        OrderStatusUpdate update = OrderStatusUpdate.builder()
                .id(beerOrder.getId())
                .orderId(beerOrder.getId())
                .version(version.intValue())
                .createdDate(dateMapper.asOffsetDateTime(beerOrder.getCreatedDate()))
                .lastModifiedDate(dateMapper.asOffsetDateTime(beerOrder.getLastModifiedDate()))
                .orderStatus(beerOrder.getOrderStatus().toString())
                .customerRef(beerOrder.getCustomerRef())
                .build();


        try {
            log.debug("Rest callback to url");

            this.restTemplate.postForObject(beerOrder.getOrderStatusCallbackUrl(), update, String.class);
        }catch (Throwable e){
            log.debug("Rest callback to url failed " + e.getLocalizedMessage());

        }
    }
}
