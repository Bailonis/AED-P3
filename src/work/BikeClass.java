package work;

import work.exceptions.*;
import dataStructures.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */

class BikeClass implements Bike {

	private static final long serialVersionUID = 1L;
	private String id;
	private String license;
	private ParkSet currentPark;
	private User currentUser;
	private boolean moving;
	private List<PickUp> pickups;

	public BikeClass(String id, ParkSet park, String license) {
		this.id = id;
		this.currentPark = park;
		this.license = license;
		this.currentUser = null;
		this.moving = false;
		pickups = new DoublyLinkedList<PickUp>();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String license() {
		return license;
	}

	@Override
	public ParkSet currentPark() {
		return currentPark;
	}

	@Override
	public boolean hasPickups() {
		return pickups.size() != 0;
	}

	@Override
	public Iterator<PickUp> bikePickups() throws BikeNotUsedException, BikeOnFirstPickupException {
		if (!this.hasPickups()) {
			if (!moving)
				throw new BikeNotUsedException();
			else
				throw new BikeOnFirstPickupException();
		}
		return pickups.iterator();
	}

	@Override
	public User getCurrentUser() {
		return currentUser;
	}

	@Override
	public boolean isMoving() {

		return moving;
	}

	@Override
	public void buttonBike() {
		moving = !moving;

	}

	@Override
	public void changeCurrentPark(ParkSet park) {
		currentPark = park;

	}

	@Override
	public void changeCurrentUser(User user) {
		currentUser = user;

	}

	@Override
	public void addPickUp(PickUp pickUp) {
		pickups.addLast(pickUp);

	}

	@Override
	public PickUpSet lastPickup() {

		return (PickUpSet) pickups.getLast();
	}

	@Override
	public PickUp firstPickup() {
		return pickups.getFirst();

	}

	public int numPickups() {
		return pickups.size();
	}

	

}
