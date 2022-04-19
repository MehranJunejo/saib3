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
import com.saib.rest.request.PostInternationalPaymentRequest;
import com.saib.rest.request.PostInternationalPeriodicPaymentRequest;
import com.saib.rest.response.GetInternationalPaymentByIdResponse;
import com.saib.rest.response.GetInternationalPeriodicPaymentByIdResponse;
import com.saib.rest.response.PostInternationalPeriodicPaymentResponse;
import com.saib.service.InternationalPeriodicPaymentsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/international-periodic-payments/")
@OpenAPIDefinition(info = @Info(title = "International periodic Payment Service", version = "1.0", description = "All endpoints of International periodic Payment"))
public class InternationalPeriodicPaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalPeriodicPaymentController.class);
	@Autowired
	private Internationalization internationalization;

	@Autowired
	private InternationalPeriodicPaymentsService internationalPeriodicPaymentsService;

	@Operation(summary = "Create a international periodic payment record. This API should be used to execute the authorised payment.", description = "Create a international periodic payment record. This API should be used to execute the authorised payment.", tags = "POST")
	@PostMapping(value = "v1", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PostInternationalPeriodicPaymentResponse> addInternationalPeriodicPayment(
			@RequestBody PostInternationalPeriodicPaymentRequest request) {

		PostInternationalPeriodicPaymentResponse response = new PostInternationalPeriodicPaymentResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside /v1 postInternationalPayment ");
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

			response = internationalPeriodicPaymentsService.addInternationalPeriodicPayment(request);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {

		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}

	// international-payment-consents/{internationalPaymentConsentId}
	@Operation(summary = "Retrieve the international periodic payment details.", description = "Retrieve the international periodic payment details.", tags = "GET")
	@GetMapping(value = "v1/{internationalPeriodicPaymentId}/{lang}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPeriodicPaymentByIdResponse> getInternationalPeriodicPaymentById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPeriodicPaymentId}/{lang} getInternationalPeriodicPaymentById ");
			response = internationalPeriodicPaymentsService.getInternationalPeriodicPaymentById(internationalPaymentConsentId,
					lang);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {
		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}


	@Operation(summary = "Retrieve the international periodic payment record to check its status.", description = "Retrieve the international periodic payment record to check its status.", tags = "GET")
	@GetMapping(value = "v1/{internationalPeriodicPaymentId}/{lang}/status", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPeriodicPaymentByIdResponse> getInternationalPeriodicPaymentStatusById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPeriodicPaymentId}/{lang}/status getInternationalPeriodicPaymentStatusById ");
			response = internationalPeriodicPaymentsService.getInternationalPeriodicPaymentStatusById(internationalPaymentConsentId,
					lang);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {
		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}
	
	@Operation(summary = "Cancel a international periodic payment record", description = "Cancel a international periodic payment record", tags = "DELETE")
	@GetMapping(value = "v1/{internationalPeriodicPaymentId}/{lang}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPeriodicPaymentByIdResponse> deleteInternationalPeriodicPaymentById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPeriodicPaymentByIdResponse response = new GetInternationalPeriodicPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPeriodicPaymentId}/{lang}/status deleteInternationalPeriodicPaymentById ");
			response = internationalPeriodicPaymentsService.deleteInternationalPeriodicPaymentById(internationalPaymentConsentId,
					lang);

		} catch (Throwable e) {

			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponesMsg("Something went wrong.");
			response.setErrorMessage(CommonUtil.getException(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} finally {
		}

		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

	}
	
}
