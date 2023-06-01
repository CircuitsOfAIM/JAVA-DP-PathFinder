package assignment6;

import java.util.Arrays;

public class DiceSolver {

	public static long nrCalls = 0;

	/**
	 * This is a helper function for geWinChanceDynamic.
	 * It generates a table for dynamic programming to store each calculated value and access it instead of recalculating, to save time complexity as a trade of for space complexity.
	 * @param maximumValue The value that counts for the end of the game as integer. If the currentValue or friendsValue exceeds this value. The game is over.
	 * @param sides It represents the sides of the dice and is needed to calculate the chance of winning.
	 * @return It returns the probability of the player winning, represented as a double.
	 */
	public double getWinChanceDynamic(int maximumValue, int sides){
		double[][] table = new double[maximumValue+1][maximumValue+1];
		for(int row = 0; row < table.length; row++){
			for(int j = 0; j < table[row].length; j++){
				table[row][j] = -1;
			}
		}
		return getWinChanceDynamic(0, 0, maximumValue, sides, table);
	}

	/**
	 * This function is a dynamic programming style version of calculating the probability of winning by the player in a game of chance.
	 * The rules are that the starting player throws a dice once, and then the turn switches. Now the other player throws as long as its value is lower than the other players value. Then the player throws again until he exceeds the other players value. This continues.
	 * The game ends if someone exceeds the maximumValue.
	 * @param currentValue A variable to save the amount of points of the player.
	 * @param friendValue	A variable to save the amount of points for the other player.
	 * @param maximumValue This value represents the end of the game, if one player exceeds that value.
	 * @param sides It represents the sides of the dice and is needed to calculate the chance of winning.
	 * @param table A 2D Table to story the results of the recursive call to access them instead of recalculating, already done calculations.
	 * @return It returns the probability of the player winning, represented as a double.
	 */
	public double getWinChanceDynamic(int currentValue, int friendValue, int maximumValue, int sides, double[][] table){
		///////////////////////////////////////
		// WRITE YOUR CODE FOR PART 6.2 HERE //
		///////////////////////////////////////
		// Whenever we are going to this function
		// we increment the number of calls by 1
		nrCalls++;

		// The first checks is to see whether
		// we lost the game
		if (currentValue > maximumValue)
			return 0.0;
			// The second check is to see
			// whether our friend lost
		else if (friendValue > maximumValue)
			return 1.0;
		// If there is value saved at this point we reuse it, rather than doing every underlying calculation again.
		else if(table[currentValue][friendValue] >= 0 && table[currentValue][friendValue] <= 1){
			return table[currentValue][friendValue];
		}
		// If neither player lost, we will
		// recursively calculate the winChance
		double winChance = 0.0;

		// If neither player lost, but our value
		// is lower than our friend's
		if (currentValue <= friendValue){
			// Check each possible result separately
			for (int result = 1; result <= sides; result++)
				// Add the win chance given that result, but
				// multiply it by the chance of getting that outcome
				winChance += (1.0/sides) * getWinChanceDynamic(currentValue + result, friendValue, maximumValue, sides,table);

		}
		// Alternatively, if our friend's value
		// is lower than ours, but we did not lose
		else {
			// Check each possible result separately
			for (int result = 1; result <= sides; result++)
				// Add the win chance given that result, but
				// multiply it by the chance of getting that outcome
				winChance += (1.0/sides) * getWinChanceDynamic(currentValue, friendValue + result, maximumValue, sides,table);
		}
		return table[currentValue][friendValue] = winChance;
	}

	//I assume as this function was giving documenting it, it is unnecessary.
	public double getWinChance (int currentValue, int friendValue, int maximumValue, int sides) {
		// Whenever we are going to this function
		// we increment the number of calls by 1
		nrCalls++;

		// The first checks is to see whether
		// we lost the game
		if (currentValue > maximumValue)
			return 0.0;

		// The second check is to see
		// whether our friend lost
		else if (friendValue > maximumValue)
			return 1.0;

		// If neither player lost, we will
		// recursively calculate the winChance
		double winChance = 0.0;

		// If neither player lost, but our value
		// is lower than our friend's
		if (currentValue <= friendValue){
			// Check each possible result separately
			for (int result = 1; result <= sides; result++)
				// Add the win chance given that result, but
				// multiply it by the chance of getting that outcome
				winChance += (1.0/sides) * getWinChance(currentValue + result, friendValue, maximumValue, sides);
		}

		// Alternatively, if our friend's value
		// is lower than ours, but we did not lose
		else {
			// Check each possible result separately
			for (int result = 1; result <= sides; result++)
				// Add the win chance given that result, but
				// multiply it by the chance of getting that outcome
				winChance += (1.0/sides) * getWinChance(currentValue, friendValue + result, maximumValue, sides);
		}
		return winChance;
	}
}
