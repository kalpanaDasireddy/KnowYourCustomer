package com.psl.kyc.model.customer;

import com.googlecode.jmapper.annotations.JGlobalMap;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
@ApiModel
public class CustomerDto {
    private String id;
    private String forename;
    private String surname;
    private LocalDate dateOfBirth;
    private List<String> accounts;
}
