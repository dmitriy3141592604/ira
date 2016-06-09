package org.i2g.ira;

import org.springframework.stereotype.Service;

@Service
public class FileDaoImpl implements FileDao {

	@Override
	public Integer sizeByName(String name) {
		return 49;
	}

}
