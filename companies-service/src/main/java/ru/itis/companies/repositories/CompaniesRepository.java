package ru.itis.companies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.companies.models.Company;

public interface CompaniesRepository extends JpaRepository<Company, Long> {
}