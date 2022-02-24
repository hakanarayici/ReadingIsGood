package com.hakan.arayici.readingisgood.data.dao;

import com.hakan.arayici.readingisgood.data.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<BookEntity> findById(Long id);
}
