package com.shailendra.service;

import com.shailendra.enums.CallEvents;
import com.shailendra.pojo.Report;
import com.shailendra.repo.CallRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.shailendra.enums.CallEvents.ANSWERED;
import static com.shailendra.enums.CallEvents.HANGUP;
import static com.shailendra.enums.CallEvents.ORIGINATE;
import static com.shailendra.enums.CallEvents.RING;

@Service
public class CallRecordReportingService implements ReportingService{

    @Autowired
    private CallRecordRepository callRecordRepository;

    @Override
    public Report getDefaultReport() {
        EnumSet<CallEvents> successfulCallRecordStatus = EnumSet.of(ANSWERED, HANGUP);
        EnumSet<CallEvents> incompleteCallRecordStatus = EnumSet.of(ORIGINATE, RING);
        return callRecordRepository.getCallDetailRecordReport(convertToString(successfulCallRecordStatus), convertToString(incompleteCallRecordStatus));
    }

    private List<String> convertToString(EnumSet<CallEvents> events){
        return events.stream()
                .map(CallEvents::getValue)
                .collect(Collectors.toList());
    }
}
