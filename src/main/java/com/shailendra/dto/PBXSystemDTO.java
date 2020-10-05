package com.shailendra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PBXSystemDTO {

    @JsonProperty("host_name")
    private String hostName;

    @JsonProperty("port_number")
    private int portNumber;

    public PBXSystemDTO() {
    }

    public PBXSystemDTO(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
