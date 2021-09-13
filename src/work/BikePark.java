package work;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import work.exceptions.*;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public interface BikePark extends java.io.Serializable {

	/**
	 * Adiciona um utilizador a lista de utilizadores
	 * 
	 * @param id
	 *            identificador do utilizador, != null
	 * @param nif
	 *            nif do utilizador, != null
	 * @param mail
	 *            mail do utilizador != null
	 * @param phone
	 *            numero de telemovel do utilizador, >0
	 * @param name
	 *            nome do utilizador, != null
	 * @param adress
	 *            morada do utilizador != null
	 * @throws ExistentUserException
	 *             a execao e lancada caso o utilizador ja exista
	 */
	void addUser(String id, int nif, String mail, int phone, String name, String adress) throws ExistentUserException;

	/**
	 * remove um utilizador da lista de utilizadores
	 * 
	 * @param id
	 *            identificador do utilizador, != null
	 * @throws NonExistentUserException
	 *             a execao e lancada caso nao exista utilizadaor
	 * @throws UserHasPickupsException
	 *             a execao e lancada caso o utilizador tenha efectuado pelo menos
	 *             uma pickup
	 */
	void removeUser(String id) throws NonExistentUserException, UserHasPickupsException;

	/**
	 * Adiciona um parque a lista de parques
	 * 
	 * @param id
	 *            identificador do parque a adicionar, != null
	 * @param name
	 *            nome do parque a adicionar, != null
	 * @param address
	 *            morada do parque a adicionar, != null
	 * @throws ExistentParkException
	 *             a execao e lancada caso ja exista este parque
	 */
	void addPark(String id, String name, String address) throws ExistentParkException;

	/**
	 * Adiciona uma bicicleta a um parque e a lista de bicicletas
	 * 
	 * @param id
	 *            identificador da bicicleta a adicionar, != null
	 * @param idPark
	 *            identificador do parque onde se vai adicionar a bicicleta, != null
	 * @param license
	 *            matricula da bicicleta a adicionar, != null
	 * @throws ExistentBikeException
	 *             a execao e lancada caso a bicicleta ja exista
	 * @throws NonExistentParkException
	 *             a execao e lancada caso nao exista o dado parque
	 */
	void addBike(String id, String idPark, String license) throws ExistentBikeException, NonExistentParkException;

	/**
	 * Remove uma bicicleta da lista de bicicletas e do parque onde esta
	 * estacionada, apenas funciona se a bicicleta nao tiver pickups
	 * 
	 * @param id
	 *            identificador da bicicleta a remover != null
	 * @throws NonExistentBikeException
	 *             a execao e lancada caso a bicicleta nao exista
	 * @throws BikeHasPickupsException
	 *             a execao e lancada caso a bicicleta tenha pelo menos uma pickup
	 */
	void removeBike(String id) throws NonExistentBikeException, BikeHasPickupsException;

	/**
	 * Permite ao utilizador fazer um pickup
	 * 
	 * @param idBike
	 *            identificador da bicicleta que o utilizador vai fazer pickup, !=
	 *            null
	 * @param idUser
	 *            identificador do utilizador que vai fazer pickup, != null
	 * @throws NonExistentBikeException
	 *             a execao e lancada caso a bicicleta nao exista
	 * @throws MovingBikeException
	 *             a execao e lancada caso a bicicleta esteja em movimento
	 * @throws NonExistentUserException
	 *             a execao e lancada caso o utilizador nao exista
	 * @throws MovingUserException
	 *             a execao e lancada caso o utilizador esteja em movimento
	 * @throws NoMoneyException
	 *             a execao e lancada caso o utilizador nao tenha dinheiro
	 *             suficiente
	 */
	void pickup(String idBike, String idUser) throws NonExistentBikeException, MovingBikeException,
			NonExistentUserException, MovingUserException, NoMoneyException;

	/**
	 * Permite ao utilizador fazer um pickdown
	 * 
	 * @param idBike
	 *            identificador da bicicleta que o utilizador vai fazer pickdown, !=
	 *            null
	 * @param idPark
	 *            identificador do parque onde o utilizador vai fazer pickdown, !=
	 *            null
	 * @param min
	 *            tempo que o utilizador utilizou a bicicleta, >= 0
	 * @throws NonExistentBikeException
	 *             a execao e lancada caso a bicicleta nao exista no sistema
	 * @throws NonExistentUserException
	 *             a execao e lancada caso o user nao exista no sistema
	 * @throws NonMovingBikeException
	 *             a execao e lancada caso a bicicleta tiver parada
	 * @throws NonExistentParkException
	 *             a execao e lancada caso o parque nao exista no sistema
	 * @throws InvalidNumberException
	 *             a execao e lancada caso o tempo que o utilizador usou a bicicleta
	 *             for < 0
	 */
	void pickdown(String idBike, String idPark, int min) throws NonExistentBikeException, NonExistentUserException,
			NonMovingBikeException, NonExistentParkException, InvalidNumberException;

	/**
	 * cobra dinheiro ao utilizador que se atrasou
	 * 
	 * @param idUser
	 *            identificador do utilizador que se atrasou, != null
	 * @param value
	 *            valor do custo da multa, >= 0
	 * @throws NonExistentUserException
	 *             a execao e lancada caso o utilizador nao exista no sistema
	 * @throws InvalidNumberException
	 *             a execao e lancada caso o valor do custo da multa for < 0
	 */
	void chargeUser(String idUser, int value) throws NonExistentUserException, InvalidNumberException;

	/**
	 * Verifica se uma bicicleta esta estacionada num certo parque atraves dos seus
	 * identificadores
	 * 
	 * @param idBike
	 *            identificador da bicicleta, != null
	 * @param idPark
	 *            identificador do parque, != null
	 * @return true se a bicicleta estiver estacionada no parque, false caso
	 *         contrario
	 * @throws NonExistentBikeException
	 *             a execao e lancada caso a bicicleta nao exista no sistema
	 * @throws NonExistentParkException
	 *             a execao e lancada caso o parque nao exista no sistema
	 */
	boolean parkedBike(String idBike, String idPark) throws NonExistentBikeException, NonExistentParkException;

	/**
	 * devolve a mensagem a ser imprimida quando e chamado o comando USERINFO
	 * 
	 * @param id
	 *            id do user
	 * @return mensagem a ser imprimida
	 * @throws NonExistentUserException
	 *             caso nao exista nenhum user com o id dado
	 */
	String userInfo(String id) throws NonExistentUserException;

	/**
	 * devolve a mensagem a ser imprimida quando e chamado o comando PARKINFO
	 * 
	 * @param id
	 *            id do parque
	 * @return mensagem a ser imprimida
	 * @throws NonExistentParkException
	 *             caso nao exista nenhum parque com o id dado
	 */
	String parkInfo(String id) throws NonExistentParkException;

	/**
	 * devolve a mensagem a ser imprimida quando e chamado o comando PICKDOWN
	 * 
	 * @param idBike
	 *            id da bicicleta
	 * @return mensagem a ser imprimida
	 * @throws NonExistentBikeException
	 *             caso nao exista nenhuma bicicleta com o id dado
	 * 
	 */
	String bikePickdown(String idBike) throws NonExistentBikeException;

	/**
	 * devolve o saldo atual dum user
	 * 
	 * @param idUser
	 *            id do user
	 * @return saldo do user
	 * @throws NonExistentUserException
	 *             caso nao exista nenhum user como o id dado
	 */
	int getUserMoney(String idUser) throws NonExistentUserException;

	/**
	 * Retorna o iteardor que percorre a lista de pickups duma bicicleta
	 * 
	 * @param idBike
	 *            id da bicicleta
	 * @return iterador de pickups
	 * @throws NonExistentBikeException
	 *             caso nao exista uma bicicleta com o id dado
	 * @throws BikeNotUsedException
	 *             caso a bicicleta nao tenha nenhum pickup
	 * @throws BikeOnFirstPickupException
	 *             caso ainda esteja a decorrer o primeiro pickup da bicicleta
	 * 
	 */
	Iterator<PickUp> bikePickups(String idBike)
			throws NonExistentBikeException, BikeNotUsedException, BikeOnFirstPickupException;

	/**
	 * Retorna o iteardor que percorre a lista de pickups dum user
	 * 
	 * @param idUser
	 *            id do User
	 * @return iterador de pickups
	 * @throws NonExistentUserException
	 *             caso nao exista nenhum user com o id dado
	 * @throws UserNotUsedException
	 *             caso o user nao tenha nunca iniciado um pickup
	 * @throws UserOnFirstPickupException
	 *             caso esteja a decorrer o primeiro pickup do user
	 */
	Iterator<PickUp> userPickups(String idUser)
			throws NonExistentUserException, UserNotUsedException, UserOnFirstPickupException;

	/**
	 * Multa o utilizador
	 * 
	 * @param idBike
	 *            id da bicicleta que o user vai fazer pickdown
	 * @param min
	 *            tempo em minutos da duracao do pickup
	 * @throws NonExistentBikeException
	 *             caso nao exista nenhuma bicicleta com o id dado
	 * @throws NonMovingBikeException
	 *             caso a bicicleta nao estaja em movimento
	 * 
	 */
	void ticket(String idBike, int min) throws NonExistentBikeException, NonMovingBikeException;

	/**
	 * 
	 * Retorna o iterador com os utilizadores ordenados por pontos, em caso de
	 * empate por tempo no sistema
	 * 
	 * @return iterador de utilizadores por pontos
	 * @throws UserNotUsedException
	 *             caso todos os utilizadores tenham 0 pontos
	 */
	Iterator<Entry<Integer, List<User>>> delayUsers() throws NoDelaysException;

	/**
	 * Retorna o iterador dos parkes com mais pickup ordenados por numero de
	 * pickups, em caso de empate por ordem alfabetica
	 * 
	 * @return iterador dos parques com mais pickups
	 * @throws NoPickupException
	 *             caso o numero de pickups do(s) parque(s) seja 0
	 */
	Iterator<Entry<String, Park>> getFavoriteParks() throws NoPickupException;
}
