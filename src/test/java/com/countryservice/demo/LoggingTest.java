package com.countryservice.demo;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class LoggingTest {
    @Mock
    CountryRepository countryrep;

    @InjectMocks
    CountryService  countryService;
    int i;

    @Test
    void testLogging(){
        log.info("Logging Info");
        log.warn("Logging Warning");
        log.error("Logging Error");
    }

//    Logging Test Add
    @Test
    void testLongLogging(){
        for(i=0; i<10; i++){
            List<Country> mycountries=new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia", "Medan"));
            mycountries.add(new Country(2, "Thailand", "Bangkok"));

            when(countryrep.findAll()).thenReturn(mycountries);
            log.warn("Return ->{}", i);
            assertEquals(2, countryService.getAllCountries().size());
        }
    }

    @Test
    public void test_getCountrybyID() {
        for (i = 0; i < 10; i++) {
            List<Country> mycountries = new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia", "Medan"));
            mycountries.add(new Country(2, "Thailand", "Bangkok"));
            int countryID = 1;

            when(countryrep.findAll()).thenReturn(mycountries); //Mocking
            log.warn("Return -> {}", i);
            assertEquals(countryID, countryService.getCountrybyID(countryID).getId());
        }
    }

    @Test
    public void test_getCountryByName(){
        for(i=0; i<10; i++){
            List<Country> mycountries=new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia", "Medan"));
            mycountries.add(new Country(2, "Thailand", "Bangkok"));
            String  countryName = "Indonesia";

            when(countryrep.findAll()).thenReturn(mycountries); //Mocking
            log.warn("Return -> {}", i);
            assertEquals(countryName, countryService.getCountrybyName(countryName).getCountryName());
        }
    }

    @Test
    public void test_AddCountry() {
        for (i = 0; i < 100; i++) {
            Country country = new Country(8, "Germany", "Berlin");
            when(countryrep.save(country)).thenReturn(country);
            assertEquals(country, countryService.addCountry(country));
            log.warn("Return -> {}", i);
        }
    }

    @Test
    public  void  test_UpdateCountry(){
        for(i=0; i<100; i++){
            Country country = new Country(8, "Belanda","Amsterdam");
            when(countryrep.save(country)).thenReturn(country);
            assertEquals(country, countryService.updateCountry(country));
            log.warn("Return -> {}", i);
        }
    }

    @Test
    public void test_DeleteCountry(){
        for (i=0; i<100; i++){
            Country country = new Country(8, "Belanda", "Amsterdam");
            countryService.deleteCountry(country);
            verify(countryrep,times(1)).delete(country);
            log.warn("Return -> {}", i);
        }
    }
}
