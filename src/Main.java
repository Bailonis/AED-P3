import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import work.*;
import work.exceptions.*;

/**
 * 
 */

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
public class Main {

	private static final String FINAL = "Gravando e terminando...";
	private static final String ADD_USER = "Insercao de utilizador com sucesso.";
	private static final String REMOVE_USER = "Utilizador removido com sucesso.";
	private static final String ADD_PARK = "Parque adicionado com sucesso.";
	private static final String ADD_BIKE = "Bicicleta adicionada com sucesso.";
	private static final String REMOVE_BIKE = "Bicicleta removida com sucesso.";
	private static final String PICKUP = "PickUp com sucesso.";
	private static final String PICKDOWN = "Pickdown com sucesso: ";
	private static final String CHARGE_USER = "Saldo: ";
	private static final String PARKED_BIKE = "Bicicleta estacionada no parque.";
	private static final String BIKE_NOT_PARKED = "Bicicleta nao esta em parque.";
	private static final String NO_DELAYS = "Nao se registaram atrasos.";
	private static final String EUROS = " euros";
	private static final String NO_PICKUPS = "Nao foram efetuados pickups.";
	private static final String FILENAME = "savedata";

	private enum Command {
		ADDUSER("ADDUSER"), REMOVEUSER("REMOVEUSER"), GETUSERINFO("GETUSERINFO"), ADDPARK("INSERTPARK"), ADDBIKE(
				"INSERTBIKE"), REMOVEBIKE("REMOVEBIKE"), GETPARKINFO("GETPARKINFO"), PICKUP("PICKUP"), PICKDOWN(
						"PICKDOWN"), CHARGEUSER("CHAREGUSER"), BIKEPICKUPS("BIKEPICKUPS"), USERPICKUPS(
								"USERPICKUPS"), PARKEDBIKE("PARKEDBIKE"), LISTDELAYED(
										"LISTDELAYED"), FAVORITEPARKS("FAVORITEPARKS"), XS("XS"), UNKNOWN("invalid");

		private String description;

		Command(String description) {
			this.description = description;
		}

		public String toString() {
			return this.description;
		}
	}

	private enum Error {

		INVALIDNUMBER("Dados invalidos."), EXISTENTUSER("Utilizador existente."), EXISTENTPARK(
				"Parque existente."), EXISTENTBIKE("Bicicleta existente."), MOVINGBIKE(
						"Bicicleta em movimento."), MOVINGUSER("Utilizador em movimento."), NOMONEY(
								"Saldo insuficiente."), NONEXISTENTBIKE("Bicicleta inexistente."), NONEXISTENTUSER(
										"Utilizador inexistente."), NONEXISTENTPARK(
												"Parque inexistente."), BIKEHASPICKUPS(
														"Bicicleta ja foi utilizada."), BIKENOTPARKED(
																"Bicicleta nao esta em parque."), BIKENOTUSED(
																		"Bicicleta nao foi utilizada"), BIKEONFIRSTPICKUP(
																				"Bicicleta em movimento em primeiro pickup."), NONMOVINGBIKE(
																						"Bicicleta parada."), USERHASPICKUPS(
																								"Utilizador ja utilizou o sistema."), USERNOTUSED(
																										"Utilizador nao utilizou o sistema."), USERONFIRSTPICKUP(
																												"Utilizador em primeiro PickUp.");

		private String message;

		Error(String message) {
			this.message = message;
		}

		public String toString() {
			return this.message;
		}
	}

	public static void main(String[] args) {
		BikePark bikepark = load();
		Scanner in = new Scanner(System.in);
		Command comm = getCommand(in);
		while (!comm.equals(Command.XS)) {
			command(comm, bikepark, in);
			comm = getCommand(in);
		}
		save(bikepark);
		System.out.println(FINAL);
		System.out.println();
		in.close();
	}

	private static BikePark load() {
		ObjectInputStream in;
		BikePark bikepark = null;
		try {
			in = new ObjectInputStream(new FileInputStream(FILENAME));
			bikepark = (BikePark) in.readObject();
			in.close();

		} catch (IOException i) {
			return new BikeParkClass();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();

		}
		return bikepark;
	}

