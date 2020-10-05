package com.shailendra.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shailendra.dto.CallDetailRecordDTO;

import java.util.List;

public class CDRResponse {

	@JsonProperty
	private long total;

	@JsonProperty("data")
	private List<CallDetailRecordDTO> callRecords;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<CallDetailRecordDTO> getCallDetailRecords() {
		return callRecords;
	}

	public void setCallDetailRecords(List<CallDetailRecordDTO> callRecords) {
		this.callRecords = callRecords;
	}
}
