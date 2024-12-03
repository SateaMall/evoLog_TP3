package com.example.evoLog_TP3.logging.loggingGenerater;


import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.processing.AbstractProcessor;

public class LoggingInjector extends AbstractProcessor<CtMethod<?>> {
    @Override
    public void process(CtMethod<?> method) {
        if (isDatabaseOperation(method)) { // Define your criteria for a DB operation
            if (method.getSimpleName().equals("getProductById")) {
                // Special handling for getProductById to include product price
                CtCodeSnippetStatement logStatement = createLogStatementWithPrice(method);
                method.getBody().insertBegin(logStatement);
            } else {
                // General logging for other methods
                CtCodeSnippetStatement logStatement = createLogStatement(method);
                method.getBody().insertBegin(logStatement);
            }
        }
    }

    private boolean isDatabaseOperation(CtMethod<?> method) {
        String methodName = method.getSimpleName().toLowerCase();
        return methodName.contains("add") || methodName.contains("get") || methodName.contains("delete") || methodName.contains("update");
    }

    private CtCodeSnippetStatement createLogStatement(CtMethod<?> method) {
        Factory factory = getFactory();
        return factory.Code().createCodeSnippetStatement(
                "logger.info(\"User action logged for user: \" + userId + \" in method: " + method.getSimpleName() + "\");"
        );
    }

    private CtCodeSnippetStatement createLogStatementWithPrice(CtMethod<?> method) {
        Factory factory = getFactory();
        return factory.Code().createCodeSnippetStatement(
                "try {" +
                        "    Product product = productService.getProductById(id);" +
                        "    logger.info(\"User action logged for user: \" + userId + \" in method: " + method.getSimpleName() + " with product price: \" + product.getPrice());" +
                        "} catch (RuntimeException e) {" +
                        "    logger.info(\"User action logged for user: \" + userId + \" in method: " + method.getSimpleName() + " but no product price could be retrieved.\");" +
                        "}"
        );
    }
}