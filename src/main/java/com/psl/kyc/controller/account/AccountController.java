package com.psl.kyc.controller.account;

import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.account.AccountDto;
import com.psl.kyc.service.account.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "Add an Account with a Customer")
    @PostMapping("/create")
    public AccountDto create(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.createAccount(accountCreateRequest);
    }

    @ApiOperation(value = "Lookup Account by Customer Id")
    @GetMapping("/lookupByCustomerId")
    public List<AccountDto> lookup(@Valid @NotBlank @RequestParam String customerId) {
        return accountService.findAllAccountsByCustomerId(customerId);
    }
}
