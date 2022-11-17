package com.example.appcarscompanies.Repository;

import com.example.appcarscompanies.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepository extends JpaRepository<Car, Long> {
    public List<Car> findByName(String name);
    public List<Car> findByModel(String model);

}
