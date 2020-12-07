/**
 * author--Harshita Agarwal
 */
package com.cg.corona.service;

import java.util.List;

import com.cg.corona.domain.Laboratory;

/**
 * 
 * @author HARSHITA AGARWAL
 *
 */
public interface LaboratoryService {
	/**
	 * 
	 * @param patientId
	 * @return
	 * defining the CRUD method and check the service detail according to the patient Id 
	 */
	Laboratory findPatientById(int patientId);
	/**
	 * 
	 * @param testName
	 * @return
	 */

	Laboratory findPatientByTestName(String testName);

	Laboratory findPatientByRoomType(String roomType);

	Laboratory createPatient(Laboratory laboratory);

	Laboratory updateOnCriteria(int patientId, Laboratory laboratory);

	List<Laboratory> findAllLaboratory();

	boolean deletePatientById(int patientId);

	}
