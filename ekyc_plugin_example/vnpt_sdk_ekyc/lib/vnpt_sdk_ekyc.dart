
import 'vnpt_sdk_ekyc_platform_interface.dart';

class VnptSdkEkyc {
  Future<String?> getPlatformVersion() {
    return VnptSdkEkycPlatform.instance.getPlatformVersion();
  }
}
