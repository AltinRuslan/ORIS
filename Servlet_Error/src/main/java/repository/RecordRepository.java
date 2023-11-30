package repository;

import models.Record;
import models.User;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record>{
    List<Record> findAllRecords(String id);
}
