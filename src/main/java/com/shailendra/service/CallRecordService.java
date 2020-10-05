package com.shailendra.service;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.dto.PBXSystemDTO;
import com.shailendra.ext.CallRecordRetriever;
import com.shailendra.pojo.CDRSyncTimeManager;
import com.shailendra.pojo.CallDetailRecord;
import com.shailendra.pojo.Contact;
import com.shailendra.repo.CDRSyncTimeManagerRepo;
import com.shailendra.repo.CallRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CallRecordService {

    @Autowired
    private CallRecordRepository callRecordRepository;

    @Autowired
    private CDRSyncTimeManagerRepo cdrSyncTimeManagerRepo;

    @Autowired
    private ContactService contactService;

    @Autowired
    private CallRecordRetriever callRecordRetriever;

    private ModelMapper modelMapper = new ModelMapper();

    public List<CallDetailRecordDTO> getCallRecords() {
        List<CallDetailRecord> records =  callRecordRepository.findAll();
        return records.parallelStream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CallDetailRecordDTO> saveCallRecord(List<CallDetailRecordDTO> records) {
        return records.stream()
                .map(this::saveCallRecord)
                .collect(Collectors.toList());
    }

    public List<CallDetailRecordDTO> syncCDRWithPBXSystem(PBXSystemDTO system) {
        cdrSyncTimeManagerRepo.save(new CDRSyncTimeManager());
        return saveCallRecord(callRecordRetriever.getCallRecords(system));
    }

    public CallDetailRecordDTO saveCallRecord(CallDetailRecordDTO callDetailRecordDTO) {
        Contact contact = contactService.getContactEntity(callDetailRecordDTO.getDestinationNumber());
        if (Objects.isNull(contact)) {
            contact = contactService.saveContact(new Contact(callDetailRecordDTO.getDestinationNumber()));
        }
        CallDetailRecord callDetailRecord = convertToEntity(callDetailRecordDTO);
        callDetailRecord.setContact(contact);
        return convertToDTO(callRecordRepository.save(callDetailRecord));
    }

    public CallDetailRecordDTO getCallRecord(String recordId) {
        CallDetailRecord detailRecord = callRecordRepository.findByUuid(recordId);
        return convertToDTO(detailRecord);
    }

    @Transactional
    public void deleteCallRecord(String uuid) {
        callRecordRepository.deleteByUuid(uuid);
    }

    private CallDetailRecord convertToEntity(CallDetailRecordDTO callDetailRecordDTO) {
        return modelMapper.map(callDetailRecordDTO, CallDetailRecord.class);
    }

    private CallDetailRecordDTO convertToDTO(CallDetailRecord callDetailRecord){
        CallDetailRecordDTO callDetailRecordDTO = modelMapper.map(callDetailRecord, CallDetailRecordDTO.class);
        callDetailRecordDTO.setDestinationNumber(callDetailRecord.getContact().getNumber());
        return callDetailRecordDTO;
    }
}
