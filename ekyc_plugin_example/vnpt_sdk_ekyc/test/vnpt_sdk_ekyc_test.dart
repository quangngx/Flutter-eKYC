import 'package:flutter_test/flutter_test.dart';
import 'package:vnpt_sdk_ekyc/vnpt_sdk_ekyc.dart';
import 'package:vnpt_sdk_ekyc/vnpt_sdk_ekyc_platform_interface.dart';
import 'package:vnpt_sdk_ekyc/vnpt_sdk_ekyc_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockVnptSdkEkycPlatform
    with MockPlatformInterfaceMixin
    implements VnptSdkEkycPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final VnptSdkEkycPlatform initialPlatform = VnptSdkEkycPlatform.instance;

  test('$MethodChannelVnptSdkEkyc is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelVnptSdkEkyc>());
  });

  test('getPlatformVersion', () async {
    VnptSdkEkyc vnptSdkEkycPlugin = VnptSdkEkyc();
    MockVnptSdkEkycPlatform fakePlatform = MockVnptSdkEkycPlatform();
    VnptSdkEkycPlatform.instance = fakePlatform;

    expect(await vnptSdkEkycPlugin.getPlatformVersion(), '42');
  });
}
