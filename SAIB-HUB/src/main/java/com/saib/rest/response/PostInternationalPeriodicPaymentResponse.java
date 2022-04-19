package com.saib.rest.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class PostInternationalPeriodicPaymentResponse extends BaseResponse {}
