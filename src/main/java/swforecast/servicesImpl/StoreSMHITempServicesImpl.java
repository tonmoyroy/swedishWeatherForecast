package swforecast.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swforecast.dao.StoreSMHITempdao;
import swforecast.entities.SMHIData;
import swforecast.entities.SMHIPoints;
import swforecast.services.StoreSMHITempServices;

@Service
public class StoreSMHITempServicesImpl implements StoreSMHITempServices {
	@Autowired
	StoreSMHITempdao StoreSMHITempdao;

	public List<SMHIData> list(SMHIPoints points) {
		// TODO Auto-generated method stub
		return StoreSMHITempdao.list(points);
	}

	public List<SMHIData> getforecast(SMHIPoints points) {
		// TODO Auto-generated method stub
		return StoreSMHITempdao.getforecast(points);
	}

	public List<SMHIPoints> listpoints() {
		// TODO Auto-generated method stub
		return StoreSMHITempdao.listpoints();
	}

	public boolean save(List<SMHIData> data, Date lastupdatedate) {
		// TODO Auto-generated method stub
		return StoreSMHITempdao.save(data, lastupdatedate);
	}

	public SMHIPoints getcorrdinates(String city) {
		// TODO Auto-generated method stub
		return StoreSMHITempdao.getcorrdinates(city);
	}

}
