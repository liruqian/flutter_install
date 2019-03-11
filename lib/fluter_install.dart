import 'dart:async';

import 'package:flutter/services.dart';

class FluterInstall {
  static const MethodChannel _channel =
      const MethodChannel('fluter_install');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

   static   installApk(String path) async {
     await _channel.invokeMethod('install',{"path":path});
  }
}
