package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.model.Serverinfo;

public interface ServerinfoRepository extends JpaRepository<Serverinfo, Long> {

	@Query("select count(e) FROM Serverinfo e")
	int getServersCount();
	
	@Query("select distinct envCategory FROM Serverinfo")
	List<String> findDistinctEnvCategories();
	
	@Query("select environmentName FROM Serverinfo e where e.envCategory=:envCategory")
	List<String> getEnvByEnvCat(@Param("envCategory")String envCategory);
	
	@Query("select e FROM Serverinfo e where e.environmentName=:environmentName")
	List<Serverinfo> getServersByEnv(@Param("environmentName")String environmentName);
	
	@Query("select count(distinct e.environmentName) FROM Serverinfo e where e.envCategory=:envCategory")
	int getEnvCountByEnvCategory(@Param("envCategory")String envCategory);
	
	@Query("select count(e) FROM Serverinfo e where e.envCategory=:envCategory")
	int getServersCountByEnvCategory(@Param("envCategory")String envCategory);
	
}
