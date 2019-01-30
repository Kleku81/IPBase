package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
public class MerchandiseEntity {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    private Double price;
    @NonNull
    private String brand;
 
    /*public MerchandiseEntity() {
    }*/
 
    /*public MerchandiseEntity(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }*/
}
