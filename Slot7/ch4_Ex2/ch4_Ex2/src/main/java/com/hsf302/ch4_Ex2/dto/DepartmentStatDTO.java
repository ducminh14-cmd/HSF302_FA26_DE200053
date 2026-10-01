package com.hsf302.ch4_Ex2.dto;

public record DepartmentStatDTO(String code, String name, Long totalStudents, Double avgGpa) {

    @Override
    public String toString() {
        return String.format("%-3s | %-25s | %2d | %s",
                code, name, totalStudents, avgGpa == null ? "null" : String.format("%.3f", avgGpa));
    }
}