import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'vnpt_sdk_ekyc_method_channel.dart';

abstract class VnptSdkEkycPlatform extends PlatformInterface {
  /// Constructs a VnptSdkEkycPlatform.
  VnptSdkEkycPlatform() : super(token: _token);

  static final Object _token = Object();

  static VnptSdkEkycPlatform _instance = MethodChannelVnptSdkEkyc();

  /// The default instance of [VnptSdkEkycPlatform] to use.
  ///
  /// Defaults to [MethodChannelVnptSdkEkyc].
  static VnptSdkEkycPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [VnptSdkEkycPlatform] when
  /// they register themselves.
  static set instance(VnptSdkEkycPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
