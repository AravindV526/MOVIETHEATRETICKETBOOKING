 import javafx.application.Application;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.layout.*;
 import javafx.stage.*;
 import javafx.geometry.*;
 import java.io.*;
 import java.util.*;
 public class MovieTicketBookingApp extends Application {
 private Stage primaryStage;
 private final Map<String, String> users = new HashMap<>();
 private final List<String> seats = new ArrayList<>(Collections.nCopies(30, "Available"));
 private String selectedState;
 private String selectedDistrict;
 private String selectedMovie;
 private String selectedTime;
 private final List<Integer> selectedSeats = new ArrayList<>();
 private double totalAmount;
 @Override
 public void start(Stage primaryStage) {
 this.primaryStage = primaryStage;
 showLoginPage();
 }
 private void showLoginPage() {
 Label welcomeLabel = new Label("Welcome to Movie Ticket Booking");
 Button loginButton = new Button("Login");
 Button registerButton = new Button("Register");
 VBox vbox = new VBox(10, welcomeLabel, loginButton, registerButton);
 vbox.setAlignment(Pos.CENTER);
loginButton.setOnAction(e-> showLoginDialog());
 registerButton.setOnAction(e-> showRegisterDialog());
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 primaryStage.setTitle("Movie Tickets Booking");
 primaryStage.show();
 }
 private void showLoginDialog() {
 Dialog<String> dialog = new Dialog<>();
 dialog.setTitle("Login");
 GridPane grid = new GridPane();
 grid.setHgap(10);
 grid.setVgap(10);
 grid.setPadding(new Insets(20, 150, 10, 10));
 TextField usernameField = new TextField();
 usernameField.setPromptText("username");
 PasswordField passwordField = new PasswordField();
 passwordField.setPromptText("password");
 grid.add(new Label("Username:"), 0, 0);
 grid.add(usernameField, 1, 0);
 grid.add(new Label("Password:"), 0, 1);
 grid.add(passwordField, 1, 1);
 dialog.getDialogPane().setContent(grid);
 ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
 dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
 dialog.setResultConverter(dialogButton-> {
 if (dialogButton == loginButtonType) {
 String username = usernameField.getText();
 String password = passwordField.getText();
 if (users.containsKey(username) && users.get(username).equals(password)) {
 showStateSelection();
 return null;
 } else {
 showError("Invalid credentials!");
 }
}
 return null;
 });
 dialog.showAndWait();
 }
 private void showRegisterDialog() {
 Dialog<String> dialog = new Dialog<>();
 dialog.setTitle("Register");
 GridPane grid = new GridPane();
 grid.setHgap(10);
 grid.setVgap(10);
 grid.setPadding(new Insets(20, 150, 10, 10));
 TextField usernameField = new TextField();
 usernameField.setPromptText("Username");
 PasswordField passwordField = new PasswordField();
 passwordField.setPromptText("Password");
 grid.add(new Label("Username:"), 0, 0);
 grid.add(usernameField, 1, 0);
 grid.add(new Label("Password:"), 0, 1);
 grid.add(passwordField, 1, 1);
 dialog.getDialogPane().setContent(grid);
 ButtonType registerButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
 dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);
 dialog.setResultConverter(dialogButton-> {
 if (dialogButton == registerButtonType) {
 String username = usernameField.getText();
 String password = passwordField.getText();
 if (username.isEmpty() | password.isEmpty()) {
 showError("Fields cannot be empty!");
 } else {
 users.put(username, password);
 showSuccess("Registration successful!");
 }
 }
 return null;
 });
dialog.showAndWait();
 }
 private void showStateSelection() {
 ChoiceBox<String> stateBox = new ChoiceBox<>();
 stateBox.getItems().addAll("ANDHRA PRADESH", "ARUNACHAL PRADESH",
 "ASSAM","BIHAR","CHHATTISGARH","GOA","GUJARAT","HARYANA","HIMACHAL
 PRADESH","JHARKHAND","KARNATAKA","KERALA","MAHARASHTRA","MADHYA
 PRADESH","MANIPUR","MEGHALAYA","MIZORAM","NAGALAND","ODISHA","PUNJAB","RA
 JASTHAN","SIKKIM","TAMILNADU","UTTAR PRADESH","UTTARAKHAND","WEST
 BENGAL","ANDAMAN&NICOBAR","CHANDIGARTH","DADRA&NAGAR
 HAVELI","DAMAN&DIU","DELHI","JAMMU&KASHMIR","LADAKH","LAKSHADWEEP","PUD
 UCHERRY");
 stateBox.setValue("ANDHRA PRADESH");
 Button nextButton = new Button("Next");
 VBox vbox = new VBox(10, new Label("Select State:"), stateBox, nextButton);
 vbox.setAlignment(Pos.CENTER);
 nextButton.setOnAction(e-> {
 selectedState = stateBox.getValue();
 showDistrictSelection();
 });
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 }
 private void showDistrictSelection() {
 ChoiceBox<String> districtBox = new ChoiceBox<>();
 districtBox.getItems().addAll("Anakapalli","Anantapur", "Bapatla", "Chittoor", "East Godavari",
 "Eluru"," Guntur", "Kakinada", "Krishna", "Kurnool", "Nandyal", "NTR", "Palnadu", "Parvathipuram
 Manyam", "Prakasam", "srikakulam", "Sri Sathya Sai", "Tirupati", "Vizianagaram", "Visakhapatnam",
 "West Godavari", "YSR Kadapa", "Alluri Sitharama Raju", "Annamayya", "Konaseema","Anjaw",
 "Changlang"," Dibang Valley", "East Kameng", "East Siang", "Kamle", "Kra Daadi", "Kurung Kumey",
 "Lepa Rada", "Lohit", "Longding", "Lower Dibang Valley", "Lower Siang", "Lower Subansiri",
 "Namsai", "Pakke Kessang", "Papum Pare", "Shi Yomi", "Siang, Tawang", "Tirap", "Upper Siang",
 "Upper Subansiri", "West Kameng", "West Siang", "Itanagar","Baksa", "Barpeta", "Biswanath",
 "Bongaigaon", "Cachar", "Charaideo", "Chirang", "Darrang", "Dhemaji", "Dhubri", "Dibrugarh", "Dima
 Hasao", "Goalpara", "Golaghat", "Hailakandi", "Hojai", "Jorhat", "Kamrup", "Kamrup Metropolitan",
 "Karbi Anglong", "Karimganj, Kokrajhar", "Lakhimpur", "Majuli", "Morigaon", "Nagaon", "Nalbari",
 "Sivasagar", "Sonitpur", "South Salmara-Mankachar", "Tinsukia", "Udalguri", "West Karbi Anglong",
 "Bajali", "Tamulpur","Araria", "Arwal", "Aurangabad", "Banka", "Begusarai", "Bhagalpur", "Bhojpur",
"Buxar", "Darbhanga", "East Champaran (Motihari)", "Gaya, Gopalganj", "Jamui", "Jehanabad", "Kaimur
 (Bhabua)", "Katihar", "Khagaria", "Kishanganj", "Lakhisarai", "Madhepura", "Madhubani", "Munger",
 "Muzaffarpur", "Nalanda", "Nawada Patna", "Purnia", "Rohtas", "Saharsa", "Samastipur", "Saran",
 "Sheikhpura", "Sheohar", "Sitamarhi", "Siwan", "Supaul", "Vaishali", "West Champaran
 (Bettiah)","Balod", "Baloda Bazar", "Balrampur", "Bastar", "Bemetara", "Bijapur", "Bilaspur",
 "Dantewada (South Bastar)", "Dhamtari", "Durg", "Gariaband", "Gaurela-Pendra-Marwahi",
 "Janjgir-Champa", "Jashpur", "Kabirdham (Kawardha)", "Kanker (North Bastar)", "Kondagaon",
 "Korba", "Koriya", "Mahasamund", "Mungeli", "Narayanpur", "Raigarh", "Raipur", "Rajnandgaon",
 "Sukma", "Surajpur", "Surguja", "Manendragarh-Chirmiri-Bharatpur", "Mohla-Manpur-Ambagarh
 Chowki", "Sakti", "Khairagarh-Chhuikhadan-Gandai", "Sarangarh-Bilaigarh","North Goa", "South
 Goa","Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha", "Bharuch", "Bhavnagar", "Botad",
 "Chhota Udepur", "Dahod", "Dang", "Devbhoomi Dwarka", "Gandhinagar", "Gir Somnath", "Jamnagar",
 "Junagadh", "Kheda", "Kutch", "Mahisagar", "Mehsana", "Morbi", "Narmada", "Navsari", "Panchmahal",
 "Patan", "Porbandar", "Rajkot", "Sabarkantha", "Surat", "Surendranagar", "Tapi", "Vadodara",
 "Valsad","Ambala", "Bhiwani", "Charkhi Dadri", "Faridabad", "Fatehabad", "Gurugram", "Hisar",
 "Jhajjar", "Jind", "Kaithal", "Karnal", "Kurukshetra", "Mahendragarh", "Nuh", "Palwal", "Panchkula",
 "Panipat", "Rewari", "Rohtak", "Sirsa", "Sonipat", "Yamunanagar","Bilaspur", "Chamba", "Hamirpur",
 "Kangra", "Kinnaur", "Kullu","Lahaul and Spiti", "Mandi", "Shimla", "Sirmaur", "Solan",
 "Una","Bagalkot", "Ballari (Bellary)", "Belagavi (Belgaum)", "Bengaluru Rural", "Bengaluru Urban",
 "Bidar", "Chamarajanagar", "Chikkaballapur", "Chikkamagaluru (Chikmagalur)", "Chitradurga",
 "Dakshina Kannada", "Davangere", "Dharwad", "Gadag", "Hassan", "Haveri", "Kalaburagi (Gulbarga)",
 "Kodagu", "Kolar", "Koppal", "Mandya", "Mysuru (Mysore)", "Raichur", "Ramanagara", "Shivamogga
 (Shimoga)", "Tumakuru (Tumkur)", "Udupi", "Uttara Kannada (Karwar)", "Vijayapura (Bijapur)",
 "Yadgir", "Channapatna","Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod", "Kollam",
 "Kottayam", "Kozhikode", "Malappuram", "Palakkad", "Pathanamthitta", "Thiruvananthapuram",
 "Thrissur", "Wayanad","Agar Malwa", "Alirajpur", "Anuppur"," Ashoknagar", "Balaghat", "Barwani",
 "Betul", "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh", "Datia"," Dhar","
 Dindori", "Guna", "Gwalior", "Harda", "Hoshangabad", "Indore", "Jabalpur", "Jhabua"," Katni",
 "Khandwa", "Khargone", "Mandla", "Mandsaur", "Morena", "Narsinghpur", "Neemuch","Niwari",
 "Panna", "Raisen", "Rajgarh", "Ratlam", "Rewa", "Sagar", "Satna", "Sehore", "Seoni", "Shahdol",
 "Shajapur", "Sheopur"," Shivpuri", "Sidhi"," Singrauli", "Tikamgarh", "Ujjain", "Umaria",
 "Vidisha","Ariyalur", "Chengalpattu", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri", "Dindigul",
 "Erode"," Kallakurichi", "Kanchipuram", "Kanniyakumari", "Karur", "Krishnagiri", "Madurai",
 "Mayiladuthurai", "Nagapattinam", "Namakkal", "Nilgiris", "Perambalur", "Pudukkottai",
 "Ramanathapuram", "Ranipet", "Salem", "Sivaganga", "Tenkasi", "Thanjavur", "Theni", "Thoothukudi",
 "Tiruchirappalli"," Tirunelveli", "Tirupattur", "Tiruppur", "Tiruvallur", "Tiruvannamalai", "Tiruvarur",
 "Vellore", "Villupuram", "Virudhunagar"
 );
 districtBox.setValue("Anakapalli");
 Button nextButton = new Button("Next");
 VBox vbox = new VBox(10, new Label("Select District:"), districtBox, nextButton);
 vbox.setAlignment(Pos.CENTER);
nextButton.setOnAction(e-> {
 selectedDistrict = districtBox.getValue();
 showMovieSelection();
 });
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 }
 private void showMovieSelection() {
 ChoiceBox<String> movieBox = new ChoiceBox<>();
 movieBox.getItems().addAll("Captain Miller (TAMIL)","Mission: Chapter 1(TAMIL)","Singapore
 Saloon (TAMIL)","Blue Star (TAMIL)","Vadakkupatti Ramasamy (TAMIL)","Marakkuma Nenjam
 (TAMIL)","Demonte Colony 2 (TAMIL)","Maharaja (TAMIL)","Captain Miller (ENGLISH)","Mission:
 Chapter 1 (ENGLISH)","Devil (ENGLISH)","Singapore Saloon (MALAYALAM)","Blue Star
 (MALAYALAM)","Vadakkupatti Ramasamy (MALAYALAM)","Marakkuma Nenjam
 (MALAYALAM)","Demonte Colony 2 (MALAYALAM)","Maharaja (MALAYALAM)","Singapore
 Saloon (TELUGU)","Blue Star (TELUGU)","Vadakkupatti Ramasamy (TELUGU)","Marakkuma
 Nenjam (TELUGU)","Demonte Colony 2 (TELUGU)","Maharaja (TELUGU)","Singapore Saloon
 (HINDI)","Blue Star (HINDI)","Vadakkupatti Ramasamy (HINDI)","Marakkuma Nenjam
 (HINDI)","Demonte Colony 2 (HINDI)","Maharaja (HINDI)","Singapore Saloon (KANADA)","Blue
 Star (KANADA)","Vadakkupatti Ramasamy (KANADA)","Marakkuma Nenjam (KANADA)","Demonte
 Colony 2 (KANADA)","Maharaja (KANADA)","Singapore Saloon (MARATHI)","Blue Star
 (MARATHI)","Vadakkupatti Ramasamy (MARATHI)","Marakkuma Nenjam (MARATHI)","Demonte
 Colony 2 (MARATHI)","Maharaja (MARATHI)");
 movieBox.setValue("Captain Miller (TAMIL)");
 Button nextButton = new Button("Next");
 VBox vbox = new VBox(10, new Label("Select Movie:"), movieBox, nextButton);
 vbox.setAlignment(Pos.CENTER);
 nextButton.setOnAction(e-> {
 selectedMovie = movieBox.getValue();
 showTimeSelection();
 });
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 }
 private void showTimeSelection() {
 ChoiceBox<String> timeBox = new ChoiceBox<>();
 timeBox.getItems().addAll("10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30
 PM","1:00 PM","1:30 PM","2:00 PM","2:30","3:00 PM","3:30","4:00 PM","4:30 PM","5:00 PM","5:30
PM","6:00 PM","6:30 PM","7:00 PM","7:30 PM","8:00 PM","8:30 PM","9:00 PM","9:30 PM","10:00
 PM","10:30 PM","11:00 PM","11:30 PM","12.00 AM");
 timeBox.setValue("10:00 AM");
 Button nextButton = new Button("Next");
 VBox vbox = new VBox(10, new Label("Select Time:"), timeBox, nextButton);
 vbox.setAlignment(Pos.CENTER);
 nextButton.setOnAction(e-> {
 selectedTime = timeBox.getValue();
 showSeatSelection();
 });
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 }
 private void showSeatSelection() {
 GridPane seatGrid = new GridPane();
 seatGrid.setHgap(15);
 seatGrid.setVgap(10);
 for (int i = 0; i < 100; i++) {
 Button seatButton = new Button("S" + (i + 1));
 int seatIndex = i;
 seatButton.setOnAction(e-> toggleSeatSelection(seatIndex, seatButton));
 seatGrid.add(seatButton, i % 10, i / 10);
 }
 Button confirmButton = new Button("Confirm");
 confirmButton.setOnAction(e-> showPaymentScreen());
 VBox vbox = new VBox(10, new Label("Select Seats:"), seatGrid, confirmButton);
 vbox.setAlignment(Pos.CENTER);
 Scene scene = new Scene(vbox, 500, 400);
 primaryStage.setScene(scene);
 }
 private void toggleSeatSelection(int seatIndex, Button seatButton) {
 if (selectedSeats.contains(seatIndex)) {
 selectedSeats.remove(Integer.valueOf(seatIndex));
 seatButton.setStyle("");
 } else {
selectedSeats.add(seatIndex);
 seatButton.setStyle("-fx-background-color: green;");
 }
 calculateTotal();
 }
 private void calculateTotal() {
 totalAmount = selectedSeats.size() * 150;
 }
 private void showPaymentScreen() {
 Label totalLabel = new Label("Total: ₹" + totalAmount);
 Button saveButton = new Button("Save Ticket");
 VBox vbox = new VBox(10, totalLabel, saveButton);
 vbox.setAlignment(Pos.CENTER);
 saveButton.setOnAction(e-> saveTicket());
 Scene scene = new Scene(vbox, 400, 300);
 primaryStage.setScene(scene);
 }
 private void saveTicket() {
 FileChooser fileChooser = new FileChooser();
 fileChooser.setTitle("Save Ticket");
 fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
 File file = fileChooser.showSaveDialog(primaryStage);
 if (file != null) {
 try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
 writer.write(generateTicketDetails());
 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ticket saved successfully!");
 alert.showAndWait();
 resetApp();
 } catch (IOException e) {
 showError("Failed to save ticket: " + e.getMessage());
 }
 }
 }
 private String generateTicketDetails() {
 return "Movie Ticket\n" +
 "----------------\n" +
"State: " + selectedState + "\n" +
 "District: " + selectedDistrict + "\n" +
 "Movie: " + selectedMovie + "\n" +
 "Time: " + selectedTime + "\n" +
 "Seats: " + selectedSeats + "\n" +
 "Total Amount: ₹" + totalAmount + "\n" +
 "----------------\n" +
 "Enjoy your movie!";
 }
 private void resetApp() {
 selectedState = null;
 selectedDistrict = null;
 selectedMovie = null;
 selectedTime = null;
 selectedSeats.clear();
 totalAmount = 0;
 showLoginPage();
 }
 private void showError(String message) {
 Alert alert = new Alert(Alert.AlertType.ERROR, message);
 alert.showAndWait();
 }
 private void showSuccess(String message) {
 Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
 alert.showAndWait();
 }
 public static void main(String[] args) {
 launch(args);
 }
 }