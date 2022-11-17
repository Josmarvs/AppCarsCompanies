package com.example.appcarscompanies.Service;

import com.example.appcarscompanies.Entity.Car;

import java.util.List;

public interface ICarService extends CrudService<Car>{
    public List<Car> findByName(String name) throws Exception;
    public List<Car> findByModel(String model) throws Exception;

}
