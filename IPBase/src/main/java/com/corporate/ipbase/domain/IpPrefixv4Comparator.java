package com.corporate.ipbase.domain;

import java.util.Comparator;

public class IpPrefixv4Comparator implements Comparator<IpPrefixv4> {
  

	@Override
	public int compare(IpPrefixv4 o1, IpPrefixv4 o2) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < 4; i++) {
			if(o1.getBytes()[i] > o2.getBytes()[i])
				return 1;
			else
				return -1;
		}
		return 0;
	}
}