
package com.bobday.countryInformationService.service;

import com.bobday.countryInformationService.model.entity.Country;
import com.bobday.countryInformationService.model.repository.CountryRepository;
import com.bobday.countryInformationService.service.upload.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class CountryService {

    private final Uploader<URL> urlUploader;
    private final CountryRepository repository;

    @Autowired
    public CountryService(Uploader<URL> urlUploader, CountryRepository repository) {
        this.urlUploader = urlUploader;
        this.repository = repository;
    }

    public Country getCountryByName(String name) {
        return repository.getCountryByNameIgnoreCase(name);
    }

    public Country getCountryByDomain(String domain) {
        return repository.findCountryByTopLevelDomain(domain);
    }

    public void reloadCountries() {
        repository.deleteAll();
        urlUploader.upload();
    }

    public void reloadCountries(URL source) {
        repository.deleteAll();
        urlUploader.upload(source);
    }
}

