import UIKit
import ActivityKit
import SwiftUI
import shared
import LocalAuthentication

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()

    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct RepresentedMyView: UIViewRepresentable {
    typealias UIViewType = ViewCustomIos

    func makeUIView(context: Context) -> ViewCustomIos {
        let view = ViewCustomIos()

        // Do some configurations here if needed.
        return view
    }

    func updateUIView(_ uiView: ViewCustomIos, context: Context) {
        // Updates the state of the specified view controller with new information from SwiftUI.
    }
}

struct ContentView: View {

    @available(iOS 16.1, *)
    weak public var activityLive: Activity<LiveWidgetDataBean>? {
        get {
            return _activityLive as? Activity<LiveWidgetDataBean>
        } set {

            _activityLive = newValue
        }

    }
    @State var _activityLive: Any? = nil


    init(){
    /*     let vcBiometric = UIHostingController(rootView: BiometricView().edgesIgnoringSafeArea(.leading).edgesIgnoringSafeArea(.trailing).edgesIgnoringSafeArea(.bottom))
        UtilNativeToCompose.Companion.shared.setCustomViewNativeBiometricFactory(factory: vcBiometric.view)
 */
    }

    var body: some View {



        ZStack {

            // shared commonMain
            let commonColor = CommonColor()

            // util swift
            let utilUi = UiColorFromName()


            let my = utilUi.named(commonColor.getPrimaryColour())

            // imposta il color per la top bar
            Color(my!).edgesIgnoringSafeArea(.top)

            //PickerIosView()
            //ContentViewPhoto()
            //BiometricView()

               ComposeView()
                        .ignoresSafeArea(.keyboard)
                        .edgesIgnoringSafeArea(.bottom)
                        .edgesIgnoringSafeArea(.leading)
                        .edgesIgnoringSafeArea(.trailing)
                        .onReceive(timer) { _ in
                        }
        }
        .onAppear {

        }
    }
}



struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
