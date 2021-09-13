package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
class ParkClass implements Park, ParkSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String adress;
	private int pickups;
	private int nBike;

	public ParkClass(String id, String name, String adress) {

		this.id = id;
		this.name = name;
		this.adress = adress;
		pickups = 0;
		nBike = 0;

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return adress;
	}

	@Override
	public int numbPickups() {
		return pickups;
	}

	@Override
	public void addPickups() {
		pickups++;
	}

	@Override
	public int getNumBikes() {
		return nBike;
	}

	@Override
	public void addBike() {
		nBike++;

	}

	@Override
	public void removeBike() {
		nBike--;

	}

}
