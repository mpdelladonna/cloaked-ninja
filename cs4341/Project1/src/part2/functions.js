/*Author: Mike Della Donna*/
function hasWater()
{
	return wm.water;
}

function thereIsWater()
{
	wm.water = true;
}

var numCells = null;
function getNumCells()
{
	if(numCells == null)
		{
		numCells = 0;
		/*this is specfic to a java scriptengine execution envrionment
		this allows you to import specific java packages and use them within
		the with(){} context only to avoid polluting the javascript global context*/
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				while(iter.hasNext())
				{
					iter.next();
					numCells++;
				}
		}
		}
	else
		{
		return numCells;
		}
}

var numTrees = null;
function getTreePercentage()
{
	if(numTrees == null)
	{
		numTrees = 0;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'tree')
						{
						numTrees++;
						}	
				}
		}
	}
	else
	{
		return numTrees / getNumCells();
	}
}

function treePercentageGreaterThan(percent)
{
	return getTreePercentage() > (percent / 100);
}

function treePercentageLessThan(percent)
{
	return getTreePercentage() < (percent / 100);
}

var numRocks = null;
function getRockPercentage()
{
	if(numRocks == null)
	{
		numRocks = 0;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'rock')
						{
						numRocks++;
						}	
				}
		}
	}
	else
	{
		return numRocks / getNumCells();
	}
}

function rockPercentageGreaterThan(percent)
{
	return getRockPercentage() > (percent / 100);
}


var numBuildings = null;
function getNumBuildings()
{
	if(numBuildings == null)
	{
		numBuildings = 0;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'building')
						{
						numBuildings++;
						}	
				}
		}
	}
	else
	{
		return numBuildings;
	}
}

function numberOfBuildingsGreaterThan(aNum)
{
	return getNumBuildings() > aNum; 
}

var maximumHeight = null;
function getMaximumHeight()
{
	if(maximumHeight == null)
	{
		maximumHeight = 0;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.getElevation() > maximumHeight)
						{
						maximumHeight = currentCell.getElevation();
						}	
				}
		}
	}
	else
	{
		return maximumHeight;
	}
}

var minimumHeight = null;
function getMinimumHeight()
{
	if(minimumHeight == null)
	{
		minimumHeight = 0;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) {
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.getElevation() < minimumHeight)
						{
						minimumHeight = currentCell.getElevation();
						}	
				}
		}
	}
	else
	{
		return minimumHeight;
	}
}

function maximumHeightGreaterThan(aNum)
{
	return getMaximumHeight() > aNum; 
}

function maximumHeightLessThan(aNum)
{
	return getMaximumHeight() < aNum; 
}

function maximumHeightDifferenceIsLessThan(aNum)
{
	return (getMaximumHeight() - getMinimumHeight()) < aNum;
}


function hasRiver()
{
	if(wm.hasRiver === undefined)
	{
		wm.hasRiver = false;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) 
		{
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'river')
						{
						wm.hasRiver = true;
						}	
				}
		}
	}
	else
		{
		return wm.hasRiver;
		}
}

function hasLake()
{
	if(wm.hasLake === undefined)
	{
		wm.hasLake = false;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) 
		{
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'lake')
						{
						wm.hasLake = true;
						}	
				}
		}
	}
	else
		{
		return wm.hasLake;
		}
}


function hasVehicle()
{
	if(wm.hasVehicle === undefined)
	{
		wm.hasVehicle = false;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) 
		{
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isVehicle())
						{
							wm.hasVehicle = true;
						}	
				}
		}
	}
	else
	{
		return wm.hasVehicle;
	}
}
	
function setEcology(eco)
{
	wm.ecology = eco;
}

function ecologyIs(eco)
{
	return wm.ecology == eco;
}

function dangerousAnimalsArePresent()
{
	return wm.danger;
}

function thereAreDangerousAnimals()
{
	wm.danger = true;
}

function climateIs(climate)
{
	return wm.climate == climate;
}

function setClimate(climate)
{
	wm.climate = climate;
}

function thoseAnimalsArePoisonous()
{
	return wm.poisonousAnimals;
}

function thereArePoisonousAnimals()
{
	wm.poisonousAnimals = true;
}

function foragableFoodIsLessThan(aNum)
{
	return wm.foodFactor < aNum;
}

function setForagableFoodLevelTo(aNum)
{
	wm.foodFactor = aNum;
}

function rainIsExpected()
{
	return wm.rainy;
}

function expectRain()
{
	wm.rainy = true;
}


function landscapeIs(landscape)
{
	return wm.landscape == landscape;
}

function setLandscape(landscape)
{
	wm.landscape = landscape;
}

function addGear(gear)
{
	if(wm.gear != null)
	{
		wm.gear.push(gear);
	}
	else
		{
		wm.gear = new Array();
		wm.gear.push(gear);
		}
}

function hasMudpit()
{
	if(wm.hasMudpit === undefined)
	{
		wm.hasMudpit = false;
		var lists = new JavaImporter(java.util.List,java.util.Iterator);
		with (lists) 
		{
				var cells = land.getCells();
				var iter = cells.iterator();
				var currentCell;
				while(iter.hasNext())
				{
					currentCell = iter.next();
					if(currentCell.isObstacle() && currentCell.getType() == 'mudpit')
						{
						wm.hasMudpit = true;
						}	
				}
		}
	}
	else
		{
		return wm.hasMudpit;
		}
}