package com.example.appcarscompanies.Service;

import com.example.appcarscompanies.Entity.Company;

import java.util.List;

public interface ICompanyService extends CrudService<Company>{
    public List<Company> findByName(String name) throws Exception;
    public List<Company> findByCity(String city) throws Exception;
}
