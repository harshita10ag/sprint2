package com.cg.corona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.corona.domain.Laboratory;
import com.cg.corona.exception.PatientIdException;
import com.cg.corona.service.LaboratoryService;

@RestController
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {

	@Autowired
	private LaboratoryService labService;


	@PostMapping
	public Laboratory create(@RequestBody final Laboratory laboratory) throws PatientIdException {
		return labService.createPatient(laboratory);
	}

	/**
	 * 
	 * @param patientId
	 * @return
	 * @throws PatientIdException
	 */
	@GetMapping
	@RequestMapping("/{patientId}")
	public ResponseEntity<?> getLaboratoryServiceByPatientId(@PathVariable int patientId) throws PatientIdException {
		return new ResponseEntity<Laboratory>(labService.findPatientById(patientId), HttpStatus.OK);
	}

	/**
	 * 
	 * @param patientId
	 * @param laboratory
	 * @return
	 * @throws PatientIdException
	 */
	@PutMapping("/update/{patientId}")
	public ResponseEntity<?> updatePatient(@PathVariable int patientId, @RequestBody Laboratory laboratory)
			throws PatientIdException {
		return new ResponseEntity<Laboratory>(labService.updateOnCriteria(patientId, laboratory), HttpStatus.OK);
	}

	/**
	 * 
	 * all patient detail
	 */
	@GetMapping("/all")
	public Iterable<Laboratory> getAllPatient() {

		return labService.findAllLaboratory();
	}

	/**
	 * 
	 * @param patientId
	 * @return
	 * @throws PatientIdException
	 */
	@DeleteMapping("delete/{patientId}")
	public ResponseEntity<?> deletePatient(@PathVariable int patientId) throws PatientIdException {
		labService.deletePatientById(patientId);
		return new ResponseEntity<String>("Patient with Id : " + patientId + " Deleted!", HttpStatus.OK);
	}

}
