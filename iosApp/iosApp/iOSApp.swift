import SwiftUI
import composeApp

@main
struct iOSApp: App {

   init() {
        PlatformSDK().doInit(platformConfiguration: PlatformConfiguration(), driverFactory: DriverFactory(), elevationResolver: ElevationResolver())
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

}
