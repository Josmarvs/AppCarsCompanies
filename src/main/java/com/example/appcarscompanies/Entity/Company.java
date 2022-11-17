package com.example.appcarscompanies.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Company.findByCompanyName", query = "select c from Company c where c.name = ?1")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "ruc", nullable = true, length = 12)
    private long ruc;
    @Column(name = "address", nullable = true, length = 50)
    private String address;
    @Column(name = "contry", nullable = true, length = 50)
    private String contry;
    @Column(name = "city", nullable = true, length = 50)
    private String city;
    @Column(name = "email", nullable = true, length = 50)
    private String email;

}
