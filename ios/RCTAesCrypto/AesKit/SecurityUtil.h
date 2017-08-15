//
//  SecurityUtil.h
//  Smile
//
//  Created by 蒲晓涛 on 12-11-24.
//  Copyright (c) 2012年 BOX. All rights reserved.
//

// 版权属于原作者
// http://code4app.com (cn) http://code4app.net (en)
// 发布代码于最专业的源码分享网站: Code4App.com

#import <Foundation/Foundation.h>

@interface SecurityUtil : NSObject 

#pragma mark - base64
+ (NSString*)encodeBase64String:(NSString *)input;
+ (NSString*)decodeBase64String:(NSString *)input;

+ (NSString*)encodeBase64Data:(NSData *)data;
+ (NSString*)decodeBase64Data:(NSData *)data;

#pragma mark - AES加密
//将string转成带密码的data
+ (NSString*)encryptAESData:(NSString*)string app_key:(NSString*)key gIv:(NSString *)gIv;

//将带密码的data转成string
+(NSString*)decryptAESData:(NSData*)data app_key:(NSString*)key gIv:(NSString *)gIv;
+(NSData*)decryptAESWithData:(NSData*)data  app_key:(NSString*)key gIv:(NSString *)gIv;
// 将带密码的NSString转成string 解密
+(NSString*)decryptAESNString:(NSString *)str app_key:(NSString *)key gIv:(NSString *)gIv;


@end
