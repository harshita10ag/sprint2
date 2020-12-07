package com.cg.corona.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.ServiceNotFoundException;
import com.cg.corona.service.LaboratoryService;
/**
 * 
 * @author HARSHITA AGARWAL
 * checking the output is blank or null or not
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LaboratoryController.class)
public class LaboratoryControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	LaboratoryService labService;

	/**
	 * 
	 * @throws Exception check status is 200 or not check the value is blank or not
	 */
	@Test
	void test_getServiceDetail() throws Exception {
		BDDMockito.given(labService.findPatientById(Mockito.anyInt()))
				.willReturn(new Laboratory(201, "Covid Test", 1200.0, "Private", 1200.0, 2400.0));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/laboratory/121")).andExpect(status().isOk())// expected
																										// output
				// 200------- actual
				// output 404
				.andExpect(jsonPath("$").isMap())// expected output {}-----actual output blank ;
				.andExpect(jsonPath("patientId").value(201)).andExpect(jsonPath("testName").value("Covid Test"))
				.andExpect(jsonPath("testPrice").value(1200.0)).andExpect(jsonPath("roomType").value("Private"))
				.andExpect(jsonPath("roomPrice").value(1200.0)).andExpect(jsonPath("totalBill").value(2400.0));
	}
	/**
	 * 
	 * @throws Exception
	 * if patient id does not exist it gives servicenot found exception
	 */
	@Test
	void test_getServiceDetail_ThrowServiceNotFoundException() throws Exception {

		BDDMockito.given(labService.findPatientById(Mockito.anyInt()))
				.willThrow(new ServiceNotFoundException("Patient id does not exist"));
		mockMvc.perform(MockMvcRequestBuilders.get("/patient/121")).andExpect(status().isNotFound());

	}

}
