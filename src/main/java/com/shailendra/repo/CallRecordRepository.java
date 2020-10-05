package com.shailendra.repo;

import com.shailendra.pojo.CallDetailRecord;
import com.shailendra.pojo.ICallRecordReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRecordRepository extends CrudRepository<CallDetailRecord, Long> {

    List<CallDetailRecord> findAll();

    CallDetailRecord findByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Query(value = "SELECT" +
            " COUNT(cr.*) as totalCallCount," +
            " SUM(CASE WHEN cr.action in :successfulCallStatus then 1 else 0 end) as totalSuccessfulCallCount," +
            " SUM(CASE WHEN cr.action in :incompleteCallStatus then 1 else 0 end) as totalIncompleteCallCount," +
            " SUM(CASE WHEN cr.answer_start is null OR cr.call_end is null or cr.ring_start is null or cr.recording is null then 1 else 0 end) as totalUnmatchedCallCount," +
            " MAX(cdr_sync_tm.last_sync_time) as lastSyncDate" +
            " FROM call_records AS cr, cdr_sync_tm", nativeQuery = true)
    ICallRecordReport getCallDetailRecordReport(List<String> successfulCallStatus, List<String> incompleteCallStatus);

}
