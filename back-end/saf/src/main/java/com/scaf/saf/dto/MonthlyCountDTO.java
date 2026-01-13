package com.scaf.saf.dto;

public class MonthlyCountDTO {
    private String mes;
    private Long count;
    
    public MonthlyCountDTO(String mes, Long count) {
        
    }
    
    public String getMes() { return mes; }
    public Long getCount() { return count; }
}
