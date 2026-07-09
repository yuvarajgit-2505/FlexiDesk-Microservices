package com.flexidesk.desk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexidesk.desk.entity.Desk;
import com.flexidesk.desk.entity.enums.DeskType;

public interface DeskRepository extends JpaRepository<Desk,Long> {
    List<Desk> findByDeskType(DeskType deskType);
    List<Desk> findByAvailableGreaterThan(Integer available);
}
