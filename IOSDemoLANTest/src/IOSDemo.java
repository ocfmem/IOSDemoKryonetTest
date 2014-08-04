import net.LocalClient;
import net.LocalServer;

import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;


public class IOSDemo extends UIApplicationDelegateAdapter {

    private UIWindow window = null;
    

    @Override
    public boolean didFinishLaunching(UIApplication application,
            NSDictionary launchOptions) {
       

    	
        final UIButton serverButton = UIButton.create(UIButtonType.RoundedRect);
        serverButton.setFrame(new CGRect(115.0f, 121.0f, 91.0f, 37.0f));
        serverButton.setTitle("Server", UIControlState.Normal);
        serverButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside(UIControl control, UIEvent event) {

            	LocalServer localServer = new LocalServer();
            	localServer.start();
            }
        });
        
        final UIButton clientButton = UIButton.create(UIButtonType.RoundedRect);
        clientButton.setFrame(new CGRect(115.0f, 350, 91.0f, 37.0f));
        clientButton.setTitle("Client", UIControlState.Normal);
        clientButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside(UIControl control, UIEvent event) {
            	 LocalClient localClient = new LocalClient();
            	 localClient.connect();
            }
        });
        
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setBackgroundColor(UIColor.colorLightGray());
        window.addSubview(serverButton);
        window.addSubview(clientButton);
        window.makeKeyAndVisible();
        
        return true;
    }

    public static void main(String[] args) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, IOSDemo.class);
        pool.close();
    }
}