package com.shailendra.controller;

import com.shailendra.dto.PBXSystemDTO;
import com.shailendra.service.PBXSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pbxSystem")
public class PBXSystemController {

    @Autowired
    private PBXSystemService pbxSystemService;

    @GetMapping("all")
    public List<PBXSystemDTO> getAll() {
        return pbxSystemService.getAllRegisteredSystem();
    }

    @GetMapping("get/{hostName}/{portNumber}")
    public PBXSystemDTO getByHostAndPort(@PathVariable("hostName") String hostName, @PathVariable("portNumber") int port) {
        return pbxSystemService.getPBX(hostName, port);
    }

    @PostMapping("register")
    @ResponseBody
    public PBXSystemDTO registerPBXSystem(@RequestBody PBXSystemDTO system) {
        return pbxSystemService.addPBX(system);
    }

    @GetMapping("unregister/{hostName}/{portNumber}")
    public void unregisterByHostAndPort(@PathVariable("hostName") String hostName, @PathVariable("portNumber") int port) {
        pbxSystemService.unregisterPBX(hostName, port);
    }
}
