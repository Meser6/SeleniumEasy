package Tests.Helpers;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenshotHelper implements AfterTestExecutionCallback {

    public boolean isFailed;

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        isFailed = extensionContext.getExecutionException().isPresent();
    }
}
