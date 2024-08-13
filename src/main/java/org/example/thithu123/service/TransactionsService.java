package org.example.thithu123.service;

import org.example.thithu123.model.Currency;
import org.example.thithu123.model.Transactions;
import org.example.thithu123.repository.CurrencyRepository;
import org.example.thithu123.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    public List<Transactions> getAll() {
        return transactionsRepository.findAll();
    }

    public Object save(Transactions transactions) {
        return transactionsRepository.save(transactions);
    }

    public Object update(Long id, Transactions transactions) {
        Transactions listid = transactionsRepository.findById(id).orElseThrow();
        Currency Listid = currencyRepository.findById(transactions.getCurrency().getCurrency_id()).get();
        listid.setAmount(transactions.getAmount());
        listid.setTransaction_date(listid.getTransaction_date());
        listid.setCurrency(Listid);
        return transactionsRepository.save(listid);
    }

    public Page<Transactions> getpage(Pageable pageable) {
        return transactionsRepository.findpage(pageable);
    }


    public void delete(Long id) {
        transactionsRepository.deleteById(id);
    }
}
