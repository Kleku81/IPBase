package data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.IpPrefix;

@Repository
public interface IpPrefixRepository extends CrudRepository<IpPrefix,Long> {

		
}


