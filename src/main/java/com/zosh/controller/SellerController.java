package com.zosh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.domain.AccountStatus;
import com.zosh.exception.SellerException;
import com.zosh.modal.Seller;
import com.zosh.modal.SellerReport;
import com.zosh.modal.VerificationCode;
import com.zosh.repository.VerificationCodeRepository;
import com.zosh.request.LoginRequest;
import com.zosh.response.AuthResponse;
import com.zosh.service.AuthService;
import com.zosh.service.EmailService;
import com.zosh.service.SellerReportService;
import com.zosh.service.SellerService;
import com.zosh.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final AuthService authService;
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception{

        String email = req.getEmail();

        // VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        // if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
        //     throw new Exception("Wrong otp...");
        // }

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);

    }


    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(
            @PathVariable String otp) throws Exception{


        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp...");
        }        

        Seller seller = sellerService.VerifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(
             @RequestBody Seller seller) throws Exception{

         Seller savedSeller = sellerService.createSeller(seller);
         
         String otp = OtpUtil.generateOtp();

         VerificationCode verificationCode = new VerificationCode();
         verificationCode.setOtp(otp);
         verificationCode.setEmail(seller.getEmail());
         verificationCodeRepository.save(verificationCode);

         String subject = "Zosh Bazar Email Verification Code";
         String text = "Welcome to Zosh Bazar, Verify your acoount using this link ";
         String frontend_url = "http://localhost:3000/verify-seller/";
         emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text + frontend_url);

         return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
         

    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException{
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }
    

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception{

        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport report = sellerReportService.getSellerReport(seller);

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(
        @RequestParam(required = false) AccountStatus status){
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(
        @RequestHeader("Authorization") String jwt,
        @RequestBody Seller seller) throws Exception{

        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updatedSeller(profile.getId(),seller);

        return ResponseEntity.ok(updatedSeller);
    }

    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception{
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
    
    



}
