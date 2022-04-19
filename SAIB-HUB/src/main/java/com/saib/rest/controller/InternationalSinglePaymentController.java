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
import com.saib.rest.request.PostInternationalPaymentRequest;
import com.saib.rest.response.GetInternationalPaymentByIdResponse;
import com.saib.rest.response.GetInternationalPaymentConstentByIdResponse;
import com.saib.rest.response.PostInternationalPaymentConstentResponse;
import com.saib.rest.response.PostInternationalPaymentResponse;
import com.saib.service.InternationalSinglePaymentConsentService;
import com.saib.service.InternationalSinglePaymentsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/international-payments/")
@OpenAPIDefinition(info = @Info(title = "International Single Payment Service", version = "1.0", description = "All endpoints of International Single Payment"))
public class InternationalSinglePaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalSinglePaymentController.class);
	@Autowired
	private Internationalization internationalization;

	@Autowired
	private InternationalSinglePaymentsService internationalSinglePaymentsService;

	@Operation(summary = "Create a international payment record. This API should be used to execute the authorised payment.", description = "Create a international payment record. This API should be used to execute the authorised payment.", tags = "POST")
	@PostMapping(value = "v1", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PostInternationalPaymentResponse> postInternationalPayment(
			@RequestBody PostInternationalPaymentRequest request) {

		PostInternationalPaymentResponse response = new PostInternationalPaymentResponse();
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

			response = internationalSinglePaymentsService.addInternationalPayment(request);

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
	@Operation(summary = "Retrieve the international payment details.", description = "Retrieve the international payment details.", tags = "GET")
	@GetMapping(value = "v1/{internationalPaymentId}/{lang}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPaymentByIdResponse> getInternationalPaymentById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPaymentId}/{lang} getInternationalPaymentById ");
			response = internationalSinglePaymentsService.getInternationalPaymentById(internationalPaymentConsentId,
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


	@Operation(summary = "Retrieve the international payment record to check its status.", description = "Retrieve the international payment record to check its status.", tags = "GET")
	@GetMapping(value = "v1/{internationalPaymentId}/{lang}/status", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPaymentByIdResponse> getInternationalPaymentStatusById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPaymentId}/{lang}/status getInternationalPaymentStatusById ");
			response = internationalSinglePaymentsService.getInternationalPaymentStatusById(internationalPaymentConsentId,
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
	
	@Operation(summary = "Cancel a international payment record", description = "Cancel a international payment record", tags = "DELETE")
	@GetMapping(value = "v1/{internationalPaymentId}/{lang}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GetInternationalPaymentByIdResponse> deleteInternationalPaymentById(
			@PathVariable Long internationalPaymentConsentId, @PathVariable String lang) {

		GetInternationalPaymentByIdResponse response = new GetInternationalPaymentByIdResponse();
		HttpHeaders httpHeaders = new HttpHeaders();

		try {

			LOGGER.debug("Inside v1/{internationalPaymentId}/{lang}/status deleteInternationalPaymentById ");
			response = internationalSinglePaymentsService.deleteInternationalPaymentById(internationalPaymentConsentId,
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
