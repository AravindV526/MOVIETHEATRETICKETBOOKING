# 🎬 Movie Ticket Booking App (JavaFX)

A simple **Movie Ticket Booking System** built with **JavaFX**.  
Users can register/login, select their state, district, movie, showtime, and seats, then generate and save a ticket as a text file.

---

## ✨ Features

- 🔑 **User Authentication** (Register & Login)
- 🌍 **State & District Selection** (covers Indian states & districts)
- 🎥 **Movie Selection** (multi-language support)
- ⏰ **Showtime Selection** (from morning to late night)
- 💺 **Seat Booking System**
  - Select multiple seats
  - Highlights chosen seats in green
- 💳 **Payment Simulation**
  - Calculates total amount (₹150 per ticket)
- 📝 **Ticket Generation**
  - Saves ticket details to a `.txt` file
  - Includes state, district, movie, time, seats, and total price
- 🔄 **App Reset** after booking

---

## 🛠 Tech Stack

- **Java 8+**
- **JavaFX** (UI framework)
- **File I/O** for saving tickets
- **Collections API** (HashMap, ArrayList)

---

MovieTicketBookingApp.java # Main JavaFX application 
---

## ▶️ How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/movie-ticket-booking.git
Open the project in your IDE (IntelliJ IDEA, Eclipse, or VS Code with JavaFX support).

Make sure JavaFX libraries are configured in your IDE.

Run the application: 
javac MovieTicketBookingApp.java
java MovieTicketBookingApp 
📸 Screens (Key Steps)

Login / Register

Select State & District

Choose Movie & Showtime

Pick Seats

Confirm & Save Ticket

📑 Sample Ticket (Text File)  
Movie Ticket
----------------
State: ANDHRA PRADESH
District: Visakhapatnam
Movie: Captain Miller (TAMIL)
Time: 7:00 PM
Seats: [S5, S6, S7]
Total Amount: ₹450
----------------
Enjoy your movie!
📝 License
This project is for educational/demo purposes.
Feel free to fork, modify, and use it.

