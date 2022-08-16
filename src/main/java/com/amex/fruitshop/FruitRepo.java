package com.amex.fruitshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Again, normally would be in its own package
@Repository
public interface FruitRepo extends JpaRepository<FruitOrder, Long> {
    
}
