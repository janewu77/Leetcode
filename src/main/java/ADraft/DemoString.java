package ADraft;

import java.lang.reflect.Field;

public class DemoString {

    public static void main(String[] args) throws Exception{
        String xx = "𝄞";
        System.out.println(xx.length());

        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));

        String str3 = new String("abc");
        System.out.println(str2 == str3);//False
        System.out.println(str2.equals(str3));

        String str4 ="a"+"b"+"c";
        System.out.println(str2 == str4);
        System.out.println(str2.equals(str4));

        String str5 ="ab";
        String str6 =str5+"c";
        System.out.println(str2 == str6); //False
        System.out.println(str2.equals(str6));

        String str = "Hello Python";
        System.out.println(str); // Hello Python

        // 改变"不可变"的字符串（骚操作！）
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);

        char[] value = (char[])field.get(str);
        value[6] = 'J';
        value[7] = 'a';
        value[8] = 'v';
        value[9] = 'a';
        value[10] = '!';
//        value[11] = '!';
        System.out.println(str); // Hello Java!!

        System.out.println("==================");

        String ss = "abcdd";
        char[] aa = ss.toCharArray();
        for (int i=0; i< aa.length; i++){
            System.out.println(aa[i]);
        }

    }
}
