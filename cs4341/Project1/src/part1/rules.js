IF (colorIs('green') AND (NOT isHeavy())) THEN setType('grape')
IF (shapeIs('round') AND (colorIs('red') OR colorIs('green')))	THEN setType('apple')
IF	colorIs('red') THEN setType('tomato')
IF	typeIs('tomato') THEN setPlant('yes') setHeight('short')
IF (NOT ripe()) THEN setColor('green')
IF canCarry() THEN setHeavy('no')
IF	typeHasValue() THEN stop()
