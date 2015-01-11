<?php
/**
 * Combinatorics methods for strings and integers
 */

class Combinatorics // no public/private/protected keyword required
{
	/**
	* Inserts a character into a string
	* @param string $str, string $ch (of length 1), integer $i
	* @return string
	*/
	private static function insert_char_at($str, $ch, $i)
	{
		if ($i === 0) return $ch . $str;
		if ($i === strlen($str)) return $str. $ch;
		else return substr($str, 0, $i) . $ch . substr($str, $i);
	}
	
	/**
	* Removes a character from a string
	* @param string $str, integer $i
	* @return string
	*/
	private static function remove_char_at($str, $i)
	{
		return substr($str, 0, $i) . substr($str, $i + 1);
	}
	
	/**
	* Gets new strings formed by adding one character to an existing string
	* @param string $str, string $ch (of length 1)
	* @return array of strings
	*/
	private static function all_new_strings1($str, $ch) // PHP does not support overloaded functions
	{
		$result = array();
		for ($i = 0; $i < strlen($str) + 1; $i++)
		{
			$result[] = self::insert_char_at($str, $ch, $i);
		}
		return $result;
	}
	
	/**
	* Gets new strings formed by adding one character to an existing array of strings
	* @param array of strings $strings, string $ch (of length 1)
	* @return array of strings
	*/
	private static function all_new_strings2($strings, $ch)
	{
		$p = count($strings);
		$q = strlen($strings[0]) + 1;
		$result = array();
		$j = -1;
		for ($i = 0; $i < $p * $q; $i++)
		{
			if ($i % $q === 0) $j++;
			$result[] = self::all_new_strings1($strings[$j], $ch)[$i % $q]; 
		}
		return $result;
	}
	
	/**
	* Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations, returns the next k-combination 	e.g. next({1, 4, 5}, 5) returns {2, 3, 4}
	* @param array of integers $T, integer $n
	* @return array of integers
	*/
	public static function next($T, $n)
	{
		$result = array();
		for ($i = 0; $i < count($T); $i++)
		{
			$result[] = $T[$i];
		}
		$i = 0;
		while (count($T) - 1 - $i > -1)
		{
			if ($T[count($T) - 1 - $i] < $n - $i)
			{
				$result[count($result) - 1 - $i]++;
				for ($j = count($result) - $i; $j < count($T); $j++)
				{
					$result[$j] = $result[count($result) - 1 - $i] + 1 + ($j - (count($result) - $i));
				}
				break;
			}
			$i++;
		}
		return $result;
	}
	
	/**
	* Enumerates all k-combinations of a set containing n elements in lexicographic order e.g. enum_n_choose_k(3, 5) returns {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, 	{1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}
	* @param integer $k, integer $n
	* @return two dimensional array of integers
	*/
	public static function enum_n_choose_k($k, $n) 
	{
		$result = array();
		$first_enum = array();
		for ($i = 1; $i < $k + 1; $i++)
		{
			$first_enum[] = $i;
		}
		$result[] = $first_enum;
		for ($i = 0; $i < self::n_choose_k($k, $n) - 1; $i++)
		{
			$result[] = self::next($result[$i], $n);
		}
		return $result;
	}
	
	/**
	* Calculates the factorial of a number
	* @param integer $n
	* @return integer
	*/
	public static function fact($n)
	{
		if ($n === 0) return 1;
		$result = 1;
		for ($i = 1; $i <= $n; $i++)
		{
			$result *= $i;
		}
		return $result;
	}
	
	/**
	* Calculates n choose k
	* @param integer $k, integer $n
	* @return integer
	*/
	public static function n_choose_k($k, $n)
	{
		if ($k === 0) return 1;
		$result = 1;
		for ($i = 1; $i < $k + 1; $i++) {
			$result *= $n - ($k - $i);
			$result = floor($result / $i);
		}
		return $result;
	}
	
	/**
	* Checks if two numbers are permutations of one another
	* @param integer $a, integer $b
	* @return boolean
	*/
	public static function is_perm($a, $b)
	{
		$aAsStr = strval($a);
		$bAsStr = strval($b);
		if (strlen($aAsStr) !== strlen($bAsStr)) return false;
		$A = array();
		$B = array();
		for ($i = 0; $i < strlen($aAsStr); $i++)
		{
			$A[intval(substr($aAsStr, $i, 1))]++;
			$B[intval(substr($bAsStr, $i, 1))]++;
		}
		for ($i = 0; $i < 10; $i++)
		{
			if ($A[$i] !== $B[$i]) return false;
		}
		return true;
	}
	
	/**
	* Gets all permutations of a string
	* @param string $str
	* @return array of strings
	*/
	public static function permutations_of_a_string($str)
	{
		if (strlen($str) == 2) 
		{
			$result = array();
			$result[0] = $str;
			$result[1] = substr($str, 1) . substr($str, 0, 1);
			return $result;
		}
		return self::all_new_strings2(
										self::permutations_of_a_string(self::remove_char_at($str, strlen($str) - 1)), 
									 	substr($str, strlen($str) - 1)
									);
	}	
}
?>

