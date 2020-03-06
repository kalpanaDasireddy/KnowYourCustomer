package com.psl.kyc.model.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AccountCustomerMatchRequest {
    private String customerForename;
    private String customerSurname;
    @ApiModelProperty(example = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate customerDateofBirth;
    private Integer accountNumber;
    private String customerId;
    private String accountId;
}
