package com.corporate.ipbase.data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.corporate.ipbase.domain.IpPrefix;

@Repository
public interface IpPrefixRepository extends CrudRepository<IpPrefix,Long> {

		
}


