import UIKit
import Flutter
import FinalSDK

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate, ICEkycCameraDelegate {
    var flutterResult: FlutterResult?
    var flutterMethodCall: FlutterMethodCall?
    
    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        
        // Key test on side Product
        SaveData.shared().sdTokenKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKPPS8sTctKjf6XMKXqAbSARctU0eQu5pP5LoUVFC32jfULFKvR9/o4coa2RZ1vldfhcXO/CGucI9p25K9FwlRsCAwEAAQ=="
        SaveData.shared().sdTokenId = "01d58710-720a-2e81-e063-62199f0a9d5f"
//        SaveData.shared().SDUserName = "unknow@vnpt.vn"
//        SaveData.shared().SDPassword = "Abc@1234"
        SaveData.shared().sdAuthorization = ""//
        
        let controller : FlutterViewController = window?.rootViewController as! FlutterViewController
        let channel = FlutterMethodChannel(name: "com.flutter.devekyc/callsdk", binaryMessenger: controller.binaryMessenger)
        channel.setMethodCallHandler({
            (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
            // Note: this method is invoked on the UI thread.
            // Handle battery messages.
            self.flutterResult = result
            
            DispatchQueue.main.async {
                self.openSDKeKYC(controller)
            }
        })
        GeneratedPluginRegistrant.register(with: self)
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
    
    
    
    
    func openSDKeKYC(_ controller:UIViewController) {
        
//        if !Reachability.isConnectedToNetwork() {
//            Toast.showErr("Không có kết nối mạng. Vui lòng kiểm tra lại kết nối")
//            return
//        }
        
        let objCamera = ICEkycCameraRouter.createModule() as! ICEkycCameraViewController
        
//        objCamera.isVersion = .pro
//        objCamera.flowType = .full
//        objCamera.isType = .cmt
        objCamera.cameraDelegate = self
//        objCamera.stepNow = .stepFront
        
        objCamera.unitCustomer = "test1"
        objCamera.resourceCustomer = "VNPT"
        objCamera.challengeCode = "INNOVATIONCENTER"
        
        objCamera.isShowResult = true
        objCamera.isShowHelp = true
        objCamera.isShowTrademark = true
        objCamera.isCheckLivenessCard = true
        objCamera.isCheckMaskFace = true
        objCamera.isAddFace = true
        objCamera.isCheckLivenessFace = true
        objCamera.languageApplication = "vi"
        objCamera.isValidatePostcode = true
        
        objCamera.modalPresentationStyle = .fullScreen
        objCamera.modalTransitionStyle = .coverVertical
        controller.present(objCamera, animated: true, completion: nil)
    }
    
    func getInformationCard() -> String {
        return SaveData.shared().jsonInfo
    }
    
    
    func getResult() {
        
        DispatchQueue.main.async {
            self.flutterResult!(self.getInformationCard())
        }
        
    }
    
}

