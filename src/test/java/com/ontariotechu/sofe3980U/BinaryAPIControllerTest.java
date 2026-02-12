package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    //Test cases for binary bitwise AND, OR and Multiply
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111").param("operand2", "1010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("10"));
    }

    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "111").param("operand2", "1010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "111").param("operand2", "1010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1000110"));
    }

    // Comprehensive AND tests for API
    @Test
    public void andBasic() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1111").param("operand2", "1010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1010"));
    }

    @Test
    public void andWithZero() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1111").param("operand2", "0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
    }

    @Test
    public void andSameValue() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1111").param("operand2", "1111"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void andAllZeros() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "0101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
    }

    // Comprehensive OR tests for API
    @Test
    public void orBasic() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1100").param("operand2", "0011"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void orWithZero() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1111").param("operand2", "0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void orSameValue() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1111").param("operand2", "1111"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void orDifferentLengths() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "11").param("operand2", "1000"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1011"));
    }

    // Comprehensive Multiply tests for API
    @Test
    public void multiplyByZero() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
    }

    @Test
    public void multiplyByOne() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1111").param("operand2", "1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void multiplyByTwo() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "10"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1010"));
    }

    @Test
    public void multiplyLargeNumbers() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1111").param("operand2", "1111"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("11100001"));
    }

    //More Addition tests for API
    @Test
    public void addWithZero() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1111").param("operand2", "0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
    }

    @Test
    public void addCarryPropagation() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1111").param("operand2", "1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("10000"));
    }

    @Test
    public void addLargeNumbers() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "11111111").param("operand2", "1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("100000000"));
    }
}