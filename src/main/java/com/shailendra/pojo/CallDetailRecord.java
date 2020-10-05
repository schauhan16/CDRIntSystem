package com.shailendra.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shailendra.enums.CallEvents;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "call_records")
public class CallDetailRecord {

	@Id
	@NonNull
	private String uuid;

	@JsonProperty("domain_name")
	private String domainName;

	@JsonProperty("caller_name")
	private String callerName;

	@JsonProperty("caller_number")
	private long callerNumebr;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "destination_number")
	private Contact contact;

	private String direction;

	@JsonProperty("call_start")
	private Date callStart;

	@JsonProperty("ring_start")
	private Date ringStart;

	@JsonProperty("answer_start")
	private Date answerStart;

	@JsonProperty("call_end")
	private Date callEnd;
	private long duration;
	private String recording;

	@JsonProperty("click_to_call")
	private boolean clickToCall;

	@JsonProperty("click_to_call_data")
	private String clickToCallData;

	@Enumerated(EnumType.STRING)
	private CallEvents action;

	@JsonIgnore
	@UpdateTimestamp
	private Date lastUpdated;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public long getCallerNumebr() {
		return callerNumebr;
	}

	public void setCallerNumebr(long callerNumebr) {
		this.callerNumebr = callerNumebr;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Date getCallStart() {
		return callStart;
	}

	public void setCallStart(Date callStart) {
		this.callStart = callStart;
	}

	public Date getRingStart() {
		return ringStart;
	}

	public void setRingStart(Date ringStart) {
		this.ringStart = ringStart;
	}

	public Date getAnswerStart() {
		return answerStart;
	}

	public void setAnswerStart(Date answerStart) {
		this.answerStart = answerStart;
	}

	public Date getCallEnd() {
		return callEnd;
	}

	public void setCallEnd(Date callEnd) {
		this.callEnd = callEnd;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getRecording() {
		return recording;
	}

	public void setRecording(String recording) {
		this.recording = recording;
	}

	public boolean isClickToCall() {
		return clickToCall;
	}

	public void setClickToCall(boolean clickToCall) {
		this.clickToCall = clickToCall;
	}

	public String getClickToCallData() {
		return clickToCallData;
	}

	public void setClickToCallData(String clickToCallData) {
		this.clickToCallData = clickToCallData;
	}

	public CallEvents getAction() {
		return action;
	}

	public void setAction(CallEvents action) {
		this.action = action;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "CallDetailRecord{" +
				"uuid='" + uuid + '\'' +
				", domainName='" + domainName + '\'' +
				", callerName='" + callerName + '\'' +
				", callerNumebr=" + callerNumebr +
				", contact=" + contact +
				", direction='" + direction + '\'' +
				", callStart=" + callStart +
				", ringStart=" + ringStart +
				", answerStart=" + answerStart +
				", callEnd=" + callEnd +
				", duration=" + duration +
				", recording='" + recording + '\'' +
				", clickToCall=" + clickToCall +
				", clickToCallData='" + clickToCallData + '\'' +
				", action='" + action + '\'' +
				", lastUpdated=" + lastUpdated +
				'}';
	}
}
