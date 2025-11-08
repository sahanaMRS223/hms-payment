
package com.hms.payment.service;

import com.hms.payment.model.Payment;
import com.hms.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> listAll() {
        return repository.findAll();
    }

    public Optional<Payment> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Payment createPayment(Payment p) {
        // idempotency check
        if (p.getIdempotencyKey() != null) {
            Optional<Payment> existing = repository.findByIdempotencyKey(p.getIdempotencyKey());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        p.setStatus("PAID");
        p.setPaidAt(LocalDateTime.now());
        return repository.save(p);
    }
}
