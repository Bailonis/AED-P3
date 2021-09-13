package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
interface UserSet extends User {
	/**
	 * Carrega o saldo do utilizador
	 * 
	 * @param value
	 *            > 0
	 */
	void chargeUser(int value);

	/**
	 * Adiciona uma pickups ao perfil do utilizador
	 * 
	 * @param pickUp
	 *            != null
	 */
	void addPickUp(PickUp pickUp);

	/**
	 * Multa um utilizador, aumentado tambem os pontos
	 * 
	 * @param value
	 *            valor da multa
	 */
	void ticket(int value);
}
