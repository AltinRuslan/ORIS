package repository;

import models.Doctor;
import models.User;

public interface DoctorRepository extends CrudRepository<Doctor>{
    String findDoctorById(String id);

    String findDoctorByName(String doctorName);
}
