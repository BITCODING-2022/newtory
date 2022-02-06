package com.newtory.pocket.repository;

import com.newtory.pocket.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
