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
import com.cg.corona.exception.ServiceNotFoundException;
import com.cg.corona.service.LaboratoryService;
/**
 * 
 * @author HARSHITA AGARWAL
 * controlling all the test cases and run the program through the  service class
 *
 */
@RestController
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {

	@Autowired
	private LaboratoryService labService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<Laboratory> getServiceDetail(@PathVariable int patientId) throws ServiceNotFoundException {
		Laboratory laboratory=labService.findPatientById(patientId);
		return new ResponseEntity<>(laboratory,HttpStatus.OK);
	}

	@PostMapping
	public Laboratory create(@RequestBody final Laboratory laboratory) throws ServiceNotFoundException {
		return labService.createPatient(laboratory);
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
			throws ServiceNotFoundException {
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
	public ResponseEntity<?> deletePatient(@PathVariable int patientId) throws ServiceNotFoundException {
		Boolean service=labService.deletePatientById(patientId);
		return ResponseEntity.ok(service);
	}

}
