package HW_1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * @author : Syed Mahvish (CWID : 10456845)
 * This program will accept Binary number.
 * Perform basic operation like shift to Right, convert it to decimal and add two Binary number.
 */

class BinaryNumber{
    private int[] data;
    private boolean overflow = false;


    /***
     * This constructor will instantiate data array.
     * @param length create data array of length of length
     */
    BinaryNumber(int length) {
        length = getValidNumericValue(length, 1 , Integer.MAX_VALUE, "");
        setDefaultDataArray(length);
    }

    /***
     * This constructor will initialize data array with String value.
     * @param str value to be store in data array.
     */
    BinaryNumber(String str){
        setData(str);
    }

    /***
     * This method set default data array for default constructor.
     * It assign zero value to data array.
     * @param length specify length of default data array.
     */
    private void setDefaultDataArray(int length){
        data = new int[length];
        Arrays.fill(data, 0);
        System.out.println("Default Binary number is:" + toString());
    }

    /***
     * This method will initialize data array with String value.
     * Checks if string and data array lenght is same.
     * @param str stores value in given array.
     */
    public void setData(String str) {
        str = getValidBinaryInput(str);
        data = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    /***
     * This method checks validation of given numeric integer value.
     * It checks if input number lies between given min and max limit.
     * @param num whose validation needs to be check.
     * @param minLimit minimum valid integer value for input num.
     * @param maxLimit maximum valid integer value for input num.
     * @param msg Print message for ask for valid input.
     * @return
     */
    public int getValidNumericValue(int num, int minLimit, int maxLimit, String msg){
        boolean flag = false;

        Scanner scan = new Scanner(System.in);

        while (!flag){
            try {
                if(num >= minLimit && num < maxLimit){
                    flag = true;
                }else{
                    System.out.println("Enter valid value " + msg + " :");
                    num = scan.nextInt();
                }
            }catch (InputMismatchException e){
                 //clears buffer.
                 scan.nextLine();
                 System.out.println(e.getMessage());
            }
        }
        return num;
    }


    /***
     * This method allows user to enter binary number and stores them in string format.
     * Also checks if entered string is valid binary number or not
     * @return Valid binary number in string format.
     */
    public String getValidBinaryInput(String str){
        Scanner scan = new Scanner(System.in);

        while (true) {
            if (isValidBinaryString(str)) {
                return str;
            } else {
                System.out.println(str + " is invalid binary number. Please enter valid binary number.");
                str = scan.nextLine();
            }
        }

    }

    /***
     * This method checks validity of binary number string.
     * If binary number string is invalid, it prompts message.
     * @param str binary number string whose validity needs to be check.
     * @return boolean value if provided string is valid binary number or not.
     */
    public boolean isValidBinaryString(String str){
        if(str.length() == 0) return false;

        for(int i = 0 ; i < str.length() ; i++) {
            int temp = Character.getNumericValue(str.charAt(i));
            if(temp == 0 || temp == 1){}
            else return false;
        }

        return true;
    }


    /***
     * This method returns length of binary number.
     * @return length of binary number.
     */
    public int getLength(){
        return data.length;
    }

    /***
     * This method returns value of binary digit at provided index.
     * It also checks if index is out of bound or not. If so, display an appropriate message indicating same.
     * @param index specify index of which binary digit.
     * @return value of binary digit at provided index. If index is out of bound returns -1.
     */
    public int getDigit(int index) {
        String msg = "(between 0 and " + getLength() + ")";
        index = getValidNumericValue(index, 0, getLength() , msg);
        return data[index];
    }

    /***
     * This method converts binary number to decimal.
     * @return decimal value of given binary number.
     */
    public int toDecimal(){
        int sum = 0 , j = 0;
        for(int i = data.length - 1 ; i >= 0 && j < data.length ; i--) {
            sum = (int)(sum + (data[j++] * Math.pow(2 , i)));
        }
        return sum;
    }


    /***
     * This method shifts data array to right for specified amount in parameter.
     * Perform padding on shift places with zeros.
     * @param amount specify number of right shift.
     */
    public void shiftR(int amount){
        int j = 0;
        String msg = "greater than 0";
        amount = getValidNumericValue(amount , 1 , Integer.MAX_VALUE, msg);

        // creating copy of array with specified shift amount.
        data = Arrays.copyOf(data, data.length + amount);
        int size = getLength() - 1;

        for (int i = size; i >= 0; i--) {
            if (j < (getLength() - amount))
                data[size - j] = data[size - j - amount];
            else
                data[i] = 0;
            j++;

        }

        //Print binary number after shift.
        System.out.println("After shifting " + amount + " places right :" + toString());
    }


    /***
     * This method adds two binary number. Also checks if addition creates overflow.
     * If so it handle using overflow flag.
     * When overflow is true, data array length is incremented by 1 to stores carry value in that.
     * @param aBinaryNumber second binary object to perform addiiton.
     */
    public void add(BinaryNumber aBinaryNumber) {

        int firstNumberLength = getLength();
        int carryValue = 0;
        int firstDigit, secondDigit;
        int[] sum = new int[firstNumberLength];
        int j = 0;


        // checks if both binary numbers are of same length. If not prompt message.
        if(firstNumberLength == aBinaryNumber.getLength()){

            // perform addition of each binary digit.
            for(int i = firstNumberLength - 1 ; i >= 0 ; i--){
                firstDigit = getDigit(i);
                secondDigit = aBinaryNumber.getDigit(i);

                sum[j++] = (firstDigit + secondDigit + carryValue) % 2;
                //store carry.
                carryValue = (firstDigit + secondDigit + carryValue) / 2;
            }

            //check if overflow is performed.
            if(carryValue == 1) {
                overflow = true;
            }


            //If so increase data array length and store carry value.
            if(overflow) {
                aBinaryNumber.data = Arrays.copyOf(sum, firstNumberLength + 1);
                aBinaryNumber.data[aBinaryNumber.getLength() - 1] = 1;
                //clears overflow flag.
                clearOverFlow();
            }else{
                aBinaryNumber.data = sum.clone();
            }

            //prints result
            System.out.println("Addition of two binary number is: ");

            int i = 0;
            j = aBinaryNumber.getLength() - 1;
            int[] temp = aBinaryNumber.data.clone();

            while (i < temp.length){
                aBinaryNumber.data[i++] = temp[j--];
            }

            System.out.println( aBinaryNumber.toString());
        }else{
            System.out.println("The length of given binary number does not match with second binary number. Please provide binary number of length " + getLength());
        }
    }


    /***
     * This method set overflow flag to false.
     */
    public void clearOverFlow(){
        overflow = false;
    }


    /***
     * This method overrides toString() method and returns binary number in String format.
     * @return String of binary number.
     */
    @Override
    public  String toString(){

        String binaryNumber = "";
        for (int i = 0; i < data.length; i++) {
            binaryNumber += data[i];
        }
        return binaryNumber;
    }
}