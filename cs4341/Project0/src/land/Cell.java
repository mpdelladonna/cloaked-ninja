/**
 *  @author Mike Della Donna
 */

package land;


public class Cell {

	public boolean occupied;
	public boolean obstacle;
	public boolean vehicle;
	public boolean valid;
	
	public int elevation;
	public int cost;
	
	public String type;
	
	public Point location;

	/**
	 * 
	 * note, cells cannot contain both a vehicle and an obstacle
	 * 
	 * @param occupied  Whether or not the cell is occupied
	 * @param obstacle  If the cell is occupied by an obstacle
	 * @param vehicle  if the cell is occupied by a vehicle
	 * @param valid  if the cell is valid
	 * @param elevation  the elevation of the cell
	 * @param cost  the cost associated with entering the cell
	 * @param type  the type of obstacle or vehicle occupying the cell
	 */
	public Cell(boolean occupied, boolean obstacle, boolean vehicle,
			boolean valid, int elevation, int cost, String type, Point location) {
		super();
		this.occupied = occupied;
		this.obstacle = obstacle;
		this.vehicle = vehicle;
		this.valid = valid;
		this.elevation = elevation;
		this.cost = cost;
		this.type = type;
		this.location = location;
		
		if(vehicle && obstacle)
		{
			throw new RuntimeException("Cell cannot contain both a vehicle and an obstacle");
		}
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) throws InvalidContentException {
		if(vehicle && obstacle)
		{
			throw new InvalidContentException("this cell already contains a vehicle");
		}
		this.obstacle = obstacle;
	}

	public boolean isVehicle() {
		return vehicle;
	}

	public void setVehicle(boolean vehicle) throws InvalidContentException {
		if(vehicle && obstacle)
		{
			throw new InvalidContentException("this cell already contains an obstacle");
		}
		this.vehicle = vehicle;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Cell [occupied=" + occupied + ", "
				+ (type != null ? "type=" + type + ", " : "")
				+ (location != null ? "location=" + location : "") + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cell)
		{
			if(this.location.equals( ((Cell) obj).location ))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}


	
}
