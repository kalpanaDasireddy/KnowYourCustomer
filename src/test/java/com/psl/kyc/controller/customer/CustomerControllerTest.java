package com.psl.kyc.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.account.AccountCustomerMatchRequest;
import com.psl.kyc.service.customer.CustomerService;
import com.psl.kyc.service.customer.VerifyDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;
    @MockBean
    private VerifyDetailsService verifyDetailsService;

    @Test
    public void testLookup_invalidInput() throws Exception{
        mockMvc.perform(get("/customer/lookupByAccountId")
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountId", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLookup_validInput() throws Exception{
        mockMvc.perform(get("/customer/lookupByAccountId" )
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountId", "xxxxxxx"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMatch_validInput() throws Exception{
        AccountCustomerMatchRequest accountCustomerMatchRequest = new AccountCustomerMatchRequest("k", "d", LocalDate.parse("1982-04-04"), null, null, null);

        mockMvc.perform(post("/customer/match" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCustomerMatchRequest)))
                .andExpect(status().isOk());
    }
}
