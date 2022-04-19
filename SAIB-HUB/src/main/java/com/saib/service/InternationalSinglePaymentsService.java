package com.saib.service;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.saib.common.Internationalization;
import com.saib.model.ConsentModel;
import com.saib.repository.ConsentRepo;
import com.saib.rest.request.PostInternationalPaymentRequest;
import com.saib.rest.response.GetInternationalPaymentByIdResponse;
import com.saib.rest.response.GetInternationalPaymentConstentByIdResponse;
import com.saib.rest.response.PostInternationalPaymentResponse;

@Service
public class InternationalSinglePaymentsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalSinglePaymentsService.class);

	@Autowired
	private Internationalization internationalization;

	public PostInternationalPaymentResponse addInternationalPayment(
			PostInternationalPaymentRequest request) {
		PostInternationalPaymentResponse response = new PostInternationalPaymentResponse();
		LOGGER.debug("Inside InternationalSinglePaymentsService addInternationalPayment");
		response.setResponesMsg(internationalization.messageResource()
					.getMessage("international.payment.constent.create", null, new Locale(request.getLang())));
			response.setResponseCode(HttpStatus.OK.value());

		return response;

	}
	
	
	public GetInternationalPaymentByIdResponse getInternationalPaymentById(Long id,String lang) {
		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		LOGGER.debug("Inside InternationalSinglePaymentsService getInternationalPaymentById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("international.payment.constent.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.OK.value());
		return response;
		
	}
	
	
	public GetInternationalPaymentByIdResponse getInternationalPaymentStatusById(Long id,String lang) {
		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		LOGGER.debug("Inside InternationalSinglePaymentsService getInternationalPaymentStatusById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("international.payment.constent.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.OK.value());
		return response;
		
	}
	
	public GetInternationalPaymentByIdResponse deleteInternationalPaymentById(Long id,String lang) {
		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		LOGGER.debug("Inside InternationalSinglePaymentsService deleteInternationalPaymentById");
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("base.response.not.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
		return response;
		
	}
	

}
