/**
 * Problem 62
 */

var n = 2000000;
var cubes = [];
for (var i = 0; i < n; i++) {
	cubes.push(i * i * i);
}
for (var i = 0; i < cubes.length; i++) {
	var cubeIAsStr = cubes[i].toString();
	var nbDigitsCubeI = cubeIAsStr.length; 
	var nbPermsCubeI = 0;
	for (var j = 0; j < cubes.length; j++) {
		var cubeJAsStr = cubes[j].toString();
		var nbDigitsCubeJ = cubeJAsStr.length;
		if (nbDigitsCubeJ > nbDigitsCubeI) break;
		if (nbDigitsCubeJ == nbDigitsCubeI) {
			var freqCubeI = [];
			var freqCubeJ = [];
			for (var a = 0; a < 10; a++) {
				freqCubeI.push(0); freqCubeJ.push(0);
			}
			for (var k = 0; k < nbDigitsCubeI; k++) {
				freqCubeI[parseInt(cubeIAsStr.substring(k, k + 1))]++;
				freqCubeJ[parseInt(cubeJAsStr.substring(k, k + 1))]++;
			}
			for (var l = 0; l < freqCubeI.length; l++) {
				if (freqCubeI[l] != freqCubeJ[l]) break;
				if (l == freqCubeI.length - 1) nbPermsCubeI++;
			}
		}
	}
	if (nbPermsCubeI == 5) {
		console.log('The solution is ' + cubes[i]);
		break;
	}		
}