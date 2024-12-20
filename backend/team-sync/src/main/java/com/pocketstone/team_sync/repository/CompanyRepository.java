package com.pocketstone.team_sync.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocketstone.team_sync.entity.Company;

public interface CompanyRepository  extends JpaRepository<Company, Long>  {
    Optional<Company> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
