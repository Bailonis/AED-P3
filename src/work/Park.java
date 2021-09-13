package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public interface Park extends java.io.Serializable {
	/**
	 * Consulta o id do parque
	 * 
	 * @return id do parque
	 */
	String getId();

	/**
	 * consulta o nome do parque
	 * 
	 * @return nome do parque
	 */
	String getName();

	/**
	 * Consulta a morada do parque
	 * 
	 * @return morada do parque
	 */
	String getAddress();

	/**
	 * Consulta o numero de pickups do parque
	 * 
	 * @return numero de pickups do parque
	 */
	int numbPickups();

	/**
	 * Consulta o numero de bicicletas que o parque tem
	 * 
	 * @return numero de bicicletas do parque
	 */
	int getNumBikes();

}
