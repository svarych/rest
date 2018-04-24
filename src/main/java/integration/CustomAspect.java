package integration;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

@SuppressWarnings("unused")
@Aspect
public class CustomAspect {

    private static AllureLifecycle lifecycle;

    @Pointcut("execution(* com.pages.*.*(..))")  // -> (2)
    public void anyMethod() {
        //pointcut body, should be empty
    }

    @Around("anyMethod()")
    public Object step(ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String name = joinPoint.getArgs().length > 0
                ? String.format("%s (%s)", methodSignature.getName(), arrayToString(joinPoint.getArgs())) // -> (1)
                : methodSignature.getName();
        final String uuid = UUID.randomUUID().toString();
        final StepResult result = new StepResult()
                .withName(name);
        getLifecycle().startStep(uuid, result);
        try {
            final Object proceed = joinPoint.proceed();
            getLifecycle().updateStep(uuid, s -> s.withStatus(Status.PASSED));
            return proceed;
        } catch (Throwable e) {
            getLifecycle().updateStep(uuid, s -> s
                    .withStatus(getStatus(e).orElse(Status.BROKEN))
                    .withStatusDetails(getStatusDetails(e).orElse(null)));
            throw e;
        } finally {
            getLifecycle().stopStep(uuid);
        }

    }

    public static AllureLifecycle getLifecycle() {
        if (Objects.isNull(lifecycle)) {
            lifecycle = Allure.getLifecycle();
        }
        return lifecycle;
    }

    private static String arrayToString(final Object... array) {
        return Stream.of(array)
                .map(object -> {
                    if (object.getClass().isArray()) {
                        return arrayToString((Object[]) object);
                    }
                    return Objects.toString(object);
                })
                .collect(Collectors.joining(", "));
    }

}
