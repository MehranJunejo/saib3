package com.saib.rest.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.saib.dto.DetailsType;
import com.saib.rest.request.PostInternationalPaymentConstentRequest;

@JsonInclude(value = Include.NON_EMPTY)
public class PostInternationalPaymentConstentResponse extends BaseResponse {}
