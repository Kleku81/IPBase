package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import inet.ipaddr.IPAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
public class IpPrefix {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @NonNull
	  private String bytes;
	  private final Long id = -1L;
	  

}
