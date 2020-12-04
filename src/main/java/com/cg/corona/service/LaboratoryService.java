/**
 * author--Harshita Agarwal
 */
package com.cg.corona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.PatientIdException;
import com.cg.corona.repository.LaboratoryRepository;

@Service
public class LaboratoryService {

	@Autowired
	private LaboratoryRepository labRepo;

	/**
	 * 
	 * find all patient detail whose used services
	 */
	public Iterable<Laboratory> findAllLaboratory() {
		return labRepo.findAll();

	}

	/**
	 * 
	 * @param patientid
	 * @return
	 * @throws PatientIdException find all services used by patient using patientId
	 */
	public Laboratory findPatientById(int patientid) throws PatientIdException {
		Laboratory labService = labRepo.findByPatientId(patientid);
		if (labService == null) {
			throw new PatientIdException("Patient id" + patientid + " does not exist");
		}
		return labService;
	}

	/**
	 * 
	 * @param patientId
	 * @throws PatientIdException delete detail by patientId
	 */
	public void deletePatientById(int patientId) throws PatientIdException {
		Laboratory labService = labRepo.findByPatientId(patientId);
		try {
			if (labService == null) {
				throw new PatientIdException("Patient id:" + patientId + " does not exist");
			}
			labRepo.deleteById(patientId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param patientId
	 * @param labService
	 * @return
	 * @throws PatientIdException update service detail
	 */
	public Laboratory updateOnCriteria(int patientId, Laboratory labService) throws PatientIdException {
		Laboratory laboratory = labRepo.findByPatientId(patientId);
		if (laboratory == null) {
			throw new PatientIdException("Patient id" + patientId + " does not exist");
		}
		if (labService.getRoomType() == null) {
			labService.setRoomType(laboratory.getRoomType());
		}
		if (labService.getRoomPrice() == 0.0) {
			labService.setRoomPrice(laboratory.getRoomPrice());
		}
		if (labService.getTestName() == null) {
			labService.setTestName(laboratory.getTestName());
		}
		if (labService.getTestPrice() == 0.0) {
			labService.setTestPrice(laboratory.getTestPrice());
		}
		if (labService.getPatientId() == 0) {
			labService.setPatientId(laboratory.getPatientId());
		}
		if (labService.getId() == null) {
			labService.setId(laboratory.getId());
		}
		labService.setTotalBill(labService.getTestPrice() + labService.getRoomPrice());
		return labRepo.saveAndFlush(labService);
	}

	/**
	 * 
	 * @param labService
	 * @return
	 * @throws PatientIdException provide service to the new patient
	 */
	public Laboratory createPatient(Laboratory labService) throws PatientIdException {

		try {
			labService.setPatientId(labService.getPatientId());
			labService.setTotalBill(labService.getTestPrice() + labService.getRoomPrice());

			return labRepo.saveAndFlush(labService);
		} catch (Exception e) {
			throw new PatientIdException("Patient Id" + labService.getPatientId() + " already available");
		}

	}
}
//package com.cg.doctor.exception;
//import java.util.regex.*;
//import com.cg.doctor.domain.MedicalHistory;
//import com.cg.doctor.exception.PatientNotFoundException;
///*This class validates the input*/
//public class DoctorValidator {
//	public boolean validatePatientId(Integer patientId)throws PatientNotFoundException {
//		String regex="^[0-9]*$";
//		Pattern pat=Pattern.compile(regex);
//		Matcher m=null;
//		 m =pat.matcher(String.valueOf(patientId));
//		try {
//		}catch(NumberFormatException e) {
//			throw new PatientNotFoundException("Invalid input, id should be a number");
//			}
//		return m.matches();
//	}
//}
