package com.countryservice.demo;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ControllerMackitotest.class})
public class ControllerMackitotest {
    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    List<Country> myCountries;
    Country country;

    @Test
    @Order(1)
    public void  test_getAllCountries(){
        myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1, "Indonesia", "Medan"));
        myCountries.add(new Country(2, "Thailand", "Bangkok"));

        when(countryService.getAllCountries()).thenReturn(myCountries);
        ResponseEntity<List<Country>> res=countryController.getCountries();
        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(2, res.getBody().size());
    }

    @Test
    @Order(2)
    public void test_getCountrybyID(){
        country = new Country(2, "Thailand", "Bangkok");
        int countryID = 2;

        when(countryService.getCountrybyID(countryID)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getCountryById(countryID);

//        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(countryID, res.getBody().getId());
    }

    @Test
    @Order(3)
    public void test_getCountrybyName(){
        country =  new Country (2, "Thailand", "Bangkok");
        String countryName="Thailand";

        when(countryService.getCountrybyName(countryName)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getCountryByName(countryName);

//        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(countryName, res.getBody().getCountryName());
    }

    @Test
    @Order(4)
    public void test_AddCountry(){
        country = new Country(9, "China", "Beijing");
        when(countryService.addCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.addCountry(country);

//        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(country, res.getBody());
    }

    @Test
    @Order(5)
    public void  test_UpdateCountry(){
        country = new Country(9, "Belanda", "Amsterdamm");
        int countryID=9;

        when(countryService.getCountrybyID(countryID)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.updateCountry(countryID, country);

        assertEquals(9, res.getBody().getId());
        assertEquals("Belanda", res.getBody().getCountryName());
//        assertEquals("Amsterdam", res.getBody().getCountryCapital());
    }

    @Test
    @Order(6)
    public void test_DeleteCountry(){
        country = new Country(9, "Belanda", "Amsterdam");
        int countryID = 9;

        when(countryService.getCountrybyID(countryID)).thenReturn(country);
        ResponseEntity<Country> res = countryController.deleteCountry(countryID);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
