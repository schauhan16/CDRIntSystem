package com.shailendra.schedular;

import com.shailendra.service.CallRecordService;
import com.shailendra.service.PBXSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClassRecordSyncScheduler {

    @Autowired
    private PBXSystemService pbxSystemService;

    @Autowired
    private CallRecordService callRecordService;

    @Scheduled(cron = "0 0 0 * * ?")
    private void syncCDRRecord(){
        pbxSystemService.getAllRegisteredSystem()
                .stream()
                .forEach(callRecordService::syncCDRWithPBXSystem);
    }

}
