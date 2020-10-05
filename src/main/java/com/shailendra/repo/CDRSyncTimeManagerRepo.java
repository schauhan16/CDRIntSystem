package com.shailendra.repo;

import com.shailendra.pojo.CDRSyncTimeManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRSyncTimeManagerRepo extends CrudRepository<CDRSyncTimeManager, Long> {
}
