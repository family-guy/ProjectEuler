import java.util.*;
/*
 * Problem 62
 */

public class Problem62 {
	public static void main(String[] arg) {
		int n = 2000000;
		long[] cubes = new long[n];
		for (int i = 0; i < cubes.length; i++) {
			long iAsLong = (long)i;
			cubes[i] = iAsLong * iAsLong * iAsLong;
		}
		for (int i = 0; i < cubes.length; i++) {
			String cubeIAsStr = Long.toString(cubes[i]);
			int nbDigitsCubeI = cubeIAsStr.length(); 
			int nbPermsCubeI = 0;
			for (int j = 0; j < cubes.length; j++) {
				String cubeJAsStr = Long.toString(cubes[j]);
				int nbDigitsCubeJ = cubeJAsStr.length();
				if (nbDigitsCubeJ > nbDigitsCubeI) break;
				if (nbDigitsCubeJ == nbDigitsCubeI) {
					int[] freqCubeI = new int[10];
					int[] freqCubeJ = new int[10];
					for (int k = 0; k < nbDigitsCubeI; k++) {
						freqCubeI[Integer.parseInt(cubeIAsStr.substring(k, k + 1))]++;
						freqCubeJ[Integer.parseInt(cubeJAsStr.substring(k, k + 1))]++;
					}
					int l = 0;
					for (l = 0; l < freqCubeI.length; l++) {
						if (freqCubeI[l] != freqCubeJ[l]) break;
					}
					if (l == freqCubeI.length) nbPermsCubeI++;
				}
			}
			if (nbPermsCubeI == 5) {
				System.out.println("The solution is " + cubes[i]);
				break;
			}		
		}
	}
}
