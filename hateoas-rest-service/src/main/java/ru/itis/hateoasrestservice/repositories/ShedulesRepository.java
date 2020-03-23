package ru.itis.hateoasrestservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrestservice.models.Shedule;

public interface ShedulesRepository extends JpaRepository<Shedule, Long> {
}