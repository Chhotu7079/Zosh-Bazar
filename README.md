This e-commerce platform is a fully-featured system designed to serve customers, sellers, and administrators. 
The project leverages modern technologies and robust frameworks to deliver a secure, scalable, and user-friendly shopping experience. 
Below is an in-depth explanation of the platform's components and features:

>**Technology Stack**

__Backend:_
Spring Boot: Handles business logic, REST APIs, and service layers.
MySQL: Stores data for users, products, orders, and transactions.
Spring Security: Ensures secure user authentication and role-based access.
JWT (JSON Web Tokens): Secures API communication with token-based authentication.
Java Mail Sender: Sends automated emails, such as order confirmations and notifications.

__Payment Gateways:_
Razorpay: Integrated for Indian users to process local payments.
Stripe: Used for international users to handle global transactions.

__Features for Customers__
1. Chatbot for Queries
An AI-powered chatbot assists customers with:
Order History: Provides details of past purchases.
Cart Information: Displays current cart contents and prices.
Product Details: Offers instant answers about product specifications.
2. Product Management
Fetch Product List: Displays all available products to customers.
Filter & Sort: Customers can filter products by categories, price range, or other attributes and sort them by price or relevance.
Pagination: Products are divided into pages, improving page loading speed and user experience.
Product Details: Offers comprehensive information about individual products, including descriptions, specifications, images, and pricing.
3. Cart Management
Add Item to Cart: Customers can select products and add them to their cart.
Update Cart Item: Modify the quantity of items or remove products from the cart.
4. Checkout Process
Apply Coupons: Customers can use promotional codes to avail of discounts.
Add New Address: Supports multiple shipping addresses, which can be added or managed during checkout.
Payment Gateways: Offers secure payment options via Razorpay (for Indian users) or Stripe (for international users).
5. Order History
View Past Orders: Customers can access a list of their completed and pending orders, including details such as product names, quantities, prices, and delivery dates.
Cancel Order: Option to cancel an order within a specific timeframe.
6. User Account Management
Personal Details: Update profile information such as name, email, and contact details.
Order Tracking: Monitor the status of current and previous orders.
Account Settings: Manage preferences and account security.
7. Reviews & Wishlist
Write Reviews: Customers can provide feedback and ratings for purchased products.
Wishlist: Add and remove products to/from the wishlist for future purchases.

__Features for Sellers__
1. Seller Dashboard
Displays an overview of the seller’s business performance through:
Earning Graphs: Visual representation of earnings for today, the last 7 days, and the last 12 months.
Seller Reports:
Total Sales: Number of products sold.
Total Earnings: Revenue generated.
Refunds & Cancellations: Metrics on returned and canceled orders.
2. Product Management
Create Products: Sellers can add new product listings, including details like name, category, price, description, and images.
3. Order Management
View and manage customer orders, including order status updates, shipping details, and handling returns or cancellations.
4. Payment & Transactions
Track Payments: Sellers can monitor incoming payments for their products.
Transaction History: Detailed log of completed transactions for reference and financial tracking.
5. Seller Account Management
Update profile information such as store name, contact details, and business address.

__Features for Admin__
1. Admin Dashboard
A central hub for administrators to view platform-wide performance metrics, such as sales, active users, and traffic.
2. Seller Management
Approve or suspend seller accounts based on compliance with platform policies.
3. Coupon Management
Create, Edit, and Delete Coupons: Control the promotional codes available to customers for discounts.
4. Home Page Management
Customize the homepage layout, banners, and featured products to enhance user engagement.
5. Deal Management
Update and manage promotional deals and offers displayed on the homepage.

>__Platform Goals__
User Experience: Offers an intuitive and efficient interface for customers, sellers, and admins, ensuring easy navigation and task completion.
Performance: Incorporates features like pagination and optimized APIs to ensure fast loading times and smooth operations.
Security: Utilizes Spring Security and JWT for secure data access and user authentication.
Scalability: Supports multiple roles and functionalities, with provisions for future enhancements.
Global Reach: With dual payment gateways, the platform serves both domestic and international audiences.

**This project is a robust, full-featured e-commerce platform that combines ease of use, security, and scalability.
Its modular design caters to diverse user roles, making it a versatile solution for businesses aiming to establish a strong online presence.**
