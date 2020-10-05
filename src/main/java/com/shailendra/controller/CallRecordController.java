package com.shailendra.controller;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.service.CallRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("callRecords")
public class CallRecordController {

    @Autowired
    private CallRecordService callRecordService;

    @GetMapping("all")
    public List<CallDetailRecordDTO> getAllRecords(){
        return callRecordService.getCallRecords();
    }

    @GetMapping
    public CallDetailRecordDTO getCallRecords(@RequestParam(value = "recordId") String recordId){
        return callRecordService.getCallRecord(recordId);
    }

    @PostMapping
    @ResponseBody
    public CallDetailRecordDTO addCallRecord(@RequestBody CallDetailRecordDTO record){
        return callRecordService.saveCallRecord(record);
    }

    @GetMapping("delete/{uuid}")
    public void deleteCallRecord(@PathVariable String uuid){
        callRecordService.deleteCallRecord(uuid);
    }
}
