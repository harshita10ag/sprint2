package com.cg.corona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.corona.domain.Laboratory;


public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {

	Laboratory findByPatientId(int patientid);


}
