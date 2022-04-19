package com.saib.rest.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.saib.dto.DetailsType;

@JsonInclude(value = Include.NON_EMPTY)
public class GetDetailsByBlzCodeResponse extends BaseResponse {



	@Override
	public String toString() {
		return "GetDetailsByBlzCodeResponse [responseCode=" + responseCode + ", responesMsg=" + responesMsg
				+ ", bankDetails=" + bankDetails + "]";
	}

	private Integer responseCode;
	private String responesMsg;
	private DetailsType bankDetails;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponesMsg() {
		return responesMsg;
	}

	public void setResponesMsg(String responesMsg) {
		this.responesMsg = responesMsg;
	}

	public DetailsType getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(DetailsType bankDetails) {
		this.bankDetails = bankDetails;
	}

}
