package com.shailendra.ext;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.service.CallRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cdr")
public class CDRWebHookEnpoint {

    @Autowired
    private CallRecordService callRecordService;

    @PostMapping(value = "webhook")
    @ResponseBody
    public CallDetailRecordDTO saveCallRecord(@RequestBody CallDetailRecordDTO record){
        return callRecordService.saveCallRecord(record);
    }

}
