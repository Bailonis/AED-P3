package work;

import dataStructures.*;
import work.exceptions.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
class UserClass implements User, UserSet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int nif;
	private String mail;
	private int phone;
	private String name;
	private String address;
	private int money;
	private int points;
	private boolean moving;
	private List<PickUp> pickups;
	private static final int MONEY = 5;
	private static final int POINTS = 0;

	public UserClass(String id, int nif, String mail, int phone, String name, String address) {
		this.id = id;
		this.nif = nif;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.moving = false;
		this.name = name;
		money = MONEY;
		points = POINTS;
		pickups = new DoublyLinkedList<PickUp>();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getNif() {
		return nif;
	}

	@Override
	public String getEmail() {
		return mail;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public int getMoney() {
		return money;
	}

	@Override
	public int getPhone() {
		return phone;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public boolean hasPickups() {
		return pickups.size() != 0;
	}

	@Override
	public Iterator<PickUp> userPickups() throws UserNotUsedException, UserOnFirstPickupException {
		if (!this.hasPickups()) {
			if (!moving)
				throw new UserNotUsedException();
			else
				throw new UserOnFirstPickupException();
		}
		return pickups.iterator();
	}

	@Override
	public boolean isMoving() {
		return moving;
	}

	@Override
	public void buttonUser() {
		moving = !moving;

	}

	@Override
	public void chargeUser(int value) {
		money += value;

	}

	@Override
	public void addPickUp(PickUp pickUp) {

		pickups.addLast(pickUp);

	}

	@Override
	public void ticket(int value) {
		if (money - value < 0)
			money = 0;
		else
			money -= value;
		points++;

	}

	public PickUp firstPickup() {
		return pickups.getFirst();

	}
	
	public int numPickups() {
		return pickups.size();
	}

}
