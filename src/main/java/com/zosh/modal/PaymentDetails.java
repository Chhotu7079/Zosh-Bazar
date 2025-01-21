package com.zosh.modal;

import com.zosh.domain.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPayLinkReferenceId;
    private String razorpayPayLinkStatus;
    private String razorpayPayIdZWSP;
    private PaymentStatus status;

}
