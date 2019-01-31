package com.corporate.ipbase.data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.corporate.ipbase.domain.IpPrefixv4;

@Repository
public interface IpPrefixRepository extends CrudRepository<IpPrefixv4,Long> {

		
}


