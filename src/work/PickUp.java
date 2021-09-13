package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public interface PickUp extends java.io.Serializable {
	/**
	 * Consulta o utilizador que fez o pickup
	 * 
	 * @return o utilizador que fez o pickup
	 */
	UserSet getUser();

	/**
	 * Consulta a bicicleta que pertenceu ao pickup
	 * 
	 * @return bicicleta que pertenceu ao pickup
	 */
	Bike getBike();

	/**
	 * Consulta o parque onde foi efectuado o pickup
	 * 
	 * @return parque onde foi efectuado o pickup
	 */
	Park getInitialPark();

	/**
	 * Consulta o parque onde foi efectuado o pickdown
	 * 
	 * @return parque onde foi efectuado o pickdown
	 */
	Park getFinalPark();

	/**
	 * Consulta o tempo (em minutos) entre o pickup e o pickdown
	 * 
	 * @return o tempo entre o pickup e o pickdown
	 */
	int time();

	/**
	 * Consulta o tempo(em minutos) que o utilizador se atrasou a entregar a
	 * bicicleta
	 * 
	 * @return tempo(em minutos) que o utilizador se atrasou a entregar a bicicleta
	 */
	int delay();

	/**
	 * Consulta quanto o utilizador teve de pagar pelo atraso
	 * 
	 * @return quanto o utilizador teve de pagar pelo atraso
	 */
	int cost();

	/**
	 * 
	 * @return id do user que levantou a bicicleta
	 */
	String userId();

	/**
	 * 
	 * @return id da bicicleta
	 */
	String bikeId();

	/**
	 * 
	 * @return id do parque donde foi levantada a bicicleta
	 */
	String initialParkId();

	/**
	 * 
	 * @return id do parque onde foi colocada a bicicleta
	 */
	String finalParkId();


}
