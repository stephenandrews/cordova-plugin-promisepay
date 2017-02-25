var exec = require('cordova/exec');

module.exports = {

    /**
     * Set publishable key
     * @param key {string} Publishable key
     * @param success {Function} Success callback
     * @param error {Function} Error callback
     */
    setPublishableKey: function(env, key, success, error) {
        exec(success, error, "CordovaPromisePay", "setPublishableKey", [env, key]);
    },

    /**
     * Create a credit card token
     * @param creditCard {Object} Credit card information
     * @param success {Function} Success callback
     * @param error {Function} Error callback
     */
    createCardToken: function(token, number, name, emonth, eyear, ccv, success, error) {
        exec(success, error, "CordovaPromisePay", "createCardToken", [number, name, emonth, eyear, ccv]);
    }

};