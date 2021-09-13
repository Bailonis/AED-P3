package work;

import dataStructures.Iterator;
import work.exceptions.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
interface Bike extends java.io.Serializable {

	/**
	 * Consulta o id da bicicleta
	 * 
	 * @return id da bicicleta
	 */
	String getId();

	/**
	 * Consulta a matricula da bicicleta
	 * 
	 * @return matricula da bicicleta
	 */
	String license();

	/**
	 * Consulta o parque onde a bicicleta esta antes do pickup
	 * 
	 * @return
	 */
	ParkSet currentPark();

	/**
	 * Consulta se a bicicleta tem pickups
	 * 
	 * @return
	 */
	boolean hasPickups();

	/**
	 * 
	 * Lista os pickups da bicicleta
	 * 
	 * @return iterador que percorre a lista de pickups
	 * @throws BikeNotUsedException
	 *             quando a bicicleta nao tem pickups
	 * @throws BikeOnFirstPickupException
	 *             quando a bicicleta nao terminou o primeiro pickup
	 */
	Iterator<PickUp> bikePickups() throws BikeNotUsedException, BikeOnFirstPickupException;

	/**
	 * Consulta o utilizador da bicicleta durante o pickup
	 * 
	 * @return utilizador da bicicleta durante o pickup
	 */
	User getCurrentUser();

	/**
	 * Consulta se a bicicleta esta em movimento
	 * 
	 * @return true se a bicicleta esta em movimento, false caso contrario
	 */
	boolean isMoving();

	/**
	 * Se a bicicleta esta em movimento faz com que fique parada e vice versa
	 */
	void buttonBike();

	/**
	 * Muda o parque atual da bicicleta
	 * 
	 * @param parkName
	 *            != null
	 */
	void changeCurrentPark(ParkSet park);

	/**
	 * Muda o utilizador atual da bicicleta
	 * 
	 * @param userId
	 *            != null
	 */
	void changeCurrentUser(User user);

	/**
	 * Adiciona um pickup a bicicleta
	 * 
	 * @param pickUp
	 *            != null
	 */
	void addPickUp(PickUp pickUp);

	/**
	 * Retorna o pickup que ainda nao esta terminado
	 * 
	 * @return pickup que ainda nao esta terminado
	 */
	PickUpSet lastPickup();

	/**
	 * Retorna o primeiro pickup da bicicleta
	 * 
	 * @return o primeiro pickup da bicicleta
	 */
	PickUp firstPickup();

	/**
	 * Consulta o numero de pickups da bicicleta
	 * 
	 * @return o numero de pickups da bicicleta
	 */
	int numPickups();

}
