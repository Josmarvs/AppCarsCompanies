package com.example.appcarscompanies.Repository;

import com.example.appcarscompanies.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
    public List<Company> findByName(String name);
    public List<Company> findByCity(String city);
}
