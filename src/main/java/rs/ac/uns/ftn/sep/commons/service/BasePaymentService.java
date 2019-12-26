package rs.ac.uns.ftn.sep.commons.service;

import rs.ac.uns.ftn.sep.commons.dto.*;

public interface BasePaymentService {

    PaymentStatusResponse getPaymentStatus(PaymentStatusRequest request);

    CreatePaymentResponse createPayment(CreatePaymentRequest request);
}
