package com.example.appcarscompanies.Service.impl;

import com.example.appcarscompanies.Entity.Car;
import com.example.appcarscompanies.Repository.ICarRepository;
import com.example.appcarscompanies.Service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    @Override
    @Transactional
    public Car save(Car car) throws Exception {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAll() throws Exception {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getById(Long id) throws Exception {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findByName(String name) throws Exception {
        return carRepository.findByName(name);
    }

    @Override
    public List<Car> findByModel(String model) throws Exception {
        return carRepository.findByModel(model);
    }
}
