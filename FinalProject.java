//stored in a package named SwingApplication in Eclipse.
package SwingApplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class User{
	//phone number & email
	String phoneNum, emailAddress;
	
	//constructors
	public User() {
		phoneNum = "None";
		emailAddress = "None";
	}
	public User(String phone, String email) {
		phoneNum = phone;
		emailAddress = email;
	}
	
	//getters
	public String getPhone() {
		return phoneNum;
	}
	public String getEmail() {
		return emailAddress;
	}
	
	//setters
	public void setPhone(String phone) {
		phoneNum = phone;
	}
	public void setEmail(String email) {
		emailAddress = email;
	}
}

class Owner extends User{
	//number of houses that he/she would enter info about
	int houseNum;
	//maximum discount provided for customers
	Double maxDiscount;
	House[] houseList;
	
	//constructors
	public Owner() {
		super();
	}
	public Owner(String phone, String email, int house, double maxDiscount) {
		super(phone, email);
		houseNum = house;
		this.maxDiscount = maxDiscount;
	}
	
	//getters
	public int getHouse() {
		return houseNum;
	}
	public double getDis() {
		return maxDiscount;
	}
	
	//setters
	public void setHouse(int house) {
		houseNum = house;
	}
	public void setDis(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	
	//create house
	public void House() {
		houseList = new House[houseNum];
	}
}

class House{
	//Owner-entered rent
	double rent; 
	//address; Owner-entered description of house
	String location, description;
	
	//constructors
	public House() {
		rent = 0.0;
		location = "None";
		description = "None";
	}
	public House(double rent, String loc, String descrip) {
		this.rent = rent;
		location = loc;
		description = descrip;
	}
	
	//getters
	public double getRent() {
		return rent;
	}
	public String getLoc() {
		return location;
	}
	public String getDescrip() {
		return description;
	}
	
	//setters
	public void setRent(int rent) {
		this.rent = rent;
	}
	public void setLoc(String loc) {
		location = loc;
	}
	public void setDescrip(String descrip) {
		description = descrip;
	}
}

class Customer extends User{
	//Customer-entered renting length; number that represents the house customer wants to rent
	int rentLength, house_num;
	//move-in date of customer
	String moveDate;
	
	//constructors
	public Customer() {
		super();
	}
	public Customer(String phone, String email) {
		super(phone, email);
	}
	public Customer(String phone, String email, int length, String date) {
		super(phone, email);
		rentLength = length;
		moveDate = date;
	}
	
	//getters
	public String getPhone() {
		return super.getPhone();
	}
	public String getEmail() {
		return super.getEmail();
	}
	public int getLength() {
		return rentLength;
	}
	public String moveDate() {
		return moveDate;
	}
	public int house_num() {
		return house_num;
	}
	
	//setters
	public void setLength(int length) {
		rentLength = length;
	}
	public void setDate(String date) {
		moveDate = date;
	}
	public void setHouse(int num) {
		house_num = num;
	}
}

//main class
public class FinalProject extends JFrame implements ActionListener{
	//number of customer logged in
	int indicator = 0;
	JTextField fTitle, status;
	JPanel info1;
	JButton start, background;
	ImageIcon bg_icon;
	Owner owner;
	Customer[] customer = new Customer[5];
	
