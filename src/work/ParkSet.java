package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
interface ParkSet extends Park {
	/**
	 * Adiciona uma bicicleta a colecao de bicicletas do parque
	 * 
	 * @param bike
	 *            != null
	 * 
	 */
	void addBike();

	
	

	/**
	 * Remove uma bicicleta da colecao de bicicletas do parque
	 * 
	 * @param bike
	 *            != null
	 */
	void removeBike();

	/**
	 * Incrementa o numero de pickups do parque
	 */
	void addPickups();
}
