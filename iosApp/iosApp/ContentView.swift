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


struct ContentView: View {
    init(){
    /*     let vcBiometric = UIHostingController(rootView: BiometricView().edgesIgnoringSafeArea(.leading).edgesIgnoringSafeArea(.trailing).edgesIgnoringSafeArea(.bottom))
        UtilNativeToCompose.Companion.shared.setCustomViewNativeBiometricFactory(factory: vcBiometric.view)
 */
    }

    var body: some View {



        ZStack {
            //BiometricView()

               ComposeView()
                        .ignoresSafeArea(.keyboard)
                        .edgesIgnoringSafeArea(.bottom)
                        .edgesIgnoringSafeArea(.leading)
                        .edgesIgnoringSafeArea(.trailing)
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
