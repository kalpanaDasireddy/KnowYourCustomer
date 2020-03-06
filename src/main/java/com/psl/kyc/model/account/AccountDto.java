package com.psl.kyc.model.account;

import com.googlecode.jmapper.annotations.JGlobalMap;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@JGlobalMap
public class AccountDto {
    private String id;
    private Integer accountNumber;
    private List<String> customers;
}
