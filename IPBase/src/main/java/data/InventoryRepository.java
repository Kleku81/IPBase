package data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.MerchandiseEntity;

@Repository
public interface InventoryRepository 
  extends CrudRepository<MerchandiseEntity, Long> {
}