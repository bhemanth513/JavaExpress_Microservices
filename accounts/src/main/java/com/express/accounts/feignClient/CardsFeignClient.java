package com.express.accounts.feignClient;


import com.express.accounts.dto.CardDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Cards",fallback = CardsFallback.class)
@LoadBalancerClient("Cards")
public interface CardsFeignClient {
    @GetMapping(value = "javaExpress/cards/getCard/{mobileNumber}",produces = "application/json")
    public CardDto getCardDetails(@PathVariable("mobileNumber") String mobileNumber);
}
