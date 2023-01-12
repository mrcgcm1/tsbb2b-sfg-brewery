package guru.springframework.brewery.events;

import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.OrderStatusEnum;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BeerOrderStatusChangeEvent extends ApplicationEvent {

    private final OrderStatusEnum previousStatus;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BeerOrderStatusChangeEvent(BeerOrder source, OrderStatusEnum previousStatus ) {
        super(source);
        this.previousStatus = previousStatus;
    }




}
