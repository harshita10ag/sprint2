/**
 * author--Harshita Agarwal
 */
package com.cg.corona.service;

import org.springframework.beans.BeanUtils;
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
	public Iterable<Laboratory> findAllLaboratory(){
		return labRepo.findAll();
		
	}
	/**
	 * 
	 * @param patientid
	 * @return
	 * @throws PatientIdException
	 * find all services used by patient using patientId 
	 */
	public Laboratory findPatientById(int patientid) throws PatientIdException {
		Laboratory labService=labRepo.findByPatientId(patientid);
		if(labService==null) {
			throw new PatientIdException("Patient id"+patientid+" does not exist");
		}
		return labService;
	}
	/**
	 * 
	 * @param patientId
	 * @throws PatientIdException
	 * delete detail by patientId
	 */
	public void deletePatientById(int patientId) throws PatientIdException {
		Laboratory labService=labRepo.findByPatientId(patientId);
		if(labService==null) {
			throw new PatientIdException("Patient id:"+patientId+" does not exist");
		}
		labRepo.deleteById(patientId);
	}
	/**
	 * 
	 * @param patientId
	 * @param labService
	 * @return
	 * @throws PatientIdException
	 * update service detail
	 */
	public Laboratory updateOnCriteria(int patientId, Laboratory labService) throws PatientIdException {
		Laboratory laboratory=labRepo.findByPatientId(patientId);
		if(laboratory==null) {
			throw new PatientIdException("Patient id"+patientId+" does not exist");
		}
		BeanUtils.copyProperties(labService, laboratory,"patientId");
		return labRepo.save(labService);
	}
	/**
	 * 
	 * @param labService
	 * @return
	 * @throws PatientIdException
	 * provide service to the new patient
	 */
	public Laboratory createPatient(Laboratory labService) throws PatientIdException {
		try {
			labService.setPatientId(labService.getPatientId());
			return labRepo.saveAndFlush(labService);
		}
		catch (Exception e) {
			throw new PatientIdException("Patient Id"+labService.getPatientId()+" already available");
		}
		
	}
}
