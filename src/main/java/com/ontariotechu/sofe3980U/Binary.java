package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// uncomment the following code
		
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
  		
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	/**
	* Performing bitwise logical OR operation over two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with the result of <i>num1 OR num2</i>.
	*/
	public static Binary or(Binary num1, Binary num2)
	{
		// Pad the shorter number with leading zeros to match lengths
		String bin1 = num1.number;
		String bin2 = num2.number;
		
		int maxLen = Math.max(bin1.length(), bin2.length());
		
		// Pad with leading zeros
		while (bin1.length() < maxLen) {
			bin1 = "0" + bin1;
		}
		while (bin2.length() < maxLen) {
			bin2 = "0" + bin2;
		}
		
		String result = "";
		for (int i = 0; i < maxLen; i++) {
			char bit1 = bin1.charAt(i);
			char bit2 = bin2.charAt(i);
			
			// OR operation: result is '1' if either bit is '1'
			if (bit1 == '1' || bit2 == '1') {
				result += "1";
			} else {
				result += "0";
			}
		}
		
		Binary orResult = new Binary(result);
		return orResult;
	}
	
	/**
	* Performing bitwise logical AND operation over two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with the result of <i>num1 AND num2</i>.
	*/
	public static Binary and(Binary num1, Binary num2)
	{
		// Pad the shorter number with leading zeros to match lengths
		String bin1 = num1.number;
		String bin2 = num2.number;
		
		int maxLen = Math.max(bin1.length(), bin2.length());
		
		// Pad with leading zeros
		while (bin1.length() < maxLen) {
			bin1 = "0" + bin1;
		}
		while (bin2.length() < maxLen) {
			bin2 = "0" + bin2;
		}
		
		String result = "";
		for (int i = 0; i < maxLen; i++) {
			char bit1 = bin1.charAt(i);
			char bit2 = bin2.charAt(i);
			
			// AND operation: result is '1' only if both bits are '1'
			if (bit1 == '1' && bit2 == '1') {
				result += "1";
			} else {
				result += "0";
			}
		}
		
		Binary andResult = new Binary(result);
		return andResult;
	}
	
	/**
	* Multiply two binary variables. Uses the add function to perform multiplication
	* through repeated addition.
	*
	* @param num1 The first operand (multiplicand)
	* @param num2 The second operand (multiplier)
	* @return A binary variable with the result of <i>num1 * num2</i>.
	*/
	public static Binary multiply(Binary num1, Binary num2)
	{
		Binary result = new Binary("0");
		
		// Handle edge case: if either number is 0, result is 0
		if (num1.number.equals("0") || num2.number.equals("0")) {
			return result;
		}
		
		// Multiply using repeated addition
		// For each bit position in num2, if bit is 1, add num1 shifted by that position
		String bin2 = num2.number;
		int powerOfTwo = 0;
		
		// Process bits from right to left
		for (int i = bin2.length() - 1; i >= 0; i--) {
			if (bin2.charAt(i) == '1') {
				// Shift num1 left by powerOfTwo positions (add powerOfTwo zeros to the right)
				String shiftedNum1 = num1.number;
				for (int j = 0; j < powerOfTwo; j++) {
					shiftedNum1 += "0";
				}
				Binary shiftedBinary = new Binary(shiftedNum1);
				result = add(result, shiftedBinary);
			}
			powerOfTwo++;
		}
		
		return result;
	}
}	
