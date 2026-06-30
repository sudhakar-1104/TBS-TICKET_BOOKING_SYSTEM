# TicketApp

A console-based event ticket booking system written in Java. Users can sign up, log in, browse events by city/date/category, view seat maps, book and pay for seats, generate tickets, and write or edit reviews for events they've attended.

## Features

- **User accounts** — sign-up with duplicate username/email checks and basic email format validation; login authentication against an in-memory user list.
- **Event browsing** — filter events by city (Coimbatore or Bangalore), date, and category (Movie, Sport, Comedy Show, Concert).
- **Visual seat maps** — `Event.displayAvailableSeats()` renders an ASCII seat layout per event type (a circular stadium layout for Sport, a stage/FanZone layout for Concert, a screen layout for Movie, and a comedian-stage layout for Comedy Show).
- **Seat booking & checkout** — select any number of seats, choose a payment mode (net banking or card), and confirm payment.
- **Simulated background processing** — `Payment` and `Ticket` run on separate `Thread`s implementing `Runnable`, simulating asynchronous payment processing and ticket generation.
- **Booking history** — every booking is recorded against the user and can be reviewed at any time.
- **Reviews & ratings** — users can leave one review per attended booking, edit existing reviews, and an event's average rating is computed live from all its reviews.
- **Profile management** — users can edit their username, password, name, and email after logging in.

## Requirements

- Java Development Kit (JDK) 8 or later (uses `ArrayList`, `Scanner`, lambda-free stream usage, and basic threading — no external dependencies).

## How to Run

```bash
javac TicketApp.java
java TicketApp
```

The application starts an interactive text menu in the terminal. All input is read via `Scanner`, so the program expects numeric menu choices and free-text entries (names, comments, etc.) typed directly into the console.

## Sample Data

On startup, `main()` seeds the application with sample data so it can be explored immediately:

- 16 venues across Coimbatore and Bangalore.
- 22 events spanning four categories (Movie, Sport, Comedy Show, Concert), each scheduled for one of two dates (`15-12-2023` or `16-12-2023`).
- Seats generated per event type, each with its own pricing tier (e.g. Movie seats at Rs. 150, Sport seats at Rs. 400, Concert FanZone at Rs. 200 vs. Normal at Rs. 100, Comedy Show seats at Rs. 100).
- 5 pre-registered users, each with sample bookings and reviews already attached so booking history and review flows can be tested without manual setup.

### Sample login credentials

| Username    | Password |
|-------------|----------|
| jd          | 123      |
| sudhakar    | abc      |
| siddharth   | 456      |
| suren       | def      |
| praysun     | 789      |

You can also choose **Sign Up** from the main menu to create a new account.

## Application Flow

1. **Main menu** — Login, Sign Up, or Exit.
2. **After login** — View profile, view booking history, view your reviews, book a ticket, edit profile, write a review, or sign out.
3. **Booking a ticket** — choose city → date → category → event → number of seats → specific seat numbers (validated against the live seat map) → payment method → confirmation.
4. **After a successful booking** — payment and ticket generation each run on a background thread before the booking confirmation and ticket ID are printed.

## Architecture

The codebase is a single file (`TicketApp.java`) containing one public class and several supporting classes/interfaces.

### Interfaces

| Interface | Purpose |
|---|---|
| `Displayable` | Implemented by any class that needs a standard `displayDetails()` console output (e.g. `User`, `Event`, `Booking`, `Review`, `BookingHistory`). |
| `Runnable` | Implemented by `Payment` and `Ticket` to simulate asynchronous processing on background threads. |

### Domain classes

| Class | Responsibility |
|---|---|
| `User` | Account credentials, profile data, owns a `BookingHistory` and a list of `Review`s. Handles profile editing, writing/listing reviews, and viewing booking history. |
| `Venue` | A physical location (ID, name, city). |
| `Event` | A bookable event (movie, match, comedy show, or concert) tied to a `Venue`, with its own seat inventory, review list, and computed average rating. Renders an ASCII seat map based on event type. |
| `Seat` | A single seat with number, type, price, and availability flag. |
| `Booking` | A confirmed reservation linking a `User`, an `Event`, and a list of `Seat`s; computes total price and can cancel itself (releasing seats back to availability). |
| `BookingHistory` | The ordered list of a user's `Booking`s. |
| `Review` | A rating + comment tied to a specific `Booking`; supports in-place editing. |
| `Payment` | Simulates payment processing on a background thread (`Thread.sleep`). |
| `Ticket` | Simulates ticket generation on a background thread once payment succeeds. |

### `TicketApp` (entry point)

Holds `main()` plus the procedural booking workflow:

- `booktickets(...)` — drives the city → date → category → event selection loop.
- `bookevent(...)` — drives seat selection, payment, and ticket generation for a chosen event.
- `login(...)` — validates credentials against the in-memory user list.
- `createAccount(...)` — constructs a new `User` for sign-up.

## Known Limitations

This project favors a straightforward, single-file procedural design and has several areas that could be hardened for production use:

- **No persistence** — all data (users, events, bookings, reviews) lives in memory and is lost when the program exits.
- **Plaintext passwords** — passwords are stored and compared as plain strings; no hashing is applied.
- **Limited input validation** — `Scanner.nextInt()` calls will throw `InputMismatchException` on non-numeric input, and most menus don't recover gracefully from malformed input.
- **String comparison with `==`** — a few event-type comparisons in `booktickets()` use `==` instead of `.equals()`, which works only because of Java string interning but is not a reliable pattern in general.
- **Hard-coded sample data** — venues, events, dates, and seat pricing are hard-coded into `main()` rather than loaded from a configuration source or database.
- **Single-threaded UI with cosmetic concurrency** — `Payment` and `Ticket` run on real threads, but the main thread blocks on `join()` immediately afterward, so there is no actual concurrent benefit; it's used purely to simulate processing delay.

