package com.bobday.countryInformationService.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionalBlocs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties
    private Long id;

    @Column
    private String acronym;

    @Column
    private String name;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> otherAcronyms;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> otherNames;

}
