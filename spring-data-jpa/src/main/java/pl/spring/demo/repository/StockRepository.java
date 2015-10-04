package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import pl.spring.demo.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    

}