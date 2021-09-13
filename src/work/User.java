package work;

import dataStructures.*;
import work.exceptions.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public interface User extends java.io.Serializable {

	/**
	 * Consulta o id do utilizador
	 * 
	 * @return id do utilizador
	 */
	String getId();

	/**
	 * Consulta o nif do utilizador
	 * 
	 * @return nif do utilizador
	 */
	int getNif();

	/**
	 * Consulta o email do utilizador
	 * 
	 * @return email do utilizador
	 */
	String getEmail();

	/**
	 * Consulta o nome do utilizador
	 * 
	 * @return nome do utilizador
	 */
	String getName();

	/**
	 * Consulta a morada do utilizador
	 * 
	 * @return morada do utilizador
	 */
	String getAddress();

	/**
	 * Consulta a quantidade de dinheiro que o utilizador tem
	 * 
	 * @return quantidade de dinheiro que o utilizador tem
	 */
	int getMoney();

	/**
	 * Consulta o numero de telemovel do utilizador
	 * 
	 * @return numero de telemovel do utilizador
	 */
	int getPhone();

	/**
	 * Consulta os pontos de atraso do utilizador
	 * 
	 * @return pontos de atraso do utilizador
	 */
	int getPoints();

	/**
	 * Verifica se o utilizador tem pickups
	 * 
	 * @return true se o utilizador tiver pickups, false caso contrario
	 */
	boolean hasPickups();

	/**
	 * Verifica se o utilizador esta em movimento
	 * 
	 * @return true se o utilizador tiver em movimento, false caso contrario
	 */
	boolean isMoving();

	/**
	 * Caso o utilizador estiver parado colaca o em movimento e vice versa
	 */
	void buttonUser();

	/**
	 * 
	 * @return uma lista de pickups do utilizador
	 * @throws UserNotUsedException
	 *             a execao e lancada caso o utilizador nao exista no sistema
	 * @throws UserOnFirstPickupException
	 *             a execao e lancada caso o utilizador esteja durante a sua
	 *             primeira pickup
	 */
	Iterator<PickUp> userPickups() throws UserNotUsedException, UserOnFirstPickupException;

	/**
	 * Retorna o primeiro pickup do utilizador
	 * 
	 * @return o primeiro pickup do utilizador
	 */
	PickUp firstPickup();

	/**
	 * Consulta o numero de pickups do utilizador
	 * 
	 * @return o numero de pickups do utilizador
	 */
	int numPickups();

}
