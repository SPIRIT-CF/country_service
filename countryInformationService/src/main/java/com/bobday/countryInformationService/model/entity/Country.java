package com.bobday.countryInformationService.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties
    private Long id;

    @Column
    private String name;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> topLevelDomain;

    @Column
    private Long population;

    @Column
    @ElementCollection(targetClass = Double.class)
    private List<Double> latlng;

    @ManyToMany(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Currency> currencies;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Language> languages;

    @Column
    @ElementCollection(targetClass = HashMap.class)
    private Map<String, String> translations;

    @JsonAnyGetter
    public Map<String, String> getTranslations() {
        return translations;
    }

    @Column
    private String flag;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<RegionalBlocs> regionalBlocs;
}

