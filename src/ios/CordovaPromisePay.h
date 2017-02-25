#import <Cordova/CDV.h>

@interface CordovaPromisePay : CDVPlugin

- (void) setPublishableKey:(CDVInvokedUrlCommand*)command;
- (void) createCardToken:(CDVInvokedUrlCommand*)command;

@end
