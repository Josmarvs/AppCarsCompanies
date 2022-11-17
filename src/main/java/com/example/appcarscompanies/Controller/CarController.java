package com.example.appcarscompanies.Controller;

import com.example.appcarscompanies.Entity.Car;
import com.example.appcarscompanies.Entity.Company;
import com.example.appcarscompanies.Service.ICarService;
import com.example.appcarscompanies.Service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@Api(tags = "Car", value = "Service Web RESTFul de Cars")
public class CarController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ICarService carService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Cars", notes = "Método para listar todos los cars")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cars encontrados"),
            @ApiResponse(code = 404, message = "Cars no encontrados")
    })
    public ResponseEntity<List<Car>> findAllCar() {
        try {
            List<Car> cars = carService.getAll();
            if (cars.size() > 0)
                return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
            else
                return new ResponseEntity<List<Car>>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<List<Car>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Car por Id", notes = "Método para listar un car por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cars encontrados"),
            @ApiResponse(code = 404, message = "Cars no encontrados")
    })
    public ResponseEntity<Car> findCarById(@PathVariable("id") Long id) {
        try {
            Optional<Car> car = carService.getById(id);
            if (!car.isPresent())
                return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Car>(car.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Car de un Customers", notes = "Método que registra un car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Car creado"),
            @ApiResponse(code = 404, message = "Car no creado")
    })
    public ResponseEntity<Car> insertCar(@PathVariable("id") Long id, @Valid @RequestBody Car car) {
        try {
            Optional<Company> company = companyService.getById(id);
            if (company.isPresent()) {
                car.setCompany(company.get());
                Car carNew = carService.save(car);
                return ResponseEntity.status(HttpStatus.CREATED).body(carNew);
            } else
                return new ResponseEntity<Car>(HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Car", notes = "Metodo que actualiza los datos de Car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Car actualizados"),
            @ApiResponse(code = 404, message = "Car no encontrado")
    })
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @Valid @RequestBody Car car) {
        try {
            Optional<Car> carOld = carService.getById(id);
            if (!carOld.isPresent())
                return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
            else {
                car.setId(id);
                carService.save(car);
                return new ResponseEntity<Car>(car, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Car por Id", notes = "Metodo que elimina los datos de un Car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Car eliminados"),
            @ApiResponse(code = 404, message = "Car no encontrado")
    })
    public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id) {
        try {
            Optional<Car> carDelete = carService.getById(id);
            if (carDelete.isPresent()) {
                carService.delete(id);
                return new ResponseEntity<Car>(HttpStatus.OK);
            } else
                return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
