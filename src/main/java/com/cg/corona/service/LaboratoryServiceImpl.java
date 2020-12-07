package com.cg.corona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.ServiceNotFoundException;
import com.cg.corona.repository.LaboratoryRepository;
/**
 * 
 * @author HARSHITA AGARWAL
 * Defining all the CRUD function
 *
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	String notExist="does not exist";
	/**
	 * 
	 * find all patient detail whose used services
	 */
	public List<Laboratory> findAllLaboratory() {
		return laboratoryRepository.findAll();

	}

	/**
	 * 
	 * @param patientid
	 * @return
	 * @throws PatientIdException find all services used by patient using patientId
	 * finding patient service detail by patient id
	 */
	public Laboratory findPatientById(int patientId) {
		Laboratory laboratory = null;
		Optional<Laboratory> optionalLaboratory = laboratoryRepository.findPatientByPatientId(patientId);
		if (optionalLaboratory.isPresent()) {
			laboratory = optionalLaboratory.get();
		} else {
			throw new ServiceNotFoundException("Patient id "+patientId+" "+notExist);
		}
		return laboratory;
	}
	
	public Laboratory findPatientByTestName(String testName) {
		Laboratory laboratory = null;
		Optional<Laboratory> optionalLaboratory = laboratoryRepository.findByTestName(testName);
		if (optionalLaboratory.isPresent()) {
			laboratory = optionalLaboratory.get();
		} else {
			throw new ServiceNotFoundException("Test name "+testName+" "+notExist);
		}
		return laboratory;
	}
	/**
	 * find patient service detail by room type
	 */
	
	public Laboratory findPatientByRoomType(String roomType) {
		Laboratory laboratory = null;
		Optional<Laboratory> optionalLaboratory = laboratoryRepository.findByRoomType(roomType);
		if (optionalLaboratory.isPresent()) {
			laboratory = optionalLaboratory.get();
		} else {
			throw new ServiceNotFoundException("Room type "+roomType+" "+notExist);
		}
		return laboratory;
	}

	/**
	 * 
	 * @param patientId
	 * @throws PatientIdException delete detail by patientId
	 */

	public boolean deletePatientById(int patientId) throws ServiceNotFoundException {
		Laboratory labService = laboratoryRepository.findByPatientId(patientId);
		laboratoryRepository.deleteById(labService.getPatientId());
			return false;
	}

	/**
	 * 
	 * @param patientId
	 * @param labService
	 * @return
	 * @throws PatientIdException update service detail
	 */
	public Laboratory updateOnCriteria(int patientId, Laboratory labService) throws ServiceNotFoundException {
		Laboratory laboratory = laboratoryRepository.findByPatientId(patientId);
		if (laboratory == null) {
			throw new ServiceNotFoundException("Patient id" + patientId + " "+notExist);
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
		return laboratoryRepository.saveAndFlush(labService);
	}

	/**
	 * 
	 * @param labService
	 * @return
	 * @throws PatientIdException provide service to the new patient
	 */
	public Laboratory createPatient(Laboratory labService) throws ServiceNotFoundException {

		labService.setTotalBill(labService.getTestPrice() + labService.getRoomPrice());
		
		return laboratoryRepository.saveAndFlush(labService);
		

	}


}
