package com.dcrpg.db;

import com.dcrpg.business.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepo extends JpaRepository<Weapon, Integer> 
{
	
}