	public FinalProject() {
		//set up the page
		setTitle("Online House Renting System");
		fTitle = new JTextField("Profile Page");
		fTitle.setEditable(false);
		status = new JTextField("Status: Unavailable");
		status.setEditable(false);
		info1 = new JPanel();
		info1.add(fTitle);
		info1.add(status);
		
		start = new JButton("Start");
		start.addActionListener(this);
		
		background = new JButton();
		bg_icon = new ImageIcon("Background.jpg");
		background.setIcon(bg_icon);
		
		Container content = getContentPane();
		content.add(info1, BorderLayout.NORTH);
		content.add(start, BorderLayout.WEST);
		content.add(background, BorderLayout.EAST);
		
		//set close operation; set size and not resizable
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setVisible(true);
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if user clicks on houseOwner
		if(choice == start) {
			//a dialog asking user to register pops up
			startPage start_dialog = new startPage(indicator);
			start_dialog.show();
			//if user is the owner
			if(start_dialog.getIdentity() == 0) {
				owner = start_dialog.get_Owner();
				for(int i = 0; i < start_dialog.getHList().length; i++) {
					owner.houseList[i] = start_dialog.getHList()[i];
				}
			}
			//if user is a customer
			else if(start_dialog.getIdentity() == 1) {
				for(int i = 0; i < start_dialog.get_Customers().length; i++) {
					customer[i] = start_dialog.get_Customers()[i];
					indicator++;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		new FinalProject();
	}
}

//startPage
class startPage extends JDialog implements ActionListener{
	//indicate the number of times a customer logs in; identity of user; total number of houses
	int indicator, identity, houseTotal;
	House[] houseList;
	JTextField sTitle;
	JPanel buttons, buttons1, buttons2;
	JButton houseOwner, Customer, Administrator, comLogo, about;
	ImageIcon com_Logo;
	Owner owner;
	Customer[] customerList = new Customer[5];
	
	public startPage(int indicator) {
		//save sent variable
		this.indicator = indicator;
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		sTitle = new JTextField("Log In Page");
		sTitle.setEditable(false);
				
		houseOwner = new JButton("House Owner");
		Customer = new JButton("Customer");
		Administrator = new JButton("Administrator");
		about = new JButton("About");
		buttons = new JPanel(new BorderLayout());
		buttons.add(houseOwner, BorderLayout.WEST);
		buttons.add(Customer, BorderLayout.CENTER);
		buttons.add(Administrator, BorderLayout.EAST);
				
		houseOwner.addActionListener(this);
		Customer.addActionListener(this);
		Administrator.addActionListener(this);
		about.addActionListener(this);
				
		comLogo = new JButton();
		com_Logo = new ImageIcon("HouseRentApp.jpg");
		comLogo.setIcon(com_Logo);
				
		Container content = getContentPane();
		content.add(sTitle, BorderLayout.NORTH);
		content.add(buttons, BorderLayout.WEST);
		content.add(comLogo, BorderLayout.EAST);
		content.add(about, BorderLayout.SOUTH);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getters
	public Owner get_Owner() {
		return owner;
	}
	public Customer[] get_Customers() {
		return customerList;
	}
	public House[] getHList() {
		return houseList;
	}
	public int getIdentity() {
		return identity;
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if user clicks on houseOwner
		if(choice == houseOwner) {
			identity = 0;
			//a dialog asking user to register pops up
			UserInput owner_dialog = new UserInput(0, owner, 0);
			owner_dialog.show();
			//check if user input a houseNum <= 5
			while(owner_dialog.getNum() > 5|| owner_dialog.getDiscount() >= 1) {
				owner_dialog.dispose();
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Sorry, there is an error in your input. Please re-enter your info.");
				owner_dialog = new UserInput(0, owner, 0);
				owner_dialog.show();
			}
			//save owner's info
			owner = new Owner(owner_dialog.getPhone(), owner_dialog.getMail(), owner_dialog.getNum(), owner_dialog.getDiscount());
			//initialize house List
			houseList = new House[owner_dialog.getNum()];
			for(int i = 0; i < owner_dialog.getNum(); i++) {
				houseList[i] = new House();
			}
			//save number of houses owner want
			houseTotal = owner_dialog.getNum();
			//copy the updated houseList
			for(int i = 0; i < owner_dialog.getHList().length; i++) {
				houseList[i] = owner_dialog.getHList()[i];
			}
		}
		//if user clicks on Customer
		if(choice == Customer) {
			if(indicator < 5) {
				identity = 1;
				//a dialog asking user to register pops up
				UserInput customer_dialog = new UserInput(1, owner, houseTotal, houseList);
				customer_dialog.show();
				//save info about registered customer
				customerList[indicator] = new Customer(customer_dialog.getPhone(), customer_dialog.getMail());
				indicator++;
				//copy the updated houseList
				for(int i = 0; i < customer_dialog.getHList().length; i++) {
					houseList[i] = customer_dialog.getHList()[i];
				}
			}
			else {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Sorry, this app provides limited service to 5 customers and 1 owner at a time"
						+ ", please restart the app to enjoy the service. Thank you for understanding!");
			}
		}
		//if user clicks on Administrator
		if(choice == Administrator) {
			String owner_info = "Owner:\nPhone: " + owner.getPhone() + "\nEmail: " + owner.getEmail() + "\nNumber of Houses: " + owner.getHouse()
				+ "\nMaximum Discount: " + owner.getDis();
			String house_info = "\nHouse(s):";
			for(int i = 0; i < houseList.length; i++) {
				house_info += "\nHouse " + (i+1) + ":\nRent(dollars per month): " + houseList[i].getRent() + "\nAddress: " + houseList[i].getLoc() + 
					"\n*Description is not shown.";
			}
			String info = owner_info + "\n" + house_info;
			Administrator_page Admin_page = new Administrator_page(info);
			Admin_page.show();
		}
		//if user clicks on about
		if(choice == about) {
			identity = 3;
			//a dialog that shows the info of the app pops up
			About aboutPage = new About();
			aboutPage.show();
		}
	}
}

//Dialog that display info about owner and houses
class Administrator_page extends JDialog{
	JTextField aTitle;
	JTextArea information;
	
	public Administrator_page(String info) {
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		aTitle = new JTextField("Administrator Page");
		aTitle.setEditable(false);
		
		information = new JTextArea(info, 50, 50);
		information.setEditable(false);
		
		Container content = getContentPane();
		content.add(aTitle, BorderLayout.NORTH);
		content.add(information, BorderLayout.CENTER);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
}

//Dialog that displays info about the app
class About extends JDialog{
	JTextField aTitle;
	JTextArea appDescrip;
	
	public About() {
		//set size
		setSize(500, 500);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		aTitle = new JTextField("About This App");
		aTitle.setEditable(false);
		appDescrip = new JTextArea("This is an app that provides the platform for house owners and customers to\ninteract with"
				+ "one another. For the stability of the app, there can be at most 1\nowner and 5 customers using the app at once."
				+ "Thank you for choosing Online\nHouse Renting System! This is a developing app and your understanding is the\nbest support for us."
				, 30, 30);
		appDescrip.setEditable(false);
		
		Container content = getContentPane();
		content.add(aTitle, BorderLayout.NORTH);
		content.add(appDescrip, BorderLayout.WEST);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
}

//Dialog that asks Owner to register
class UserInput extends JDialog implements ActionListener{
	//identity of user, total number of houses
	int identity, houseTotal;
	Owner owner;
	House[] houseList;
	JTextField oTitle, phone, phoneInput, email, emailInput, houseNum, houseInput, discountNum, discountInput;
	JPanel phoneInfo, emailInfo, houseInfo, discountInfo, info1, info2;
	JButton register;
	
	//for owner
	public UserInput(int identity, Owner owner, int houseTotal) {	
		this.identity = identity;
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		this.houseList = new House[houseTotal];
		for(int i = 0; i < houseTotal; i++) {
			this.houseList[i] = new House();
		}
		
		phone = new JTextField("Phone Number: ");
		phoneInput = new JTextField(30);
		phoneInfo = new JPanel(new BorderLayout());
		phoneInfo.add(phone, BorderLayout.WEST);
		phoneInfo.add(phoneInput, BorderLayout.EAST);
		
		email = new JTextField("Email Address: ");
		emailInput = new JTextField(30);
		emailInfo = new JPanel(new BorderLayout());
		emailInfo.add(email, BorderLayout.WEST);
		emailInfo.add(emailInput, BorderLayout.EAST);
		info2 = new JPanel(new BorderLayout());
		info2.add(emailInfo, BorderLayout.NORTH);
		info1 = new JPanel(new BorderLayout());
		
		oTitle = new JTextField("Owner Register Page");
		oTitle.setEditable(false);
			
		houseNum = new JTextField("Number of houses available: ");
		houseInput = new JTextField(30);
		houseInfo = new JPanel(new BorderLayout());
		houseInfo.add(houseNum, BorderLayout.WEST);
		houseInfo.add(houseInput, BorderLayout.EAST);
		info2.add(houseInfo, BorderLayout.SOUTH);
		
		discountNum = new JTextField("Highest discount (in decimal) available: ");
		discountInput = new JTextField(30);
		discountInfo = new JPanel(new BorderLayout());
		discountInfo.add(discountNum, BorderLayout.WEST);
		discountInfo.add(discountInput, BorderLayout.EAST);
			
		info1.add(discountInfo, BorderLayout.SOUTH);	
		info1.add(phoneInfo, BorderLayout.NORTH);
		info1.add(info2, BorderLayout.CENTER);
		register = new JButton("Register");
		
		register.addActionListener(this);

		Container content = getContentPane();
		content.add(oTitle, BorderLayout.NORTH);
		content.add(info1, BorderLayout.WEST);
		content.add(register, BorderLayout.EAST);
		
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	//for customer
	public UserInput(int identity, Owner owner, int houseTotal, House[] houseList) {
		this.identity = identity;
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		//save the sent variables
		this.owner = owner;
		this.houseTotal = houseTotal;
		//copy the sent house list
		this.houseList = new House[houseTotal];
		for(int i = 0; i < houseTotal; i++) {
			this.houseList[i] = houseList[i];
		}
		
		phone = new JTextField("Phone Number: ");
		phoneInput = new JTextField(30);
		phoneInfo = new JPanel(new BorderLayout());
		phoneInfo.add(phone, BorderLayout.WEST);
		phoneInfo.add(phoneInput, BorderLayout.EAST);
		
		email = new JTextField("Email Address: ");
		emailInput = new JTextField(30);
		emailInfo = new JPanel(new BorderLayout());
		emailInfo.add(email, BorderLayout.WEST);
		emailInfo.add(emailInput, BorderLayout.EAST);
		info2 = new JPanel(new BorderLayout());
		info2.add(emailInfo, BorderLayout.NORTH);
		info1 = new JPanel(new BorderLayout());
		
		oTitle = new JTextField("Customer Register Page");
		oTitle.setEditable(false);
		
		info1.add(phoneInfo, BorderLayout.NORTH);
		info1.add(info2, BorderLayout.CENTER);
		register = new JButton("Register");
		
		register.addActionListener(this);

		Container content = getContentPane();
		content.add(oTitle, BorderLayout.NORTH);
		content.add(info1, BorderLayout.WEST);
		content.add(register, BorderLayout.EAST);
		
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getters
	public String getPhone() {
		return phoneInput.getText();
	}
	public String getMail() {
		return emailInput.getText();
	}

	public int getNum() {
		return Integer.parseInt(houseInput.getText());
	}
	public double getDiscount() {
		return Double.parseDouble(discountInput.getText());
	}
	public House[] getHList() {
		return houseList;
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if user clicks on register
		if(choice == register) {
			dispose();
			JFrame jFrame = new JFrame();
			//if user is the owner
			if(identity == 0) {
				//check if the owner-entered number of houses <= 5 and owner-entered discount < 1
				if (Integer.parseInt(houseInput.getText()) <= 5 && Double.parseDouble(discountInput.getText()) < 1) {
					JOptionPane.showMessageDialog(jFrame, "Successful Log-in! Welcome!");
					ProfilePage owner_page = new ProfilePage(Integer.parseInt(houseInput.getText()), owner, 0);
					owner_page.show();
					//save the owner-entered house number
					houseTotal = Integer.parseInt(houseInput.getText());
					//initialize house list
					houseList = new House[houseTotal];
					//copy updated house list
					for(int i = 0; i < owner_page.getHList().length; i++) {
						houseList[i] = owner_page.getHList()[i];
					}
				}
			}
			//if user is a customer
			else if(identity == 1) {
				JOptionPane.showMessageDialog(jFrame, "Successful Log-in! Welcome!");
				ProfilePage customer_page = new ProfilePage(houseList, houseTotal, owner, 1);
				customer_page.show();
				//copy updated house list
				for(int i = 0; i < customer_page.getHList().length; i++) {
					houseList[i] = customer_page.getHList()[i];
				}
			}
		}
	}
}

//A window that shows information about the user
class ProfilePage extends JDialog implements ActionListener{
	//total number of houses; identity of user
	int houseNum, identity;
	//indicate if the Details button is clicked the first time
	int indicator = 0;
	Owner owner;
	House[] houseList;
	JTextField fTitle, status, houseInfo;
	JButton Details, background, LogOut;
	JPanel info1, info2;
	ImageIcon bg_icon;
	
	//constructor for owner
	public ProfilePage(int houseNum, Owner owner, int identity) {
		//save the sent variables
		this.houseNum = houseNum;
		this.identity = identity;
		//initialize the house list
		this.houseList = new House[houseNum];
		for(int i = 0; i < houseNum; i++) {
			this.houseList[i] = new House();
		}
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		if(identity == 0) {
			fTitle = new JTextField("Owner Profile Page");
			fTitle.setEditable(false);
		}
		status = new JTextField("Status: Logged in");
		status.setEditable(false);
		houseInfo = new JTextField("House Information");
		houseInfo.setEditable(false);
				
		Details = new JButton("Details");
		Details.addActionListener(this);
		LogOut = new JButton("Log Out");
		LogOut.addActionListener(this);
		info1 = new JPanel(new BorderLayout());
		info1.add(fTitle, BorderLayout.NORTH);
		info1.add(status, BorderLayout.SOUTH);
		info2 = new JPanel(new BorderLayout());
		info2.add(houseInfo, BorderLayout.NORTH);
		info2.add(Details, BorderLayout.CENTER);
		info2.add(LogOut, BorderLayout.SOUTH);
				
		background = new JButton();
		bg_icon = new ImageIcon("Background.jpg");  //Look for this pic
		background.setIcon(bg_icon);
				
		Container content = getContentPane();
		content.add(info1, BorderLayout.NORTH);
		content.add(info2, BorderLayout.WEST);
		content.add(background, BorderLayout.EAST);
				
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	//constructor for customer
	public ProfilePage(House[] houseList, int houseNum, Owner owner, int identity) {
		//save the sent variables
		this.houseNum = houseNum;
		this.identity = identity;
		this.houseList = new House[houseNum];
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		this.owner = owner;
		fTitle = new JTextField("Customer Profile Page");
		fTitle.setEditable(false);
		for(int i = 0; i < houseNum; i++) {
			this.houseList[i] = houseList[i];
		}
		status = new JTextField("Status: Logged in");
		status.setEditable(false);
		houseInfo = new JTextField("House Information");
		houseInfo.setEditable(false);
				
		Details = new JButton("Details");
		Details.addActionListener(this);
		LogOut = new JButton("Log Out");
		LogOut.addActionListener(this);
		info1 = new JPanel(new BorderLayout());
		info1.add(fTitle, BorderLayout.NORTH);
		info1.add(status, BorderLayout.SOUTH);
		info2 = new JPanel(new BorderLayout());
		info2.add(houseInfo, BorderLayout.NORTH);
		info2.add(Details, BorderLayout.CENTER);
		info2.add(LogOut, BorderLayout.SOUTH);
				
		background = new JButton();
		bg_icon = new ImageIcon("Background.jpg");  //Look for this pic
		background.setIcon(bg_icon);
				
		Container content = getContentPane();
		content.add(info1, BorderLayout.NORTH);
		content.add(info2, BorderLayout.WEST);
		content.add(background, BorderLayout.EAST);
				
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getter
	public House[] getHList() {
		return houseList;
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if user clicks Details
		if(choice == Details) {
			//if user is a customer
			if(identity == 1) {
				//indicate this is not the first time the buttons is clicked
				indicator++;
			}
			HousesPage HousePage = new HousesPage(houseList, houseNum, identity, owner, indicator);
			HousePage.show();
			//copy the house List modified by user
			for(int i = 0; i < HousePage.getHList().length; i++) {
				houseList[i] = HousePage.getHList()[i];
			}
		}
		//if user clicks LogOut
		if(choice == LogOut) {
			dispose();
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Successfully Logged Out!");
			
		}
	}
}

//Dialog that allows user to access details about Houses
class HousesPage extends JDialog implements ActionListener{
	//identity of user; indicate if the buttons are initiated for the first time the Details button on ProfilePage is clicked
	int identity, indicator;
	Owner owner;
	House[] houseList;
	JPanel info, info1, info2;
	JTextField eTitle;
	JTextArea houseStatus;
	JButton house1, house2, house3, house4, house5, Exit;
	
	public HousesPage() {
		dispose();
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "Error! Please retry.");
	}
	
	public HousesPage(House[] houseList, int houseNum, int identity, Owner owner, int indicator) {
		//save the sent variables
		this.identity = identity;
		this.owner = owner;
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		eTitle = new JTextField("Houses Details");
		eTitle.setEditable(false);
		Exit = new JButton("Exit");
		Exit.addActionListener(this);
		
		Container content = getContentPane();
		//set up the houseList and buttons
		//if user is a customer
		if(identity == 1) {
			this.houseList = new House[houseNum];
			//save the set info about houses
			for(int i = 0; i < houseNum; i++) {
				this.houseList[i] = houseList[i];
			}
		}
		if(houseNum == 1){
			house1 = new JButton("House 1");
			house1.addActionListener(this);
			
			info1 = new JPanel(new BorderLayout());
			info1.add(house1, BorderLayout.NORTH);
			content.add(info1, BorderLayout.CENTER);
			
			//check if this is the first time the button is clicked
			if(indicator == 0) {
				this.houseList = new House[houseNum];
				//if yes, initiate the house list
				for(int i = 0; i < houseNum; i++) {
					this.houseList[i] = new House();
				}
				indicator++;
			}
		}
		else if(houseNum == 2) {
			house1 = new JButton("House 1");
			house2 = new JButton("House 2");
			house1.addActionListener(this);
			house2.addActionListener(this);
			
			info1 = new JPanel(new BorderLayout());
			info1.add(house1, BorderLayout.NORTH);
			info1.add(house2, BorderLayout.SOUTH);
			content.add(info1, BorderLayout.CENTER);
			
			//check if this is the first time the button is clicked
			if(indicator == 0) {
				this.houseList = new House[houseNum];
				//if yes, initiate the house list
				for(int i = 0; i < houseNum; i++) {
					this.houseList[i] = new House();
				}
				indicator++;
			}
		}
		else if(houseNum == 3) {
			house1 = new JButton("House 1");
			house2 = new JButton("House 2");
			house3 = new JButton("House 3");
			house1.addActionListener(this);
			house2.addActionListener(this);
			house3.addActionListener(this);
			
			info1 = new JPanel(new BorderLayout());
			info1.add(house1, BorderLayout.NORTH);
			info1.add(house2, BorderLayout.SOUTH);
			info2 = new JPanel(new BorderLayout());
			info2.add(house3, BorderLayout.NORTH);
			info = new JPanel(new BorderLayout());
			info.add(info1, BorderLayout.NORTH);
			info.add(info2, BorderLayout.CENTER);
			content.add(info, BorderLayout.CENTER);
			
			//check if this is the first time the button is clicked
			if(indicator == 0) {
				this.houseList = new House[houseNum];
				//if yes, initiate the house list
				for(int i = 0; i < houseNum; i++) {
					this.houseList[i] = new House();
				}
				indicator++;
			}
		}
		else if(houseNum == 4) {
			house1 = new JButton("House 1");
			house2 = new JButton("House 2");
			house3 = new JButton("House 3");
			house4 = new JButton("House 4");
			house1.addActionListener(this);
			house2.addActionListener(this);
			house3.addActionListener(this);
			house4.addActionListener(this);
			
			info1 = new JPanel(new BorderLayout());
			info1.add(house1, BorderLayout.NORTH);
			info1.add(house2, BorderLayout.SOUTH);
			info2 = new JPanel(new BorderLayout());
			info2.add(house3, BorderLayout.NORTH);
			info2.add(house4, BorderLayout.SOUTH);
			info = new JPanel(new BorderLayout());
			info.add(info1, BorderLayout.NORTH);
			info.add(info2, BorderLayout.CENTER);
			content.add(info, BorderLayout.CENTER);
			
			//check if this is the first time the button is clicked
			if(indicator == 0) {
				this.houseList = new House[houseNum];
				//if yes, initiate the house list
				for(int i = 0; i < houseNum; i++) {
					this.houseList[i] = new House();
				}
				indicator++;
			}
		}
		else if(houseNum == 5) {
			house1 = new JButton("House 1");
			house2 = new JButton("House 2");
			house3 = new JButton("House 3");
			house4 = new JButton("House 4");
			house5 = new JButton("House 5");
			house1.addActionListener(this);
			house2.addActionListener(this);
			house3.addActionListener(this);
			house4.addActionListener(this);
			house5.addActionListener(this);
			
			info1 = new JPanel(new BorderLayout());
			info1.add(house1, BorderLayout.NORTH);
			info1.add(house2, BorderLayout.SOUTH);
			info2 = new JPanel(new BorderLayout());
			info2.add(house3, BorderLayout.NORTH);
			info2.add(house4, BorderLayout.SOUTH);
			info = new JPanel(new BorderLayout());
			info.add(info1, BorderLayout.NORTH);
			info.add(info2, BorderLayout.CENTER);
			info.add(house5, BorderLayout.SOUTH);
			content.add(info, BorderLayout.CENTER);
			
			//check if this is the first time the button is clicked
			if(indicator == 0) {
				this.houseList = new House[houseNum];
				//if yes, initiate the house list
				for(int i = 0; i < houseNum; i++) {
					this.houseList[i] = new House();
				}
				indicator++;
			}
		}
		
		content.add(eTitle, BorderLayout.NORTH);
		content.add(Exit, BorderLayout.SOUTH);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getters
	public House[] getHList() {
		return houseList;
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();

		//if user clicks on Exit
		if(choice == Exit) {
			dispose();
		}
		//if user clicks on house1 button
		else if(choice == house1) {
			HouseDetail HouseInputPage = new HouseDetail(identity, houseList, owner, 1);
			HouseInputPage.show();
			//if user is the owner
			if(identity == 0) {
				//save info entered
				houseList[0] = new House(HouseInputPage.getHRent(), HouseInputPage.getHAddress(), HouseInputPage.getHDescrip());
			}
			else {
				for(int i = 0; i < HouseInputPage.getHList().length; i++) {
					this.houseList[i] = HouseInputPage.getHList()[i];
				}
			}
		}
		//if user clicks on house2 button
		else if(choice == house2) {
			HouseDetail HouseInputPage = new HouseDetail(identity, houseList, owner, 2);
			HouseInputPage.show();
			//if user is the owner
			if(identity == 0) {
				//save info entered
				houseList[1] = new House(HouseInputPage.getHRent(), HouseInputPage.getHAddress(), HouseInputPage.getHDescrip());
			}
			//if user is a customer
			else {
				//save the updated house list
				for(int i = 0; i < HouseInputPage.getHList().length; i++) {
					this.houseList[i] = HouseInputPage.getHList()[i];
				}
			}		
		}
		//if user clicks on house3 button
		else if(choice == house3) {
			HouseDetail HouseInputPage = new HouseDetail(identity, houseList, owner, 3);
			HouseInputPage.show();
			//if user is the owner
			if(identity == 0) {
				//save info entered
				houseList[2] = new House(HouseInputPage.getHRent(), HouseInputPage.getHAddress(), HouseInputPage.getHDescrip());
			}
			//if user is a customer
			else {
				//save the updated house list
				for(int i = 0; i < HouseInputPage.getHList().length; i++) {
					this.houseList[i] = HouseInputPage.getHList()[i];
				}
			}		
		}
		//if user clicks on house4 button
		else if(choice == house4) {
			HouseDetail HouseInputPage = new HouseDetail(identity, houseList, owner, 4);
			HouseInputPage.show();
			//if user is the owner
			if(identity == 0) {
				//save info entered
				houseList[3] = new House(HouseInputPage.getHRent(), HouseInputPage.getHAddress(), HouseInputPage.getHDescrip());
			}
			//if user is a customer
			else {
				//save the updated house list
				for(int i = 0; i < HouseInputPage.getHList().length; i++) {
					this.houseList[i] = HouseInputPage.getHList()[i];
				}
			}		
		}
		//if user clicks on house5 button
		else if(choice == house5) {
			HouseDetail HouseInputPage = new HouseDetail(identity, houseList, owner, 5);
			HouseInputPage.show();
			//if user is the owner
			if(identity == 0) {
				//save the info entered
				houseList[4] = new House(HouseInputPage.getHRent(), HouseInputPage.getHAddress(), HouseInputPage.getHDescrip());
			}
			//if user is a customer
			else {
				//save the updated house list
				for(int i = 0; i < HouseInputPage.getHList().length; i++) {
					this.houseList[i] = HouseInputPage.getHList()[i];
				}
			}		
		}
	}
}

//Dialog that asks owner to input house information and customer to see house information
class HouseDetail extends JDialog implements ActionListener{
	//indicate identity of user; total number of houses; the house chosen; minimum renting length set by owner
	int identity, houseNum, houseNo;
	Owner owner;
	House[] houseList;
	JTextField addressInfo, addressInput, phoneInfo, phoneInput, emailInfo, emailInput, rentInfo, rentInput, lengthInfo, lengthInput, hiTitle;
	JTextArea descrip;
	JPanel address, rent, phone, email, info1, info2, info3, info4;
	JButton Save, Rent;
	
	public HouseDetail(int identity, House[] houseList, Owner owner, int houseNo) {
		//save the value of sent variables
		this.identity = identity;
		this.houseNum = houseList.length;
		this.houseNo = houseNo;
		this.owner = owner;
		//initialize list of houses
		this.houseList = new House[houseNum];
		for(int i = 0; i < houseNum; i++) {
			this.houseList[i] = new House();
		}
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		hiTitle = new JTextField("House Information Page");
		hiTitle.setEditable(false);
		
		Container content = getContentPane();
		content.add(hiTitle, BorderLayout.NORTH);
		//if user is the owner, allow user to edit info
		if(identity == 0) {
			addressInfo = new JTextField("Address: ");
			addressInput = new JTextField(30);
			rentInfo = new JTextField("Rent (Canadian dollars per month): ");
			rentInput = new JTextField(30);
			phoneInfo = new JTextField("Contact Phone Number: ");
			phoneInput = new JTextField(30);
			emailInfo = new JTextField("Contact Email: ");
			emailInput = new JTextField(30);
			descrip = new JTextArea("Description: ", 30, 30);
			Save = new JButton("Save");
			Save.addActionListener(this);
			content.add(Save, BorderLayout.EAST);
		}
		//if user is a customer, disable user from editing info
		else if(identity == 1) {
			//save info about houses
			for(int i = 0; i < houseList.length; i++) {
				this.houseList[i] = houseList[i];
			}
			addressInfo = new JTextField("Address: ");
			addressInput = new JTextField(this.houseList[houseNo-1].getLoc());
			addressInput.setEditable(false);
			rentInfo = new JTextField("Rent (Canadian dollars per month): ");
			rentInput = new JTextField(Double.toString(houseList[houseNo-1].getRent()));
			rentInput.setEditable(false);
			phoneInfo = new JTextField("Contact Phone Number: ");
			phoneInput = new JTextField(owner.getPhone());
			phoneInput.setEditable(false);
			emailInfo = new JTextField("Contact Email: ");
			emailInput = new JTextField(owner.getEmail());
			emailInput.setEditable(false);
			descrip = new JTextArea(this.houseList[houseNo-1].getDescrip(), 30, 30);
			descrip.setEditable(false);
			Rent = new JButton("Rent");
			Rent.addActionListener(this);
			content.add(Rent, BorderLayout.EAST);
		}
		address = new JPanel(new BorderLayout());
		address.add(addressInfo, BorderLayout.WEST);
		address.add(addressInput, BorderLayout.EAST);
		rent = new JPanel(new BorderLayout());
		rent.add(rentInfo, BorderLayout.WEST);
		rent.add(rentInput, BorderLayout.EAST);
		phone = new JPanel(new BorderLayout());
		phone.add(phoneInfo, BorderLayout.WEST);
		phone.add(phoneInput, BorderLayout.EAST);
		email = new JPanel(new BorderLayout());
		email.add(emailInfo, BorderLayout.WEST);
		email.add(emailInput, BorderLayout.EAST);
		
		info1 = new JPanel(new BorderLayout());
		info1.add(address, BorderLayout.NORTH);
		info1.add(rent, BorderLayout.SOUTH);
		info2 = new JPanel(new BorderLayout());
		info2.add(descrip, BorderLayout.SOUTH);
		info3 = new JPanel(new BorderLayout());
		info3.add(phone, BorderLayout.NORTH);
		info3.add(email, BorderLayout.SOUTH);
		info4 = new JPanel(new BorderLayout());
		info4.add(info1, BorderLayout.NORTH);
		info4.add(info2, BorderLayout.CENTER);
		info4.add(info3, BorderLayout.SOUTH);
		content.add(info4, BorderLayout.WEST);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getters
	public String getHAddress() {
		return addressInput.getText();
	}
	public double getHRent() {
		return Double.parseDouble(rentInput.getText());
	}
	public int getHNum() {
		return houseNum;
	}
	public int getHNo() {
		return houseNo;
	}
	public String getHDescrip() {
		return descrip.getText();
	}
	public House[] getHList() {
		return houseList;
	}
	public int getIdentity() {
		return identity;
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if the owner wants to save data
		if(identity == 0) {
			if(choice == Save) {
				dispose();
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Successfully Saved!");
			}
		}
		//if a customer want to rent the house
		else if(identity == 1) {
			if(choice == Rent) {
				//create a page for renting purpose
				HouseRent HouseRentPage = new HouseRent(houseList, houseNo, owner);
				HouseRentPage.show();
				//remove the house from the list if a customer rent it
				if(HouseRentPage.get_indicator() == 1) {
					houseList[houseNo-1] = new House();
				}
				dispose();
			}
		}
	}
}

//the page for which customers use to rent houses
class HouseRent extends JDialog implements ActionListener{
	//indicate if house is rented
	int indicator = 0;
	//discount desired by user
	double entered_discount= 0.0;
	Owner owner;
	//Owner-set rent and discount
	Double rent, Owner_discount;
	JTextField cTitle, phoneInfo, phoneInput, emailInfo, emailInput, lengthInfo, lengthInput, timeInfo, timeInput, discount_value, dtitle, rentInfo;
	JPanel negociate_panel, phone, email, length, time, info1, info2, info3;
	JButton Confirm, Negotiate;
	
	public HouseRent(House[] houseList, int houseNo, Owner owner) {
		rent = houseList[houseNo-1].getRent();
		Owner_discount = owner.getDis();
		this.owner = owner;
		//set size
		setSize(800, 800);
		setModal(true);
		//set up the page
		setTitle("Online House Renting System");
		cTitle = new JTextField("House Renting Page");
		cTitle.setEditable(false);
		
		phoneInfo = new JTextField("Contact Phone Number: ");
		phoneInput = new JTextField(30);
		phone = new JPanel(new BorderLayout());
		phone.add(phoneInfo, BorderLayout.WEST);
		phone.add(phoneInput, BorderLayout.EAST);
		emailInfo = new JTextField("Contact Email: ");
		emailInput = new JTextField(30);
		email = new JPanel(new BorderLayout());
		email.add(emailInfo, BorderLayout.WEST);
		email.add(emailInput, BorderLayout.EAST);
		info1 = new JPanel(new BorderLayout());
		info1.add(phone, BorderLayout.NORTH);
		info1.add(email, BorderLayout.SOUTH);
		
		lengthInfo = new JTextField("Length You want to rent (in months): ");
		lengthInput = new JTextField(30);
		length = new JPanel(new BorderLayout());
		length.add(lengthInfo, BorderLayout.WEST);
		length.add(lengthInput, BorderLayout.EAST);
		timeInfo = new JTextField("Time You would move in (Month/Year): ");
		timeInput = new JTextField(30);
		time = new JPanel(new BorderLayout());
		time.add(timeInfo, BorderLayout.WEST);
		time.add(timeInput, BorderLayout.EAST);
		info2 = new JPanel(new BorderLayout());
		info2.add(length, BorderLayout.NORTH);
		info2.add(time, BorderLayout.SOUTH);
		
		dtitle = new JTextField("You have the choice to haggle with the owner!\nPlease enter the discount you desire (in decimal): ");
		discount_value = new JTextField(30);
		Negotiate = new JButton("Negotiate");
		Negotiate.addActionListener(this);
		negociate_panel = new JPanel(new BorderLayout());
		negociate_panel.add(dtitle, BorderLayout.NORTH);
		negociate_panel.add(discount_value, BorderLayout.WEST);
		negociate_panel.add(Negotiate, BorderLayout.EAST);
		info3 = new JPanel(new BorderLayout());
		info3.add(info1, BorderLayout.NORTH);
		info3.add(info2, BorderLayout.CENTER);
		info3.add(negociate_panel, BorderLayout.SOUTH); 
		
		Confirm = new JButton("Confirm");
		Confirm.addActionListener(this);
		
		Container content = getContentPane();
		content.add(cTitle, BorderLayout.NORTH);
		content.add(Confirm, BorderLayout.EAST);
		content.add(info3, BorderLayout.WEST);
		
		//set close operation; set not resizable
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	//getters
	public int get_indicator() {
		return indicator;
	}
	public String get_time() {
		return timeInput.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		//The action user makes
		Object choice = e.getSource();
		
		//if user clicks on Confirm
		if(choice == Confirm) {
			//check if user enters a desirable discount
			if(!discount_value.getText().equals(null)) {
				//check if user enters a discount <=  the one set by owner
				if(Double.parseDouble(discount_value.getText()) <= Owner_discount) {
					//indicate that this house is successfully rented out
					indicator++;
					entered_discount = Double.parseDouble(discount_value.getText());
					dispose();
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Your payment is made! The total rent is: $" + (Integer.parseInt(lengthInput.getText())*rent
						*(1-entered_discount)) + " Thank you for renting with Online House Renting System!");
				}
				//if the entered discount > what owner accepts
				else {
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Sorry, the owner does not accept this discount %, try again!");
				}
			}
			//if user does not enter a desirable discount
			else {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Your payment is made! The total rent is: $" + (Integer.parseInt(lengthInput.getText())*rent) 
					+ " Thank you for renting with Online House Renting System!");
			}
		}
		//if user clicks Negotiate
		else if(choice == Negotiate) {
			//check if the entered discount <= the one set by owner
			if(Double.parseDouble(discount_value.getText()) <= Owner_discount) {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "The owner accept this discount %! Close this window and click on Confirm button to finish renting! ");
			}
			//if entered discount the one set by owner
			else {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Sorry, the owner does not accept this discount %, try again!");
			}
		}
	}
}
