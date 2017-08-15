//
//  RCTAesCrypto.m
//  RCTAesCrypto
//
//  Created by RocWang on 2017/8/14.
//  Copyright © 2017年 RocWang. All rights reserved.
//

#import "RCTAesCrypto.h"
#import "SecurityUtil.h"
#import <CommonCrypto/CommonCryptor.h>

@implementation RCTAesCrypto

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(encrypt:(NSString *)string
                  appkey:(NSString *)key
                  gIv:(NSString *)gIv
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    if ([[SecurityUtil encryptAESData:string app_key:key gIv:gIv] length] <= 0) {
        reject(@"ERROR", @"decrypt failed", nil);
    }
    resolve ([SecurityUtil encryptAESData:string app_key:key gIv:gIv]);
}

RCT_EXPORT_METHOD(decrypt:(NSString *)string
                  appkey:(NSString *)key
                  gIv:(NSString *)gIv
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    if ([[SecurityUtil decryptAESNString:string app_key:key gIv:gIv] length] <= 0) {
        reject(@"ERROR", @"decrypt failed", nil);
    }
    resolve ([SecurityUtil decryptAESNString:string app_key:key gIv:gIv]);
}
@end
