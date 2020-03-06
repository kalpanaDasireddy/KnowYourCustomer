package com.psl.kyc.model.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AccountCreateRequest {
    @NotBlank(message = "Customer Forename Cannot be blank")
    @ApiModelProperty(required = true)
    private String customerForename;

    @NotBlank(message = "Customer Surname Cannot be blank")
    @ApiModelProperty(required = true)
    private String customerSurname;

    @NotNull(message = "Customer DateofBirth Cannot be null")
    @ApiModelProperty(required = true, example = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate customerDateofBirth;
}
