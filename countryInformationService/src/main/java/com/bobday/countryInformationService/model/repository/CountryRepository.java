package com.bobday.countryInformationService.model.repository;

import com.bobday.countryInformationService.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country getCountryByNameIgnoreCase(String name);
    Country findCountryByTopLevelDomain(String domain);

}
