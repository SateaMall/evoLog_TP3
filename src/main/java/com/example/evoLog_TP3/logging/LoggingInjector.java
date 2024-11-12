package com.example.evoLog_TP3.logging;


import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.processing.AbstractProcessor;

public class LoggingInjector extends AbstractProcessor<CtMethod<?>> {
    @Override
    public void process(CtMethod<?> method) {
        if (isDatabaseOperation(method)) { // Define your criteria for a DB operation
            CtCodeSnippetStatement logStatement = createLogStatement(method);
            method.getBody().insertBegin(logStatement); // Inject logging at the beginning of each method
        }
    }

    private boolean isDatabaseOperation(CtMethod<?> method) {
        String methodName = method.getSimpleName().toLowerCase();
        return methodName.contains("add") || methodName.contains("get") || methodName.contains("delete");
    }

    private CtCodeSnippetStatement createLogStatement(CtMethod<?> method) {
        Factory factory = getFactory();
        CtCodeSnippetStatement logStatement = factory.Code().createCodeSnippetStatement(
                "logger.info(\"User action logged for method: " + method.getSimpleName() + "\")"
        );
        return logStatement;
    }
}