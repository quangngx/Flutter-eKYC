import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'vnpt_sdk_ekyc_platform_interface.dart';

/// An implementation of [VnptSdkEkycPlatform] that uses method channels.
class MethodChannelVnptSdkEkyc extends VnptSdkEkycPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('vnpt_sdk_ekyc');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
