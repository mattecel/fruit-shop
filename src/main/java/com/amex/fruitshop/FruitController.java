package com.amex.fruitshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Normally Would be in an API package
@RestController
@RequestMapping("/fruitshop")
class FruitController {

    @PostMapping("/createFruitOrder")
    public ResponseEntity<FruitOrder> create(@RequestBody FruitOrder fOrder) {
        try {
            // Would normally save the order into the repo here but no need for step 1
            fOrder.printFruitOrder();
            return new ResponseEntity<>(fOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}