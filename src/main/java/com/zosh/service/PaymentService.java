package com.zosh.service;

import java.util.Set;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
// import com.stripe.model.PaymentLink;
import com.zosh.modal.Order;
import com.zosh.modal.PaymentOrder;
import com.zosh.modal.User;

public interface PaymentService {

    PaymentOrder createOrder(User user, Set<Order> Orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;
    PaymentLink createRazorPayPaymentLink(User user, Long amount, Long orderId) throws Exception;
    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
