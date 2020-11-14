package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start: " + jointPoint.toString());
        try {
            return jointPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + jointPoint.toString() + " " + timeMs + "ms");
        }
    }
}
