package com.shailendra.service;

import com.shailendra.dto.PBXSystemDTO;
import com.shailendra.pojo.PBXSystem;
import com.shailendra.repo.PBXSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PBXSystemService {

    @Autowired
    private PBXSystemRepo pbxSystemRepo;

    @Autowired
    private CallRecordService callRecordService;

    private ModelMapper modelMapper = new ModelMapper();

    public List<PBXSystemDTO> getAllRegisteredSystem(){
        return ((List<PBXSystem>) pbxSystemRepo.findAll())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PBXSystemDTO addPBX(PBXSystemDTO systemDTO) {
         PBXSystem system = pbxSystemRepo.save(convertToEntity(systemDTO));
         callRecordService.syncCDRWithPBXSystem(systemDTO);
         return convertToDTO(system);
    }

    public PBXSystemDTO getPBX(String hostName, int port) {
        return convertToDTO(pbxSystemRepo.findByHostNameAndPortNumber(hostName, port));
    }

    public void unregisterPBX(String hostName, int port) {
        PBXSystem system = getPBXEntity(hostName, port);
        pbxSystemRepo.delete(system);
    }

    private PBXSystem getPBXEntity(String hostName, int port) {
        return pbxSystemRepo.findByHostNameAndPortNumber(hostName, port);
    }

    private PBXSystem convertToEntity(PBXSystemDTO pbxSystemDTO) {
        return modelMapper.map(pbxSystemDTO, PBXSystem.class);
    }

    private PBXSystemDTO convertToDTO(PBXSystem pbxSystem){
        return modelMapper.map(pbxSystem, PBXSystemDTO.class);
    }
}
