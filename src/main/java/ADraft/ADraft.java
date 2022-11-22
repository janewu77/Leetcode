package ADraft;

public class ADraft {

    public static void main(String[] args) throws Exception{


        test_replaceSpace();
    }

    public static void test_replaceSpace(){

        String str_1 = "ab c de    X";
        String result_1 = new ADraft().replaceSpace(str_1, str_1.length()-1);
        System.out.println("["+result_1.equals("ab%20c%20deX") + "]["+ str_1 + "] ==> [" +result_1+"]");

        String str_2 = "ab c de     X";
        String result_2 = new ADraft().replaceSpace(str_2, str_2.length()-2);
        System.out.println("["+result_2.equals("ab%20c%20de X") + "]["+ str_2 + "] ==> [" +result_2+"]");

        String str_3 = "abde      X";
        String result_3 = new ADraft().replaceSpace(str_3, str_3.length()-1);
        System.out.println("["+result_3.equals("abde%20%20X") + "]["+ str_3 + "] ==> [" +result_3+"]");

    }

    /**
     * URLify: Write a method to replace all spaces in a string with '%20'.
     * You may assume that the string has sufficient space at the end to
     * hold the additional characters,and that you are given the "true" length of the string.
     * (Note: If implementing in Java,please use a character array so that you can perform
     * this operation in place.)
     * EXAMPLE
     * Input: "Mr John Smith ", 13 Output: "Mr%20John%20Smith"
     * @param x Input 待替换的字串
     * @param x_len  the "true" length of the string. 替换后的实际长度
     * @return
     */
    public String replaceSpace(String x, int x_len){
        char[] xx = x.toCharArray();
        int c = 0, j = 0;
        while (j + c * 3 < x_len)
            if (xx[j++] == ' ') c++;

        System.out.println("the number of spaces："+c);

        int p1 = x_len - c * 2 - 1, p2 = x_len - 1;
        while(p1 > 0){
            if (xx[p1] == ' '){
                xx[p2--] = '0';
                xx[p2--] = '2';
                xx[p2--] = '%';
                p1--;
            }else{
                xx[p2--] = xx[p1--];
            }
        }

        return String.copyValueOf(xx);
    }

    public  void textChar() throws Exception{
        char charA = 'a';
        int charAInt=Character.getNumericValue(charA);
        System.out.println("charA:"+charA);
        System.out.println("charAInt:"+charAInt);

        char charB = 'a'+1;
        System.out.println("charB:"+charB);


        char charOne = '1';
        int charOneInt=Character.getNumericValue(charOne);
        System.out.println("'1':"+charOne);
        System.out.println("charOneInt:"+charOneInt);

        char charTwo =(char) (charOne +1);
        System.out.println("charTwo:"+charTwo);

    }


}
