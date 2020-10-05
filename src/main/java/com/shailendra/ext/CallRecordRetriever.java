package com.shailendra.ext;

import com.shailendra.dto.CallDetailRecordDTO;
import com.shailendra.dto.PBXSystemDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CallRecordRetriever {

	private static final String URL_PREFIX="http://";
	private static final String SLASH = "/";
	private static final String COLON = ":";
	private static final String GET_CDR = "get_cdr";

	public List<CallDetailRecordDTO> getCallRecords(PBXSystemDTO system) {
		String uri = getCDRURL(system.getHostName(), system.getPortNumber());

		RestTemplate restTemplate = new RestTemplate();
		CDRResponse result = restTemplate.getForObject(uri, CDRResponse.class);

		return Objects.nonNull(result) ? result.getCallDetailRecords() : Arrays.asList();
	}

	private String getCDRURL(String hostName, int port){
		return URL_PREFIX + hostName + COLON + port + SLASH + GET_CDR;
	}
}
