# 🎟️ Event Ticket Platform

A full-stack web platform that enables organizers to create and manage events, sell tickets, and validate attendees at entry using QR codes.

---

## 📌 Overview

Event Ticket Platform is a role-based ticketing system built with a Spring Boot backend and a React frontend, secured via Keycloak (OAuth2 / OpenID Connect). It supports the full event lifecycle — from creation and ticket sales to on-site QR code validation.

---

## ✨ Features

- **Event Management** — Organizers can create, update, and delete events with full details including venue, date, time, and ticket types
- **Ticket Sales** — Attendees can browse published events, select ticket types, and purchase tickets
- **QR Code Generation** — Each purchased ticket generates a unique QR code for entry
- **Ticket Validation** — Staff can scan QR codes on-site to verify authenticity and prevent duplicate entry
- **Role-Based Access Control** — Three distinct roles: Organizer, Attendee, and Staff
- **Sales Management** — Automatic cutoff at sales end date, oversell prevention, and a dashboard for tracking revenue and attendance

---

## 🧱 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Frontend | React |
| Auth | Keycloak (OAuth2 / OpenID Connect) |
| Database | PostgreSQL |
| Containerization | Docker / Docker Compose |
| Build Tool | Maven |

---

## 👥 User Roles

| Role | Description |
|---|---|
| **Organizer** | Creates and manages events, monitors ticket sales |
| **Attendee** | Browses events, purchases tickets, retrieves QR codes |
| **Staff** | Scans and validates QR codes at event entry |

---

## 🗂️ Domain Model

```
User
 ├── Organizer   → manages Events
 ├── Attendee    → purchases Tickets
 └── Staff       → validates Tickets at entry

Event
 └── has many TicketTypes
      └── has many Tickets
           ├── has many QRCodes (new one generated on update/reissue)
           └── has many TicketValidations
```

### Key Relationships

- A **User** can have many **Tickets**
- A **Ticket** belongs to one **TicketType**; a **TicketType** has many **Tickets**
- A **Ticket** can have multiple **QR Codes** (new code generated on cancellation or reissue)
- A **Ticket** can have many **TicketValidations**
- An **Organizer** can manage zero or many **Events**; an **Event** must always have an **Organizer**
- A **Staff** member can be assigned to zero or many **Events**
- An **Attendee** can attend zero or many **Events**

---

## 🔌 REST API Reference

### Authentication
> Handled via Keycloak — OAuth2 / OpenID Connect. No custom login endpoint required.

---

### Events

| Method | Endpoint | Role | Description |
|---|---|---|---|
| `POST` | `/api/v1/events` | Organizer | Create a new event |
| `GET` | `/api/v1/events` | Organizer | List all events |
| `GET` | `/api/v1/events/{event_id}` | Organizer | Get a specific event |
| `PUT` | `/api/v1/events/{event_id}` | Organizer | Update an event |
| `DELETE` | `/api/v1/events/{event_id}` | Organizer | Delete an event |

### Tickets

| Method | Endpoint | Role | Description |
|---|---|---|---|
| `GET` | `/api/v1/events/{event_id}/tickets` | Organizer | List tickets for an event |
| `GET` | `/api/v1/events/{event_id}/tickets/{id}` | Organizer | Get a specific ticket |
| `PATCH` | `/api/v1/events/{event_id}/tickets` | Organizer | Update ticket status |

### Ticket Types

| Method | Endpoint | Role | Description |
|---|---|---|---|
| `GET` | `/api/v1/events/{event_id}/ticket-types` | Organizer | List ticket types |
| `GET` | `/api/v1/events/{event_id}/ticket-types/{ticket_type_id}` | Organizer | Get a specific ticket type |
| `PATCH` | `/api/v1/events/{event_id}/ticket-types/{ticket_type_id}` | Organizer | Update a ticket type |
| `DELETE` | `/api/v1/events/{event_id}/ticket-types/{ticket_type_id}` | Organizer | Delete a ticket type |

### Public (Attendee-Facing)

| Method | Endpoint | Role | Description |
|---|---|---|---|
| `GET` | `/api/v1/published-events` | Public | Browse all published events |
| `GET` | `/api/v1/published-events/{event_id}` | Public | Get a specific published event |
| `POST` | `/api/v1/published-events/{event_id}/ticket-types/{ticket_type_id}` | Attendee | Purchase a ticket |
| `GET` | `/api/v1/tickets/{ticket_id}/qr-code` | Attendee | Retrieve ticket QR code |

### Ticket Validation

| Method | Endpoint | Role | Description |
|---|---|---|---|
| `POST` | `/api/v1/ticket-validations` | Staff | Validate a ticket via QR scan or manual input |

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Node.js 18+
- Docker & Docker Compose
- Keycloak instance (can be run via Docker)

### Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/event-ticket-platform.git
cd event-ticket-platform
```

### Run with Docker Compose

```bash
docker-compose up -d
```

This starts PostgreSQL and Keycloak locally.

### Run the Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Run the Frontend

```bash
cd frontend
npm install --legacy-peer-deps
npm run dev
```

---

## 🔐 Security

- Authentication is delegated entirely to **Keycloak** via OAuth2 / OpenID Connect
- The backend is configured as an **OAuth2 Resource Server** that validates JWTs on every request
- Roles (`ORGANIZER`, `STAFF`) are extracted from Keycloak's `realm_access.roles` claim
- Users are automatically provisioned in the local database on first authenticated request
- Sessions are fully **stateless** — no server-side session storage

---

## 📁 Project Structure

```
event-ticket-platform/
├── backend/                  # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/         # Application source code
│   │   │   └── resources/    # Configuration files
│   │   └── test/             # Unit and integration tests
│   ├── docker-compose.yaml
│   └── pom.xml
└── frontend/                 # React application
```

---

## 📄 License

This project is intended for learning purposes and is provided as-is.
