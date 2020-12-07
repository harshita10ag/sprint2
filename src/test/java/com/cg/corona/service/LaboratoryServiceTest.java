package com.cg.corona.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.ServiceNotFoundException;
import com.cg.corona.repository.LaboratoryRepository;

/**
 * 
 * @author HARSHITA AGARWAL checking the service detail is exist or not
 *
 */

public class LaboratoryServiceTest {
	@Mock
	LaboratoryRepository laboratoryRepository;

	@InjectMocks
	LaboratoryServiceImpl laboratoryServiceImpl;
	
	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * check find patient by patient id and check the value are same or not
	 * 
	 * @throws PatientIdException
	 */
	@Test
	void test_findPatientById() throws ServiceNotFoundException {
		BDDMockito.given(laboratoryRepository.findPatientByPatientId(201))
				.willReturn(Optional.of(new Laboratory(201, "Blood test", 800, "Super Delux", 1800, 2600)));
		Laboratory laboratory = laboratoryServiceImpl.findPatientById(201);
		assertNotNull(laboratory);
		assertEquals("Blood test", laboratory.getTestName());
		assertEquals(800, laboratory.getTestPrice());
		assertEquals("Super Delux", laboratory.getRoomType());
		assertEquals(1800, laboratory.getRoomPrice());
		assertEquals(2600, laboratory.getTotalBill());
	}

	/**
	 * check patient service detail by test name
	 */
	@Test
	void test_findServiceByTestName() {
		BDDMockito.given(laboratoryRepository.findByTestName("Covid test"))
				.willReturn(Optional.of(new Laboratory(203, "Covid test", 1200, "Private", 1200, 2400)));
		Laboratory laboratory = laboratoryServiceImpl.findPatientByTestName("Covid test");
		assertNotNull(laboratory);
		assertEquals("Covid test", laboratory.getTestName());
		assertEquals(1200, laboratory.getTestPrice());
		assertEquals("Private", laboratory.getRoomType());
		assertEquals(1200, laboratory.getRoomPrice());
		assertEquals(2400, laboratory.getTotalBill());
	}

	/**
	 * check patient service detail by room type
	 */
	@Test
	void test_findServiceByRoomType() {
		
		BDDMockito.given(laboratoryRepository.findByRoomType("Private"))
				.willReturn(Optional.of(new Laboratory(204, "Blood test", 800, "Private", 1200, 2000)));
		Laboratory laboratory = laboratoryServiceImpl.findPatientByRoomType("Private");
		assertNotNull(laboratory);
		assertEquals("Blood test", laboratory.getTestName());
		assertEquals(800, laboratory.getTestPrice());
		assertEquals("Private", laboratory.getRoomType());
		assertEquals(1200, laboratory.getRoomPrice());
		assertEquals(2000, laboratory.getTotalBill());

	}
	@Test
	void test_deleteServiceById() {
		Laboratory laboratory=new Laboratory();
		laboratory.setPatientId(201);
		laboratory.setTestName("Covid test");
		laboratory.setTestPrice(1200);
		laboratory.setRoomType("Private");
		laboratory.setRoomPrice(1200);
		laboratory.setTotalBill(2400);
		laboratoryRepository.deleteById(laboratory.getPatientId());
		assertThat(laboratoryRepository.findById(204).isEmpty());
	}
	@Test
	void test_UpdateServiceDetailByPatientId() throws Exception {
		Laboratory laboratory=new Laboratory();
		laboratory.setPatientId(201);
		laboratory.setTestName("Covid test");
		laboratory.setTestPrice(1200);
		laboratory.setRoomType("Private");
		laboratory.setRoomPrice(1200);
		laboratory.setTotalBill(2400);
		Mockito.when(laboratoryRepository.findByPatientId(401)).thenReturn(laboratory);
		laboratory.setTestName("Blood Test");
		Mockito.when(laboratoryRepository.save(laboratory)).thenReturn(laboratory);
		System.out.println(laboratory.getTestName());
	}
	@Test
	void test_getAllServiceDetail() throws Exception{
		Laboratory laboratory1 =new Laboratory(201,"Covid test",1200,"Private",1200,2400);
		Laboratory laboratory2 =new Laboratory(202,"Blood test",800,"Super Delux",1800,2600);
		List<Laboratory> laboratoryList = new ArrayList<>();
		laboratoryList.add(laboratory1);
		laboratoryList.add(laboratory2);
		Mockito.when(laboratoryRepository.findAll()).thenReturn(laboratoryList);
		assertThat(laboratoryServiceImpl.findAllLaboratory().equals(laboratoryList));
		
	}
	/**
	 * inserting patient detail whose used service
	 */
	@Test
	void test_insertNewPatientRecord() {
		Laboratory laboratory=new Laboratory();
		laboratory.setPatientId(201);
		laboratory.setTestName("Covid test");
		laboratory.setTestPrice(1200);
		laboratory.setRoomType("Private");
		laboratory.setRoomPrice(1200);
		laboratory.setTotalBill(2400);
		
	}

	
	/**
	 * patient id is different then it throw service not found exception
	 */
	@Test
	void test_findPatientById_ThrowServiceNotFoundException() {
		BDDMockito.given(laboratoryRepository.findPatientByPatientId(201))
				.willThrow(new ServiceNotFoundException("Patient Id does not exist"));
		assertThrows(ServiceNotFoundException.class, () -> laboratoryServiceImpl.findPatientById(352));
	}

	/**
	 * test name is different then it throw service not found exception
	 */
	@Test
	void test_findPatientByTestName_ThrowServiceNotFoundException() {
		BDDMockito.given(laboratoryRepository.findByTestName("Covid test"))
				.willThrow(new ServiceNotFoundException("Test name does not exist"));
		assertThrows(ServiceNotFoundException.class, () -> laboratoryServiceImpl.findPatientByTestName("CT Scan test"));//check the testname we are given is same or not

	}

	/**
	 * room type is different then it throw service not found exception
	 */
	@Test
	void test_findPatientByRoomType_ThrowServiceNotFoundException() {
		BDDMockito.given(laboratoryRepository.findByRoomType("Private"))
				.willThrow(new ServiceNotFoundException("Room Type does not exist"));
		assertThrows(ServiceNotFoundException.class, () -> laboratoryServiceImpl.findPatientByRoomType("Super Delux"));

	}
	
}
