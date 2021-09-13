package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
interface PickUpSet extends PickUp {
	/**
	 * devolve a bicicleta e termina o pickup
	 * 
	 * @param finalPark
	 *            parque onde a bicicleta e colocada
	 * @param time
	 *            tempo total do pickup
	 */
	void pickDown(Park finalPark, int time);

	/**
	 * So e usado se min > 60 
	 * Muda o tempo do pickup
	 * 
	 * @param min
	 *            tempo total do pickup
	 */
	void ticket(int min);
	
	

}
