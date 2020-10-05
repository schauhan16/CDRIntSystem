package com.shailendra.pojo;

import java.util.Date;

public interface ICallRecordReport extends Report {
    Integer getTotalCallCount();

    Integer getTotalSuccessfulCallCount();

    Integer getTotalIncompleteCallCount();

    Integer getTotalUnmatchedCallCount();

    Date getLastSyncDate();
}
