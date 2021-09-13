package work;

/**
 * @author Antonio Santos 51879 ajl.santos@campus.fct.unl.pt
 * @author Pedro Bailao 53675 p.bailao@campus.fct.unl.pt
 *
 */
class PickUpClass implements PickUp, PickUpSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserSet user;
	private Bike bike;
	private Park initialPark;
	private Park finalPark;
	private int time;

	public PickUpClass(UserSet user, Bike bike, Park inicialParkId) {
		this.user = user;
		this.bike = bike;
		this.initialPark = inicialParkId;
		this.finalPark = null;
		this.time = 0;
	}

	@Override
	public void pickDown(Park finalPark, int time) {
		this.finalPark = finalPark;
		this.time = time;

	}

	@Override
	public void ticket(int min) {
		this.time = min;
	}

	@Override
	public UserSet getUser() {
		return user;
	}

	@Override
	public Bike getBike() {
		return bike;
	}

	@Override
	public Park getInitialPark() {
		return initialPark;
	}

	@Override
	public Park getFinalPark() {
		return finalPark;
	}

	@Override
	public int time() {
		return time;
	}

	@Override
	public int delay() {
		if (time <= 60)
			return 0;
		else
			return (time - 60);
	}

	@Override
	public int cost() {

		int y = this.delay();
		int x = y / 30 + (y % 30 != 0 ? 1 : 0);
		return x;
	}

	@Override
	public String userId() {

		return getUser().getId();
	}

	@Override
	public String bikeId() {

		return getBike().getId();
	}

	@Override
	public String initialParkId() {

		return getInitialPark().getId();
	}

	@Override
	public String finalParkId() {

		return getFinalPark().getId();
	}
	

}
