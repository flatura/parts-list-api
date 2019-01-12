package com.javarush.internship.entrancetest.repository;

import com.javarush.internship.entrancetest.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {}
