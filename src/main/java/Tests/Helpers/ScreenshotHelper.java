package Tests.Helpers;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenshotHelper implements AfterTestExecutionCallback {

    public boolean isFiled;

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        isFiled = extensionContext.getExecutionException().isPresent();
    }
}
