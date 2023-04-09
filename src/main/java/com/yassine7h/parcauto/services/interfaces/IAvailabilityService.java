package com.yassine7h.parcauto.services.interfaces;

import java.sql.Date;

public interface IAvailabilityService {
    boolean isVehicleAvailable(int idVehicle, Date startDate, Date endDate);
    boolean isDriverAvailable(int idDriver, Date startDate,Date endDate);
}
