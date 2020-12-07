package com.cg.corona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.corona.domain.Laboratory;
/**
 * 
 * @author HARSHITA AGARWAL
 * using JPARepository
 * defining declare the method 
 *
 */

public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {

	Laboratory findByPatientId(int patientid);
	public Optional<Laboratory> findPatientByPatientId(int patientId);
	public Optional<Laboratory> findByTestName(String testName);
	public Optional<Laboratory> findByRoomType(String roomType);
	void deleteById(Long patientId);


}
