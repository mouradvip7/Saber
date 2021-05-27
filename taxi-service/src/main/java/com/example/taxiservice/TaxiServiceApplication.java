package com.example.taxiservice;


import com.example.taxiconfig.config.RedisConfig;
import com.example.taximodel.enums.TaxiStatus;
import com.example.taximodel.enums.TaxiType;
import com.example.taximodel.util.LocationGenerator;
import com.example.taxiservice.listener.TaxiBookingAcceptedEventMessageListener;
import com.example.taxiservice.model.Taxi;
import com.example.taxiservice.repo.TaxiRepository;
import com.example.taxiservice.service.TaxiService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.UUID;

@SpringBootApplication
@Import({RedisConfig.class})
public class TaxiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(TaxiRepository taxiRepository, TaxiService taxiService) {
        return args -> {
            taxiRepository.deleteAll();

            taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.MINI, TaxiStatus.AVAILABLE));
            taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.NANO, TaxiStatus.AVAILABLE));
            taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.VAN, TaxiStatus.AVAILABLE));

            Iterable<Taxi> taxis = taxiRepository.findAll();

            taxis.forEach(t -> {
                taxiService.updateLocation(t.getTaxiId(), LocationGenerator.getLocation(79.865072, 6.927610, 3000)).subscribe();
            });
        };
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, TaxiBookingAcceptedEventMessageListener taxiBookingAcceptedEventMessageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(taxiBookingAcceptedEventMessageListener, new PatternTopic(RedisConfig.ACCEPTED_EVENT_CHANNEL));
        return container;
    }

}
