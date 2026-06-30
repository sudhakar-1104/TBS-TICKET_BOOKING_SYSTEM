import java.util.ArrayList;
import java.util.Scanner;

interface Displayable {
    void displayDetails();
}

class Review implements Displayable {
    private Booking booking;
    private int rating;
    private String comment;

    public Review(Booking booking, int rating, String comment) {
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters
    public Booking getBooking() {
        return booking;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    // Setters
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void editReview() {
        Scanner scanner = new Scanner(System.in);
        while (true){
		System.out.println("1 -> Edit comment\n2 -> Edit rating\n0 -> back");
        	int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice==1){
			System.out.println("Write your new review");
        		String c = scanner.nextLine();
                        this.setComment(c);
			System.out.println("Changes saved successfully");
		} else if (choice==2){
			System.out.println("Rate the experience out of 10");
        		int r = scanner.nextInt();
        		this.setRating(r);
			System.out.println("Changes saved successfully");
		} else if (choice==0){
			break;
		} else {
			System.out.println("Invalid choice try again");
                }
	}
    }

    public void displayDetails() {
	System.out.println("Booking ID: "+this.booking.getBookingId());
	System.out.println("User: "+this.booking.getUser().getFirstName()+" "+this.booking.getUser().getLastName());
        System.out.println("Event: " +this.booking.getEvent().getName());
	System.out.println("Comment: "+this.comment);
	System.out.println("Rating(out of 10): "+this.rating);
	System.out.println("");
    }

}

class Venue {
    private int venueId;
    private String name;
    private String location;

    public Venue(int venueId, String name, String location) {
        this.venueId = venueId;
        this.name = name;
        this.location = location;
    }

    
    // Getters and Setters
    public int getVenueId() {
        return venueId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }    
}

class BookingHistory implements Displayable {
    private User user;
    private ArrayList<Booking> bookings;

    public BookingHistory(User user, ArrayList<Booking> bookings) {
        this.user = user;
        this.bookings = bookings;
    }

    
    // Getters and Setters
    public User getUser() {
        return user;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void displayDetails() {
	if(bookings.size()>0){
        	for(int i=0;i<this.bookings.size();i++){
			this.bookings.get(i).displayDetails();
		}
	}else{
		System.out.println("You have not made any bookings yet");
		System.out.println("");
	}
    }
}

class Payment implements Runnable {
    private int paymentId;
    private double amount;
    private String paymentMode;

    public Payment(int paymentId, double amount, String paymentMode) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
    }


    public void run() {
        System.out.println("Processing payment...");
        try {
            Thread.sleep(2000); // Simulating payment processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment processed successfully.");
    }
    
    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}

class Booking implements Displayable {
    private int bookingId;
    private Event event;
    private User user;
    private ArrayList<Seat> seats;
    private double totalPrice;

    public Booking(int bookingId, Event event, User user,ArrayList<Seat> seats) {
        this.bookingId = bookingId;
        this.event = event;
        this.user = user;
        this.seats = seats;
        this.totalPrice=this.calculateTotalPrice();
	this.createBooking();
    }

    
    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean createBooking() {
        for (int i=0;i<this.seats.size();i++){
		seats.get(i).setAvailable(false);
	}
        return true;
    }

    public boolean cancelBooking() {
        for (int i=0;i<this.seats.size();i++){
		seats.get(i).setAvailable(true);
	}
	System.out.println("Booking cancelled\n");
        return true;
    }

    public double calculateTotalPrice() {
	double totalPrice=0.0;
        for (int i=0;i<this.seats.size();i++){
		totalPrice += seats.get(i).getCost();
	}
        return totalPrice;
    }

    public void displayDetails(){
	System.out.println("");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Event Details:");
        System.out.println("  Event ID: " + event.getEventId());
        System.out.println("  Event Name: " + event.getName());
        System.out.println("  Event Type: " + event.getEventType());
        System.out.println("  Event Date: " + event.getDate());
        System.out.println("Booked Seats:");
        for (int i=0;i<this.seats.size();i++) {
            System.out.println("  Seat Number: " + this.seats.get(i).displayseatno());
        }
        System.out.println("Total Price: Rs. " + totalPrice);
	System.out.println("");
    }

}


class Ticket implements Runnable{
    private int ticketId;
    private Booking booking;
    private ArrayList<Seat> seats;
    private Event event;

    public Ticket(int ticketId, Booking booking, ArrayList<Seat> seats, Event event) {
        this.ticketId = ticketId;
        this.booking = booking;
        this.seats = seats;
        this.event = event;
    }

