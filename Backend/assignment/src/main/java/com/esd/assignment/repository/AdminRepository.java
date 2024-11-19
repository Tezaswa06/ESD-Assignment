package com.esd.assignment.repository;

import com.esd.assignment.entities.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findAdminByAdminEmail(String adminEmail);
}
