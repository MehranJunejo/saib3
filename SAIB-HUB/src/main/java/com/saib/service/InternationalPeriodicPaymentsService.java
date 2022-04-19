package com.saib.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.saib.common.Internationalization;
import com.saib.rest.request.PostInternationalPeriodicPaymentRequest;
import com.saib.rest.response.GetInternationalPeriodicPaymentByIdResponse;
import com.saib.rest.response.PostInternationalPeriodicPaymentResponse;

@Service
public class InternationalPeriodicPaymentsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalPeriodicPaymentsService.class);

	@Autowired
	private Internationalization internationalization;

	public PostInternationalPeriodicPaymentResponse addInternationalPeriodicPayment(
			PostInternationalPeriodicPaymentRequest request) {
		PostInternationalPeriodicPaymentResponse response = new PostInternationalPeriodicPaymentResponse();
		LOGGER.debug("Inside InternationalPeriodicPaymentsService addInternationalPeriodicPayment");
		response.setResponesMsg(internationalization.messageResource()
					.getMessage("international.payment.constent.create", null, new Locale(request.getLang())));
			response.setResponseCode(HttpStatus.OK.value());

		return response;

	}
	
	
	public GetInternationalPeriodicPaymentByIdResponse getInternationalPeriodicPaymentById(Long id,String lang) {
		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		LOGGER.debug("Inside InternationalPeriodicPaymentsService getInternationalPeriodicPaymentById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("international.payment.constent.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.OK.value());
		return response;
		
	}
	
	
	public GetInternationalPeriodicPaymentByIdResponse getInternationalPeriodicPaymentStatusById(Long id,String lang) {
		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		LOGGER.debug("Inside InternationalPeriodicPaymentsService getInternationalPeriodicPaymentStatusById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("international.payment.constent.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.OK.value());
		return response;
		
	}
	
	public GetInternationalPeriodicPaymentByIdResponse deleteInternationalPeriodicPaymentById(Long id,String lang) {
		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		LOGGER.debug("Inside InternationalPeriodicPaymentsService deleteInternationalPeriodicPaymentById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("base.response.not.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
		return response;
		
	}
	

}
