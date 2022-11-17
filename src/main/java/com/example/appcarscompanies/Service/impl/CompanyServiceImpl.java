package com.example.appcarscompanies.Service.impl;

import com.example.appcarscompanies.Entity.Company;
import com.example.appcarscompanies.Repository.ICompanyRepository;
import com.example.appcarscompanies.Service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    @Transactional
    public Company save(Company company) throws Exception {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAll() throws Exception {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) throws Exception {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> findByName(String name) throws Exception {
        return companyRepository.findByName(name);
    }

    @Override
    public List<Company> findByCity(String city) throws Exception {
        return companyRepository.findByCity(city);
    }
}
