package org.example.thithu123.Controller;

import jakarta.validation.Valid;
import org.example.thithu123.model.Transactions;
import org.example.thithu123.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/rest")
public class TransactionsController {
    @Autowired
    TransactionsService transactionsService;
    @GetMapping("list")
    public List<Transactions> list() {
        return transactionsService.getAll();
    }
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid Transactions transactions, BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            if(error != null){
                return ResponseEntity.badRequest().body(error.getDefaultMessage());
            }
        }
        return ResponseEntity.ok(transactionsService.save(transactions));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Transactions transactions, BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            if(error != null){
                return ResponseEntity.badRequest().body(error.getDefaultMessage());
            }
        }
        return ResponseEntity.ok(transactionsService.update(id,transactions));
    }
    @GetMapping("listpage")
    Page<Transactions> listpage(Pageable pageable) {
        return transactionsService.getpage(pageable);
    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionsService.delete(id);
        return "done";
    }
}
