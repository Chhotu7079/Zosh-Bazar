package com.zosh.controller;

import java.net.ResponseCache;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.service.climate.OrderService;
import com.zosh.exception.SellerException;
import com.zosh.modal.Order;
import com.zosh.modal.PaymentOrder;
import com.zosh.modal.Seller;
import com.zosh.modal.SellerReport;
import com.zosh.modal.User;
import com.zosh.response.ApiResponse;
import com.zosh.response.PaymentLinkResponse;
import com.zosh.service.PaymentService;
import com.zosh.service.SellerReportService;
import com.zosh.service.SellerService;
import com.zosh.service.TransactionService;
import com.zosh.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    // private final OrderService orderService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;


    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt) throws Exception{

    User user = userService.findUserByJwtToken(jwt);
    PaymentLinkResponse paymentLinkResponse;
    PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

    boolean paymentSuccess = paymentService.ProceedPaymentOrder(paymentOrder, paymentId, paymentLinkId);

    if (paymentSuccess) {
        for(Order order:paymentOrder.getOrders()){
            transactionService.createTransaction(order);
            Seller seller = sellerService.getSellerById(order.getSellerId());
            SellerReport report = sellerReportService.getSellerReport(seller);
            report.setTotalOrders(report.getTotalOrders());
            report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
            report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
            sellerReportService.updaSellerReport(report);
        }
    }

    ApiResponse res = new ApiResponse();
    res.setMessage("Payment Successfull");

     return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
}
