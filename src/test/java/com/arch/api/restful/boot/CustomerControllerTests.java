package com.arch.api.restful.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by SRINIVASULU on 07/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {



    static{
        System.setProperty("spring.profiles.active","dev");
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerNewCustomerTest() throws Exception {

        try {
            this.mockMvc.perform(post("/customer/register").content("{   \"firstName\" : \"Dave\" , \"lastName\" : \"LastNameDave\" , \"pan\" : \"TG234432K\" , \"company\" : \"KloudPortal\" , \"address1\" : \"Address Line 1 # dasd$ , -dsfd \" , \"address2\" : \"Address Line 2 # dasd$ , -dsfd \" , \"city\" : \"Hyd\" , \"area\" : \"Area 1\" , \"associationSinceFrom\" : 2010 , \"mobile1\" : \"9239239239\" , \"mobile2\" : \"23432423243\" , \"verifyStatus\" : \"Just Registering\" , \"isSuspended\" : false , \"type\" : \"TaxPayee\" , \"passwd\" : \"Sha1password\" , \"nickname\" : \"Dave Nick Name\" , \"age\" : 34 , \"email\" : \"dave2@email.com\" } ")
                    // .header("Authorization","Basic c3JpbmlrOnNlY3JldDE=")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName").value("Dave"));


        }catch (DuplicateKeyException ex){

        }
    }

    /*@Test
    public void registerAgainCustomerTest() throws Exception {

        this.mockMvc.perform(get("/customer/register").header("Authorization","Basic c3JpbmlrOnNlY3JldDE=")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }*/

    @Test
    public void updateCustomerTest() throws Exception {

        this.mockMvc.perform(post("/customer/update").content("{\n" +
                "\t\"_id\":  \"582099f683d1340db7ff5f27\",\n" +
                "\t\"firstName\": \"DaveUpdated\",\n" +
                "\t\"lastName\": \"LastNameDaveUpdate\",\n" +
                "\t\"pan\": \"TG234432K\",\n" +
                "\t\"company\": \"KloudPortal\",\n" +
                "\t\"address1\": \"Address Line 1 # dasd$ , -dsfd \",\n" +
                "\t\"address2\": \"Address Line 2 # dasd$ , -dsfd \",\n" +
                "\t\"city\": \"Hyd\",\n" +
                "\t\"passwd\": \"Sha1password\",\n" +
                "\t\"area\": \"Area 1\",\n" +
                "\t\"associationSinceFrom\": 2010,\n" +
                "\t\"mobile1\": \"9239239239\",\n" +
                "\t\"mobile2\": \"23432423243\",\n" +
                "\t\"verifyStatus\": \"Just Registering\",\n" +
                "\t\"isSuspended\": false,\n" +
                "\t\"type\": \"TaxPayee\",\n" +
                "\t\"nickname\": \"Dave Nick Name\",\n" +
                "\t\"age\": 34,\n" +
                "\t\"email\": \"dave2@email.com\"\n" +
                "} ")
                .header("Authorization","Basic ZGF2ZTJAZW1haWwuY29tOlNoYTFwYXNzd29yZA==")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("DaveUpdated"));
    }

    @Test
    public void suspendCustomerTest() throws Exception {

        this.mockMvc.perform(post("/customer/suspend")
                .header("Authorization","Basic ZGF2ZTJAZW1haWwuY29tOlNoYTFwYXNzd29yZA==")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("DaveUpdated"));
    }

    @Test
    public void VerifyCustomerSuccessTest() throws Exception {

        this.mockMvc.perform(get("/customer/verify")
                .header("Authorization","Basic ZGF2ZTJAZW1haWwuY29tOlNoYTFwYXNzd29yZA==")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()
                );
    }

    @Test
    public void VerfiyCustomerSuspendTest() throws Exception {

        this.mockMvc.perform(get("/customer/verify").header("Authorization","Basic c3JpbmlrOnNlY3JldDE=")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void loginCustomerTest() throws Exception {

        this.mockMvc.perform(get("/customer/getCustomer")
                .header("Authorization","Basic ZGF2ZTJAZW1haWwuY29tOlNoYTFwYXNzd29yZA==")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("DaveUpdated"));
    }


    @Test
    public void changePasswordAndLoginCustomerTest() throws Exception {

        this.mockMvc.perform(post("/customer/changepwd").content("{\n" +
                "\t\"_id\":  \"582099f683d1340db7ff5f27\",\n" +

                "\t\"passwd\": \"Sha1password\",\n" +

                "\t\"email\": \"dave2@email.com\"\n" +
                "} ")
                .header("Authorization","Basic ZGF2ZTJAZW1haWwuY29tOlNoYTFwYXNzd29yZDcy")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("DaveUpdated"));
    }
}
