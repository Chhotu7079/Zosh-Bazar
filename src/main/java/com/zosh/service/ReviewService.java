package com.zosh.service;

import java.util.List;

import com.zosh.modal.Product;
import com.zosh.modal.Review;
import com.zosh.modal.User;
import com.zosh.request.CreateReviewRequest;

public interface ReviewService {

    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
