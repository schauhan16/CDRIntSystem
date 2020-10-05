package com.shailendra.service;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.enums.CallEvents;
import com.shailendra.main.CDRSystemApplication;
import com.shailendra.pojo.Report;
import com.shailendra.repo.CallRecordRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = CDRSystemApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CallRecordReportingServiceTest {

    @Autowired
    private CallRecordReportingService callRecordReportingService;

    @Autowired
    private CallRecordRepository callRecordRepository;

    @Autowired
    private CallRecordService callRecordService;

    private List<CallEvents> randomCallEvents;

    @After
    public void cleanup(){
        callRecordRepository.deleteAll();
    }

    private List<CallDetailRecordDTO> multipleCallRecords(){
        return IntStream.range(0, 10)
                .mapToObj(i -> createCallRecord(randomCallEvents.get(i)))
                .collect(Collectors.toList());
    }

    private CallDetailRecordDTO createCallRecord(CallEvents callEvent){
        CallDetailRecordDTO record = new CallDetailRecordDTO();
        record.setDestinationNumber(new Random().nextInt());
        record.setUuid(UUID.randomUUID().toString());
        record.setAction(callEvent);
        return record;
    }


    private void createRandomCallEventList(){
        List<CallEvents> events = Arrays.asList(CallEvents.values());
        randomCallEvents =  IntStream.range(0, 10)
        .mapToObj(i -> events.get(new Random().nextInt(events.size())))
                .collect(Collectors.toList());
    }

    @Test
    public void testReport() {
        createRandomCallEventList();
        callRecordService.saveCallRecord(multipleCallRecords());
        Report report = callRecordReportingService.getDefaultReport();
        assertNotNull(report);
    }

}
