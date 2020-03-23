package ru.itis.hateoasrestservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrestservice.models.Company;

public interface CompaniesRepository extends JpaRepository<Company, Long> {
}