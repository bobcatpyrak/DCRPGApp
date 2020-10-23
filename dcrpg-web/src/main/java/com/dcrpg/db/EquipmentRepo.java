package com.dcrpg.db;

import com.dcrpg.business.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> 
{
	
}
