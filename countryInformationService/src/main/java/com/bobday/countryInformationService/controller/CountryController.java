package com.bobday.countryInformationService.controller;

import com.bobday.countryInformationService.model.entity.Country;
import com.bobday.countryInformationService.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/cis/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        Country country = countryService.getCountryByName(name);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/domain")
    public ResponseEntity<Country> getCountryByDomains(@RequestParam String domain) {
        Country country = countryService.getCountryByDomain(domain);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/reload")
    public ResponseEntity<String> reloadCountries() {
        countryService.reloadCountries();
        String message = "Countries reloaded";
        log.debug(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(value = "/reload/url")
    public ResponseEntity<String> reloadCountries(@RequestParam String url) throws Exception {
        try {
            countryService.reloadCountries(new URL(url));
            String message = "Countries reloaded";
            log.debug(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (MalformedURLException e) {
            log.error("URL error");
            throw new Exception("URL error");
        }
    }
}
