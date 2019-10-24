package x40240.grzegorz.wegrzynowicz.a5.app.model;

import java.text.DecimalFormat;

public class LengthInfo {

	public static final String CENTIMETER = "Centimeter";
	public static final String METER      = "Meter";
	public static final String INCH       = "Inch";
	public static final String FEET       = "Feet";
	
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
    	else if(fromScale.compareTo(CENTIMETER) == 0 && toScale.compareTo(METER) == 0)
    		converted = Double.valueOf(quantity / 100);
    	else if(fromScale.compareTo(CENTIMETER) == 0 && toScale.compareTo(INCH) == 0)
    		converted = Double.valueOf(quantity * 0.3937);
    	else if(fromScale.compareTo(CENTIMETER) == 0 && toScale.compareTo(FEET) == 0)
    		converted = Double.valueOf(quantity * 0.0328);
    	else if(fromScale.compareTo(METER) == 0 && toScale.compareTo(CENTIMETER) == 0)
    		converted = Double.valueOf(quantity * 100);
    	else if(fromScale.compareTo(METER) == 0 && toScale.compareTo(INCH) == 0)
    		converted = Double.valueOf(quantity * 39.37);
    	else if(fromScale.compareTo(METER) == 0 && toScale.compareTo(FEET) == 0)
    		converted = Double.valueOf(quantity * 3.2808);
    	else if(fromScale.compareTo(INCH) == 0 && toScale.compareTo(CENTIMETER) == 0)
    		converted = Double.valueOf(quantity * 2.54);
    	else if(fromScale.compareTo(INCH) == 0 && toScale.compareTo(METER) == 0)
    		converted = Double.valueOf(quantity * 0.0254);
    	else if(fromScale.compareTo(INCH) == 0 && toScale.compareTo(FEET) == 0)
    		converted = Double.valueOf(quantity * 0.0833);
    	else if(fromScale.compareTo(FEET) == 0 && toScale.compareTo(CENTIMETER) == 0)
    		converted = Double.valueOf(quantity * 30.48);
    	else if(fromScale.compareTo(FEET) == 0 && toScale.compareTo(METER) == 0)
    		converted = Double.valueOf(quantity * 0.3048);
    	else if(fromScale.compareTo(FEET) == 0 && toScale.compareTo(INCH) == 0)
    		converted = Double.valueOf(quantity * 12);
    	
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
