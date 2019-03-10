package com.corporate.ipbase.data;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.corporate.ipbase.domain.IpPrefixv4;

@Repository
public interface IpPrefixv4Repository extends JpaRepository<IpPrefixv4,Long> {

	List<IpPrefixv4> findByNested(boolean nested);
	
	List<IpPrefixv4> findByMask(int mask);
}


