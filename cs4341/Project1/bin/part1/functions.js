/*Author Mike Della Donna*/
function shapeIs(shape)
{
	return wm.shape == shape;
}

function setHeight(height)
{
	wm.height = height;
}

function setPlant(plant)
{
	wm.plant = plant;
}

function ripe()
{
	return wm.ripe;
}

function colorIs(color)
{
	return wm.color == color;
}

function setColor(color)
{
	wm.color = color;
}

function setType(type)
{
	wm.type = type;
}

function typeIs(type)
{
	return wm.type == type;
}

function typeHasValue()
{
	return wm.type != null;
}

function canCarry()
{
	return wm.carry;
}

function isHeavy()
{
	return wm.heavy;
}

function setHeavy(heavy)
{
	wm.heavy = convertYesNoToTrueFalse(heavy);
}

function convertYesNoToTrueFalse(aString)
{
	if(aString == 'no' || aString == 'NO' || aString == 'No')
	{
		return false;
	}
	else
		{
		return true;
		}
}