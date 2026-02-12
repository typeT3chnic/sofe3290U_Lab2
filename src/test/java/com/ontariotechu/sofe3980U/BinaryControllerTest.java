package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }

	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }





    //Test cases for binary bitwise AND, OR and Multiply
    @Test
    public void postParameterAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator", "&").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"))
            .andExpect(model().attribute("operator", "&"));
    }

    @Test
    public void postParameterOr() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator", "|").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"))
            .andExpect(model().attribute("operator", "|"));
    }

    @Test
    public void postParameterMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator", "*").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000110"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"))
            .andExpect(model().attribute("operator", "*"));
    }

    //More AND tests
    @Test
    public void postParameterAndBasic() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "&").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1010"))
            .andExpect(model().attribute("operator", "&"));
    }

    @Test
    public void postParameterAndWithZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "&").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postParameterAndSameValue() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "&").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterAndAllZeros() throws Exception {
        this.mvc.perform(post("/").param("operand1","1010").param("operator", "&").param("operand2","0101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    //More OR tests
    @Test
    public void postParameterOrBasic() throws Exception {
        this.mvc.perform(post("/").param("operand1","1100").param("operator", "|").param("operand2","0011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterOrWithZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "|").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterOrSameValue() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "|").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterOrDifferentLengths() throws Exception {
        this.mvc.perform(post("/").param("operand1","11").param("operator", "|").param("operand2","1000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1011"));
    }

    //More Multiply tests
    @Test
    public void postParameterMultiplyByZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","1010").param("operator", "*").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postParameterMultiplyByOne() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "*").param("operand2","1"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterMultiplyByTwo() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator", "*").param("operand2","10"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1010"));
    }

    @Test
    public void postParameterMultiplyLargeNumbers() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "*").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11100001"));
    }

    //More Addition tests
    @Test
    public void postParameterAddWithZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "+").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postParameterAddCarryPropagation() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator", "+").param("operand2","1"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10000"));
    }

    @Test
    public void postParameterAddLargeNumbers() throws Exception {
        this.mvc.perform(post("/").param("operand1","11111111").param("operator", "+").param("operand2","1"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100000000"));
    }

}