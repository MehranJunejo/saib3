package com.saib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saib.model.LogsModel;

@Repository
public interface LogsRepo extends CrudRepository<LogsModel, Long>  {

}
