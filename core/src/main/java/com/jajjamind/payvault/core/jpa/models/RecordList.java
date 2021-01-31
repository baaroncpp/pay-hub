package com.jajjamind.payvault.core.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author akena
 * 29/01/2021
 * 19:48
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class RecordList<T> {

    private Long recordsTotal;
    private int recordsFiltered;
    private List<T> records;

    public RecordList() {
    }
    public RecordList(Long recordsTotal,List<T> records) {
        this.recordsTotal = recordsTotal;
        this.records = records;
    }
    public RecordList(Long recordsTotal, int recordsFiltered, List<T> records) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.records = records;
    }
}