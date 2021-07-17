package com.bobday.countryInformationService.service.upload;

import com.bobday.countryInformationService.model.entity.Country;
import com.bobday.countryInformationService.model.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CountryUploader implements Uploader<URL> {

    @Value("${origin.address}")
    private String originUrl;
    private final CountryRepository countryRepository;

    @Autowired
    public CountryUploader(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void upload() {
       uploadFromOriginSource();
    }

    @Override
    public void upload(URL sourceURL) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Country> countries = null;
        try {
            countries = objectMapper.readValue(sourceURL, new TypeReference<>() {});
        } catch (IOException e) {
            log.error("Some problem during country request for {}, error: {}", sourceURL, e.getMessage());
        }
        countryRepository.saveAll(Objects.requireNonNull(countries));
        log.debug("Countries loaded and saved to the database");
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void uploadFromOriginSource() {
        try {
            upload(new URL(originUrl));
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), "URL error");
        }
    }
}