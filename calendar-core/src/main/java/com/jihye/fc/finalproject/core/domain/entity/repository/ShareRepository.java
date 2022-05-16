package com.jihye.fc.finalproject.core.domain.entity.repository;

import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
	
	//@Query : jpql 또는 native query를 그대로 사용할 수 있음
	@Query("SELECT s from Share s where (s.fromUserId = ?1 or s.toUserId = ?1) and s.requestStatus = ?2 and s.direction = ?3")
	List<Share> findAllByDirection(Long id, RequestStatus accepted, Share.Direction biDirection);
	
	List<Share> findAllByToUserIdAndRequestStatusAndDirection(Long userId, RequestStatus requestStatus, Share.Direction direction)
}
