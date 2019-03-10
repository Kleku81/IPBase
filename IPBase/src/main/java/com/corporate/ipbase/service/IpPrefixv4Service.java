package com.corporate.ipbase.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import com.corporate.ipbase.data.IpPrefixv4Repository;
import com.corporate.ipbase.domain.IpPrefixv4;

import antlr.collections.impl.IntRange;

@Service
public class IpPrefixv4Service {
	



	private final IpPrefixv4Repository repo;

	public IpPrefixv4Service(IpPrefixv4Repository repo) {
		this.repo = repo;
	}
	
	public IpPrefixv4Repository getRepo() {
		return repo;
	}
	
	
	public Optional<IpPrefixv4> checkExistance(IpPrefixv4 prefix) {
		
		Optional<IpPrefixv4> prefix_box = Optional.empty();
		
		for (int temp_mask : revRange(8, prefix.getMask()-1).toArray())
		{
			for (IpPrefixv4 temp_prefix : repo.findByMask(temp_mask))
			{
				if (prefix.isContained(temp_prefix))
					return prefix_box.of(temp_prefix);
			}
				
		}
		return prefix_box;
			
	}
	
	static IntStream revRange(int from, int to) {
	    return IntStream.range(from, to)
	                    .map(i -> to - i + from - 1);
	}
	
	
	

}
