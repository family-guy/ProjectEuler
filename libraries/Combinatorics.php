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
		$numerator = 1;
		$denominator = 1;
		for ($i = 1; $i < $k + 1; $i++)
		{
			$numerator *= $n - ($k - $i);
			$denominator *= $i;
		}
		return $numerator / $denominator;
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

