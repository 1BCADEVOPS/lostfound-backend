package com.example.lostfound.repository;

import com.example.lostfound.entity.LostFoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostFoundRepository extends JpaRepository<LostFoundItem, Long> {
}
