package com.dcrpg.db;

import com.dcrpg.business.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLinkRepo extends JpaRepository<SocialLink, Integer> 
{
	
}
