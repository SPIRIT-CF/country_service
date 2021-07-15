package com.bobday.countryInformationService.controller;

import com.bobday.countryInformationService.model.entity.Country;
import com.bobday.countryInformationService.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/cis/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{name}")
    public Country getCountryByName(@PathVariable String name) {
        return countryService.getCountryByName(name);
    }

/*    @GetMapping(value = "/domain")
    public List<Country> getCountryByDomains(@RequestParam List<String> domains) {
        return countryService.getCountriesByDomains(domains);
    }*/

    @PutMapping(value = "/reload")
    public boolean reloadCountries() {
            countryService.reloadCountries();
            log.debug("Countries reloaded");
            return true;
    }

    @PutMapping(value = "/reload/url")
    public boolean reloadCountries(@RequestParam String url) throws Exception {
        try {
            countryService.reloadCountries(new URL(url));
            log.debug("Countries reloaded");
            return true;
        } catch (MalformedURLException e) {
            log.error("URL error");
            throw new Exception("URL error");
        }
    }
}
