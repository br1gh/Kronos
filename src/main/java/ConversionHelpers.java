public class ConversionHelpers
{
    public static String integerToString(Integer n)
    {
        if ( n == null ) {
            return "null";
        }
        else {
            return String.valueOf(n);
        }
    }
}
