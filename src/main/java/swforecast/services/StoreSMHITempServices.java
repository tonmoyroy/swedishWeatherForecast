package swforecast.services;

import java.util.Date;
import java.util.List;

import swforecast.entities.SMHIData;
import swforecast.entities.SMHIPoints;

public interface StoreSMHITempServices {
	public List<SMHIData> list(SMHIPoints points);

	public List<SMHIPoints> listpoints();
	
	public List<SMHIData> getforecast(SMHIPoints points);

	public boolean save(List<SMHIData> data, Date lastupdatedate);
	
	public SMHIPoints getcorrdinates(String city);
}
