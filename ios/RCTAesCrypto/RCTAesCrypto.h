//
//  RCTAesCrypto.h
//  RCTAesCrypto
//
//  Created by RocWang on 2017/8/14.
//  Copyright © 2017年 RocWang. All rights reserved.
//

#import <Foundation/Foundation.h>
#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#elif __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#elif __has_include("React/RCTBridgeModule.h")
#import "React/RCTBridgeModule.h"
#endif
@interface RCTAesCrypto : NSObject<RCTBridgeModule>

@end
