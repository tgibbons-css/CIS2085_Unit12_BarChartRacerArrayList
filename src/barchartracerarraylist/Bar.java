package barchartracerarraylist;

public class Bar implements Comparable<Bar> {
    private String name;
    private int value;
    private String category;

    // Creates a new bar.
    public Bar(String inName, int inValue, String inCategory)
    {
        name = inName;
        value = inValue;
        category = inCategory;
    }

    // Returns the name of this bar.
    public String getName()
    {
        return name;
    }

    // Returns the value of this bar.
    public int getValue()
    {
        return value;
    }

    // Returns the category of this bar.
    public String getCategory()
    {
        return category;
    }

    // Compare two bars by value.
    @Override
    public int compareTo(Bar that)
    {
        int result = 0;
        /*
        if (value < that.value)
        {
            result = 1;
        }
        else if (value > that.value)
        {
            result = -1;
        }
        */
        
        if (that != null)
        {
            Integer thisValue = value;
            Integer thatValue = that.value;
            result = -1 * thisValue.compareTo(thatValue);
        }
        
        return result;
    }
}
