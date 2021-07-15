
package com.bobday.countryInformationService.service;

import com.bobday.countryInformationService.model.entity.Country;
import com.bobday.countryInformationService.model.repository.CountryRepository;
import com.bobday.countryInformationService.service.upload.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class CountryService {

    private Uploader<URL> urlUploader;
    private CountryRepository repository;

    @Autowired
    public CountryService(Uploader<URL> urlUploader, CountryRepository repository) {
        this.urlUploader = urlUploader;
        this.repository = repository;
    }

    public Country getCountryByName(String name) {
        return repository.getCountryByNameIgnoreCase(name);
    }

/*    public List<Country> getCountriesByDomains(List<String> domains) {
        return repository.findCountriesByTopLevelDomains(domains);
    }*/

    public boolean reloadCountries() {
        repository.deleteAll();
        return urlUploader.upload();
    }

    public boolean reloadCountries(URL source) {
        repository.deleteAll();
        return urlUploader.upload();
    }
}

