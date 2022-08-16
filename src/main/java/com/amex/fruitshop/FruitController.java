package com.amex.fruitshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Normally Would be in an API package
@RestController
@RequestMapping("/fruitshop")
class FruitController {

    @Autowired
    FruitRepo fRepo;


    @GetMapping("/FruitOrders")
    public ResponseEntity<List<FruitOrder>> getAll() {
        try {
            List<FruitOrder> FruitOrders = new ArrayList<FruitOrder>();

            fRepo.findAll().forEach(FruitOrders::add);

            if (FruitOrders.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(FruitOrders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitOrder> getById(@PathVariable("id") Long id) {
        Optional<FruitOrder> fruitOrderOptional = fRepo.findById(id);

        if (fruitOrderOptional.isPresent()) {
            return new ResponseEntity<>(fruitOrderOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createFruitOrder")
    public ResponseEntity<FruitOrder> createFruitOrder(@RequestBody FruitOrder FruitOrder) {
        FruitOrder fo = fRepo.save(FruitOrder);
        if (fo == null) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(fo, HttpStatus.CREATED);
    }
}