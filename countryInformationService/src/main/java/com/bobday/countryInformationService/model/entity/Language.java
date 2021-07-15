package com.bobday.countryInformationService.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties
    private Long id;

    @Column
    private String iso639_1;

    @Column
    private String iso639_2;

    @Column
    private String name;

    @Column
    private String nativeName;

}
