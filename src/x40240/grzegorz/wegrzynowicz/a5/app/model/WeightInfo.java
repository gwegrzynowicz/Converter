package x40240.grzegorz.wegrzynowicz.a5.app.model;

import java.text.DecimalFormat;

public class WeightInfo {

	public static final String KILOGRAM  = "Kilogram";
	public static final String OUNCE     = "Ounce";
	public static final String POUND     = "Pound";
	
	private double quantity;
	private String fromScale;
	private String toScale;
	DecimalFormat df = new DecimalFormat("#.####");
	
	public double getQuantity () {
	    return quantity;
	}
	
	public void setQuantity (double quantity) {
	    this.quantity = quantity;
	}
	
	public String getFromScale() {
        return fromScale;
    }
    
    public void setFromScale (String fromScale) {
        this.fromScale = fromScale;
    }
    
    public String getToScale() {
        return toScale;
    }
    
    public void setToScale (String toScale) {
        this.toScale = toScale;
    }
    
    public String convert() {
    	Double converted = null;
    	String dispString = "";
    	if(fromScale.compareTo(toScale) == 0)
    		converted = Double.valueOf(quantity);
    	else if(fromScale.compareTo(KILOGRAM) == 0 && toScale.compareTo(OUNCE) == 0)
    		converted = Double.valueOf( quantity * 35.274 );
    	else if(fromScale.compareTo(KILOGRAM) == 0 && toScale.compareTo(POUND) == 0)
    		converted = Double.valueOf( quantity * 2.2046 );
    	else if(fromScale.compareTo(OUNCE) == 0 && toScale.compareTo(KILOGRAM) == 0)
    		converted = Double.valueOf( quantity / 35.274 );
    	else if(fromScale.compareTo(OUNCE) == 0 && toScale.compareTo(POUND) == 0)
    		converted = Double.valueOf( quantity * 0.0625 );
    	else if(fromScale.compareTo(POUND) == 0 && toScale.compareTo(KILOGRAM) == 0)
    		converted = Double.valueOf( quantity / 2.2046 );
    	else if(fromScale.compareTo(POUND) == 0 && toScale.compareTo(OUNCE) == 0)
    		converted = Double.valueOf( quantity * 16 );
    	
    	if (converted != null)
    		dispString = (String) this.getDisplayString(df.format(converted));
    	return dispString;
    }
    
    public String getDisplayString(String num) {
    	final StringBuilder sb = new StringBuilder();
    	sb.append(this.quantity);
        sb.append(" ");
        sb.append(this.fromScale.toString());
        sb.append(" = ");
        sb.append(num);
        sb.append(" ");
        sb.append(this.toScale.toString());
        return sb.toString();
    }
}
