package com.bobday.countryInformationService.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String symbol;
}
