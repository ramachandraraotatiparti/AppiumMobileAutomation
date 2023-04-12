package base;


import com.automate.utils.AppiumServerManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;

public class BaseTest {

  protected BaseTest() {
  }

  @BeforeSuite(alwaysRun = true)
  protected void beforeSuite() {
    AppiumServerManager.startAppiumServer();
  }

  @Parameters({"platformName", "udid", "deviceName", "systemPort", "chromeDriverPort", "emulator", "wdaLocalPort",
    "webkitDebugProxyPort"})
  @BeforeMethod
  protected void setUp(String platformName, String udid, String deviceName, @Optional("androidOnly") String systemPort,
                       @Optional("androidOnly") String chromeDriverPort, @Optional("androidOnly") String emulator,
                       @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) {
    PlatformManager.setPlatformName(platformName);
    DeviceManager.setDeviceName(deviceName);

    }
  }

  @AfterMethod
  protected void tearDown(ITestResult result) {
    DriverFactory.quitDriver();
  }

  @AfterSuite(alwaysRun = true)
  protected void afterSuite() {
    AppiumServerManager.stopAppiumServer();
  }
}
