package com.shailendra.service;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.main.CDRSystemApplication;
import com.shailendra.repo.CallRecordRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = CDRSystemApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CallRecordServiceTest {

    @Autowired
    private CallRecordService callRecordService;

    @Autowired
    private CallRecordRepository callRecordRepository;

    @After
    public void cleanUp(){
        callRecordRepository.deleteAll();
    }

    private List<CallDetailRecordDTO> multipleCallRecords(){
        return IntStream.range(0, 10)
                .mapToObj(i -> createCallRecord())
                .collect(Collectors.toList());
    }

    private CallDetailRecordDTO createCallRecord(){
        CallDetailRecordDTO record = new CallDetailRecordDTO();
        record.setDestinationNumber(new Random().nextInt());
        record.setUuid(UUID.randomUUID().toString());
        return record;
    }

    @Test
    public void testSavingSingleCallRecord(){
        CallDetailRecordDTO callDetailRecord = createCallRecord();

        CallDetailRecordDTO savedRecord = callRecordService.saveCallRecord(callDetailRecord);
        Assert.assertEquals(callDetailRecord, savedRecord);

        Assert.assertEquals(1, callRecordService.getCallRecords().size());
    }

    @Test
    public void testSavingMultipleCallRecord(){
        List<CallDetailRecordDTO> callRecords = multipleCallRecords();

        callRecordService.saveCallRecord(callRecords);
        Assert.assertEquals(callRecords, callRecordService.getCallRecords());

        Assert.assertEquals(10, callRecordService.getCallRecords().size());
    }

    @Test
    public void testDeletionCallRecord(){
        List<CallDetailRecordDTO> callRecords = multipleCallRecords();

        callRecordService.saveCallRecord(callRecords);
        Assert.assertEquals(callRecords, callRecordService.getCallRecords());

        Assert.assertEquals(10, callRecordService.getCallRecords().size());

        callRecordService.deleteCallRecord(callRecords.get(0).getUuid());
        Assert.assertEquals(9, callRecordService.getCallRecords().size());
    }

    @Test
    public void testUpdateCallRecord(){
        List<CallDetailRecordDTO> callRecords = multipleCallRecords();

        callRecordService.saveCallRecord(callRecords);
        Assert.assertEquals(callRecords, callRecordService.getCallRecords());

        Assert.assertEquals(10, callRecordService.getCallRecords().size());

        String callerName = "Shailendra";
        CallDetailRecordDTO toUpdate = callRecords.get(0);
        toUpdate.setCallerName(callerName);
        callRecordService.saveCallRecord(callRecords);
        Assert.assertEquals(10, callRecordService.getCallRecords().size());

        Assert.assertEquals(toUpdate, callRecordService.getCallRecord(toUpdate.getUuid()));
    }

    @Test(expected = Exception.class)
    public void throwExceptionIfUuidIsNull(){
        CallDetailRecordDTO callDetailRecord = createCallRecord();
        callDetailRecord.setUuid(null);

        callRecordService.saveCallRecord(callDetailRecord);
    }

}
