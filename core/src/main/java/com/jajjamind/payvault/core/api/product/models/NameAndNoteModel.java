package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 15/02/2021
 * 14:24
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.Data
public class NameAndNoteModel {
    private Integer id;
    private String name;
    private String note;
}
