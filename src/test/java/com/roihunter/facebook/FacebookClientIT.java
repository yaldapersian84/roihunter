package com.roihunter.facebook;

import com.roihunter.facebook.config.FacebookConfigs;
import com.roihunter.facebook.exception.PspClientException;
import com.roihunter.facebook.util.WiremockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ContextConfiguration
@TestPropertySource({"classpath:application-test.properties", "classpath:facebook/test.properties"})
@ExtendWith({SpringExtension.class, WiremockExtension.class})
//@EnableContainers(images = {ImageType.MySQL, ImageType.Wiremock}, autoReset = true)
public class FacebookClientIT {
	@Autowired
	private FacebookClient client;

	@Autowired
	private FacebookConfigs configs;


	@Test
	void authenticate() throws PspClientException {
		UserDataResponse response = client.getData(configs.getFbId(), configs.getFbAccessToken());

		assertThat(response).isNotNull();
		assertThat(response.getName()).isNotBlank();
	}

	//	@Test
//	void authenticate_failedNoInput() {
//		BankClientException exception = assertThrows(PspClientException.class, () -> client.authenticate(null));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_BANK_UNKNOWN_ERROR);
//	}
//
//	@Test
//	void authenticate_failedInvalidCredentials() {
//		PspClientException exception = assertThrows(PspClientException.class, () -> client.authenticate(new AnsarAuthRequest("invalid_username", "invalid_pass")));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_ACCESS_ERROR);
//	}
//
//	@Test
//	void cardProfile() throws PspClientException {
////        AnsarCardProfileRequest request = new MiddleCardProfileRequest();
////        request.setPan("5859471010316820");
////
////        AnsarCardProfileResponse response = client.cardProfile(request, response1.getSessionId());
////
////        assertThat(response).isNotNull();
//	}
//
//	@Test
//	void cardTransfer() throws PspClientException {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		AnsarCardTransferResponse response = client.cardTransfer(request, "1e1711a3-baef-48c6-8f9e-751f21361b31");
//
//		assertThat(response).isNotNull();
//		assertThat(response.getReferenceNumber()).isNotNull();
//	}
//
//	@Test
//	void cardTransfer_invalidSession() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, "invalid_session_id"));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_ACCESS_ERROR);
//		assertThat(exception.getBankErrorMessage()).isEqualTo("InvalidSessionWSException");
//	}
//
//	@Test
//	void cardTransfer_noSession() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, null));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_ACCESS_ERROR);
//		assertThat(exception.getBankErrorMessage()).contains("ServiceAccessIsDeniedWSException");
//	}
//
//	@Test
//	void cardTransfer_notEnoughCredit() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.INSUFFICIENT_AMOUNT);
//	}
//
//	@Test
//	void cardTransfer_amountExceeded() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(35000000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_CARD_TRANSFER_AMOUNT_EXCEEDED);
//	}
//
//	@Test
//	void cardTransfer_invalidAmount() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, "304fda07-2f90-485e-8d42-244b753cce7d"));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_BANK_UNKNOWN_ERROR);
//	}
//
//	@Test
//	void cardTransfer_invalidCard() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6243814621796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_BANK_UNKNOWN_ERROR);
//	}
//
//	@Test
//	void cardTransfer_invalidPin() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("4654645");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_CARD_INVALID);
//	}
//
//	@Test
//	void cardTransfer_invalidCvv2() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("555");
//		request.setPin2("111360");
//		request.setExpireDate("0006");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_CARD_INVALID);
//	}
//
//	@Test
//	@Disabled
//	void cardTransfer_invalidFormatExpireDate() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("1366");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_CARD_INVALID);
//	}
//
//	@Test
//	void cardTransfer_invalidExpireDate() {
//		AnsarCardTransferRequest request = new AnsarCardTransferRequest();
//		request.setSourcePan("6273811121796780");
//		request.setTargetPan("5859471010316820");
//		request.setCvv2("139");
//		request.setPin2("111360");
//		request.setExpireDate("0012");
//		request.setAmount(1000L);
//
//		BankClientException exception = assertThrows(BankClientException.class, () -> client.cardTransfer(request, ""));
//
//		assertThat(exception.getResultStatus()).isEqualTo(ResultStatus.BANKPROXY_CARD_INVALID);
//	}
//
	@Configuration
	@EnableAspectJAutoProxy(proxyTargetClass = true)
	@ComponentScan(basePackageClasses = {FacebookClient.class, FacebookConfigs.class})
	public static class Configs {
	}
}
