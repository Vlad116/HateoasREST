package ru.itis.hateoasrestservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrestservice.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
