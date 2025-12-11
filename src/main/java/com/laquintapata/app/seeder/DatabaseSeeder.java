package com.laquintapata.app.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.entity.Origin;
import com.laquintapata.app.repository.AxisRepository;
import com.laquintapata.app.repository.OriginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    private final AxisRepository axisRepository;
    private final OriginRepository originRepository;

    @Override
    public void run(String... args) {

        if (axisRepository.count() == 0) {

            axisRepository.save(Axis.builder().type("Autobiografia").build());
            axisRepository.save(Axis.builder().type("Objetos").build());
            axisRepository.save(Axis.builder().type("Discriminación por origen").build());
            axisRepository.save(Axis.builder().type("Discriminación por genero").build());
            axisRepository.save(Axis.builder().type("Resistencia").build());

            log.info("Ejes insertados con succeso.");
        } else {
            log.info("La tabla Axes ya tiene datos. El seeder no será ejecutado");
        }

        
        if (originRepository.count() == 0) {

            originRepository.save(Origin.builder().country("Angola").build());
            originRepository.save(Origin.builder().country("Argentina").build());
            originRepository.save(Origin.builder().country("Bolivia").build());
            originRepository.save(Origin.builder().country("Brasil").build());
            originRepository.save(Origin.builder().country("Catalunya").build());
            originRepository.save(Origin.builder().country("Chile").build());
            originRepository.save(Origin.builder().country("Colombia").build());
            originRepository.save(Origin.builder().country("Ecuador").build());
            originRepository.save(Origin.builder().country("España").build());
            originRepository.save(Origin.builder().country("India").build());
            originRepository.save(Origin.builder().country("Italia").build());
            originRepository.save(Origin.builder().country("Marruecos").build());
            originRepository.save(Origin.builder().country("Mexico").build());
            originRepository.save(Origin.builder().country("Pakistan").build());
            originRepository.save(Origin.builder().country("Peru").build());
            originRepository.save(Origin.builder().country("Senegal").build());
            originRepository.save(Origin.builder().country("Somalia").build());
            originRepository.save(Origin.builder().country("Togo").build());
            originRepository.save(Origin.builder().country("Uruguay").build());
            originRepository.save(Origin.builder().country("Venezuela").build());


            log.info("Paises insertados correctamente.");
        } else {
            log.info("La tabla Countries ya tiene datos. El seeder no será ejecutado");
        }
    }
}