    public void run() {
        System.out.println("Generating tickets...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tickets generated successfully.\nTicket ID: "+ticketId);
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public Booking getBooking() {
        return booking;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public Event getEvent() {
        return event;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}


class Seat {
    private int seatId;
    private int seatNumber;
    private String seatType;
    private boolean isAvailable;
    private double cost;

    public Seat(int seatId, int seatNumber, String seatType,double cost,boolean isAvailable) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
	this.isAvailable = isAvailable;
	this.cost=cost;
    }

        // Getters and Setters
    public int getSeatId() {
        return seatId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getCost() {
        return cost;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }   

    public void setAvailable(boolean available) {
        isAvailable = available;
    } 

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String displayseatno(){
	return "Seat-" + this.seatNumber;
    }

}

class Event implements Displayable{
    private int eventId;
    private String name;
    private String eventType;
    private String date; 
    private Venue venue;
    private ArrayList<Seat> seats;
    private ArrayList<Review> reviews;
    private double rating;
    private String time;

    public Event(int eventId, String name, String eventType, String date, Venue venue,String time) {
        this.eventId = eventId;
        this.name = name;
        this.eventType = eventType;
        this.date = date;
        this.venue = venue;
	this.seats=new ArrayList<>();
	this.reviews=new ArrayList<Review>();
	this.time=time;
    }

    
    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public String getTime(){
	return this.time;
    }

    public ArrayList<Seat> getseats(){
	return this.seats;
    }

    public String getName() {
        return name;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDate() {
        return date;
    }

    public Venue getVenue() {
        return venue;
    }

    public ArrayList<Review> getreviews(){
	return this.reviews;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setTime(String time){
	this.time=time;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public double calculaterating(){
	int count=0;
	this.rating=0.0;
	for(int i=0;i<this.reviews.size();i++){
		this.rating+=this.reviews.get(i).getRating();
		count++;
	}
	if(count!=0){
		this.rating=this.rating/count;
	}
	return this.rating;
    }

    public void displayDetails() {
        System.out.println("Event ID: " + this.eventId);
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.eventType);
        System.out.println("Date: " + this.date);
	System.out.println("Time: "+this.time);
        System.out.println("Venue: " + this.venue.getName());
	if(this.calculaterating()!=0.0){
		System.out.println("Rating: "+this.calculaterating());
	}else{
		System.out.println("No reviews in for this event yet");
	}
    }


    public void displayeventreviews(){
	if(this.reviews.size()>0){
		for(int i=0;i<this.reviews.size();i++){
			this.reviews.get(i).displayDetails();
		}
	}else{
		System.out.println("No review in for this event yet");
	}
    }



    public void displayAvailableSeats() {
    	System.out.println("Available Seats for " + this.getEventType() + " - " + this.getName());

    	if (this.getEventType().equals("Sport")) {
    		for (int i = 0; i <= 12; i++) {
        		for (int j = 0; j <= 12; j++) {
            			double dist = Math.sqrt((i - 6) * (i - 6) + (j - 6) * (j - 6));

            			if (Math.abs(dist - 6) < 0.5 || Math.abs(dist - 5.5) < 0.5) {
                			boolean foundAvailableSeat = false;

                			for (Seat seat : this.getseats()) {
                    				if (seat.isAvailable() && seat.getSeatNumber() == (i * 13 + j + 1)) {
                        				System.out.printf("(%3d) ", seat.getSeatNumber());
                        				foundAvailableSeat = true;
                        				break;
                    					}
                			}

                			if (!foundAvailableSeat) {
                    				System.out.print("( X )");
                			}
            			} else {
                			System.out.print("     ");
            			}
        		}
        	System.out.println();
    		}

	}else if (this.getEventType().equals("Concert")) {
        	System.out.println("------------------------------------------------------STAGE------------------------------------------------------------\n");
		System.out.println("\t\t\t\t\t\t\tFanZone");
        	for (Seat seat : this.getseats()) {
            		if (seat.isAvailable()) {
                		System.out.printf("(%3d) ", seat.getSeatNumber());
            		} else {
                		System.out.printf("( X ) "); // Display a blank space for booked seats
            		}

            		if (seat.getSeatNumber() % 20 == 0) {
                		System.out.println();
            		}
			if (seat.getSeatNumber() == 60) {
                		System.out.println();
				System.out.println("\t\t\t\t\t\t\tNormal");
            		}
         	}

        	System.out.println(); 
    	} else if(this.getEventType().equals("Movie")){
		System.out.println("---------------------------SCREEN---------------------------\n");
        	for (Seat seat : this.getseats()) {
            		if (seat.isAvailable()) {
                		System.out.printf("(%3d) ", seat.getSeatNumber());
            		} else {
                		System.out.printf("( X ) "); // Display a blank space for booked seats
            		}

            		if (seat.getSeatNumber() % 10 == 0) {
                		System.out.println();
            		}
			if (seat.getSeatNumber() == 20) {
                		System.out.println();
            		}
			if (seat.getSeatNumber() == 80) {
                		System.out.println();
            		}
         	}

        	System.out.println(); 
	}else{
		System.out.println("---------------------------COMEDIAN---------------------------\n");
        	for (Seat seat : this.getseats()) {
            		if (seat.isAvailable()) {
                		System.out.printf("(%3d) ", seat.getSeatNumber());
            		} else {
                		System.out.printf("( X ) "); // Display a blank space for booked seats
            		}

            		if (seat.getSeatNumber() % 10 == 0) {
                		System.out.println();
            		}
			if (seat.getSeatNumber() == 20) {
                		System.out.println();
            		}
			if (seat.getSeatNumber() == 40) {
                		System.out.println();
            		}
         	}

        	System.out.println();
	}
    }
}








class User implements Displayable{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private BookingHistory bookinghistory;
    private ArrayList<Review> rlist;

    public User(String username, String password, String firstName, String lastName, String email,BookingHistory bookinghistory,ArrayList<Review> rlist) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
	this.bookinghistory = bookinghistory;
	this.rlist = rlist;
    }

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
	this.bookinghistory = new BookingHistory(this,new ArrayList<Booking>());
	this.rlist = new ArrayList<Review>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BookingHistory getBookingHistory() {
        return bookinghistory;
    }

    public ArrayList<Review> getrlist() {
        return this.rlist;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBookingHistory(BookingHistory bookingHistory) {
        this.bookinghistory = bookingHistory;
    }


    public void editProfile() {
	Scanner scanner = new Scanner(System.in);
        while(true){
		System.out.println("1 -> edit username\n2 -> edit password\n3 -> edit firstname\n4 -> edit lastname\n5 -> edit email\n0 -> back");
		int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice == 1){
			System.out.println("Enter new username: ");
			String uname = scanner.nextLine();
			this.setUsername(uname);
			System.out.println("Username updated successfully");
		} else if (choice == 2){
			System.out.println("Enter new password: ");
			String pword = scanner.nextLine();
			this.setPassword(pword);
			System.out.println("Password updated successfully");
		} else if (choice == 3){
			System.out.println("Enter new first name: ");
			String fname = scanner.nextLine();
			this.setFirstName(fname);
			System.out.println("First Name updated successfully");
		} else if (choice == 4){
			System.out.println("Enter new last name: ");
			String lname = scanner.nextLine();
			this.setLastName(lname);
			System.out.println("Last name updated successfully");
		} else if (choice == 5){
			System.out.println("Enter new E mail: ");
			String email = scanner.nextLine();
			if(email.contains("@") && email.contains(".com")){
				this.setEmail(email);
				System.out.println("email updated successfully");
			}else{
				System.out.println("Incorrect email... failed to update");
			}
		} else if (choice == 0){
			break;
		} else {
			System.out.println("Invalid choice try again");
		}
	}
    }

    public void displayDetails() {
    	System.out.println("Username: " + getUsername());
    	System.out.println("First Name: " + getFirstName());
    	System.out.println("Last Name: " + getLastName());
    		System.out.println("Email: " + getEmail());
    System.out.println("");
    }


    public void viewBookingHistory() {
        this.bookinghistory.displayDetails();
    }

    public void writeReviews() {
    Scanner scanner = new Scanner(System.in);
    boolean canWrite = false;
    Booking booking = null;
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    ArrayList<Integer> ids = new ArrayList<Integer>();

    for (int i = 0; i < this.bookinghistory.getBookings().size(); i++) {
        boolean found = false;

        for (int l = 0; l < this.rlist.size(); l++) {
            if (this.bookinghistory.getBookings().get(i).equals(this.rlist.get(l).getBooking())) {
                found = true;
                break;
            }
        }

        if (!found) {
            canWrite = true;
            bookings.add(this.bookinghistory.getBookings().get(i));
            ids.add(this.bookinghistory.getBookings().get(i).getBookingId());
        }
    }

    if (canWrite) {
        System.out.println("You can add a review for booking:\n");

        for (int j = 0; j < bookings.size(); j++) {
            bookings.get(j).displayDetails();
        }

        int revid = 0;  // Initialize revid outside the loop

        while (true) {
            System.out.println("Enter the booking ID for which you want to write a review (0 -> back)");
            revid = scanner.nextInt();
            scanner.nextLine();
            if (ids.contains(revid) || revid == 0) {
                break;
            } else {
                System.out.println("Invalid choice...Try again");
            }
        }

        if (revid == 0) {
            return;
        } else {
            for (int k = 0; k < bookings.size(); k++) {
                if (bookings.get(k).getBookingId() == revid) {
                    booking = bookings.get(k);
                }
            }

            System.out.println("Write your review");
            String c = scanner.nextLine();
            System.out.println("Rate the experience");
            int r = scanner.nextInt();
            scanner.nextLine();
            Review review = new Review(booking, r, c);
	    this.rlist.add(review);
            System.out.println("Review added successfully");
        }
    } else {
        System.out.println("You have given a review for every event you have attended");
    }
}



    public void listreviews(){
	Scanner scanner = new Scanner(System.in);
	if(rlist.size()>0){
	System.out.println("You have given reviews for: ");
	for(int i=0;i<this.rlist.size();i++){
		this.rlist.get(i).displayDetails();
	}
	System.out.println("Do you want to edit any of these reviews?(1 -> yes & any other number -> no)");
	int yn=scanner.nextInt();
	scanner.nextLine();
	boolean exists=false;
	if(yn==1){
		System.out.println("Enter the booking ID for which you want to edit the review: ");
		int bid=scanner.nextInt();
		scanner.nextLine();
		for(int j=0;j<this.rlist.size();j++){
			if(this.rlist.get(j).getBooking().getBookingId()==bid){
				this.rlist.get(j).editReview();
				exists=true;
				break;
			}
		}
		if(exists==false){
			System.out.println("No such booking Id...");
		}
	}
	}else{
		System.out.println("You have not given any reviews\n");
	}
   }
    
    

}



public class TicketApp {

    public static void booktickets(User user,ArrayList<Event> eventList,int bid){
	Scanner scanner = new Scanner(System.in);
	int cityChoice; 
	String city = "";
	while(true){
		System.out.println("Select city: ");
		System.out.println("1 -> Coimbatore\n2 -> Bangalore\n0 -> Back to main menu");
		cityChoice=scanner.nextInt();
		scanner.nextLine();
    		if (cityChoice == 1) {
        		city = "Coimbatore";
			break;
    		} else if (cityChoice == 2) {
        		city = "Bangalore";
			break;
    		} else if (cityChoice == 0) {
        		return;
    		} else {
        		System.out.println("Invalid choice... Try again\n");
    		}
	}
	

    	String date = "";
	while(true){
		System.out.println("1 -> 15-12-2023\n2 -> 16-12-2023\n0 -> Back to main menu");
    		int dateChoice = scanner.nextInt();
    		scanner.nextLine();
    		if (dateChoice == 1) {
        		date = "15-12-2023";
			break;
    		} else if (dateChoice == 2) {
        		date = "16-12-2023";
			break;
    		} else if (dateChoice == 0) {
        		break;
    		} else {
        		System.out.println("Invalid choice... Try again\n");
    		}
	}

	int category;
	ArrayList<Integer> validids = new ArrayList<Integer>();
	String eventtype="";
	while(true){
		System.out.println("Select category: ");
		System.out.println("1 -> Movie\n2 -> Sport\n3 -> Comedy\n4 -> Concert\n0 -> Back to main menu\n");
		category=scanner.nextInt();
		scanner.nextLine();
		int eventid;
		if(category==1){
			for(int i=0;i<eventList.size();i++){
				if(eventList.get(i).getEventType() == "Movie" && eventList.get(i).getDate().equals(date) && eventList.get(i).getVenue().getLocation().equals(city)){
					eventtype="Movie";
					eventList.get(i).displayDetails();
					validids.add(eventList.get(i).getEventId());
					System.out.println("\n");
				}
			}
			System.out.println("Enter the ID of the event you want to see: (0 -> back to category)");
			eventid=scanner.nextInt();
			scanner.nextLine();
			while(true){
				if(validids.contains(eventid) || eventid==0){
					break;
				}else{
					System.out.println("Invalid Event ID...try again");
					eventid=scanner.nextInt();
					scanner.nextLine();
				}
			}
			while(true){
				if(eventid==0){
					break;
				}
				System.out.println("1 -> book tickets\n2 -> view reviews\n0 -> back to category");
				int what=scanner.nextInt();
				scanner.nextLine();
				if(what==1){
					bookevent(eventid,eventList,date,city,eventtype,bid,user);
					break;
				}else if(what==2){
					for(int i=0;i<eventList.size();i++){
						if(eventList.get(i).getEventId()==eventid){
							eventList.get(i).displayeventreviews();
						}
					}
				}else if(what==0){
					break;
				}else{
					System.out.println("Invalid choice...try again");
				}
			}
		}else if(category==2){
			for(int i=0;i<eventList.size();i++){
				if(eventList.get(i).getEventType() == "Sport" && eventList.get(i).getDate().equals(date) && eventList.get(i).getVenue().getLocation().equals(city)){
						eventtype="Sport";
						eventList.get(i).displayDetails();
						validids.add(eventList.get(i).getEventId());
						System.out.println("\n");
				}
			}
			System.out.println("Enter the ID of the event you want to see: (0 -> back to category)");
			eventid=scanner.nextInt();
			scanner.nextLine();
			while(true){
				if(validids.contains(eventid) || eventid==0){
					break;
				}else{
					System.out.println("Invalid Event ID...try again");
					eventid=scanner.nextInt();
					scanner.nextLine();
				}
			}
			while(true){
				if(eventid==0){
					break;
				}
				System.out.println("1 -> book tickets\n2 -> view reviews\n0 -> back to category");
				int what=scanner.nextInt();
				scanner.nextLine();
				if(what==1){
					bookevent(eventid,eventList,date,city,eventtype,bid,user);
					break;
				}else if(what==2){
					for(int i=0;i<eventList.size();i++){
						if(eventList.get(i).getEventId()==eventid){
							eventList.get(i).displayeventreviews();
						}
					}
				}else if(what==0){
					break;
				}else{
					System.out.println("Invalid choice...try again");
				}
			}
		}else if(category==3){
			for(int i=0;i<eventList.size();i++){
				if(eventList.get(i).getEventType() == "Comedy Show" && eventList.get(i).getDate().equals(date) && eventList.get(i).getVenue().getLocation().equals(city)){
					eventtype="Comedy Show";
					eventList.get(i).displayDetails();
					validids.add(eventList.get(i).getEventId());
					System.out.println("\n");
				}
			}
			System.out.println("Enter the ID of the event you want to see: (0 -> back to category)");
			eventid=scanner.nextInt();
			scanner.nextLine();
			while(true){
				if(validids.contains(eventid) || eventid==0){
					break;
				}else{
					System.out.println("Invalid Event ID...try again");
					eventid=scanner.nextInt();
					scanner.nextLine();
				}
			}
			while(true){
				if(eventid==0){
					break;
				}
				System.out.println("1 -> book tickets\n2 -> view reviews\n0 -> back to category");
				int what=scanner.nextInt();
				scanner.nextLine();
				if(what==1){
					bookevent(eventid,eventList,date,city,eventtype,bid,user);
					break;
				}else if(what==2){
					for(int i=0;i<eventList.size();i++){
						if(eventList.get(i).getEventId()==eventid){
							eventList.get(i).displayeventreviews();
						}
					}
				}else if(what==0){
					break;
				}else{
					System.out.println("Invalid choice...try again");
				}
			}
		}else if(category==4){
			for(int i=0;i<eventList.size();i++){
				if(eventList.get(i).getEventType() == "Concert" && eventList.get(i).getDate().equals(date) && eventList.get(i).getVenue().getLocation().equals(city)){
					eventtype="Concert";
					eventList.get(i).displayDetails();
					validids.add(eventList.get(i).getEventId());
					System.out.println("\n");
				}
			}
			System.out.println("Enter the ID of the event you want to see: (0 -> back to category)");
			eventid=scanner.nextInt();
			scanner.nextLine();
			while(true){
				if(validids.contains(eventid) || eventid==0){
					break;
				}else{
					System.out.println("Invalid Event ID...try again");
					eventid=scanner.nextInt();
					scanner.nextLine();
				}
			}
			while(true){
				if(eventid==0){
					break;
				}
				System.out.println("1 -> book tickets\n2 -> view reviews\n0 -> back to category");
				int what=scanner.nextInt();
				scanner.nextLine();
				if(what==1){
					bookevent(eventid,eventList,date,city,eventtype,bid,user);
					break;
				}else if(what==2){
					for(int i=0;i<eventList.size();i++){
						if(eventList.get(i).getEventId()==eventid){
							eventList.get(i).displayeventreviews();
						}
					}
				}else if(what==0){
					break;
				}else{
					System.out.println("Invalid choice...try again");
				}
			}
		}else if(category==0){
			break;
		}else{
			System.out.println("Ivalid choice... Try again");
		}
	}


    }


    public static void bookevent(int eventid, ArrayList<Event> eventList, String date, String city, String eventtype,int bid,User user) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Seat> seats = new ArrayList<Seat>();
    boolean valid = false;
    Event event = null;
    int n = 0;

    while (true) {
        if (eventid == 0) {
            break;
        } else {
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).getEventId() == eventid && eventList.get(i).getDate().equals(date)
                        && eventList.get(i).getVenue().getLocation().equals(city)
                        && eventList.get(i).getEventType().equals(eventtype)) {
                    valid = true;
                    event = eventList.get(i);
                    System.out.println("Enter the number of seats you want to book: ");
                    n = scanner.nextInt();
                    scanner.nextLine();
                }
            }
            if (!valid) {
                System.out.println("Invalid eventID...Try again");
                eventid = scanner.nextInt();
                scanner.nextLine();
            } else {
                event.displayAvailableSeats();
                for (int j = 0; j < n; j++) {
                    while (true) {
                        System.out.println((j + 1) + ") Enter seat number: ");
                        int sn = scanner.nextInt();
                        scanner.nextLine();

                        if (sn >= 1 && sn <= event.getseats().size()) {
                            Seat seat = event.getseats().get(sn - 1);
                            if (seat.isAvailable()) {
                                seats.add(seat);
                                seat.setAvailable(false);
                                break;
                            } else {
                                System.out.println("Seat has already been booked...Please choose another seat");
                            }
                        } else {
                            System.out.println("Invalid seat number please enter a valid one");
                        }
                    }
                }
		Booking booking=new Booking(bid,event, user,seats);
		while(true){
			System.out.println("pay Rs. "+booking.getTotalPrice()+" ?");
			System.out.println("1 -> yes \n0 -> no");
			int yesorno=scanner.nextInt();
			scanner.nextLine();
			if(yesorno==1){
				System.out.println("Choose a mode of payment: ");
				while(true){
					System.out.println("1 -> netbanking\n2 -> card\n0 -> cancel payment");
					int pm = scanner.nextInt();
					scanner.nextLine();
					if(pm==1){
						Thread paymentThread = new Thread(new Payment(1, booking.getTotalPrice(), "net banking"));
        					paymentThread.start();
						try {
            						paymentThread.join();
        					} catch (InterruptedException e) {
            						e.printStackTrace();
        					}
						System.out.println("Booking Successful\n");
						Thread ticketThread = new Thread(new Ticket(bid, booking,seats,event));
        					ticketThread.start();
						try {
            						ticketThread.join();
        					} catch (InterruptedException e) {
            						e.printStackTrace();
        					}
						booking.displayDetails();
						user.getBookingHistory().getBookings().add(booking);
                				break;
					}else if(pm==2){
						Thread paymentThread = new Thread(new Payment(1, booking.getTotalPrice(), "card"));
        					paymentThread.start();
						try {
            						paymentThread.join();
        					} catch (InterruptedException e) {
            						e.printStackTrace();
        					}
						System.out.println("Booking Successful\n");
						Thread ticketThread = new Thread(new Ticket(bid, booking,seats,event));
        					ticketThread.start();
						try {
            						ticketThread.join();
        					} catch (InterruptedException e) {
            						e.printStackTrace();
        					}
						booking.displayDetails();
						user.getBookingHistory().getBookings().add(booking);
                				break;
					}else if(pm==0){
						booking.cancelBooking();
						break;
					}else{
						System.out.println("Invalid choice...try again");
					}
				}
				break;
				}else if(yesorno==0){
					booking.cancelBooking();
					break;
			}else{
				System.out.println("Ivalid choice..try again");
			}
		}
		System.out.println("Continue browsing by category");
		break;
		
            }
        }
	
    }
}


