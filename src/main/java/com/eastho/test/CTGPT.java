package com.eastho.test;


import java.util.*;



class CTGPT {
/*
	결제 시스템은 동일한 카드로 동시에 여러 결제가 시도될 경우 중복 결제를 방지해야 합니다.
	다음 조건을 만족하는 결제 승인 서비스를 구현하세요.
	요구사항
	각 결제 요청에는 다음 정보가 포함됩니다:
		transactionId (결제 요청 고유 ID, 문자열)
		cardNumber (카드 번호, 문자열)
		amount (결제 금액, long)
		timestamp (결제 요청 시각, millisecond)

	결제 승인 로직:
		동일한 cardNumber로 3초(3000ms) 이내에 들어온 다른 transactionId 요청은 중복 결제로 간주하여 거부(DENIED)해야 합니다.
		그렇지 않으면 결제를 승인(APPROVED)합니다.

	성능 요구사항:
		초당 최대 100,000건의 요청을 처리할 수 있어야 하며, 메모리를 효율적으로 사용해야 합니다.
	입력
		결제 요청 리스트 (List<PaymentRequest>)가 주어짐
		각 요청을 순서대로 처리하며 승인/거부 결과를 반환
	출력
		요청 순서대로 "APPROVED" 또는 "DENIED" 문자열 리스트를 반환
*/

    public static void main(String[] args) {
        Map<String, PaymentRequest> latestPaymentMap = new HashMap<>();

        List<PaymentRequest> paymentRequestList = List.of(
                new PaymentRequest("1", "1234-5678-9999", 1000, 1000),
                new PaymentRequest("2", "1234-5678-9999", 2000, 2001),
                new PaymentRequest("3", "9876-1111-2222", 500, 1500),
                new PaymentRequest("4", "1234-5678-9999", 3000, 4500)
        );

        for (PaymentRequest request : paymentRequestList) {
            PaymentRequest last = latestPaymentMap.get(request.cardNumber);

            if (last == null) {
                System.out.println("APPROVED");
                latestPaymentMap.put(request.cardNumber, request);
                continue;
            }

            long diff = request.timestamp - last.timestamp;
            if (request.timestamp > last.timestamp && diff > 3000) {
                System.out.println("APPROVED");
                latestPaymentMap.put(request.cardNumber, request);
            } else {
                System.out.println("DENIED");
            }
        }
	}
	static class PaymentRequest {
		String transactionId;
		String cardNumber;
		long amount;
		long timestamp;

		public PaymentRequest(String transactionId, String cardNumber, long amount, long timestamp) {
			this.transactionId = transactionId;
			this.cardNumber = cardNumber;
			this.amount = amount;
			this.timestamp = timestamp;
		}
	}
}
