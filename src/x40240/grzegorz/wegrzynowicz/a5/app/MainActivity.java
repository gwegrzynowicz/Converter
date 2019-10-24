package x40240.grzegorz.wegrzynowicz.a5.app;

import x40240.grzegorz.wegrzynowicz.a5.app.model.LengthInfo;
import x40240.grzegorz.wegrzynowicz.a5.app.model.TemperatureInfo;
import x40240.grzegorz.wegrzynowicz.a5.app.model.WeightInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	private static final String LOGTAG = MainActivity.class.getSimpleName();
    private final boolean DEBUG = true;
    private String CATEGORY_LENGTH;
    private String CATEGORY_TEMPERATURE;
    private String CATEGORY_WEIGHT;
    
    private Spinner    categorySpinner;
	private EditText   quantityEdit;
	private Spinner    fromSpinner;
	private Spinner    toSpinner;
	private TextView   resultEdit;
	
	String categoryArray [];
	String lengthArray [];
	String temperatureArray [];
	String weightArray [];
	ArrayAdapter<String> fromArrayAdapter;
	ArrayAdapter<String> toArrayAdapter;
	
	Context context; 
	CharSequence text = "Please enter valid quantity";
	int duration = Toast.LENGTH_SHORT;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        context = getApplicationContext();
        categorySpinner = (Spinner) findViewById(R.id.spinner1);
        quantityEdit = (EditText) findViewById(R.id.quantity_edit);
        fromSpinner = (Spinner) findViewById(R.id.spinner2);
        toSpinner = (Spinner) findViewById(R.id.spinner3);
        resultEdit = (TextView) findViewById(R.id.textView6);
        categoryArray = getResources().getStringArray(R.array.category_array);
        lengthArray = getResources().getStringArray(R.array.length_array);
        temperatureArray = getResources().getStringArray(R.array.temperature_array);
        weightArray = getResources().getStringArray(R.array.weight_array);
        CATEGORY_LENGTH = categoryArray[0];
        CATEGORY_TEMPERATURE = categoryArray[1];
        CATEGORY_WEIGHT = categoryArray[2];
        categorySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onPause() {
        if (DEBUG) Log.d(LOGTAG, "onPause()");
        super.onPause();
    }
    
    @Override
    protected void onResume() {
    	if (DEBUG) Log.d(LOGTAG, "onResume()");
	    super.onResume();
	    clearData();
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_exit:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void onClearButtonClick(View view) {
    	if (DEBUG) Log.d(LOGTAG, "onClearButtonClick()");
		clearData();
	}
    
    public void onConvertButtonClick(View view) {
    	Log.d(LOGTAG, "quantityEdit=" + quantityEdit.getText().toString());
    	String catSelected = ((String)categorySpinner.getItemAtPosition(categorySpinner.getSelectedItemPosition()));
    	LengthInfo length = null;
    	TemperatureInfo temperature = null;
    	WeightInfo weight = null;
    	String result = "";
    	try {
	    	if (catSelected.equals(CATEGORY_LENGTH)) {
	    		length = new LengthInfo();
	    		length.setQuantity(Double.parseDouble(quantityEdit.getText().toString()));
	    		length.setFromScale((String)fromSpinner.getItemAtPosition(fromSpinner.getSelectedItemPosition()));
	    		length.setToScale((String)toSpinner.getItemAtPosition(toSpinner.getSelectedItemPosition()));
	    		result = length.convert();
	        }
	    	if (catSelected.equals(CATEGORY_TEMPERATURE)) {
	    		temperature = new TemperatureInfo();
	    		temperature.setQuantity(Double.parseDouble(quantityEdit.getText().toString()));
	    		temperature.setFromScale((String)fromSpinner.getItemAtPosition(fromSpinner.getSelectedItemPosition()));
	    		temperature.setToScale((String)toSpinner.getItemAtPosition(toSpinner.getSelectedItemPosition()));
	    		result = temperature.convert();
	       	}
	    	if (catSelected.equals(CATEGORY_WEIGHT)) {
	    		weight = new WeightInfo();
	    		weight.setQuantity(Double.parseDouble(quantityEdit.getText().toString()));
	    		weight.setFromScale((String)fromSpinner.getItemAtPosition(fromSpinner.getSelectedItemPosition()));
	    		weight.setToScale((String)toSpinner.getItemAtPosition(toSpinner.getSelectedItemPosition()));
	    		result = weight.convert();
	    	}
    	}
    	catch (Exception e) {
    		Toast.makeText(context, text, duration).show();
    		e.printStackTrace();
        }
    	resultEdit.setText(result);
	}
    
    private void clearData() {
    	if (DEBUG) Log.d(LOGTAG, "clearData()");
    	quantityEdit.setText(null);
	    resultEdit.setText(null);
	}
    
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	if (DEBUG) Log.d(LOGTAG, "onItemSelected()");
    	String selected = parent.getItemAtPosition(position).toString();
    	if (selected.equals(CATEGORY_LENGTH)) {
            fromArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lengthArray);
            toArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lengthArray);
    	}
    	if (selected.equals(CATEGORY_TEMPERATURE)) {
            fromArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, temperatureArray);
            toArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, temperatureArray);
    	}
    	if (selected.equals(CATEGORY_WEIGHT)) {
            fromArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weightArray);
            toArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weightArray);
    	}
    	fromSpinner.setAdapter(fromArrayAdapter);
    	toSpinner.setAdapter(toArrayAdapter);
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {}
}