    public static boolean login(String uname,String pass,ArrayList<User> users) {
	for(int i=0;i<users.size();i++){
        	if(uname.equals(users.get(i).getUsername()) && pass.equals(users.get(i).getPassword())){
			return true;
		}
	}
	return false;
     }

    public static User createAccount(String username, String password, String firstName, String lastName, String email) {
        return new User(username, password, firstName, lastName, email);
    }

    public static void main(String[] args) {

	int bid=11;
	ArrayList<Event> eventList = new ArrayList<>();

        Venue venue1 = new Venue(1, "Brookfields Mall", "Coimbatore");
        Venue venue2 = new Venue(2, "Broadway cinemas(imax)", "Coimbatore");
        Venue venue3 = new Venue(3, "Narendra Modi Stadium", "Coimbatore");
        Venue venue4 = new Venue(4, "Chepauk Stadium", "Coimbatore");
        Venue venue5 = new Venue(5, "Co india building", "Coimbatore");
        Venue venue6 = new Venue(6, "Fun republic Mall", "Coimbatore");
        Venue venue7 = new Venue(7, "Coddisia", "Coimbatore");
        Venue venue8 = new Venue(8, "PSG CAS", "Coimbatore");
	Venue venue9 = new Venue(9, "Orion Mall", "Bangalore");
	Venue venue10 = new Venue(10, "PVR Forum Mall", "Bangalore");
	Venue venue11 = new Venue(11, "M Chinnaswamy Stadium", "Bangalore");
	Venue venue12 = new Venue(12, "Ritz-Carlton Ballroom", "Bangalore");
	Venue venue13 = new Venue(13, "UB City", "Bangalore");
	Venue venue14 = new Venue(14, "Manpho Convention Center", "Bangalore");
	Venue venue15 = new Venue(15, "Sree Kanteerava Stadium", "Bangalore");
	Venue venue16 = new Venue(16, "Phoenix Marketcity", "Bangalore");

        Event movieEvent1 = new Event(1, "Avengers: Endgame", "Movie", "15-12-2023", venue1,"2:00pm");
        Event movieEvent2 = new Event(2, "The Matrix Resurrections", "Movie", "15-12-2023", venue2,"2:30pm");
        Event movieEvent3 = new Event(3, "Spider-Man: No Way Home", "Movie", "16-12-2023", venue1,"5:00pm");
        eventList.add(movieEvent1);
        eventList.add(movieEvent2);
        eventList.add(movieEvent3);
	Event movieEvent4 = new Event(4, "Avengers: Endgame", "Movie", "15-12-2023", venue9,"2:00pm");
        Event movieEvent5 = new Event(5, "The Matrix Resurrections", "Movie", "15-12-2023", venue10,"2:30pm");
        Event movieEvent6 = new Event(6, "Spider-Man: No Way Home", "Movie", "16-12-2023", venue9,"5:00pm");
        eventList.add(movieEvent4);
        eventList.add(movieEvent5);
        eventList.add(movieEvent6);

        Event sportEvent1 = new Event(7, "GT vs SRH", "Sport", "16-12-2023", venue3,"5:00pm");
        Event sportEvent2 = new Event(8, "DC vs CSK", "Sport", "15-12-2023", venue4,"7:00pm");
        Event sportEvent3 = new Event(9, "GT vs MI", "Sport", "16-12-2023", venue3,"11:30am");
        eventList.add(sportEvent1);
        eventList.add(sportEvent2);
        eventList.add(sportEvent3);
	Event sportEvent4 = new Event(10, "RCB VS PBKS", "Sport", "16-12-2023", venue11,"5:00pm");
        Event sportEvent5 = new Event(11, "bengaluru fc vs chennaiyan fc", "Sport", "15-12-2023", venue15,"7:00pm");
        Event sportEvent6 = new Event(12, "RR vs KKR", "Sport", "16-12-2023", venue11,"11:30am");
        eventList.add(sportEvent4);
        eventList.add(sportEvent5);
        eventList.add(sportEvent6);

        Event comedyEvent1 = new Event(13, "Stand-Up Comedy Night", "Comedy Show", "16-12-2023", venue5,"8:00pm");
        Event comedyEvent2 = new Event(14, "Laugh Fest", "Comedy Show", "15-12-2023", venue6,"6:00pm");
        Event comedyEvent3 = new Event(15, "Comedy Extravaganza", "Comedy Show", "15-12-2023", venue5,"12:00pm");
        eventList.add(comedyEvent1);
        eventList.add(comedyEvent2);
        eventList.add(comedyEvent3);
	Event comedyEvent4 = new Event(16, "Stand-Up Comedy evening", "Comedy Show", "15-12-2023", venue14,"4:00pm");
        Event comedyEvent5 = new Event(17, "Laughter non stop", "Comedy Show", "15-12-2023", venue16,"6:00pm");
        Event comedyEvent6 = new Event(18, "Comedy Central", "Comedy Show", "16-12-2023", venue14,"12:00pm");
        eventList.add(comedyEvent4);
        eventList.add(comedyEvent5);
        eventList.add(comedyEvent6);

        Event concertEvent1 = new Event(19, "Yuvan special", "Concert", "15-12-2023", venue7,"7:00pm");
        Event concertEvent2 = new Event(20, "Rockstar Anirudh", "Concert", "16-12-2023", venue8,"6:30pm");
        eventList.add(concertEvent1);
        eventList.add(concertEvent2);
	Event concertEvent3 = new Event(21, "Arjit Singh", "Concert", "15-12-2023", venue12,"7:00pm");
        Event concertEvent4 = new Event(22, "Shreya Ghosal", "Concert", "16-12-2023", venue13,"6:30pm");
        eventList.add(concertEvent3);
        eventList.add(concertEvent4);

	for (Event event : eventList) { 
            if (event.getEventType().equals("Movie")) {
                for (int i = 1; i <= 100; i++) {
                    Seat seat = new Seat(i,i, "Regular", 150.0, true);
                    event.addSeat(seat);
                }
            }
        }

	for (Event event : eventList) {
            if (event.getEventType().equals("Sport")) {
                for (int i = 1; i <= 200; i++) {
                    Seat seat = new Seat(i,i, "Regular", 400.0, true);
                    event.addSeat(seat);
                }
            }
        }

        for (Event event : eventList) {
            if (event.getEventType().equals("Comedy Show")) {
                for (int i = 1; i <= 70; i++) {
                    Seat seat = new Seat(i,i, "Regular", 100.0, true);
                    event.addSeat(seat);
                }
            }
        }

        for (Event event : eventList)  {
            if (event.getEventType().equals("Concert")) {
                for (int i = 1; i <= 60; i++) {
                    Seat seat = new Seat(i,i, "FanZone", 200.0, true);
                    event.addSeat(seat);
                }

                for (int i = 61; i <= 200; i++) {
                    Seat seat = new Seat(i,i, "Normal", 100.0, true);
                    event.addSeat(seat);
                }
            }
        }

        User user1 = new User("jd", "123", "Jaideep", "P", "jd@gmail.com");
        User user2 = new User("sudhakar", "abc", "Sudhakar", "KS", "sks@gmail.com");
        User user3 = new User("siddharth", "456", "Siddharth", "Swamy", "ss@gmail.com");
        User user4 = new User("suren", "def", "Suren", "Adithiya", "sa@gmail.com");
        User user5 = new User("praysun", "789", "Praysun", "Raja", "pr@gmail.com");
	ArrayList<User> users = new ArrayList<User>();
	users.add(user1);
	users.add(user2);
	users.add(user3);
	users.add(user4);
	users.add(user5);


	ArrayList<Seat> seatuser1_1 = new ArrayList<Seat>();
	for(int i=50;i<57;i++){
		seatuser1_1.add(eventList.get(0).getseats().get(i));
	}
	Booking bookingUser1_1 = new Booking(1, eventList.get(0),user1, seatuser1_1);
	bookingUser1_1.createBooking();
	user1.getBookingHistory().getBookings().add(bookingUser1_1);
	Review reviewuser1_1 = new Review(bookingUser1_1,7,"Good cinematography poor plot");
	user1.getrlist().add(reviewuser1_1);
	bookingUser1_1.getEvent().getreviews().add(reviewuser1_1);

	ArrayList<Seat> seatuser1_2 = new ArrayList<Seat>();
	for(int i=40;i<43;i++){
		seatuser1_2.add(eventList.get(7).getseats().get(i));
	}
	Booking bookingUser1_2 = new Booking(2, eventList.get(7),user1, seatuser1_2);
	bookingUser1_2.createBooking();
	user1.getBookingHistory().getBookings().add(bookingUser1_2);
	//Review reviewuser1_2 = new Review(bookingUser1_2,8,"Very good stadium and atmosphere");
	//user1.getrlist().add(reviewuser1_2);
	//bookingUser1_2.getEvent().getreviews().add(reviewuser1_2);

	
	ArrayList<Seat> seatuser2_1 = new ArrayList<Seat>();
	for(int i=40;i<45;i++){
		seatuser2_1.add(eventList.get(1).getseats().get(i));
	}
	Booking bookingUser2_1 = new Booking(3, eventList.get(1),user2, seatuser2_1);
	bookingUser2_1.createBooking();
	user2.getBookingHistory().getBookings().add(bookingUser2_1);
	Review reviewuser2_1 = new Review(bookingUser2_1,10,"Perfect movie");
	user2.getrlist().add(reviewuser2_1);
	bookingUser2_1.getEvent().getreviews().add(reviewuser2_1);

	ArrayList<Seat> seatuser2_2 = new ArrayList<Seat>();
	for(int i=20;i<24;i++){
		seatuser2_2.add(eventList.get(11).getseats().get(i));
	}
	Booking bookingUser2_2 = new Booking(4, eventList.get(11),user2, seatuser2_2);
	bookingUser2_2.createBooking();
	user2.getBookingHistory().getBookings().add(bookingUser2_2);
	Review reviewuser2_2 = new Review(bookingUser2_2,8,"Good stadium but bad crowd");
	user2.getrlist().add(reviewuser2_2);
	bookingUser2_2.getEvent().getreviews().add(reviewuser2_2);


	ArrayList<Seat> seatuser3_1 = new ArrayList<Seat>();
	for(int i=40;i<45;i++){
		seatuser3_1.add(eventList.get(12).getseats().get(i));
	}
	Booking bookingUser3_1 = new Booking(5, eventList.get(12),user3, seatuser3_1);
	bookingUser3_1.createBooking();
	user3.getBookingHistory().getBookings().add(bookingUser3_1);
	Review reviewuser3_1 = new Review(bookingUser3_1,6,"Didnt find it that much funny");
	user3.getrlist().add(reviewuser3_1);
	bookingUser3_1.getEvent().getreviews().add(reviewuser3_1);

	ArrayList<Seat> seatuser3_2 = new ArrayList<Seat>();
	for(int i=10;i<15;i++){
		seatuser3_2.add(eventList.get(9).getseats().get(i));
	}
	Booking bookingUser3_2 = new Booking(6, eventList.get(9),user3, seatuser3_2);
	bookingUser3_2.createBooking();
	user3.getBookingHistory().getBookings().add(bookingUser3_2);
	Review reviewuser3_2 = new Review(bookingUser3_2,7,"crowd was very bad");
	user3.getrlist().add(reviewuser3_2);
	bookingUser3_2.getEvent().getreviews().add(reviewuser3_2);


	ArrayList<Seat> seatuser4_1 = new ArrayList<Seat>();
	for(int i=30;i<38;i++){
		seatuser4_1.add(eventList.get(3).getseats().get(i));
	}
	Booking bookingUser4_1 = new Booking(7, eventList.get(3),user4, seatuser4_1);
	bookingUser4_1.createBooking();
	user4.getBookingHistory().getBookings().add(bookingUser4_1);
	Review reviewuser4_1 = new Review(bookingUser4_1,5,"Not the best superhero movie graphics were poor");
	user4.getrlist().add(reviewuser4_1);
	bookingUser4_1.getEvent().getreviews().add(reviewuser4_1);

	ArrayList<Seat> seatuser4_2 = new ArrayList<Seat>();
	for(int i=100;i<120;i++){
		seatuser4_2.add(eventList.get(19).getseats().get(i));
	}
	Booking bookingUser4_2 = new Booking(8, eventList.get(19),user4, seatuser4_2);
	bookingUser4_2.createBooking();
	user4.getBookingHistory().getBookings().add(bookingUser4_2);
	Review reviewuser4_2 = new Review(bookingUser4_2,10,"Great experience everyone should witness it");
	user4.getrlist().add(reviewuser4_2);
	bookingUser4_2.getEvent().getreviews().add(reviewuser4_2);


	ArrayList<Seat> seatuser5_1 = new ArrayList<Seat>();
	for(int i=40;i<45;i++){
		seatuser5_1.add(eventList.get(18).getseats().get(i));
	}
	Booking bookingUser5_1 = new Booking(9, eventList.get(18),user5, seatuser5_1);
	bookingUser5_1.createBooking();
	user5.getBookingHistory().getBookings().add(bookingUser5_1);
	Review reviewuser5_1 = new Review(bookingUser5_1,9,"I could listen to yuvan all day");
	user5.getrlist().add(reviewuser5_1);
	bookingUser5_1.getEvent().getreviews().add(reviewuser5_1);

	ArrayList<Seat> seatuser5_2 = new ArrayList<Seat>();
	for(int i=20;i<27;i++){
		seatuser5_2.add(eventList.get(9).getseats().get(i));
	}
	Booking bookingUser5_2 = new Booking(10, eventList.get(9),user5, seatuser5_2);
	bookingUser5_2.createBooking();
	user5.getBookingHistory().getBookings().add(bookingUser5_2);
	Review reviewuser5_2 = new Review(bookingUser5_2,6,"Good stadium but worst crowd");
	user5.getrlist().add(reviewuser5_2);
	bookingUser5_2.getEvent().getreviews().add(reviewuser5_2);



	Scanner scanner = new Scanner(System.in);
	while(true){
	System.out.println("1 -> Login\n2 -> Sign Up\nAny other number -> Exit");
	int begin=scanner.nextInt();
	scanner.nextLine();
	if(begin==1){
		int ta=1;
		String uname;
		String pass;
		User user = null;
		while(ta!=0){
			System.out.println("Username: ");
			uname=scanner.nextLine();
			System.out.println("Password: ");
			pass=scanner.nextLine();
			if(login(uname,pass,users)){
				for(int i=0;i<users.size();i++){
					if(uname.equals(users.get(i).getUsername()) && pass.equals(users.get(i).getPassword())){
						user=users.get(i);
						break;
					}
				}
				System.out.println("----------------------------------------WELCOME TO TICKET BOOKING APP-------------------------------");


				while(true){
					System.out.println("What do u want to do?");
					System.out.println("1 -> View profile\n2 -> View Booking History\n3 -> View Reviews\n4 -> Book Ticket\n5 ->Edit profile\n6 -> Write review\n0 -> Sign Out: ");
					int choice=scanner.nextInt();
					scanner.nextLine();
					if(choice==4){
						booktickets(user,eventList,bid);
						bid++;
					}else if(choice==5){
						user.editProfile();
					}else if(choice==3){
						user.listreviews();
					}else if(choice==2){
						user.viewBookingHistory();
					}else if(choice==0){
						ta=0;
						System.out.println("Thank you for using the app");
						break;
					}else if(choice==1){
						user.displayDetails();
					}else if(choice==6){
						user.writeReviews();
					}else{
						System.out.println("Ivalid choice... Try again");
					}
				}
			
			}else{
				System.out.println("Ivalid credentials try again...(press 0 to exit & any other number to try again)");
				ta=scanner.nextInt();
				scanner.nextLine();
			}
		}
	}else if (begin == 2) {
    		System.out.println("Enter username: ");
    		String username = scanner.nextLine();

    		// Check if the username already exists
    		boolean usernameExists = users.stream().anyMatch(user -> user.getUsername().equals(username));
    		if (usernameExists) {
        		System.out.println("Username already taken. Failed to create account.");
    		} else {
        		System.out.println("Enter password: ");
        		String password = scanner.nextLine();
        		System.out.println("Enter firstName: ");
        		String firstName = scanner.nextLine();
        		System.out.println("Enter lastName: ");
        		String lastName = scanner.nextLine();
        		System.out.println("Enter email: ");
        		String email = scanner.nextLine();

        		// Check if the email already exists
        		boolean emailExists = users.stream().anyMatch(user -> user.getEmail().equals(email));  //fetches data from stream api to check if entered email is matching with any existing one.
        		if (emailExists) {
        		    System.out.println("Email already exists. Failed to create account.");
        		} else if (email.contains("@") && email.contains(".com")) {
            			users.add(createAccount(username, password, firstName, lastName, email));
            			System.out.println("Account created successfully!");
        		} else {
            			System.out.println("Invalid email. Failed to create account.");
        		}
    		}

	}else{
		System.out.println("Closing application");
		break;
	}
	}
    }
	
} 
