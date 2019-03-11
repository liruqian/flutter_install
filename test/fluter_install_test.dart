import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:fluter_install/fluter_install.dart';

void main() {
  const MethodChannel channel = MethodChannel('fluter_install');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FluterInstall.platformVersion, '42');
  });
}
