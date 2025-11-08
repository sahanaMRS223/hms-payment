
package com.hms.payment.controller;

import com.hms.payment.model.Payment;
import com.hms.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payment> list(@RequestParam(defaultValue = "0") int skip, @RequestParam(defaultValue = "50") int limit) {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> get(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey, @RequestBody Payment p) {
        if (idempotencyKey != null && p.getIdempotencyKey() == null) {
            p.setIdempotencyKey(idempotencyKey);
        }
        Payment created = service.createPayment(p);
        HttpStatus status = (created.getId() != null) ? HttpStatus.CREATED : HttpStatus.OK;
        return new ResponseEntity<>(created, status);
    }
}
