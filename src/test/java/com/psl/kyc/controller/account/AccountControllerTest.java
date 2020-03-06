package com.psl.kyc.controller.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.repository.AccountNumberGeneratorRepository;
import com.psl.kyc.repository.AccountRepository;
import com.psl.kyc.service.account.AccountService;
import com.psl.kyc.service.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private AccountNumberGeneratorRepository accountNumberGeneratorRepository;
    @MockBean
    private CustomerService customerService;

    @Test
    public void testCreateAccount_EmptyForename() throws Exception{
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest(null, "d", LocalDate.parse("1982-04-04"));
        mockMvc.perform(post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCreateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateAccount_validInput() throws Exception{
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest("k", "d", LocalDate.parse("1982-04-04"));
        mockMvc.perform(post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCreateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testLookup_invalidInput() throws Exception{
        mockMvc.perform(get("/account/lookupByCustomerId")
                .contentType(MediaType.APPLICATION_JSON)
                .param("customerId", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLookup_validInput() throws Exception{
        mockMvc.perform(get("/account/lookupByCustomerId" )
                .contentType(MediaType.APPLICATION_JSON)
                .param("customerId", "xxxxxxx"))
                .andExpect(status().isOk());
    }
}