	private static void save(BikePark bikepark) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(FILENAME));
			out.writeObject(bikepark);
			out.flush();
			out.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}

	private static void command(Command comm, BikePark bikepark, Scanner in) {
		switch (comm) {
		case ADDUSER:
			addUser(bikepark, in);
			System.out.println();
			break;
		case REMOVEUSER:
			removeUser(bikepark, in);
			System.out.println();
			break;
		case GETUSERINFO:
			userInfo(bikepark, in);
			System.out.println();
			break;
		case ADDPARK:
			insertPark(bikepark, in);
			System.out.println();
			break;
		case ADDBIKE:
			insertBike(bikepark, in);
			System.out.println();
			break;
		case REMOVEBIKE:
			removeBike(bikepark, in);
			System.out.println();
			break;
		case GETPARKINFO:
			parkInfo(bikepark, in);
			System.out.println();
			break;
		case PICKUP:
			pickup(bikepark, in);
			System.out.println();
			break;
		case PICKDOWN:
			pickdown(bikepark, in);
			System.out.println();
			break;
		case CHARGEUSER:
			chargeUser(bikepark, in);
			System.out.println();
			break;
		case BIKEPICKUPS:
			bikePickups(bikepark, in);
			System.out.println();
			break;
		case USERPICKUPS:
			userPickups(bikepark, in);
			System.out.println();
			break;
		case PARKEDBIKE:
			parkedBike(bikepark, in);
			System.out.println();
			break;
		case LISTDELAYED:
			listDelayed(bikepark);
			System.out.println();
			break;
		case FAVORITEPARKS:
			favouriteParks(bikepark);
			System.out.println();
			break;
		default:
			System.out.println(Command.UNKNOWN);
			break;

		}
	}

	private static void addUser(BikePark bikepark, Scanner in) {
		String id, mail, name, address;
		int nif, phone;
		id = in.next().toLowerCase();
		nif = in.nextInt();
		mail = in.next();
		phone = in.nextInt();
		name = in.nextLine().trim();
		address = in.nextLine().trim();
		try {
			bikepark.addUser(id, nif, mail, phone, name, address);
			System.out.println(ADD_USER);
		} catch (ExistentUserException e) {
			System.out.println(Error.EXISTENTUSER);
		}
	}

	private static void removeUser(BikePark bikepark, Scanner in) {
		String id;
		id = in.next().toLowerCase();
		try {
			bikepark.removeUser(id);
			System.out.println(REMOVE_USER);
		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		} catch (UserHasPickupsException e) {
			System.out.println(Error.USERHASPICKUPS);
		}
	}

	private static void userInfo(BikePark bikepark, Scanner in) {
		String id;
		id = in.next().toLowerCase();
		try {

			System.out.println(bikepark.userInfo(id));
		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		}
	}

	private static void insertPark(BikePark bikepark, Scanner in) {
		String id, name, address;
		id = in.next().toLowerCase();
		name = in.nextLine().trim();
		address = in.nextLine().trim();
		try {
			bikepark.addPark(id, name, address);
			System.out.println(ADD_PARK);
		} catch (ExistentParkException e) {
			System.out.println(Error.EXISTENTPARK);
		}
	}

	private static void insertBike(BikePark bikepark, Scanner in) {
		String id, idPark, license;
		id = in.next().toLowerCase();
		idPark = in.next().toLowerCase();
		license = in.next();
		in.nextLine();
		try {
			bikepark.addBike(id, idPark, license);
			System.out.println(ADD_BIKE);
		} catch (ExistentBikeException e) {
			System.out.println(Error.EXISTENTBIKE);
		} catch (NonExistentParkException e) {
			System.out.println(Error.NONEXISTENTPARK);
		}
	}

	private static void removeBike(BikePark bikepark, Scanner in) {
		String id;
		id = in.next().toLowerCase();
		in.nextLine();
		try {
			bikepark.removeBike(id);
			System.out.println(REMOVE_BIKE);
		} catch (NonExistentBikeException e) {
			System.out.println(Error.NONEXISTENTBIKE);
		} catch (BikeHasPickupsException e) {
			System.out.println(Error.BIKEHASPICKUPS);
		}
	}

	private static void parkInfo(BikePark bikepark, Scanner in) {
		String id;
		id = in.next().toLowerCase();
		in.nextLine();
		try {

			System.out.println(bikepark.parkInfo(id));
		} catch (NonExistentParkException e) {
			System.out.println(Error.NONEXISTENTPARK);
		}
	}

	private static void pickup(BikePark bikepark, Scanner in) {
		String idBike, idUser;
		idBike = in.next().toLowerCase();
		idUser = in.next().toLowerCase();
		in.nextLine();
		try {
			bikepark.pickup(idBike, idUser);
			System.out.println(PICKUP);
		} catch (NonExistentBikeException e) {
			System.out.println(Error.NONEXISTENTBIKE);
		} catch (MovingBikeException e) {
			System.out.println(Error.MOVINGBIKE);
		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		} catch (MovingUserException e) {
			System.out.println(Error.MOVINGUSER);
		} catch (NoMoneyException e) {
			System.out.println(Error.NOMONEY);
		}
	}

	private static void pickdown(BikePark bikepark, Scanner in) {
		String idBike, idPark;
		int min;
		idBike = in.next().toLowerCase();
		idPark = in.next().toLowerCase();
		min = in.nextInt();
		in.nextLine();
		try {
			if (min > 60)
				bikepark.ticket(idBike, min);
			String result = bikepark.bikePickdown(idBike);
			bikepark.pickdown(idBike, idPark, min);
			System.out.println(PICKDOWN + result);
		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		} catch (NonExistentBikeException e) {
			System.out.println(Error.NONEXISTENTBIKE);
		} catch (NonMovingBikeException e) {
			System.out.println(Error.NONMOVINGBIKE);
		} catch (NonExistentParkException e) {
			System.out.println(Error.NONEXISTENTPARK);
		} catch (InvalidNumberException e) {
			System.out.println(Error.INVALIDNUMBER);
		}
	}

	private static void chargeUser(BikePark bikepark, Scanner in) {
		String idUser;
		int value;
		idUser = in.next().toLowerCase();
		value = in.nextInt();
		in.nextLine();
		try {
			bikepark.chargeUser(idUser, value);
			System.out.println(CHARGE_USER + bikepark.getUserMoney(idUser) + EUROS);
		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		} catch (InvalidNumberException e) {
			System.out.println(Error.INVALIDNUMBER);
		}
	}

	private static void bikePickups(BikePark bikepark, Scanner in) {
		String idBike;
		idBike = in.next().toLowerCase();
		in.nextLine();
		try {

			Iterator<PickUp> it = bikepark.bikePickups(idBike);
			while (it.hasNext()) {
				PickUp pickup = it.next();
				if (pickup.getFinalPark() != null) {
					String result = pickup.userId() + " " + pickup.initialParkId() + " " + pickup.finalParkId() + " "
							+ pickup.time() + " " + pickup.delay() + " " + pickup.cost();
					System.out.println(result);
				}
			}

		} catch (NonExistentBikeException e) {
			System.out.println(Error.NONEXISTENTBIKE);
		} catch (BikeNotUsedException e) {
			System.out.println(Error.BIKENOTUSED);
		} catch (BikeOnFirstPickupException e) {
			System.out.println(Error.BIKEONFIRSTPICKUP);
		}
	}

	private static void userPickups(BikePark bikepark, Scanner in) {
		String idUser;
		idUser = in.next().toLowerCase();
		in.nextLine();

		try {
			Iterator<PickUp> it = bikepark.userPickups(idUser);
			while (it.hasNext()) {
				PickUp pickup = it.next();
				if (pickup.getFinalPark() != null) {
					String result = pickup.bikeId() + " " + pickup.initialParkId() + " " + pickup.finalParkId() + " "
							+ pickup.time() + " " + pickup.delay() + " " + pickup.cost();
					System.out.println(result);
				}
			}

		} catch (NonExistentUserException e) {
			System.out.println(Error.NONEXISTENTUSER);
		} catch (UserNotUsedException e) {
			System.out.println(Error.USERNOTUSED);
		} catch (UserOnFirstPickupException e) {
			System.out.println(Error.USERONFIRSTPICKUP);
		}
	}

	private static void parkedBike(BikePark bikepark, Scanner in) {
		String idBike, idPark;
		idBike = in.next().toLowerCase();
		idPark = in.next().toLowerCase();
		in.nextLine();
		try {
			if (bikepark.parkedBike(idBike, idPark))
				System.out.println(PARKED_BIKE);
			else
				System.out.println(BIKE_NOT_PARKED);
		} catch (NonExistentBikeException e) {
			System.out.println(Error.NONEXISTENTBIKE);
		} catch (NonExistentParkException e) {
			System.out.println(Error.NONEXISTENTPARK);
		}
	}

	private static void listDelayed(BikePark bikepark) {
		try {
			Iterator<Entry<Integer, List<User>>> it = bikepark.delayUsers();

			while (it.hasNext()) {
				Iterator<User> value = it.next().getValue().iterator();
				while (value.hasNext()) {
					User user = value.next();
					String result = user.getName() + ": " + user.getNif() + ", " + user.getAddress() + ", "
							+ user.getEmail() + ", " + user.getPhone() + ", " + user.getMoney() + ", "
							+ user.getPoints();
					System.out.println(result);
				}

			}

		} catch (NoDelaysException e) {
			System.out.println(NO_DELAYS);
		}
	}

	private static void favouriteParks(BikePark bikepark) {

		try {
			Iterator<Entry<String, Park>> it = bikepark.getFavoriteParks();

			while (it.hasNext()) {
				Park park = it.next().getValue();
				String result = park.getName() + ": " + park.getAddress() + ", " + park.numbPickups();
				System.out.println(result);
			}

		} catch (NoPickupException e) {
			System.out.println(NO_PICKUPS);
			;
		}

	}

}