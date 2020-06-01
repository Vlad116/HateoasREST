package ru.itis.equeue.services;

import reactor.core.publisher.Flux;
import ru.itis.equeue.entries.CompaniesData;

public interface CompaniesService {
    Flux<CompaniesData> getAll();
}
