package edu.ap.backendspring.repository;

import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository  extends JpaRepository<Certificate, Integer> {
}
