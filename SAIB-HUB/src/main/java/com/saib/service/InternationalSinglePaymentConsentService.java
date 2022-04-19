package com.saib.service;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.saib.common.CommonUtil;
import com.saib.common.Constants;
import com.saib.common.Internationalization;
import com.saib.model.ConsentModel;
import com.saib.repository.ConsentRepo;
import com.saib.rest.request.PostInternationalPaymentConstentRequest;
import com.saib.rest.response.GetDomesticPeriodicConsentByIdResponse;
import com.saib.rest.response.GetInternationalPaymentConstentByIdResponse;
import com.saib.rest.response.PostInternationalPaymentConstentResponse;

@Service
public class InternationalSinglePaymentConsentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalSinglePaymentConsentService.class);

	@Autowired
	private ConsentRepo consentRepo;

	@Autowired
	private Internationalization internationalization;

	public PostInternationalPaymentConstentResponse addInternationalPaymentConsent(
			PostInternationalPaymentConstentRequest request) {
		PostInternationalPaymentConstentResponse response = new PostInternationalPaymentConstentResponse();
		LOGGER.debug("Inside InternationalSinglePaymentConsentService addInternationalPaymentConsent");
		ConsentModel consentModel = new ConsentModel();
		consentModel.setName("International Payment Consent");
		consentModel.setTransactionType(Constants.TRANSACTION_TYPE_SINGLE);
		consentModel.setConsentType(Constants.CONSENT_TYPE_INTL);
		consentModel.setCreatedBy(request.getMerchantName()); 
		consentModel = consentRepo.save(consentModel); 
		if (CommonUtil.isNotNull(consentModel)) {
			response.setResponesMsg(internationalization.messageResource()
					.getMessage("international.payment.constent.create", null, new Locale(request.getLang())));
			response.setResponseCode(HttpStatus.OK.value());

		} else {

			response.setResponesMsg(internationalization.messageResource().getMessage("base.response.not.created", null,
					new Locale(request.getLang())));
			response.setResponseCode(HttpStatus.CONFLICT.value());

		}

		return response;

	}
	
	
	public GetInternationalPaymentConstentByIdResponse getInternationalPaymentConstentById(Long id,String lang) {
		GetInternationalPaymentConstentByIdResponse response = new GetInternationalPaymentConstentByIdResponse();
		ConsentModel consentModel=null;
		LOGGER.debug("Inside InternationalSinglePaymentConsentService getInternationalPaymentConstentById");
		Optional<ConsentModel> constent=consentRepo.findById(id);
		if(constent.isPresent()){ 
			  	consentModel = constent.get();
			  	response.setMerchant(null);
				response.setConsentType(consentModel.getConsentType());
				response.setTransactionType(consentModel.getTransactionType());
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("international.payment.constent.found", null, new Locale(lang)));
				response.setResponseCode(HttpStatus.OK.value());
				
				
		 }else {

				response.setResponesMsg(internationalization.messageResource().getMessage("base.response.not.found", null,
						new Locale(lang)));
				response.setResponseCode(HttpStatus.NOT_FOUND.value());

			}
		
		return response;
		
	}
	
	
	

}
