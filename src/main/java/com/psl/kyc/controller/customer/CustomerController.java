package com.psl.kyc.controller.customer;

import com.psl.kyc.model.account.AccountCustomerMatchRequest;
import com.psl.kyc.model.customer.CustomerDto;
import com.psl.kyc.service.customer.CustomerService;
import com.psl.kyc.service.customer.VerifyDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
    private CustomerService customerService;
    private VerifyDetailsService verifyDetailsService;

    public CustomerController(CustomerService customerService, VerifyDetailsService verifyDetailsService) {
        this.customerService = customerService;
        this.verifyDetailsService = verifyDetailsService;
    }

    @ApiOperation(value = "Lookup Customer by Account Id")
    @GetMapping("/lookupByAccountId")
    public List<CustomerDto> lookup(@Valid @NotBlank @RequestParam String accountId) {
        return customerService.findCustomersByAccountId(accountId);
    }

    @ApiOperation(value = "Match Customer or Account Details")
    @PostMapping("/match")
    public boolean matchCustomer(@Valid @RequestBody AccountCustomerMatchRequest accountCustomerMatchRequest) {
        return verifyDetailsService.matchInputDetails(accountCustomerMatchRequest);
    }
}
