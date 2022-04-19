package com.saib.rest.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saib.common.CommonUtil;
import com.saib.common.Internationalization;
import com.saib.rest.request.PostInternationalPaymentConstentRequest;
import com.saib.rest.response.GetInternationalPaymentConstentByIdResponse;
import com.saib.rest.response.PostInternationalPaymentConstentResponse;
import com.saib.service.InternationalPeriodicPaymentConsentService;
import com.saib.service.InternationalSinglePaymentConsentService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/international-periodic-payment-consents/")
@OpenAPIDefinition(info = @Info(title = "International periodic payment consent Services.", version = "1.0", description = "All endpoints of international periodic payment consent"))
public class InternationalPeriodicPaymentConsentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalPeriodicPaymentConsentController.class);
	@Autowired
	private Internationalization internationalization;

	@Autowired
	private InternationalPeriodicPaymentConsentService internationalPeriodicPaymentConsentService;

	@Operation(summary = "Create a international periodic payment consent record.", description = "Create a international periodic payment consent record..", tags = "POST")
	@PostMapping(value = "v1", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PostInternationalPaymentConstentResponse> postInternationalPeroidicPaymentConstent(
			@RequestBody PostInternationalPaymentConstentRequest request) {

		PostInternationalPaymentConstentResponse response = new PostInternationalPaymentConstentResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside /v1 postInternationalPeroidicPaymentConstent ");
			if (CommonUtil.isNullOrEmty(request.getMerchantName())) {
				response.setResponseCode(HttpStatus.BAD_REQUEST.value());
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("base.request.marchant.null.empty", null, new Locale(request.getLang())));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(response);
			}

			if (CommonUtil.isNullOrEmty(request.getLang())) {
				response.setResponseCode(HttpStatus.BAD_REQUEST.value());
				response.setResponesMsg(internationalization.messageResource()
						.getMessage("base.request.lang.null.empty", null, new Locale(request.getLang())));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(response);
			}

			response = internationalPeriodicPaymentConsentService.addInternationalPeriodicPaymentConsent(request);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {
			// Response file Logging
			// DataBase Logging (Single Database HIT)
//			LOGGER.debug("Inside getDetailsByBlzCode F" + response.getErrorMessage());

		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}

	// international-payment-consents/{internationalPaymentConsentId}
	@Operation(summary = "Retrieve the international payment consent record to check its status.", description = "Retrieve the international payment consent record to check its status.", tags = "GET")
	@GetMapping(value = "v1/{internationalPeriodicPaymentConsentId}/{lang}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPaymentConstentByIdResponse> getInternationalPeroidicPaymentConstentById(
			@PathVariable Long internationalPeriodicPaymentConsentId, @PathVariable String lang) {

		GetInternationalPaymentConstentByIdResponse response = new GetInternationalPaymentConstentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPeriodicPaymentConsentId}/{lang} getInternationalPeroidicPaymentConstentById ");
			response = internationalPeriodicPaymentConsentService
					.getInternationalPeriodicPaymentConstentById(internationalPeriodicPaymentConsentId, lang);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {
			// Response file Logging
			// DataBase Logging (Single Database HIT)
//			LOGGER.debug("Inside getDetailsByBlzCode F" + response.getErrorMessage());

		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}
}
