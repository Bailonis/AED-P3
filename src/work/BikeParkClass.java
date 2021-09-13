package work;

import dataStructures.*;
import work.exceptions.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public class BikeParkClass implements BikePark {

	private static final int DEFAULT = 1000;

	private static final long serialVersionUID = 1L;
	private static final String COMMA = ", ";
	private static final String COLON = ": ";
	private static final String EUROS = " euros";
	private static final String POINTS = " pontos";
	private static final int MONEY = 5;
	private static final int INVALID = 0;
	private int maxPickup;

	private Dictionary<String, Bike> bikeList;
	private Dictionary<String, UserSet> userList;
	private Dictionary<String, ParkSet> parkList;

	private AVLTree<Integer, List<User>> delayUsers;

	// private List<PickUpSet> pickUpsList;

	public BikeParkClass() {

		bikeList = new ChainedHashTable<String, Bike>(DEFAULT);
		userList = new ChainedHashTable<String, UserSet>(DEFAULT);
		parkList = new ChainedHashTable<String, ParkSet>(DEFAULT / 2);

		delayUsers = new AVLTree<Integer, List<User>>();
		maxPickup = 0;

	}

	/**
	 * Verifica se existe um utilizador com o dado id
	 * 
	 * @param id
	 *            identificador do utilizador
	 * @return true se existir utilizador, false caso contrario
	 */
	private boolean hasUser(String id) {
		boolean result = false;
		if (userList.find(id) != null)
			result = true;
		return result;
	}

	@Override
	public void addUser(String id, int nif, String mail, int phone, String name, String address)
			throws ExistentUserException {
		if (this.hasUser(id))
			throw new ExistentUserException();
		else
			userList.insert(id, (new UserClass(id, nif, mail, phone, name, address)));
	}

	/**
	 * Verifica se o utilizador com o dado id tem pickups
	 * 
	 * @param id
	 *            identificador do utilizador
	 * @return true se o utilizador tiver pickups, false caso contrario
	 */
	private boolean userHasPickups(String id) {
		User user = userList.find(id);
		return user.hasPickups();
	}

	@Override
	public void removeUser(String id) throws NonExistentUserException, UserHasPickupsException {
		User result = null;

		result = userList.find(id);
		if (result == null)
			throw new NonExistentUserException();
		else if (this.userHasPickups(id))
			throw new UserHasPickupsException();
		else
			userList.remove(result.getId());

	}

	/**
	 * Retorna o utilizador com o dado id
	 * 
	 * @param id
	 *            identififacor do utilizador
	 * @return utilizador com o dado id
	 * @throws NonExistentUserException
	 *             caso nao exista nenhum utilizador no sistema com o dado id
	 */
	private UserSet getUser(String id) throws NonExistentUserException {
		UserSet result = null;

		result = userList.find(id);

		if (result == null)
			throw new NonExistentUserException();
		return result;
	}

	@Override
	public void addPark(String id, String name, String address) throws ExistentParkException {
		Park park = parkList.find(id);
		if (park != null)
			throw new ExistentParkException();
		else
			parkList.insert(id, new ParkClass(id, name, address));
	}

	@Override
	public void addBike(String id, String idPark, String license)
			throws ExistentBikeException, NonExistentParkException {
		ParkSet park = parkList.find(idPark);
		if (this.hasBike(id))
			throw new ExistentBikeException();
		else if (park == null)
			throw new NonExistentParkException();
		else {
			bikeList.insert(id, (new BikeClass(id, park, license)));
			park.addBike();
		}

	}

	/**
	 * Verifica se uma bicicleta tem pickups
	 * 
	 * @param id
	 *            identificador da bicicleta
	 * @return true se a bicicleta tiver pickups, false caso contrario
	 */
	public boolean bikeHasPickups(String id) {
		Bike bike = bikeList.find(id);
		return bike.hasPickups();
	}

	@Override
	public void removeBike(String id) throws NonExistentBikeException, BikeHasPickupsException {

		Bike result = null;

		result = bikeList.find(id);

		if (result == null)
			throw new NonExistentBikeException();
		else if (result.hasPickups())
			throw new BikeHasPickupsException();
		else {
			ParkSet park = result.currentPark();
			park.removeBike();
			bikeList.remove(result.getId());

		}
	}

	/**
	 * Retorna o parque com o dado id
	 * 
	 * @param id
	 *            id po parque
	 * @return parque com o dado id
	 * @throws NonExistentParkException
	 *             caso nao exista nenhum parque no sistema com o dado id
	 */
	private ParkSet getPark(String id) throws NonExistentParkException {
		ParkSet result = null;

		result = parkList.find(id);

		if (result == null)
			throw new NonExistentParkException();
		return result;

	}

	/**
	 * Retorna o pickup que tem a bicicleta com o bike Id
	 * 
	 * @param bikeId
	 *            id da bicicleta que esta no pickup
	 * @return pick up que tem a bicicleta com o bike Id
	 */

	/**
	 * Verifica se existe uma dada bicicleta no sistema
	 * 
	 * @param id
	 *            id da bicicleta
	 * @return true se houver, false caso contrario
	 */
	private boolean hasBike(String id) {
		boolean result = false;
		if (bikeList.find(id) != null)
			result = true;
		return result;
	}

	@Override
	public void pickup(String idBike, String idUser) throws NonExistentBikeException, MovingBikeException,
			NonExistentUserException, MovingUserException, NoMoneyException {

		Bike bike = this.getBike(idBike);
		UserSet user = userList.find(idUser);
		ParkSet inicialPark = bike.currentPark();
		if (user == null)
			throw new NonExistentUserException();
		else if (bike.isMoving())
			throw new MovingBikeException();
		else if (user.isMoving())
			throw new MovingUserException();
		else if (MONEY > user.getMoney())
			throw new NoMoneyException();

		else {
			PickUpSet pickup = new PickUpClass(user, bike, inicialPark);
			bike.currentPark().removeBike();
			bike.buttonBike();
			bike.changeCurrentPark(null);
			bike.changeCurrentUser(user);
			user.buttonUser();
			bike.addPickUp(pickup);
			user.addPickUp(pickup);

			inicialPark.addPickups();
			if (inicialPark.numbPickups() + 1 > maxPickup)
				maxPickup = inicialPark.numbPickups();
		}

	}

	@Override
	public void ticket(String idBike, int min) throws NonExistentBikeException, NonMovingBikeException {
		Bike bike = this.getBike(idBike);

		if (!bike.isMoving())
			throw new NonMovingBikeException();

		else {

			PickUpSet pickup = bike.lastPickup();
			UserSet user = pickup.getUser();
			pickup.ticket(min);
			int y = pickup.delay();
			int x = y / 30 + (y % 30 != 0 ? 1 : 0);

			List<User> list = delayUsers.find(user.getPoints() * -1 - 1);

			if (user.getPoints() != 0) {
				List<User> remove = delayUsers.find(user.getPoints() * -1);
				remove.remove(user);

			}

			if (list == null) {
				list = new DoublyLinkedList<User>();

				user.ticket(x);
				list.addLast(user);
				delayUsers.insert(user.getPoints() * -1, list);
			} else {

				user.ticket(x);
				list.addLast(user);
				delayUsers.insert(user.getPoints() * -1, list);

			}
		}

	}

	@Override
	public void pickdown(String idBike, String idPark, int min) throws NonExistentBikeException,
			NonExistentUserException, NonMovingBikeException, NonExistentParkException, InvalidNumberException {
		Bike bike = this.getBike(idBike);
		ParkSet park = this.getPark(idPark);

		if (!bike.isMoving())
			throw new NonMovingBikeException();
		else if (min <= INVALID)
			throw new InvalidNumberException();
		else {

			PickUpSet pickup = bike.lastPickup();
			UserSet user = pickup.getUser();

			pickup.pickDown(park, min);

			bike.buttonBike();
			bike.changeCurrentPark(park);
			bike.changeCurrentUser(null);
			user.buttonUser();

			park.addBike();

		}

	}

	@Override
	public void chargeUser(String idUser, int value) throws NonExistentUserException, InvalidNumberException {

		UserSet userObject = this.getUser(idUser);
		if (value <= INVALID)
			throw new InvalidNumberException();
		else
			userObject.chargeUser(value);
	}

	/**
	 * Retorna a bicicleta com o dado id
	 * 
	 * @param idBike
	 *            id da bicicleta que se procura
	 * @return bicicleta com o dado id
	 * @throws NonExistentBikeException
	 *             caso o id dado nao esteja no sistema
	 */
	private Bike getBike(String idBike) throws NonExistentBikeException {
		Bike result = null;

		result = bikeList.find(idBike);

		if (result == null)
			throw new NonExistentBikeException();
		return result;
	}

	@Override
	public boolean parkedBike(String idBike, String idPark) throws NonExistentBikeException, NonExistentParkException {
		Bike bike = this.getBike(idBike);
		Park park = this.getPark(idPark);
		if (park == null || bike == null)
			return false;
		else
			return !bike.isMoving();

	}

	@Override
	public String userInfo(String id) throws NonExistentUserException {
		User user = this.getUser(id);
		String result = user.getName() + COLON + user.getNif() + COMMA + user.getAddress() + COMMA + user.getEmail()
				+ COMMA + user.getPhone() + COMMA + user.getMoney() + COMMA + user.getPoints();
		return result;
	}

	@Override
	public String parkInfo(String id) throws NonExistentParkException {
		Park park = this.getPark(id);
		String result = park.getName() + COLON + park.getAddress() + COMMA + park.getNumBikes();

		return result;
	}

	@Override
	public String bikePickdown(String idBike) throws NonExistentBikeException {
		String result = "";

		Bike bike = this.getBike(idBike);
		User user = bike.getCurrentUser();
		if (user != null)
			result = user.getMoney() + EUROS + COMMA + user.getPoints() + POINTS;

		return result;
	}

	@Override
	public int getUserMoney(String idUser) throws NonExistentUserException {
		int result = this.getUser(idUser).getMoney();

		return result;
	}

	@Override
	public Iterator<PickUp> bikePickups(String idBike)
			throws NonExistentBikeException, BikeNotUsedException, BikeOnFirstPickupException {

		Bike bike = this.getBike(idBike);
		if (bike.numPickups() == INVALID)
			throw new BikeNotUsedException();
		else if (bike.firstPickup().getFinalPark() == null)
			throw new BikeOnFirstPickupException();
		Iterator<PickUp> it = bike.bikePickups();
		return it;

	}

	@Override
	public Iterator<PickUp> userPickups(String idUser)
			throws NonExistentUserException, UserNotUsedException, UserOnFirstPickupException {

		User user = this.getUser(idUser);
		if (user.numPickups() == INVALID)
			throw new UserNotUsedException();
		if (user.firstPickup().getFinalPark() == null)
			throw new UserOnFirstPickupException();
		Iterator<PickUp> it = user.userPickups();
		return it;

	}

	@Override
	public Iterator<Entry<Integer, List<User>>> delayUsers() throws NoDelaysException {
		if (delayUsers.isEmpty())
			throw new NoDelaysException();

		return delayUsers.iterator();
	}

	@Override
	public Iterator<Entry<String, Park>> getFavoriteParks() throws NoPickupException {

		if (maxPickup == INVALID)
			throw new NoPickupException();

		Park park;
		Dictionary<String, Park> tree = new AVLTree<String, Park>();
		Iterator<Entry<String, ParkSet>> it = parkList.iterator();
		while (it.hasNext()) {
			park = it.next().getValue();
			if (park.numbPickups() == maxPickup) {
				tree.insert(park.getName(), park);

			}
		}

		return tree.iterator();

	}

}
