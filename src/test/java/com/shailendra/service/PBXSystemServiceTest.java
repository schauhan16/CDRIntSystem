package com.shailendra.service;

import com.shailendra.dto.PBXSystemDTO;
import com.shailendra.main.CDRSystemApplication;
import com.shailendra.repo.PBXSystemRepo;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = CDRSystemApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class PBXSystemServiceTest {

    @Autowired
    private PBXSystemService pbxSystemService;

    @Autowired
    private PBXSystemRepo pbxSystemRepo;

    @MockBean
    private CallRecordService callRecordService;

    @After
    public void cleanup(){
        pbxSystemRepo.deleteAll();
    }

    private PBXSystemDTO createPBXSystemDTO(){
        return new PBXSystemDTO("localhost, 3030);
    }

    @Test
    public void testRegisterSystem(){
        PBXSystemDTO system = createPBXSystemDTO();
        Mockito.when(callRecordService.syncCDRWithPBXSystem(system)).thenReturn(Arrays.asList());
        PBXSystemDTO savedEntity = pbxSystemService.addPBX(system);

        assertEquals(1, pbxSystemService.getAllRegisteredSystem().size());
    }

    @Test
    public void testUnRegisterSystem(){
        PBXSystemDTO system = createPBXSystemDTO();
        Mockito.when(callRecordService.syncCDRWithPBXSystem(system)).thenReturn(Arrays.asList());

        PBXSystemDTO savedEntity = pbxSystemService.addPBX(system);
        assertEquals(1, pbxSystemService.getAllRegisteredSystem().size());

        pbxSystemService.unregisterPBX(system.getHostName(), system.getPortNumber());
        assertEquals(0, pbxSystemService.getAllRegisteredSystem().size());
    }
}
