package guru.springframework.brewery.events;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.OrderStatusEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(WireMockExtension.class)
class BeerOrderStatusChangeEventListenerTest {

    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    private static final String BASE_REF = "http://localhost:";

    private BeerOrderStatusChangeEventListener listener;

    @BeforeEach
    void setup (){

        RestTemplateBuilder builder = new RestTemplateBuilder();

        listener = new BeerOrderStatusChangeEventListener(builder);
        wireMockServer.start();
    }

    @Test
    void listen(){

        wireMockServer.stubFor(post("/update").willReturn(ok()));

        String orderStatusCallbackUrl = BASE_REF + wireMockServer.port() + "/update";

        BeerOrder beerOrder = BeerOrder.builder()
                .version(1L)
                .orderStatus(OrderStatusEnum.READY)
                .orderStatusCallbackUrl(orderStatusCallbackUrl)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        BeerOrderStatusChangeEvent event = new BeerOrderStatusChangeEvent(beerOrder, OrderStatusEnum.NEW);

        listener.listen(event);

 //       verify(1, postRequestedFor(urlEqualTo("/update")));
        UrlPattern urlPattern = urlEqualTo("/update");
        verify(1, postRequestedFor(urlPattern));


    }
    @AfterEach
    void after(){
        wireMockServer.stop();
    }
}