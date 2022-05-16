package com.jihye.fc.finalproject.core.domain.entity.repository;

import com.jihye.fc.finalproject.core.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
}
