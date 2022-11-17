package com.example.appcarscompanies.Controller;

import com.example.appcarscompanies.Entity.Company;
import com.example.appcarscompanies.Service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@Api(tags = "Company", value = "Service Web RESTFul de Companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Comapnies", notes = "Métodos para listar todos los companies")
    @ApiResponses({
            @ApiResponse(code= 201, message = "Companies encontrados"),
            @ApiResponse(code=404, message = "Companies no encontrados")
    })
    public ResponseEntity<List<Company>> findAll(){
        try {
            List<Company> companies = companyService.getAll();
            if (companies.size() > 0)
                return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
            else
                return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity<List<Company>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Company por Id", notes = "Métodos para encontrar un company por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company encontrado"),
            @ApiResponse(code = 404, message = "Company no encontrado")
    })
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        try {
            Optional<Company> company = companyService.getById(id);
            if (!company.isPresent())
                return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Company>(company.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByName/{name}")
    @ApiOperation(value = "Buscar Company por lastname", notes = "Métodos para encontrar un company por su respectivo name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company encontrados"),
            @ApiResponse(code = 404, message = "Company no encontrados")
    })
    public ResponseEntity<List<Company>> findByName(@PathVariable("name") String name) {
        try {
            List<Company> companies = companyService.findByName(name);
            if (companies.size() > 0)
                return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
            else
                return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Company>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("searchByCity/{city}")
    @ApiOperation(value = "Buscar Company por city", notes = "Métodos para encontrar un Company por su respectivo city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company encontrados"),
            @ApiResponse(code = 404, message = "Company no encontrados")
    })
    public ResponseEntity<List<Company>> findByCity(@PathVariable("city") String city) {
        try {
            List<Company> companies = companyService.findByCity(city);
            if (companies.size() > 0)
                return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
            else
                return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Company>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Companies", notes = "Método que registra companies en BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company creado"),
            @ApiResponse(code = 404, message = "Company no creado")
    })
    public ResponseEntity<Company> insertCompany(@Valid @RequestBody Company company) {
        try {
            Company companyNew = companyService.save(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyNew);
        } catch (Exception e) {
            return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de companies", notes = "Metodo que actualiza los datos de Companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Company actualizados"),
            @ApiResponse(code = 404, message = "Company no encontrado")
    })
    public ResponseEntity<Company> updateCompany(
            @PathVariable("id") Long id, @Valid @RequestBody Company company) {
        try {
            Optional<Company> companyUp = companyService.getById(id);
            if (!companyUp.isPresent())
                return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
            company.setId(id);
            companyService.save(company);
            return new ResponseEntity<Company>(company, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de companies", notes = "Metodo que elimina los datos de Companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Company eliminados"),
            @ApiResponse(code = 404, message = "Company no encontrado")
    })
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id) {
        try {
            Optional<Company> companyDelete = companyService.getById(id);
            if (!companyDelete.isPresent())
                return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
            companyService.delete(id);
            return new ResponseEntity<Company>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
