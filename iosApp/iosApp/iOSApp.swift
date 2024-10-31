import SwiftUI
import composeApp

@main
struct iOSApp: App {

   init() {
        PlatformSDK().doInit(platformConfiguration: PlatformConfiguration())
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

}
