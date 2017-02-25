package cordova.plugins.promisepay;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.github.promisepay.PromisePay;

public class CordovaPromisePay extends CordovaPlugin {

    private PromisePay promisePay;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        promisePay = PromisePay.getInstance();
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("setPublishableKey")) {
            setPublishableKey(data.getString(0), data.getString(1), callbackContext);
        } else if (action.equals("createCardToken")) {
            createCardToken(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), callbackContext);
        } else {
            return false;
        }

        return true;

    }

    private void setPublishableKey(String env, String key, final CallbackContext callbackContext) {

        try {
            promisePay.initialize(env, key);
            callbackContext.success();
        } catch (AuthenticationException e) {
            callbackContext.error(e.getMessage());
        }

    }

    private void createCardToken(String token, String number, String fullName, String expiryMonth, String expiryYear, String cvv, final CallbackContext callbackContext) {

        try {
			PPCard card = new PPCard(number, fullName, expiryMonth, expiryYear, cvv);
			promisePay.createCardAccount(token, card, new PromisePay.OnPromiseRequestListener() {
				@Override
				public void onSuccess(JSONObject response) {
					callbackContext.success(response.toString());
				}

				@Override
				public void onError(Exception e) {
					callbackContext.error(e.getMessage());
				}
			});

        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }

    }

}
