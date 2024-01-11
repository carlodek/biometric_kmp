//
//  JTouchID.m
//  JTouchID
//

#import "JTouchID.h"

#import <LocalAuthentication/LocalAuthentication.h>

int32_t touchidSupported(void) {
    BOOL supportsTouchID = [[LAContext new] canEvaluatePolicy:LAPolicyDeviceOwnerAuthenticationWithBiometrics  error:nil];
    NSLog(@"Supports TouchID: %d", supportsTouchID);
    return supportsTouchID;
}

void touchidAuthenticate(const char* msg, auth_cb_t callback) {
    NSLog(@"Authenticate with biometrics");
    NSString* reason = [NSString stringWithCString:msg encoding:NSUTF8StringEncoding];
    [[LAContext new] evaluatePolicy:LAPolicyDeviceOwnerAuthentication
                    localizedReason:reason
                              reply:^(BOOL success, NSError * _Nullable error) {
        if (success) {
            NSLog(@"Successfully authenticated with biometrics");
            callback(YES, 0);
        } else {
            NSLog(@"Biometric authentication failed: %@", error);
            callback(NO, (int32_t)error.code);
        }
    }];
}
