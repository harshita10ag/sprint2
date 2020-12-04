package com.cg.corona;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;


import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.PatientIdException;
import com.cg.corona.service.LaboratoryService;

@SpringBootTest
@WebMvcTest(value = Laboratory.class)
class CoronaCounsaltancyApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private LaboratoryService laboratoryService;
	
	@Test
	void contextLoads() {
	}

//	@Test
@Before(value = "")
	public void setUp() {
		Laboratory laboratory=new Laboratory(1L,201,"Private",1200.00,"Covid- Test",1200.00,2400.00);
	
		try {
			Mockito.when(laboratoryService.findPatientById(laboratory.getPatientId())).thenReturn(laboratory);
		} catch (PatientIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	 void FindPatintServiceDetailByPatientId_thenReturnServiceDetail() throws PatientIdException {
		int patientId=201;
		Laboratory laboratory=laboratoryService.findPatientById(patientId);	
		assertThat(laboratory.getPatientId()).isEqualTo(patientId);
		
	}

	
}
