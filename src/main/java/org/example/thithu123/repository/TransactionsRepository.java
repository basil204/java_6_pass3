package org.example.thithu123.repository;

import org.example.thithu123.model.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    @Query("Select tr from Transactions tr join fetch tr.currency")
    Page<Transactions> findpage(Pageable pageable);
}
