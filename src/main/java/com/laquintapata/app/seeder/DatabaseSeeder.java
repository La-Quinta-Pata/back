package com.laquintapata.app.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.repository.AxisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    private final AxisRepository axisRepository;

    @Override
    public void run(String... args) {

        if (axisRepository.count() == 0) {

            axisRepository.save(Axis.builder().type("autobiografia").build());
            axisRepository.save(Axis.builder().type("objetos").build());
            axisRepository.save(Axis.builder().type("discriminación_origen").build());
            axisRepository.save(Axis.builder().type("discriminación_genero").build());
            axisRepository.save(Axis.builder().type("resistencia").build());

            log.info("Ejes insertados con succeso.");
        } else {
            log.info("La tabla Axes ya tiene datos. El seeder no será ejecutado");
        }
    }
}
