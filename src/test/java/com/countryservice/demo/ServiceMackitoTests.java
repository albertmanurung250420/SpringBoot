package com.countryservice.demo;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMackitoTests.class})
public class ServiceMackitoTests {
    @Mock
    CountryRepository countryrep;
    @InjectMocks
    CountryService countryService;

    public List<Country> mycountries;

    @Test
    @Order(1)
    public void test_getAllCountries(){
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia", "Medan"));
        mycountries.add(new Country(2, "Thailand", "Bangkok"));

        when(countryrep.findAll()).thenReturn(mycountries);
        assertEquals(2, countryService.getAllCountries().size());
    }

    @Test
    @Order(2)
    public void test_getAllCountryByID(){
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia", "Medan"));
        mycountries.add(new Country(2, "Thailand", "Bangkok"));
        int  countryID = 1;

        when(countryrep.findAll()).thenReturn(mycountries); //Mocking
        assertEquals(countryID, countryService.getCountrybyID(countryID).getId());
    }

    @Test
    @Order(3)
    public void test_getAllCountryByName(){
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia", "Medan"));
        mycountries.add(new Country(2, "Thailand", "Bangkok"));
        String  countryName = "Indonesia";

        when(countryrep.findAll()).thenReturn(mycountries); //Mocking
        assertEquals(countryName, countryService.getCountrybyName(countryName).getCountryName());
    }

    @Test
    @Order(4)
    public void test_addCountry(){
        Country country = new Country(8, "Germany", "Berlin");
        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    public void test_updateCountry(){
        Country country = new Country(8, "Belanda","Amsterdam");
        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    public void test_deleteCountry(){
        Country country = new Country(8, "Belanda", "Amsterdam");
        countryService.deleteCountry(country);
        verify(countryrep,times(1)).delete(country);
    }
}
