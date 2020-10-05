package com.shailendra.repo;

import com.shailendra.pojo.PBXSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PBXSystemRepo extends CrudRepository<PBXSystem, Long> {

    PBXSystem findByHostNameAndPortNumber(String hostName, int portNumber);
}
