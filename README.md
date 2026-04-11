# E-Commerce Backend
---
A RESTful backend API built with Java Spring Boot that powers an Amazon-inspired e-commerce platform. It exposes endpoints for retrieving products and managing orders, backed by an H2 in-memory database that is automatically initialized with table creation and data seeding on startup. The backend handles the full order lifecycle, from receiving cart data to persisting Order and OrderItem records.

**Technologies**
---
- Java
- Spring Boot
- Spring Framework (Spring MVC, Spring Data JPA)
- Hibernate (JPA implementation)
- H2 (in-memory database)
- Maven (via wrapper)

**Features**
---
- Product Endpoints: Exposes GET /api/products to fetch all products, GET /api/products/{id} to fetch a single product, and GET /api/products/search?query= to search products by name case-insensitively.
- Order Endpoints: Exposes GET /api/orders to fetch all orders, GET /api/orders/{id} to fetch a specific order, POST /api/orders to create a new order, POST /api/orders/{orderId}/items to add an order item linked to an existing order and product, and DELETE /api/orders/{id} to remove an order.
- Cascade Persistence: When an OrderItem is added to an Order, JPA's CascadeType.ALL automatically inserts it into the database, with the order and product stored as foreign keys.
- Auto Table Generation: Hibernate automatically creates all database tables from entity definitions on startup and seeds them with initial product data via data.sql.
- In-Memory Database: Uses H2 to run a lightweight database entirely in memory with no external installation required, accessible via a browser console at /h2-console during development.
- CORS Support: All endpoints allow cross-origin requests, enabling the frontend to communicate with the backend without browser security restrictions.

🤖 **Frontend Repository**  
---
👉 [E-Commerce Frontend](https://github.com/TapJc/ecommerce-frontend)
