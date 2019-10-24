package x40240.grzegorz.wegrzynowicz.a5.app.model;

import java.text.DecimalFormat;

public class TemperatureInfo {

	public static final String CELSIUS    = "Celsius";
	public static final String FAHRENHEIT = "Fahrenheit";
	public static final String KELVIN     = "Kelvin";
	
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
    	else if(fromScale.compareTo(CELSIUS) == 0 && toScale.compareTo(FAHRENHEIT) == 0)
    		converted = Double.valueOf( quantity * 1.8 + 32 );
    	else if(fromScale.compareTo(CELSIUS) == 0 && toScale.compareTo(KELVIN) == 0)
    		converted = Double.valueOf( quantity + 273.15 );
    	else if(fromScale.compareTo(FAHRENHEIT) == 0 && toScale.compareTo(CELSIUS) == 0)
    		converted = Double.valueOf( ( quantity - 32 ) / 1.8 );
    	else if(fromScale.compareTo(FAHRENHEIT) == 0 && toScale.compareTo(KELVIN) == 0)
    		converted = Double.valueOf( (quantity + 459.67) / 1.8 );
    	else if(fromScale.compareTo(KELVIN) == 0 && toScale.compareTo(CELSIUS) == 0)
    		converted = Double.valueOf( quantity - 273.15 );
    	else if(fromScale.compareTo(KELVIN) == 0 && toScale.compareTo(FAHRENHEIT) == 0)
    		converted = Double.valueOf( quantity * 1.8 - 459.67 );
    	
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
